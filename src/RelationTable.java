import java.util.ArrayList;

public class RelationTable 
{
	// Private Data Members
	private ArrayList<String> m_Name;
	private ArrayList<String> m_Operator;
	private ArrayList<Integer> m_P1;
	private ArrayList<Integer> m_P2;
	private ArrayList<Double> m_Value;
	private int m_TableLength = 0;
	private int m_Line = 1;

	// Constructor
	public RelationTable()
	{
		this.m_Name = new ArrayList<String>();
		this.m_Operator = new ArrayList<String>();
		this.m_P1 = new ArrayList<Integer>();
		this.m_P2 = new ArrayList<Integer>();
		this.m_Value = new ArrayList<Double>();
		this.Init();
	}
	
	// Private Functions
	private void ClearTable() // Clear The Table If Not Empty
	{
			this.m_Name.clear();
			this.m_Operator.clear();
			this.m_P1.clear();
			this.m_P2.clear();
			this.m_Value.clear();
			this.m_TableLength = 0;
			this.m_Line = 1;
	}
	private void Init() // Insert All Base Names To The Table, Must be Hard Coded.
	{
		this.ClearTable();
		
		this.Insert("TxAz1", "", 0, 0, 0);
		this.Insert("TxAz2", "", 0, 0, 0);
		this.Insert("TxAz3", "", 0, 0, 0);

		this.Insert("TxX", "", 0, 0, 0);
		this.Insert("TxY", "", 0, 0, 0);

		this.Insert("TxMajor", "", 0, 0, 0);
		this.Insert("TxMinor", "", 0, 0, 0);
		this.Insert("TxAngle", "", 0, 0, 0);

		this.Insert("TxAzDist", "", 0, 0, 0);
		this.Insert("TxDistance", "", 0, 0, 0);

		this.Insert("S1X", "", 0, 0, 0);
		this.Insert("S1Y", "", 0, 0, 0);
		this.Insert("MsrAz1", "", 0, 0, 0);

		this.Insert("S1Dist", "", 0, 0, 0);

		this.Insert("S2X", "", 0, 0, 0);
		this.Insert("S2Y", "", 0, 0, 0);
		this.Insert("MsrAz2", "", 0, 0, 0);

		this.Insert("S2Dist", "", 0, 0, 0);

		this.Insert("S3X", "", 0, 0, 0);
		this.Insert("S3Y", "", 0, 0, 0);
		this.Insert("MsrAz3", "", 0, 0, 0);
		this.Insert("S3Dist", "", 0, 0, 0);

		this.Insert("AvgSx", "", 0, 0, 0);
		this.Insert("AvgSy", "", 0, 0, 0);

		this.Insert("MsrX", "", 0, 0, 0);
		this.Insert("MsrY", "", 0, 0, 0);

		this.Insert("MsrMajor", "", 0, 0, 0);
		this.Insert("MsrMinor", "", 0, 0, 0);
		this.Insert("MsrAngle", "", 0, 0, 0);

		this.Insert("MsrAzDist", "", 0, 0, 0);
		this.Insert("MsrDistance", "", 0, 0, 0);
		
		this.Insert("AttachedToTx", "", 0, 0, 0);
	}
	
	// General Functions
	public void CalculateValues() throws Exception // Calculate Value Each Arithmetic Relations From The Table
	{
		for (int i = 0; i < m_TableLength; i++) {
			switch (m_Operator.get(i)) {
			case "+":
				m_Value.set(i, m_Value.get(m_P1.get(i)) + m_Value.get(m_P2.get(i)));
				break;
			case "-":
				m_Value.set(i, m_Value.get(m_P1.get(i)) - m_Value.get(m_P2.get(i)));
				break;
			case "*":
				m_Value.set(i, m_Value.get(m_P1.get(i)) * m_Value.get(m_P2.get(i)));
				break;
			case "/":
				// Sometimes Operand 2 Will Be Zero After All Calculations Without The
				// Possibility To Control It
				// So In Cases Like This We Set Value = 0
				// According To Customer's Requirement CRC-005
				if (m_Value.get(m_P2.get(i)) == 0)
				{
					m_Value.set(i, 0.0);
				} 
				else 
				{
					m_Value.set(i, m_Value.get(m_P1.get(i)) / m_Value.get(m_P2.get(i)));
				}
				break;
			case "^":
				m_Value.set(i, Math.pow(m_Value.get(m_P1.get(i)), m_Value.get(m_P2.get(i))));
				break;
			}
		}
	}

	public void Insert(String name, String operator, int p1, int p2, double value) // Insert A Member Into The Table
	{
		m_Name.add(name);
		m_Operator.add(operator);
		m_P1.add(p1);
		m_P2.add(p2);
		m_Value.add(value);
		m_TableLength++;
	}

