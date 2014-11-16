
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
}
