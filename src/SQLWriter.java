import java.io.FileWriter;
import java.io.IOException;

public class SQLWriter implements Writable
{
	static final int NUM_OF_DFs = 3;
		
	@Override
	public void WriteToFile(Point p, FileWriter writer) throws IOException {}
	@Override
	public void WriteToFile(Transmitter t, FileWriter writer) throws IOException 
	{
		String data = "exec ML_insert_transmitter ";
		
		data +=  t.GetID() + ",1," + t.GetX() + "," + t.GetY() + "," + t.GetEllipse().GetSemiMajor() + "," + t.GetEllipse().GetSemiMinor() + "," + t.GetEllipse().GetAngle() + "\r\n";
		
		writer.write(data);
	}
	@Override
	public void WriteToFile(SensorsSet s, FileWriter writer) throws IOException
	{
		String data = "";
		writer.write(data);

		for (int i = 0 ; i < NUM_OF_DFs ; i++)
		{
			data += "exec ML_insert_sensor ";
			data += (i+1) + "," + s.GetSensor(i).GetX() + "," + s.GetSensor(i).GetY() + "\r\n";
		}

		writer.write(data);
	}
	@Override
	public void WriteToFile(RelationTable rt, FileWriter writer) throws IOException {}
	@Override
	public void WriteToFile(Measurement m, Transmitter t, FileWriter writer) throws IOException 
	{
		String data = "exec ML_insert_dlr ";
		
		data +=  (m.GetID() + "," + t.GetID() + "," + m.GetX() + "," + m.GetY() + "," + m.GetEllipse().GetSemiMajor() + "," + m.GetEllipse().GetSemiMinor() + "," + m.GetEllipse().GetAngle() + "\r\n");
		writer.write(data);
		
		for (int i = 0 ; i < NUM_OF_DFs ; i++)
		{
			data = "exec ML_insert_measurement ";
			data += m.GetID() + "," + (i+1) + "," + m.GetDF(i).GetSensor().GetX() + "," + m.GetDF(i).GetSensor().GetY() + "," + m.GetDF(i).GetAzimuth() + "\r\n";
			writer.write(data);
		}	
	}
}
