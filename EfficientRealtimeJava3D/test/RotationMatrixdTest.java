import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class RotationMatrixdTest
{
	Random random = new Random();
	int nTests = 10;

	public void setUp()
	{

	}

	public void tearDown()
	{
		System.gc();
	}

	@Test
	public void testRotX()
	{
		RotationMatrixd matrix = new RotationMatrixd();

		for (int i = 0; i < nTests; i++)
		{
			double theta = random.nextDouble() * 10 - 5;
			matrix.rotX(theta);

			assertEquals(matrix.m00, 1, 1e-15);
			assertEquals(matrix.m01, 0, 1e-15);
			assertEquals(matrix.m02, 0, 1e-15);
			assertEquals(matrix.m10, 0, 1e-15);
			assertEquals(matrix.m20, 0, 1e-15);
			assertEquals(matrix.m11, Math.cos(theta), 1e-15);
			assertEquals(matrix.m22, Math.cos(theta), 1e-15);
			assertEquals(matrix.m12, -Math.sin(theta), 1e-15);
			assertEquals(matrix.m21, Math.sin(theta), 1e-15);
		}
	}

	@Test
	public void testRotY()
	{
		RotationMatrixd matrix = new RotationMatrixd();

		for (int i = 0; i < nTests; i++)
		{
			double theta = random.nextDouble() * 10 - 5;
			matrix.rotY(theta);

			assertEquals(matrix.m11, 1, 1e-15);
			assertEquals(matrix.m01, 0, 1e-15);
			assertEquals(matrix.m12, 0, 1e-15);
			assertEquals(matrix.m10, 0, 1e-15);
			assertEquals(matrix.m21, 0, 1e-15);
			assertEquals(matrix.m00, Math.cos(theta), 1e-15);
			assertEquals(matrix.m22, Math.cos(theta), 1e-15);
			assertEquals(matrix.m20, -Math.sin(theta), 1e-15);
			assertEquals(matrix.m02, Math.sin(theta), 1e-15);
		}
	}

	@Test
	public void testRotZ()
	{
		RotationMatrixd matrix = new RotationMatrixd();

		for (int i = 0; i < nTests; i++)
		{
			double theta = random.nextDouble() * 10 - 5;
			matrix.rotZ(theta);

			assertEquals(matrix.m22, 1, 1e-15);
			assertEquals(matrix.m02, 0, 1e-15);
			assertEquals(matrix.m12, 0, 1e-15);
			assertEquals(matrix.m20, 0, 1e-15);
			assertEquals(matrix.m21, 0, 1e-15);
			assertEquals(matrix.m00, Math.cos(theta), 1e-15);
			assertEquals(matrix.m11, Math.cos(theta), 1e-15);
			assertEquals(matrix.m01, -Math.sin(theta), 1e-15);
			assertEquals(matrix.m10, Math.sin(theta), 1e-15);
		}
	}

	@Test
	public void testInvert()
	{
		RotationMatrixd matrix1 = new RotationMatrixd();
		RotationMatrixd matrix2 = new RotationMatrixd();
		RotationMatrixd matrix3 = new RotationMatrixd();

		for (int i = 0; i < nTests; i++)
		{
			matrix2.rotZ(random.nextDouble());
			matrix3.set(matrix2);
			matrix2.invert();

			matrix2.multiply(matrix3);

			assertMatrix3dEquals(matrix2, matrix1, 1e-15);
		}
	}

	@Test
	public void testNormalize()
	{
		RotationMatrixd matrix = new RotationMatrixd();
		Vector3d vector = new Vector3d();
		Vector3d vector2 = new Vector3d();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix3d(random, matrix);
			matrix.normalize();

			matrix.getColumn(1, vector);
			assertEquals(vector.length(), 1.0, 1e-5);

			matrix.getColumn(2, vector);
			assertEquals(vector.length(), 1.0, 1e-12);

			matrix.getColumn(3, vector);
			assertEquals(vector.length(), 1.0, 1e-12);

			matrix.getColumn(1, vector);
			matrix.getColumn(2, vector2);
			assertEquals(vector.dot(vector2), 0, 1e-12);

			matrix.getColumn(3, vector2);
			assertEquals(vector.dot(vector2), 0, 1e-12);

		}
	}

	@Test
	public void testNormalize2()
	{
		RotationMatrixd matrix1 = new RotationMatrixd();
		RotationMatrixd matrix2 = new RotationMatrixd();

		for (int i = 0; i < nTests; i++)
		{
			matrix1.setToIdentity();
			for(int j = 0; j<100; j++)
			{
				createRandomRotationMatrix(matrix2, random);
				matrix1.multiply(matrix2);
			}
			matrix1.scale(1+1e-3); //Perturb rotation matrix elements
			assertFalse(isRotationMatrixProper(matrix1));
			matrix1.normalize();
			assertTrue(isRotationMatrixProper(matrix1));
		}
	}

	private void randomizeMatrix3d(Random random, Matrix3d matrix)
	{
		matrix.m00 = random.nextDouble();
		matrix.m01 = random.nextDouble();
		matrix.m02 = random.nextDouble();
		matrix.m10 = random.nextDouble();
		matrix.m11 = random.nextDouble();
		matrix.m12 = random.nextDouble();
		matrix.m20 = random.nextDouble();
		matrix.m21 = random.nextDouble();
		matrix.m22 = random.nextDouble();
	}

	private void assertMatrix3dEquals(Matrix3d matrix1, Matrix3d matrix2,
			double epsilon)
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

	private boolean isRotationMatrixProper(RotationMatrixd matrix)
	{
		double epsilon = 1e-10;
		double determinant = matrix.determinant();
		Matrix3d mat1 = new Matrix3d();

		mat1.multiplyTransposeRight(matrix, matrix);
		boolean ret = isIdentity(mat1, epsilon)
				&& Math.abs(determinant - 1) < epsilon;

		return ret;
	}

	private boolean isIdentity(Matrix3d matrix, double epsilon)
	{
		return Math.abs(matrix.m00 - 1) < epsilon
				&& Math.abs(matrix.m11 - 1) < epsilon
				&& Math.abs(matrix.m22 - 1) < epsilon
				&& Math.abs(matrix.m10) < epsilon
				&& Math.abs(matrix.m20) < epsilon
				&& Math.abs(matrix.m01) < epsilon
				&& Math.abs(matrix.m02) < epsilon
				&& Math.abs(matrix.m12) < epsilon
				&& Math.abs(matrix.m21) < epsilon;
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
