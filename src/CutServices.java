
public class CutServices {

	
	// ----------------------------------------------- Is_Parallel_DF_Set -------------------------
	//---------------------------------------------------------------------------------------------
	boolean Is_Parallel_DF_Set(DF[]	DFs/*[MAX_Sensors]*/,
								Location_For_Cut_Type	LR_For_Cut)
	{
		// Minimum 2 DFs per Location
		if (LR_For_Cut.Actual_NumDFs < Cut.MIN_DF_NUM)
			return false;

		for (int i = 0; i < Cut.MAX_Sensors; i++)
		{
			if (DFs[i].GetUse())
			{
				LR_For_Cut.DF_For_Cut[i].Use = DFs[i].GetUse();
				LR_For_Cut.DF_For_Cut[i].DOA_Rad = DFs[i].GetAzimuth() * Cut.DEGREE_TO_RADIAN;
				LR_For_Cut.DF_For_Cut[i].Sensor.Sx = DFs[i].GetSensor().GetX();
				LR_For_Cut.DF_For_Cut[i].Sensor.Sy = DFs[i].GetSensor().GetY();
				LR_For_Cut.DF_For_Cut[i].Sensor.Sh = DFs[i].GetSensor().GetHeight();
				LR_For_Cut.DF_For_Cut[i].Bias_Rad = DFs[i].GetBias() * Cut.DEGREE_TO_RADIAN;
				
				double sin_DF = Math.sin (LR_For_Cut.DF_For_Cut[i].DOA_Rad);
				double cos_DF = Math.cos (LR_For_Cut.DF_For_Cut[i].DOA_Rad);
				
				LR_For_Cut.DF_For_Cut[i].a = cos_DF;
				LR_For_Cut.DF_For_Cut[i].b = -sin_DF;

				LR_For_Cut.DF_For_Cut[i].c = (DFs[i].GetSensor().GetY() * sin_DF) - (DFs[i].GetSensor().GetX() * cos_DF);

				LR_For_Cut.DF_For_Cut[i].w = -1.0;

				if (Cut.PRINT)
					System.out.format("Is_Parallel_DF_Set - DF%d  a=%2.6f  b=%2.6f  c=%4.6f  w=%f  Sx=%3.6f  Sy=%3.6f  Status=%s%n",
						i, LR_For_Cut.DF_For_Cut[i].a, LR_For_Cut.DF_For_Cut[i].b, LR_For_Cut.DF_For_Cut[i].c, LR_For_Cut.DF_For_Cut[i].w,
						DFs[i].GetSensor().GetX(), DFs[i].GetSensor().GetY(), ((DFs[i].GetUse()) ? "OK" : "NODF"));
			}

		}

		if (Cut.PRINT)
			System.out.format("%n");

		return DF_Pencil (LR_For_Cut);
	}


