import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvalidRelationsTest {
	
	SimulatorManager m_simulator;
	ConfigReader m_config;
	
	InvalidRelationsTest() throws Exception
	{
		m_simulator = SimulatorManager.GetInstance();
		m_config = new ConfigReader("src\\Tests\\Input\\config.json");
	}
	
	@Test
	void testDuplicateNameInRelationFile()
	{
		m_config.setProperty("Error: PathRelationsFile", "src\\Tests\\Input\\TestDuplicateName.txt");
		m_simulator.InitParams(SimulatorOutputTest.GetConfigParams(m_config));
		
		try 
		{
			m_simulator.StartFlow();
		} 
		catch (Exception e)
		{
			assertEquals("Error: Relations File Line 4 Duplicate Name \"Half\"", e.getMessage());
		}
	}
	
	@Test
	void testMissingOperand1()
	{
		m_config.setProperty("PathRelationsFile", "src\\Tests\\Input\\InvalidOperand1.txt");
		m_simulator.InitParams(SimulatorOutputTest.GetConfigParams(m_config));
		
		try 
		{
			m_simulator.StartFlow();
		} 
		catch (Exception e)
		{
			assertEquals("Error: Relations File Line 10: Undefined Operand 1 \"Temp6\"", e.getMessage());
		}
	}
	
	@Test
	void testMissingOperand2()
	{
		m_config.setProperty("PathRelationsFile", "src\\Tests\\Input\\InvalidOperand2.txt");
		m_simulator.InitParams(SimulatorOutputTest.GetConfigParams(m_config));
		
		try 
		{
			m_simulator.StartFlow();
		} 
		catch (Exception e)
		{
			assertEquals("Error: Relations File Line 10: Undefined Operand 2 \"Temp6\"", e.getMessage());
		}
	}

}
