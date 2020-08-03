import java.io.FileWriter;

public class SensorsManager
{
	static final int NUM_OF_FILE_TYPES = 2;
	
	// Data Members
	private Writable[] m_Writer;
	
	// Constructor
	public SensorsManager()
	{
		m_Writer = new Writable[NUM_OF_FILE_TYPES];
		m_Writer[0] = new CSVWriter();
		m_Writer[1] = new SQLWriter();
	}
	
	// General Functions
	public void SensorDeployment(FileWriter[] FilesHandler, SensorsSet ss) throws Exception // Get Data From UI To Sensors & Write To CSV & SQL
	{
		m_Writer[0].WriteToFile(ss, FilesHandler[0]);
		m_Writer[1].WriteToFile(ss, FilesHandler[5]);
	}
}
