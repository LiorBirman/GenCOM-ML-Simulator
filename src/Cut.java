
public class Cut {

	// Constants declarations
	public static final boolean PRINT = false;
	public static final boolean PRINT_RESULTS = false;
	
	private static final double LIGHT_SPEED_KM_PER_SEC = 300000.0;
	
	public static final double PI = 3.141592653;
	public static final double DEGREE_TO_RADIAN = 2 * PI / 360.0;
	public static final double RADIAN_TO_DEGREE = 360 / (2 * PI);
	public static final double SMALL_DET = 0.00000000000000000001;
	public static final double SMALL_SHIFT = 0.001;
	public static final double MAX_DF_CUT_ANGLE_TO_RADIAN = 0.17999999999999999;
	public static final double DF_NOISE_FOR_ELLIPSE_CALC = 0.0087260399999999991;
	public static final double MAX_LR_ELLIPSE_MAJOR = 100.0;
	public static final double ELL_CONST = 4.0;
	public static final double DOUBLE_QUARTA = 0.25;
	
	public static final int MAX_Sensors = 3;
	public static final int MIN_DF_NUM = 2;
	public static final int MAX_CUT_ITTERATIONS = 20;

	public CutServices cutServices = new CutServices();
	public Location_For_Cut_Type LR_For_Cut = new Location_For_Cut_Type();

	// DF Cut - location calculation
	public boolean perform_DF_Cut(Location LR, Location_For_Cut_Type LR_For_Cut) {
		boolean retVal = false;

		LR_For_Cut.Actual_NumDFs = 0;

		for (int i = 0; i < MAX_Sensors; i++)
			LR_For_Cut.Actual_NumDFs++;

		retVal = cutServices.Is_Parallel_DF_Set(LR.GetDFSet(), LR_For_Cut);

		if (retVal == false) {
			if (PRINT)
				System.out.format("perform_DF_Cut - Is_Parallel_DF_Set = %s%n", (retVal) ? "Succeeded" : "Failed");
		}

		if (retVal == true) {
			retVal = cutServices.Point_Belong_2_Pencil(LR_For_Cut);
			if (retVal == false) {
				if (PRINT)
					System.out.format("perform_DF_Cut - Point_Belong_2_Pencil = %s%n",
							(retVal) ? "Succeeded" : "Failed");
			} else {
				retVal = cutServices.DOAs_Cut(LR_For_Cut);

				if (retVal) {
					LR.SetX(LR_For_Cut.CutX);
					LR.SetY(LR_For_Cut.CutY);
				}

				if (PRINT_RESULTS)
					System.out.format("%nperform_DF_Cut - DOAs_Cut Cut = (%f, %f)  Loc = (%f, %f) - %s%n",
							LR_For_Cut.CutX, LR_For_Cut.CutY, LR.GetX(), LR.GetY(), (retVal) ? "Succeeded" : "Failed");
			}
		}

		if (PRINT)
			System.out.format("%n");

		return retVal;
	}
	/// Ellipse Calculation
	public boolean perform_Ellipse_Calc(Location LR) {
		boolean retVal = false;

		retVal = cutServices.Ellipse_Calculation(LR, LR_For_Cut);

		if (PRINT_RESULTS)
			System.out.format(
					"perform_DF_Cut - Ellipse_Calculation -  Semi-Major = %f  Semi-Minor = %f  Angle = %f  %s%n",
					LR.GetEllipse().GetSemiMajor(), LR.GetEllipse().GetSemiMinor(), LR.GetEllipse().GetAngle(),
					(retVal) ? "Succeeded" : "Failed");

		/*
		 * 
		 * if (retVal == false) {
		 * 
		 * retVal = isPointInAOI(cut_Point); // -------------------------------- if
		 * (retVal == false) { if (PRINT)
		 * System.out.format("perform_DF_Cut - isPointInAOI = %s%n", (retVal) ? "In AOI"
		 * : "Out AOI"); } else { if (PRINT)
		 * System.out.format("perform_DF_Cut - isPointInAOI = %s%n", (retVal) ? "In AOI"
		 * : "Out AOI"); } }
		 */
		return retVal;
	}
	public double perform_Calc_DF	(double	Sx,	double	Sy,	double   TxX, double	TxY)
	{

		double delta_X = TxX - Sx;
		double delta_Y = TxY - Sy;
		double radian_Geo = Math.atan2 (delta_X, delta_Y);

		double deg_DOA = radian_Geo * RADIAN_TO_DEGREE;

		// North - East
		if ((delta_X > 0.0) && (delta_Y > 0.0))
		{
			if (deg_DOA < 0)
			{	
				deg_DOA = 360.0 - deg_DOA;
			}
		}

		//North - West
		if ((delta_X < 0.0) && (delta_Y > 0.0))
		{
			if (deg_DOA < 0)
			{
				deg_DOA = 360.0 + deg_DOA;
			}
		}

		//South - West
		if ((delta_X < 0.0) && (delta_Y < 0.0))
		{
			if (deg_DOA < 0)
			{
				deg_DOA = 360.0 + deg_DOA;
			}
			else
			{
				deg_DOA = 360.0 - deg_DOA;
			}
		}

		//South - East
		if ((delta_X > 0.0) && (delta_Y < 0.0))
		{
			if (deg_DOA < 0)
			{
				deg_DOA = 360.0 + deg_DOA;
			}
		}

		// DOA should be in range:    >= 0 && <360
		deg_DOA = (int)(deg_DOA) % 360 + deg_DOA - (int)(deg_DOA);

		//NTAA doesn't receive negative degrees 
		if (deg_DOA < 0.0)
		{
			deg_DOA = deg_DOA + 360.0;
		}

		// For future use: Calculate Distance Signal Propagation Time
		double Distance = Math.sqrt((delta_X * delta_X) + (delta_Y * delta_Y));
		double Propagation_Time = Distance / LIGHT_SPEED_KM_PER_SEC;

		if (PRINT_RESULTS)
			System.out.format("perform_Calc_DF - Sx = = %f  Sy = %f  TxX = %f  TxY = %f DOA = %f  Distance = %f  PropagationTime = %f\n", Sx, Sy, TxX, TxY, deg_DOA, Distance, Propagation_Time);

		return deg_DOA;
	}
	public double perform_DFsAnglesDiff_Calc (DF df[])
	{
		
		double angle_1 = (df[0].GetAzimuth() - df[1].GetAzimuth() + 360) % 360;
		if (angle_1 > 180)
			angle_1 = 360 - angle_1;
		
		double angle_2 = (df[0].GetAzimuth() - df[2].GetAzimuth() + 360) % 360;
		if (angle_2 > 180)
			angle_2 = 360 - angle_2;
		
		double angle_3 = (df[1].GetAzimuth() - df[2].GetAzimuth() + 360) % 360;
		if (angle_3 > 180)
			angle_3 = 360 - angle_3;
		
		return Math.max(Math.max(angle_1, angle_2), angle_3);
	}
	public double perform_Distance_Calc(double x_1, double y_1, double x_2, double y_2)
	{
		// Pythagoras
		double a = Math.pow(x_2 - x_1, 2);
		double b = Math.pow(y_2 - y_1, 2);
		return Math.sqrt(a + b);
	}
}


