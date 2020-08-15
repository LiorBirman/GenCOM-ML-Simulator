import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.Calendar;

public class SimulatorManager
{	
	private static SimulatorManager m_simulatorManager = null;
	
	// Constants
	private static final int NUM_OF_DFs = 3;
	private static final int NUM_OF_FILES = 8;
	
	// Data Members
	private SensorsSet m_SensorsSet;
	private Parameters m_Params;
	private RelationTable m_Table;
	private String[] m_FilesNames;
	private SensorsManager m_SManager;
	private GridManager m_GManager;
	private IDs m_IDs;
	
	// Constructor
	public SimulatorManager() throws Exception
	{
		this.m_SensorsSet = new SensorsSet();
		this.m_Params = new Parameters();
		this.m_FilesNames = new String[NUM_OF_FILES];
	}
	
	public RelationTable GetRelationTable() {
		return m_Table;
	}
	public Parameters GetParams() {return m_Params;}
	
	// Private Functions
	private static String[] FileToSplitString(String FileName) throws IOException	// Copy Data From File Into A String For Each Sentence
	{
		String FileData = "";
		try
		{
			FileData = new String(Files.readAllBytes(Paths.get(FileName)));
		}
		catch (IOException e) {}
		
        FileData += "\r";
        
        return FileData.split("\n");
	}
	private void InitFilesNames()
	{
		// Files Name Format: yyyy-MM-dd-HHmm. Example: "2020-06-13-1741" = 2020.06.13-17:41
		Date date = (Date) Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");  
        String strDate = dateFormat.format(date); 
        String path = m_Params.GetPath();
        
        for (int i = 0 ; i < NUM_OF_FILES ; i++)
        {
        	this.m_FilesNames[i] = new String(path + strDate);
        }
        
        this.m_FilesNames[0] += "-Sensors.csv";
        this.m_FilesNames[1] += "-Points.csv";
        this.m_FilesNames[2] += "-Transmitters.csv";
        this.m_FilesNames[3] += "-Measurements.csv";
        this.m_FilesNames[4] += "-Relations.csv";
        this.m_FilesNames[5] += "-Sensors.sql";
        this.m_FilesNames[6] += "-Transmitters.sql";
        this.m_FilesNames[7] += "-Measurements.sql";
	}
	private void CreateFiles() // Create The CSV & SQL Files
	{
		try
		{
			// Create All Files
			for (int i = 0 ; i < m_FilesNames.length ; i++)
			{
				File file = new File(m_FilesNames[i]);
				if (file.createNewFile())
				{
					System.out.println("File " + m_FilesNames[i] + " Created");
				}
				else System.out.println("File Already Exists");
			}
			System.out.println("\n");
		}
		
		catch (IOException e)	
		{
			System.out.println("Error On File Creation");
			e.printStackTrace();
		}
	}
	private void OpenFiles(FileWriter[] FilesHandler) // Open The Files For Writing
	{
		try
		{
			for (int i = 0 ; i < FilesHandler.length ; i++)
			{
				FilesHandler[i] = new FileWriter(m_FilesNames[i]);
			}
			System.out.println("All Files Are Now Open");
		}
		
		catch (IOException e)
		{
			System.out.println("Did Not Open File");
			e.printStackTrace();
		}
	}
    private void CloseFiles (FileWriter[] files) throws IOException // Close The Files After Writing
    {
    	for (int i = 0 ; i < files.length ; i++)
    	{
    		files[i].close();
    	}
    	System.out.println("All Files Are Now Closed");
    }
    
    // General Functions
    public void StartFlow() throws Exception // Start Simulating
    {
    	// Initialize Data Members
		this.m_IDs = new IDs();
    	this.m_Table = new RelationTable();
		this.m_SManager = new SensorsManager();
		this.m_GManager = new GridManager();
    	
		// Parse Relations File & Insert To Table
    	ParseFileToTable(this.GetRelationTable(), this.GetParams().GetRelationsFileName());
    	
    	// Initialize Files Names & Create All Files On Flow Start
    	this.InitFilesNames();
    	this.CreateFiles();
		
		// Open All Files For Writing On Flow Start
		FileWriter[] FilesHandler = new FileWriter[m_FilesNames.length];
		this.OpenFiles(FilesHandler);
		
		// Flow Progression -> Next Filter \\
	    m_SManager.SensorDeployment(FilesHandler, m_SensorsSet);
	     
    	// Flow Progression -> Next Filter \\
	    m_GManager.GenerateGrid(FilesHandler, m_Params, m_SensorsSet, m_Table, m_IDs);

	    // Close All Files After Flow
		System.out.println("\n");
		this.CloseFiles(FilesHandler);
    }
   	public void InitParams(Map<String, String> map)
	{
		m_Params.SetParams(map);
		
		this.m_SensorsSet.GetSensor(0).SetPoint(m_Params.GetS1X(), m_Params.GetS1Y());
		this.m_SensorsSet.GetSensor(1).SetPoint(m_Params.GetS2X(), m_Params.GetS2Y());
		this.m_SensorsSet.GetSensor(2).SetPoint(m_Params.GetS3X(), m_Params.GetS3Y());

		for (int i = 0 ; i < NUM_OF_DFs ; i++)
		{
			this.m_SensorsSet.GetSensor(i).SetUncertainty(m_Params.GetUncertainty(i));
		}
	}
	public static void ParseFileToTable(RelationTable table, String relationspath) throws Exception
	{
		// Read From Relations File And Insert Into The Table
		String[] SplitString = FileToSplitString(relationspath);
		for (int i = 0 ; i < SplitString.length - 1 ; i++)
		{
			try 	
			{
				table.ParsAndInsert(SplitString[i], i);
			}
			catch (Exception e)
			{
				throw e;
			}
		}
		
		if (SplitString[0].charAt(0) == '\r')
		{
			throw new Exception("Illegal Relations File.");
		}
	}

	public static SimulatorManager GetInstance() throws Exception 
	{
		if (m_simulatorManager == null) 
            m_simulatorManager = new SimulatorManager(); 
        return m_simulatorManager; 
	}
}




