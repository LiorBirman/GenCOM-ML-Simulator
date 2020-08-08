
public class Transmitter extends Location
{
	// Data Members
	private double m_LocAngleRange;
	private double m_LocDistance;
	private int m_ID = 0;

	// Constructor
	public Transmitter() 
	{
		super();
	}
	
	// Getters
	public double GetLocAngleRange() {return m_LocAngleRange;}
	public double GetLocDistance() {return m_LocDistance;}
	public int GetID() {return m_ID;}
	public void SetID(int ID) {this.m_ID = ID;}
	
	// Setters
	public void SetLocAngleRange(double LocAngleRange) {this.m_LocAngleRange = LocAngleRange;}
	public void SetLocDistance(double LocDistance) {this.m_LocDistance = LocDistance;}
}
