
public class RotationMatrix extends Matrix3d
{
	public RotationMatrix()
	{
		super.setToIdentity();
	}
	
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
