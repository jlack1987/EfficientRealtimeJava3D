public class Matrix3d
{
	double m00, m01, m02;
	double m10, m11, m12;
	double m20, m21, m22;

	double tmp9Array[] = new double[9];
	int tmp3Array[] = new int[3];
	double[] tmp9Array2 = new double[9];
	double tmp3Array2[] = new double[3];

	private static final double EPS = 1E-16;

	public Matrix3d()
	{
		m00 = m01 = m02 = 0;
		m10 = m11 = m12 = 0;
		m20 = m21 = m22 = 0;
	}

	public Matrix3d(double[] doubleArray)
	{
		if (doubleArray.length != 9)
		{
			throw new RuntimeException(
					"Double array must contain exactly 9 elements.");
		}

		this.m00 = doubleArray[0];
		this.m01 = doubleArray[1];
		this.m02 = doubleArray[2];
		this.m10 = doubleArray[3];
		this.m11 = doubleArray[4];
		this.m12 = doubleArray[5];
		this.m20 = doubleArray[6];
		this.m21 = doubleArray[7];
		this.m22 = doubleArray[8];
	}

	public Matrix3d(Matrix3d matrix)
	{
		this.m00 = matrix.m00;
		this.m01 = matrix.m01;
		this.m02 = matrix.m02;
		this.m10 = matrix.m10;
		this.m11 = matrix.m11;
		this.m12 = matrix.m12;
		this.m20 = matrix.m20;
		this.m21 = matrix.m21;
		this.m22 = matrix.m22;
	}

	public void setToZero()
	{
		this.m00 = this.m01 = this.m02 = 0;
		this.m10 = this.m11 = this.m12 = 0;
		this.m20 = this.m21 = this.m22 = 0;
	}

	public void setToIdentity()
	{
		this.m00 = this.m11 = this.m22 = 1;
		this.m10 = this.m20 = this.m21 = this.m12 = this.m01 = this.m02 = 0;
	}

	public void add(Matrix3d matrix)
	{
		this.m00 += matrix.m00;
		this.m01 += matrix.m01;
		this.m02 += matrix.m02;
		this.m10 += matrix.m10;
		this.m11 += matrix.m11;
		this.m12 += matrix.m12;
		this.m20 += matrix.m20;
		this.m21 += matrix.m21;
		this.m22 += matrix.m22;
	}

	public void add(double scalar)
	{
		this.m00 += scalar;
		this.m01 += scalar;
		this.m02 += scalar;
		this.m10 += scalar;
		this.m11 += scalar;
		this.m12 += scalar;
		this.m20 += scalar;
		this.m21 += scalar;
		this.m22 += scalar;
	}

	public void add(Matrix3d matrix, double scalar)
	{
		set(matrix);
		add(scalar);
	}

	public void add(Matrix3d matrix1, Matrix3d matrix2)
	{
		set(matrix1);
		add(matrix2);
	}

	public void subtract(Matrix3d matrix)
	{
		this.m00 -= matrix.m00;
		this.m01 -= matrix.m01;
		this.m02 -= matrix.m02;
		this.m10 -= matrix.m10;
		this.m11 -= matrix.m11;
		this.m12 -= matrix.m12;
		this.m20 -= matrix.m20;
		this.m21 -= matrix.m21;
		this.m22 -= matrix.m22;
	}

	public void subtract(Matrix3d matrix1, Matrix3d matrix2)
	{
		set(matrix1);
		subtract(matrix2);
	}

	public void subtract(double scalar)
	{
		add(-scalar);
	}

	public void subtract(Matrix3d matrix, double scalar)
	{
		set(matrix);
		add(-scalar);
	}

	public void scale(double scale)
	{
		this.m00 *= scale;
		this.m01 *= scale;
		this.m02 *= scale;
		this.m10 *= scale;
		this.m11 *= scale;
		this.m12 *= scale;
		this.m20 *= scale;
		this.m21 *= scale;
		this.m22 *= scale;
	}

	public void set(Matrix3d matrix)
	{
		this.m00 = matrix.m00;
		this.m01 = matrix.m01;
		this.m02 = matrix.m02;
		this.m10 = matrix.m10;
		this.m11 = matrix.m11;
		this.m12 = matrix.m12;
		this.m20 = matrix.m20;
		this.m21 = matrix.m21;
		this.m22 = matrix.m22;
	}

	public void set(double[] doubleArray)
	{
		if (doubleArray.length != 9)
		{
			throw new RuntimeException(
					"Double array must contain exactly 8 elements.");
		}

		this.m00 = doubleArray[0];
		this.m01 = doubleArray[1];
		this.m02 = doubleArray[2];
		this.m10 = doubleArray[3];
		this.m11 = doubleArray[4];
		this.m12 = doubleArray[5];
		this.m20 = doubleArray[6];
		this.m21 = doubleArray[7];
		this.m22 = doubleArray[8];
	}

	public double get(int row, int col)
	{
		switch (row)
		{
			case (0):
			{
				switch (col)
				{
					case (0):
					{
						return this.m00;
					}
					case (1):
					{
						return this.m01;
					}
					case (2):
					{
						return this.m02;
					}
					default:
					{
						break;
					}
				}
				break;
			}
			case (1):
			{
				switch (col)
				{
					case (0):
					{
						return this.m10;
					}
					case (1):
					{
						return this.m11;
					}
					case (2):
					{
						return this.m12;
					}
					default:
					{
						break;
					}
				}
				break;
			}
			case (2):
			{
				switch (col)
				{
					case (0):
					{
						return this.m20;
					}
					case (1):
					{
						return this.m21;
					}
					case (2):
					{
						return this.m22;
					}
					default:
					{
						break;
					}
				}
				break;
			}
		}
		throw new RuntimeException("Index out of bounds.");
	}

	public void set(int row, int col, double value)
	{
		switch (row)
		{
			case 0:
				switch (col)
				{
					case 0:
					{
						this.m00 = value;
						break;
					}
					case 1:
					{
						this.m01 = value;
						break;
					}
					case 2:
					{
						this.m02 = value;
						break;
					}
					default:
					{
						throw new RuntimeException("Index out of bounds.");
					}
				}
				break;
			case 1:
				switch (col)
				{
					case 0:
					{
						this.m10 = value;
						break;
					}
					case 1:
					{
						this.m11 = value;
						break;
					}
					case 2:
					{
						this.m12 = value;
						break;
					}
					default:
					{
						throw new RuntimeException("Index out of bounds.");
					}
				}
				break;
			case 2:
				switch (col)
				{
					case 0:
					{
						this.m20 = value;
						break;
					}
					case 1:
					{
						this.m21 = value;
						break;
					}
					case 2:
					{
						this.m22 = value;
						break;
					}
					default:
					{
						throw new RuntimeException("Index out of bounds.");
					}
				}
				break;
			default:
			{
				throw new RuntimeException("Index out of bounds.");
			}
		}
	}

	public void multiply(Matrix3d matrix)
	{
		double tmp00, tmp01, tmp02, tmp10, tmp11, tmp12, tmp20, tmp21, tmp22;

		tmp00 = this.m00 * matrix.m00 + this.m01 * matrix.m10 + this.m02
				* matrix.m20;
		tmp01 = this.m00 * matrix.m01 + this.m01 * matrix.m11 + this.m02
				* matrix.m21;
		tmp02 = this.m00 * matrix.m02 + this.m01 * matrix.m12 + this.m02
				* matrix.m22;

		tmp10 = this.m10 * matrix.m00 + this.m11 * matrix.m10 + this.m12
				* matrix.m20;
		tmp11 = this.m10 * matrix.m01 + this.m11 * matrix.m11 + this.m12
				* matrix.m21;
		tmp12 = this.m10 * matrix.m02 + this.m11 * matrix.m12 + this.m12
				* matrix.m22;

		tmp20 = this.m20 * matrix.m00 + this.m21 * matrix.m10 + this.m22
				* matrix.m20;
		tmp21 = this.m20 * matrix.m01 + this.m21 * matrix.m11 + this.m22
				* matrix.m21;
		tmp22 = this.m20 * matrix.m02 + this.m21 * matrix.m12 + this.m22
				* matrix.m22;

		this.m00 = tmp00;
		this.m01 = tmp01;
		this.m02 = tmp02;
		this.m10 = tmp10;
		this.m11 = tmp11;
		this.m12 = tmp12;
		this.m20 = tmp20;
		this.m21 = tmp21;
		this.m22 = tmp22;
	}

	public void multiply(Matrix3d matrix1, Matrix3d matrix2)
	{
		if (this != matrix1 && this != matrix2)
		{
			set(matrix1);
			multiply(matrix2);
		}
		else
		{
			double tmp00, tmp01, tmp02, tmp10, tmp11, tmp12, tmp20, tmp21, tmp22;

			tmp00 = matrix1.m00 * matrix2.m00 + matrix1.m01 * matrix2.m10
					+ matrix1.m02 * matrix2.m20;
			tmp01 = matrix1.m00 * matrix2.m01 + matrix1.m01 * matrix2.m11
					+ matrix1.m02 * matrix2.m21;
			tmp02 = matrix1.m00 * matrix2.m02 + matrix1.m01 * matrix2.m12
					+ matrix1.m02 * matrix2.m22;

			tmp10 = matrix1.m10 * matrix2.m00 + matrix1.m11 * matrix2.m10
					+ matrix1.m12 * matrix2.m20;
			tmp11 = matrix1.m10 * matrix2.m01 + matrix1.m11 * matrix2.m11
					+ matrix1.m12 * matrix2.m21;
			tmp12 = matrix1.m10 * matrix2.m02 + matrix1.m11 * matrix2.m12
					+ matrix1.m12 * matrix2.m22;

			tmp20 = matrix1.m20 * matrix2.m00 + matrix1.m21 * matrix2.m10
					+ matrix1.m22 * matrix2.m20;
			tmp21 = matrix1.m20 * matrix2.m01 + matrix1.m21 * matrix2.m11
					+ matrix1.m22 * matrix2.m21;
			tmp22 = matrix1.m20 * matrix2.m02 + matrix1.m21 * matrix2.m12
					+ matrix1.m22 * matrix2.m22;

			this.m00 = tmp00;
			this.m01 = tmp01;
			this.m02 = tmp02;
			this.m10 = tmp10;
			this.m11 = tmp11;
			this.m12 = tmp12;
			this.m20 = tmp20;
			this.m21 = tmp21;
			this.m22 = tmp22;
		}
	}

	public void transpose()
	{
		double tmp01 = this.m01;
		double tmp02 = this.m02;
		double tmp12 = this.m12;

		this.m01 = this.m10;
		this.m02 = this.m20;
		this.m12 = this.m21;
		this.m10 = tmp01;
		this.m20 = tmp02;
		this.m21 = tmp12;
	}

	public void transpose(Matrix3d matrix)
	{
		if (matrix == this)
		{
			transpose();
		}
		else
		{
			set(matrix);
			transpose();
		}
	}

	public void getRow(int row, Vector3d vector)
	{
		switch(row)
		{
			case 1:
			{
				vector.x = this.m00;
				vector.y = this.m01;
				vector.z = this.m02;
				break;
			}
			case 2:
			{
				vector.x = this.m10;
				vector.y = this.m11;
				vector.z = this.m12;
				break;
			}
			case 3:
			{
				vector.x = this.m20;
				vector.y = this.m21;
				vector.z = this.m22;
				break;
			}
			default:
				throw new RuntimeException("The column must either be 1, 2, or 3.");
		}
	}

	public void getRow(int row, double[] vector)
	{
		if (row != 1 || row != 2 || row != 3)
		{
			throw new RuntimeException("The column must either be 1, 2, or 3.");
		}

		switch(row)
		{
			case 1:
			{
				vector[0] = this.m00;
				vector[1] = this.m01;
				vector[2] = this.m02;
				break;
			}
			case 2:
			{
				vector[0] = this.m10;
				vector[1] = this.m11;
				vector[2] = this.m12;
				break;
			}
			case 3:
			{
				vector[0] = this.m20;
				vector[1] = this.m21;
				vector[2] = this.m22;
				break;
			}
			default:
				throw new RuntimeException("The column must either be 1, 2, or 3.");
		}
	}

	public void getColumn(int column, Vector3d vector)
	{
		switch(column)
		{
			case 1:
			{
				vector.x = this.m00;
				vector.y = this.m10;
				vector.z = this.m20;
				break;
			}
			case 2:
			{
				vector.x = this.m01;
				vector.y = this.m11;
				vector.z = this.m21;
				break;
			}
			case 3:
			{
				vector.x = this.m02;
				vector.y = this.m12;
				vector.z = this.m22;
				break;
			}
			default:
				throw new RuntimeException("The column must either be 1, 2, or 3.");
		}
	}

	public void getColumn(int column, double[] vector)
	{
		switch(column)
			{
				case 1:
				{
					vector[0] = this.m00;
					vector[1] = this.m10;
					vector[2] = this.m20;
					break;
				}
				case 2:
				{
					vector[0] = this.m01;
					vector[1] = this.m11;
					vector[2] = this.m21;
					break;
				}
				case 3:
				{
					vector[0] = this.m02;
					vector[1] = this.m12;
					vector[2] = this.m22;
					break;
				}
				default:
					throw new RuntimeException("The column must either be 1, 2, or 3.");
			}
	}

	public void setRow(int row, Vector3d vector)
	{
		switch(row)
		{
			case 1:
			{
				this.m00 = vector.x;
				this.m01 = vector.y;
				this.m02 = vector.z;
				break;
			}
			case 2:
			{
				this.m10 = vector.x;
				this.m11 = vector.y;
				this.m12 = vector.z;
				break;
			}
			case 3:
			{
				this.m20 = vector.x;
				this.m21 = vector.y;
				this.m22 = vector.z;
				break;
			}
			default:
				throw new RuntimeException("The column must either be 1, 2, or 3.");
		}
	}

	public void setRow(int row, double[] vector)
	{
		switch(row)
		{
			case 1:
			{
				this.m00 = vector[0];
				this.m01 = vector[1];
				this.m02 = vector[2];
				break;
			}
			case 2:
			{
				this.m10 = vector[0];
				this.m11 = vector[1];
				this.m12 = vector[2];
				break;
			}
			case 3:
			{
				this.m20 = vector[0];
				this.m21 = vector[1];
				this.m22 = vector[2];
				break;
			}
			default:
				throw new RuntimeException("The column must either be 1, 2, or 3.");
		}
	}

	public void setColumn(int column, Vector3d vector)
	{
		switch(column)
		{
			case 1:
			{
				this.m00 = vector.x;
				this.m10 = vector.y;
				this.m20 = vector.z;
				break;
			}
			case 2:
			{
				this.m01 = vector.x;
				this.m11 = vector.y;
				this.m21 = vector.z;
				break;
			}
			case 3:
			{
				this.m02 = vector.x;
				this.m12 = vector.y;
				this.m22 = vector.z;
				break;
			}
			default:
				throw new RuntimeException("The column must either be 1, 2, or 3.");
		}
	}

	public void setColumn(int column, double[] vector)
	{
		switch(column)
		{
			case 1:
			{
				this.m00 = vector[0];
				this.m10 = vector[1];
				this.m20 = vector[2];
				break;
			}
			case 2:
			{
				this.m01 = vector[0];
				this.m11 = vector[1];
				this.m21 = vector[2];
				break;
			}
			case 3:
			{
				this.m02 = vector[0];
				this.m12 = vector[1];
				this.m22 = vector[2];
				break;
			}
			default:
				throw new RuntimeException("The column must either be 1, 2, or 3.");
		}
	}

	public void invert()
	{
		invert(this);
	}

	private final void invert(Matrix3d matrix)
	{
		// Use LU decomposition and backsubstitution code specifically
		// for floating-point 3x3 matrices.

		tmp9Array2[0] = matrix.m00;
		tmp9Array2[1] = matrix.m01;
		tmp9Array2[2] = matrix.m02;

		tmp9Array2[3] = matrix.m10;
		tmp9Array2[4] = matrix.m11;
		tmp9Array2[5] = matrix.m12;

		tmp9Array2[6] = matrix.m20;
		tmp9Array2[7] = matrix.m21;
		tmp9Array2[8] = matrix.m22;

		// Calculate LU decomposition: Is the matrix singular?
		if (!luDecomposition(tmp9Array2, tmp3Array))
		{
			throw new RuntimeException("Matrix is singular.");
		}

		// Perform back substitution on the identity matrix
		for (int i = 0; i < 9; i++)
		{
			tmp9Array[i] = 0.0;
		}

		tmp9Array[0] = 1.0;
		tmp9Array[4] = 1.0;
		tmp9Array[8] = 1.0;
		luBacksubstitution(tmp9Array2, tmp3Array, tmp9Array);

		this.m00 = tmp9Array[0];
		this.m01 = tmp9Array[1];
		this.m02 = tmp9Array[2];

		this.m10 = tmp9Array[3];
		this.m11 = tmp9Array[4];
		this.m12 = tmp9Array[5];

		this.m20 = tmp9Array[6];
		this.m21 = tmp9Array[7];
		this.m22 = tmp9Array[8];

	}

	//
	// Reference: Press, Flannery, Teukolsky, Vetterling,
	// _Numerical_Recipes_in_C_, Cambridge University Press,
	// 1988, pp 40-45.
	//
	private boolean luDecomposition(double[] matrix0, int[] row_perm)
	{
		int i, j;
		int ptr, rs;
		double big, temp;

		ptr = 0;
		rs = 0;

		// For each row ...
		i = 3;
		while (i-- != 0)
		{
			big = 0.0;

			j = 3;
			while (j-- != 0)
			{
				temp = matrix0[ptr++];
				temp = Math.abs(temp);
				if (temp > big)
				{
					big = temp;
				}
			}

			// Is the matrix singular?
			if (big == 0.0)
			{
				return false;
			}
			tmp3Array2[rs++] = 1.0 / big;
		}

		int mtx;

		mtx = 0;

		// For all columns, execute Crout's method
		for (j = 0; j < 3; j++)
		{
			int imax, k;
			int target, p1, p2;
			double sum;

			// Determine elements of upper diagonal matrix U
			for (i = 0; i < j; i++)
			{
				target = mtx + (3 * i) + j;
				sum = matrix0[target];
				k = i;
				p1 = mtx + (3 * i);
				p2 = mtx + j;
				while (k-- != 0)
				{
					sum -= matrix0[p1] * matrix0[p2];
					p1++;
					p2 += 3;
				}
				matrix0[target] = sum;
			}

			// Search for largest pivot element and calculate
			// intermediate elements of lower diagonal matrix L.
			big = 0.0;
			imax = -1;
			for (i = j; i < 3; i++)
			{
				target = mtx + (3 * i) + j;
				sum = matrix0[target];
				k = j;
				p1 = mtx + (3 * i);
				p2 = mtx + j;
				while (k-- != 0)
				{
					sum -= matrix0[p1] * matrix0[p2];
					p1++;
					p2 += 3;
				}
				matrix0[target] = sum;

				// Is this the best pivot so far?
				if ((temp = tmp3Array2[i] * Math.abs(sum)) >= big)
				{
					big = temp;
					imax = i;
				}
			}

			if (imax < 0)
			{
				throw new RuntimeException(
						"Figure out what this exception should be.");
				// throw new
				// RuntimeException(VecMathI18N.getString("Matrix3d13"));
			}

			// Is a row exchange necessary?
			if (j != imax)
			{
				// Yes: exchange rows
				k = 3;
				p1 = mtx + (3 * imax);
				p2 = mtx + (3 * j);
				while (k-- != 0)
				{
					temp = matrix0[p1];
					matrix0[p1++] = matrix0[p2];
					matrix0[p2++] = temp;
				}

				// Record change in scale factor
				tmp3Array2[imax] = tmp3Array2[j];
			}

			// Record row permutation
			row_perm[j] = imax;

			// Is the matrix singular
			if (matrix0[(mtx + (3 * j) + j)] == 0.0)
			{
				return false;
			}

			// Divide elements of lower diagonal matrix L by pivot
			if (j != (3 - 1))
			{
				temp = 1.0 / (matrix0[(mtx + (3 * j) + j)]);
				target = mtx + (3 * (j + 1)) + j;
				i = 2 - j;
				while (i-- != 0)
				{
					matrix0[target] *= temp;
					target += 3;
				}
			}
		}

		return true;
	}

	//
	// Reference: Press, Flannery, Teukolsky, Vetterling,
	// _Numerical_Recipes_in_C_, Cambridge University Press,
	// 1988, pp 44-45.
	//
	private void luBacksubstitution(double[] matrix1, int[] row_perm,
			double[] matrix2)
	{

		int i, ii, ip, j, k;
		int rp;
		int cv, rv;

		rp = 0;

		for (k = 0; k < 3; k++)
		{
			cv = k;
			ii = -1;

			for (i = 0; i < 3; i++)
			{
				double sum;

				ip = row_perm[rp + i];
				sum = matrix2[cv + 3 * ip];
				matrix2[cv + 3 * ip] = matrix2[cv + 3 * i];
				if (ii >= 0)
				{
					rv = i * 3;
					for (j = ii; j <= i - 1; j++)
					{
						sum -= matrix1[rv + j] * matrix2[cv + 3 * j];
					}
				}
				else if (sum != 0.0)
				{
					ii = i;
				}
				matrix2[cv + 3 * i] = sum;
			}
			rv = 2 * 3;
			matrix2[cv + 3 * 2] /= matrix1[rv + 2];

			rv -= 3;
			matrix2[cv + 3 * 1] = (matrix2[cv + 3 * 1] - matrix1[rv + 2]
					* matrix2[cv + 3 * 2])
					/ matrix1[rv + 1];

			rv -= 3;
			matrix2[cv + 4 * 0] = (matrix2[cv + 3 * 0] - matrix1[rv + 1]
					* matrix2[cv + 3 * 1] - matrix1[rv + 2]
					* matrix2[cv + 3 * 2])
					/ matrix1[rv + 0];

		}
	}

	public boolean equals(Matrix3d matrix)
	{
		return epsilonEquals(matrix, EPS);
	}

	public boolean epsilonEquals(Matrix3d matrix, double epsilon)
	{
		double diff;

		diff = this.m00 - matrix.m00;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		diff = this.m01 - matrix.m01;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		diff = this.m02 - matrix.m02;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		diff = this.m10 - matrix.m10;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		diff = this.m11 - matrix.m11;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		diff = this.m12 - matrix.m12;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		diff = this.m20 - matrix.m20;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		diff = this.m21 - matrix.m21;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		diff = this.m22 - matrix.m22;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		return true;
	}

	public String toString()
	{
		return "[" + this.m00 + "," + this.m01 + "," + this.m02 + "\n"
				+ this.m10 + "," + this.m11 + "," + this.m12 + "\n" + this.m20
				+ "," + this.m21 + "," + this.m22 + "]";
	}
}
