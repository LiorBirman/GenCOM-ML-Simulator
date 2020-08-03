import java.io.FileWriter;

public class TransmittersManager 
{
	static final int NUM_OF_FILE_TYPES = 2;
	static final int NUM_OF_DFs = 3;
	
	// DataMembers
	private Writable[] m_Writer;
	
	// Constructor
	public TransmittersManager()
	{
		m_Writer = new Writable[NUM_OF_FILE_TYPES];
		m_Writer[0] = new CSVWriter();
		m_Writer[1] = new SQLWriter();
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
	
	public static double CalcPythagoras(double x, double y)
	{
		return Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), 0.5);
	}
}
