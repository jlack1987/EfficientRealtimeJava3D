public class Matrix4f implements java.io.Serializable
{
	private static final long serialVersionUID = -1732879764184827457L;
	double row_scale[] = new double[4];
	double result[] = new double[16];
	int row_perm[] = new int[4];

	double[] tmp = new double[16];

	float m00, m01, m02, m03;
	float m10, m11, m12, m13;
	float m20, m21, m22, m23;
	float m30, m31, m32, m33;

	public Matrix4f()
	{
		setToZero();
	}

	public Matrix4f(Matrix4f matrix)
	{
		set(matrix);
	}

	public Matrix4f(Matrix4d matrix)
	{
		set(matrix);
	}

	public Matrix4f(double[] array)
	{
		set(array);
	}

	public Matrix4f(float[] array)
	{
		set(array);
	}

	public void set(Matrix4d matrix)
	{
		this.m00 = (float)matrix.m00;
		this.m01 = (float)matrix.m01;
		this.m02 = (float)matrix.m02;
		this.m03 = (float)matrix.m03;
		this.m10 = (float)matrix.m10;
		this.m11 = (float)matrix.m11;
		this.m12 = (float)matrix.m12;
		this.m13 = (float)matrix.m13;
		this.m20 = (float)matrix.m20;
		this.m21 = (float)matrix.m21;
		this.m22 = (float)matrix.m22;
		this.m23 = (float)matrix.m23;
		this.m30 = (float)matrix.m30;
		this.m31 = (float)matrix.m31;
		this.m32 = (float)matrix.m32;
		this.m33 = (float)matrix.m33;
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
		this.m00 = (float)array[0];
		this.m01 = (float)array[1];
		this.m02 = (float)array[2];
		this.m03 = (float)array[3];
		this.m10 = (float)array[4];
		this.m11 = (float)array[5];
		this.m12 = (float)array[6];
		this.m13 = (float)array[7];
		this.m20 = (float)array[8];
		this.m21 = (float)array[9];
		this.m22 = (float)array[10];
		this.m23 = (float)array[11];
		this.m30 = (float)array[12];
		this.m31 = (float)array[13];
		this.m32 = (float)array[14];
		this.m33 = (float)array[15];
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

	public Matrix4f(float m00, float m01, float m02, float m03, float m10,
			float m11, float m12, float m13, float m20, float m21,
			float m22, float m23, float m30, float m31, float m32,
			float m33)
	{
		set(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30,
				m31, m32, m33);
	}

	public void set(float m00, float m01, float m02, float m03, float m10,
			float m11, float m12, float m13, float m20, float m21,
			float m22, float m23, float m30, float m31, float m32,
			float m33)
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

	public void set(int row, int column, float value)
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

	public void get(Matrix4d matrix)
	{
		matrix.m00 = this.m00;
		matrix.m01 = this.m01;
		matrix.m02 = this.m02;
		matrix.m03 = this.m03;
		matrix.m10 = this.m10;
		matrix.m11 = this.m11;
		matrix.m12 = this.m12;
		matrix.m13 = this.m13;
		matrix.m20 = this.m20;
		matrix.m21 = this.m21;
		matrix.m22 = this.m22;
		matrix.m23 = this.m23;
		matrix.m30 = this.m30;
		matrix.m31 = this.m31;
		matrix.m32 = this.m32;
		matrix.m33 = this.m33;
	}

	public void get(Matrix4f matrix)
	{
		matrix.m00 = (float) this.m00;
		matrix.m01 = (float) this.m01;
		matrix.m02 = (float) this.m02;
		matrix.m03 = (float) this.m03;
		matrix.m10 = (float) this.m10;
		matrix.m11 = (float) this.m11;
		matrix.m12 = (float) this.m12;
		matrix.m13 = (float) this.m13;
		matrix.m20 = (float) this.m20;
		matrix.m21 = (float) this.m21;
		matrix.m22 = (float) this.m22;
		matrix.m23 = (float) this.m23;
		matrix.m30 = (float) this.m30;
		matrix.m31 = (float) this.m31;
		matrix.m32 = (float) this.m32;
		matrix.m33 = (float) this.m33;
	}

	public void get(double[] array)
	{
		array[0] = this.m00;
		array[1] = this.m01;
		array[2] = this.m02;
		array[3] = this.m03;
		array[4] = this.m10;
		array[5] = this.m11;
		array[6] = this.m12;
		array[7] = this.m13;
		array[8] = this.m20;
		array[9] = this.m21;
		array[10] = this.m22;
		array[11] = this.m23;
		array[12] = this.m30;
		array[13] = this.m31;
		array[14] = this.m32;
		array[15] = this.m33;
	}

	public void get(float[] array)
	{
		array[0] = this.m00;
		array[1] = this.m01;
		array[2] = this.m02;
		array[3] = this.m03;
		array[4] = this.m10;
		array[5] = this.m11;
		array[6] = this.m12;
		array[7] = this.m13;
		array[8] = this.m20;
		array[9] = this.m21;
		array[10] = this.m22;
		array[11] = this.m23;
		array[12] = this.m30;
		array[13] = this.m31;
		array[14] = this.m32;
		array[15] = this.m33;
	}

	public float get(int row, int column)
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

	public void getRow(int row, Vector4f vector)
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

	public void getRow(int row, float vector[])
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

	public void getColumn(int column, Vector4f vector)
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

	public void getColumn(int column, float vector[])
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

	public void setRow(int row, float x, float y, float z, float w)
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

	public void setRow(int row, Vector4f vector)
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

	public void setRow(int row, float vector[])
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

	public void setColumn(int column, float x, float y, float z, float w)
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

	public void setColumn(int column, Vector4f vector)
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

	public void setColumn(int column, float vector[])
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

	public void add(float scalar)
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

	public void add(Matrix4f matrix1, Matrix4f matrix2)
	{
		set(matrix1);
		add(matrix2);
	}

	public void add(Matrix4f matrix)
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

	public void subtract(Matrix4f matrix1, Matrix4f matrix2)
	{
		set(matrix1);
		subtract(matrix2);
	}

	public void subtract(float scalar)
	{
		add(-scalar);
	}

	public void subtract(Matrix4f matrix)
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
		float temp;

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

	public void transpose(Matrix4f matrix)
	{
		if (this != matrix)
		{
			set(matrix);
			transpose();
		}
		else
		{
			transpose();
		}
	}

	public void multiply(float scalar)
	{
		this.m00 *= scalar;
		this.m01 *= scalar;
		this.m02 *= scalar;
		this.m03 *= scalar;
		this.m10 *= scalar;
		this.m11 *= scalar;
		this.m12 *= scalar;
		this.m13 *= scalar;
		this.m20 *= scalar;
		this.m21 *= scalar;
		this.m22 *= scalar;
		this.m23 *= scalar;
		this.m30 *= scalar;
		this.m31 *= scalar;
		this.m32 *= scalar;
		this.m33 *= scalar;
	}

	public void multiply(Matrix4f matrix, float scalar)
	{
		set(matrix);
		multiply(scalar);
	}

	public final void multiply(Matrix4f matrix)
	{
		float tmp00, tmp01, tmp02, tmp03, tmp10, tmp11, tmp12, tmp13;
		float tmp20, tmp21, tmp22, tmp23, tmp30, tmp31, tmp32, tmp33;

		tmp00 = this.m00 * matrix.m00 + this.m01 * matrix.m10 + this.m02
				* matrix.m20 + this.m03 * matrix.m30;
		tmp01 = this.m00 * matrix.m01 + this.m01 * matrix.m11 + this.m02
				* matrix.m21 + this.m03 * matrix.m31;
		tmp02 = this.m00 * matrix.m02 + this.m01 * matrix.m12 + this.m02
				* matrix.m22 + this.m03 * matrix.m32;
		tmp03 = this.m00 * matrix.m03 + this.m01 * matrix.m13 + this.m02
				* matrix.m23 + this.m03 * matrix.m33;

		tmp10 = this.m10 * matrix.m00 + this.m11 * matrix.m10 + this.m12
				* matrix.m20 + this.m13 * matrix.m30;
		tmp11 = this.m10 * matrix.m01 + this.m11 * matrix.m11 + this.m12
				* matrix.m21 + this.m13 * matrix.m31;
		tmp12 = this.m10 * matrix.m02 + this.m11 * matrix.m12 + this.m12
				* matrix.m22 + this.m13 * matrix.m32;
		tmp13 = this.m10 * matrix.m03 + this.m11 * matrix.m13 + this.m12
				* matrix.m23 + this.m13 * matrix.m33;

		tmp20 = this.m20 * matrix.m00 + this.m21 * matrix.m10 + this.m22
				* matrix.m20 + this.m23 * matrix.m30;
		tmp21 = this.m20 * matrix.m01 + this.m21 * matrix.m11 + this.m22
				* matrix.m21 + this.m23 * matrix.m31;
		tmp22 = this.m20 * matrix.m02 + this.m21 * matrix.m12 + this.m22
				* matrix.m22 + this.m23 * matrix.m32;
		tmp23 = this.m20 * matrix.m03 + this.m21 * matrix.m13 + this.m22
				* matrix.m23 + this.m23 * matrix.m33;

		tmp30 = this.m30 * matrix.m00 + this.m31 * matrix.m10 + this.m32
				* matrix.m20 + this.m33 * matrix.m30;
		tmp31 = this.m30 * matrix.m01 + this.m31 * matrix.m11 + this.m32
				* matrix.m21 + this.m33 * matrix.m31;
		tmp32 = this.m30 * matrix.m02 + this.m31 * matrix.m12 + this.m32
				* matrix.m22 + this.m33 * matrix.m32;
		tmp33 = this.m30 * matrix.m03 + this.m31 * matrix.m13 + this.m32
				* matrix.m23 + this.m33 * matrix.m33;

		this.m00 = tmp00;
		this.m01 = tmp01;
		this.m02 = tmp02;
		this.m03 = tmp03;
		this.m10 = tmp10;
		this.m11 = tmp11;
		this.m12 = tmp12;
		this.m13 = tmp13;
		this.m20 = tmp20;
		this.m21 = tmp21;
		this.m22 = tmp22;
		this.m23 = tmp23;
		this.m30 = tmp30;
		this.m31 = tmp31;
		this.m32 = tmp32;
		this.m33 = tmp33;
	}

	public final void multiply(Matrix4f m1, Matrix4f m2)
	{
		if (this != m1 && this != m2)
		{
			this.m00 = m1.m00 * m2.m00 + m1.m01 * m2.m10 + m1.m02 * m2.m20
					+ m1.m03 * m2.m30;
			this.m01 = m1.m00 * m2.m01 + m1.m01 * m2.m11 + m1.m02 * m2.m21
					+ m1.m03 * m2.m31;
			this.m02 = m1.m00 * m2.m02 + m1.m01 * m2.m12 + m1.m02 * m2.m22
					+ m1.m03 * m2.m32;
			this.m03 = m1.m00 * m2.m03 + m1.m01 * m2.m13 + m1.m02 * m2.m23
					+ m1.m03 * m2.m33;

			this.m10 = m1.m10 * m2.m00 + m1.m11 * m2.m10 + m1.m12 * m2.m20
					+ m1.m13 * m2.m30;
			this.m11 = m1.m10 * m2.m01 + m1.m11 * m2.m11 + m1.m12 * m2.m21
					+ m1.m13 * m2.m31;
			this.m12 = m1.m10 * m2.m02 + m1.m11 * m2.m12 + m1.m12 * m2.m22
					+ m1.m13 * m2.m32;
			this.m13 = m1.m10 * m2.m03 + m1.m11 * m2.m13 + m1.m12 * m2.m23
					+ m1.m13 * m2.m33;

			this.m20 = m1.m20 * m2.m00 + m1.m21 * m2.m10 + m1.m22 * m2.m20
					+ m1.m23 * m2.m30;
			this.m21 = m1.m20 * m2.m01 + m1.m21 * m2.m11 + m1.m22 * m2.m21
					+ m1.m23 * m2.m31;
			this.m22 = m1.m20 * m2.m02 + m1.m21 * m2.m12 + m1.m22 * m2.m22
					+ m1.m23 * m2.m32;
			this.m23 = m1.m20 * m2.m03 + m1.m21 * m2.m13 + m1.m22 * m2.m23
					+ m1.m23 * m2.m33;

			this.m30 = m1.m30 * m2.m00 + m1.m31 * m2.m10 + m1.m32 * m2.m20
					+ m1.m33 * m2.m30;
			this.m31 = m1.m30 * m2.m01 + m1.m31 * m2.m11 + m1.m32 * m2.m21
					+ m1.m33 * m2.m31;
			this.m32 = m1.m30 * m2.m02 + m1.m31 * m2.m12 + m1.m32 * m2.m22
					+ m1.m33 * m2.m32;
			this.m33 = m1.m30 * m2.m03 + m1.m31 * m2.m13 + m1.m32 * m2.m23
					+ m1.m33 * m2.m33;
		}
		else
		{
			float tmp00, tmp01, tmp02, tmp03, tmp10, tmp11, tmp12, tmp13;
			float tmp20, tmp21, tmp22, tmp23, tmp30, tmp31, tmp32, tmp33;

			tmp00 = m1.m00 * m2.m00 + m1.m01 * m2.m10 + m1.m02 * m2.m20
					+ m1.m03 * m2.m30;
			tmp01 = m1.m00 * m2.m01 + m1.m01 * m2.m11 + m1.m02 * m2.m21
					+ m1.m03 * m2.m31;
			tmp02 = m1.m00 * m2.m02 + m1.m01 * m2.m12 + m1.m02 * m2.m22
					+ m1.m03 * m2.m32;
			tmp03 = m1.m00 * m2.m03 + m1.m01 * m2.m13 + m1.m02 * m2.m23
					+ m1.m03 * m2.m33;
			tmp10 = m1.m10 * m2.m00 + m1.m11 * m2.m10 + m1.m12 * m2.m20
					+ m1.m13 * m2.m30;
			tmp11 = m1.m10 * m2.m01 + m1.m11 * m2.m11 + m1.m12 * m2.m21
					+ m1.m13 * m2.m31;
			tmp12 = m1.m10 * m2.m02 + m1.m11 * m2.m12 + m1.m12 * m2.m22
					+ m1.m13 * m2.m32;
			tmp13 = m1.m10 * m2.m03 + m1.m11 * m2.m13 + m1.m12 * m2.m23
					+ m1.m13 * m2.m33;
			tmp20 = m1.m20 * m2.m00 + m1.m21 * m2.m10 + m1.m22 * m2.m20
					+ m1.m23 * m2.m30;
			tmp21 = m1.m20 * m2.m01 + m1.m21 * m2.m11 + m1.m22 * m2.m21
					+ m1.m23 * m2.m31;
			tmp22 = m1.m20 * m2.m02 + m1.m21 * m2.m12 + m1.m22 * m2.m22
					+ m1.m23 * m2.m32;
			tmp23 = m1.m20 * m2.m03 + m1.m21 * m2.m13 + m1.m22 * m2.m23
					+ m1.m23 * m2.m33;
			tmp30 = m1.m30 * m2.m00 + m1.m31 * m2.m10 + m1.m32 * m2.m20
					+ m1.m33 * m2.m30;
			tmp31 = m1.m30 * m2.m01 + m1.m31 * m2.m11 + m1.m32 * m2.m21
					+ m1.m33 * m2.m31;
			tmp32 = m1.m30 * m2.m02 + m1.m31 * m2.m12 + m1.m32 * m2.m22
					+ m1.m33 * m2.m32;
			tmp33 = m1.m30 * m2.m03 + m1.m31 * m2.m13 + m1.m32 * m2.m23
					+ m1.m33 * m2.m33;

			this.m00 = tmp00;
			this.m01 = tmp01;
			this.m02 = tmp02;
			this.m03 = tmp03;
			this.m10 = tmp10;
			this.m11 = tmp11;
			this.m12 = tmp12;
			this.m13 = tmp13;
			this.m20 = tmp20;
			this.m21 = tmp21;
			this.m22 = tmp22;
			this.m23 = tmp23;
			this.m30 = tmp30;
			this.m31 = tmp31;
			this.m32 = tmp32;
			this.m33 = tmp33;

		}
	}

	public final void multiplyTransposeBoth(Matrix4f m1, Matrix4f m2)
	{
		if (this != m1 && this != m2)
		{
			this.m00 = m1.m00 * m2.m00 + m1.m10 * m2.m01 + m1.m20 * m2.m02
					+ m1.m30 * m2.m03;
			this.m01 = m1.m00 * m2.m10 + m1.m10 * m2.m11 + m1.m20 * m2.m12
					+ m1.m30 * m2.m13;
			this.m02 = m1.m00 * m2.m20 + m1.m10 * m2.m21 + m1.m20 * m2.m22
					+ m1.m30 * m2.m23;
			this.m03 = m1.m00 * m2.m30 + m1.m10 * m2.m31 + m1.m20 * m2.m32
					+ m1.m30 * m2.m33;
			this.m10 = m1.m01 * m2.m00 + m1.m11 * m2.m01 + m1.m21 * m2.m02
					+ m1.m31 * m2.m03;
			this.m11 = m1.m01 * m2.m10 + m1.m11 * m2.m11 + m1.m21 * m2.m12
					+ m1.m31 * m2.m13;
			this.m12 = m1.m01 * m2.m20 + m1.m11 * m2.m21 + m1.m21 * m2.m22
					+ m1.m31 * m2.m23;
			this.m13 = m1.m01 * m2.m30 + m1.m11 * m2.m31 + m1.m21 * m2.m32
					+ m1.m31 * m2.m33;
			this.m20 = m1.m02 * m2.m00 + m1.m12 * m2.m01 + m1.m22 * m2.m02
					+ m1.m32 * m2.m03;
			this.m21 = m1.m02 * m2.m10 + m1.m12 * m2.m11 + m1.m22 * m2.m12
					+ m1.m32 * m2.m13;
			this.m22 = m1.m02 * m2.m20 + m1.m12 * m2.m21 + m1.m22 * m2.m22
					+ m1.m32 * m2.m23;
			this.m23 = m1.m02 * m2.m30 + m1.m12 * m2.m31 + m1.m22 * m2.m32
					+ m1.m32 * m2.m33;
			this.m30 = m1.m03 * m2.m00 + m1.m13 * m2.m01 + m1.m23 * m2.m02
					+ m1.m33 * m2.m03;
			this.m31 = m1.m03 * m2.m10 + m1.m13 * m2.m11 + m1.m23 * m2.m12
					+ m1.m33 * m2.m13;
			this.m32 = m1.m03 * m2.m20 + m1.m13 * m2.m21 + m1.m23 * m2.m22
					+ m1.m33 * m2.m23;
			this.m33 = m1.m03 * m2.m30 + m1.m13 * m2.m31 + m1.m23 * m2.m32
					+ m1.m33 * m2.m33;
		}
		else
		{
			float tmp00, tmp01, tmp02, tmp03, tmp10, tmp11, tmp12, tmp13;
			float tmp20, tmp21, tmp22, tmp23, tmp30, tmp31, tmp32, tmp33;

			tmp00 = m1.m00 * m2.m00 + m1.m10 * m2.m01 + m1.m20 * m2.m02
					+ m1.m30 * m2.m03;
			tmp01 = m1.m00 * m2.m10 + m1.m10 * m2.m11 + m1.m20 * m2.m12
					+ m1.m30 * m2.m13;
			tmp02 = m1.m00 * m2.m20 + m1.m10 * m2.m21 + m1.m20 * m2.m22
					+ m1.m30 * m2.m23;
			tmp03 = m1.m00 * m2.m30 + m1.m10 * m2.m31 + m1.m20 * m2.m32
					+ m1.m30 * m2.m33;
			tmp10 = m1.m01 * m2.m00 + m1.m11 * m2.m01 + m1.m21 * m2.m02
					+ m1.m31 * m2.m03;
			tmp11 = m1.m01 * m2.m10 + m1.m11 * m2.m11 + m1.m21 * m2.m12
					+ m1.m31 * m2.m13;
			tmp12 = m1.m01 * m2.m20 + m1.m11 * m2.m21 + m1.m21 * m2.m22
					+ m1.m31 * m2.m23;
			tmp13 = m1.m01 * m2.m30 + m1.m11 * m2.m31 + m1.m21 * m2.m32
					+ m1.m31 * m2.m33;
			tmp20 = m1.m02 * m2.m00 + m1.m12 * m2.m01 + m1.m22 * m2.m02
					+ m1.m32 * m2.m03;
			tmp21 = m1.m02 * m2.m10 + m1.m12 * m2.m11 + m1.m22 * m2.m12
					+ m1.m32 * m2.m13;
			tmp22 = m1.m02 * m2.m20 + m1.m12 * m2.m21 + m1.m22 * m2.m22
					+ m1.m32 * m2.m23;
			tmp23 = m1.m02 * m2.m30 + m1.m12 * m2.m31 + m1.m22 * m2.m32
					+ m1.m32 * m2.m33;
			tmp30 = m1.m03 * m2.m00 + m1.m13 * m2.m01 + m1.m23 * m2.m02
					+ m1.m33 * m2.m03;
			tmp31 = m1.m03 * m2.m10 + m1.m13 * m2.m11 + m1.m23 * m2.m12
					+ m1.m33 * m2.m13;
			tmp32 = m1.m03 * m2.m20 + m1.m13 * m2.m21 + m1.m23 * m2.m22
					+ m1.m33 * m2.m23;
			tmp33 = m1.m03 * m2.m30 + m1.m13 * m2.m31 + m1.m23 * m2.m32
					+ m1.m33 * m2.m33;

			this.m00 = tmp00;
			this.m01 = tmp01;
			this.m02 = tmp02;
			this.m03 = tmp03;
			this.m10 = tmp10;
			this.m11 = tmp11;
			this.m12 = tmp12;
			this.m13 = tmp13;
			this.m20 = tmp20;
			this.m21 = tmp21;
			this.m22 = tmp22;
			this.m23 = tmp23;
			this.m30 = tmp30;
			this.m31 = tmp31;
			this.m32 = tmp32;
			this.m33 = tmp33;
		}
	}

	public final void multiplyTransposeRight(Matrix4f m1, Matrix4f m2)
	{
		if (this != m1 && this != m2)
		{
			this.m00 = m1.m00 * m2.m00 + m1.m01 * m2.m01 + m1.m02 * m2.m02
					+ m1.m03 * m2.m03;
			this.m01 = m1.m00 * m2.m10 + m1.m01 * m2.m11 + m1.m02 * m2.m12
					+ m1.m03 * m2.m13;
			this.m02 = m1.m00 * m2.m20 + m1.m01 * m2.m21 + m1.m02 * m2.m22
					+ m1.m03 * m2.m23;
			this.m03 = m1.m00 * m2.m30 + m1.m01 * m2.m31 + m1.m02 * m2.m32
					+ m1.m03 * m2.m33;
			this.m10 = m1.m10 * m2.m00 + m1.m11 * m2.m01 + m1.m12 * m2.m02
					+ m1.m13 * m2.m03;
			this.m11 = m1.m10 * m2.m10 + m1.m11 * m2.m11 + m1.m12 * m2.m12
					+ m1.m13 * m2.m13;
			this.m12 = m1.m10 * m2.m20 + m1.m11 * m2.m21 + m1.m12 * m2.m22
					+ m1.m13 * m2.m23;
			this.m13 = m1.m10 * m2.m30 + m1.m11 * m2.m31 + m1.m12 * m2.m32
					+ m1.m13 * m2.m33;
			this.m20 = m1.m20 * m2.m00 + m1.m21 * m2.m01 + m1.m22 * m2.m02
					+ m1.m23 * m2.m03;
			this.m21 = m1.m20 * m2.m10 + m1.m21 * m2.m11 + m1.m22 * m2.m12
					+ m1.m23 * m2.m13;
			this.m22 = m1.m20 * m2.m20 + m1.m21 * m2.m21 + m1.m22 * m2.m22
					+ m1.m23 * m2.m23;
			this.m23 = m1.m20 * m2.m30 + m1.m21 * m2.m31 + m1.m22 * m2.m32
					+ m1.m23 * m2.m33;
			this.m30 = m1.m30 * m2.m00 + m1.m31 * m2.m01 + m1.m32 * m2.m02
					+ m1.m33 * m2.m03;
			this.m31 = m1.m30 * m2.m10 + m1.m31 * m2.m11 + m1.m32 * m2.m12
					+ m1.m33 * m2.m13;
			this.m32 = m1.m30 * m2.m20 + m1.m31 * m2.m21 + m1.m32 * m2.m22
					+ m1.m33 * m2.m23;
			this.m33 = m1.m30 * m2.m30 + m1.m31 * m2.m31 + m1.m32 * m2.m32
					+ m1.m33 * m2.m33;
		}
		else
		{
			float tmp00, tmp01, tmp02, tmp03, tmp10, tmp11, tmp12, tmp13;
			float tmp20, tmp21, tmp22, tmp23, tmp30, tmp31, tmp32, tmp33;

			tmp00 = m1.m00 * m2.m00 + m1.m01 * m2.m01 + m1.m02 * m2.m02
					+ m1.m03 * m2.m03;
			tmp01 = m1.m00 * m2.m10 + m1.m01 * m2.m11 + m1.m02 * m2.m12
					+ m1.m03 * m2.m13;
			tmp02 = m1.m00 * m2.m20 + m1.m01 * m2.m21 + m1.m02 * m2.m22
					+ m1.m03 * m2.m23;
			tmp03 = m1.m00 * m2.m30 + m1.m01 * m2.m31 + m1.m02 * m2.m32
					+ m1.m03 * m2.m33;

			tmp10 = m1.m10 * m2.m00 + m1.m11 * m2.m01 + m1.m12 * m2.m02
					+ m1.m13 * m2.m03;
			tmp11 = m1.m10 * m2.m10 + m1.m11 * m2.m11 + m1.m12 * m2.m12
					+ m1.m13 * m2.m13;
			tmp12 = m1.m10 * m2.m20 + m1.m11 * m2.m21 + m1.m12 * m2.m22
					+ m1.m13 * m2.m23;
			tmp13 = m1.m10 * m2.m30 + m1.m11 * m2.m31 + m1.m12 * m2.m32
					+ m1.m13 * m2.m33;

			tmp20 = m1.m20 * m2.m00 + m1.m21 * m2.m01 + m1.m22 * m2.m02
					+ m1.m23 * m2.m03;
			tmp21 = m1.m20 * m2.m10 + m1.m21 * m2.m11 + m1.m22 * m2.m12
					+ m1.m23 * m2.m13;
			tmp22 = m1.m20 * m2.m20 + m1.m21 * m2.m21 + m1.m22 * m2.m22
					+ m1.m23 * m2.m23;
			tmp23 = m1.m20 * m2.m30 + m1.m21 * m2.m31 + m1.m22 * m2.m32
					+ m1.m23 * m2.m33;

			tmp30 = m1.m30 * m2.m00 + m1.m31 * m2.m01 + m1.m32 * m2.m02
					+ m1.m33 * m2.m03;
			tmp31 = m1.m30 * m2.m10 + m1.m31 * m2.m11 + m1.m32 * m2.m12
					+ m1.m33 * m2.m13;
			tmp32 = m1.m30 * m2.m20 + m1.m31 * m2.m21 + m1.m32 * m2.m22
					+ m1.m33 * m2.m23;
			tmp33 = m1.m30 * m2.m30 + m1.m31 * m2.m31 + m1.m32 * m2.m32
					+ m1.m33 * m2.m33;

			this.m00 = tmp00;
			this.m01 = tmp01;
			this.m02 = tmp02;
			this.m03 = tmp03;
			this.m10 = tmp10;
			this.m11 = tmp11;
			this.m12 = tmp12;
			this.m13 = tmp13;
			this.m20 = tmp20;
			this.m21 = tmp21;
			this.m22 = tmp22;
			this.m23 = tmp23;
			this.m30 = tmp30;
			this.m31 = tmp31;
			this.m32 = tmp32;
			this.m33 = tmp33;
		}
	}

	public final void multiplyTransposeLeft(Matrix4f m1, Matrix4f m2)
	{
		if (this != m1 && this != m2)
		{
			this.m00 = m1.m00 * m2.m00 + m1.m10 * m2.m10 + m1.m20 * m2.m20
					+ m1.m30 * m2.m30;
			this.m01 = m1.m00 * m2.m01 + m1.m10 * m2.m11 + m1.m20 * m2.m21
					+ m1.m30 * m2.m31;
			this.m02 = m1.m00 * m2.m02 + m1.m10 * m2.m12 + m1.m20 * m2.m22
					+ m1.m30 * m2.m32;
			this.m03 = m1.m00 * m2.m03 + m1.m10 * m2.m13 + m1.m20 * m2.m23
					+ m1.m30 * m2.m33;
			this.m10 = m1.m01 * m2.m00 + m1.m11 * m2.m10 + m1.m21 * m2.m20
					+ m1.m31 * m2.m30;
			this.m11 = m1.m01 * m2.m01 + m1.m11 * m2.m11 + m1.m21 * m2.m21
					+ m1.m31 * m2.m31;
			this.m12 = m1.m01 * m2.m02 + m1.m11 * m2.m12 + m1.m21 * m2.m22
					+ m1.m31 * m2.m32;
			this.m13 = m1.m01 * m2.m03 + m1.m11 * m2.m13 + m1.m21 * m2.m23
					+ m1.m31 * m2.m33;
			this.m20 = m1.m02 * m2.m00 + m1.m12 * m2.m10 + m1.m22 * m2.m20
					+ m1.m32 * m2.m30;
			this.m21 = m1.m02 * m2.m01 + m1.m12 * m2.m11 + m1.m22 * m2.m21
					+ m1.m32 * m2.m31;
			this.m22 = m1.m02 * m2.m02 + m1.m12 * m2.m12 + m1.m22 * m2.m22
					+ m1.m32 * m2.m32;
			this.m23 = m1.m02 * m2.m03 + m1.m12 * m2.m13 + m1.m22 * m2.m23
					+ m1.m32 * m2.m33;
			this.m30 = m1.m03 * m2.m00 + m1.m13 * m2.m10 + m1.m23 * m2.m20
					+ m1.m33 * m2.m30;
			this.m31 = m1.m03 * m2.m01 + m1.m13 * m2.m11 + m1.m23 * m2.m21
					+ m1.m33 * m2.m31;
			this.m32 = m1.m03 * m2.m02 + m1.m13 * m2.m12 + m1.m23 * m2.m22
					+ m1.m33 * m2.m32;
			this.m33 = m1.m03 * m2.m03 + m1.m13 * m2.m13 + m1.m23 * m2.m23
					+ m1.m33 * m2.m33;
		}
		else
		{
			float tmp00, tmp01, tmp02, tmp03, tmp10, tmp11, tmp12, tmp13;
			float tmp20, tmp21, tmp22, tmp23, tmp30, tmp31, tmp32, tmp33;

			tmp00 = m1.m00 * m2.m00 + m1.m10 * m2.m10 + m1.m20 * m2.m20
					+ m1.m30 * m2.m30;
			tmp01 = m1.m00 * m2.m01 + m1.m10 * m2.m11 + m1.m20 * m2.m21
					+ m1.m30 * m2.m31;
			tmp02 = m1.m00 * m2.m02 + m1.m10 * m2.m12 + m1.m20 * m2.m22
					+ m1.m30 * m2.m32;
			tmp03 = m1.m00 * m2.m03 + m1.m10 * m2.m13 + m1.m20 * m2.m23
					+ m1.m30 * m2.m33;

			tmp10 = m1.m01 * m2.m00 + m1.m11 * m2.m10 + m1.m21 * m2.m20
					+ m1.m31 * m2.m30;
			tmp11 = m1.m01 * m2.m01 + m1.m11 * m2.m11 + m1.m21 * m2.m21
					+ m1.m31 * m2.m31;
			tmp12 = m1.m01 * m2.m02 + m1.m11 * m2.m12 + m1.m21 * m2.m22
					+ m1.m31 * m2.m32;
			tmp13 = m1.m01 * m2.m03 + m1.m11 * m2.m13 + m1.m21 * m2.m23
					+ m1.m31 * m2.m33;

			tmp20 = m1.m02 * m2.m00 + m1.m12 * m2.m10 + m1.m22 * m2.m20
					+ m1.m32 * m2.m30;
			tmp21 = m1.m02 * m2.m01 + m1.m12 * m2.m11 + m1.m22 * m2.m21
					+ m1.m32 * m2.m31;
			tmp22 = m1.m02 * m2.m02 + m1.m12 * m2.m12 + m1.m22 * m2.m22
					+ m1.m32 * m2.m32;
			tmp23 = m1.m02 * m2.m03 + m1.m12 * m2.m13 + m1.m22 * m2.m23
					+ m1.m32 * m2.m33;

			tmp30 = m1.m03 * m2.m00 + m1.m13 * m2.m10 + m1.m23 * m2.m20
					+ m1.m33 * m2.m30;
			tmp31 = m1.m03 * m2.m01 + m1.m13 * m2.m11 + m1.m23 * m2.m21
					+ m1.m33 * m2.m31;
			tmp32 = m1.m03 * m2.m02 + m1.m13 * m2.m12 + m1.m23 * m2.m22
					+ m1.m33 * m2.m32;
			tmp33 = m1.m03 * m2.m03 + m1.m13 * m2.m13 + m1.m23 * m2.m23
					+ m1.m33 * m2.m33;

			this.m00 = tmp00;
			this.m01 = tmp01;
			this.m02 = tmp02;
			this.m03 = tmp03;
			this.m10 = tmp10;
			this.m11 = tmp11;
			this.m12 = tmp12;
			this.m13 = tmp13;
			this.m20 = tmp20;
			this.m21 = tmp21;
			this.m22 = tmp22;
			this.m23 = tmp23;
			this.m30 = tmp30;
			this.m31 = tmp31;
			this.m32 = tmp32;
			this.m33 = tmp33;
		}
	}

	public boolean equals(Matrix4f matrix)
	{
		return epsilonEquals(matrix, 1e-6f);
	}

	public boolean epsilonEquals(Matrix4f matrix, float epsilon)
	{
		float diff;

		diff = m00 - matrix.m00;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m01 - matrix.m01;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m02 - matrix.m02;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m03 - matrix.m03;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m10 - matrix.m10;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m11 - matrix.m11;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m12 - matrix.m12;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m13 - matrix.m13;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m20 - matrix.m20;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m21 - matrix.m21;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m22 - matrix.m22;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m23 - matrix.m23;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m30 - matrix.m30;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m31 - matrix.m31;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m32 - matrix.m32;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;
		diff = m33 - matrix.m33;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		return true;
	}

	public void negate(Matrix4f matrix)
	{
		set(matrix);
		negate();
	}

	public void negate()
	{
		m00 = -m00;
		m01 = -m01;
		m02 = -m02;
		m03 = -m03;
		m10 = -m10;
		m11 = -m11;
		m12 = -m12;
		m13 = -m13;
		m20 = -m20;
		m21 = -m21;
		m22 = -m22;
		m23 = -m23;
		m30 = -m30;
		m31 = -m31;
		m32 = -m32;
		m33 = -m33;
	}

	public void invert(Matrix4f matrix)
	{
		if (this != matrix)
		{
			set(matrix);
			invert();
		}
		else
		{
			invert();
		}
	}

	public void invert()
	{
		tmp[0] = this.m00;
		tmp[1] = this.m01;
		tmp[2] = this.m02;
		tmp[3] = this.m03;

		tmp[4] = this.m10;
		tmp[5] = this.m11;
		tmp[6] = this.m12;
		tmp[7] = this.m13;

		tmp[8] = this.m20;
		tmp[9] = this.m21;
		tmp[10] = this.m22;
		tmp[11] = this.m23;

		tmp[12] = this.m30;
		tmp[13] = this.m31;
		tmp[14] = this.m32;
		tmp[15] = this.m33;

		if (!luDecomposition(tmp, row_perm))
		{
			// Matrix has no inverse
			throw new RuntimeException("Matrix is singular.");
		}

		for (int i = 0; i < 16; i++)
			result[i] = 0.0;

		result[0] = 1.0;
		result[5] = 1.0;
		result[10] = 1.0;
		result[15] = 1.0;
		luBacksubstitution(tmp, row_perm, result);

		this.m00 = (float) result[0];
		this.m01 = (float) result[1];
		this.m02 = (float) result[2];
		this.m03 = (float) result[3];
		this.m10 = (float) result[4];
		this.m11 = (float) result[5];
		this.m12 = (float) result[6];
		this.m13 = (float) result[7];
		this.m20 = (float) result[8];
		this.m21 = (float) result[9];
		this.m22 = (float) result[10];
		this.m23 = (float) result[11];
		this.m30 = (float) result[12];
		this.m31 = (float) result[13];
		this.m32 = (float) result[14];
		this.m33 = (float) result[15];

	}

	private boolean luDecomposition(double[] matrix0, int[] row_perm)
	{
		int i, j;
		int ptr, rs;
		double big, temp;

		ptr = 0;
		rs = 0;

		i = 4;
		while (i-- != 0)
		{
			big = 0.0;

			j = 4;
			while (j-- != 0)
			{
				temp = matrix0[ptr++];
				temp = Math.abs(temp);
				if (temp > big)
				{
					big = temp;
				}
			}

			if (big == 0.0)
			{
				return false;
			}
			row_scale[rs++] = 1.0 / big;
		}
		int mtx;

		mtx = 0;

		// For all columns, execute Crout's method
		for (j = 0; j < 4; j++)
		{
			int imax, k;
			int target, p1, p2;
			double sum;

			// Determine elements of upper diagonal matrix U
			for (i = 0; i < j; i++)
			{
				target = mtx + (4 * i) + j;
				sum = matrix0[target];
				k = i;
				p1 = mtx + (4 * i);
				p2 = mtx + j;
				while (k-- != 0)
				{
					sum -= matrix0[p1] * matrix0[p2];
					p1++;
					p2 += 4;
				}
				matrix0[target] = sum;
			}

			// Search for largest pivot element and calculate
			// intermediate elements of lower diagonal matrix L.
			big = 0.0;
			imax = -1;
			for (i = j; i < 4; i++)
			{
				target = mtx + (4 * i) + j;
				sum = matrix0[target];
				k = j;
				p1 = mtx + (4 * i);
				p2 = mtx + j;
				while (k-- != 0)
				{
					sum -= matrix0[p1] * matrix0[p2];
					p1++;
					p2 += 4;
				}
				matrix0[target] = sum;

				// Is this the best pivot so far?
				if ((temp = row_scale[i] * Math.abs(sum)) >= big)
				{
					big = temp;
					imax = i;
				}
			}

			if (imax < 0)
			{
				throw new RuntimeException(
						"Value of imax cannot be less than zero.");
			}

			if (j != imax)
			{
				// Yes: exchange rows
				k = 4;
				p1 = mtx + (4 * imax);
				p2 = mtx + (4 * j);
				while (k-- != 0)
				{
					temp = matrix0[p1];
					matrix0[p1++] = matrix0[p2];
					matrix0[p2++] = temp;
				}

				// Record change in scale factor
				row_scale[imax] = row_scale[j];
			}

			// Record row permutation
			row_perm[j] = imax;

			// Is the matrix singular
			if (matrix0[(mtx + (4 * j) + j)] == 0.0)
			{
				return false;
			}

			// Divide elements of lower diagonal matrix L by pivot
			if (j != (4 - 1))
			{
				temp = 1.0 / (matrix0[(mtx + (4 * j) + j)]);
				target = mtx + (4 * (j + 1)) + j;
				i = 3 - j;
				while (i-- != 0)
				{
					matrix0[target] *= temp;
					target += 4;
				}
			}
		}

		return true;
	}

	private void luBacksubstitution(double[] matrix1, int[] row_perm,
			double[] matrix2)
	{
		int i, ii, ip, j, k;
		int rp;
		int cv, rv;

		rp = 0;

		for (k = 0; k < 4; k++)
		{
			cv = k;
			ii = -1;

			// Forward substitution
			for (i = 0; i < 4; i++)
			{
				double sum;

				ip = row_perm[rp + i];
				sum = matrix2[cv + 4 * ip];
				matrix2[cv + 4 * ip] = matrix2[cv + 4 * i];
				if (ii >= 0)
				{
					// rv = &(matrix1[i][0]);
					rv = i * 4;
					for (j = ii; j <= i - 1; j++)
					{
						sum -= matrix1[rv + j] * matrix2[cv + 4 * j];
					}
				}
				else if (sum != 0.0)
				{
					ii = i;
				}
				matrix2[cv + 4 * i] = sum;
			}

			rv = 3 * 4;
			matrix2[cv + 4 * 3] /= matrix1[rv + 3];

			rv -= 4;
			matrix2[cv + 4 * 2] = (matrix2[cv + 4 * 2] - matrix1[rv + 3]
					* matrix2[cv + 4 * 3])
					/ matrix1[rv + 2];

			rv -= 4;
			matrix2[cv + 4 * 1] = (matrix2[cv + 4 * 1] - matrix1[rv + 2]
					* matrix2[cv + 4 * 2] - matrix1[rv + 3]
					* matrix2[cv + 4 * 3])
					/ matrix1[rv + 1];

			rv -= 4;
			matrix2[cv + 4 * 0] = (matrix2[cv + 4 * 0] - matrix1[rv + 1]
					* matrix2[cv + 4 * 1] - matrix1[rv + 2]
					* matrix2[cv + 4 * 2] - matrix1[rv + 3]
					* matrix2[cv + 4 * 3])
					/ matrix1[rv + 0];
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
