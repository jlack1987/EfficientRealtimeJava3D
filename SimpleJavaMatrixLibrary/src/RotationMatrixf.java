
public class RotationMatrixf extends Matrix3f implements java.io.Serializable
{
	private static final long serialVersionUID = -4858597915120632932L;

	public RotationMatrixf()
	{
		super.setToIdentity();
	}
	
	public RotationMatrixf(RotationMatrixf matrix)
	{
		set(matrix);
	}
	
	public RotationMatrixf(Quaternionf quaternion)
	{
		set(quaternion);
	}
	
	public RotationMatrixf(AxisAnglef axisAngle)
	{
		set(axisAngle);
	}
	
	public void set(Quaternionf quaternion)
	{
		setRotationWithQuaternion(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
	}
	
	private void setRotationWithQuaternion(float qx, float qy, float qz, float qw)
	   {
	      float yy2 = 2.0f * qy * qy;
	      float zz2 = 2.0f * qz * qz;
	      float xx2 = 2.0f * qx * qx;
	      float xy2 = 2.0f * qx * qy;
	      float wz2 = 2.0f * qw * qz;
	      float xz2 = 2.0f * qx * qz;
	      float wy2 = 2.0f * qw * qy;
	      float yz2 = 2.0f * qy * qz;
	      float wx2 = 2.0f * qw * qx;

	      m00 = (1.0f - yy2 - zz2);
	      m01 = (xy2 - wz2);
	      m02 = (xz2 + wy2);
	      m10 = (xy2 + wz2);
	      m11 = (1.0f - xx2 - zz2);
	      m12 = (yz2 - wx2);
	      m20 = (xz2 - wy2);
	      m21 = (yz2 + wx2);
	      m22 = (1.0f - xx2 - yy2);
	   }
	
	public void set(AxisAnglef axisAngle)
	{
		setRotationWithAxisAngle(axisAngle.x,axisAngle.y,axisAngle.z,axisAngle.angle);
	}
	
	private void setRotationWithAxisAngle(float axisAngleX, float axisAngleY, float axisAngleZ, float axisAngleTheta)
	   {
	      float mag = (float)Math.sqrt(axisAngleX * axisAngleX + axisAngleY * axisAngleY + axisAngleZ * axisAngleZ);

	      if (almostZero(mag))
	      {
	         setToIdentity();
	      }
	      else
	      {
	         mag = 1.0f / mag;
	         float ax = axisAngleX * mag;
	         float ay = axisAngleY * mag;
	         float az = axisAngleZ * mag;
	         float sinTheta = (float)Math.sin(axisAngleTheta);
	         float cosTheta = (float)Math.cos(axisAngleTheta);
	         float t = 1.0f - cosTheta;
	         float xz = ax * az;
	         float xy = ax * ay;
	         float yz = ay * az;

	         this.m00 = (t * ax * ax + cosTheta);
	         this.m01 = (t * xy - sinTheta * az);
	         m02 = (t * xz + sinTheta * ay);
	         m10 = (t * xy + sinTheta * az);
	         m11 = (t * ay * ay + cosTheta);
	         m12 = (t * yz - sinTheta * ax);
	         m20 = (t * xz - sinTheta * ay);
	         m21 = (t * yz + sinTheta * ax);
	         m22 = (t * az * az + cosTheta);
	      }
	   }

	public final void rotX(float theta)
	{
		float sTheta, cTheta;

		sTheta = (float)Math.sin(theta);
		cTheta = (float)Math.cos(theta);

		this.m00 = 1.0f;
		this.m01 = 0.0f;
		this.m02 = 0.0f;
		this.m10 = 0.0f;
		this.m11 = cTheta;
		this.m12 = -sTheta;
		this.m20 = 0.0f;
		this.m21 = sTheta;
		this.m22 = cTheta;
	}

	public final void rotY(float theta)
	{
		float sTheta, cTheta;

		sTheta = (float)Math.sin(theta);
		cTheta = (float)Math.cos(theta);

		this.m00 = cTheta;
		this.m01 = 0.0f;
		this.m02 = sTheta;

		this.m10 = 0.0f;
		this.m11 = 1.0f;
		this.m12 = 0.0f;

		this.m20 = -sTheta;
		this.m21 = 0.0f;
		this.m22 = cTheta;
	}

	public final void rotZ(float theta)
	{
		float sTheta, cTheta;

		sTheta = (float)Math.sin(theta);
		cTheta = (float)Math.cos(theta);

		this.m00 = cTheta;
		this.m01 = -sTheta;
		this.m02 = 0.0f;

		this.m10 = sTheta;
		this.m11 = cTheta;
		this.m12 = 0.0f;

		this.m20 = 0.0f;
		this.m21 = 0.0f;
		this.m22 = 1.0f;
	}
	
	public void rotate(Vector3f vector)
	{
		float x,y;
		
		x = vector.x*m00 + vector.y*m01 + vector.z*m02;
		y = vector.x*m10 + vector.y*m11 + vector.z*m12;
		vector.z = vector.x*m20 + vector.y*m21 + vector.z*m22;
		vector.x = x;
		vector.y = y;
	}
	
	public void rotate(Vector3f vectorIn, Vector3f vectorOut)
	{
		if(vectorIn != vectorOut)
		{
			vectorOut.x = vectorIn.x*m00 + vectorIn.y*m01 + vectorIn.z*m02;
			vectorOut.y = vectorIn.x*m10 + vectorIn.y*m11 + vectorIn.z*m12;
			vectorOut.z = vectorIn.x*m20 + vectorIn.y*m21 + vectorIn.z*m22;
		}
		else
		{
			rotate(vectorIn);
		}
	}
	
	public void rotate(Point3d point)
	{
		double x,y;
		
		x = point.x*m00 + point.y*m01 + point.z*m02;
		y = point.x*m10 + point.y*m11 + point.z*m12;
		point.z = point.x*m20 + point.y*m21 + point.z*m22;
		point.x = x;
		point.y = y;
	}
	
	public void rotate(Point3f point)
	{
		float x,y;
		
		x = (float)(point.x*m00 + point.y*m01 + point.z*m02);
		y = (float)(point.x*m10 + point.y*m11 + point.z*m12);
		point.z = (float)(point.x*m20 + point.y*m21 + point.z*m22);
		point.x = x;
		point.y = y;
	}

	@Override
	public void invert()
	{
		super.transpose();
	}

	public void invert(RotationMatrixf matrix)
	{
		super.set(matrix);
		super.transpose();
	}
	
	/**
	 * Orthonormalize this matrix.
	 */
	public void normalize()
	{
		float xdoty = m00 * m01 + m10 * m11 + m20 * m21;
		float xdotx = m00 * m00 + m10 * m10 + m20 * m20;

		float tmp = xdoty / xdotx;

		m01 -= tmp * m00;
		m11 -= tmp * m10;
		m21 -= tmp * m20;

		float zdoty = m02 * m01 + m12 * m11 + m22 * m21;
		float zdotx = m02 * m00 + m12 * m10 + m22 * m20;
		float ydoty = m01 * m01 + m11 * m11 + m21 * m21;

		tmp = zdotx / xdotx;

		float tmp1 = zdoty / ydoty;

		m02 = m02 - (tmp * m00 + tmp1 * m01);
		m12 = m12 - (tmp * m10 + tmp1 * m11);
		m22 = m22 - (tmp * m20 + tmp1 * m21);

		// Compute orthogonalized vector magnitudes and normalize
		float magX = (float)Math.sqrt(m00 * m00 + m10 * m10 + m20 * m20);
		float magY = (float)Math.sqrt(m01 * m01 + m11 * m11 + m21 * m21);
		float magZ = (float)Math.sqrt(m02 * m02 + m12 * m12 + m22 * m22);

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
