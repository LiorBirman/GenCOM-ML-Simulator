import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RelationsTableTest
{
	RelationTable m_relationTable;
	ConfigReader m_config;
	Transmitter m_transmitter;
	Measurement m_measurement;
	
	RelationsTableTest() throws Exception
	{
		m_relationTable = new RelationTable();
		m_config = new ConfigReader("src\\Tests\\Input\\config.json");
		
		m_transmitter = new Transmitter();
		m_measurement = new Measurement();
		
		m_transmitter.SetPoint(378.8397, 7995.4653);
		m_transmitter.GetDF(0).GetSensor().SetPoint(381.617, 7990.569);
		m_transmitter.GetDF(1).GetSensor().SetPoint(356.923, 7989.604);
		m_transmitter.GetDF(2).GetSensor().SetPoint(403.979, 7988.223);
		m_transmitter.GetDF(0).GetSensor().SetUncertainty(1);
		m_transmitter.GetDF(1).GetSensor().SetUncertainty(1);
		m_transmitter.GetDF(2).GetSensor().SetUncertainty(1);
		m_transmitter.GetDF(0).SetAzimuth(330);
		m_transmitter.GetDF(1).SetAzimuth(75);
		m_transmitter.GetDF(2).SetAzimuth(286);
		
		m_measurement.SetPoint(378.8397, 7995.4653);
		m_measurement.GetDF(0).GetSensor().SetPoint(381.617, 7990.569);
		m_measurement.GetDF(1).GetSensor().SetPoint(356.923, 7989.604);
		m_measurement.GetDF(2).GetSensor().SetPoint(403.979, 7988.223);
		m_measurement.GetDF(0).GetSensor().SetUncertainty(1);
		m_measurement.GetDF(1).GetSensor().SetUncertainty(1);
		m_measurement.GetDF(2).GetSensor().SetUncertainty(1);
		m_measurement.GetDF(0).SetAzimuth(330);
		m_measurement.GetDF(1).SetAzimuth(75);
		m_measurement.GetDF(2).SetAzimuth(286);
		m_measurement.GetDF(0).SetUse(true);
		m_measurement.GetDF(1).SetUse(true);
		m_measurement.GetDF(2).SetUse(true);
	}

	@Test
	void test() throws Exception
	{
		SimulatorManager.ParseFileToTable(m_relationTable, "src\\Tests\\Input\\AdvancedVariables.txt");
		m_relationTable.SetBaseValues(m_transmitter, m_measurement);
		m_relationTable.CalculateValues();
		
	}

}
