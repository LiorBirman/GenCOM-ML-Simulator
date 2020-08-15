
public class Location extends Point
{
	static final int NUM_OF_DFs = 3; // Sensors set always has 3 sensors
	
	// Data Members
	protected DF m_DF[];
	public Cut m_Cut;
	protected Ellipse m_Ellipse;
	
	protected double m_LocAngleRange;
	protected double m_LocDistance;

	// Constructor
	public Location()
	{
		super(); // Point = (0, 0)
		this.m_Cut = new Cut();
		this.m_Ellipse = new Ellipse(0, 0, 0);
		this.m_DF = new DF[NUM_OF_DFs];		
		for (int i = 0 ; i < NUM_OF_DFs ; i++)
		{
			m_DF[i] = new DF(0.0, 0, "", 0.0);
		}
	}
	
	// Private Functions
	private double CalcPythagoras(double a, double b)
	{
		return Math.pow((Math.pow(a, 2) + Math.pow(b, 2)), 0.5);
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

	// Distance Between Tx / Msr To Sensor
	public double GetS1LocDist() {return CalcPythagoras(this.GetDF(0).GetSensor().GetX() - this.GetX(), this.GetDF(0).GetSensor().GetY() - this.GetY());}
	public double GetS2LocDist() {return CalcPythagoras(this.GetDF(1).GetSensor().GetX() - this.GetX(), this.GetDF(1).GetSensor().GetY() - this.GetY());}
	public double GetS3LocDist() {return CalcPythagoras(this.GetDF(2).GetSensor().GetX() - this.GetX(), this.GetDF(2).GetSensor().GetY() - this.GetY());}

	// Distance Between Two Sensors
	public double GetS1S2Dist() {return CalcPythagoras(this.GetDF(0).GetSensor().GetX() - this.GetDF(1).GetSensor().GetX(), this.GetDF(0).GetSensor().GetY() - this.GetDF(1).GetSensor().GetY());}
	public double GetS1S3Dist() {return CalcPythagoras(this.GetDF(0).GetSensor().GetX() - this.GetDF(2).GetSensor().GetX(), this.GetDF(0).GetSensor().GetY() - this.GetDF(2).GetSensor().GetY());}
	public double GetS2S3Dist() {return CalcPythagoras(this.GetDF(1).GetSensor().GetX() - this.GetDF(2).GetSensor().GetX(), this.GetDF(1).GetSensor().GetY() - this.GetDF(2).GetSensor().GetY());}

	// Distance Between Center Of Sensors To Sensor
	public double GetS1AvgDist() {return CalcPythagoras(this.GetAvgSensorsPos().GetX() - this.GetDF(0).GetSensor().GetX(), this.GetAvgSensorsPos().GetY() - this.GetDF(0).GetSensor().GetY());}
	public double GetS2AvgDist() {return CalcPythagoras(this.GetAvgSensorsPos().GetX() - this.GetDF(1).GetSensor().GetX(), this.GetAvgSensorsPos().GetY() - this.GetDF(1).GetSensor().GetY());}
	public double GetS3AvgDist() {return CalcPythagoras(this.GetAvgSensorsPos().GetX() - this.GetDF(2).GetSensor().GetX(), this.GetAvgSensorsPos().GetY() - this.GetDF(2).GetSensor().GetY());}

	// Distance Between DF To Other Sensor
//	public double GetS1DF2Dist()
//	public double GetS1DF3Dist()
//	public double GetS2DF1Dist()
//	public double GetS2DF3Dist()
//	public double GetS3DF1Dist()
//	public double GetS3DF2Dist()

	
	
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
	public void SetDFRef(int i, DF df)
	{
		this.m_DF[i] = df;
	}
}















