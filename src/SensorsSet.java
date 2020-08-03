
public class SensorsSet 
{
	static final int NUM_OF_SENSORS = 3;
	
	// Data Members
	private Sensor m_Sensors[];
	
	// Constructor
	public SensorsSet()
	{
		this.m_Sensors = new Sensor[NUM_OF_SENSORS];
		for (int i = 0 ; i < NUM_OF_SENSORS ; i++)
		{
			this.m_Sensors[i] = new Sensor(i, "" + i, 0);
		}
	}

	// Getters
	public Sensor GetSensor(int i) {return m_Sensors[i];}
	public Point GetAvgSensorsPos()
	{
		Point p = new Point();
		double AvgSX = (m_Sensors[0].GetX() + m_Sensors[1].GetX() + m_Sensors[2].GetX()) / 3;
		double AvgSY = (m_Sensors[0].GetY() + m_Sensors[1].GetY() + m_Sensors[2].GetY()) / 3;
		p.SetPoint(AvgSX, AvgSY);
		
		return p;
	}
	
	// Setters
	public void SetSensor(Sensor Sens, int i)
	{
		m_Sensors[i].SetSensor(Sens);
	}
}
