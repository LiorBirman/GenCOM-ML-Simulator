import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main
{	
	public static void main (String args[]) throws Exception
{			
		UI theView = new UI();
		SimulatorManager theModel = SimulatorManager.GetInstance();
		
		try
		{
			UIController theController = new UIController(theView, theModel);
			theController.OnLoadConfig();
			theView.SetWindowToVisible();
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}