	// ----------------------------------------------- DF_Pencil ----------------------------------
	//---------------------------------------------------------------------------------------------
	boolean DF_Pencil (Location_For_Cut_Type	LR_For_Cut)
	{
		boolean ret_Status = false;

		//If DFS number least than 2 => return fail
		if (LR_For_Cut.Actual_NumDFs < Cut.MIN_DF_NUM)
		{
			if (Cut.PRINT)
				System.out.format("%nDF_Pencil - Failed: Less than minimun 2 DFs%n");

			return false;
		}

		LR_For_Cut.Test_Matrix.A11 = 0.0;
		LR_For_Cut.Test_Matrix.A12 = 0.0;
		LR_For_Cut.Test_Matrix.A21 = 0.0;
		LR_For_Cut.Test_Matrix.A22 = 0.0;
		LR_For_Cut.Test_Matrix.B1 = 0.0;
		LR_For_Cut.Test_Matrix.B2 = 0.0;

		for (int i = 0; i < Cut.MAX_Sensors; i++)
		{
			if (LR_For_Cut.DF_For_Cut[i].Use)
			{
				LR_For_Cut.Test_Matrix.A11 += LR_For_Cut.DF_For_Cut[i].a * LR_For_Cut.DF_For_Cut[i].a;
				LR_For_Cut.Test_Matrix.A22 += LR_For_Cut.DF_For_Cut[i].b * LR_For_Cut.DF_For_Cut[i].b;
				LR_For_Cut.Test_Matrix.A12 += LR_For_Cut.DF_For_Cut[i].a * LR_For_Cut.DF_For_Cut[i].b;

				LR_For_Cut.Test_Matrix.B1 += LR_For_Cut.DF_For_Cut[i].a * LR_For_Cut.DF_For_Cut[i].c;
				LR_For_Cut.Test_Matrix.B2 += LR_For_Cut.DF_For_Cut[i].b * LR_For_Cut.DF_For_Cut[i].c;

				ret_Status = true;
			}
		}

		LR_For_Cut.Test_Matrix.A21 = LR_For_Cut.Test_Matrix.A12;
		LR_For_Cut.Test_Matrix.B1 = -LR_For_Cut.Test_Matrix.B1;
		LR_For_Cut.Test_Matrix.B2 = -LR_For_Cut.Test_Matrix.B2;

		if (Cut.PRINT)
			System.out.format("DF_Pencil - Pencil Matrix: A11=%2.6f  A22=%2.6f  A12=%2.6f  A21=%2.6f  B1=%2.6f  B2=%2.6f%n%n",
				LR_For_Cut.Test_Matrix.A11, LR_For_Cut.Test_Matrix.A22, LR_For_Cut.Test_Matrix.A12, LR_For_Cut.Test_Matrix.A21, LR_For_Cut.Test_Matrix.B1, LR_For_Cut.Test_Matrix.B2);

		if (ret_Status)
			ret_Status = Solve_Two_Equations(LR_For_Cut);

		if (ret_Status == true)
		{
			//For each DF check if the location has legal direction
			for (int i = 0; i < Cut.MAX_Sensors; i++)
			{
				if (LR_For_Cut.DF_For_Cut[i].Use)

					ret_Status = Check_Direction	(LR_For_Cut.DF_For_Cut[i].a,
													LR_For_Cut.DF_For_Cut[i].b,
													LR_For_Cut.CutX - LR_For_Cut.DF_For_Cut[i].Sensor.Sx,
													LR_For_Cut.CutY - LR_For_Cut.DF_For_Cut[i].Sensor.Sy);
			}
		}

		if (Cut.PRINT)
			System.out.format("%nDF_Pencil - Succeeded: %s%n", (ret_Status ? "Yes" : "No"));

		return ret_Status;
	}


	// ----------------------------------------------- Solve_Two_Equations ----------------------------------
	//-------------------------------------------------------------------------------------------------------
	boolean Solve_Two_Equations (Location_For_Cut_Type	LR_For_Cut)
	{
		boolean ret_Status = true;

		double  det;
		double  det_X;
		double  det_Y;

		//Clear cut_Point
		LR_For_Cut.CutX = 0.0;
		LR_For_Cut.CutY = 0.0;

		det = (LR_For_Cut.Test_Matrix.A11 * LR_For_Cut.Test_Matrix.A22) - (LR_For_Cut.Test_Matrix.A12 * LR_For_Cut.Test_Matrix.A21);

		if (det < Cut.SMALL_DET)
			ret_Status = false;

		if (Cut.PRINT)
			System.out.format("Solve_Two_Equations - det = %f  %s  %f  ", det, (ret_Status ? "Succeeded >= " : "Failed < "), Cut.SMALL_DET);

		if (ret_Status)
		{
			det_X = (LR_For_Cut.Test_Matrix.B1 * LR_For_Cut.Test_Matrix.A22) - (LR_For_Cut.Test_Matrix.B2 * LR_For_Cut.Test_Matrix.A12);
			det_Y = (LR_For_Cut.Test_Matrix.B2 * LR_For_Cut.Test_Matrix.A11) - (LR_For_Cut.Test_Matrix.B1 * LR_For_Cut.Test_Matrix.A21);

			LR_For_Cut.CutX = det_X / det;
			LR_For_Cut.CutY = det_Y / det;

			if (Cut.PRINT)
				System.out.format("det_X = %f  det_Y = %f  CutX = %f  CutY = %f", det_X, det_Y, LR_For_Cut.CutX, LR_For_Cut.CutY);

			ret_Status = true;
		}

		if (Cut.PRINT)
			System.out.format("%n%n");

		return ret_Status;
	}


