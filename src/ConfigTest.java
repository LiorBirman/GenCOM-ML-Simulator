import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConfigTest {

	@Test
	void testReadConfig() {
		try 
		{
			ConfigReader config = new ConfigReader("src\\config.json\\");
			
			String s1x = config.getProperty("s1x");
			assertNotNull(s1x);
			
			String s1y = config.getProperty("s1y");
			assertNotNull(s1y);
			
			String s1Uncertaninty = config.getProperty("s1Uncertaninty");
			assertNotNull(s1Uncertaninty);
			
			String s2x = config.getProperty("s2x");
			assertNotNull(s2x);
			
			String s2y = config.getProperty("s2y");
			assertNotNull(s2y);
			
			String s2Uncertaninty = config.getProperty("s2Uncertaninty");
			assertNotNull(s2Uncertaninty);
			
			String s3x = config.getProperty("s3x");
			assertNotNull(s3x);
			
			String s3y = config.getProperty("s3y");
			assertNotNull(s3y);
			
			String s3Uncertaninty = config.getProperty("s3Uncertaninty");
			assertNotNull(s3Uncertaninty);
			
			String range = config.getProperty("Range");
			assertNotNull(range);
			
			String nLines = config.getProperty("NLines");
			assertNotNull(nLines);
			
			String N0CorrectMeas = config.getProperty("N0CorrectMeas");
			assertNotNull(N0CorrectMeas);
			
			String N1CorrectMeas = config.getProperty("N1CorrectMeas");
			assertNotNull(N1CorrectMeas);
			
			String N2CorrectMeas = config.getProperty("N2CorrectMeas");
			assertNotNull(N2CorrectMeas);
			
			String N3CorrectMeas = config.getProperty("N3CorrectMeas");
			assertNotNull(N3CorrectMeas);
			
			String PathOutputFolder = config.getProperty("PathOutputFolder");
			assertNotNull(PathOutputFolder);
			
			String PathRelationsFile = config.getProperty("PathRelationsFile");
			assertNotNull(PathRelationsFile);
			
		} 
			catch (Exception e) {
		}
	}

}
