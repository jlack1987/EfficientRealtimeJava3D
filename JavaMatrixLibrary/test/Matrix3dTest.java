import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;


public class Matrix3dTest 
{
	Random random = new Random();
	int nTests = 10;

	@Test
	public void testMatrix3dEmptyConstructor()
	{
		Matrix3d matrix = new Matrix3d();
		
		assertEquals(0,matrix.m00,1e-12);
		assertEquals(0,matrix.m01,1e-12);
		assertEquals(0,matrix.m02,1e-12);
		assertEquals(0,matrix.m10,1e-12);
		assertEquals(0,matrix.m11,1e-12);
		assertEquals(0,matrix.m12,1e-12);
		assertEquals(0,matrix.m20,1e-12);
		assertEquals(0,matrix.m21,1e-12);
		assertEquals(0,matrix.m22,1e-12);
	}
	
	@Test
	public void testMatrix3dConstructor2()
	{
		for(int i = 0; i<nTests; i++)
		{
			
			Matrix3d matrix = new Matrix3d();
			randomizeMatrix(random, matrix);
			
			Matrix3d matrixToCheck = new Matrix3d(matrix);
			
			assertEquals(matrixToCheck.m00,matrix.m00,1e-12);
			assertEquals(matrixToCheck.m01,matrix.m01,1e-12);
			assertEquals(matrixToCheck.m02,matrix.m02,1e-12);
			assertEquals(matrixToCheck.m10,matrix.m10,1e-12);
			assertEquals(matrixToCheck.m11,matrix.m11,1e-12);
			assertEquals(matrixToCheck.m12,matrix.m12,1e-12);
			assertEquals(matrixToCheck.m20,matrix.m20,1e-12);
			assertEquals(matrixToCheck.m21,matrix.m21,1e-12);
			assertEquals(matrixToCheck.m22,matrix.m22,1e-12);
		}
	}
	
