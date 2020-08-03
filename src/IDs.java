
public class IDs 
{
	// Data Members
	private int m_TxID = 1;
	private int m_MsrID = 1;

	
	// Getters
	public int GetTxID() {return m_TxID;}
	public int GetMsrID() {return m_MsrID;}
	
	// GeneralFunctions
    public int GetAndIncrementTxID() {return m_TxID++;}
    public int GetAndIncrementMsrID() {return m_MsrID++;}
}
