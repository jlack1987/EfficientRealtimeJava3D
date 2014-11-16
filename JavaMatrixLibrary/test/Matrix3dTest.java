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
}
