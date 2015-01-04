public class RotationMatrixd extends Matrix3d implements java.io.Serializable
{
	private static final long serialVersionUID = -6338795858018627706L;

	public RotationMatrixd()
	{
		super.setToIdentity();
	}
	
	public RotationMatrixd(RotationMatrixd matrix)
	{
		set(matrix);
	}
	
	public RotationMatrixd(Quaterniond quaternion)
	{
		set(quaternion);
	}
	
	public RotationMatrixd(AxisAngled axisAngle)
	{
		set(axisAngle);
	}
	
	public void set(Quaterniond quaternion)
	{
		setRotationWithQuaternion(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
	}
	
	private void setRotationWithQuaternion(double qx, double qy, double qz, double qw)
	   {
	      double yy2 = 2.0 * qy * qy;
	      double zz2 = 2.0 * qz * qz;
	      double xx2 = 2.0 * qx * qx;
	      double xy2 = 2.0 * qx * qy;
	      double wz2 = 2.0 * qw * qz;
	      double xz2 = 2.0 * qx * qz;
	      double wy2 = 2.0 * qw * qy;
	      double yz2 = 2.0 * qy * qz;
	      double wx2 = 2.0 * qw * qx;

	      m00 = (1.0 - yy2 - zz2);
	      m01 = (xy2 - wz2);
	      m02 = (xz2 + wy2);
	      m10 = (xy2 + wz2);
	      m11 = (1.0 - xx2 - zz2);
	      m12 = (yz2 - wx2);
	      m20 = (xz2 - wy2);
	      m21 = (yz2 + wx2);
	      m22 = (1.0 - xx2 - yy2);
	   }
	
	public void set(AxisAngled axisAngle)
	{
		setRotationWithAxisAngle(axisAngle.x,axisAngle.y,axisAngle.z,axisAngle.angle);
	}
	
	private void setRotationWithAxisAngle(double axisAngleX, double axisAngleY, double axisAngleZ, double axisAngleTheta)
	   {
	      double mag = Math.sqrt(axisAngleX * axisAngleX + axisAngleY * axisAngleY + axisAngleZ * axisAngleZ);

	      if (almostZero(mag))
	      {
	         setToIdentity();
	      }
	      else
	      {
	         mag = 1.0 / mag;
	         double ax = axisAngleX * mag;
	         double ay = axisAngleY * mag;
	         double az = axisAngleZ * mag;

	         double sinTheta = Math.sin(axisAngleTheta);
	         double cosTheta = Math.cos(axisAngleTheta);
	         double t = 1.0 - cosTheta;

	         double xz = ax * az;
	         double xy = ax * ay;
	         double yz = ay * az;

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
	
	public void rotate(Vector3d vector)
	{
		double x,y;
		
		x = vector.x*m00 + vector.y*m01 + vector.z*m02;
		y = vector.x*m10 + vector.y*m11 + vector.z*m12;
		vector.z = vector.x*m20 + vector.y*m21 + vector.z*m22;
		vector.x = x;
		vector.y = y;
	}
	
	public void rotate(Vector3f vector)
	{
		float x,y;
		
		x = (float)(vector.x*m00 + vector.y*m01 + vector.z*m02);
		y = (float)(vector.x*m10 + vector.y*m11 + vector.z*m12);
		vector.z = (float)(vector.x*m20 + vector.y*m21 + vector.z*m22);
		vector.x = x;
		vector.y = y;
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
	
	public void rotate(Vector3d vectorIn, Vector3d vectorOut)
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
	
	public void rotate(Vector3f vectorIn, Vector3f vectorOut)
	{
		if(vectorIn != vectorOut)
		{
			vectorOut.x = (float)(vectorIn.x*m00 + vectorIn.y*m01 + vectorIn.z*m02);
			vectorOut.y = (float)(vectorIn.x*m10 + vectorIn.y*m11 + vectorIn.z*m12);
			vectorOut.z = (float)(vectorIn.x*m20 + vectorIn.y*m21 + vectorIn.z*m22);
		}
		else
		{
			rotate(vectorIn);
		}
	}
	
	public void rotate(Point3d pointIn, Point3d pointOut)
	{
		if(pointIn != pointOut)
		{
			pointOut.x = pointIn.x*m00 + pointIn.y*m01 + pointIn.z*m02;
			pointOut.y = pointIn.x*m10 + pointIn.y*m11 + pointIn.z*m12;
			pointOut.z = pointIn.x*m20 + pointIn.y*m21 + pointIn.z*m22;
		}
		else
		{
			rotate(pointIn);
		}
	}
	
	public void rotate(Point3f pointIn, Point3f pointOut)
	{
		if(pointIn != pointOut)
		{
			pointOut.x = (float)(pointIn.x*m00 + pointIn.y*m01 + pointIn.z*m02);
			pointOut.y = (float)(pointIn.x*m10 + pointIn.y*m11 + pointIn.z*m12);
			pointOut.z = (float)(pointIn.x*m20 + pointIn.y*m21 + pointIn.z*m22);
		}
		else
		{
			rotate(pointIn);
		}
	}

	@Override
	public void invert()
	{
		super.transpose();
	}

	public void invert(RotationMatrixd matrix)
	{
		if(this != matrix)
		{
			super.set(matrix);
			super.transpose();
		}
		else
		{
			invert();
		}
	}
	
	/**
	 * Orthonormalize this matrix.
	 */
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
	
	public boolean isRotationProper()
	{
		if(!((this.determinant()-1) < 1e-10))
			return false;
		
		double a1 = m00; double a2 = m10; double a3 = m20;
		double b1 = m01; double b2 = m11; double b3 = m21;
		
		double zx = a2*b3 - a3*b2;
		double zy = a3*b1 - a1*b3;
		double zz = a1*b2 - a2*b1;
		
		return ((zx-m02)<1e-10 && (zy-m12)<1e-10 && (zz-m22)<1e-10);
	}
}
