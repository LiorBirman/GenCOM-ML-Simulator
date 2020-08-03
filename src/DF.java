
public class DF
{
	// Data Members
	private boolean m_Use; // If the sensor did not intercept the signal, then use = 0 and do not include this DF in the calculation
	private boolean m_AttachedToTx;
	private double m_SensorDist;
	private double m_Azimuth;
	private Sensor m_Sensor;

	// Constructor
	public DF(double Azimuth, int SensorID, String SensorName, double SensorUncertainty) 
	{
		this.m_Sensor = new Sensor(SensorID, SensorName, SensorUncertainty);
		this.m_Azimuth = Azimuth;
		this.m_Use = false;
		this.m_SensorDist = 0;
	}

	// Getters
	public double GetAzimuth() {return m_Azimuth;}
	public double GetSensorDist() {return m_SensorDist;}
	public Sensor GetSensor() {return m_Sensor;}
	public boolean GetUse() {return this.m_Use;}
	public boolean GetAttachedToTx() {return this.m_AttachedToTx;}
	
	// Setters
	public void SetSensorDist(double SensorDist) {this.m_SensorDist = SensorDist;}
	public void SetAzimuth(double Azimuth) {this.m_Azimuth = Azimuth;}	
	public void SetUse(boolean Use) {this.m_Use = Use;}
	public void SetAttachedToTx(boolean AttachedToTx) {this.m_AttachedToTx = AttachedToTx;}
}
