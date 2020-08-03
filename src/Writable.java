import java.io.FileWriter;
import java.io.IOException;

public interface Writable 
{
	void WriteToFile(Point p, FileWriter writer) throws IOException;
	void WriteToFile(Measurement m, Transmitter t, FileWriter writer) throws IOException;
	void WriteToFile(Transmitter t, FileWriter writer) throws IOException;
	void WriteToFile(SensorsSet s, FileWriter writer)throws IOException;
	void WriteToFile(RelationTable rt, FileWriter writer) throws IOException;
}