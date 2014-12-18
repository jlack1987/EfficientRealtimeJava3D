import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class AxisAnglefTest
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
		RotationMatrixf matrix = new RotationMatrixf();
		AxisAnglef axisAngle = new AxisAnglef();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			axisAngle.set(matrix);
			RotationMatrixf matrix2 = new RotationMatrixf(axisAngle);
			assertMatrixEquals(matrix, matrix2, 1e-5f);
		}
	}
	
	@Test
	public void testSetFromRotationMatrix2()
	{
		AxisAnglef axisAngle1 = new AxisAnglef();
		AxisAnglef axisAngle2 = new AxisAnglef();
		axisAngle2.toString();
		RotationMatrixf matrix = new RotationMatrixf();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomAxisAngle(random, axisAngle1);
			matrix.set(axisAngle1);
			axisAngle2.set(matrix);
			assertAxisAnglefEquals(axisAngle1, axisAngle2, 1e-5f);
		}
	}
	
	@Test
	public void testConstructors()
	{
		AxisAnglef A = new AxisAnglef();
		float angle = (float)Math.PI/4;
		A.x = (float)Math.cos(angle);
		A.y = (float)Math.sin(angle);
		A.z = 0;
		A.angle = angle;
		AxisAnglef A2 = new AxisAnglef(A);
		A2.get(A);
		assertEquals(A.x,A2.x,1e-12);
		assertEquals(A.y,A2.y,1e-12);
		assertEquals(A.z,A2.z,1e-12);
		assertEquals(A.angle,A2.angle,1e-12);
	}
	
	@Test
	public void testGetAndSetFromAxisAndAngle()
	{
		Vector3f V = new Vector3f();
		V.set(random.nextFloat(),random.nextFloat(),random.nextFloat());
		float angle = random.nextFloat();
		
		AxisAnglef A = new AxisAnglef(V,angle);
		float[] D = new float[4];
		A.get(D);
		assertEquals(D[0],A.x,1e-12);
		assertEquals(D[1],A.y,1e-12);
		assertEquals(D[2],A.z,1e-12);
		assertEquals(D[3],A.angle,1e-12);
	}
	
	@Test
	public void testSetFromQuaternion1()
	{
		Quaternionf quaternion = new Quaternionf();
		AxisAnglef axisAngle1 = new AxisAnglef();
		AxisAnglef axisAngle2 = new AxisAnglef();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomAxisAngle(random, axisAngle1);
			quaternion.set(axisAngle1);
			axisAngle2.set(quaternion);
			
			assertAxisAnglefEquals(axisAngle1, axisAngle2, 1e-5f);
		}
	}
	
	@Test
	public void testSetFromZeroQuaternion()
	{
		Quaternionf Q = new Quaternionf();
		Q.set(0,0,0,0);
		
		AxisAnglef A = new AxisAnglef(Q);
		A.toString();
		assertEquals(A.x,0,1e-12);
		assertEquals(A.y,1,1e-12);
		assertEquals(A.z,0,1e-12);
		assertEquals(A.angle,0,1e-12);
	}
	
	@Test
	public void testEquals()
	{
		float[] D = new float[4];
		D[0] = 0;
		D[1] = 0;
		D[2] = 0;
		D[3] = 0;
		
		AxisAnglef A = new AxisAnglef(D);
		AxisAnglef A2 = new AxisAnglef(D);
		assertTrue(A.equals(A2));
		
		D[1] = 1e-7f;
		
		A.set(D);
		assertFalse(A.equals(A2));
		assertTrue(A.epsilonEquals(A2, 1e-5));
	}
	
	@Test
	public void testSetFromIdentityRotation()
	{
		RotationMatrixf R = new RotationMatrixf();
		R.setToIdentity();
		
		AxisAnglef A = new AxisAnglef(R);
		
		Vector3f V = new Vector3f();
		A.getAxis(V);
		double angle = A.getAngle();
		assertEquals(V.x,0,1e-12);
		assertEquals(V.y,1,1e-12);
		assertEquals(V.z,0,1e-12);
		assertEquals(angle,0,1e-12);
	}
	
	@SuppressWarnings("unused")
	private void assertQuaternionfEquals(Quaternionf q1, Quaternionf q2, double epsilon)
	{
		assertEquals(q1.x,q2.x,epsilon);
		assertEquals(q1.y,q2.y,epsilon);
		assertEquals(q1.z,q2.z,epsilon);
		assertEquals(q1.w,q2.w,epsilon);
	}
	
	private void assertAxisAnglefEquals(AxisAnglef axisAngle1, AxisAnglef axisAngle2, double epsilon)
	{
		assertEquals(axisAngle1.x,axisAngle2.x,epsilon);
		assertEquals(axisAngle1.y,axisAngle2.y,epsilon);
		assertEquals(axisAngle2.z,axisAngle2.z,epsilon);
		assertEquals(axisAngle1.angle,axisAngle2.angle,epsilon);
	}
	
	private void assertMatrixEquals(Matrix3f matrix1, Matrix3f matrix2, double epsilon)
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
	
	private void createRandomRotationMatrix(Matrix3f matrix, Random random)
	{
		Matrix3d rotX = new Matrix3d();
		Matrix3d rotY = new Matrix3d();
		Matrix3d rotZ = new Matrix3d();

		createRandomRotationMatrixX(random, rotX);
		createRandomRotationMatrixY(random, rotY);
		createRandomRotationMatrixZ(random, rotZ);

		rotX.multiply(rotY);
		rotX.multiply(rotZ);

		matrix.m00 = (float)rotX.m00;
		matrix.m01 = (float)rotX.m01;
		matrix.m02 = (float)rotX.m02;
		matrix.m10 = (float)rotX.m10;
		matrix.m11 = (float)rotX.m11;
		matrix.m12 = (float)rotX.m12;
		matrix.m20 = (float)rotX.m20;
		matrix.m21 = (float)rotX.m21;
		matrix.m22 = (float)rotX.m22;
	}

	private void createRandomRotationMatrixX(Random random, Matrix3d matrix)
	{
		double theta = random.nextFloat();
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
		double theta = random.nextFloat();
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
	
	private void createRandomAxisAngle(Random random, AxisAnglef axisAngle)
	{
		axisAngle.x = random.nextFloat();
		axisAngle.y = random.nextFloat();
		axisAngle.z = random.nextFloat();
		double mag = Math.sqrt(axisAngle.x*axisAngle.x + axisAngle.y*axisAngle.y + axisAngle.z*axisAngle.z);
		
		axisAngle.x *= 1/mag;
		axisAngle.y *= 1/mag;
		axisAngle.z *= 1/mag;
		
		axisAngle.angle = random.nextFloat()*(float)Math.PI;
	}
	
	
	@SuppressWarnings("unused")
	private void createRandomQuaternion(Random random, Quaternionf q)
	{
		q.x = random.nextFloat();
		q.y = random.nextFloat();
		q.z = random.nextFloat();
		q.w = random.nextFloat();
		
		double val = 1/(Math.sqrt(q.x*q.x + q.y*q.y + q.z*q.z + q.w*q.w));
		
		q.x *= val;
		q.y *= val;
		q.z *= val;
		q.w *= val;
	}
	
  private void createRandomRotationMatrixZ(Random random, Matrix3d matrix)
  {
     double theta = random.nextFloat();
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
