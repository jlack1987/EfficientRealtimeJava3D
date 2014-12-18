import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class AxisAngledTest
{
	int nTests = 10;
	Random random = new Random();
	
	public void setUp()
	{
		
	}
	
	public void tearDown()
	{
		System.gc();
	}
	
	@Test
	public void testSetFromRotationMatrix()
	{
		RotationMatrixd matrix = new RotationMatrixd();
		AxisAngled axisAngle = new AxisAngled();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			axisAngle.set(matrix);
			RotationMatrixd matrix2 = new RotationMatrixd(axisAngle);
			assertMatrixEquals(matrix, matrix2, 1e-8);
		}
	}
	
	@Test
	public void testSetFromRotationMatrix2()
	{
		AxisAngled axisAngle1 = new AxisAngled();
		AxisAngled axisAngle2 = new AxisAngled();
		axisAngle2.toString();
		RotationMatrixd matrix = new RotationMatrixd();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomAxisAngle(random, axisAngle1);
			matrix.set(axisAngle1);
			axisAngle2.set(matrix);
			assertAxisAngledEquals(axisAngle1, axisAngle2, 1e-8);
		}
	}
	
	@Test
	public void testConstructors()
	{
		AxisAngled A = new AxisAngled();
		double angle = Math.PI/4;
		A.x = Math.cos(angle);
		A.y = Math.sin(angle);
		A.z = 0;
		A.angle = angle;
		AxisAngled A2 = new AxisAngled(A);
		A2.get(A);
		assertEquals(A.x,A2.x,1e-12);
		assertEquals(A.y,A2.y,1e-12);
		assertEquals(A.z,A2.z,1e-12);
		assertEquals(A.angle,A2.angle,1e-12);
	}
	
	@Test
	public void testGetAndSetFromAxisAndAngle()
	{
		Vector3d V = new Vector3d();
		V.set(random.nextDouble(),random.nextDouble(),random.nextDouble());
		double angle = random.nextDouble();
		
		AxisAngled A = new AxisAngled(V,angle);
		double[] D = new double[4];
		A.get(D);
		assertEquals(D[0],A.x,1e-12);
		assertEquals(D[1],A.y,1e-12);
		assertEquals(D[2],A.z,1e-12);
		assertEquals(D[3],A.angle,1e-12);
	}
	
	@Test
	public void testSetFromQuaternion1()
	{
		Quaterniond quaternion = new Quaterniond();
		AxisAngled axisAngle1 = new AxisAngled();
		AxisAngled axisAngle2 = new AxisAngled();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomAxisAngle(random, axisAngle1);
			quaternion.set(axisAngle1);
			axisAngle2.set(quaternion);
			
			assertAxisAngledEquals(axisAngle1, axisAngle2, 1e-8);
		}
	}
	
	@Test
	public void testSetFromZeroQuaternion()
	{
		Quaterniond Q = new Quaterniond();
		Q.set(0,0,0,0);
		
		AxisAngled A = new AxisAngled(Q);
		A.toString();
		assertEquals(A.x,0,1e-12);
		assertEquals(A.y,1,1e-12);
		assertEquals(A.z,0,1e-12);
		assertEquals(A.angle,0,1e-12);
	}
	
	@Test
	public void testEquals()
	{
		double[] D = new double[4];
		D[0] = 0;
		D[1] = 0;
		D[2] = 0;
		D[3] = 0;
		
		AxisAngled A = new AxisAngled(D);
		AxisAngled A2 = new AxisAngled(D);
		assertTrue(A.equals(A2));
		
		D[1] = 1e-12;
		
		A.set(D);
		assertFalse(A.equals(A2));
		assertTrue(A.epsilonEquals(A2, 1e-5));
	}
	
	@Test
	public void testSetFromIdentityRotation()
	{
		RotationMatrixd R = new RotationMatrixd();
		R.setToIdentity();
		
		AxisAngled A = new AxisAngled(R);
		
		Vector3d V = new Vector3d();
		A.getAxis(V);
		double angle = A.getAngle();
		assertEquals(V.x,0,1e-12);
		assertEquals(V.y,1,1e-12);
		assertEquals(V.z,0,1e-12);
		assertEquals(angle,0,1e-12);
	}
	
	@SuppressWarnings("unused")
	private void assertQuaterniondEquals(Quaterniond q1, Quaterniond q2, double epsilon)
	{
		assertEquals(q1.x,q2.x,epsilon);
		assertEquals(q1.y,q2.y,epsilon);
		assertEquals(q1.z,q2.z,epsilon);
		assertEquals(q1.w,q2.w,epsilon);
	}
	
	private void assertAxisAngledEquals(AxisAngled axisAngle1, AxisAngled axisAngle2, double epsilon)
	{
		assertEquals(axisAngle1.x,axisAngle2.x,epsilon);
		assertEquals(axisAngle1.y,axisAngle2.y,epsilon);
		assertEquals(axisAngle2.z,axisAngle2.z,epsilon);
		assertEquals(axisAngle1.angle,axisAngle2.angle,epsilon);
	}
	
	private void assertMatrixEquals(Matrix3d matrix1, Matrix3d matrix2, double epsilon)
	{
		assertEquals(matrix1.m00, matrix2.m00, epsilon);
		assertEquals(matrix1.m01, matrix2.m01, epsilon);
		assertEquals(matrix1.m02, matrix2.m02, epsilon);
		assertEquals(matrix1.m10, matrix2.m10, epsilon);
		assertEquals(matrix1.m11, matrix2.m11, epsilon);
		assertEquals(matrix1.m12, matrix2.m12, epsilon);
		assertEquals(matrix1.m20, matrix2.m20, epsilon);
		assertEquals(matrix1.m21, matrix2.m21, epsilon);
		assertEquals(matrix1.m22, matrix2.m22, epsilon);
	}
	
	private void createRandomRotationMatrix(Matrix3d matrix, Random random)
	{
		Matrix3d rotX = new Matrix3d();
		Matrix3d rotY = new Matrix3d();
		Matrix3d rotZ = new Matrix3d();

		createRandomRotationMatrixX(random, rotX);
		createRandomRotationMatrixY(random, rotY);
		createRandomRotationMatrixZ(random, rotZ);

		rotX.multiply(rotY);
		rotX.multiply(rotZ);

		matrix.m00 = rotX.m00;
		matrix.m01 = rotX.m01;
		matrix.m02 = rotX.m02;
		matrix.m10 = rotX.m10;
		matrix.m11 = rotX.m11;
		matrix.m12 = rotX.m12;
		matrix.m20 = rotX.m20;
		matrix.m21 = rotX.m21;
		matrix.m22 = rotX.m22;
	}

	private void createRandomRotationMatrixX(Random random, Matrix3d matrix)
	{
		double theta = random.nextDouble();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = 1;
		matrix.m01 = 0;
		matrix.m02 = 0;
		matrix.m10 = 0;
		matrix.m11 = cTheta;
		matrix.m12 = -sTheta;
		matrix.m20 = 0;
		matrix.m21 = sTheta;
		matrix.m22 = cTheta;
	}

	private void createRandomRotationMatrixY(Random random, Matrix3d matrix)
	{
		double theta = random.nextDouble();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = cTheta;
		matrix.m01 = 0;
		matrix.m02 = sTheta;
		matrix.m10 = 0;
		matrix.m11 = 1;
		matrix.m12 = 0;
		matrix.m20 = -sTheta;
		matrix.m21 = 0;
		matrix.m22 = cTheta;
	}
	
	private void createRandomAxisAngle(Random random, AxisAngled axisAngle)
	{
		axisAngle.x = random.nextDouble();
		axisAngle.y = random.nextDouble();
		axisAngle.z = random.nextDouble();
		double mag = Math.sqrt(axisAngle.x*axisAngle.x + axisAngle.y*axisAngle.y + axisAngle.z*axisAngle.z);
		
		axisAngle.x *= 1/mag;
		axisAngle.y *= 1/mag;
		axisAngle.z *= 1/mag;
		
		axisAngle.angle = random.nextDouble()*Math.PI;
	}
	
	
	@SuppressWarnings("unused")
	private void createRandomQuaternion(Random random, Quaterniond q)
	{
		q.x = random.nextDouble();
		q.y = random.nextDouble();
		q.z = random.nextDouble();
		q.w = random.nextDouble();
		
		double val = 1/(Math.sqrt(q.x*q.x + q.y*q.y + q.z*q.z + q.w*q.w));
		
		q.x *= val;
		q.y *= val;
		q.z *= val;
		q.w *= val;
	}
	
  private void createRandomRotationMatrixZ(Random random, Matrix3d matrix)
  {
     double theta = random.nextDouble();
     double cTheta = Math.cos(theta);
     double sTheta = Math.sin(theta);
     matrix.m00 = cTheta;
     matrix.m01 = -sTheta;
     matrix.m02 = 0;
     matrix.m10 = sTheta;
     matrix.m11 = cTheta;
     matrix.m12 = 0;
     matrix.m20 = 0;
     matrix.m21 = 0;
     matrix.m22 = 1;
  }
}
