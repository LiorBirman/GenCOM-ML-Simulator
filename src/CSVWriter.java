import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter implements Writable
{
	private static final int NUM_OF_DFs = 3;
	
	// Data Members
	private boolean m_WriteTitles;
	private int m_PLine; // Points Line
	
	// Constructor
	CSVWriter()
	{
		m_PLine = 1;
		m_WriteTitles = true;
	}

	// General Functions
	public void WriteToFile(Point p, FileWriter writer) throws IOException
	{
		if (m_PLine > 1)
			m_WriteTitles = false;
		
		String data = "";
		
		data += p.GetY() + "," +  p.GetX() + "\r\n";
		
		if (m_WriteTitles == true)
		{
			writer.write("ID,Latitude,Longitude" + "\r\n");
			m_WriteTitles = false;
		}
		
		writer.write(m_PLine + "," + data);
		m_PLine++;
	}
	@Override
	public void WriteToFile(Measurement m, Transmitter t, FileWriter writer) throws IOException 
	{
		if (m.GetID() > 1)
			m_WriteTitles = false;
		
		String data = "";
		
		data += m.GetID() + "," + m.GetDFSet()[0].GetSensor().GetX() + "," + m.GetDFSet()[0].GetSensor().GetY() + "," +  m.GetDFSet()[1].GetSensor().GetX() + "," +  m.GetDFSet()[1].GetSensor().GetY() + "," + m.GetDFSet()[2].GetSensor().GetX() + "," + m.GetDFSet()[2].GetSensor().GetY() + "," + m.GetDF(0).GetAzimuth() + "," + m.GetDF(0).GetUse() + "," + m.GetDF(1).GetAzimuth() + "," + m.GetDF(1).GetUse() + "," + m.GetDF(2).GetAzimuth() + "," + m.GetDF(2).GetUse() + "," + m.GetY() + "," + m.GetX() + "," + m.GetEllipse().GetSemiMajor() + "," + m.GetEllipse().GetSemiMinor() + "," + m.GetEllipse().GetAngle() + "," + m.GetLocAngleRange() + "," + m.GetLocDistance() + "\r\n";
		
		if (m_WriteTitles == true)
		{
			writer.write("ID,S1X,S1Y,S2X,S2Y,S3X,S3Y,MsrDF1,Used1,MsrDF2,Used2,MsrDF3,Used3,MsrLat,MsrLong,MsrMajor,MsrMinor,MsrAngle,AngleRange,Distance" + "\r\n");
			m_WriteTitles = false;
		}
		
		writer.write(data);
	}
	@Override
	public void WriteToFile(Transmitter t, FileWriter writer) throws IOException
	{
		if (t.GetID() > 1)
			m_WriteTitles = false;
		
		String data = "";
		data += t.GetID() + "," + t.GetDFSet()[0].GetSensor().GetX() + "," + t.GetDFSet()[0].GetSensor().GetY() + "," +  t.GetDFSet()[1].GetSensor().GetX() + "," +  t.GetDFSet()[1].GetSensor().GetY() + "," + t.GetDFSet()[2].GetSensor().GetX() + "," + t.GetDFSet()[2].GetSensor().GetY() + "," + t.GetDF(0).GetAzimuth() + "," + t.GetDF(0).GetUse() + "," + t.GetDF(1).GetAzimuth() + "," + t.GetDF(1).GetUse() + "," + t.GetDF(2).GetAzimuth() + "," + t.GetDF(2).GetUse() + "," + t.GetY() + "," + t.GetX() + "," + t.GetEllipse().GetSemiMajor() + "," + t.GetEllipse().GetSemiMinor() + "," + t.GetEllipse().GetAngle() + "," + t.GetLocAngleRange() + "," + t.GetLocDistance() + "\r\n";
		
		if (m_WriteTitles == true)
		{
			writer.write("ID,S1X,S1Y,S2X,S2Y,S3X,S3Y,TxDF1,Used1,TxDF2,Used2,TxDF3,Used3,TxLat,TxLong,TxMajor,TxMinor,TxAngle,AngleRange,Distance" + "\r\n");
			m_WriteTitles = false;
		}
		
		writer.write(data);
	}
	@Override
	public void WriteToFile(SensorsSet s, FileWriter writer) throws IOException
	{
		String data = "";

		writer.write("ID,SX,SY,Uncertainty" + "\r\n");
		
		for (int i = 0 ; i < NUM_OF_DFs ; i++)
		{
			data = s.GetSensor(i).GetX() + "," + s.GetSensor(i).GetY() + "," + s.GetSensor(i).GetUncertainty() + "\r\n";
			writer.write((i+1) + "," + data);
		}
	}
	@Override
	public void WriteToFile(RelationTable rt, FileWriter writer) throws IOException
	{
		if (rt.GetLine() > 1)
			m_WriteTitles = false;
		
		String data = "";

		for (int i = 0 ; i < rt.GetTableLength() ; i++)
		{
			data += "," + rt.GetValue(i);
		}
		data += "\r\n";
		if (m_WriteTitles == true)
		{
			String titles = "ID";
			for (int i = 0 ; i < rt.GetTableLength() ; i++)
			{
				titles +=  "," + rt.GetName(i);
			}
			writer.write(titles + "\r\n");
			m_WriteTitles = false;
		}
		
		writer.write(rt.GetLine() + data);
		rt.IncrementLine();
	}
}