	// ----------------------------------------------- Check_Direction --------------------------------------
	//-------------------------------------------------------------------------------------------------------
	boolean Check_Direction	(double	a,
							double	b,
							double	delta_X,
							double	delta_Y)
	{
		boolean  ret_Status = false;
		double Calc;

		Calc = delta_Y * a - delta_X * b;

		if (Calc > 0.0)
			ret_Status = true;

		if (Cut.PRINT)
			System.out.format("Check_Direction - Calc = %f  > 0.0  -> Succeeded = %s%n", Calc, (ret_Status ? "Yes" : "No"));

		return ret_Status;
	}


	// ----------------------------------------- Point_Belong_2_Pencil --------------------------------------
	//-------------------------------------------------------------------------------------------------------
	boolean Point_Belong_2_Pencil (Location_For_Cut_Type	LR_For_Cut)
	{
		boolean	retVal = true;

		double  dx;
		double  dy;
		double  norm;
		double	sin_Alfa;
		double	cos_Alfa;
		double	sin_Angle;

		for (int i = 0; i < Cut.MAX_Sensors; i++)
		{
			if (LR_For_Cut.DF_For_Cut[i].Use)
			{
				double  a = LR_For_Cut.DF_For_Cut[i].a;
				double  b = LR_For_Cut.DF_For_Cut[i].b;

				dx = LR_For_Cut.CutX - LR_For_Cut.DF_For_Cut[i].Sensor.Sx;
				dy = LR_For_Cut.CutY - LR_For_Cut.DF_For_Cut[i].Sensor.Sy;
				double Calc = ((dy * a) - (dx * b));

				if (Calc < 0.0)
				{
					if (Cut.PRINT)
						System.out.format("Point_Belong_2_Pencil - DF%d  dx = %2.6f  dy = %2.6f  Calc = %4.6f  < 0.0    Status=%s%n", i, dx, dy, Calc, (Calc >= 0.0) ? "Succeeded" : "Failed");

					return false;
				}
				else
				{
					if (Cut.PRINT)
						System.out.format("Point_Belong_2_Pencil - DF%d  dx = %2.6f  dy = %2.6f  Calc = %4.6f  > 0.0    Status=%s%n", i, dx, dy, Calc, (Calc >= 0.0) ? "Succeeded" : "Failed");
				}

				norm = 1.0 / Math.sqrt(dx * dx + dy * dy);

				sin_Alfa = dx * norm;
				cos_Alfa = dy * norm;

				sin_Angle = Math.abs (sin_Alfa * a + cos_Alfa * b);

				if (Cut.PRINT)
					System.out.format("Point_Belong_2_Pencil - DF%d  dx=%f  dy=%f  a=%f  b=%f  Calc=%f  norm=%f  sin_Alfa=%f  cos_alfa-%f  sin_Angle=%f %n",
						i, dx, dy, a, b, Calc, norm, sin_Alfa, cos_Alfa, sin_Angle);

			}
		}

		return retVal;
	}


