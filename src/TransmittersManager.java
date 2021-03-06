import java.io.FileWriter;
import java.util.Scanner;

public class TransmittersManager 
{
	static final int NUM_OF_FILE_TYPES = 2;
	static final int NUM_OF_DFs = 3;
	static final int NUM_OF_NODF_COMBINATIONS = 4;
	
	// Data Members
	private Writable[] m_Writer;
	
	// Constructor
	public TransmittersManager()
	{
		m_Writer = new Writable[NUM_OF_FILE_TYPES];
		m_Writer[0] = new CSVWriter();
		m_Writer[1] = new SQLWriter();
	}
	
	// Private Functions
	boolean AdjustSensorsCombination(int CombinationIndex, Transmitter t, boolean ContinueFlag, DF BaseDFs[])
	{		
		ContinueFlag = true; // Make Sure Flag Is True For All Cases Except The Last
		
		switch (CombinationIndex)
		{
		case 0: // 1 2 3
			break;
		case 1: // 1 3 2
			t.SetDFRef(0, BaseDFs[0]);
			t.SetDFRef(1, BaseDFs[2]);
			t.SetDFRef(2, BaseDFs[1]);
			break;
		case 2: // 2 1 3
			t.SetDFRef(0, BaseDFs[1]);
			t.SetDFRef(1, BaseDFs[0]);
			t.SetDFRef(2, BaseDFs[2]);
			break;
		case 3: // 2 3 1
			t.SetDFRef(0, BaseDFs[1]);
			t.SetDFRef(1, BaseDFs[2]);
			t.SetDFRef(2, BaseDFs[0]);
			break;
		case 4: // 3 1 2
			t.SetDFRef(0, BaseDFs[2]);
			t.SetDFRef(1, BaseDFs[0]);
			t.SetDFRef(2, BaseDFs[1]);
			break;
		case 5: // 3 2 1
			t.SetDFRef(0, BaseDFs[2]);
			t.SetDFRef(1, BaseDFs[1]);
			t.SetDFRef(2, BaseDFs[0]);
			ContinueFlag = false; // Last Case Indication
			break;
		}
		
		return ContinueFlag;
	}
	
	// General Functions
	public void TransmitterManagement(Parameters params, Point p, SensorsSet ss, FileWriter[] FilesHandler, RelationTable rt, IDs id) throws Exception // Get True Tx Positions, Perform Basic Calculations & Write To CSV & SQL
	{
		double SensorDist = 0;
		MeasurementsManager mm = new MeasurementsManager();
		Transmitter t = new Transmitter();

		t.SetID(id.GetAndIncrementTxID()); // Set And Increment ID
		t.SetPoint(p.GetX(), p.GetY());	// Get Tx Point
		
		for (int i = 0 ; i < NUM_OF_DFs ; i++)
		{
			t.GetDF(i).SetUse(true); // For Future Use, Currently Always True

			// Get Sensors & DFs Data
			t.GetDF(i).GetSensor().SetPoint(ss.GetSensor(i).GetX(), ss.GetSensor(i).GetY());
			t.GetDF(i).GetSensor().SetUncertainty(params.GetUncertainty(i));
			
			// Sensor Distance Calculation
			SensorDist = CalcPythagoras(t.GetDF(i).GetSensor().GetX() - t.GetX(), t.GetDF(i).GetSensor().GetY() - t.GetY());
			t.GetDF(i).SetSensorDist(SensorDist);
			
			// Azimuth Calculation
			t.GetDF(i).SetAzimuth(t.m_Cut.perform_Calc_DF(t.GetDF(i).GetSensor().GetX(), t.GetDF(i).GetSensor().GetY(), t.GetX(), t.GetY()));
		}
		
		// Preparations For Basic Tx Combinations
		boolean BasicTxContinue = true;
		int SensorsCombinationIndex = 0;
		
		// Save Base DFs Combination
		DF BaseDFs[] = new DF[NUM_OF_DFs];		
		for (int i = 0 ; i < NUM_OF_DFs ; i++)
		{
			BaseDFs[i] = t.GetDF(i);
		}
		
		// Save Base DFs Azimuth Value
		double BaseAzimuth[] = new double[NUM_OF_DFs];
		for (int i = 0 ; i < NUM_OF_DFs ; i++)
		{
			BaseAzimuth[i] = t.GetDF(i).GetAzimuth();
		}
		
		// Repeat For All Possible Combinations Of DFs
		while (BasicTxContinue)
		{
			// Sensors Combination
			BasicTxContinue = this.AdjustSensorsCombination(SensorsCombinationIndex, t, BasicTxContinue, BaseDFs);
			
			for (double DF1Bias = params.GetMaxBias() * -1 ; DF1Bias < params.GetMaxBias() ; DF1Bias += params.GetBiasStep())
			{
				t.GetDF(0).SetBias(DF1Bias);
				t.GetDF(0).SetAzimuth(BaseAzimuth[0] + DF1Bias);
				
				for (double DF2Bias = params.GetMaxBias() * -1 ; DF2Bias < params.GetMaxBias() ; DF2Bias += params.GetBiasStep())
				{
					t.GetDF(1).SetBias(DF2Bias);
					t.GetDF(1).SetAzimuth(BaseAzimuth[1] + DF2Bias);
					
					for (double DF3Bias = params.GetMaxBias() * -1 ; DF3Bias < params.GetMaxBias() ; DF3Bias += params.GetBiasStep())
					{
						t.GetDF(2).SetBias(DF3Bias);
						t.GetDF(2).SetAzimuth(BaseAzimuth[2] + DF3Bias);
						
						for (int i = 0 ; i < NUM_OF_NODF_COMBINATIONS ; i++)
						{
							switch (i)
							{
							case 0: // True True True
								break;
							case 1: // False True True
								t.GetDF(0).SetUse(false);
								t.GetDF(1).SetUse(true);
								t.GetDF(2).SetUse(true);
								break;
							case 2: // True False True
								t.GetDF(0).SetUse(true);
								t.GetDF(1).SetUse(false);
								t.GetDF(2).SetUse(true);
								break;
							case 3: // True True False
								t.GetDF(0).SetUse(true);
								t.GetDF(1).SetUse(true);
								t.GetDF(2).SetUse(false);
								break;
							}
							
							// DF Calculation
							t.m_Cut.perform_DF_Cut(t, t.m_Cut.LR_For_Cut);
					
							// Ellipse Calculation
							t.m_Cut.perform_Ellipse_Calc(t);
					
							// DFs Angles Difference Calculation
							t.SetLocAngleRange(t.m_Cut.perform_DFsAnglesDiff_Calc(t.GetDFSet()));
							
							// Transmitter To Sensor Distance Calculation
							t.SetLocDistance(t.m_Cut.perform_Distance_Calc(t.GetX(), t.GetY(), t.GetAvgSensorsPos().GetX(), t.GetAvgSensorsPos().GetY()));
							
							m_Writer[0].WriteToFile(t, FilesHandler[2]);
							m_Writer[1].WriteToFile(t, FilesHandler[6]);
							
							   // Flow Progression -> Next Filter \\
							mm.MeasurementsManagement(t, params, FilesHandler, rt, id);
						}
					}
				}
			}
			SensorsCombinationIndex++;
		}
	}
	
	public static double CalcPythagoras(double x, double y)
	{
		return Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), 0.5);
	}
}
