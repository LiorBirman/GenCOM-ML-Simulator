
public class Point 
{
	// Data Members
	protected double m_X;
	protected double m_Y;
	
	// Constructor
	public Point()
	{
		this.m_X = 0;
		this.m_Y = 0;
	}

	// Getters
	public double GetX() {return m_X;}
	public double GetY() {return m_Y;}

	// Setters
	public void SetX(double m_X) {this.m_X = m_X;}
	public void SetY(double Y) {this.m_Y = Y;}
	public void SetPoint(double X, double Y) {this.SetX(X); this.SetY(Y);}
}
