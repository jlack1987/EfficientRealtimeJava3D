public class Matrix4d implements java.io.Serializable
{
	private static final long serialVersionUID = -1737981318054196218L;

	double m00, m01, m02, m03;
	double m10, m11, m12, m13;
	double m20, m21, m22, m23;
	double m30, m31, m32, m33;

	public Matrix4d()
	{
		setToZero();
	}

	public Matrix4d(Matrix4d matrix)
	{
		set(matrix);
	}

	public Matrix4d(Matrix4f matrix)
	{
		set(matrix);
	}

	public Matrix4d(double[] array)
	{
		set(array);
	}

	public Matrix4d(float[] array)
	{
		set(array);
	}

	public void set(Matrix4d matrix)
	{
		this.m00 = matrix.m00;
		this.m01 = matrix.m01;
		this.m02 = matrix.m02;
		this.m03 = matrix.m03;
		this.m10 = matrix.m10;
		this.m11 = matrix.m11;
		this.m12 = matrix.m12;
		this.m13 = matrix.m13;
		this.m20 = matrix.m20;
		this.m21 = matrix.m21;
		this.m22 = matrix.m22;
		this.m23 = matrix.m23;
		this.m30 = matrix.m30;
		this.m31 = matrix.m31;
		this.m32 = matrix.m32;
		this.m33 = matrix.m33;
	}

	public void set(Matrix4f matrix)
	{
		this.m00 = matrix.m00;
		this.m01 = matrix.m01;
		this.m02 = matrix.m02;
		this.m03 = matrix.m03;
		this.m10 = matrix.m10;
		this.m11 = matrix.m11;
		this.m12 = matrix.m12;
		this.m13 = matrix.m13;
		this.m20 = matrix.m20;
		this.m21 = matrix.m21;
		this.m22 = matrix.m22;
		this.m23 = matrix.m23;
		this.m30 = matrix.m30;
		this.m31 = matrix.m31;
		this.m32 = matrix.m32;
		this.m33 = matrix.m33;
	}

	public void set(double[] array)
	{
		this.m00 = array[0];
		this.m01 = array[1];
		this.m02 = array[2];
		this.m03 = array[3];
		this.m10 = array[4];
		this.m11 = array[5];
		this.m12 = array[6];
		this.m13 = array[7];
		this.m20 = array[8];
		this.m21 = array[9];
		this.m22 = array[10];
		this.m23 = array[11];
		this.m30 = array[12];
		this.m31 = array[13];
		this.m32 = array[14];
		this.m33 = array[15];
	}

	public void set(float[] array)
	{
		this.m00 = array[0];
		this.m01 = array[1];
		this.m02 = array[2];
		this.m03 = array[3];
		this.m10 = array[4];
		this.m11 = array[5];
		this.m12 = array[6];
		this.m13 = array[7];
		this.m20 = array[8];
		this.m21 = array[9];
		this.m22 = array[10];
		this.m23 = array[11];
		this.m30 = array[12];
		this.m31 = array[13];
		this.m32 = array[14];
		this.m33 = array[15];
	}

	public Matrix4d(double m00, double m01, double m02, double m03, double m10,
			double m11, double m12, double m13, double m20, double m21,
			double m22, double m23, double m30, double m31, double m32,
			double m33)
	{
		set(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30,
				m31, m32, m33);
	}

	public void set(double m00, double m01, double m02, double m03, double m10,
			double m11, double m12, double m13, double m20, double m21,
			double m22, double m23, double m30, double m31, double m32,
			double m33)
	{
		this.m00 = m00;
		this.m01 = m01;
		this.m02 = m02;
		this.m03 = m03;
		this.m10 = m10;
		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m20 = m20;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m30 = m30;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;
	}

	public void setIdentity()
	{
		this.m00 = 1;
		this.m01 = 0;
		this.m02 = 0;
		this.m03 = 0;
		this.m10 = 0;
		this.m11 = 1;
		this.m12 = 0;
		this.m13 = 0;
		this.m20 = 0;
		this.m21 = 0;
		this.m22 = 1;
		this.m23 = 0;
		this.m30 = 0;
		this.m31 = 0;
		this.m32 = 0;
		this.m33 = 1;
	}

	public void setToZero()
	{
		this.m00 = 0;
		this.m01 = 0;
		this.m02 = 0;
		this.m03 = 0;
		this.m10 = 0;
		this.m11 = 0;
		this.m12 = 0;
		this.m13 = 0;
		this.m20 = 0;
		this.m21 = 0;
		this.m22 = 0;
		this.m23 = 0;
		this.m30 = 0;
		this.m31 = 0;
		this.m32 = 0;
		this.m33 = 0;
	}

	public void set(int row, int column, double value)
	{
		switch (row)
		{
			case 0:
				switch (column)
				{
					case 0:
						this.m00 = value;
						break;
					case 1:
						this.m01 = value;
						break;
					case 2:
						this.m02 = value;
						break;
					case 3:
						this.m03 = value;
						break;
					default:
						throw new RuntimeException("Index out of bounds.");
				}
				break;

			case 1:
				switch (column)
				{
					case 0:
						this.m10 = value;
						break;
					case 1:
						this.m11 = value;
						break;
					case 2:
						this.m12 = value;
						break;
					case 3:
						this.m13 = value;
						break;
					default:
						throw new RuntimeException("Index out of bounds.");
				}
				break;

			case 2:
				switch (column)
				{
					case 0:
						this.m20 = value;
						break;
					case 1:
						this.m21 = value;
						break;
					case 2:
						this.m22 = value;
						break;
					case 3:
						this.m23 = value;
						break;
					default:
						throw new RuntimeException("Index out of bounds.");
				}
				break;

			case 3:
				switch (column)
				{
					case 0:
						this.m30 = value;
						break;
					case 1:
						this.m31 = value;
						break;
					case 2:
						this.m32 = value;
						break;
					case 3:
						this.m33 = value;
						break;
					default:
						throw new RuntimeException("Index out of bounds.");
				}
				break;

			default:
				throw new RuntimeException("Index out of bounds.");
		}
	}

	public double getElement(int row, int column)
	{
		switch (row)
		{
			case 0:
				switch (column)
				{
					case 0:
						return (this.m00);
					case 1:
						return (this.m01);
					case 2:
						return (this.m02);
					case 3:
						return (this.m03);
					default:
						break;
				}
				break;
			case 1:
				switch (column)
				{
					case 0:
						return (this.m10);
					case 1:
						return (this.m11);
					case 2:
						return (this.m12);
					case 3:
						return (this.m13);
					default:
						break;
				}
				break;

			case 2:
				switch (column)
				{
					case 0:
						return (this.m20);
					case 1:
						return (this.m21);
					case 2:
						return (this.m22);
					case 3:
						return (this.m23);
					default:
						break;
				}
				break;

			case 3:
				switch (column)
				{
					case 0:
						return (this.m30);
					case 1:
						return (this.m31);
					case 2:
						return (this.m32);
					case 3:
						return (this.m33);
					default:
						break;
				}
				break;

			default:
				break;
		}
		throw new RuntimeException("Index out of bounds.");
	}

	public void getRow(int row, Vector4d vector)
	{
		if (row == 0)
		{
			vector.x = m00;
			vector.y = m01;
			vector.z = m02;
			vector.w = m03;
		}
		else if (row == 1)
		{
			vector.x = m10;
			vector.y = m11;
			vector.z = m12;
			vector.w = m13;
		}
		else if (row == 2)
		{
			vector.x = m20;
			vector.y = m21;
			vector.z = m22;
			vector.w = m23;
		}
		else if (row == 3)
		{
			vector.x = m30;
			vector.y = m31;
			vector.z = m32;
			vector.w = m33;
		}
		else
		{
			throw new RuntimeException("Index out of bounds.");
		}
	}

	public void getRow(int row, double vector[])
	{
		if (row == 0)
		{
			vector[0] = m00;
			vector[1] = m01;
			vector[2] = m02;
			vector[3] = m03;
		}
		else if (row == 1)
		{
			vector[0] = m10;
			vector[1] = m11;
			vector[2] = m12;
			vector[3] = m13;
		}
		else if (row == 2)
		{
			vector[0] = m20;
			vector[1] = m21;
			vector[2] = m22;
			vector[3] = m23;
		}
		else if (row == 3)
		{
			vector[0] = m30;
			vector[1] = m31;
			vector[2] = m32;
			vector[3] = m33;
		}
		else
		{

			throw new RuntimeException("Index out of bounds.");
		}
	}

	public void getColumn(int column, Vector4d vector)
	{
		if (column == 0)
		{
			vector.x = m00;
			vector.y = m10;
			vector.z = m20;
			vector.w = m30;
		}
		else if (column == 1)
		{
			vector.x = m01;
			vector.y = m11;
			vector.z = m21;
			vector.w = m31;
		}
		else if (column == 2)
		{
			vector.x = m02;
			vector.y = m12;
			vector.z = m22;
			vector.w = m32;
		}
		else if (column == 3)
		{
			vector.x = m03;
			vector.y = m13;
			vector.z = m23;
			vector.w = m33;
		}
		else
		{
			throw new RuntimeException("Index out of bounds.");
		}
	}

	public void getColumn(int column, double vector[])
	{
		if (column == 0)
		{
			vector[0] = m00;
			vector[1] = m10;
			vector[2] = m20;
			vector[3] = m30;
		}
		else if (column == 1)
		{
			vector[0] = m01;
			vector[1] = m11;
			vector[2] = m21;
			vector[3] = m31;
		}
		else if (column == 2)
		{
			vector[0] = m02;
			vector[1] = m12;
			vector[2] = m22;
			vector[3] = m32;
		}
		else if (column == 3)
		{
			vector[0] = m03;
			vector[1] = m13;
			vector[2] = m23;
			vector[3] = m33;
		}
		else
		{
			throw new RuntimeException("Index out of bounds.");

		}
	}

	public void setRow(int row, double x, double y, double z, double w)
	{
		switch (row)
		{
			case 0:
				this.m00 = x;
				this.m01 = y;
				this.m02 = z;
				this.m03 = w;
				break;

			case 1:
				this.m10 = x;
				this.m11 = y;
				this.m12 = z;
				this.m13 = w;
				break;

			case 2:
				this.m20 = x;
				this.m21 = y;
				this.m22 = z;
				this.m23 = w;
				break;

			case 3:
				this.m30 = x;
				this.m31 = y;
				this.m32 = z;
				this.m33 = w;
				break;

			default:
				throw new RuntimeException("Index out of bounds.");
		}
	}

	public void setRow(int row, Vector4d vector)
	{
		switch (row)
		{
			case 0:
				this.m00 = vector.x;
				this.m01 = vector.y;
				this.m02 = vector.z;
				this.m03 = vector.w;
				break;

			case 1:
				this.m10 = vector.x;
				this.m11 = vector.y;
				this.m12 = vector.z;
				this.m13 = vector.w;
				break;

			case 2:
				this.m20 = vector.x;
				this.m21 = vector.y;
				this.m22 = vector.z;
				this.m23 = vector.w;
				break;

			case 3:
				this.m30 = vector.x;
				this.m31 = vector.y;
				this.m32 = vector.z;
				this.m33 = vector.w;
				break;

			default:
				throw new RuntimeException("Index out of bounds.");
		}
	}

	public void setRow(int row, double vector[])
	{
		switch (row)
		{
			case 0:
				this.m00 = vector[0];
				this.m01 = vector[1];
				this.m02 = vector[2];
				this.m03 = vector[3];
				break;

			case 1:
				this.m10 = vector[0];
				this.m11 = vector[1];
				this.m12 = vector[2];
				this.m13 = vector[3];
				break;

			case 2:
				this.m20 = vector[0];
				this.m21 = vector[1];
				this.m22 = vector[2];
				this.m23 = vector[3];
				break;

			case 3:
				this.m30 = vector[0];
				this.m31 = vector[1];
				this.m32 = vector[2];
				this.m33 = vector[3];
				break;

			default:
				throw new RuntimeException("Index out of bounds.");
		}
	}

	public void setColumn(int column, double x, double y, double z, double w)
	{
		switch (column)
		{
			case 0:
				this.m00 = x;
				this.m10 = y;
				this.m20 = z;
				this.m30 = w;
				break;

			case 1:
				this.m01 = x;
				this.m11 = y;
				this.m21 = z;
				this.m31 = w;
				break;

			case 2:
				this.m02 = x;
				this.m12 = y;
				this.m22 = z;
				this.m32 = w;
				break;

			case 3:
				this.m03 = x;
				this.m13 = y;
				this.m23 = z;
				this.m33 = w;
				break;

			default:
				throw new RuntimeException("Index out of bounds.");
		}
	}

	public void setColumn(int column, Vector4d vector)
	{
		switch (column)
		{
			case 0:
				this.m00 = vector.x;
				this.m10 = vector.y;
				this.m20 = vector.z;
				this.m30 = vector.w;
				break;

			case 1:
				this.m01 = vector.x;
				this.m11 = vector.y;
				this.m21 = vector.z;
				this.m31 = vector.w;
				break;

			case 2:
				this.m02 = vector.x;
				this.m12 = vector.y;
				this.m22 = vector.z;
				this.m32 = vector.w;
				break;

			case 3:
				this.m03 = vector.x;
				this.m13 = vector.y;
				this.m23 = vector.z;
				this.m33 = vector.w;
				break;

			default:
				throw new RuntimeException("Index out of bounds.");
		}
	}

	public void setColumn(int column, double vector[])
	{
		switch (column)
		{
			case 0:
				this.m00 = vector[0];
				this.m10 = vector[1];
				this.m20 = vector[2];
				this.m30 = vector[3];
				break;

			case 1:
				this.m01 = vector[0];
				this.m11 = vector[1];
				this.m21 = vector[2];
				this.m31 = vector[3];
				break;

			case 2:
				this.m02 = vector[0];
				this.m12 = vector[1];
				this.m22 = vector[2];
				this.m32 = vector[3];
				break;

			case 3:
				this.m03 = vector[0];
				this.m13 = vector[1];
				this.m23 = vector[2];
				this.m33 = vector[3];
				break;

			default:
				throw new RuntimeException("Index out of bounds.");
		}
	}

	public void add(double scalar)
	{
		this.m00 += scalar;
		this.m01 += scalar;
		this.m02 += scalar;
		this.m03 += scalar;
		this.m10 += scalar;
		this.m11 += scalar;
		this.m12 += scalar;
		this.m13 += scalar;
		this.m20 += scalar;
		this.m21 += scalar;
		this.m22 += scalar;
		this.m23 += scalar;
		this.m30 += scalar;
		this.m31 += scalar;
		this.m32 += scalar;
		this.m33 += scalar;
	}

	public void add(Matrix4d matrix1, Matrix4d matrix2)
	{
		set(matrix1);
		add(matrix2);
	}

	public void add(Matrix4d matrix)
	{
		this.m00 += matrix.m00;
		this.m01 += matrix.m01;
		this.m02 += matrix.m02;
		this.m03 += matrix.m03;
		this.m10 += matrix.m10;
		this.m11 += matrix.m11;
		this.m12 += matrix.m12;
		this.m13 += matrix.m13;
		this.m20 += matrix.m20;
		this.m21 += matrix.m21;
		this.m22 += matrix.m22;
		this.m23 += matrix.m23;
		this.m30 += matrix.m30;
		this.m31 += matrix.m31;
		this.m32 += matrix.m32;
		this.m33 += matrix.m33;
	}

	public void subtract(Matrix4d matrix1, Matrix4d matrix2)
	{
		set(matrix1);
		subtract(matrix2);
	}

	public void subtract(Matrix4d matrix)
	{
		this.m00 -= matrix.m00;
		this.m01 -= matrix.m01;
		this.m02 -= matrix.m02;
		this.m03 -= matrix.m03;
		this.m10 -= matrix.m10;
		this.m11 -= matrix.m11;
		this.m12 -= matrix.m12;
		this.m13 -= matrix.m13;
		this.m20 -= matrix.m20;
		this.m21 -= matrix.m21;
		this.m22 -= matrix.m22;
		this.m23 -= matrix.m23;
		this.m30 -= matrix.m30;
		this.m31 -= matrix.m31;
		this.m32 -= matrix.m32;
		this.m33 -= matrix.m33;
	}

	public void transpose()
	{
		double temp;

		temp = this.m10;
		this.m10 = this.m01;
		this.m01 = temp;

		temp = this.m20;
		this.m20 = this.m02;
		this.m02 = temp;

		temp = this.m30;
		this.m30 = this.m03;
		this.m03 = temp;

		temp = this.m21;
		this.m21 = this.m12;
		this.m12 = temp;

		temp = this.m31;
		this.m31 = this.m13;
		this.m13 = temp;

		temp = this.m32;
		this.m32 = this.m23;
		this.m23 = temp;
	}
	
	public void transpose(Matrix4d matrix)
	{
		if(this != matrix)
		{
			set(matrix);
			transpose();
		}
		else
		{
			transpose();
		}
	}

	public String toString()
	{
		return this.m00 + ", " + this.m01 + ", " + this.m02 + ", " + this.m03
				+ "\n" + this.m10 + ", " + this.m11 + ", " + this.m12 + ", "
				+ this.m13 + "\n" + this.m20 + ", " + this.m21 + ", "
				+ this.m22 + ", " + this.m23 + "\n" + this.m30 + ", "
				+ this.m31 + ", " + this.m32 + ", " + this.m33 + "\n";
	}
}
