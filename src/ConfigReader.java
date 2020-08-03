import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Iterator; 
import java.util.Map; 

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 
 
public class ConfigReader 
{
	JSONObject m_config;

   public ConfigReader(String pathToConfig) throws Exception
   {
	   Object obj;
	   try 
	   {
		   obj = new JSONParser().parse(new FileReader(pathToConfig)); 
	       m_config = (JSONObject) obj; 
	   }
	   catch (Exception ex)
	   {
		   throw new Exception("Could not read config file Exception");
	   }
   }
 
   public String getProperty(String key)
   {
	 return (String) m_config.get(key);
   }
   
   public void setProperty(String key, String value)
   {
	   m_config.put(key, value);
   }

   public void WriteToConfigFile()
   {
	   PrintWriter pw = null;
	   try
	   {
		 // writing JSON to file:"JSONExample.json" in cwd 
	     pw = new PrintWriter("src\\config.json"); 
	     pw.write(m_config.toJSONString()); 
	     System.out.println("wrote config to file");
	   } 
	   catch (Exception ex)
	   {
		   System.out.println("Exception while writing config to file");
		   ex.printStackTrace();
	   }
	   finally
	   {
		  if (pw != null)
		  {
			  pw.flush(); 
		      pw.close(); 
		  }
	   }
   }
}