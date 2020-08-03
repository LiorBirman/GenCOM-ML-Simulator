import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class UIController {

	private UI m_TheView;
	private SimulatorManager m_Simulator;
	ConfigReader m_configFile;
	
	public UIController(UI theView, SimulatorManager theModel) throws Exception
	{
		m_TheView = theView;
		m_Simulator = theModel;
		m_configFile = new ConfigReader("src\\config.json\\");
		
		m_TheView.AddGenerateListner(new GenerateListner());
		m_TheView.AddLoadConfigListner(new ConfigLoadListner());
		m_TheView.AddSaveUIParamsListner(new ConfigSaveUIParamsListner());
		m_TheView.AddSelectOutputPathListner(new OutputPathListber());
		m_TheView.AddSelectRelationsPathListner(new RelationFileListner());
		m_TheView.AddGridStepListner(new GridStepListner());
	}
	
	private void OnGenerate()
	{
		m_TheView.AppendToLogTextArea("Generation started.");
		m_TheView.AppendToLogTextArea("Please wait, the process may take some time.");
		new GenerateWorker().execute();
	}
	
	public void CalcAndSetGridStep()
	{
		int range = (int) Double.parseDouble(m_TheView.getTextField_Range());
		int nLines = (int) Double.parseDouble(m_TheView.getTextField_NLines());
		m_TheView.setTextField_GridStep(String.valueOf(range / nLines));
	}

	
	private Map<String, String> GetUiMap()
	{
		Map<String, String> map = new HashMap<>();
		
		try
		{
			map.put("s1x", m_TheView.getTextField_s1x().getText());
			map.put("s1y", m_TheView.getTextField_s1y().getText());
			map.put("s1Uncertaninty", m_TheView.getTextField_s1Uncertaninty().getText());
			map.put("s2x", m_TheView.getTextField_s2x().getText());
			map.put("s2y", m_TheView.getTextField_s2y().getText());
			map.put("s2Uncertaninty", m_TheView.getTextField_s2Uncertaninty().getText());
			map.put("s3x", m_TheView.getTextField_s3x().getText());
			map.put("s3y", m_TheView.getTextField_s3y().getText());
			map.put("s3Uncertaninty", m_TheView.getTextField_s3Uncertaninty().getText());
			map.put("Range", m_TheView.getTextField_Range());
			map.put("NLines", m_TheView.getTextField_NLines());
			map.put("GridStep", m_TheView.getTextField_GridStep());
			map.put("N0CorrectMeas", m_TheView.getTextField_N0CorrectMeas());
			map.put("N1CorrectMeas", m_TheView.getTextField_N1CorrectMeas());
			map.put("N2CorrectMeas", m_TheView.getTextField_N2CorrectMeas());
			map.put("N3CorrectMeas", m_TheView.getTextField_N3CorrectMeas());
			map.put("PathOutputFolder", m_TheView.getOutputPath());
			map.put("PathRelationsFile", m_TheView.getRelationsFilePath());
		}
		catch (Exception e) {
			m_TheView.AppendToLogTextArea(e.getMessage().toString());
			m_TheView.AppendToLogTextArea(e.getStackTrace().toString());
		}
		return map;
	}
	
	
	public void OnLoadConfig() 
	{
		
		m_TheView.setTextField_s1x(m_configFile.getProperty("s1x").trim());
		m_TheView.setTextField_s1y(m_configFile.getProperty("s1y").trim());
		m_TheView.setTextField_s1Uncertaninty(m_configFile.getProperty("s1Uncertaninty").trim());
		m_TheView.setTextField_s2x(m_configFile.getProperty("s2x").trim());
		m_TheView.setTextField_s2y(m_configFile.getProperty("s2y").trim());
		m_TheView.setTextField_s2Uncertaninty(m_configFile.getProperty("s2Uncertaninty").trim());
		m_TheView.setTextField_s3x(m_configFile.getProperty("s3x").trim());
		m_TheView.setTextField_s3y(m_configFile.getProperty("s3y").trim());
		m_TheView.setTextField_s3Uncertaninty(m_configFile.getProperty("s3Uncertaninty").trim());
		m_TheView.setTextField_Range((m_configFile.getProperty("Range").trim()));
		m_TheView.setTextField_NLines((m_configFile.getProperty("NLines").trim()));
		m_TheView.setTextField_N0CorrectMeas(m_configFile.getProperty("N0CorrectMeas").trim());
		m_TheView.setTextField_N1CorrectMeas(m_configFile.getProperty("N1CorrectMeas").trim());
		m_TheView.setTextField_N2CorrectMeas(m_configFile.getProperty("N2CorrectMeas").trim());
		m_TheView.setTextField_N3CorrectMeas(m_configFile.getProperty("N3CorrectMeas").trim());
		String outputFolder = m_configFile.getProperty("PathOutputFolder");
		outputFolder = outputFolder.length() > 2 ? outputFolder.substring(0, outputFolder.length() - 1) : outputFolder; 		// Chop unnecessary '\' from json string
		m_TheView.setTextField_pathOutputFolder(outputFolder);
		m_TheView.setTextField_pathRelationsFile(m_configFile.getProperty("PathRelationsFile"));
		CalcAndSetGridStep();
	}
	
	
	public void PrintProgressMessage(String str)
	{
		m_TheView.AppendToLogTextArea(str);
	}
	
	
	
	//	#### Start of Inner classes - Actions paired with the UI Controller ####
	class GenerateListner implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			OnGenerate();
		}
	}
	
	
	class ConfigSaveUIParamsListner implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Map<String, String> map = GetUiMap();
			
			m_configFile.setProperty("s1x", map.get("s1x"));
			m_configFile.setProperty("s1y", map.get("s1y"));
			m_configFile.setProperty("s1Uncertaninty", map.get("s1Uncertaninty"));
			m_configFile.setProperty("s2x", map.get("s2x"));
			m_configFile.setProperty("s2y", map.get("s2y"));
			m_configFile.setProperty("s2Uncertaninty", map.get("s2Uncertaninty"));
			m_configFile.setProperty("s3x", map.get("s3x"));
			m_configFile.setProperty("s3y", map.get("s3y"));
			m_configFile.setProperty("s3Uncertaninty", map.get("s3Uncertaninty"));
			m_configFile.setProperty("Range", map.get("Range"));
			m_configFile.setProperty("NLines", map.get("NLines"));
			m_configFile.setProperty("GridStep", map.get("GridStep"));
			m_configFile.setProperty("N0CorrectMeas", map.get("N0CorrectMeas"));
			m_configFile.setProperty("N1CorrectMeas", map.get("N1CorrectMeas"));
			m_configFile.setProperty("N2CorrectMeas", map.get("N2CorrectMeas"));
			m_configFile.setProperty("N3CorrectMeas", map.get("N3CorrectMeas"));
			m_configFile.setProperty("PathOutputFolder", map.get("PathOutputFolder"));
			m_configFile.setProperty("PathRelationsFile", map.get("PathRelationsFile"));

			m_configFile.WriteToConfigFile();
		}
		
		private String VerifyDoubleForwardSlash(String toCheck)
		{
			String ans;
			char forwardSlash = '\\';
			int length = toCheck.length();
		
			// already has 2 forward slashes
			if (toCheck.charAt(length - 1) == forwardSlash && toCheck.charAt(length - 2) == forwardSlash)
			{
				ans = toCheck;
			} 
			//  has only one forward slashes
			else if (toCheck.charAt(length - 1) == forwardSlash)
			{
				ans = toCheck + "\\";
			}
			//  has no forward slashes
			else
			{
				ans = toCheck + "\\" + "\\"; 
			}
			
			return ans;
		}
	}
	
	
	class ConfigLoadListner implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			OnLoadConfig();
		}
	}
	
	
	class OutputPathListber implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			OnSelectOutputFolder();
		}		
		
		private void OnSelectOutputFolder() 
		{
			m_TheView.f_OutputPathChooser = new JFileChooser(); 
			m_TheView.f_OutputPathChooser.setCurrentDirectory(new java.io.File("."));
			m_TheView.f_OutputPathChooser.setDialogTitle("Select Output Folder");
			m_TheView.f_OutputPathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
		    // disable the "All files" option.
			m_TheView.f_OutputPathChooser.setAcceptAllFileFilterUsed(false);
		        
		    if (m_TheView.f_OutputPathChooser.showOpenDialog(m_TheView) == JFileChooser.APPROVE_OPTION)
		    { 
		    	m_TheView.setTextField_pathOutputFolder(m_TheView.f_OutputPathChooser.getSelectedFile().toString());
		    }
	     }
	}
	
	
	class RelationFileListner implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			OnSelectRelationsFile();
		}		
		
		private void OnSelectRelationsFile() 
		{
			m_TheView.f_RelationsFileChooser = new JFileChooser(); 
			m_TheView.f_RelationsFileChooser.setCurrentDirectory(new java.io.File("."));
			m_TheView.f_RelationsFileChooser.setDialogTitle("Select Relations File");
			m_TheView.f_RelationsFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
		    // disable the "All files" option.
			m_TheView.f_RelationsFileChooser.setAcceptAllFileFilterUsed(false);
		        
		    if (m_TheView.f_RelationsFileChooser.showOpenDialog(m_TheView) == JFileChooser.APPROVE_OPTION)
		    { 
		    	m_TheView.setTextField_pathRelationsFile(m_TheView.f_RelationsFileChooser.getSelectedFile().toString());
		    }
	     }
	}

	
	class GenerateWorker extends SwingWorker<Integer, Void>
	{
	    protected Integer doInBackground() throws Exception
	    {
	    	try {
				
				Map<String, String> map = GetUiMap();
				m_Simulator = SimulatorManager.GetInstance();
				m_Simulator.InitParams(map);
				m_Simulator.StartFlow();
		
			}
			catch (Exception ex)
			{
				m_TheView.AppendToLogTextArea(ex.getMessage());
			}		
	    	return 0;
	    }

	    protected void done()
	    {
	    	m_TheView.AppendToLogTextArea("Finished!");
	    }
	}
	
	class GridStepListner implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			CalcAndSetGridStep();
		}
	}
	
	//	#### End of Inner classes - Actions paired with the UI Controller ####

}
