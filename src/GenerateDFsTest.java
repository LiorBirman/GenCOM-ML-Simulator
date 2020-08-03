import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenerateDFsTest 
{
	final int numOfDfs = 3;
	
	Transmitter m_transmitter;
	Measurement m_measurement;
	
	@BeforeEach
	void GenerateDF()
	{
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
	void testInvalidMeasurement()
	{
		// DFs Calculation
		m_measurement.GenerateDFs(m_transmitter, 0x110);
		
		boolean azimuth = Math.abs(330 - m_measurement.GetDF(0).GetAzimuth()) > 1;
		assertTrue(azimuth);
		
		azimuth = Math.abs(75 - m_measurement.GetDF(1).GetAzimuth()) <= 1;
		assertTrue(azimuth);
		
		azimuth = Math.abs(286 - m_measurement.GetDF(2).GetAzimuth()) <= 1;
		assertTrue(azimuth);
	}
	
	
	@Test 
	void testValidMeasurement()
	{
		// DFs Calculation
		m_measurement.GenerateDFs(m_transmitter, 0x111);
		
		boolean azimuth = Math.abs(330 - m_measurement.GetDF(0).GetAzimuth()) <= 1;
		assertTrue(azimuth);
		
		azimuth = Math.abs(75 - m_measurement.GetDF(1).GetAzimuth()) <= 1;
		assertTrue(azimuth);
		
		azimuth = Math.abs(286 - m_measurement.GetDF(2).GetAzimuth()) <= 1;
		assertTrue(azimuth);
		
		boolean trueCut = m_measurement.m_Cut.perform_DF_Cut(m_measurement, m_measurement.m_Cut.LR_For_Cut);
		assertTrue(trueCut);
		
		m_measurement.m_Cut.perform_Ellipse_Calc(m_measurement);
		
		boolean angle = Math.abs(148 - m_measurement.GetEllipse().GetAngle()) <= 1;
		boolean semiMajor = Math.abs(0.5 - m_measurement.GetEllipse().GetSemiMajor()) <= 0.1;
		boolean semiMinor = Math.abs(0.15 - m_measurement.GetEllipse().GetSemiMinor()) <= 0.1;
	}
	
	@Test
	void testNoiseGenerator()
	{
		double uncertanity_value = 1.0;
		double absRandomNoise = Math.abs(Measurement.CalculateDFNoise(uncertanity_value));
		
		boolean noise = Math.abs(absRandomNoise - uncertanity_value) <= uncertanity_value;
		assertTrue(noise);
	}

}
