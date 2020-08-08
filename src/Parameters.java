import java.util.Map;

public class Parameters 
{
	static final int NUM_OF_SENSORS = 3;
	static final int NUM_OF_SETS = 1;
	
	
	   // For Future Use \\
//	private double m_Latitude;
//	private double m_Longitude;
//	private int m_NSensors;
//	private int m_NSets;
	
	// Data Members
	private double m_S1X;
	private double m_S1Y;
	private double m_S2X;
	private double m_S2Y;
	private double m_S3X;
	private double m_S3Y;
	private double m_Uncertainty1;
	private double m_Uncertainty2;
	private double m_Uncertainty3;
	private double m_Step;
	private double m_Range;
	private double m_MaxBias;
	private double m_BiasStep;
	private int m_NLines;
	private int m_N0CorrectMeas;
	private int m_N1CorrectMeas;
	private int m_N2CorrectMeas;
	private int m_N3CorrectMeas;
	private String m_RelationsFileName;
	private String m_Path;
	
	// Constructor
	public Parameters() {}
	
	public void SetParams(Map<String, String> map)
	{	
		this.m_S1X = Double.parseDouble((map.get("s1x")));
		this.m_S1Y = Double.parseDouble((map.get("s1y")));
		this.m_S2X = Double.parseDouble((map.get("s2x")));
		this.m_S2Y = Double.parseDouble((map.get("s2y")));
		this.m_S3X = Double.parseDouble((map.get("s3x")));
		this.m_S3Y = Double.parseDouble((map.get("s3y")));
		this.m_Uncertainty1 = Double.parseDouble((map.get("s1Uncertaninty")));
		this.m_Uncertainty2 = Double.parseDouble((map.get("s2Uncertaninty")));
		this.m_Uncertainty3 = Double.parseDouble((map.get("s3Uncertaninty")));
		
		this.m_MaxBias = Double.parseDouble((map.get("MaxBias")));
		this.m_BiasStep = Double.parseDouble((map.get("BiasStep")));
		
		this.m_Range = Double.parseDouble((map.get("Range")));
		this.m_NLines = (int)Double.parseDouble((map.get("NLines")));
		this.m_Step = this.m_Range / this.m_NLines;
		
		this.m_N0CorrectMeas = Integer.parseInt(map.get("N0CorrectMeas"));
		this.m_N1CorrectMeas = Integer.parseInt(map.get("N1CorrectMeas"));
		this.m_N2CorrectMeas = Integer.parseInt(map.get("N2CorrectMeas"));
		this.m_N3CorrectMeas = Integer.parseInt(map.get("N3CorrectMeas"));
		
		this.m_Path = map.get("PathOutputFolder");
		this.m_RelationsFileName = map.get("PathRelationsFile");

//		Future usage
//		this.m_Latitude = Latitude;
//		this.m_Longitude = Longitude;
//		this.m_NSensors = NUM_OF_SENSORS;
//		this.m_NSets = NUM_OF_SETS;
	}

	// Getters
	public double GetRange() {return m_Range;}
//	public double GetLatitude() {return m_Latitude;}
//	public double GetLongitude() {return m_Longitude;}
	public int GetNLines() {return m_NLines;}
//	public int GetNSensors() {return m_NSensors;}
//	public int GetNSets() {return m_NSets;}
	public int GetN0CorrectMeas() {return m_N0CorrectMeas;}
	public int GetN1CorrectMeas() {return m_N1CorrectMeas;}
	public int GetN2CorrectMeas() {return m_N2CorrectMeas;}
	public int GetN3CorrectMeas() {return m_N3CorrectMeas;}
	public double GetStep() {return m_Step;}
	public double GetS1X() {return m_S1X;}
	public double GetS1Y() {return m_S1Y;}
	public double GetS2X() {return m_S2X;}
	public double GetS2Y() {return m_S2Y;}
	public double GetS3X() {return m_S3X;}
	public double GetS3Y() {return m_S3Y;}
	public String GetRelationsFileName() {return m_RelationsFileName;}
	public String GetPath() {return m_Path;}
	public double GetUncertainty(int i)
	{
		if (i == 1)
		{
			return m_Uncertainty1;
		}
		if (i ==2 )
		{
			return m_Uncertainty2;
		}
		
		return m_Uncertainty3;
	}
	public double GetMaxBias() {return m_MaxBias;}
	public double GetBiasStep() {return m_BiasStep;}
	
	// Setters
	public void SetRange(int Range) {this.m_Range = Range;}
//	public void SetLatitude(double Latitude) {this.m_Latitude = Latitude;}
//	public void SetLongitude(double Longitude) {this.m_Longitude = Longitude;}
	public void SetNumOfLines(int NumOfLines) {this.m_NLines = NumOfLines;}
//	public void SetNumOfSensors(int NumOfSensors) {this.m_NSensors = NumOfSensors;}
//	public void SetNumOfSets(int NumOfSets) {this.m_NSets = NumOfSets;}
	public void SetS1X(double S1X) {this.m_S1X = S1X;}
	public void SetS1Y(double S1Y) {this.m_S1Y = S1Y;}
	public void SetS2X(double S2X) {this.m_S2X = S2X;}
	public void SetS2Y(double S2Y) {this.m_S2Y = S2Y;}
	public void SetS3X(double S3X) {this.m_S3X = S3X;}
	public void SetS3Y(double S3Y) {this.m_S3Y = S3Y;}
	public void SetUncertainty1(double Uncertainty) {this.m_Uncertainty1 = Uncertainty;}
	public void SetUncertainty2(double Uncertainty) {this.m_Uncertainty2 = Uncertainty;}
	public void SetUncertainty3(double Uncertainty) {this.m_Uncertainty3 = Uncertainty;}
	public void SetMaxBias(double MaxBias) {this.m_MaxBias = MaxBias;}
	public void SetBiasStep(double BiasStep) {this.m_BiasStep = BiasStep;}
}