	// ------------------------------------------------------ DOAs_Cut --------------------------------------
	//-------------------------------------------------------------------------------------------------------
	boolean DOAs_Cut (Location_For_Cut_Type	LR_For_Cut)
	{
		double	dx;
		double	dy;
		double	CutX_Back;
		double	CutY_Back;
		double	shift;
		int		iIteration_Counter = 0;

		double	delta_X;
		double	delta_Y;
		double	d;
		double  w;



		CutX_Back = LR_For_Cut.CutX;
		CutY_Back = LR_For_Cut.CutY;

		shift = Cut.SMALL_SHIFT + 1;

		while (shift > Cut.SMALL_SHIFT)
		{
			if (iIteration_Counter > Cut.MAX_CUT_ITTERATIONS)
			{
				if (Cut.PRINT)
					System.out.format("DOAs_Cut - Too many itterations  %d  Shift = %f still > %f   Failed %n", iIteration_Counter, shift, Cut.SMALL_SHIFT);

				return false;
			}

			iIteration_Counter++;

			for (int i = 0; i < Cut.MAX_Sensors; i++)
			{
				if (LR_For_Cut.DF_For_Cut[i].Use)
				{
					dx = LR_For_Cut.CutX - LR_For_Cut.DF_For_Cut[i].Sensor.Sx;
					dy = LR_For_Cut.CutY - LR_For_Cut.DF_For_Cut[i].Sensor.Sy;

					double DF_Noise_For_Ellipse = Math.sin(LR_For_Cut.DF_For_Cut[i].Bias_Rad / 2);
					d = DF_Noise_For_Ellipse * (dy * LR_For_Cut.DF_For_Cut[i].a - dx * LR_For_Cut.DF_For_Cut[i].b);
					// d = Cut.DF_NOISE_FOR_ELLIPSE_CALC * (dy * LR_For_Cut.DF_For_Cut[i].a - dx * LR_For_Cut.DF_For_Cut[i].b);
					w = 1.0 / d;

					LR_For_Cut.DF_For_Cut[i].w = w;

					LR_For_Cut.DF_For_Cut[i].a = LR_For_Cut.DF_For_Cut[i].a * w;
					LR_For_Cut.DF_For_Cut[i].b = LR_For_Cut.DF_For_Cut[i].b * w;
					LR_For_Cut.DF_For_Cut[i].c = LR_For_Cut.DF_For_Cut[i].c * w;
				}
			}

			//DF pencil test
			if (DF_Pencil (LR_For_Cut) == false)
			{
				if (Cut.PRINT)
					System.out.format("DOAs_Cut - DF_Pencil Failed %n");

				return false;
			}

			delta_X = LR_For_Cut.CutX - CutX_Back;
			delta_Y = LR_For_Cut.CutY - CutY_Back;

			shift = Math.max (Math.abs (delta_X), Math.abs (delta_Y) );

			//Save new point
			CutX_Back = LR_For_Cut.CutX;
			CutY_Back = LR_For_Cut.CutY;

			if (Cut.PRINT)
				System.out.format("DOAs_Cut - Itteration = %d < 20  delta_X = %f  delta_Y = %f  shift = %f > %f %n", iIteration_Counter, delta_X, delta_Y, shift, Cut.SMALL_SHIFT);
		}

		if (Cut.PRINT)
			System.out.format("DOAs_Cut - shift = %f < %f  Succeeded%n", shift, Cut.SMALL_SHIFT);

		return true;
	}


	// ------------------------------------------- Ellipse_Calculation --------------------------------------
	//-------------------------------------------------------------------------------------------------------
	boolean Ellipse_Calculation	(Location			LR,
								Location_For_Cut_Type	LR_For_Cut)
	{
		boolean	retVal = false;

		double det = 0;


		LR.SetX(LR_For_Cut.CutX);
		LR.SetY(LR_For_Cut.CutY);

		LR.GetEllipse().SetDispersion(0.0);

		retVal = Calc_Ellipse_For_Invert_Matrix (LR_For_Cut.Test_Matrix.A11,
												LR_For_Cut.Test_Matrix.A22,
												LR_For_Cut.Test_Matrix.A12,
												LR.GetEllipse());


		if (Cut.PRINT)
			System.out.format("Ellipse_Calculation - CCalc_Ellipse_For_Invert_Matrix  %s %n", (retVal) ? "Succeeded" : "Failed");

		if (retVal == false)
		{
			return false;
		}

		//Maximum ellipse major for LR check
		if (LR.GetEllipse().GetSemiMajor() > Cut.MAX_LR_ELLIPSE_MAJOR)
		{
			if (Cut.PRINT)
				System.out.format("Ellipse_Calculation - SemiMajor = %f > %f%n", LR.GetEllipse().GetSemiMajor(), Cut.MAX_LR_ELLIPSE_MAJOR);

			return false;
		}

		// Co-Variance matrix calculation

		retVal = Invert2_Calc	(LR_For_Cut.Test_Matrix.A11,
								LR_For_Cut.Test_Matrix.A22,
								LR_For_Cut.Test_Matrix.A12,
								det,
								LR.GetEllipse().GetR11(),
								LR.GetEllipse().GetR22(),
								LR.GetEllipse().GetR12());
		
		if (Cut.PRINT)
			System.out.format("EllipseCalculation - Invert2_Calc  det = %f  %s %n", det, (retVal) ? "Succeeded" : "Failed");

		if (retVal == false)
		{
			return false;
		}

		//Maximum ellipse major for LR check
		if (LR.GetEllipse().GetSemiMajor() > Cut.MAX_LR_ELLIPSE_MAJOR)
		{
			if (Cut.PRINT)
				System.out.format("EllipseCalculation - SemiMajor = %f > %f%n", LR.GetEllipse().GetSemiMajor(), Cut.MAX_LR_ELLIPSE_MAJOR);

			return false;
		}

		return true;

	}


