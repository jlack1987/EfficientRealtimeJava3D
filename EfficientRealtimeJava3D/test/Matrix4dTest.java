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
			
			matrix1.multiplyTransposeBoth(matrix1, matrix2);
			
			assertMatrix4dEquals(matrix1, matrix3, 1e-16);
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
			
			assertTrue(matrix1.epsilonEquals(matrix2, 1e-14));
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
