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
		double m00,m01,m02;
		double m10,m11,m12;
		double m20,m21,m22;
		
		for(int i = 0; i<nTests; i++)
		{
			m00 = random.nextDouble();
			m01 = random.nextDouble();
			m02 = random.nextDouble();
			m10 = random.nextDouble();
			m11 = random.nextDouble();
			m12 = random.nextDouble();
			m20 = random.nextDouble();
			m21 = random.nextDouble();
			m22 = random.nextDouble();
			
			Matrix3d matrix = new Matrix3d();
			matrix.m00 = m00;
			matrix.m01 = m01;
			matrix.m02 = m02;
			matrix.m10 = m10;
			matrix.m11 = m11;
			matrix.m12 = m12;
			matrix.m20 = m20;
			matrix.m21 = m21;
			matrix.m22 = m22;
			
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
	public void testSetToIdentity()
	{
		double m00,m01,m02;
		double m10,m11,m12;
		double m20,m21,m22;
		
		for(int i = 0; i<nTests; i++)
		{
			m00 = random.nextDouble();
			m01 = random.nextDouble();
			m02 = random.nextDouble();
			m10 = random.nextDouble();
			m11 = random.nextDouble();
			m12 = random.nextDouble();
			m20 = random.nextDouble();
			m21 = random.nextDouble();
			m22 = random.nextDouble();
			
			Matrix3d matrix = new Matrix3d();
			matrix.m00 = m00;
			matrix.m01 = m01;
			matrix.m02 = m02;
			matrix.m10 = m10;
			matrix.m11 = m11;
			matrix.m12 = m12;
			matrix.m20 = m20;
			matrix.m21 = m21;
			matrix.m22 = m22;
			
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
	public void testSubtract()
	{
		Matrix3d matrix1 = new Matrix3d();
		Matrix3d matrix2 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random, matrix1);
			randomizeMatrix(random, matrix2);
			
			double t00 = matrix1.m00 - matrix2.m00;
			double t01 = matrix1.m01 - matrix2.m01;
			double t02 = matrix1.m02 - matrix2.m02;
			double t10 = matrix1.m10 - matrix2.m10;
			double t11 = matrix1.m11 - matrix2.m11;
			double t12 = matrix1.m12 - matrix2.m12;
			double t20 = matrix1.m20 - matrix2.m20;
			double t21 = matrix1.m21 - matrix2.m21;
			double t22 = matrix1.m22 - matrix2.m22;
			
			matrix1.subtract(matrix2);
			
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
	public void testSubtract2()
	{
		Matrix3d matrix1 = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random, matrix1);
			
			double t00 = matrix1.m00 - matrix1.m00;
			double t01 = matrix1.m01 - matrix1.m01;
			double t02 = matrix1.m02 - matrix1.m02;
			double t10 = matrix1.m10 - matrix1.m10;
			double t11 = matrix1.m11 - matrix1.m11;
			double t12 = matrix1.m12 - matrix1.m12;
			double t20 = matrix1.m20 - matrix1.m20;
			double t21 = matrix1.m21 - matrix1.m21;
			double t22 = matrix1.m22 - matrix1.m22;
			
			matrix1.subtract(matrix1);
			
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
	public void testScale()
	{
		Matrix3d matrix = new Matrix3d();
		
		for(int i = 0; i<nTests; i++)
		{
			randomizeMatrix(random, matrix);
			double scale = random.nextDouble();
			
			double t00 = matrix.m00*scale;
			double t01 = matrix.m01*scale;
			double t02 = matrix.m02*scale;
			double t10 = matrix.m10*scale;
			double t11 = matrix.m11*scale;
			double t12 = matrix.m12*scale;
			double t20 = matrix.m20*scale;
			double t21 = matrix.m21*scale;
			double t22 = matrix.m22*scale;
			
			matrix.scale(scale);
		
			assertEquals(t00,matrix.m00,1e-10);
			assertEquals(t01,matrix.m01,1e-10);
			assertEquals(t02,matrix.m02,1e-10);
			assertEquals(t10,matrix.m10,1e-10);
			assertEquals(t11,matrix.m11,1e-10);
			assertEquals(t12,matrix.m12,1e-10);
			assertEquals(t20,matrix.m20,1e-10);
			assertEquals(t21,matrix.m21,1e-10);
			assertEquals(t22,matrix.m22,1e-10);
		}
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