	double Determinate_Calc	(double  A,
							double  B,
							double  C)
	{
		return ((A * B) - (C * C));
	}


	// ------------------------------------------- Calc_Ellipse_For_Invert_Matrix --------------------------------------
	//------------------------------------------------------------------------------------------------------------------
	boolean Calc_Ellipse_For_Invert_Matrix (double	A,
											double  B,
											double  C,
											Ellipse	Ellipse)
	{
		double  I;
		double  lm_1;
		double  lm_2;
		double  tmp;

		double  det = Determinate_Calc(A, B, C);
		det *= Cut.DOUBLE_QUARTA;

		if (det < Cut.SMALL_DET)
		{
			if (Cut.PRINT)
				System.out.format("Calc_Ellipse_For_Invert_Matrix - det = %f  <  %f   Failed %n", det, Cut.SMALL_DET);

			return false;

		}

		I = (A + B) * Cut.DOUBLE_QUARTA;
		tmp = Math.sqrt((I * I) - det);

		//lambda calculation
		lm_1 = I + tmp;
		lm_2 = I - tmp;

		//Set ellipse major, minor and angle
		Ellipse.SetSemiMajor(Math.sqrt(Cut.ELL_CONST / lm_2));
		Ellipse.SetSemiMinor(Math.sqrt(Cut.ELL_CONST / lm_1));

		Ellipse.SetAngle((0.5 * Math.atan2 (2.0 * C, (A - B))) * Cut.RADIAN_TO_DEGREE);

		Ellipse.SetAngle(90 - Ellipse.GetAngle());
		while (Ellipse.GetAngle() < 0)
		{
			Ellipse.SetAngle(Ellipse.GetAngle() + 180);
		}

		Ellipse.SetAngle(Ellipse.GetAngle() + 90);
		if (Ellipse.GetAngle() > 360)
		{
			Ellipse.SetAngle(Ellipse.GetAngle() - 180);
		}


		if (Cut.PRINT)
			System.out.format("Calc_Ellipse_For_Invert_Matrix - Semi-Major = %f  Semi-Minor = %f  Angle = %f   Succeeded %n", Ellipse.GetSemiMajor(), Ellipse.GetSemiMinor(), Ellipse.GetAngle());

		return true;
	}


	boolean Invert2_Calc (double	R11,
						  double   	R22,
						  double   	R12,
						  double   	det,
						  double   	New_R11,
						  double   	New_R22,
						  double    New_R12)
	{
		det = Determinate_Calc(R11, R22, R12);

		if (det < Cut.SMALL_DET)
		{
			if (Cut.PRINT)
				System.out.format("Invert2_Calc - det = %f  <  %f   Status = Failed %n", det, Cut.SMALL_DET);

			return false;
		}

		double  inv_Det = 1.0 / det;

		New_R11 = R22 * inv_Det;
		New_R22 = R11 * inv_Det;
		New_R12 = -1.0 * R12 * inv_Det;

		return true;
	}

}
