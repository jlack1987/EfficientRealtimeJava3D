
public class Quaternionf implements java.io.Serializable
{
	private static final long serialVersionUID = -4671918833820538367L;

	private static final double EPS = 1e-6;

	float x, y, z, w;

	public Quaternionf()
	{
		set(0, 0, 0, 1);
	}

	public Quaternionf(double[] array)
	{
		set((float)array[0], (float)array[1], (float)array[2], (float)array[3]);
		normalize();
	}

	public Quaternionf(float[] array)
	{
		set(array[0], array[1], array[2], array[3]);
		normalize();
	}
	
	public Quaternionf(Quaternionf quaternion)
	{
		set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
		normalize();
	}
	
	public Quaternionf(Vector4f vector)
	{
		set(vector.x,vector.y,vector.z,vector.w);
		normalize();
	}
	
	public Quaternionf(RotationMatrixf R)
	{
		set(R);
	}
	
	public Quaternionf(AxisAnglef A)
	{
		set(A);
	}

	public void set(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;;
	}
	
	public void set(Quaternionf quaternion)
	{
		set(quaternion.x,quaternion.y,quaternion.z,quaternion.w);
	}

	public final void set(AxisAnglef axisAngle)
	{
		float mag, tmpMag;

		tmpMag = (float)Math.sqrt(axisAngle.x * axisAngle.x + axisAngle.y
				* axisAngle.y + axisAngle.z * axisAngle.z);

		if (tmpMag < EPS)
		{
			w = 0.0f;
			x = 0.0f;
			y = 0.0f;
			z = 0.0f;
		}
		else
		{
			tmpMag = (float)1.0 / tmpMag;
			mag = (float)Math.sin(axisAngle.angle / 2.0);
			w = (float)Math.cos(axisAngle.angle / 2.0);
			x = axisAngle.x * tmpMag * mag;
			y = axisAngle.y * tmpMag * mag;
			z = axisAngle.z * tmpMag * mag;
		}

	}

	public void set(RotationMatrixf matrix)
	{
		float az, ay, ax;
		float ai, aj, ak;
		float si, sj, sk;
		float ci, cj, ck;
		float cy, cc, cs, sc, ss;

		cy = (float)Math.sqrt(matrix.m00 * matrix.m00 + matrix.m10 * matrix.m10);

		if (cy > EPS)
		{
			ax = (float)Math.atan2(matrix.m21, matrix.m22);
			ay = (float)Math.atan2(-matrix.m20, cy);
			az = (float)Math.atan2(matrix.m10, matrix.m00);
		}
		else
		{
			ax = (float)Math.atan2(-matrix.m12, matrix.m11);
			ay = (float)Math.atan2(-matrix.m20, cy);
			az = 0.0f;
		}

		ai = ax / 2.0f;
		aj = ay / 2.0f;
		ak = az / 2.0f;

		ci = (float)Math.cos(ai);
		si = (float)Math.sin(ai);
		cj = (float)Math.cos(aj);
		sj = (float)Math.sin(aj);
		ck = (float)Math.cos(ak);
		sk = (float)Math.sin(ak);
		cc = ci * ck;
		cs = ci * sk;
		sc = si * ck;
		ss = si * sk;

		this.x = cj * sc - sj * cs;
		this.y = cj * ss + sj * cc;
		this.z = cj * cs - sj * sc;
		this.w = cj * cc + sj * ss;
	}

	public final void multiply(Quaternionf quaternion1, Quaternionf quaternion2)
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
			float x, y, w;

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

	public final void multiply(Quaternionf quaternion)
	{
		float x, y, w;

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

	public void multiplyInverse(Quaternionf quaternion)
	{
		float norm;

		norm = 1.0f / (quaternion.w * quaternion.w + quaternion.x * quaternion.x
				+ quaternion.y * quaternion.y + quaternion.z * quaternion.z);
		float tmpw = quaternion.w * norm;
		float tmpx = quaternion.x * -norm;
		float tmpy = quaternion.y * -norm;
		float tmpz = quaternion.z * -norm;

		float x, y, w;

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

	public void multiplyInverse(Quaternionf quaternion1, Quaternionf quaternion2)
	{
		float norm;

		norm = 1.0f / (quaternion2.w * quaternion2.w + quaternion2.x
				* quaternion2.x + quaternion2.y * quaternion2.y + quaternion2.z
				* quaternion2.z);
		float tmpw = quaternion2.w * norm;
		float tmpx = quaternion2.x * -norm;
		float tmpy = quaternion2.y * -norm;
		float tmpz = quaternion2.z * -norm;

		float x, y, w;

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
		float norm;

		norm = 1.0f / (this.w * this.w + this.x * this.x + this.y * this.y + this.z
				* this.z);
		this.w *= norm;
		this.x *= -norm;
		this.y *= -norm;
		this.z *= -norm;
	}

	public void inverse(Quaternionf quaternion)
	{
		set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
		inverse();
	}
	
	public void rotate(Vector3f vector)
	{
		float w = -this.x * vector.x - this.y * vector.y - this.z * vector.z;
		float x = this.w * vector.x + this.y * vector.z - this.z * vector.y;
		float y = this.w * vector.y - this.x * vector.z + this.z * vector.x;
		float z = this.w * vector.z + this.x * vector.y - this.y * vector.x;

		float norm;

		norm = 1.0f / (this.w * this.w + this.x * this.x + this.y * this.y + this.z
				* this.z);

		float tmpw = this.w * norm;
		float tmpx = this.x * -norm;
		float tmpy = this.y * -norm;
		float tmpz = this.z * -norm;

		vector.x = w * tmpx + tmpw * x + y * tmpz
				- z * tmpy;
		vector.y = w * tmpy + tmpw * y - x * tmpz
				+ z * tmpx;
		vector.z = w * tmpz + tmpw * z + x * tmpy
				- y * tmpx;
	}

	public void conjugate(Quaternionf quaternion)
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
		float mag = (x * x + y * y + z * z + w * w);
		if (mag > 0.0)
		{
			mag = 1 / (float)Math.sqrt(mag);
			x *= mag;
			y *= mag;
			z *= mag;
			w *= mag;
		}
		else
		{
			x = 0.0f;
			y = 0.0f;
			z = 0.0f;
			w = 0.0f;
		}
	}

	public void normalize(Quaternionf quaternion)
	{
		set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
		normalize();
	}
	
	public String toString()
	{
		return "(" + x + "," + y + "," + z + "," + w + ")";
	}
}
