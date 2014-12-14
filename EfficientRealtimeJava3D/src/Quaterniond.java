public class Quaterniond implements java.io.Serializable
{
	private static final long serialVersionUID = -706720842409320770L;
	private static final double EPS = 1e-12;

	double x, y, z, w;

	public Quaterniond()
	{
		set(0, 0, 0, 1);
	}
	
	public Quaterniond(AxisAngled A)
	{
		set(A);
	}

	public Quaterniond(double[] array)
	{
		set(array[0], array[1], array[2], array[3]);
	}

	public Quaterniond(float[] array)
	{
		set(array[0], array[1], array[2], array[3]);
	}

	public Quaterniond(Quaterniond quaternion)
	{
		set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
	}
	
	public Quaterniond(RotationMatrixd R)
	{
		set(R);
	}

	public void set(double x, double y, double z, double w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public void set(Quaterniond quaternion)
	{
		set(quaternion.x,quaternion.y,quaternion.z,quaternion.w);
	}

	public void set(AxisAngled axisAngle)
	{
		double mag, tmpMag;

		tmpMag = Math.sqrt(axisAngle.x * axisAngle.x + axisAngle.y
				* axisAngle.y + axisAngle.z * axisAngle.z);

		if (tmpMag < EPS)
		{
			w = 0.0;
			x = 0.0;
			y = 0.0;
			z = 0.0;
		}
		else
		{
			tmpMag = 1.0 / tmpMag;
			mag = Math.sin(axisAngle.angle / 2.0);
			w = Math.cos(axisAngle.angle / 2.0);
			x = axisAngle.x * tmpMag * mag;
			y = axisAngle.y * tmpMag * mag;
			z = axisAngle.z * tmpMag * mag;
		}

	}

	public void set(RotationMatrixd matrix)
	{
		double az, ay, ax;
		double ai, aj, ak;
		double si, sj, sk;
		double ci, cj, ck;
		double cy, cc, cs, sc, ss;

		cy = Math.sqrt(matrix.m00 * matrix.m00 + matrix.m10 * matrix.m10);

		if (cy > EPS)
		{
			ax = Math.atan2(matrix.m21, matrix.m22);
			ay = Math.atan2(-matrix.m20, cy);
			az = Math.atan2(matrix.m10, matrix.m00);
		}
		else
		{
			ax = Math.atan2(-matrix.m12, matrix.m11);
			ay = Math.atan2(-matrix.m20, cy);
			az = 0.0;
		}

		ai = ax / 2.0;
		aj = ay / 2.0;
		ak = az / 2.0;

		ci = Math.cos(ai);
		si = Math.sin(ai);
		cj = Math.cos(aj);
		sj = Math.sin(aj);
		ck = Math.cos(ak);
		sk = Math.sin(ak);
		cc = ci * ck;
		cs = ci * sk;
		sc = si * ck;
		ss = si * sk;

		this.x = cj * sc - sj * cs;
		this.y = cj * ss + sj * cc;
		this.z = cj * cs - sj * sc;
		this.w = cj * cc + sj * ss;
	}

	public final void multiply(Quaterniond quaternion1, Quaterniond quaternion2)
	{
		if (this != quaternion1 && this != quaternion2)
		{
			this.w = quaternion1.w * quaternion2.w - quaternion1.x
					* quaternion2.x - quaternion1.y * quaternion2.y
					- quaternion1.z * quaternion2.z;
			this.x = quaternion1.w * quaternion2.x + quaternion2.w
					* quaternion1.x + quaternion1.y * quaternion2.z
					- quaternion1.z * quaternion2.y;
			this.y = quaternion1.w * quaternion2.y + quaternion2.w
					* quaternion1.y - quaternion1.x * quaternion2.z
					+ quaternion1.z * quaternion2.x;
			this.z = quaternion1.w * quaternion2.z + quaternion2.w
					* quaternion1.z + quaternion1.x * quaternion2.y
					- quaternion1.y * quaternion2.x;
		}
		else
		{
			double x, y, w;

			w = quaternion1.w * quaternion2.w - quaternion1.x * quaternion2.x
					- quaternion1.y * quaternion2.y - quaternion1.z
					* quaternion2.z;
			x = quaternion1.w * quaternion2.x + quaternion2.w * quaternion1.x
					+ quaternion1.y * quaternion2.z - quaternion1.z
					* quaternion2.y;
			y = quaternion1.w * quaternion2.y + quaternion2.w * quaternion1.y
					- quaternion1.x * quaternion2.z + quaternion1.z
					* quaternion2.x;
			this.z = quaternion1.w * quaternion2.z + quaternion2.w
					* quaternion1.z + quaternion1.x * quaternion2.y
					- quaternion1.y * quaternion2.x;
			this.w = w;
			this.x = x;
			this.y = y;
		}
	}

	public final void multiply(Quaterniond quaternion)
	{
		double x, y, w;

		w = this.w * quaternion.w - this.x * quaternion.x - this.y
				* quaternion.y - this.z * quaternion.z;
		x = this.w * quaternion.x + quaternion.w * this.x + this.y
				* quaternion.z - this.z * quaternion.y;
		y = this.w * quaternion.y + quaternion.w * this.y - this.x
				* quaternion.z + this.z * quaternion.x;
		this.z = this.w * quaternion.z + quaternion.w * this.z + this.x
				* quaternion.y - this.y * quaternion.x;
		this.w = w;
		this.x = x;
		this.y = y;
	}

	public void multiplyInverse(Quaterniond quaternion)
	{
		double norm;

		norm = 1.0 / (quaternion.w * quaternion.w + quaternion.x * quaternion.x
				+ quaternion.y * quaternion.y + quaternion.z * quaternion.z);
		double tmpw = quaternion.w * norm;
		double tmpx = quaternion.x * -norm;
		double tmpy = quaternion.y * -norm;
		double tmpz = quaternion.z * -norm;

		double x, y, w;

		w = this.w * tmpw - this.x * tmpx - this.y * tmpy
				- this.z * tmpz;
		x = this.w * tmpx + tmpw * this.x + this.y * tmpz
				- this.z * tmpy;
		y = this.w * tmpy + tmpw * this.y - this.x * tmpz
				+ this.z * tmpx;
		this.z = this.w * tmpz + tmpw * this.z + this.x
				* tmpy - this.y * tmpx;
		this.w = w;
		this.x = x;
		this.y = y;
	}

	public void multiplyInverse(Quaterniond quaternion1, Quaterniond quaternion2)
	{
		double norm;

		norm = 1.0 / (quaternion2.w * quaternion2.w + quaternion2.x
				* quaternion2.x + quaternion2.y * quaternion2.y + quaternion2.z
				* quaternion2.z);
		double tmpw = quaternion2.w * norm;
		double tmpx = quaternion2.x * -norm;
		double tmpy = quaternion2.y * -norm;
		double tmpz = quaternion2.z * -norm;

		double x, y, w;

		w = quaternion1.w * tmpw - quaternion1.x * tmpx - quaternion1.y * tmpy
				- quaternion1.z * tmpz;
		x = quaternion1.w * tmpx + tmpw * quaternion1.x + quaternion1.y * tmpz
				- quaternion1.z * tmpy;
		y = quaternion1.w * tmpy + tmpw * quaternion1.y - quaternion1.x * tmpz
				+ quaternion1.z * tmpx;
		this.z = quaternion1.w * tmpz + tmpw * quaternion1.z + quaternion1.x
				* tmpy - quaternion1.y * tmpx;
		this.w = w;
		this.x = x;
		this.y = y;
	}

	public void inverse()
	{
		double norm;

		norm = 1.0 / (this.w * this.w + this.x * this.x + this.y * this.y + this.z
				* this.z);
		this.w *= norm;
		this.x *= -norm;
		this.y *= -norm;
		this.z *= -norm;
	}

	public void inverse(Quaterniond quaternion)
	{
		set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
		inverse();
	}

	public void conjugate(Quaterniond quaternion)
	{
		set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
		conjugate();
	}

	public void conjugate()
	{
		this.x *= -1;
		this.y *= -1;
		this.z *= -1;
	}

	public void normalize()
	{
		double mag = (x * x + y * y + z * z + w * w);
		if (mag > 0.0)
		{
			mag = 1 / Math.sqrt(mag);
			x *= 1 / mag;
			y *= 1 / mag;
			z *= 1 / mag;
			w *= 1 / mag;
		}
		else
		{
			x = 0;
			y = 0;
			z = 0;
			w = 0;
		}
	}

	public void normalize(Quaterniond quaternion)
	{
		set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
		normalize();
	}
	
	public String toString()
	{
		return "(" + x + "," + y + "," + z + "," + w + ")";
	}
}
