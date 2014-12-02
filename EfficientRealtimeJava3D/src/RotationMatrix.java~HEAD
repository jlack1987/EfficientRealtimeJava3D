public class RotationMatrix extends Matrix3d
{
	public RotationMatrix()
	{
		super.setToIdentity();
	}

	public void rotX(double angle)
	{
		double stheta = Math.sin(angle);
		double ctheta = Math.cos(angle);

		this.m00 = 1.0;
		this.m01 = 0.0;
		this.m02 = 0.0;

		this.m10 = 0.0;
		this.m11 = ctheta;
		this.m12 = -stheta;

		this.m20 = 0.0;
		this.m21 = stheta;
		this.m22 = ctheta;
	}
	
	@Override
	public void invert()
	{
		super.transpose();
	}
	
	public void invert(RotationMatrix matrix)
	{
		super.set(matrix);
		super.transpose();
	}
}
