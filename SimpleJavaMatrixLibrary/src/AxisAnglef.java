
public class AxisAnglef implements java.io.Serializable
{
	private static final long serialVersionUID = -1424270068156651950L;

	float x, y, z, angle;
	
	final static double EPS = 1.0e-6;

	public AxisAnglef(float x, float y, float z, float angle)
	{
		set(x,y,z,angle);
	}
	
	public AxisAnglef(float[] array)
	{
		set(array[0],array[1],array[2],array[3]);
	}

	public AxisAnglef(AxisAnglef axisAngle)
	{
		set(axisAngle);
	}

	public AxisAnglef(Vector3f axis, float angle)
	{
		set(axis,angle);
	}
	
	public AxisAnglef(RotationMatrixf matrix)
	{
		set(matrix);
	}
	
	public AxisAnglef(Quaternionf quaternion)
	{
		set(quaternion);
	}

	public AxisAnglef()
	{
		set(0.0f,0.0f,1.0f,0.0f);
	}

	public void set(float x, float y, float z, float angle)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.angle = angle;
	}

	public final void set(float[] array)
	{
		set(array[0],array[1],array[2],array[3]);
	}

	public void set(AxisAnglef axisAngle)
	{
		set(axisAngle.x,axisAngle.y,axisAngle.z,axisAngle.angle);
	}

	public void set(Vector3f axis, float angle)
	{
		set(axis.x,axis.y,axis.z,angle);
	}

	public void get(float[] array)
	{
		array[0] = this.x;
		array[1] = this.y;
		array[2] = this.z;
		array[3] = this.angle;
	}
	
	public void get(AxisAnglef axisAngle)
	{
		axisAngle.x = this.x;
		axisAngle.y = this.y;
		axisAngle.z = this.z;
		axisAngle.angle = this.angle;
	}
	
	public void getAxis(Vector3f vector)
	{
		vector.x = this.x;
		vector.y = this.y;
		vector.z = this.z;
	}
	
	public float getAngle()
	{
		return angle;
	}

	public final void set(RotationMatrixf matrix)
	{
		x = matrix.m21 - matrix.m12;
		y = matrix.m02 - matrix.m20;
		z = matrix.m10 - matrix.m01;

		float magnitude = x * x + y * y + z * z;

		if (magnitude > EPS)
		{
			magnitude = (float)Math.sqrt(magnitude);

			double sin = 0.5 * magnitude;
			double cos = 0.5 * (matrix.m00 + matrix.m11 + matrix.m22 - 1.0);

			angle = (float)Math.atan2(sin, cos);

			float invMag = 1.0f / magnitude;
			x = x * invMag;
			y = y * invMag;
			z = z * invMag;
		}
		else
		{
			x = 0.0f;
			y = 1.0f;
			z = 0.0f;
			angle = 0.0f;
		}
	}
	
	public final void set(Quaternionf quaternion)
	{
		float magnitude = quaternion.x * quaternion.x + quaternion.y * quaternion.y + quaternion.z * quaternion.z;

		if (magnitude > EPS)
		{
			magnitude = (float)Math.sqrt(magnitude);
			float invMag = 1.0f / magnitude;

			x = quaternion.x * invMag;
			y = quaternion.y * invMag;
			z = quaternion.z * invMag;
			angle = (float) (2.0 * Math.atan2(magnitude, quaternion.w));
		}
		else
		{
			x = 0.0f;
			y = 1.0f;
			z = 0.0f;
			angle = 0.0f;
		}
	}

	public String toString()
	{
		return "[" + this.x + ", " + this.y + ", " + this.z + ", " + this.angle
				+ "]";
	}

	public boolean equals(AxisAnglef axisAngle)
	{
		return epsilonEquals(axisAngle, 1e-16);
	}

	public boolean epsilonEquals(AxisAnglef axisAngle, double epsilon)
	{
		float diff;

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
