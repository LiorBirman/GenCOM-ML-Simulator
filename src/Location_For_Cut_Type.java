
public class Location_For_Cut_Type {
	int							Actual_NumDFs;
	DF_For_Cut_Type[]			DF_For_Cut;
	double						CutX;
	double						CutY;
	Test_Matrix_Type			Test_Matrix;
	
	public Location_For_Cut_Type() {
		DF_For_Cut = new DF_For_Cut_Type[Cut.MAX_Sensors];
		for (int i = 0; i < Cut.MAX_Sensors; i++)
			DF_For_Cut[i] = new DF_For_Cut_Type();
		Test_Matrix = new Test_Matrix_Type();
	}
	
}

class DF_For_Cut_Type {
	double		DOA_Rad;
	double		a;
	double		b;
	double		c;
	double		w;
	double      Bias_Rad;
	Sensor_Type	Sensor;
	boolean		Use;
	
	public DF_For_Cut_Type() {
		Sensor = new Sensor_Type();
	}
}

class Test_Matrix_Type {
	double  A11;
	double  A12;
	double  A21;
	double  A22;
	double  B1;
	double  B2;
}