	@Test
	public void testConstructor3()
	{
		double[] doubleArray = new double[9];
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeDoubleArray(random, doubleArray);
			
			Matrix3d matrix = new Matrix3d(doubleArray);
			
			assertEquals(doubleArray[0],matrix.m00,1e-10);
			assertEquals(doubleArray[1],matrix.m01,1e-10);
			assertEquals(doubleArray[2],matrix.m02,1e-10);
			assertEquals(doubleArray[3],matrix.m10,1e-10);
			assertEquals(doubleArray[4],matrix.m11,1e-10);
			assertEquals(doubleArray[5],matrix.m12,1e-10);
			assertEquals(doubleArray[6],matrix.m20,1e-10);
			assertEquals(doubleArray[7],matrix.m21,1e-10);
			assertEquals(doubleArray[8],matrix.m22,1e-10);
		}
	}
	
	@Test
	public void testSet()
	{
		Matrix3d matrix = new Matrix3d();
		double[] array = new double[9];
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeDoubleArray(random, array);
			matrix.set(array);
			
			assertEquals(matrix.m00, array[0],1e-10);
			assertEquals(matrix.m01, array[1],1e-10);
			assertEquals(matrix.m02, array[2],1e-10);
			assertEquals(matrix.m10, array[3],1e-10);
			assertEquals(matrix.m11, array[4],1e-10);
			assertEquals(matrix.m12, array[5],1e-10);
			assertEquals(matrix.m20, array[6],1e-10);
			assertEquals(matrix.m21, array[7],1e-10);
			assertEquals(matrix.m22, array[8],1e-10);
		}
	}
	
	@Test
	public void testSet2()
	{
		Matrix3d matrix = new Matrix3d();
		Matrix3d matrix2 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random, matrix);
			matrix2.set(matrix);
			
			assertEquals(matrix.m00, matrix2.m00,1e-10);
			assertEquals(matrix.m01, matrix2.m01,1e-10);
			assertEquals(matrix.m02, matrix2.m02,1e-10);
			assertEquals(matrix.m10, matrix2.m10,1e-10);
			assertEquals(matrix.m11, matrix2.m11,1e-10);
			assertEquals(matrix.m12, matrix2.m12,1e-10);
			assertEquals(matrix.m20, matrix2.m20,1e-10);
			assertEquals(matrix.m21, matrix2.m21,1e-10);
			assertEquals(matrix.m22, matrix2.m22,1e-10);
		}
	}
	
	@Test
	public void testSetToIdentity()
	{
		for(int i = 0; i<nTests; i++)
		{			
			Matrix3d matrix = new Matrix3d();
			randomizeMatrix(random,matrix);
			
			matrix.setToIdentity();
			
			assertEquals(1,matrix.m00,1e-12);
			assertEquals(0,matrix.m01,1e-12);
			assertEquals(0,matrix.m02,1e-12);
			assertEquals(0,matrix.m10,1e-12);
			assertEquals(1,matrix.m11,1e-12);
			assertEquals(0,matrix.m12,1e-12);
			assertEquals(0,matrix.m20,1e-12);
			assertEquals(0,matrix.m21,1e-12);
			assertEquals(1,matrix.m22,1e-12);
		}
	}
	
	@Test
	public void testSetToZero()
	{
		for(int i = 0; i<nTests; i++)
		{	
			Matrix3d matrix = new Matrix3d();
			randomizeMatrix(random, matrix);
			
			matrix.setToZero();
			
			assertEquals(0,matrix.m00,1e-12);
			assertEquals(0,matrix.m01,1e-12);
			assertEquals(0,matrix.m02,1e-12);
			assertEquals(0,matrix.m10,1e-12);
			assertEquals(0,matrix.m11,1e-12);
			assertEquals(0,matrix.m12,1e-12);
			assertEquals(0,matrix.m20,1e-12);
			assertEquals(0,matrix.m21,1e-12);
			assertEquals(0,matrix.m22,1e-12);
		}
	}
	
	@Test
	public void testAdd()
	{
		Matrix3d matrix1 = new Matrix3d();
		Matrix3d matrix2 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
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
			
			assertEquals(t00, matrix1.m00,1e-10);
			assertEquals(t01, matrix1.m01,1e-10);
			assertEquals(t02, matrix1.m02,1e-10);
			assertEquals(t10, matrix1.m10,1e-10);
			assertEquals(t11, matrix1.m11,1e-10);
			assertEquals(t12, matrix1.m12,1e-10);
			assertEquals(t20, matrix1.m20,1e-10);
			assertEquals(t21, matrix1.m21,1e-10);
			assertEquals(t22, matrix1.m22,1e-10);
		}
	}
	
	@Test
	public void testAdd2()
	{
		Matrix3d matrix1 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
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
			
			assertEquals(t00, matrix1.m00,1e-10);
			assertEquals(t01, matrix1.m01,1e-10);
			assertEquals(t02, matrix1.m02,1e-10);
			assertEquals(t10, matrix1.m10,1e-10);
			assertEquals(t11, matrix1.m11,1e-10);
			assertEquals(t12, matrix1.m12,1e-10);
			assertEquals(t20, matrix1.m20,1e-10);
			assertEquals(t21, matrix1.m21,1e-10);
			assertEquals(t22, matrix1.m22,1e-10);
		}
	}
	
	@Test
	public void testAdd3()
	{
		Matrix3d matrix = new Matrix3d();
		Matrix3d matrix1 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			double scalar = random.nextDouble();
			randomizeMatrix(random,matrix);
			
			matrix1.set(matrix);
			
			matrix.add(scalar);
			
			assertEquals(matrix1.m00 + scalar, matrix.m00,1e-10);
			assertEquals(matrix1.m01 + scalar, matrix.m01,1e-10);
			assertEquals(matrix1.m02 + scalar, matrix.m02,1e-10);
			assertEquals(matrix1.m10 + scalar, matrix.m10,1e-10);
			assertEquals(matrix1.m11 + scalar, matrix.m11,1e-10);
			assertEquals(matrix1.m12 + scalar, matrix.m12,1e-10);
			assertEquals(matrix1.m20 + scalar, matrix.m20,1e-10);
			assertEquals(matrix1.m21 + scalar, matrix.m21,1e-10);
			assertEquals(matrix1.m22 + scalar, matrix.m22,1e-10);
		}
	}
	
	@Test
	public void testAdd4()
	{
		Matrix3d matrix = new Matrix3d();
		Matrix3d matrix1 = new Matrix3d();
		for(int i = 0; i<nTests; i++)
		{
			double scalar = random.nextDouble();
			randomizeMatrix(random,matrix);
			
			matrix1.add(matrix,scalar);
			
			assertEquals(matrix1.m00, matrix.m00 + scalar,1e-10);
			assertEquals(matrix1.m01, matrix.m01 + scalar,1e-10);
			assertEquals(matrix1.m02, matrix.m02 + scalar,1e-10);
			assertEquals(matrix1.m10, matrix.m10 + scalar,1e-10);
			assertEquals(matrix1.m11, matrix.m11 + scalar,1e-10);
			assertEquals(matrix1.m12, matrix.m12 + scalar,1e-10);
			assertEquals(matrix1.m20, matrix.m20 + scalar,1e-10);
			assertEquals(matrix1.m21, matrix.m21 + scalar,1e-10);
			assertEquals(matrix1.m22, matrix.m22 + scalar,1e-10);
		}
	}
	
	public void testAdd5()
	{
		Matrix3d matrix = new Matrix3d();
		Matrix3d matrix1 = new Matrix3d();
		for(int i = 0; i<nTests; i++)
		{
			double scalar = random.nextDouble();
			randomizeMatrix(random,matrix);
			matrix1.set(matrix);
			
			matrix.add(matrix,scalar);
			
			assertEquals(matrix.m00, matrix1.m00 + scalar,1e-10);
			assertEquals(matrix.m01, matrix1.m01 + scalar,1e-10);
			assertEquals(matrix.m02, matrix1.m02 + scalar,1e-10);
			assertEquals(matrix.m10, matrix1.m10 + scalar,1e-10);
			assertEquals(matrix.m11, matrix1.m11 + scalar,1e-10);
			assertEquals(matrix.m12, matrix1.m12 + scalar,1e-10);
			assertEquals(matrix.m20, matrix1.m20 + scalar,1e-10);
			assertEquals(matrix.m21, matrix1.m21 + scalar,1e-10);
			assertEquals(matrix.m22, matrix1.m22 + scalar,1e-10);
		}
	}
	
	public void testAdd6()
	{
		Matrix3d matrix = new Matrix3d();
		Matrix3d matrix1 = new Matrix3d();
		Matrix3d matrix2 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random,matrix);
			randomizeMatrix(random,matrix1);
			matrix2.set(matrix);
			
			matrix.add(matrix,matrix1);
			
			assertEquals(matrix.m00, matrix1.m00 + matrix2.m00,1e-10);
			assertEquals(matrix.m01, matrix1.m01 + matrix2.m01,1e-10);
			assertEquals(matrix.m02, matrix1.m02 + matrix2.m02,1e-10);
			assertEquals(matrix.m10, matrix1.m10 + matrix2.m10,1e-10);
			assertEquals(matrix.m11, matrix1.m11 + matrix2.m11,1e-10);
			assertEquals(matrix.m12, matrix1.m12 + matrix2.m12,1e-10);
			assertEquals(matrix.m20, matrix1.m20 + matrix2.m20,1e-10);
			assertEquals(matrix.m21, matrix1.m21 + matrix2.m21,1e-10);
			assertEquals(matrix.m22, matrix1.m22 + matrix2.m22,1e-10);
		}
	}
	
	public void testAdd7()
	{
		Matrix3d matrix = new Matrix3d();
		Matrix3d matrix1 = new Matrix3d();
		Matrix3d matrix2 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random,matrix);
			randomizeMatrix(random,matrix1);
			matrix2.set(matrix);
			
			matrix1.add(matrix,matrix);
			
			assertEquals(matrix1.m00, matrix2.m00 + matrix2.m00,1e-10);
			assertEquals(matrix1.m01, matrix2.m01 + matrix2.m01,1e-10);
			assertEquals(matrix1.m02, matrix2.m02 + matrix2.m02,1e-10);
			assertEquals(matrix1.m10, matrix2.m10 + matrix2.m10,1e-10);
			assertEquals(matrix1.m11, matrix2.m11 + matrix2.m11,1e-10);
			assertEquals(matrix1.m12, matrix2.m12 + matrix2.m12,1e-10);
			assertEquals(matrix1.m20, matrix2.m20 + matrix2.m20,1e-10);
			assertEquals(matrix1.m21, matrix2.m21 + matrix2.m21,1e-10);
			assertEquals(matrix1.m22, matrix2.m22 + matrix2.m22,1e-10);
		}
	}
	
	@Test
	public void testSubtract()
	{
		Matrix3d matrix1 = new Matrix3d();
		Matrix3d matrix2 = new Matrix3d();
		Matrix3d matrix3 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random, matrix1);
			randomizeMatrix(random, matrix2);
			
			matrix3.set(matrix1);
			
			matrix1.subtract(matrix2);
			
			assertEquals(matrix1.m00,-matrix2.m00+matrix3.m00,1e-10);
			assertEquals(matrix1.m01,-matrix2.m01+matrix3.m01,1e-10);
			assertEquals(matrix1.m02,-matrix2.m02+matrix3.m02,1e-10);
			assertEquals(matrix1.m10,-matrix2.m10+matrix3.m10,1e-10);
			assertEquals(matrix1.m11,-matrix2.m11+matrix3.m11,1e-10);
			assertEquals(matrix1.m12,-matrix2.m12+matrix3.m12,1e-10);
			assertEquals(matrix1.m20,-matrix2.m20+matrix3.m20,1e-10);
			assertEquals(matrix1.m21,-matrix2.m21+matrix3.m21,1e-10);
			assertEquals(matrix1.m22,-matrix2.m22+matrix3.m22,1e-10);
		}
	}
	
	@Test
	public void testSubtract2()
	{
		Matrix3d matrix1 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random, matrix1);
			
			matrix1.subtract(matrix1);
			
			assertEquals(matrix1.m00, 0,1e-10);
			assertEquals(matrix1.m01, 0,1e-10);
			assertEquals(matrix1.m02, 0,1e-10);
			assertEquals(matrix1.m10, 0,1e-10);
			assertEquals(matrix1.m11, 0,1e-10);
			assertEquals(matrix1.m12, 0,1e-10);
			assertEquals(matrix1.m20, 0,1e-10);
			assertEquals(matrix1.m21, 0,1e-10);
			assertEquals(matrix1.m22, 0,1e-10);
		}
	}
	
	public void testSubtract3()
	{
		Matrix3d matrix = new Matrix3d();
		Matrix3d matrix1 = new Matrix3d();
		Matrix3d matrix2 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random,matrix);
			randomizeMatrix(random,matrix1);
			matrix2.set(matrix);
			
			matrix.subtract(matrix,matrix1);
			
			assertEquals(matrix.m00, matrix1.m00 - matrix2.m00,1e-10);
			assertEquals(matrix.m01, matrix1.m01 - matrix2.m01,1e-10);
			assertEquals(matrix.m02, matrix1.m02 - matrix2.m02,1e-10);
			assertEquals(matrix.m10, matrix1.m10 - matrix2.m10,1e-10);
			assertEquals(matrix.m11, matrix1.m11 - matrix2.m11,1e-10);
			assertEquals(matrix.m12, matrix1.m12 - matrix2.m12,1e-10);
			assertEquals(matrix.m20, matrix1.m20 - matrix2.m20,1e-10);
			assertEquals(matrix.m21, matrix1.m21 - matrix2.m21,1e-10);
			assertEquals(matrix.m22, matrix1.m22 - matrix2.m22,1e-10);
		}
	}
	
	@Test
	public void testScale()
	{
		Matrix3d matrix = new Matrix3d();
		Matrix3d matrix2 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random, matrix);
			double scale = random.nextDouble();
			matrix2.set(matrix);
			
			matrix.scale(scale);
		
			assertEquals(matrix2.m00*scale,matrix.m00,1e-10);
			assertEquals(matrix2.m01*scale,matrix.m01,1e-10);
			assertEquals(matrix2.m02*scale,matrix.m02,1e-10);
			assertEquals(matrix2.m10*scale,matrix.m10,1e-10);
			assertEquals(matrix2.m11*scale,matrix.m11,1e-10);
			assertEquals(matrix2.m12*scale,matrix.m12,1e-10);
			assertEquals(matrix2.m20*scale,matrix.m20,1e-10);
			assertEquals(matrix2.m21*scale,matrix.m21,1e-10);
			assertEquals(matrix2.m22*scale,matrix.m22,1e-10);
		}
	}
	
	@Test
	public void testTranspose()
	{
		Matrix3d matrix = new Matrix3d();
		Matrix3d matrix2 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random,matrix);
			matrix2.set(matrix);
			
			matrix.transpose();
			
			assertEquals(matrix.m00,matrix2.m00,1e-10);
			assertEquals(matrix.m01,matrix2.m10,1e-10);
			assertEquals(matrix.m02,matrix2.m20,1e-10);
			assertEquals(matrix.m11,matrix2.m11,1e-10);
			assertEquals(matrix.m12,matrix2.m21,1e-10);
			assertEquals(matrix.m22,matrix2.m22,1e-10);
		}
	}
	
	@Test
	public void testTranspose2()
	{
		Matrix3d matrix = new Matrix3d();
		Matrix3d matrix2 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random,matrix);
			matrix2.set(matrix);
			
			matrix.transpose(matrix);
			matrix2.transpose();
			
			assertMatrixEquals(matrix,matrix2,1e-10);
		}
	}
	
	public void testGetColumn()
	{
		Matrix3d matrix = new Matrix3d();
		Vector3d vector = new Vector3d();
		Vector3d vector2 = new Vector3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random, matrix);
			vector.set(matrix.m00,matrix.m10,matrix.m20);
			
			matrix.getColumn(1, vector2);
			assertVectorEquals(vector, vector2, 1e-10);
			
			vector.set(matrix.m01,matrix.m11,matrix.m21);
			matrix.getColumn(2, vector2);
			assertVectorEquals(vector, vector2, 1e-10);
			
			vector.set(matrix.m02,matrix.m12,matrix.m22);
			matrix.getColumn(3, vector2);
			assertVectorEquals(vector, vector2, 1e-10);
		}
	}
	
	public void testGetRow()
	{
		Matrix3d matrix = new Matrix3d();
		Vector3d vector = new Vector3d();
		Vector3d vector2 = new Vector3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random, matrix);
			vector.set(matrix.m00,matrix.m01,matrix.m02);
			
			matrix.getRow(1, vector2);
			assertVectorEquals(vector, vector2, 1e-10);
			
			vector.set(matrix.m10,matrix.m11,matrix.m12);
			matrix.getRow(2, vector2);
			assertVectorEquals(vector, vector2, 1e-10);
			
			vector.set(matrix.m20,matrix.m21,matrix.m22);
			matrix.getRow(3, vector2);
			assertVectorEquals(vector, vector2, 1e-10);
		}
	}
	
	public void testGetElemente()
	{
		Matrix3d matrix = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random, matrix);
			
			assertEquals(matrix.m00,matrix.get(0, 0),1e-10);
			assertEquals(matrix.m01,matrix.get(0, 1),1e-10);
			assertEquals(matrix.m02,matrix.get(0, 2),1e-10);
			assertEquals(matrix.m10,matrix.get(1, 0),1e-10);
			assertEquals(matrix.m11,matrix.get(1, 1),1e-10);
			assertEquals(matrix.m12,matrix.get(1, 2),1e-10);
			assertEquals(matrix.m20,matrix.get(2, 0),1e-10);
			assertEquals(matrix.m21,matrix.get(2, 1),1e-10);
			assertEquals(matrix.m22,matrix.get(2, 2),1e-10);
			
			try
			{
				matrix.get(4,5);
				matrix.get(-1,2);
				assertTrue(false);
			}
			catch(Exception e)
			{
				assertTrue(true);
			}
		}
	}
	
	public void assertVectorEquals(Vector3d vector1, Vector3d vector2,double epsilon)
	{
		assertEquals(vector1.x,vector2.x,epsilon);
		assertEquals(vector1.y,vector2.y,epsilon);
		assertEquals(vector1.z,vector2.z,epsilon);
	}
	
	public void assertMatrixEquals(Matrix3d matrix1, Matrix3d matrix2, double epsilon)
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
	
	public void randomizeDoubleArray(Random random, double[] doubleArray)
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
	
	public void randomizeMatrix(Random random, Matrix3d matrix)
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
}
