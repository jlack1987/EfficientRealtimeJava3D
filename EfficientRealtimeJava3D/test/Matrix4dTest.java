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
		matrix.setIdentity();
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
		matrix.m00 = random.nextDouble()*1000-500;
		matrix.m01 = random.nextDouble()*1000-500;
		matrix.m02 = random.nextDouble()*1000-500;
		matrix.m03 = random.nextDouble()*1000-500;
		matrix.m10 = random.nextDouble()*1000-500;
		matrix.m11 = random.nextDouble()*1000-500;
		matrix.m12 = random.nextDouble()*1000-500;
		matrix.m13 = random.nextDouble()*1000-500;
		matrix.m20 = random.nextDouble()*1000-500;
		matrix.m21 = random.nextDouble()*1000-500;
		matrix.m22 = random.nextDouble()*1000-500;
		matrix.m23 = random.nextDouble()*1000-500;
		matrix.m30 = random.nextDouble()*1000-500;
		matrix.m31 = random.nextDouble()*1000-500;
		matrix.m32 = random.nextDouble()*1000-500;
		matrix.m33 = random.nextDouble()*1000-500;
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
		assertEquals(matrix1.m00,matrix2.m00,epsilon);
		assertEquals(matrix1.m01,matrix2.m01,epsilon);
		assertEquals(matrix1.m02,matrix2.m02,epsilon);
		assertEquals(matrix1.m03,matrix2.m03,epsilon);
		assertEquals(matrix1.m10,matrix2.m10,epsilon);
		assertEquals(matrix1.m11,matrix2.m11,epsilon);
		assertEquals(matrix1.m12,matrix2.m12,epsilon);
		assertEquals(matrix1.m13,matrix2.m13,epsilon);
		assertEquals(matrix1.m20,matrix2.m20,epsilon);
		assertEquals(matrix1.m21,matrix2.m21,epsilon);
		assertEquals(matrix1.m22,matrix2.m22,epsilon);
		assertEquals(matrix1.m23,matrix2.m23,epsilon);
		assertEquals(matrix1.m30,matrix2.m30,epsilon);
		assertEquals(matrix1.m31,matrix2.m31,epsilon);
		assertEquals(matrix1.m32,matrix2.m32,epsilon);
		assertEquals(matrix1.m33,matrix2.m33,epsilon);
	}
	
	public boolean assertMatrix4dIsIdentity(Matrix4d matrix)
	{
		Matrix4d matrix1 = new Matrix4d();
		matrix1.set(0,0,1.0);
		matrix1.set(1,1,1.0);
		matrix1.set(2,2,1.0);
		matrix1.set(3,3,1.0);
		
		return matrix.equals(matrix1);
	}
}
