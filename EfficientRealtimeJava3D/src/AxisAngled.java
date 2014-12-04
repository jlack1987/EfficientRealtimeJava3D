public class AxisAngled implements java.io.Serializable
{
	private static final long serialVersionUID = 2674015964465577742L;

	double x, y, z, angle;

	final static double EPS = 1.0e-12;

	public AxisAngled(double x, double y, double z, double angle)
	{
		set(x,y,z,angle);
	}

	public AxisAngled(double[] array)
	{
		set(array[0],array[1],array[2],array[3]);
	}

	public AxisAngled(AxisAngled axisAngle)
	{
		set(axisAngle.x,axisAngle.y,axisAngle.z,axisAngle.angle);
	}

	public AxisAngled(Vector3d axis, double angle)
	{
		set(axis.x,axis.y,axis.z,angle);
	}
	
	public AxisAngled(RotationMatrixd matrix)
	{
		set(matrix);
	}
	
	public AxisAngled(Quaterniond quaternion)
	{
		set(quaternion);
	}

	public AxisAngled()
	{
		set(0.0,0.0,1.0,0.0);
	}

	public void set(double x, double y, double z, double angle)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.angle = angle;
	}

	public final void set(double[] array)
	{
		set(array[0],array[1],array[2],array[3]);
	}

	public void set(AxisAngled axisAngle)
	{
		set(axisAngle.x,axisAngle.y,axisAngle.z,axisAngle.angle);
	}

	public void set(Vector3d axis, double angle)
	{
		set(axis.x,axis.y,axis.z,angle);
	}

	public void get(double[] array)
	{
		array[0] = this.x;
		array[1] = this.y;
		array[2] = this.z;
		array[3] = this.angle;
	}
	
	public void get(AxisAngled axisAngle)
	{
		axisAngle.x = this.x;
		axisAngle.y = this.y;
		axisAngle.z = this.z;
		axisAngle.angle = this.angle;
	}
	
	public void getAxis(Vector3d vector)
	{
		vector.x = this.x;
		vector.y = this.y;
		vector.z = this.z;
	}
	
	public double getAngle()
	{
		return angle;
	}

	public final void set(RotationMatrixd matrix)
	{
		x = matrix.m21 - matrix.m12;
		y = matrix.m02 - matrix.m20;
		z = matrix.m10 - matrix.m01;

		double magnitude = x * x + y * y + z * z;

		if (magnitude > EPS)
		{
			magnitude = Math.sqrt(magnitude);

			double sin = 0.5 * magnitude;
			double cos = 0.5 * (matrix.m00 + matrix.m11 + matrix.m22 - 1.0);

			angle = Math.atan2(sin, cos);

			double invMag = 1.0 / magnitude;
			x = x * invMag;
			y = y * invMag;
			z = z * invMag;
		}
		else
		{
			x = 0.0;
			y = 1.0;
			z = 0.0;
			angle = 0.0;
		}
	}
	
	public final void set(Quaterniond quaternion)
	{
		double magnitude = quaternion.x * quaternion.x + quaternion.y * quaternion.y + quaternion.z * quaternion.z;

		if (magnitude > EPS)
		{
			magnitude = Math.sqrt(magnitude);
			double invMag = 1.0 / magnitude;

			x = quaternion.x * invMag;
			y = quaternion.y * invMag;
			z = quaternion.z * invMag;
			angle = 2.0 * Math.atan2(magnitude, quaternion.w);
		}
		else
		{
			x = 0.0;
			y = 1.0;
			z = 0.0;
			angle = 0;
		}
	}

	public String toString()
	{
		return "[" + this.x + ", " + this.y + ", " + this.z + ", " + this.angle
				+ "]";
	}

	public boolean equals(AxisAngled axisAngle)
	{
		return epsilonEquals(axisAngle, 1e-16);
	}

	public boolean epsilonEquals(AxisAngled axisAngle, double epsilon)
	{
		double diff;

		diff = x - axisAngle.x;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		diff = y - axisAngle.y;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		diff = z - axisAngle.z;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		diff = angle - axisAngle.angle;
		if ((diff < 0 ? -diff : diff) > epsilon)
			return false;

		return true;
	}
}
