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
