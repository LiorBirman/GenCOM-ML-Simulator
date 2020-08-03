
public class Sensor extends Point
{	
	 // For Future Use \\
	private double m_Height; 
	
	// Data Members
	private int m_SensorID;
	private String m_Name;
	private double m_Uncertainty;
	
	// Constructor
	public Sensor(int SensorID, String Name, double Uncertainty)
	{
		super(); // Point = (0, 0);
		this.m_Height = 0.0;
		this.SetSensorID(SensorID);
		Name = new String(Name);
		this.SetUncertainty(Uncertainty);
	}

	// Getters
	public int GetSensorID() {return m_SensorID;}
	public String GetName() {return m_Name;}
	public double GetUncertainty() {return m_Uncertainty;}
	public double GetX() {return this.m_X;}
	public double GetY() {return this.m_Y;}
	public double GetHeight() {return this.m_Height;}

	// Setters
	public void SetSensorID(int SensorID) {this.m_SensorID = SensorID;}
	public void SetName(String Name) {this.m_Name = Name;}
	public void SetUncertainty(double Uncertainty) {this.m_Uncertainty = Uncertainty;}
	public void SetX(double x) {this.m_X = x;}
	public void SetY(double y) {this.m_Y = y;}
	public void SetPoint(double x, double y) {this.m_X = x; this.m_Y = y;}
	public void SetHeight(double Height) {this.m_Height = Height;}
	public void SetSensor(Sensor sens)
	{
		this.SetSensorID(sens.GetSensorID());
		this.SetName(sens.GetName());
		this.SetUncertainty(sens.GetUncertainty());
		this.SetPoint(sens.GetX(), sens.GetY());
	}
}