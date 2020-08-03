import java.io.FileWriter;

public class MeasurementsManager 
{
	private static final int NUM_OF_FILE_TYPES = 2;
	private static final int NUM_OF_DFs = 3;
	private static final int ALL_POSSIBLE_CORRECTION_COMBINATIONS = 8;

	// DataMembers
	private Writable[] m_Writer;
	
	// Constructor
	public MeasurementsManager()
	{
		m_Writer = new Writable[NUM_OF_FILE_TYPES];
		m_Writer[0] = new CSVWriter();
		m_Writer[1] = new SQLWriter();
	}
	
	// General Functions
	public void MeasurementsManagement(Transmitter t, Parameters params, FileWriter[] FilesHandler, RelationTable rt, IDs id) throws Exception // Get Data From Tx, Perform Calculations With Deviations & Write To CSV & SQL 
	{
		double SensorDist = 0;
		Measurement m = new Measurement();
		RelationsManager rm = new RelationsManager();

		// Get Tx Point
		m.SetPoint(t.GetX(), t.GetY());

		// Get Sensors & DFs Data
		for (int i = 0 ; i < NUM_OF_DFs ; i++)
		{
			m.GetDF(i).SetUse(true);
			m.GetDF(i).GetSensor().SetPoint(t.GetDF(i).GetSensor().GetX(), t.GetDF(i).GetSensor().GetY());
			m.GetDF(i).GetSensor().SetUncertainty(t.GetDF(i).GetSensor().GetUncertainty());
			m.GetDF(i).SetAzimuth(t.GetDF(i).GetAzimuth());
		}
				
		// Each Member Contains The Number Of Measurements With n Correct DFs, These Are All The Possible States For 3 DFs
		int n_meas[] = new int[ALL_POSSIBLE_CORRECTION_COMBINATIONS];
		n_meas[0] = params.GetN0CorrectMeas();
		n_meas[1] = params.GetN1CorrectMeas();
		n_meas[2] = params.GetN1CorrectMeas();
		n_meas[3] = params.GetN1CorrectMeas();
		n_meas[4] = params.GetN2CorrectMeas();
		n_meas[5] = params.GetN2CorrectMeas();
		n_meas[6] = params.GetN2CorrectMeas();
		n_meas[7] = params.GetN3CorrectMeas();
		
		// Each Bit Determines If The DF Is Correct Or Not, For Example: 010 = DF1 Incorrect, DF2 Correct, DF3 Incorrect
		int bits[] = new int[ALL_POSSIBLE_CORRECTION_COMBINATIONS];
		bits[0] = 0x000;
		bits[1] = 0x001; 
		bits[2] = 0x010; 
		bits[3] = 0x100;
		bits[4] = 0x011;
		bits[5] = 0x101;
		bits[6] = 0x110;
		bits[7] = 0x111;

		
		// Calculations After Preparations
		for (int i = 0 ; i < ALL_POSSIBLE_CORRECTION_COMBINATIONS; i++)
		{
			for (int j = 0 ; j < n_meas[i] ; j++)
			{
				m.SetID(id.GetAndIncrementMsrID());
				
				// DFs Calculation
				m.GenerateDFs(t, bits[i]);
				if (m.m_Cut.perform_DF_Cut(m, m.m_Cut.LR_For_Cut))
				{
					// Ellipse Calculation
					m.m_Cut.perform_Ellipse_Calc(m);
				
					for (int k = 0 ; k < NUM_OF_DFs ; k++)
					{	
						// Sensor Distance Calculation
						SensorDist =  Math.pow((Math.pow(m.GetDF(k).GetSensor().GetX() - m.GetX(), 2) + (Math.pow(m.GetDF(k).GetSensor().GetY() - m.GetY(), 2))), 0.5);
						m.GetDF(k).SetSensorDist(SensorDist);
					}
				}
				
				// DFs Angles Difference Calculation
				m.SetLocAngleRange(m.m_Cut.perform_DFsAnglesDiff_Calc(m.GetDFSet()));
				
				// Transmitter To Sensor Distance Calculation
				m.SetLocDistance(m.m_Cut.perform_Distance_Calc(m.GetX(), m.GetY(), m.GetAvgSensorsPos().GetX(), m.GetAvgSensorsPos().GetY()));
				
				m_Writer[0].WriteToFile(m, t, FilesHandler[3]);
				m_Writer[1].WriteToFile(m, t, FilesHandler[7]);
				
		            // Flow Progression -> Next Filter \\
				rm.RelationsManagement(t, m, FilesHandler, rt);
			}
		}
	}
}
