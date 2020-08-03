import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;

public class UI extends JFrame{

	private JFrame frmGencommMl;
	private JTextField textField_s1x;
	private JTextField textField_s1y;
	private JTextField textField_s1Uncertaninty;
	private JTextField textField_s2x;
	private JTextField textField_s2y;
	private JTextField textField_s2Uncertainty;
	private JTextField textField_s3x;
	private JTextField textField_s3y;
	private JTextField textField_s3Uncertainty;
	private JTextArea textArea_output;
	private JButton btn_LoadConfig;
	private JButton btn_SaveToConfig;
	private JButton btn_Generate;
	private JButton btn_SelectRelationsFile;
	private JButton btn_SelectOutputPath;
	public JFileChooser f_OutputPathChooser;
	public JFileChooser f_RelationsFileChooser;
	
	private final Component verticalStrut_2 = Box.createVerticalStrut(20);
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_pathOutputFolder;
	private JTextField textField_pathRelationsFile;
	private JTextField textField_N3CorrectMeas;
	private JTextField textField_N2CorrectMeas;
	private JTextField textField_N1CorrectMeas;
	private JTextField textField_N0CorrectMeas;
	private JTextField textField_GridStep;
	private JTextField textField_NLines;
	private JTextField textField_Range;
	
	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGencommMl = new JFrame();
		frmGencommMl.setTitle("GenComm ML Simulator");
		frmGencommMl.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 12));
		frmGencommMl.setBounds(100, 100, 913, 569);
		frmGencommMl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 150, 150, 0, 150, 141, 0, 0, 121, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmGencommMl.getContentPane().setLayout(gridBagLayout);
		
		Component verticalStrut_9 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_9 = new GridBagConstraints();
		gbc_verticalStrut_9.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_9.gridx = 4;
		gbc_verticalStrut_9.gridy = 1;
		frmGencommMl.getContentPane().add(verticalStrut_9, gbc_verticalStrut_9);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_4 = new GridBagConstraints();
		gbc_verticalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_4.gridx = 2;
		gbc_verticalStrut_4.gridy = 2;
		frmGencommMl.getContentPane().add(verticalStrut_4, gbc_verticalStrut_4);
		
		Component verticalStrut_7 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_7 = new GridBagConstraints();
		gbc_verticalStrut_7.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_7.gridx = 4;
		gbc_verticalStrut_7.gridy = 4;
		frmGencommMl.getContentPane().add(verticalStrut_7, gbc_verticalStrut_7);
		
		Component verticalStrut_6 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_6 = new GridBagConstraints();
		gbc_verticalStrut_6.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_6.gridx = 1;
		gbc_verticalStrut_6.gridy = 5;
		frmGencommMl.getContentPane().add(verticalStrut_6, gbc_verticalStrut_6);
		
		JLabel label_8 = new JLabel("Number of Sets:");
		label_8.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 3;
		gbc_label_8.gridy = 5;
		frmGencommMl.getContentPane().add(label_8, gbc_label_8);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setText("1");
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.SOUTH;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 5;
		frmGencommMl.getContentPane().add(textField, gbc_textField);
		
		JLabel lblNumberOfSensors = new JLabel("Number of Sensors:");
		lblNumberOfSensors.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNumberOfSensors = new GridBagConstraints();
		gbc_lblNumberOfSensors.anchor = GridBagConstraints.WEST;
		gbc_lblNumberOfSensors.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfSensors.gridx = 6;
		gbc_lblNumberOfSensors.gridy = 5;
		frmGencommMl.getContentPane().add(lblNumberOfSensors, gbc_lblNumberOfSensors);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setText("3");
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.anchor = GridBagConstraints.SOUTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 7;
		gbc_textField_1.gridy = 5;
		frmGencommMl.getContentPane().add(textField_1, gbc_textField_1);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_4 = new GridBagConstraints();
		gbc_horizontalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_4.gridx = 0;
		gbc_horizontalStrut_4.gridy = 6;
		frmGencommMl.getContentPane().add(horizontalStrut_4, gbc_horizontalStrut_4);
		
		JLabel lblSensor1 = new JLabel("Sensor1:");
		lblSensor1.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblSensor1 = new GridBagConstraints();
		gbc_lblSensor1.anchor = GridBagConstraints.WEST;
		gbc_lblSensor1.insets = new Insets(0, 0, 5, 5);
		gbc_lblSensor1.gridx = 3;
		gbc_lblSensor1.gridy = 6;
		frmGencommMl.getContentPane().add(lblSensor1, gbc_lblSensor1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 5;
		gbc_horizontalStrut.gridy = 6;
		frmGencommMl.getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		
		JLabel label_1 = new JLabel("Sensor2:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 6;
		gbc_label_1.gridy = 6;
		frmGencommMl.getContentPane().add(label_1, gbc_label_1);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 8;
		gbc_horizontalStrut_1.gridy = 6;
		frmGencommMl.getContentPane().add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		JLabel label_5 = new JLabel("Sensor3:");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.WEST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 9;
		gbc_label_5.gridy = 6;
		frmGencommMl.getContentPane().add(label_5, gbc_label_5);
		
		JLabel lblSx = new JLabel("S1X:");
		lblSx.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSx.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblSx = new GridBagConstraints();
		gbc_lblSx.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblSx.insets = new Insets(0, 0, 5, 5);
		gbc_lblSx.gridx = 3;
		gbc_lblSx.gridy = 7;
		frmGencommMl.getContentPane().add(lblSx, gbc_lblSx);
		
		textField_s1x = new JTextField();
		textField_s1x.setToolTipText("UTM coordinate");
		textField_s1x.setColumns(10);
		GridBagConstraints gbc_textField_s1x = new GridBagConstraints();
		gbc_textField_s1x.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_s1x.insets = new Insets(0, 0, 5, 5);
		gbc_textField_s1x.gridx = 4;
		gbc_textField_s1x.gridy = 7;
		frmGencommMl.getContentPane().add(textField_s1x, gbc_textField_s1x);
		
		JLabel label_2 = new JLabel("S2X:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 6;
		gbc_label_2.gridy = 7;
		frmGencommMl.getContentPane().add(label_2, gbc_label_2);
		
		textField_s2x = new JTextField();
		textField_s2x.setToolTipText("UTM coordinate");
		textField_s2x.setColumns(10);
		GridBagConstraints gbc_textField_s2x = new GridBagConstraints();
		gbc_textField_s2x.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_s2x.insets = new Insets(0, 0, 5, 5);
		gbc_textField_s2x.gridx = 7;
		gbc_textField_s2x.gridy = 7;
		frmGencommMl.getContentPane().add(textField_s2x, gbc_textField_s2x);
		
		JLabel label_6 = new JLabel("S3X:");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.WEST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 9;
		gbc_label_6.gridy = 7;
		frmGencommMl.getContentPane().add(label_6, gbc_label_6);
		
		textField_s3x = new JTextField();
		textField_s3x.setToolTipText("UTM coordinate");
		textField_s3x.setColumns(10);
		GridBagConstraints gbc_textField_s3x = new GridBagConstraints();
		gbc_textField_s3x.insets = new Insets(0, 0, 5, 5);
		gbc_textField_s3x.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_s3x.gridx = 10;
		gbc_textField_s3x.gridy = 7;
		frmGencommMl.getContentPane().add(textField_s3x, gbc_textField_s3x);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_2.gridx = 13;
		gbc_horizontalStrut_2.gridy = 7;
		frmGencommMl.getContentPane().add(horizontalStrut_2, gbc_horizontalStrut_2);
		
		JLabel lblSy = new JLabel("S1Y:");
		lblSy.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSy = new GridBagConstraints();
		gbc_lblSy.anchor = GridBagConstraints.WEST;
		gbc_lblSy.insets = new Insets(0, 0, 5, 5);
		gbc_lblSy.gridx = 3;
		gbc_lblSy.gridy = 8;
		frmGencommMl.getContentPane().add(lblSy, gbc_lblSy);
		
		textField_s1y = new JTextField();
		textField_s1y.setToolTipText("UTM coordinate");
		textField_s1y.setColumns(10);
		GridBagConstraints gbc_textField_s1y = new GridBagConstraints();
		gbc_textField_s1y.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_s1y.insets = new Insets(0, 0, 5, 5);
		gbc_textField_s1y.gridx = 4;
		gbc_textField_s1y.gridy = 8;
		frmGencommMl.getContentPane().add(textField_s1y, gbc_textField_s1y);
		
		JLabel label_3 = new JLabel("S2Y:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 6;
		gbc_label_3.gridy = 8;
		frmGencommMl.getContentPane().add(label_3, gbc_label_3);
		
		textField_s2y = new JTextField();
		textField_s2y.setToolTipText("UTM coordinate");
		textField_s2y.setColumns(10);
		GridBagConstraints gbc_textField_s2y = new GridBagConstraints();
		gbc_textField_s2y.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_s2y.insets = new Insets(0, 0, 5, 5);
		gbc_textField_s2y.gridx = 7;
		gbc_textField_s2y.gridy = 8;
		frmGencommMl.getContentPane().add(textField_s2y, gbc_textField_s2y);
		
		JLabel label_7 = new JLabel("S3Y:");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.WEST;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 9;
		gbc_label_7.gridy = 8;
		frmGencommMl.getContentPane().add(label_7, gbc_label_7);
		
		textField_s3y = new JTextField();
		textField_s3y.setToolTipText("UTM coordinate");
		textField_s3y.setColumns(10);
		GridBagConstraints gbc_textField_s3y = new GridBagConstraints();
		gbc_textField_s3y.insets = new Insets(0, 0, 5, 5);
		gbc_textField_s3y.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_s3y.gridx = 10;
		gbc_textField_s3y.gridy = 8;
		frmGencommMl.getContentPane().add(textField_s3y, gbc_textField_s3y);
		
		JLabel lblS1Uncertainty = new JLabel("S1 Uncertainty:");
		lblS1Uncertainty.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblS1Uncertainty.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblS1Uncertainty = new GridBagConstraints();
		gbc_lblS1Uncertainty.anchor = GridBagConstraints.WEST;
		gbc_lblS1Uncertainty.insets = new Insets(0, 0, 5, 5);
		gbc_lblS1Uncertainty.gridx = 3;
		gbc_lblS1Uncertainty.gridy = 9;
		frmGencommMl.getContentPane().add(lblS1Uncertainty, gbc_lblS1Uncertainty);
		
		textField_s1Uncertaninty = new JTextField();
		textField_s1Uncertaninty.setColumns(10);
		GridBagConstraints gbc_textField_s1Uncertaninty = new GridBagConstraints();
		gbc_textField_s1Uncertaninty.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_s1Uncertaninty.insets = new Insets(0, 0, 5, 5);
		gbc_textField_s1Uncertaninty.gridx = 4;
		gbc_textField_s1Uncertaninty.gridy = 9;
		frmGencommMl.getContentPane().add(textField_s1Uncertaninty, gbc_textField_s1Uncertaninty);
		
		JLabel label_4 = new JLabel("S2 Uncertainty:");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.WEST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 6;
		gbc_label_4.gridy = 9;
		frmGencommMl.getContentPane().add(label_4, gbc_label_4);
		
		textField_s2Uncertainty = new JTextField();
		textField_s2Uncertainty.setColumns(10);
		GridBagConstraints gbc_textField_s2Uncertainty = new GridBagConstraints();
		gbc_textField_s2Uncertainty.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_s2Uncertainty.insets = new Insets(0, 0, 5, 5);
		gbc_textField_s2Uncertainty.gridx = 7;
		gbc_textField_s2Uncertainty.gridy = 9;
		frmGencommMl.getContentPane().add(textField_s2Uncertainty, gbc_textField_s2Uncertainty);
		
		JLabel label = new JLabel("S3 Uncertainty:");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 9;
		gbc_label.gridy = 9;
		frmGencommMl.getContentPane().add(label, gbc_label);
		
		textField_s3Uncertainty = new JTextField();
		textField_s3Uncertainty.setColumns(10);
		GridBagConstraints gbc_textField_s3Uncertainty = new GridBagConstraints();
		gbc_textField_s3Uncertainty.insets = new Insets(0, 0, 5, 5);
		gbc_textField_s3Uncertainty.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_s3Uncertainty.gridx = 10;
		gbc_textField_s3Uncertainty.gridy = 9;
		frmGencommMl.getContentPane().add(textField_s3Uncertainty, gbc_textField_s3Uncertainty);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.anchor = GridBagConstraints.WEST;
		gbc_verticalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_3.gridx = 3;
		gbc_verticalStrut_3.gridy = 10;
		frmGencommMl.getContentPane().add(verticalStrut_3, gbc_verticalStrut_3);
		
		JLabel lblOutputFolder = new JLabel("Output Folder:");
		lblOutputFolder.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOutputFolder.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblOutputFolder = new GridBagConstraints();
		gbc_lblOutputFolder.anchor = GridBagConstraints.WEST;
		gbc_lblOutputFolder.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutputFolder.gridx = 3;
		gbc_lblOutputFolder.gridy = 11;
		frmGencommMl.getContentPane().add(lblOutputFolder, gbc_lblOutputFolder);
		
		textField_pathOutputFolder = new JTextField();
		textField_pathOutputFolder.setColumns(10);
		GridBagConstraints gbc_textField_pathOutputFolder = new GridBagConstraints();
		gbc_textField_pathOutputFolder.gridwidth = 3;
		gbc_textField_pathOutputFolder.insets = new Insets(0, 0, 5, 5);
		gbc_textField_pathOutputFolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_pathOutputFolder.gridx = 4;
		gbc_textField_pathOutputFolder.gridy = 11;
		frmGencommMl.getContentPane().add(textField_pathOutputFolder, gbc_textField_pathOutputFolder);
		
		btn_SelectOutputPath = new JButton("Select Output Folder");
		btn_SelectOutputPath.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn_SelectOutputPath.setBackground(Color.WHITE);
		GridBagConstraints gbc_btn_SelectOutputPath = new GridBagConstraints();
		gbc_btn_SelectOutputPath.fill = GridBagConstraints.BOTH;
		gbc_btn_SelectOutputPath.insets = new Insets(0, 0, 5, 5);
		gbc_btn_SelectOutputPath.gridx = 7;
		gbc_btn_SelectOutputPath.gridy = 11;
		frmGencommMl.getContentPane().add(btn_SelectOutputPath, gbc_btn_SelectOutputPath);
		
		JLabel lblRelationsFile = new JLabel("Relations File:");
		lblRelationsFile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRelationsFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblRelationsFile = new GridBagConstraints();
		gbc_lblRelationsFile.anchor = GridBagConstraints.WEST;
		gbc_lblRelationsFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelationsFile.gridx = 3;
		gbc_lblRelationsFile.gridy = 12;
		frmGencommMl.getContentPane().add(lblRelationsFile, gbc_lblRelationsFile);
		
		textField_pathRelationsFile = new JTextField();
		textField_pathRelationsFile.setColumns(10);
		GridBagConstraints gbc_textField_pathRelationsFile = new GridBagConstraints();
		gbc_textField_pathRelationsFile.gridwidth = 3;
		gbc_textField_pathRelationsFile.insets = new Insets(0, 0, 5, 5);
		gbc_textField_pathRelationsFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_pathRelationsFile.gridx = 4;
		gbc_textField_pathRelationsFile.gridy = 12;
		frmGencommMl.getContentPane().add(textField_pathRelationsFile, gbc_textField_pathRelationsFile);
		
		btn_SelectRelationsFile = new JButton("Select Relations File");
		btn_SelectRelationsFile.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn_SelectRelationsFile.setBackground(Color.WHITE);
		GridBagConstraints gbc_btn_SelectRelationsFile = new GridBagConstraints();
		gbc_btn_SelectRelationsFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_SelectRelationsFile.anchor = GridBagConstraints.SOUTH;
		gbc_btn_SelectRelationsFile.insets = new Insets(0, 0, 5, 5);
		gbc_btn_SelectRelationsFile.gridx = 7;
		gbc_btn_SelectRelationsFile.gridy = 12;
		frmGencommMl.getContentPane().add(btn_SelectRelationsFile, gbc_btn_SelectRelationsFile);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.WEST;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 3;
		gbc_separator.gridy = 13;
		frmGencommMl.getContentPane().add(separator, gbc_separator);
		
		JLabel lblOutput = new JLabel("Log:");
		lblOutput.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblOutput = new GridBagConstraints();
		gbc_lblOutput.anchor = GridBagConstraints.WEST;
		gbc_lblOutput.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutput.gridx = 6;
		gbc_lblOutput.gridy = 14;
		frmGencommMl.getContentPane().add(lblOutput, gbc_lblOutput);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_3.gridx = 11;
		gbc_horizontalStrut_3.gridy = 14;
		frmGencommMl.getContentPane().add(horizontalStrut_3, gbc_horizontalStrut_3);
		
		JLabel label_15 = new JLabel("Range:");
		label_15.setHorizontalAlignment(SwingConstants.RIGHT);
		label_15.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_15 = new GridBagConstraints();
		gbc_label_15.insets = new Insets(0, 0, 5, 5);
		gbc_label_15.anchor = GridBagConstraints.WEST;
		gbc_label_15.gridx = 3;
		gbc_label_15.gridy = 15;
		frmGencommMl.getContentPane().add(label_15, gbc_label_15);
		
		textField_Range = new JTextField();
		textField_Range.setColumns(10);
		GridBagConstraints gbc_textField_Range = new GridBagConstraints();
		gbc_textField_Range.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Range.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Range.gridx = 4;
		gbc_textField_Range.gridy = 15;
		frmGencommMl.getContentPane().add(textField_Range, gbc_textField_Range);
		
		textArea_output = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea_output);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.gridheight = 11;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 6;
		gbc_scrollPane.gridy = 15;
		frmGencommMl.getContentPane().add(scrollPane, gbc_scrollPane);
		
		JLabel label_14 = new JLabel("N Lines:");
		label_14.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_14 = new GridBagConstraints();
		gbc_label_14.insets = new Insets(0, 0, 5, 5);
		gbc_label_14.anchor = GridBagConstraints.WEST;
		gbc_label_14.gridx = 3;
		gbc_label_14.gridy = 16;
		frmGencommMl.getContentPane().add(label_14, gbc_label_14);
		
		textField_NLines = new JTextField();
		textField_NLines.setColumns(10);
		GridBagConstraints gbc_textField_NLines = new GridBagConstraints();
		gbc_textField_NLines.insets = new Insets(0, 0, 5, 5);
		gbc_textField_NLines.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_NLines.gridx = 4;
		gbc_textField_NLines.gridy = 16;
		frmGencommMl.getContentPane().add(textField_NLines, gbc_textField_NLines);
		
		JLabel label_13 = new JLabel("Grid Step:");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_13 = new GridBagConstraints();
		gbc_label_13.insets = new Insets(0, 0, 5, 5);
		gbc_label_13.anchor = GridBagConstraints.WEST;
		gbc_label_13.gridx = 3;
		gbc_label_13.gridy = 17;
		frmGencommMl.getContentPane().add(label_13, gbc_label_13);
		
		textField_GridStep = new JTextField();
		textField_GridStep.setColumns(10);
		GridBagConstraints gbc_textField_GridStep = new GridBagConstraints();
		gbc_textField_GridStep.insets = new Insets(0, 0, 5, 5);
		gbc_textField_GridStep.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_GridStep.gridx = 4;
		gbc_textField_GridStep.gridy = 17;
		frmGencommMl.getContentPane().add(textField_GridStep, gbc_textField_GridStep);
		
		JLabel label_12 = new JLabel("N0 Correct Meas:");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.insets = new Insets(0, 0, 5, 5);
		gbc_label_12.anchor = GridBagConstraints.WEST;
		gbc_label_12.gridx = 3;
		gbc_label_12.gridy = 18;
		frmGencommMl.getContentPane().add(label_12, gbc_label_12);
		
		textField_N0CorrectMeas = new JTextField();
		textField_N0CorrectMeas.setColumns(10);
		GridBagConstraints gbc_textField_N0CorrectMeas = new GridBagConstraints();
		gbc_textField_N0CorrectMeas.insets = new Insets(0, 0, 5, 5);
		gbc_textField_N0CorrectMeas.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_N0CorrectMeas.gridx = 4;
		gbc_textField_N0CorrectMeas.gridy = 18;
		frmGencommMl.getContentPane().add(textField_N0CorrectMeas, gbc_textField_N0CorrectMeas);
		
		JLabel label_11 = new JLabel("N1 Correct Meas:");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.anchor = GridBagConstraints.WEST;
		gbc_label_11.gridx = 3;
		gbc_label_11.gridy = 19;
		frmGencommMl.getContentPane().add(label_11, gbc_label_11);
		
		textField_N1CorrectMeas = new JTextField();
		textField_N1CorrectMeas.setColumns(10);
		GridBagConstraints gbc_textField_N1CorrectMeas = new GridBagConstraints();
		gbc_textField_N1CorrectMeas.insets = new Insets(0, 0, 5, 5);
		gbc_textField_N1CorrectMeas.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_N1CorrectMeas.gridx = 4;
		gbc_textField_N1CorrectMeas.gridy = 19;
		frmGencommMl.getContentPane().add(textField_N1CorrectMeas, gbc_textField_N1CorrectMeas);
		
		JLabel label_10 = new JLabel("N2 Correct Meas:");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.anchor = GridBagConstraints.WEST;
		gbc_label_10.gridx = 3;
		gbc_label_10.gridy = 20;
		frmGencommMl.getContentPane().add(label_10, gbc_label_10);
		
		textField_N2CorrectMeas = new JTextField();
		textField_N2CorrectMeas.setColumns(10);
		GridBagConstraints gbc_textField_N2CorrectMeas = new GridBagConstraints();
		gbc_textField_N2CorrectMeas.insets = new Insets(0, 0, 5, 5);
		gbc_textField_N2CorrectMeas.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_N2CorrectMeas.gridx = 4;
		gbc_textField_N2CorrectMeas.gridy = 20;
		frmGencommMl.getContentPane().add(textField_N2CorrectMeas, gbc_textField_N2CorrectMeas);
		
		JLabel label_9 = new JLabel("N3 Correct Meas:");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.anchor = GridBagConstraints.WEST;
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 3;
		gbc_label_9.gridy = 21;
		frmGencommMl.getContentPane().add(label_9, gbc_label_9);
		
		textField_N3CorrectMeas = new JTextField();
		textField_N3CorrectMeas.setColumns(10);
		GridBagConstraints gbc_textField_N3CorrectMeas = new GridBagConstraints();
		gbc_textField_N3CorrectMeas.insets = new Insets(0, 0, 5, 5);
		gbc_textField_N3CorrectMeas.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_N3CorrectMeas.gridx = 4;
		gbc_textField_N3CorrectMeas.gridy = 21;
		frmGencommMl.getContentPane().add(textField_N3CorrectMeas, gbc_textField_N3CorrectMeas);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 3;
		gbc_verticalStrut_1.gridy = 22;
		frmGencommMl.getContentPane().add(verticalStrut_1, gbc_verticalStrut_1);
		
		btn_SaveToConfig = new JButton("Save To Config");
		btn_SaveToConfig.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn_SaveToConfig.setBackground(Color.WHITE);
		GridBagConstraints gbc_btn_saveToConfig = new GridBagConstraints();
		gbc_btn_saveToConfig.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_saveToConfig.insets = new Insets(0, 0, 5, 5);
		gbc_btn_saveToConfig.gridx = 3;
		gbc_btn_saveToConfig.gridy = 24;
		frmGencommMl.getContentPane().add(btn_SaveToConfig, gbc_btn_saveToConfig);
		
		btn_LoadConfig = new JButton("Load Config");
		btn_LoadConfig.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn_LoadConfig.setBackground(Color.WHITE);
		GridBagConstraints gbc_btn_loadConfig = new GridBagConstraints();
		gbc_btn_loadConfig.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_loadConfig.insets = new Insets(0, 0, 5, 5);
		gbc_btn_loadConfig.gridx = 4;
		gbc_btn_loadConfig.gridy = 24;
		frmGencommMl.getContentPane().add(btn_LoadConfig, gbc_btn_loadConfig);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_5 = new GridBagConstraints();
		gbc_horizontalStrut_5.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_5.gridx = 0;
		gbc_horizontalStrut_5.gridy = 25;
		frmGencommMl.getContentPane().add(horizontalStrut_5, gbc_horizontalStrut_5);
		
		btn_Generate = new JButton("Generate");
		btn_Generate.setBackground(UIManager.getColor("Button.light"));
		GridBagConstraints gbc_btnGenerate = new GridBagConstraints();
		gbc_btnGenerate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGenerate.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenerate.gridx = 3;
		gbc_btnGenerate.gridy = 26;
		frmGencommMl.getContentPane().add(btn_Generate, gbc_btnGenerate);
		
		Component verticalStrut_8 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_8 = new GridBagConstraints();
		gbc_verticalStrut_8.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_8.gridx = 3;
		gbc_verticalStrut_8.gridy = 27;
		frmGencommMl.getContentPane().add(verticalStrut_8, gbc_verticalStrut_8);
		
		Component verticalStrut_5 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_5 = new GridBagConstraints();
		gbc_verticalStrut_5.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_5.gridx = 3;
		gbc_verticalStrut_5.gridy = 28;
		frmGencommMl.getContentPane().add(verticalStrut_5, gbc_verticalStrut_5);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut.gridx = 3;
		gbc_verticalStrut.gridy = 29;
		frmGencommMl.getContentPane().add(verticalStrut, gbc_verticalStrut);
	}
	
	public String getTextField_pathOutputFolder() {
		return textField_pathOutputFolder.getText();
	}

	public void setTextField_pathOutputFolder(String textField_pathOutputFolder) {
		this.textField_pathOutputFolder.setText(textField_pathOutputFolder + "\\");
	}

	public String getTextField_pathRelationsFile() {
		return textField_pathRelationsFile.getText();
	}

	public void setTextField_pathRelationsFile(String textField_pathRelationsFile) {
		this.textField_pathRelationsFile.setText(textField_pathRelationsFile);
	}

	public String getTextField_N1CorrectMeas() {
		return textField_N1CorrectMeas.getText();
	}

	public void setTextField_N1CorrectMeas(String textField_N1CorrectMeas) {
		this.textField_N1CorrectMeas.setText(textField_N1CorrectMeas);
	}

	public String getTextField_N2CorrectMeas() {
		return textField_N2CorrectMeas.getText();
	}

	public void setTextField_N2CorrectMeas(String textField_N2CorrectMeas) {
		this.textField_N2CorrectMeas.setText(textField_N2CorrectMeas);
	}

	public String getTextField_N3CorrectMeas() {
		return textField_N3CorrectMeas.getText();
	}

	public void setTextField_N3CorrectMeas(String textField_N3CorrectMeas) {
		this.textField_N3CorrectMeas.setText(textField_N3CorrectMeas);
	}

	public String getTextField_N0CorrectMeas() {
		return textField_N0CorrectMeas.getText();
	}

	public void setTextField_N0CorrectMeas(String textField_N0CorrectMeas) {
		this.textField_N0CorrectMeas.setText(textField_N0CorrectMeas);
	}

	public void AddGenerateListner(ActionListener listenForGenerateButton)
	{
		btn_Generate.addActionListener(listenForGenerateButton);
	}
	
	public void AddSelectOutputPathListner(ActionListener listenForOutputPath)
	{
		btn_SelectOutputPath.addActionListener(listenForOutputPath);
	}
	
	public void AddSelectRelationsPathListner(ActionListener listenForRelationsPath)
	{
		btn_SelectRelationsFile.addActionListener(listenForRelationsPath);
	}
	
	public void AddLoadConfigListner(ActionListener listenForLoadButton)
	{
		btn_LoadConfig.addActionListener(listenForLoadButton);
	}
	
	public void AddSaveUIParamsListner(ActionListener listenForSaveButton)
	{
		btn_SaveToConfig.addActionListener(listenForSaveButton);
		
	}
	
	public void AddGridStepListner(ActionListener listenForGridStepChange)
	{
		textField_NLines.addActionListener(listenForGridStepChange);
		textField_Range.addActionListener(listenForGridStepChange);
	}
	
	public void AppendToLogTextArea(String stringToAdd)
	{
		textArea_output.append(stringToAdd + '\n');
	}
	
	public void DislayPopUpMessage(String msg)
	{
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public void SetWindowToVisible()
	{
		frmGencommMl.setVisible(true);
	}
	
	
	// Getters 
	public JTextField getTextField_s1x() {
		return textField_s1x;
	}

	public JTextField getTextField_s2uncertainty() {
		return textField_s2Uncertainty;
	}

	public void setTextField_s2uncertainty(JTextField textField_s2uncertainty) {
		this.textField_s2Uncertainty = textField_s2uncertainty;
	}

	public JTextField getTextField_s3uncertainty() {
		return textField_s3Uncertainty;
	}

	public void setTextField_s3uncertainty(JTextField textField_s3uncertainty) {
		this.textField_s3Uncertainty = textField_s3uncertainty;
	}

	public void setTextField_s1x(String textField_s1x) {
		this.textField_s1x.setText(textField_s1x);
	}

	public void setTextField_s1y(String textField_s1y) {
		this.textField_s1y.setText(textField_s1y);
	}

	public void setTextField_s1Uncertaninty(String textField_s1Uncertaninty) {
		this.textField_s1Uncertaninty.setText(textField_s1Uncertaninty);
	}
	
	public void setTextField_s2Uncertaninty(String textField_s2Uncertaninty) {
		this.textField_s2Uncertainty.setText(textField_s2Uncertaninty);
	}
	
	public void setTextField_s3Uncertaninty(String textField_s3Uncertaninty) {
		this.textField_s3Uncertainty.setText(textField_s3Uncertaninty);
	}

	public void setTextField_s2x(String textField_s2x) {
		this.textField_s2x.setText(textField_s2x);
	}

	public void setTextField_s2y(String textField_s2y) {
		this.textField_s2y.setText(textField_s2y); 
	}

	public void setTextField_s3x(String textField_s3x) {
		this.textField_s3x.setText(textField_s3x);
	}

	public void setTextField_s3y(String textField_s3y) {
		this.textField_s3y.setText(textField_s3y);
	}

	public void setTextField_Range(String textField_Range) {
		this.textField_Range.setText(textField_Range);
	}

	public void setTextField_NLines(String textField_NLines) {
		this.textField_NLines.setText(textField_NLines);
	}

	public void setTextField_GridStep(String textField_GridStep) {
		this.textField_GridStep.setText(textField_GridStep);
	}

	public JTextField getTextField_s1y() {
		return textField_s1y;
	}

	public JTextField getTextField_s1Uncertaninty() {
		return textField_s1Uncertaninty;
	}

	public JTextField getTextField_s2x() {
		return textField_s2x;
	}

	public JTextField getTextField_s2y() {
		return textField_s2y;
	}

	public JTextField getTextField_s2Uncertaninty() {
		return textField_s2Uncertainty;
	}

	public JTextField getTextField_s3x() {
		return textField_s3x;
	}

	public JTextField getTextField_s3y() {
		return textField_s3y;
	}

	public JTextField getTextField_s3Uncertaninty() {
		return textField_s3Uncertainty;
	}

	public String getTextField_Range() {
		return textField_Range.getText();
	}

	public String getTextField_NLines() {
		return textField_NLines.getText();
	}

	public String getTextField_GridStep() {
		return textField_GridStep.getText();
	} 
	
	public String getOutputPath()
	{
		return textField_pathOutputFolder.getText();
	}
	
	public String getRelationsFilePath()
	{
		return getTextField_pathRelationsFile();
	}
	
	// End of Getters Setters
	
	

}
