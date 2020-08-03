import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class SimulatorOutputTest {
	
	final String advancedVariabelsToCompareTo = "src\\Tests\\Input\\AdvancedVariables.txt";
	final String measurementsCsvToCompareTo = "src\\Tests\\Input\\2020-07-01-194333-Measurements.csv";
	final String measurementsSqlToCompareTo = "src\\Tests\\Input\\2020-07-01-194333-Measurements.sql";
	final String pointsCsvToCompareTo = "src\\Tests\\Input\\2020-07-01-194333-Points.csv";
	final String relationsCsvToCompareTo = "src\\Tests\\Input\\2020-07-01-194333-Relations.csv";
	final String sensorsCsvToCompareTo = "src\\Tests\\Input\\2020-07-01-194333-Sensors.csv";
	final String sensorsSqlToCompareTo = "src\\Tests\\Input\\2020-07-01-194333-Sensors.sql";
	final String transmittersCsvToCompareTo = "src\\Tests\\Input\\2020-07-01-194333-Transmitters.csv";
	final String transmittersSqlToCompareTo = "src\\Tests\\Input\\2020-07-01-194333-Transmitters.sql";
	
	SimulatorManager m_simulator;
	ConfigReader m_config;
	
	byte[] m_outputFileContent; 
	byte[] m_outputFileToCompareTo; 
	
	String m_advancedVariabels = null;
	String m_measurementsCsv = null;
	String m_measurementsSql = null;
	String m_pointsCsv = null;
	String m_relationsCsv = null;
	String m_sensorsCsv = null;
	String m_sensorsSql = null;
	String m_transmittersCsv = null;
	String m_transmittersSql = null;
	
	SimulatorOutputTest()
	{
		// Run full flow
		try 
		{
			m_simulator = SimulatorManager.GetInstance();
			m_config = new ConfigReader("src\\Tests\\Input\\config.json");
			m_simulator.InitParams(GetConfigParams(m_config));
			m_simulator.StartFlow();
		} 
		catch (Exception e) 
		{
			fail();
		}
		
		fillCurrentRunOutputFilesPaths();
	}
	
	@Test
	void testSimulatorOutputFilesByteByByte() 
	{
		try
		{
			compareContentOfTwoFiles(m_advancedVariabels, advancedVariabelsToCompareTo);
			compareContentOfTwoFiles(m_measurementsCsv, measurementsCsvToCompareTo);
			compareContentOfTwoFiles(m_measurementsSql, measurementsSqlToCompareTo);
			compareContentOfTwoFiles(m_pointsCsv, pointsCsvToCompareTo);
			compareContentOfTwoFiles(m_relationsCsv, relationsCsvToCompareTo);
			compareContentOfTwoFiles(m_sensorsCsv, sensorsCsvToCompareTo);
			compareContentOfTwoFiles(m_sensorsSql, sensorsSqlToCompareTo);
			compareContentOfTwoFiles(m_transmittersCsv, transmittersCsvToCompareTo);
			compareContentOfTwoFiles(m_transmittersSql, transmittersSqlToCompareTo);
		}
		catch (Exception e) {
			fail();
		}
	}
	
	
	
	private void compareContentOfTwoFiles(String uri1, String uri2) throws IOException
	{
		System.out.println(uri1);
		m_outputFileContent = Files.readAllBytes(Paths.get(uri1));
		m_outputFileToCompareTo = Files.readAllBytes(Paths.get(uri2));
		assertEquals(Arrays.equals(m_outputFileContent, m_outputFileToCompareTo), true);
	}
	
	private void fillCurrentRunOutputFilesPaths()
	{
		// Find relative path to test input file
		String basePath = new File("").getAbsolutePath();
		basePath +=  "\\src\\Tests\\Input";
		File folder = new File(basePath);
		File[] listOfFiles = folder.listFiles();
				
		String currFilePath = null;
		for (int i = 0; i < listOfFiles.length; i++)
		{
		  if (listOfFiles[i].isFile()) 
		  {
			  currFilePath = listOfFiles[i].getPath();
			  if(currFilePath.endsWith("Measurements.csv"))
			  {
				  m_measurementsCsv = currFilePath.toString();
			  }
			  else if (currFilePath.endsWith("Measurements.sql"))
			  {
				  m_measurementsSql = currFilePath.toString();
			  }
			  else if (currFilePath.endsWith("Points.csv"))
			  {
				  m_pointsCsv = currFilePath.toString();
			  }
			  else if (currFilePath.endsWith("Relations.csv"))
			  {
				  m_relationsCsv = currFilePath.toString();
			  }
			  else if (currFilePath.endsWith("Sensors.csv"))
			  {
				  m_sensorsCsv = currFilePath.toString();
			  }
			  else if (currFilePath.endsWith("Sensors.sql"))
			  {
				  m_sensorsSql = currFilePath.toString();
			  }
			  else if (currFilePath.endsWith("Transmitters.csv"))
			  {
				  m_transmittersCsv = currFilePath.toString();
			  }
			  else if (currFilePath.endsWith("Transmitters.sql"))
			  {
				  m_transmittersSql = currFilePath.toString();
			  }
			  else if (currFilePath.endsWith("AdvancedVariables.txt"))
			  {
				  m_advancedVariabels = currFilePath.toString();
			  }
		  } 
		}
	}
	
	public static Map<String, String> GetConfigParams(ConfigReader config)
	{
		Map<String, String> map = new HashMap<>();
	
		map.put("s1x", config.getProperty("s1x"));
		map.put("s1y", config.getProperty("s1y"));
		map.put("s1Uncertaninty", config.getProperty("s1Uncertaninty"));
		map.put("s2x", config.getProperty("s2x"));
		map.put("s2y", config.getProperty("s2y"));
		map.put("s2Uncertaninty", config.getProperty("s2Uncertaninty"));
		map.put("s3x", config.getProperty("s3x"));
		map.put("s3y", config.getProperty("s3y"));
		map.put("s3Uncertaninty", config.getProperty("s3Uncertaninty"));
		map.put("Range", config.getProperty("Range"));
		map.put("NLines", config.getProperty("NLines"));
		map.put("GridStep", config.getProperty("GridStep"));
		map.put("N0CorrectMeas", config.getProperty("N0CorrectMeas"));
		map.put("N1CorrectMeas", config.getProperty("N1CorrectMeas"));
		map.put("N2CorrectMeas", config.getProperty("N2CorrectMeas"));
		map.put("N3CorrectMeas", config.getProperty("N3CorrectMeas"));
		map.put("PathOutputFolder", config.getProperty("PathOutputFolder"));
		map.put("PathRelationsFile", config.getProperty("PathRelationsFile"));
		
		return map;
	}
}