	public void ParsAndInsert(String line, int j) throws Exception // Read & Insert Each Line From Relations File
	{
		String name = "";
		String operator = "";
		String p1s = "";
		String p2s = "";
		int p1 = 0;
		int p2 = 0;
		double value = 0;
		int i = 0;

		while (line.charAt(i) == ' ')
			i++;

		// Get Variable Name & Throw If Duplicate
		while (line.charAt(i) != ' ' && line.charAt(i) != '=' && line.charAt(i) != '\r' && line.charAt(i) != ';') {
			name += line.charAt(i);
			i++;
		}
		if (this.FindName(name) != -1) {
			throw new Exception("Error: Relations File Line " + j + " Duplicate Name \"" + name + "\"");
		}

		while (line.charAt(i) == ' ' || line.charAt(i) == '=')
			i++;

		// Get Operand 1 Name
		while ((Character.isLetter(line.charAt(i)) || Character.isDigit(line.charAt(i)) || line.charAt(i) == '.')
				&& line.charAt(i) != '\r' && line.charAt(i) != ';') {
			p1s += line.charAt(i);
			i++;
		}

		while (line.charAt(i) == ' ')
			i++;

		// Get Operator
		if (line.charAt(i) != '\r' && line.charAt(i) != ';') {
			operator += line.charAt(i);
			i++;
			if (operator == "") {
				operator = "=";
			}
		}

		while (line.charAt(i) == ' ')
			i++;

		// Get Operand 2 Name
		while ((Character.isLetter(line.charAt(i)) || Character.isDigit(line.charAt(i))) && line.charAt(i) != '\r'
				&& line.charAt(i) != ';') {
			p2s += line.charAt(i);
			i++;
		}

		// Get Value If Operand 1 Is A Number Or Get Index If Operand 1 Is A Variable
		// Name & Throw If Operand 1 Does Not Exist
		if (Character.isLetter(p1s.charAt(0))) {
			p1 = FindName(p1s);
			if (p1 == -1) {
				throw new Exception("Error: Relations File Line " + j + ": Undefined Operand 1 \"" + p1s + "\"");
			}
		} else {
			p1 = (int) Double.parseDouble(p1s);
			value = Double.parseDouble(p1s);
		}

		// Get Index If Operand 2 Is A Variable & Throw If Operand 2 Does Not Exist Or
		// Is Not A Variable Name
		if (p2s != "") {
			if (Character.isDigit(p2s.charAt(0))) {
				throw new Exception("Error: Relations File Line " + j + ": Operand 2 Must Be A Variable Name");
			}

			if (Character.isLetter(p2s.charAt(0))) {
				p2 = FindName(p2s);
				if (p2 == -1) {
					throw new Exception("Error: Relations File Line " + j + ": Undefined Operand 2 \"" + p2s + "\"");
				}
			}
		}

		this.Insert(name, operator, p1, p2, value);
	}

	public int FindName(String name) // Return Variable Name Index Or -1 If Does Not Exist
	{
		for (int i = 0; i < this.m_TableLength; i++) {
			if (name.equals(this.m_Name.get(i)))
				return i;
		}

		return -1;
	}

	public void SetBaseValues(Transmitter t, Measurement m) // Get Base Data From Tx & Measurement Into The Table
	{
		m_Value.set(0, t.GetDF(0).GetAzimuth());
		m_Value.set(1, t.GetDF(1).GetAzimuth());
		m_Value.set(2, t.GetDF(2).GetAzimuth());

		m_Value.set(3, t.GetX());
		m_Value.set(4, t.GetY());

		m_Value.set(5, t.GetEllipse().GetSemiMajor());
		m_Value.set(6, t.GetEllipse().GetSemiMinor());
		m_Value.set(7, t.GetEllipse().GetAngle());

		m_Value.set(8, t.GetLocAngleRange());
		m_Value.set(9, t.GetLocDistance());

		m_Value.set(10, m.GetDF(0).GetSensor().GetX());
		m_Value.set(11, m.GetDF(0).GetSensor().GetY());
		m_Value.set(12, m.GetDF(0).GetAzimuth());

		m_Value.set(13, m.GetDF(0).GetSensorDist());

		m_Value.set(14, m.GetDF(1).GetSensor().GetX());
		m_Value.set(15, m.GetDF(1).GetSensor().GetY());
		m_Value.set(16, m.GetDF(1).GetAzimuth());

		m_Value.set(17, m.GetDF(1).GetSensorDist());

		m_Value.set(18, m.GetDF(2).GetSensor().GetX());
		m_Value.set(19, m.GetDF(2).GetSensor().GetY());
		m_Value.set(20, m.GetDF(2).GetAzimuth());

		m_Value.set(21, m.GetDF(2).GetSensorDist());

		m_Value.set(22, (m.GetDF(0).GetSensor().GetX() + m.GetDF(1).GetSensor().GetX() + m.GetDF(2).GetSensor().GetX()) / 3);
		m_Value.set(23, (m.GetDF(0).GetSensor().GetY() + m.GetDF(1).GetSensor().GetY() + m.GetDF(2).GetSensor().GetY()) / 3);

		m_Value.set(24, m.GetX());
		m_Value.set(25, m.GetY());

		m_Value.set(26, m.GetEllipse().GetSemiMajor());
		m_Value.set(27, m.GetEllipse().GetSemiMinor());
		m_Value.set(28, m.GetEllipse().GetAngle());

		m_Value.set(29, m.GetLocAngleRange());
		m_Value.set(30, m.GetLocDistance());

		double attachedtotx = 0;
		if (m.GetAttachedToTx())
			attachedtotx = 1;
		m_Value.set(31, attachedtotx);
	}

	public void IncrementLine() {m_Line++;}
	
	// Getters
	public String GetName(int i) {
		return m_Name.get(i);
	}

	public String GetOperator(int i) {
		return m_Operator.get(i);
	}

	public int GetP1(int i) {
		return m_P1.get(i);
	}

	public int GetP2(int i) {
		return m_P2.get(i);
	}

	public int GetTableLength() {
		return m_TableLength;
	}

	public int GetLine() {
		return m_Line;}

	public double GetValue(int i) {
		return m_Value.get(i);
	}
	
}
