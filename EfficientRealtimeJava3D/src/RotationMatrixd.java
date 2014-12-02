public class RotationMatrixd extends Matrix3d implements java.io.Serializable
{
	private static final long serialVersionUID = -6338795858018627706L;

	public RotationMatrixd()
	{
		super.setToIdentity();
	}

	public final void rotX(double theta)
	{
		double sTheta, cTheta;

		sTheta = Math.sin(theta);
		cTheta = Math.cos(theta);

		this.m00 = 1.0;
		this.m01 = 0.0;
		this.m02 = 0.0;

		this.m10 = 0.0;
		this.m11 = cTheta;
		this.m12 = -sTheta;

		this.m20 = 0.0;
		this.m21 = sTheta;
		this.m22 = cTheta;
	}

	public final void rotY(double theta)
	{
		double sTheta, cTheta;

		sTheta = Math.sin(theta);
		cTheta = Math.cos(theta);

		this.m00 = cTheta;
		this.m01 = 0.0;
		this.m02 = sTheta;

		this.m10 = 0.0;
		this.m11 = 1.0;
		this.m12 = 0.0;

		this.m20 = -sTheta;
		this.m21 = 0.0;
		this.m22 = cTheta;
	}

	public final void rotZ(double theta)
	{
		double sTheta, cTheta;

		sTheta = Math.sin(theta);
		cTheta = Math.cos(theta);

		this.m00 = cTheta;
		this.m01 = -sTheta;
		this.m02 = 0.0;

		this.m10 = sTheta;
		this.m11 = cTheta;
		this.m12 = 0.0;

		this.m20 = 0.0;
		this.m21 = 0.0;
		this.m22 = 1.0;
	}

	@Override
	public void invert()
	{
		super.transpose();
	}

	public void invert(RotationMatrixd matrix)
	{
		super.set(matrix);
		super.transpose();
	}
}
