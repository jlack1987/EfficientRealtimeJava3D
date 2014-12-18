import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Matrix3fTest
{
	Random random = new Random();
	int nTests = 10;

	@Before()
	public void setUp()
	{

	}

	@After
	public void tearDown()
	{
		System.gc();
	}

	@Test
	public void testMatrix3fEmptyConstructor()
	{
		Matrix3f matrix = new Matrix3f();
		matrix.toString();
		int iter = 0;
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				assertEquals(0.0, matrix.get(i, j), 1e-12);
				iter++;
			}
		}
	}

	@Test
	public void testCreateFromFloatArray()
	{
		float[] F = new float[9];
		randomizeFloatArray(random, F);

		Matrix3f matrix = new Matrix3f(F);
		Matrix3f M2 = new Matrix3f();
		matrix.get(M2);
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				assertEquals(M2.get(i, j), matrix.get(i, j), 1e-12);
			}
		}
	}

	@Test
	public void testMatrix3fConstructor2()
	{
		for (int i = 0; i < nTests; i++)
		{
			double[] array = new double[9];
			Matrix3f matrix = new Matrix3f();
			randomizeMatrix(random, matrix);

			matrix.get(array);
			int iter = 0;
			for (int k = 0; k < 3; k++)
			{
				for (int j = 0; j < 3; j++)
				{
					assertEquals(matrix.get(k, j), array[iter], 1e-12);
					iter++;
				}
			}
		}
	}
	
	@Test
	public void testGetAsFloatArray()
	{
		Matrix3f M = new Matrix3f();
		float[] F = new float[9];
		randomizeMatrix(random, M);
		M.get(F);
		
		int iter = 0;
		for(int i = 0; i<3; i++)
		{
			for(int j = 0; j<3; j++)
			{
				assertEquals(M.get(i,j),F[iter],1e-5f);
				iter++;
			}
		}
	}

	@Test
	public void testConstructor3()
	{
		double[] doubleArray = new double[9];

		for (int i = 0; i < nTests; i++)
		{
			randomizeDoubleArray(random, doubleArray);

			Matrix3f matrix = new Matrix3f(doubleArray);

			assertEquals(doubleArray[0], matrix.m00, 1e-5f);
			assertEquals(doubleArray[1], matrix.m01, 1e-5f);
			assertEquals(doubleArray[2], matrix.m02, 1e-5f);
			assertEquals(doubleArray[3], matrix.m10, 1e-5f);
			assertEquals(doubleArray[4], matrix.m11, 1e-5f);
			assertEquals(doubleArray[5], matrix.m12, 1e-5f);
			assertEquals(doubleArray[6], matrix.m20, 1e-5f);
			assertEquals(doubleArray[7], matrix.m21, 1e-5f);
			assertEquals(doubleArray[8], matrix.m22, 1e-5f);
		}
	}

	@Test
	public void testCreateWithMatrix3f()
	{
		Matrix3f matrix = new Matrix3f();
		for (int n = 0; n < nTests; n++)
		{
			randomizeMatrix(random, matrix);
			Matrix3f M = new Matrix3f(matrix);

			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					assertEquals(matrix.get(i, j), M.get(i, j), 1e-12);
				}
			}
		}
	}

	@Test
	public void testSet()
	{
		Matrix3f matrix = new Matrix3f();
		double[] array = new double[9];

		for (int i = 0; i < nTests; i++)
		{
			randomizeDoubleArray(random, array);
			matrix.set(array);

			assertEquals(matrix.m00, array[0], 1e-5f);
			assertEquals(matrix.m01, array[1], 1e-5f);
			assertEquals(matrix.m02, array[2], 1e-5f);
			assertEquals(matrix.m10, array[3], 1e-5f);
			assertEquals(matrix.m11, array[4], 1e-5f);
			assertEquals(matrix.m12, array[5], 1e-5f);
			assertEquals(matrix.m20, array[6], 1e-5f);
			assertEquals(matrix.m21, array[7], 1e-5f);
			assertEquals(matrix.m22, array[8], 1e-5f);
		}
	}

	@Test
	public void testSet2()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			matrix2.set(matrix);

			assertEquals(matrix.m00, matrix2.m00, 1e-10);
			assertEquals(matrix.m01, matrix2.m01, 1e-10);
			assertEquals(matrix.m02, matrix2.m02, 1e-10);
			assertEquals(matrix.m10, matrix2.m10, 1e-10);
			assertEquals(matrix.m11, matrix2.m11, 1e-10);
			assertEquals(matrix.m12, matrix2.m12, 1e-10);
			assertEquals(matrix.m20, matrix2.m20, 1e-10);
			assertEquals(matrix.m21, matrix2.m21, 1e-10);
			assertEquals(matrix.m22, matrix2.m22, 1e-10);
		}
	}

	@Test
	public void testSetToIdentity()
	{
		for (int i = 0; i < nTests; i++)
		{
			Matrix3f matrix = new Matrix3f();
			randomizeMatrix(random, matrix);

			matrix.setToIdentity();

			assertEquals(1, matrix.m00, 1e-12);
			assertEquals(0, matrix.m01, 1e-12);
			assertEquals(0, matrix.m02, 1e-12);
			assertEquals(0, matrix.m10, 1e-12);
			assertEquals(1, matrix.m11, 1e-12);
			assertEquals(0, matrix.m12, 1e-12);
			assertEquals(0, matrix.m20, 1e-12);
			assertEquals(0, matrix.m21, 1e-12);
			assertEquals(1, matrix.m22, 1e-12);
		}
	}

	@Test
	public void testSetToZero()
	{
		for (int i = 0; i < nTests; i++)
		{
			Matrix3f matrix = new Matrix3f();
			randomizeMatrix(random, matrix);

			matrix.setToZero();

			assertEquals(0, matrix.m00, 1e-12);
			assertEquals(0, matrix.m01, 1e-12);
			assertEquals(0, matrix.m02, 1e-12);
			assertEquals(0, matrix.m10, 1e-12);
			assertEquals(0, matrix.m11, 1e-12);
			assertEquals(0, matrix.m12, 1e-12);
			assertEquals(0, matrix.m20, 1e-12);
			assertEquals(0, matrix.m21, 1e-12);
			assertEquals(0, matrix.m22, 1e-12);
		}
	}

	@Test
	public void testAdd()
	{
		Matrix3f matrix1 = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix1);
			randomizeMatrix(random, matrix2);

			double t00 = matrix1.m00 + matrix2.m00;
			double t01 = matrix1.m01 + matrix2.m01;
			double t02 = matrix1.m02 + matrix2.m02;
			double t10 = matrix1.m10 + matrix2.m10;
			double t11 = matrix1.m11 + matrix2.m11;
			double t12 = matrix1.m12 + matrix2.m12;
			double t20 = matrix1.m20 + matrix2.m20;
			double t21 = matrix1.m21 + matrix2.m21;
			double t22 = matrix1.m22 + matrix2.m22;

			matrix1.add(matrix2);

			assertEquals(t00, matrix1.m00, 1e-10);
			assertEquals(t01, matrix1.m01, 1e-10);
			assertEquals(t02, matrix1.m02, 1e-10);
			assertEquals(t10, matrix1.m10, 1e-10);
			assertEquals(t11, matrix1.m11, 1e-10);
			assertEquals(t12, matrix1.m12, 1e-10);
			assertEquals(t20, matrix1.m20, 1e-10);
			assertEquals(t21, matrix1.m21, 1e-10);
			assertEquals(t22, matrix1.m22, 1e-10);
		}
	}

	@Test
	public void testAdd2()
	{
		Matrix3f matrix1 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix1);

			double t00 = matrix1.m00 + matrix1.m00;
			double t01 = matrix1.m01 + matrix1.m01;
			double t02 = matrix1.m02 + matrix1.m02;
			double t10 = matrix1.m10 + matrix1.m10;
			double t11 = matrix1.m11 + matrix1.m11;
			double t12 = matrix1.m12 + matrix1.m12;
			double t20 = matrix1.m20 + matrix1.m20;
			double t21 = matrix1.m21 + matrix1.m21;
			double t22 = matrix1.m22 + matrix1.m22;

			matrix1.add(matrix1);

			assertEquals(t00, matrix1.m00, 1e-10);
			assertEquals(t01, matrix1.m01, 1e-10);
			assertEquals(t02, matrix1.m02, 1e-10);
			assertEquals(t10, matrix1.m10, 1e-10);
			assertEquals(t11, matrix1.m11, 1e-10);
			assertEquals(t12, matrix1.m12, 1e-10);
			assertEquals(t20, matrix1.m20, 1e-10);
			assertEquals(t21, matrix1.m21, 1e-10);
			assertEquals(t22, matrix1.m22, 1e-10);
		}
	}

	@Test
	public void testAdd3()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix1 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			float scalar = random.nextFloat();
			randomizeMatrix(random, matrix);

			matrix1.set(matrix);

			matrix.add(scalar);

			assertEquals(matrix1.m00 + scalar, matrix.m00, 1e-10);
			assertEquals(matrix1.m01 + scalar, matrix.m01, 1e-10);
			assertEquals(matrix1.m02 + scalar, matrix.m02, 1e-10);
			assertEquals(matrix1.m10 + scalar, matrix.m10, 1e-10);
			assertEquals(matrix1.m11 + scalar, matrix.m11, 1e-10);
			assertEquals(matrix1.m12 + scalar, matrix.m12, 1e-10);
			assertEquals(matrix1.m20 + scalar, matrix.m20, 1e-10);
			assertEquals(matrix1.m21 + scalar, matrix.m21, 1e-10);
			assertEquals(matrix1.m22 + scalar, matrix.m22, 1e-10);
		}
	}

	@Test
	public void testAdd4()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix1 = new Matrix3f();
		for (int i = 0; i < nTests; i++)
		{
			float scalar = random.nextFloat();
			randomizeMatrix(random, matrix);

			matrix1.add(matrix, scalar);

			assertEquals(matrix1.m00, matrix.m00 + scalar, 1e-10);
			assertEquals(matrix1.m01, matrix.m01 + scalar, 1e-10);
			assertEquals(matrix1.m02, matrix.m02 + scalar, 1e-10);
			assertEquals(matrix1.m10, matrix.m10 + scalar, 1e-10);
			assertEquals(matrix1.m11, matrix.m11 + scalar, 1e-10);
			assertEquals(matrix1.m12, matrix.m12 + scalar, 1e-10);
			assertEquals(matrix1.m20, matrix.m20 + scalar, 1e-10);
			assertEquals(matrix1.m21, matrix.m21 + scalar, 1e-10);
			assertEquals(matrix1.m22, matrix.m22 + scalar, 1e-10);
		}
	}

	@Test
	public void testAdd5()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix1 = new Matrix3f();
		for (int i = 0; i < nTests; i++)
		{
			float scalar = random.nextFloat();
			randomizeMatrix(random, matrix);
			matrix1.set(matrix);

			matrix.add(matrix, scalar);

			assertEquals(matrix.m00, matrix1.m00 + scalar, 1e-10);
			assertEquals(matrix.m01, matrix1.m01 + scalar, 1e-10);
			assertEquals(matrix.m02, matrix1.m02 + scalar, 1e-10);
			assertEquals(matrix.m10, matrix1.m10 + scalar, 1e-10);
			assertEquals(matrix.m11, matrix1.m11 + scalar, 1e-10);
			assertEquals(matrix.m12, matrix1.m12 + scalar, 1e-10);
			assertEquals(matrix.m20, matrix1.m20 + scalar, 1e-10);
			assertEquals(matrix.m21, matrix1.m21 + scalar, 1e-10);
			assertEquals(matrix.m22, matrix1.m22 + scalar, 1e-10);
		}
	}

	@Test
	public void testAdd6()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix1 = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			randomizeMatrix(random, matrix1);
			matrix2.set(matrix);

			matrix.add(matrix, matrix1);

			assertEquals(matrix.m00, matrix1.m00 + matrix2.m00, 1e-10);
			assertEquals(matrix.m01, matrix1.m01 + matrix2.m01, 1e-10);
			assertEquals(matrix.m02, matrix1.m02 + matrix2.m02, 1e-10);
			assertEquals(matrix.m10, matrix1.m10 + matrix2.m10, 1e-10);
			assertEquals(matrix.m11, matrix1.m11 + matrix2.m11, 1e-10);
			assertEquals(matrix.m12, matrix1.m12 + matrix2.m12, 1e-10);
			assertEquals(matrix.m20, matrix1.m20 + matrix2.m20, 1e-10);
			assertEquals(matrix.m21, matrix1.m21 + matrix2.m21, 1e-10);
			assertEquals(matrix.m22, matrix1.m22 + matrix2.m22, 1e-10);
		}
	}

	@Test
	public void testAdd7()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix1 = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			randomizeMatrix(random, matrix1);
			matrix2.set(matrix);

			matrix1.add(matrix, matrix);

			assertEquals(matrix1.m00, matrix2.m00 + matrix2.m00, 1e-10);
			assertEquals(matrix1.m01, matrix2.m01 + matrix2.m01, 1e-10);
			assertEquals(matrix1.m02, matrix2.m02 + matrix2.m02, 1e-10);
			assertEquals(matrix1.m10, matrix2.m10 + matrix2.m10, 1e-10);
			assertEquals(matrix1.m11, matrix2.m11 + matrix2.m11, 1e-10);
			assertEquals(matrix1.m12, matrix2.m12 + matrix2.m12, 1e-10);
			assertEquals(matrix1.m20, matrix2.m20 + matrix2.m20, 1e-10);
			assertEquals(matrix1.m21, matrix2.m21 + matrix2.m21, 1e-10);
			assertEquals(matrix1.m22, matrix2.m22 + matrix2.m22, 1e-10);
		}
	}

	@Test
	public void testSubtract()
	{
		Matrix3f matrix1 = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();
		Matrix3f matrix3 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix1);
			randomizeMatrix(random, matrix2);

			matrix3.set(matrix1);

			matrix1.subtract(matrix2);

			assertEquals(matrix1.m00, -matrix2.m00 + matrix3.m00, 1e-10);
			assertEquals(matrix1.m01, -matrix2.m01 + matrix3.m01, 1e-10);
			assertEquals(matrix1.m02, -matrix2.m02 + matrix3.m02, 1e-10);
			assertEquals(matrix1.m10, -matrix2.m10 + matrix3.m10, 1e-10);
			assertEquals(matrix1.m11, -matrix2.m11 + matrix3.m11, 1e-10);
			assertEquals(matrix1.m12, -matrix2.m12 + matrix3.m12, 1e-10);
			assertEquals(matrix1.m20, -matrix2.m20 + matrix3.m20, 1e-10);
			assertEquals(matrix1.m21, -matrix2.m21 + matrix3.m21, 1e-10);
			assertEquals(matrix1.m22, -matrix2.m22 + matrix3.m22, 1e-10);
		}
	}

	@Test
	public void testSubtract2()
	{
		Matrix3f matrix1 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix1);

			matrix1.subtract(matrix1);

			assertEquals(matrix1.m00, 0, 1e-10);
			assertEquals(matrix1.m01, 0, 1e-10);
			assertEquals(matrix1.m02, 0, 1e-10);
			assertEquals(matrix1.m10, 0, 1e-10);
			assertEquals(matrix1.m11, 0, 1e-10);
			assertEquals(matrix1.m12, 0, 1e-10);
			assertEquals(matrix1.m20, 0, 1e-10);
			assertEquals(matrix1.m21, 0, 1e-10);
			assertEquals(matrix1.m22, 0, 1e-10);
		}
	}

	@Test
	public void testSubtract3()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix1 = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			randomizeMatrix(random, matrix1);
			matrix2.set(matrix);

			matrix.subtract(matrix, matrix1);

			assertEquals(matrix.m00, matrix2.m00 - matrix1.m00, 1e-10);
			assertEquals(matrix.m01, matrix2.m01 - matrix1.m01, 1e-10);
			assertEquals(matrix.m02, matrix2.m02 - matrix1.m02, 1e-10);
			assertEquals(matrix.m10, matrix2.m10 - matrix1.m10, 1e-10);
			assertEquals(matrix.m11, matrix2.m11 - matrix1.m11, 1e-10);
			assertEquals(matrix.m12, matrix2.m12 - matrix1.m12, 1e-10);
			assertEquals(matrix.m20, matrix2.m20 - matrix1.m20, 1e-10);
			assertEquals(matrix.m21, matrix2.m21 - matrix1.m21, 1e-10);
			assertEquals(matrix.m22, matrix2.m22 - matrix1.m22, 1e-10);
		}
	}

	@Test
	public void testSubtract4()
	{
		Matrix3f M = new Matrix3f();
		randomizeMatrix(random, M);
		Matrix3f M2 = new Matrix3f();
		float scalar = random.nextFloat();
		M2.subtract(M, scalar);

		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				assertEquals(M2.get(i, j), M.get(i, j) - scalar, 1e-12);
			}
		}
	}

	@Test
	public void testScale()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			float scale = random.nextFloat();
			matrix2.set(matrix);

			matrix.scale(scale);

			assertEquals(matrix2.m00 * scale, matrix.m00, 1e-10);
			assertEquals(matrix2.m01 * scale, matrix.m01, 1e-10);
			assertEquals(matrix2.m02 * scale, matrix.m02, 1e-10);
			assertEquals(matrix2.m10 * scale, matrix.m10, 1e-10);
			assertEquals(matrix2.m11 * scale, matrix.m11, 1e-10);
			assertEquals(matrix2.m12 * scale, matrix.m12, 1e-10);
			assertEquals(matrix2.m20 * scale, matrix.m20, 1e-10);
			assertEquals(matrix2.m21 * scale, matrix.m21, 1e-10);
			assertEquals(matrix2.m22 * scale, matrix.m22, 1e-10);
		}
	}

	@Test
	public void testTranspose()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			matrix2.set(matrix);

			matrix.transpose();

			assertEquals(matrix.m00, matrix2.m00, 1e-10);
			assertEquals(matrix.m01, matrix2.m10, 1e-10);
			assertEquals(matrix.m02, matrix2.m20, 1e-10);
			assertEquals(matrix.m11, matrix2.m11, 1e-10);
			assertEquals(matrix.m12, matrix2.m21, 1e-10);
			assertEquals(matrix.m22, matrix2.m22, 1e-10);
		}
	}

	@Test
	public void testTranspose2()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			matrix2.set(matrix);

			matrix.transpose(matrix);
			matrix2.transpose();

			assertMatrixEquals(matrix, matrix2, 1e-10);
		}
	}

	@Test
	public void testDeterminant1()
	{
		Matrix3f matrix = new Matrix3f();

		assertEquals(matrix.determinant(), 0.0, 1e-12);

		matrix.setToIdentity();
		assertEquals(matrix.determinant(), 1.0, 1e-12);

		matrix.m00 = 1;
		matrix.m01 = 2;
		matrix.m02 = 3;
		matrix.m10 = 0;
		matrix.m11 = -4;
		matrix.m12 = 1;
		matrix.m20 = 0;
		matrix.m21 = 3;
		matrix.m22 = -1;

		assertEquals(matrix.determinant(), 1.0, 1e-12);
	}

	@Test
	public void testGetColumn()
	{
		Matrix3f matrix = new Matrix3f();
		Vector3f vector = new Vector3f();
		Vector3f vector2 = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			vector.set(matrix.m00, matrix.m10, matrix.m20);

			matrix.getColumn(1, vector2);
			assertVectorEquals(vector, vector2, 1e-10);

			vector.set(matrix.m01, matrix.m11, matrix.m21);
			matrix.getColumn(2, vector2);
			assertVectorEquals(vector, vector2, 1e-10);

			vector.set(matrix.m02, matrix.m12, matrix.m22);
			matrix.getColumn(3, vector2);
			assertVectorEquals(vector, vector2, 1e-10);
		}
	}

	@Test
	public void testGetColumn2()
	{
		Matrix3f matrix = new Matrix3f();
		float[] F = new float[3];
		float[] F2 = new float[3];

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			F[0] = matrix.m00;
			F[1] = matrix.m10;
			F[2] = matrix.m20;

			matrix.getColumn(1, F2);
			assertFloatArrayEquals(F, F2, 1e-5f);

			F[0] = matrix.m01;
			F[1] = matrix.m11;
			F[2] = matrix.m21;
			matrix.getColumn(2, F2);
			assertFloatArrayEquals(F, F2, 1e-5f);

			F[0] = matrix.m02;
			F[1] = matrix.m12;
			F[2] = matrix.m22;
			matrix.getColumn(3, F2);
			assertFloatArrayEquals(F, F2, 1e-5f);
		}
	}

	@Test
	public void testGetRow()
	{
		Matrix3f matrix = new Matrix3f();
		Vector3f vector = new Vector3f();
		Vector3f vector2 = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			vector.set(matrix.m00, matrix.m01, matrix.m02);

			matrix.getRow(1, vector2);
			assertVectorEquals(vector, vector2, 1e-10);

			vector.set(matrix.m10, matrix.m11, matrix.m12);
			matrix.getRow(2, vector2);
			assertVectorEquals(vector, vector2, 1e-10);

			vector.set(matrix.m20, matrix.m21, matrix.m22);
			matrix.getRow(3, vector2);
			assertVectorEquals(vector, vector2, 1e-10);
		}
	}

	@Test
	public void testGetRow2()
	{
		Matrix3f matrix = new Matrix3f();
		float[] F = new float[3];
		float[] F2 = new float[3];

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			F[0] = matrix.m00;
			F[1] = matrix.m01;
			F[2] = matrix.m02;

			matrix.getRow(1, F2);
			assertFloatArrayEquals(F, F2, 1e-5f);

			F[0] = matrix.m10;
			F[1] = matrix.m11;
			F[2] = matrix.m12;
			matrix.getRow(2, F2);
			assertFloatArrayEquals(F, F2, 1e-5f);

			F[0] = matrix.m20;
			F[1] = matrix.m21;
			F[2] = matrix.m22;
			matrix.getRow(3, F2);
			assertFloatArrayEquals(F, F2, 1e-5f);
		}
	}

	@Test
	public void testSetRow()
	{
		Matrix3f matrix = new Matrix3f();
		float[] vector = new float[3];

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			vector[0] = random.nextFloat();
			vector[1] = random.nextFloat();
			vector[2] = random.nextFloat();
			matrix.setRow(1, vector);
			matrix.setRow(2, vector);
			matrix.setRow(3, vector);

			assertEquals(matrix.m00, vector[0], 1e-10);
			assertEquals(matrix.m10, vector[0], 1e-10);
			assertEquals(matrix.m20, vector[0], 1e-10);
			assertEquals(matrix.m01, vector[1], 1e-10);
			assertEquals(matrix.m11, vector[1], 1e-10);
			assertEquals(matrix.m21, vector[1], 1e-10);
			assertEquals(matrix.m02, vector[2], 1e-10);
			assertEquals(matrix.m12, vector[2], 1e-10);
			assertEquals(matrix.m22, vector[2], 1e-10);
		}
	}

	@Test
	public void testSetRow2()
	{
		Matrix3f matrix = new Matrix3f();
		Vector3f vector = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			vector.x = random.nextFloat();
			vector.y = random.nextFloat();
			vector.z = random.nextFloat();
			matrix.setRow(1, vector);
			matrix.setRow(2, vector);
			matrix.setRow(3, vector);

			assertEquals(matrix.m00, vector.x, 1e-10);
			assertEquals(matrix.m10, vector.x, 1e-10);
			assertEquals(matrix.m20, vector.x, 1e-10);
			assertEquals(matrix.m01, vector.y, 1e-10);
			assertEquals(matrix.m11, vector.y, 1e-10);
			assertEquals(matrix.m21, vector.y, 1e-10);
			assertEquals(matrix.m02, vector.z, 1e-10);
			assertEquals(matrix.m12, vector.z, 1e-10);
			assertEquals(matrix.m22, vector.z, 1e-10);
		}
	}

	@Test
	public void testSetColumn()
	{
		Matrix3f matrix = new Matrix3f();
		float[] vector = new float[3];

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			vector[0] = random.nextFloat();
			vector[1] = random.nextFloat();
			vector[2] = random.nextFloat();
			matrix.setColumn(1, vector);
			matrix.setColumn(2, vector);
			matrix.setColumn(3, vector);

			assertEquals(matrix.m00, vector[0], 1e-10);
			assertEquals(matrix.m01, vector[0], 1e-10);
			assertEquals(matrix.m02, vector[0], 1e-10);
			assertEquals(matrix.m10, vector[1], 1e-10);
			assertEquals(matrix.m11, vector[1], 1e-10);
			assertEquals(matrix.m12, vector[1], 1e-10);
			assertEquals(matrix.m20, vector[2], 1e-10);
			assertEquals(matrix.m21, vector[2], 1e-10);
			assertEquals(matrix.m22, vector[2], 1e-10);
		}
	}

	@Test
	public void testSetColumn2()
	{
		Matrix3f matrix = new Matrix3f();
		Vector3f vector = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			vector.x = random.nextFloat();
			vector.y = random.nextFloat();
			vector.z = random.nextFloat();
			matrix.setColumn(1, vector);
			matrix.setColumn(2, vector);
			matrix.setColumn(3, vector);

			assertEquals(matrix.m00, vector.x, 1e-10);
			assertEquals(matrix.m01, vector.x, 1e-10);
			assertEquals(matrix.m02, vector.x, 1e-10);
			assertEquals(matrix.m10, vector.y, 1e-10);
			assertEquals(matrix.m11, vector.y, 1e-10);
			assertEquals(matrix.m12, vector.y, 1e-10);
			assertEquals(matrix.m20, vector.z, 1e-10);
			assertEquals(matrix.m21, vector.z, 1e-10);
			assertEquals(matrix.m22, vector.z, 1e-10);
		}
	}

	@Test
	public void testMatrixInverse()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			matrix.add(1e-8f);
			matrix2.set(matrix);
			matrix.invert();
			matrix.invert();

			assertMatrixEquals(matrix, matrix2, 1e-5f);
		}
	}

	@Test
	public void testMatrixInverse2()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();
		Matrix3f matrix3 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			matrix.add(1e-8f);
			matrix2.set(matrix);
			matrix.invert();
			matrix3.setToIdentity();

			matrix2.multiply(matrix);

			assertMatrixEquals(matrix3, matrix2, 1e-4f);
		}
	}

	@Test
	public void testGetElement()
	{
		Matrix3f matrix = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);

			assertEquals(matrix.m00, matrix.get(0, 0), 1e-10);
			assertEquals(matrix.m01, matrix.get(0, 1), 1e-10);
			assertEquals(matrix.m02, matrix.get(0, 2), 1e-10);
			assertEquals(matrix.m10, matrix.get(1, 0), 1e-10);
			assertEquals(matrix.m11, matrix.get(1, 1), 1e-10);
			assertEquals(matrix.m12, matrix.get(1, 2), 1e-10);
			assertEquals(matrix.m20, matrix.get(2, 0), 1e-10);
			assertEquals(matrix.m21, matrix.get(2, 1), 1e-10);
			assertEquals(matrix.m22, matrix.get(2, 2), 1e-10);

			try
			{
				matrix.get(4, 5);
				matrix.get(-1, 2);
				assertTrue(false);
			}
			catch (Exception e)
			{
				assertTrue(true);
			}
		}
	}

	@Test
	public void testSetElement()
	{
		Matrix3f matrix = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			float m00 = random.nextFloat();
			float m01 = random.nextFloat();
			float m02 = random.nextFloat();
			float m10 = random.nextFloat();
			float m11 = random.nextFloat();
			float m12 = random.nextFloat();
			float m20 = random.nextFloat();
			float m21 = random.nextFloat();
			float m22 = random.nextFloat();

			matrix.set(0, 0, m00);
			matrix.set(0, 1, m01);
			matrix.set(0, 2, m02);
			matrix.set(1, 0, m10);
			matrix.set(1, 1, m11);
			matrix.set(1, 2, m12);
			matrix.set(2, 0, m20);
			matrix.set(2, 1, m21);
			matrix.set(2, 2, m22);

			assertEquals(matrix.m00, m00, 1e-10);
			assertEquals(matrix.m01, m01, 1e-10);
			assertEquals(matrix.m02, m02, 1e-10);
			assertEquals(matrix.m10, m10, 1e-10);
			assertEquals(matrix.m11, m11, 1e-10);
			assertEquals(matrix.m12, m12, 1e-10);
			assertEquals(matrix.m20, m20, 1e-10);
			assertEquals(matrix.m21, m21, 1e-10);
			assertEquals(matrix.m22, m22, 1e-10);

			try
			{
				matrix.set(4, 5, 134);
				matrix.set(-1, 2, 51);
				assertTrue(false);
			}
			catch (Exception e)
			{
				assertTrue(true);
			}
		}
	}

	@Test
	public void testMatrixMultiply()
	{
		Matrix3f matrix1 = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix1);
			randomizeMatrix(random, matrix2);

			Matrix3f testMatrix = doMatrixMultiply(matrix1, matrix2);
			matrix1.multiply(matrix2);
			assertMatrixEquals(testMatrix, matrix1, 1e-10);
		}
	}

	@Test
	public void testMatrixMultiply2()
	{
		Matrix3f matrix1 = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();
		Matrix3f matrix3 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix1);
			randomizeMatrix(random, matrix2);

			Matrix3f testMatrix = doMatrixMultiply(matrix1, matrix2);
			matrix3.multiply(matrix1, matrix2);
			assertMatrixEquals(testMatrix, matrix3, 1e-10);
		}
	}

	@Test
	public void testMatrixMultiply3()
	{
		Matrix3f matrix1 = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix1);
			randomizeMatrix(random, matrix2);

			Matrix3f testMatrix = doMatrixMultiply(matrix1, matrix1);
			matrix1.multiply(matrix1, matrix1);
			assertMatrixEquals(testMatrix, matrix1, 1e-10);
		}
	}

	@Test
	public void testMatrixMultiplyTransposeRight1()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			randomizeMatrix(random, matrix2);

			matrix2.transpose();
			Matrix3f matrixCheck = doMatrixMultiply(matrix, matrix2);

			matrix2.transpose();
			matrix.multiplyTransposeRight(matrix, matrix2);

			assertMatrixEquals(matrix, matrixCheck, 1e-10);
		}
	}

	@Test
	public void testMatrixMultiplyTransposeRight2()
	{
		Matrix3f matrix1 = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();
		Matrix3f matrix3 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix1);
			randomizeMatrix(random, matrix2);

			matrix2.transpose();
			Matrix3f matrixCheck = doMatrixMultiply(matrix1, matrix2);

			matrix2.transpose();
			matrix3.multiplyTransposeRight(matrix1, matrix2);

			assertMatrixEquals(matrix3, matrixCheck, 1e-10);
		}
	}

	@Test
	public void testMatrixMultiplyTransposeLeft1()
	{
		Matrix3f matrix = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix);
			randomizeMatrix(random, matrix2);

			matrix.transpose();
			Matrix3f matrixCheck = doMatrixMultiply(matrix, matrix2);

			matrix.transpose();
			matrix.multiplyTransposeLeft(matrix, matrix2);

			assertMatrixEquals(matrix, matrixCheck, 1e-10);
		}
	}

	@Test
	public void testMatrixMultiplyTransposeLeft2()
	{
		Matrix3f matrix1 = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();
		Matrix3f matrix3 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix1);
			randomizeMatrix(random, matrix2);

			matrix1.transpose();
			Matrix3f matrixCheck = doMatrixMultiply(matrix1, matrix2);

			matrix1.transpose();
			matrix3.multiplyTransposeLeft(matrix1, matrix2);

			assertMatrixEquals(matrix3, matrixCheck, 1e-10);
		}
	}

	@Test
	public void testMatrixEquals()
	{
		Matrix3f matrix1 = new Matrix3f();
		Matrix3f matrix2 = new Matrix3f();

		for (int i = 0; i < nTests; i++)
		{
			randomizeMatrix(random, matrix1);
			matrix2.set(matrix1);

			matrix1.equals(matrix2);

			matrix1.add(1e-7f);
			matrix1.epsilonEquals(matrix2, 1e-6f);
		}
	}

	private void assertVectorEquals(Vector3f vector1, Vector3f vector2,
			double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
	}

	private void assertMatrixEquals(Matrix3f matrix1, Matrix3f matrix2,
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

	private void assertDoubleArrayEquals(double[] f1, double[] f2,
			double epsilon)
	{
		for (int i = 0; i < f1.length; i++)
		{
			assertEquals(f1[i], f2[i], epsilon);
		}
	}
	
	private void assertFloatArrayEquals(float[] f1, float[] f2,
			float epsilon)
	{
		for (int i = 0; i < f1.length; i++)
		{
			assertEquals(f1[i], f2[i], epsilon);
		}
	}

	private void randomizeDoubleArray(Random random, double[] doubleArray)
	{
		doubleArray[0] = random.nextDouble();
		doubleArray[1] = random.nextDouble();
		doubleArray[2] = random.nextDouble();
		doubleArray[3] = random.nextDouble();
		doubleArray[4] = random.nextDouble();
		doubleArray[5] = random.nextDouble();
		doubleArray[6] = random.nextDouble();
		doubleArray[7] = random.nextDouble();
		doubleArray[8] = random.nextDouble();
	}

	private void randomizeFloatArray(Random random, float[] floatArray)
	{
		floatArray[0] = random.nextFloat();
		floatArray[1] = random.nextFloat();
		floatArray[2] = random.nextFloat();
		floatArray[3] = random.nextFloat();
		floatArray[4] = random.nextFloat();
		floatArray[5] = random.nextFloat();
		floatArray[6] = random.nextFloat();
		floatArray[7] = random.nextFloat();
		floatArray[8] = random.nextFloat();
	}

	private void randomizeMatrix(Random random, Matrix3f matrix)
	{
		matrix.m00 = random.nextFloat();
		matrix.m01 = random.nextFloat();
		matrix.m02 = random.nextFloat();
		matrix.m10 = random.nextFloat();
		matrix.m11 = random.nextFloat();
		matrix.m12 = random.nextFloat();
		matrix.m20 = random.nextFloat();
		matrix.m21 = random.nextFloat();
		matrix.m22 = random.nextFloat();
	}

	private Matrix3f doMatrixMultiply(Matrix3f m1, Matrix3f m2)
	{
		Matrix3f ret = new Matrix3f();

		ret.m00 = m1.m00 * m2.m00 + m1.m01 * m2.m10 + m1.m02 * m2.m20;
		ret.m01 = m1.m00 * m2.m01 + m1.m01 * m2.m11 + m1.m02 * m2.m21;
		ret.m02 = m1.m00 * m2.m02 + m1.m01 * m2.m12 + m1.m02 * m2.m22;

		ret.m10 = m1.m10 * m2.m00 + m1.m11 * m2.m10 + m1.m12 * m2.m20;
		ret.m11 = m1.m10 * m2.m01 + m1.m11 * m2.m11 + m1.m12 * m2.m21;
		ret.m12 = m1.m10 * m2.m02 + m1.m11 * m2.m12 + m1.m12 * m2.m22;

		ret.m20 = m1.m20 * m2.m00 + m1.m21 * m2.m10 + m1.m22 * m2.m20;
		ret.m21 = m1.m20 * m2.m01 + m1.m21 * m2.m11 + m1.m22 * m2.m21;
		ret.m22 = m1.m20 * m2.m02 + m1.m21 * m2.m12 + m1.m22 * m2.m22;

		return ret;
	}
}
