
public class Ellipse 
{
	// Data Members
	private double m_SemiMajor;
	private double m_SemiMinor;
	private double m_Angle;
	private double m_Dispersion;
	private double m_R11;
	private double m_R12;
	private double m_R21;
	private double m_R22;
	
	
	// Constructor
	public Ellipse(double SemiMajor, double SemiMinor, double Angle)
	{
		this.m_SemiMajor = SemiMajor;
		this.m_SemiMinor = SemiMinor;
		this.m_Angle = Angle;
		this.m_Dispersion = 0;
	}

	// Getters
	public double GetSemiMajor() {return m_SemiMajor;}
	public double GetSemiMinor() {return m_SemiMinor;}
	public double GetAngle() {return m_Angle;}
	public double GetDispersion() {return m_Dispersion;}
	public double GetR11() {return m_R11;}
	public double GetR12() {return m_R12;}
	public double GetR21() {return m_R21;}
	public double GetR22() {return m_R22;}

	// Setters
	public void SetSemiMajor(double SemiMajor) {this.m_SemiMajor = SemiMajor;}
	public void SetSemiMinor(double SemiMinor) {this.m_SemiMinor = SemiMinor;}
	public void SetAngle(double Angle) {this.m_Angle = Angle;}
	public void SetDispersion(double Dispersion) {this.m_Dispersion = Dispersion;}
	public void SetR11(double R11) {this.m_R11 = R11;}
	public void SetR12(double R12) {this.m_R12 = R12;}
	public void SetR21(double R21) {this.m_R21 = R21;}
	public void SetR22(double R22) {this.m_R22 = R22;}

}
