import static org.junit.Assert.assertEquals;

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
		RotationMatrixf matrix2 = new RotationMatrixf();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomRotationMatrix(matrix, random);
			axisAngle.set(matrix);
			matrix2.set(axisAngle);
			assertMatrixEquals(matrix, matrix2, 1e-4);
		}
	}
	
	@Test
	public void testSetFromRotationMatrix2()
	{
		AxisAnglef axisAngle1 = new AxisAnglef();
		AxisAnglef axisAngle2 = new AxisAnglef();
		RotationMatrixf matrix = new RotationMatrixf();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomAxisAngle(random, axisAngle1);
			matrix.set(axisAngle1);
			axisAngle2.set(matrix);
			assertAxisAnglefEquals(axisAngle1, axisAngle2, 1e-4);
		}
	}
	
	@Test
	public void tesSetFromQuaternion1()
	{
		Quaternionf quaternion = new Quaternionf();
		AxisAnglef axisAngle1 = new AxisAnglef();
		AxisAnglef axisAngle2 = new AxisAnglef();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomAxisAngle(random, axisAngle1);
			quaternion.set(axisAngle1);
			axisAngle2.set(quaternion);
			
			assertAxisAnglefEquals(axisAngle1, axisAngle2, 1e-4);
		}
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
		Matrix3f rotX = new Matrix3f();
		Matrix3f rotY = new Matrix3f();
		Matrix3f rotZ = new Matrix3f();

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

	private void createRandomRotationMatrixX(Random random, Matrix3f matrix)
	{
		float theta = random.nextFloat();
		float cTheta = (float)Math.cos(theta);
		float sTheta = (float)Math.sin(theta);
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

	private void createRandomRotationMatrixY(Random random, Matrix3f matrix)
	{
		float theta = random.nextFloat();
		float cTheta = (float)Math.cos(theta);
		float sTheta = (float)Math.sin(theta);
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
		float mag = (float)(Math.sqrt(axisAngle.x*axisAngle.x + axisAngle.y*axisAngle.y + axisAngle.z*axisAngle.z));
		
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
		
		float val = (float)(1/(Math.sqrt(q.x*q.x + q.y*q.y + q.z*q.z + q.w*q.w)));
		
		q.x *= val;
		q.y *= val;
		q.z *= val;
		q.w *= val;
	}
	
  private void createRandomRotationMatrixZ(Random random, Matrix3f matrix)
  {
     float theta = random.nextFloat();
     float cTheta = (float)Math.cos(theta);
     float sTheta = (float)Math.sin(theta);
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
