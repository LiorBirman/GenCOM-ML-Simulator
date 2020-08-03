import java.io.FileWriter;

public class GridManager
{
	// Data Members
	private Writable m_Writer;
	
	// Constructor
	public GridManager()
	{
		m_Writer = new CSVWriter();
	}
	
	public void GenerateGrid(FileWriter[] FilesHandler, Parameters params, SensorsSet ss, RelationTable rt, IDs id) throws Exception // Get Data From Sensors, Create True Tx Positions & Write To CSV
	{
		Point p = new Point();
		TransmittersManager tm = new TransmittersManager();
		
		double avgSX = ss.GetAvgSensorsPos().GetX();
		double avgSY = ss.GetAvgSensorsPos().GetY();
		
		// The starting point of the grid is (0, 0) and is located at the average sensors position.
		// Each point is calculated in relation to the starting point (up, down, left, right).
		for (int i = 0 ; i < params.GetNLines() ; i++)
		{
			double latitude = i * params.GetStep();
			for (int j = 0 ; j < params.GetNLines() ; j++)
			{
				double longitude = j * params.GetStep();
				// (0, 0) has only one point
				if (i == 0 && j == 0)
				{
					p.SetPoint(avgSX, avgSY);
					m_Writer.WriteToFile(p, FilesHandler[1]);
					
					      // Flow Progression -> Next Filter \\
					tm.TransmitterManagement(params, p, ss, FilesHandler, rt, id);
				}
				
				// Y Axis has only two points (0, Y) (0, -Y)
				if (j == 0 && i != 0)
				{
					p.SetPoint(avgSX + longitude, avgSY + latitude);
					m_Writer.WriteToFile(p, FilesHandler[1]);
					
					      // Flow Progression -> Next Filter \\
					tm.TransmitterManagement(params, p, ss, FilesHandler, rt, id);
					
					p.SetPoint(avgSX - longitude, avgSY - latitude);
					m_Writer.WriteToFile(p, FilesHandler[1]);
					
				      // Flow Progression -> Next Filter \\
					tm.TransmitterManagement(params, p, ss, FilesHandler, rt, id);
				}
				
				// X Axis has only two points (X, 0) (-X, 0)
				if (j != 0 && i == 0)
				{
					p.SetPoint(avgSX + longitude, avgSY + latitude);
					m_Writer.WriteToFile(p, FilesHandler[1]);
					
				      // Flow Progression -> Next Filter \\
					tm.TransmitterManagement(params, p, ss, FilesHandler, rt, id);
					
					p.SetPoint(avgSX - longitude, avgSY - latitude);
					m_Writer.WriteToFile(p, FilesHandler[1]);
					
				      // Flow Progression -> Next Filter \\
					tm.TransmitterManagement(params, p, ss, FilesHandler, rt, id);
				}
				
				// If not on axis, has four points (X, Y) (X, -Y) (-X, Y) (-X, -Y)
				if (j != 0 && i != 0)
				{
					p.SetPoint(avgSX + longitude, avgSY + latitude);
					m_Writer.WriteToFile(p, FilesHandler[1]);
					
				      // Flow Progression -> Next Filter \\
					tm.TransmitterManagement(params, p, ss, FilesHandler, rt, id);
					
					p.SetPoint(avgSX + longitude, avgSY - latitude);
					m_Writer.WriteToFile(p, FilesHandler[1]);
					
				      // Flow Progression -> Next Filter \\
					tm.TransmitterManagement(params, p, ss, FilesHandler, rt, id);
					
					p.SetPoint(avgSX - longitude, avgSY + latitude);
					m_Writer.WriteToFile(p, FilesHandler[1]);
					
				      // Flow Progression -> Next Filter \\
					tm.TransmitterManagement(params, p, ss, FilesHandler, rt, id);
					
					p.SetPoint(avgSX - longitude, avgSY - latitude);
					m_Writer.WriteToFile(p, FilesHandler[1]);
					
				      // Flow Progression -> Next Filter \\
					tm.TransmitterManagement(params, p, ss, FilesHandler, rt, id);
				}
			}
		}	
	}
}
