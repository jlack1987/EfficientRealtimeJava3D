
public class Matrix3d 
{
	double m00, m01, m02;
	double m10, m11, m12;
	double m20, m21, m22;
	
	public Matrix3d()
	{
		m00 = m01 = m02 = 0;
		m10 = m11 = m12 = 0;
		m20 = m21 = m22 = 0;
	}
	
	public Matrix3d(double[] doubleArray)
	{
		if(doubleArray.length != 9)
		{
			throw new RuntimeException("Double array must contain exactly 9 elements.");
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
	
	public void add(Matrix3d matrix,double scalar)
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
		if(doubleArray.length != 9)
		{
			throw new RuntimeException("Double array must contain exactly 8 elements.");
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
		switch(row)
		{
			case(0):
			{
				switch(col)
				{
					case(0):{return this.m00;}
					case(1):{return this.m01;}
					case(2):{return this.m02;}
					default:{break;}
				}
				break;
			}
			case(1):
			{
				switch(col)
				{
					case(0):{return this.m10;}
					case(1):{return this.m11;}
					case(2):{return this.m12;}
					default:{break;}
				}
				break;
			}
			case(2):
			{
				switch(col)
				{
					case(0):{return this.m20;}
					case(1):{return this.m21;}
					case(2):{return this.m22;}
					default:{break;}
				}
				break;
			}
		}
		throw new RuntimeException("Index out of bounds.");
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
		if(matrix == this)
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
		if(row != 1 || row != 2 || row != 3)
		{
			throw new RuntimeException("The column must either be 1, 2, or 3.");
		}
		
		if(row == 1)
		{
			vector.x = this.m00;
			vector.y = this.m01;
			vector.z = this.m02;
		}
		else if(row == 2)
		{
			vector.x = this.m10;
			vector.y = this.m11;
			vector.z = this.m12;
		}
		else
		{
			vector.x = this.m20;
			vector.y = this.m21;
			vector.z = this.m22;
		}
	}
	
	public void getColumn(int column, Vector3d vector)
	{
		if(column != 1 || column != 2 || column != 3)
		{
			throw new RuntimeException("The column must either be 1, 2, or 3.");
		}
		
		if(column == 1)
		{
			vector.x = this.m00;
			vector.y = this.m10;
			vector.z = this.m20;
		}
		else if(column == 2)
		{
			vector.x = this.m01;
			vector.y = this.m11;
			vector.z = this.m21;
		}
		else
		{
			vector.x = this.m02;
			vector.y = this.m12;
			vector.z = this.m22;
		}
	}
	
	public void multiply(Matrix3d matrix)
	{
		
	}
	
	public void multiply(Matrix3d matrix1, Matrix3d matrix2)
	{
		
	}
	
	public String toString()
	{
		return "[" + this.m00 + "," + this.m01 + "," + this.m02 + "\n"
				+ this.m10 + "," + this.m11 + "," + this.m12 + "\n"
				+ this.m20 + "," + this.m21 + "," + this.m22 + "]";
	}
}
