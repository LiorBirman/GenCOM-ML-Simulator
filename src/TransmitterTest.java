import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class TransmitterTest {

	@Test
	void testPythagoras() 
	{
		double pythagoras = TransmittersManager.CalcPythagoras(3, 4);
		assertEquals(5, pythagoras);
	}
	
	@Test
	void testTransmitterFlow()
	{
		Transmitter t = new Transmitter();
		Point p = new Point();
		p.SetPoint(378.8397, 7995.4653);
		
		final int numOfDfs = 3;
		
		double actualAzimuth[] = new double[numOfDfs];
		double expectedAzimuth[] = {330.4, 75.0, 286.0};
		
		t.SetPoint(378.8397, 7995.4653);
		t.GetDF(0).GetSensor().SetPoint(381.617, 7990.569);
		t.GetDF(1).GetSensor().SetPoint(356.923, 7989.604);
		t.GetDF(2).GetSensor().SetPoint(403.979, 7988.223);
		t.GetDF(0).GetSensor().SetUncertainty(0);
		t.GetDF(1).GetSensor().SetUncertainty(0);
		t.GetDF(2).GetSensor().SetUncertainty(0);
		
		boolean azimuthWithinLimits = false;
		
		for (int i = 0; i < numOfDfs; i++)
		{
			t.GetDF(i).SetUse(true); // For Future Use, Currently Always True
			
			// Sensor Distance Calculation
			double SensorDist = TransmittersManager.CalcPythagoras(t.GetDF(i).GetSensor().GetX() - t.GetX(), t.GetDF(i).GetSensor().GetY() - t.GetY());
			t.GetDF(i).SetSensorDist(SensorDist);
			
			// Azimuth Calculation
			t.GetDF(i).SetAzimuth(t.m_Cut.perform_Calc_DF(t.GetDF(i).GetSensor().GetX(), t.GetDF(i).GetSensor().GetY(), t.GetX(), t.GetY()));
			actualAzimuth[i] = t.GetDF(i).GetAzimuth();
			
			// assert +- 0.1 expected dfs
			azimuthWithinLimits = Math.abs(expectedAzimuth[i] - actualAzimuth[i]) <= 0.1;
			assertTrue(azimuthWithinLimits);
		}
		
		// DF Calculation
		t.m_Cut.perform_DF_Cut(t, t.m_Cut.LR_For_Cut);
		boolean xWithinLimits = Math.abs(t.GetX() - p.GetX()) <= 0.005;
		assertTrue(xWithinLimits);
		
		boolean yWithinLimits = Math.abs(t.GetY() - p.GetY()) <= 0.005;
		assertTrue(yWithinLimits);
		
		// Ellipse Calculation
		t.m_Cut.perform_Ellipse_Calc(t);
		boolean angleWithinLimits = Math.abs(148.1 - t.GetEllipse().GetAngle()) <= 0.1;
		assertTrue(angleWithinLimits);
		
		boolean semiMajorWithinLimits = Math.abs(0.495 - t.GetEllipse().GetSemiMajor()) <= 0.005;
		assertTrue(semiMajorWithinLimits);
		
		boolean semiMinorWithinLimits = Math.abs(0.137 - t.GetEllipse().GetSemiMinor()) <= 0.005;
		assertTrue(semiMinorWithinLimits);
		
		// DFs Angles Difference Calculation
		t.SetLocAngleRange(t.m_Cut.perform_DFsAnglesDiff_Calc(t.GetDFSet()));
		boolean locAngleRange = Math.abs(148.5 - t.GetLocAngleRange()) <= 1;
		assertTrue(locAngleRange);
		
		// Transmitter To Sensor Distance Calculation
		t.SetLocDistance(t.m_Cut.perform_Distance_Calc(t.GetX(), t.GetY(), t.GetAvgSensorsPos().GetX(), t.GetAvgSensorsPos().GetY()));
		boolean locDistance = Math.abs(6.4 - t.GetLocDistance()) <= 0.2;
		assertTrue(locDistance);
	}
}
