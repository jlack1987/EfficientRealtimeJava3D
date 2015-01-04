import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;


public class Matrix4dTest
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
	public void testSettersAndGetters()
	{
		float[] F = new float[16];
		randomizeFloatArray(F);
		
		Matrix4d M = new Matrix4d(F);
		M.get(F);
		
		int iter = 0;
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				assertEquals(F[iter],M.get(i,j),1e-5f);
				iter++;
			}
		}
		
		double[] D = new double[16];
		randomizeDoubleArray(D);
		Matrix4d M2 = new Matrix4d(D);
		Matrix4d M3 = new Matrix4d(M2);
		M2.get(D);
		
		iter = 0;
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				assertEquals(D[iter],M2.get(i,j),1e-5f);
				assertEquals(M2.get(i,j),M3.get(i,j),1e-5f);
				iter++;
			}
		}
		
		Matrix4f M4 = new Matrix4f();
		createRandomMatrix4f(M4);
		
		Matrix4d M5 = new Matrix4d(M4);
		
		iter = 0;
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				assertEquals(M4.get(i,j),M5.get(i,j),1e-5f);
				iter++;
			}
		}
		float[] F2 = new float[16];
		randomizeFloatArray(F2);
		Matrix4d M6 = new Matrix4d(F2[0],F2[1],F2[2],F2[3],
				F2[4],F2[5],F2[6],F2[7],F2[8],F2[9],F2[10],
				F2[11],F2[12],F2[13],F2[14],F2[15]);
		Matrix4d M7 = new Matrix4d();
		M6.get(M7);
		
		iter = 0;
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				assertEquals(M6.get(i,j),M7.get(i,j),1e-5f);
				iter++;
			}
		}
		
		Matrix4d M8 = new Matrix4d();
		float[] F3 = new float[16];
		createRandomMatrix4d(M8);
		M8.get(F3);
		
		iter = 0;
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				assertEquals(F3[iter],M8.get(i,j),1e-5);
				iter++;
			}
		}
		
		Matrix4d M9 = new Matrix4d();
		Matrix4f M10 = new Matrix4f();
		createRandomMatrix4d(M9);
		M9.get(M10);
		
		for(int i=0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				assertEquals(M9.get(i,j),M10.get(i,j),1e-5f);
			}
		}
	}
	
	@Test
	public void testMatrixSetIdentity()
	{
		Matrix4d matrix = new Matrix4d();
		createRandomMatrix4d(matrix);
		matrix.setIdentity();
		
		assertMatrix4dIsIdentity(matrix, 1e-12);
	}
	
	@Test
	public void testGetAndSetElements()
	{
		Matrix4d matrix = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			double m00 = random.nextDouble()*100-50;
			double m01 = random.nextDouble()*100-50;
			double m02 = random.nextDouble()*100-50;
			double m03 = random.nextDouble()*100-50;
			double m10 = random.nextDouble()*100-50;
			double m11 = random.nextDouble()*100-50;
			double m12 = random.nextDouble()*100-50;
			double m13 = random.nextDouble()*100-50;
			double m20 = random.nextDouble()*100-50;
			double m21 = random.nextDouble()*100-50;
			double m22 = random.nextDouble()*100-50;
			double m23 = random.nextDouble()*100-50;
			double m30 = random.nextDouble()*100-50;
			double m31 = random.nextDouble()*100-50;
			double m32 = random.nextDouble()*100-50;
			double m33 = random.nextDouble()*100-50;
			
			matrix.set(0,0,m00);
			matrix.set(0,1,m01);
			matrix.set(0,2,m02);
			matrix.set(0,3,m03);
			matrix.set(1,0,m10);
			matrix.set(1,1,m11);
			matrix.set(1,2,m12);
			matrix.set(1,3,m13);
			matrix.set(2,0,m20);
			matrix.set(2,1,m21);
			matrix.set(2,2,m22);
			matrix.set(2,3,m23);
			matrix.set(3,0,m30);
			matrix.set(3,1,m31);
			matrix.set(3,2,m32);
			matrix.set(3,3,m33);
			
			assertEquals(matrix.get(0,0),m00,1e-16);
			assertEquals(matrix.get(0,1),m01,1e-16);
			assertEquals(matrix.get(0,2),m02,1e-16);
			assertEquals(matrix.get(0,3),m03,1e-16);
			assertEquals(matrix.get(1,0),m10,1e-16);
			assertEquals(matrix.get(1,1),m11,1e-16);
			assertEquals(matrix.get(1,2),m12,1e-16);
			assertEquals(matrix.get(1,3),m13,1e-16);
			assertEquals(matrix.get(2,0),m20,1e-16);
			assertEquals(matrix.get(2,1),m21,1e-16);
			assertEquals(matrix.get(2,2),m22,1e-16);
			assertEquals(matrix.get(2,3),m23,1e-16);
			assertEquals(matrix.get(3,0),m30,1e-16);
			assertEquals(matrix.get(3,1),m31,1e-16);
			assertEquals(matrix.get(3,2),m32,1e-16);
			assertEquals(matrix.get(3,3),m33,1e-16);
		}
	}
	
	@Test
	public void testGetAndSetRow()
	{
		Matrix4d matrix = new Matrix4d();
		Vector4d vector = new Vector4d();
		Vector4d vector1 = new Vector4d();
		for(int i = 0; i<nTests; i++)
		{
			for(int j = 0; j<4; j++)
			{
				randomizeVector4d(vector);
				matrix.setRow(j, vector);
				
				matrix.getRow(j, vector1);
				assertVector4dEquals(vector,vector1,1e-16);
			}
		}
	}
	
	@Test
	public void testGetAndSetRow2()
	{
		Matrix4d matrix = new Matrix4d();
		double[] vector = new double[4];
		double[] vector1 = new double[4];
		
		for(int i = 0; i<nTests; i++)
		{
			for(int j = 0; j<4; j++)
			{
				randomizeDoubleArray(vector);
				matrix.setRow(j, vector);
				
				matrix.getRow(j, vector1);
				assertDoubleArrayEquals(vector,vector1,1e-16);
			}
		}
	}
	
	@Test
	public void testGetAndSetRow3()
	{
		Matrix4d matrix = new Matrix4d();
		double[] vector = new double[4];
		double[] vector1 = new double[4];
		
		for(int i = 0; i<nTests; i++)
		{
			for(int j = 0; j<4; j++)
			{
				double x = random.nextDouble();
				double y = random.nextDouble();
				double z = random.nextDouble();
				double w = random.nextDouble();
				vector[0] = x;
				vector[1] = y;
				vector[2] = z;
				vector[3] = w;
				matrix.setRow(j, x,y,z,w);
				
				matrix.getRow(j, vector1);
				assertDoubleArrayEquals(vector,vector1,1e-16);
			}
		}
	}
	
	@Test
	public void testGetAndSetColumn()
	{
		Matrix4d matrix = new Matrix4d();
		Vector4d vector = new Vector4d();
		Vector4d vector1 = new Vector4d();
		for(int i = 0; i<nTests; i++)
		{
			for(int j = 0; j<4; j++)
			{
				randomizeVector4d(vector);
				matrix.setColumn(j, vector);
				
				matrix.getColumn(j, vector1);
				assertVector4dEquals(vector,vector1,1e-16);
			}
		}
	}
	
	@Test
	public void testGetAndSetColumn2()
	{
		Matrix4d matrix = new Matrix4d();
		double[] vector = new double[4];
		double[] vector1 = new double[4];
		
		for(int i = 0; i<nTests; i++)
		{
			for(int j = 0; j<4; j++)
			{
				randomizeDoubleArray(vector);
				matrix.setColumn(j, vector);
				
				matrix.getColumn(j, vector1);
				assertDoubleArrayEquals(vector,vector1,1e-16);
			}
		}
	}
	
	@Test
	public void testGetAndSetColumn3()
	{
		Matrix4d matrix = new Matrix4d();
		double[] vector = new double[4];
		double[] vector1 = new double[4];
		
		for(int i = 0; i<nTests; i++)
		{
			for(int j = 0; j<4; j++)
			{
				double x = random.nextDouble();
				double y = random.nextDouble();
				double z = random.nextDouble();
				double w = random.nextDouble();
				vector[0] = x;
				vector[1] = y;
				vector[2] = z;
				vector[3] = w;
				matrix.setColumn(j, x,y,z,w);
				
				matrix.getColumn(j, vector1);
				assertDoubleArrayEquals(vector,vector1,1e-16);
			}
		}
	}
	
	@Test
	public void testMatrixAdd1()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4d(matrix1);
			matrix2.set(matrix1);
			double a = random.nextDouble()*100-50;
			
			matrix1.add(a);
			for(int k = 0; k<4; k++)
			{
				for(int j = 0; j<4; j++)
				{
					assertEquals(matrix1.get(k,j),matrix2.get(k,j)+a,1e-16);
				}
			}
		}
	}
	
	@Test
	public void testMatrixAdd2()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		Matrix4d matrix3 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4d(matrix1);
			createRandomMatrix4d(matrix2);
			matrix3.add(matrix1,matrix2);
			
			for(int k = 0; k<4; k++)
			{
				for(int j = 0; j<4; j++)
				{
					assertEquals(matrix3.get(k,j),matrix1.get(k,j)+matrix2.get(k,j),1e-16);
				}
			}
		}
	}
	
	@Test
	public void testMatrixAdd3()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		Matrix4d matrix3 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4d(matrix1);
			createRandomMatrix4d(matrix2);
			matrix3.set(matrix1);
			matrix1.add(matrix1,matrix2);
			
			for(int k = 0; k<4; k++)
			{
				for(int j = 0; j<4; j++)
				{
					assertEquals(matrix1.get(k,j),matrix3.get(k,j)+matrix2.get(k,j),1e-16);
				}
			}
		}
	}
	
	@Test
	public void testMatrixSubtract1()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4d(matrix1);
			matrix2.set(matrix1);
			double a = random.nextDouble()*100-50;
			
			matrix1.subtract(a);
			for(int k = 0; k<4; k++)
			{
				for(int j = 0; j<4; j++)
				{
					assertEquals(matrix1.get(k,j),matrix2.get(k,j)-a,1e-16);
				}
			}
		}
	}
	
	@Test
	public void testMatrixSubtract2()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		Matrix4d matrix3 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4d(matrix1);
			createRandomMatrix4d(matrix2);
			matrix3.subtract(matrix1,matrix2);
			
			for(int k = 0; k<4; k++)
			{
				for(int j = 0; j<4; j++)
				{
					assertEquals(matrix3.get(k,j),matrix1.get(k,j)-matrix2.get(k,j),1e-16);
				}
			}
		}
	}
	
	@Test
	public void testMatrixSubtract3()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		Matrix4d matrix3 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4d(matrix1);
			createRandomMatrix4d(matrix2);
			matrix3.set(matrix1);
			matrix1.add(matrix1,matrix2);
			
			for(int k = 0; k<4; k++)
			{
				for(int j = 0; j<4; j++)
				{
					assertEquals(matrix1.get(k,j),matrix3.get(k,j)+matrix2.get(k,j),1e-16);
				}
			}
		}
	}
	
	@Test
	public void testMatrixTranspose1()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4d(matrix1);
			matrix2.set(matrix1);
			matrix2.transpose();
			
			for(int j = 0; j<4; j++)
			{
				for(int k = 0; k<4; k++)
				{
					assertEquals(matrix1.get(j,k),matrix2.get(k,j),1e-16);
				}
			}
		}
	}
	
	@Test
	public void testMatrixTranspose2()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4d(matrix1);
			matrix2.transpose(matrix1);
			
			for(int j = 0; j<4; j++)
			{
				for(int k = 0; k<4; k++)
				{
					assertEquals(matrix1.get(j,k),matrix2.get(k,j),1e-16);
				}
			}
		}
	}
	
	@Test
	public void testMatrixTranspose3()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4d(matrix1);
			matrix2.set(matrix1);
			matrix2.transpose(matrix2);
			
			for(int j = 0; j<4; j++)
			{
				for(int k = 0; k<4; k++)
				{
					assertEquals(matrix1.get(j,k),matrix2.get(k,j),1e-16);
				}
			}
		}
	}
	
	@Test
	public void testMatrixMultiplyAndInverse()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		
		for(int i = 0; i<1; i++)
		{
			createRandomMatrix4d(matrix1);
			matrix2.set(matrix1);
			
			matrix1.invert();
			matrix1.multiply(matrix2);
			assertMatrix4dIsIdentity(matrix1, 1e-8);
		}
	}
	
	@Test
	public void testMatrixMultiplyAndInverse2()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		
		for(int i = 0; i<1; i++)
		{
			createRandomMatrix4d(matrix1);
			
			matrix2.invert(matrix1);
			matrix2.multiply(matrix1);
			assertMatrix4dIsIdentity(matrix2, 1e-8);
		}
	}
	
	@Test
	public void testMatrixMultiplyAndInverse3()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		Matrix4d matrix3 = new Matrix4d();

		for (int i = 0; i < 1; i++)
		{
			createRandomMatrix4d(matrix1);
			matrix2.set(matrix1);
			matrix1.invert();
			matrix3.multiply(matrix1, matrix2);
			assertMatrix4dIsIdentity(matrix3, 1e-8);
		}
	}

	@Test
	public void testMatrixMultiplyAndInverse4()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();

		for (int i = 0; i < 1; i++)
		{
			createRandomMatrix4d(matrix1);
			matrix2.set(matrix1);
			matrix1.invert();
			matrix2.multiply(matrix1, matrix2);
			assertMatrix4dIsIdentity(matrix2, 1e-8);
		}
	}
	
	@Test
	public void testMatrixMultiply1()
	{
		Matrix4d M = new Matrix4d();
		Matrix4d M2 = new Matrix4d();

		createRandomMatrix4d(M);
		M2.set(M);
		double scalar = random.nextDouble();

		M.multiply(scalar);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(M2.get(i, j) * scalar, M.get(i, j), 1e-8);
			}
		}
	}

	@Test
	public void testMatrixMultiply2()
	{
		Matrix4d M = new Matrix4d();
		Matrix4d M2 = new Matrix4d();

		createRandomMatrix4d(M2);
		double scalar = random.nextDouble();
		M.multiply(M2, scalar);

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(M2.get(i, j) * scalar, M.get(i, j), 1e-8);
			}
		}
	}
	
	@Test
	public void testMultiplyTransposeBoth()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		Matrix4d matrix3 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			matrix1.setIdentity();
			createRandomMatrix4d(matrix2);
			matrix3.set(matrix2);
			matrix3.transpose();
			
			matrix1.multiplyTransposeBoth(matrix1, matrix2);
			
			assertMatrix4dEquals(matrix1, matrix3, 1e-16);
		}
	}
	
	@Test
	public void testMultiplyTransposeBoth2()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		Matrix4d matrix3 = new Matrix4d();
		Matrix4d matrix4 = new Matrix4d();

		for (int i = 0; i < nTests; i++)
		{
			matrix1.setIdentity();
			createRandomMatrix4d(matrix2);
			matrix3.set(matrix2);
			matrix3.transpose();

			matrix4.multiplyTransposeBoth(matrix1, matrix2);

			assertMatrix4dEquals(matrix4, matrix3, 1e-6f);
		}
	}
	
	@Test
	public void testMultiplyTransposeLeft()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			matrix1.setIdentity();
			createRandomMatrix4d(matrix2);
			
			matrix1.multiplyTransposeLeft(matrix1, matrix2);
			
			assertMatrix4dEquals(matrix1, matrix2, 1e-16);
		}
	}
	
	@Test
	public void testMultiplyTransposeLeft2()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		Matrix4d matrix3 = new Matrix4d();

		for (int i = 0; i < nTests; i++)
		{
			matrix1.setIdentity();
			createRandomMatrix4d(matrix2);

			matrix3.multiplyTransposeLeft(matrix1, matrix2);

			assertMatrix4dEquals(matrix3, matrix2, 1e-6f);
		}
	}
	
	@Test
	public void testMultiplyTransposeRight()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		Matrix4d matrix3 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			matrix1.setIdentity();
			createRandomMatrix4d(matrix2);
			matrix3.set(matrix2);
			matrix3.transpose();
			
			matrix1.multiplyTransposeRight(matrix1, matrix2);
			
			assertMatrix4dEquals(matrix1, matrix3, 1e-16);
		}
	}
	
	@Test
	public void testMultiplyTransposeRight2()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		Matrix4d matrix3 = new Matrix4d();
		Matrix4d matrix4 = new Matrix4d();
		matrix4.toString();
		for (int i = 0; i < nTests; i++)
		{
			matrix1.setIdentity();
			createRandomMatrix4d(matrix2);
			matrix3.set(matrix2);
			matrix3.transpose();

			matrix4.multiplyTransposeRight(matrix1, matrix2);

			assertMatrix4dEquals(matrix4, matrix3, 1e-6f);
		}
	}
	
	@Test
	public void testMatrixEqualsAndEpsilonEquals()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4d(matrix1);
			matrix2.set(matrix1);
			
			assertTrue(matrix1.equals(matrix2));
			
			matrix2.add(1e-15);
			
			boolean tmp = matrix1.equals(matrix2);
			assertFalse(tmp);
			
			assertTrue(matrix1.epsilonEquals(matrix2, 1e-10));
		}
	}
	
	@Test
	public void testNegate()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4d(matrix1);
			matrix2.set(matrix1);
			
			matrix1.negate();
			for(int j = 0; j<4; j++)
			{
				for(int k = 0; k<4; k++)
				{
					assertEquals(matrix1.get(j,k),-matrix2.get(j,k),1e-16);
				}
			}
		}
	}
	
	@Test
	public void testNegate2()
	{
		Matrix4d matrix1 = new Matrix4d();
		Matrix4d matrix2 = new Matrix4d();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4d(matrix1);
			
			matrix2.negate(matrix1);
			for(int j = 0; j<4; j++)
			{
				for(int k = 0; k<4; k++)
				{
					assertEquals(matrix1.get(j,k),-matrix2.get(j,k),1e-16);
				}
			}
		}
	}
	
	private void randomizeVector4d(Vector4d vector)
	{
		vector.set(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble());
	}
	
	private void randomizeDoubleArray(double[] array)
	{
		for(int i = 0; i< array.length; i++)
		{
			array[i] = random.nextDouble()*100-50;
		}
	}
	
	private void randomizeFloatArray(float[] array)
	{
		for(int i = 0; i< array.length; i++)
		{
			array[i] = random.nextFloat()*100-50;
		}
	}
	
	private void createRandomMatrix4d(Matrix4d matrix)
	{
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				matrix.set(i,j,random.nextDouble()*100-50);
			}
		}
	}
	
	private void createRandomMatrix4f(Matrix4f matrix)
	{
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				matrix.set(i,j,random.nextFloat()*100-50);
			}
		}
	}
	
	public void assertVector4dEquals(Vector4d v1, Vector4d v2, double epsilon)
	{
		assertEquals(v1.x,v2.x,epsilon);
		assertEquals(v1.y,v2.y,epsilon);
		assertEquals(v1.z,v2.z,epsilon);
		assertEquals(v1.w,v2.w,epsilon);
	}
	
	public void assertDoubleArrayEquals(double[] array1, double[] array2, double epsilon)
	{
		if(array1.length != array2.length)
		{
			throw new RuntimeException("Arrays are not the same length.");
		}
		
		for(int i = 0; i<array1.length; i++)
		{
			assertEquals(array1[i],array2[i],epsilon);
		}
	}
	
	public void assertMatrix4dEquals(Matrix4d matrix1, Matrix4d matrix2, double epsilon)
	{
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				assertEquals(matrix1.get(i,j),matrix2.get(i,j),epsilon);
			}
		}
	}
	
	public void assertMatrix4dIsIdentity(Matrix4d matrix,double epsilon)
	{
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				if(i != j)
				{
					assertEquals(matrix.get(i,j),0.0,epsilon);
				}
				else
				{
					assertEquals(matrix.get(i,j),1.0,epsilon);
				}
			}
		}
	}
}
