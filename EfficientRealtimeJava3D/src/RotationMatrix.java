public class RotationMatrix extends Matrix3d
{
	public RotationMatrix()
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

	public void invert()
	{
		super.transpose();
	}

	public void invert(RotationMatrix matrix)
	{
		super.set(matrix);
		super.transpose();
	}

	public void normalize()
	{
		double xdoty = m00 * m01 + m10 * m11 + m20 * m21;
		double xdotx = m00 * m00 + m10 * m10 + m20 * m20;

		double tmp = xdoty / xdotx;

		m01 -= tmp * m00;
		m11 -= tmp * m10;
		m21 -= tmp * m20;

		double zdoty = m02 * m01 + m12 * m11 + m22 * m21;
		double zdotx = m02 * m00 + m12 * m10 + m22 * m20;
		double ydoty = m01 * m01 + m11 * m11 + m21 * m21;

		tmp = zdotx / xdotx;

		double tmp1 = zdoty / ydoty;

		m02 = m02 - (tmp * m00 + tmp1 * m01);
		m12 = m12 - (tmp * m10 + tmp1 * m11);
		m22 = m22 - (tmp * m20 + tmp1 * m21);
		
		// Compute orthogonalized vector magnitudes and normalize
        double magX = Math.sqrt(m00 * m00 + m10 * m10 + m20 * m20);
        double magY = Math.sqrt(m01 * m01 + m11 * m11 + m21 * m21);
        double magZ = Math.sqrt(m02 * m02 + m12 * m12 + m22 * m22);

        m00 = m00 / magX;
        m10 = m10 / magX;
        m20 = m20 / magX;
        m01 = m01 / magY;
        m11 = m11 / magY;
        m21 = m21 / magY;
        m02 = m02 / magZ;
        m12 = m12 / magZ;
        m22 = m22 / magZ;
	}
}
