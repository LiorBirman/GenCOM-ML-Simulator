import java.util.Random;

public class Measurement extends Location
{
	                     // For Future Use \\
	// i = 0 3 DFs ; i = 1 DF1 + DF2 ; i = 2 DF1 + DF3 ; i = 3 DF2 + DF3
	// All possible combination without repeats with at least two DFs
	static final int NUM_OF_COMBINATIONS = 4;
	static final int NUM_OF_DFs = 3;
	
	// Data Members
	private Location[] m_SubLocations; // For Future Use \\
	private double m_LocAngleRange;
	private double m_LocDistance;
	private int m_ID = 0;
	private boolean m_AttachedToTx;
	
	// Constructor
	public Measurement( ) 
	{
		super();
		
		            // For Future Use \\
		m_SubLocations = new Location[NUM_OF_COMBINATIONS];
		for (int i = 0 ; i < NUM_OF_COMBINATIONS ; i++)
		{
			m_SubLocations[i] = new Location();
		}
	}

	// Getters
	Location GetSubLocation(int i) {return this.m_SubLocations[i];}
	public double GetLocAngleRange() {return m_LocAngleRange;}
	public double GetLocDistance() {return m_LocDistance;}
	public int GetID() {return m_ID;}
	public boolean GetAttachedToTx() {return m_AttachedToTx;}
	
	// Setters
	public void SetLocAngleRange(double LocAngleRange) {this.m_LocAngleRange = LocAngleRange;}
	public void SetLocDistance(double LocDistance) {this.m_LocDistance = LocDistance;}
	public void SetID(int ID) {this.m_ID = ID;}
	public void SetAttachedToTx (boolean AttachedToTx) {this.m_AttachedToTx = AttachedToTx;}
	
	// General Functions
	private double NormalizeAngle(double angle)
	{
		while (angle < 0)
		{
			angle += 360;
		}
		while (angle > 360)
		{
			angle -= 360;
		}
		
		return angle;
	}
	
	private void GenerateValidDF(Transmitter t, int i) // Calculate Azimuth + Noise Of A Valid DF
	{
		double noise = CalculateDFNoise(t.GetDF(i).GetSensor().GetUncertainty());
		double noisedAzimuth = noise + t.GetDF(i).GetAzimuth();

		this.GetDF(i).SetAzimuth(this.NormalizeAngle(noisedAzimuth));
		this.GetDF(i).GetSensor().SetPoint(t.GetDF(i).GetSensor().GetX(), t.GetDF(i).GetSensor().GetY());
		this.GetDF(i).SetAttachedToTx(true);

	}
	private void GenerateInvalidDF(Transmitter t, int i) // Calculate Azimuth + Noise Of An Invalid DF
	{
		// Angle outside of +- uncertainty range in any direction
		double noise = 0;
		noise = CalculateDFNoise(180 - (10 * t.GetDF(i).GetSensor().GetUncertainty()));
		double noisedAzimuth = noise + t.GetDF(i).GetAzimuth();
		
		this.GetDF(i).SetAzimuth(this.NormalizeAngle(noisedAzimuth));
		this.GetDF(i).GetSensor().SetPoint(t.GetDF(i).GetSensor().GetX(), t.GetDF(i).GetSensor().GetY());
		this.GetDF(i).SetAttachedToTx(false);
	}
	public static double CalculateDFNoise(double Uncertainty) // Calculate DF Noise With Gaussian Random
	{
			Random rand = new Random();
			double noise = ((rand.nextDouble() - 0.5) * 2) * Uncertainty;
			return noise;
	}
	public void GenerateDFs(Transmitter t, int bits) // Generate Valid Or Invalid DF According To The Bits Input.
	{
			if (bits == 0)
				this.SetAttachedToTx(false);
			else
				this.SetAttachedToTx(true);
			
			if ((bits & 0x001) == 0x001)
			     GenerateValidDF(t, 0);
			else GenerateInvalidDF(t, 0);
			
			if ((bits & 0x010) == 0x010)
				 GenerateValidDF(t, 1);
			else GenerateInvalidDF(t, 1);
			
			if ((bits & 0x100) == 0x100)
				 GenerateValidDF(t, 2);
			else GenerateInvalidDF(t, 2);
	}
}
