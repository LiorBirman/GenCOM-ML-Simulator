import java.io.FileWriter;

public class RelationsManager 
{

	// DataMembers
	private Writable m_Writer;
	
	// Constructor
	public RelationsManager()
	{
		m_Writer = new CSVWriter();
	}
	
	public void RelationsManagement(Transmitter t, Measurement m, FileWriter[] FilesHandler, RelationTable rt) throws Exception // Calculate & Set Values In The Relations Table
	{
		// Calculate & Sets Values Of Advanced Relations 
		rt.SetBaseValues(t, m);
		rt.CalculateValues();
			
		m_Writer.WriteToFile(rt, FilesHandler[4]);
	}
}
