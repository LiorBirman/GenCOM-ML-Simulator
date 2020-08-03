
public class Location extends Point
{
	static final int NUM_OF_DF = 3; // Sensors set always has 3 sensors
	
	// Data Members
	protected DF m_DF[];
	public Cut m_Cut;
	protected Ellipse m_Ellipse;
	
	// Constructor
	public Location()
	{
		super(); // Point = (0, 0)
		this.m_Cut = new Cut();
		this.m_Ellipse = new Ellipse(0, 0, 0);
		this.m_DF = new DF[NUM_OF_DF];		
		for (int i = 0 ; i < 3 ; i++)
		{
			m_DF[i] = new DF(0.0, 0, "", 0.0);
		}
	}
	
	// Getters
	public double GetX() {return this.m_X;}
	public double GetY() {return this.m_Y;}
	public DF GetDF(int i) {return m_DF[i];}
	public DF[] GetDFSet() {return m_DF;}
	public Ellipse GetEllipse() {return this.m_Ellipse;}
	public Point GetAvgSensorsPos()
	{
		Point p = new Point();
		double AvgSX = (m_DF[0].GetSensor().GetX() + m_DF[1].GetSensor().GetX() + m_DF[2].GetSensor().GetX()) / 3;
		double AvgSY = (m_DF[0].GetSensor().GetY() + m_DF[1].GetSensor().GetY() + m_DF[2].GetSensor().GetY()) / 3;
		p.SetPoint(AvgSX, AvgSY);
		
		return p;
	}

	// Setters
	public void SetX(double x) {this.m_X = x;}
	public void SetY(double y) {this.m_Y = y;}
	public void SetPoint(double x, double y) {this.m_X = x;this.m_Y = y;}
	public void SetDF(int i, double Azimuth, int SensorID, String SensorName, double SensorUncertainty)
	{
		m_DF[i].SetAzimuth(Azimuth);
		m_DF[i].GetSensor().SetSensorID(SensorID);
		m_DF[i].GetSensor().SetName(SensorName);
		m_DF[i].GetSensor().SetUncertainty(SensorUncertainty);
	}
}















