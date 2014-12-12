import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;


public class Matrix4fTest
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
		Matrix4f matrix = new Matrix4f();
		createRandomMatrix4f(matrix);
		matrix.setIdentity();
		
		assertMatrix4fIsIdentity(matrix, 1e-6f);
	}
	
	@Test
	public void testGetAndSetElements()
	{
		Matrix4f matrix = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			float m00 = random.nextFloat()*100-50;
			float m01 = random.nextFloat()*100-50;
			float m02 = random.nextFloat()*100-50;
			float m03 = random.nextFloat()*100-50;
			float m10 = random.nextFloat()*100-50;
			float m11 = random.nextFloat()*100-50;
			float m12 = random.nextFloat()*100-50;
			float m13 = random.nextFloat()*100-50;
			float m20 = random.nextFloat()*100-50;
			float m21 = random.nextFloat()*100-50;
			float m22 = random.nextFloat()*100-50;
			float m23 = random.nextFloat()*100-50;
			float m30 = random.nextFloat()*100-50;
			float m31 = random.nextFloat()*100-50;
			float m32 = random.nextFloat()*100-50;
			float m33 = random.nextFloat()*100-50;
			
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
		Matrix4f matrix = new Matrix4f();
		Vector4f vector = new Vector4f();
		Vector4f vector1 = new Vector4f();
		for(int i = 0; i<nTests; i++)
		{
			for(int j = 0; j<4; j++)
			{
				randomizeVector4f(vector);
				matrix.setRow(j, vector);
				
				matrix.getRow(j, vector1);
				assertVector4fEquals(vector,vector1,1e-6f);
			}
		}
	}
	
	@Test
	public void testGetAndSetRow2()
	{
		Matrix4f matrix = new Matrix4f();
		float[] vector = new float[4];
		float[] vector1 = new float[4];
		
		for(int i = 0; i<nTests; i++)
		{
			for(int j = 0; j<4; j++)
			{
				randomizefloatArray(vector);
				matrix.setRow(j, vector);
				
				matrix.getRow(j, vector1);
				assertfloatArrayEquals(vector,vector1,1e-6f);
			}
		}
	}
	
	@Test
	public void testGetAndSetRow3()
	{
		Matrix4f matrix = new Matrix4f();
		float[] vector = new float[4];
		float[] vector1 = new float[4];
		
		for(int i = 0; i<nTests; i++)
		{
			for(int j = 0; j<4; j++)
			{
				float x = random.nextFloat();
				float y = random.nextFloat();
				float z = random.nextFloat();
				float w = random.nextFloat();
				vector[0] = x;
				vector[1] = y;
				vector[2] = z;
				vector[3] = w;
				matrix.setRow(j, x,y,z,w);
				
				matrix.getRow(j, vector1);
				assertfloatArrayEquals(vector,vector1,1e-6f);
			}
		}
	}
	
	@Test
	public void testGetAndSetColumn()
	{
		Matrix4f matrix = new Matrix4f();
		Vector4f vector = new Vector4f();
		Vector4f vector1 = new Vector4f();
		for(int i = 0; i<nTests; i++)
		{
			for(int j = 0; j<4; j++)
			{
				randomizeVector4f(vector);
				matrix.setColumn(j, vector);
				
				matrix.getColumn(j, vector1);
				assertVector4fEquals(vector,vector1,1e-6f);
			}
		}
	}
	
	@Test
	public void testGetAndSetColumn2()
	{
		Matrix4f matrix = new Matrix4f();
		float[] vector = new float[4];
		float[] vector1 = new float[4];
		
		for(int i = 0; i<nTests; i++)
		{
			for(int j = 0; j<4; j++)
			{
				randomizefloatArray(vector);
				matrix.setColumn(j, vector);
				
				matrix.getColumn(j, vector1);
				assertfloatArrayEquals(vector,vector1,1e-6f);
			}
		}
	}
	
	@Test
	public void testGetAndSetColumn3()
	{
		Matrix4f matrix = new Matrix4f();
		float[] vector = new float[4];
		float[] vector1 = new float[4];
		
		for(int i = 0; i<nTests; i++)
		{
			for(int j = 0; j<4; j++)
			{
				float x = random.nextFloat();
				float y = random.nextFloat();
				float z = random.nextFloat();
				float w = random.nextFloat();
				vector[0] = x;
				vector[1] = y;
				vector[2] = z;
				vector[3] = w;
				matrix.setColumn(j, x,y,z,w);
				
				matrix.getColumn(j, vector1);
				assertfloatArrayEquals(vector,vector1,1e-6f);
			}
		}
	}
	
	@Test
	public void testMatrixAdd1()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4f(matrix1);
			matrix2.set(matrix1);
			float a = random.nextFloat()*100-50;
			
			matrix1.add(a);
			for(int k = 0; k<4; k++)
			{
				for(int j = 0; j<4; j++)
				{
					assertEquals(matrix1.get(k,j),matrix2.get(k,j)+a,1e-4f);
				}
			}
		}
	}
	
	@Test
	public void testMatrixAdd2()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		Matrix4f matrix3 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4f(matrix1);
			createRandomMatrix4f(matrix2);
			matrix3.add(matrix1,matrix2);
			
			for(int k = 0; k<4; k++)
			{
				for(int j = 0; j<4; j++)
				{
					assertEquals(matrix3.get(k,j),matrix1.get(k,j)+matrix2.get(k,j),1e-4f);
				}
			}
		}
	}
	
	@Test
	public void testMatrixAdd3()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		Matrix4f matrix3 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4f(matrix1);
			createRandomMatrix4f(matrix2);
			matrix3.set(matrix1);
			matrix1.add(matrix1,matrix2);
			
			for(int k = 0; k<4; k++)
			{
				for(int j = 0; j<4; j++)
				{
					assertEquals(matrix1.get(k,j),matrix3.get(k,j)+matrix2.get(k,j),1e-4f);
				}
			}
		}
	}
	
	@Test
	public void testMatrixSubtract1()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4f(matrix1);
			matrix2.set(matrix1);
			float a = random.nextFloat()*100-50;
			
			matrix1.subtract(a);
			for(int k = 0; k<4; k++)
			{
				for(int j = 0; j<4; j++)
				{
					assertEquals(matrix1.get(k,j),matrix2.get(k,j)-a,1e-4f);
				}
			}
		}
	}
	
	@Test
	public void testMatrixSubtract2()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		Matrix4f matrix3 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4f(matrix1);
			createRandomMatrix4f(matrix2);
			matrix3.subtract(matrix1,matrix2);
			
			for(int k = 0; k<4; k++)
			{
				for(int j = 0; j<4; j++)
				{
					assertEquals(matrix3.get(k,j),matrix1.get(k,j)-matrix2.get(k,j),1e-4f);
				}
			}
		}
	}
	
	@Test
	public void testMatrixSubtract3()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		Matrix4f matrix3 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4f(matrix1);
			createRandomMatrix4f(matrix2);
			matrix3.set(matrix1);
			matrix1.add(matrix1,matrix2);
			
			for(int k = 0; k<4; k++)
			{
				for(int j = 0; j<4; j++)
				{
					assertEquals(matrix1.get(k,j),matrix3.get(k,j)+matrix2.get(k,j),1e-4f);
				}
			}
		}
	}
	
	@Test
	public void testMatrixTranspose1()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4f(matrix1);
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
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4f(matrix1);
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
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		
		for(int i = 0; i<1; i++)
		{
			createRandomMatrix4f(matrix1);
			matrix2.set(matrix1);
			
			matrix1.invert();
			matrix1.multiply(matrix2);
			assertMatrix4fIsIdentity(matrix1, 1e-5f);
		}
	}
	
	@Test
	public void testMatrixMultiplyAndInverse2()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		
		for(int i = 0; i<1; i++)
		{
			createRandomMatrix4f(matrix1);
			
			matrix2.invert(matrix1);
			matrix2.multiply(matrix1);
			assertMatrix4fIsIdentity(matrix2, 1e-6f);
		}
	}
	
	@Test
	public void testMultiplyTransposeBoth()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		Matrix4f matrix3 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			matrix1.setIdentity();
			createRandomMatrix4f(matrix2);
			matrix3.set(matrix2);
			matrix3.transpose();
			
			matrix1.multiplyTransposeBoth(matrix1, matrix2);
			
			assertMatrix4fEquals(matrix1, matrix3, 1e-6f);
		}
	}
	
	@Test
	public void testMultiplyTransposeLeft()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			matrix1.setIdentity();
			createRandomMatrix4f(matrix2);
			
			matrix1.multiplyTransposeLeft(matrix1, matrix2);
			
			assertMatrix4fEquals(matrix1, matrix2, 1e-6f);
		}
	}
	
	@Test
	public void testMultiplyTransposeRight()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		Matrix4f matrix3 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			matrix1.setIdentity();
			createRandomMatrix4f(matrix2);
			matrix3.set(matrix2);
			matrix3.transpose();
			
			matrix1.multiplyTransposeBoth(matrix1, matrix2);
			
			assertMatrix4fEquals(matrix1, matrix3, 1e-6f);
		}
	}
	
	@Test
	public void testMatrixEqualsAndEpsilonEquals()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4f(matrix1);
			matrix2.set(matrix1);
			
			assertTrue(matrix1.equals(matrix2));
			
			matrix2.add(1e-6f);
			
			boolean tmp = matrix1.equals(matrix2);
			assertFalse(tmp);
			
			assertTrue(matrix1.epsilonEquals(matrix2, 1e-5f));
		}
	}
	
	@Test
	public void testNegate()
	{
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4f(matrix1);
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
		Matrix4f matrix1 = new Matrix4f();
		Matrix4f matrix2 = new Matrix4f();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomMatrix4f(matrix1);
			
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
	
	private void randomizeVector4f(Vector4f vector)
	{
		vector.set(random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat());
	}
	
	private void randomizefloatArray(float[] array)
	{
		for(int i = 0; i< array.length; i++)
		{
			array[i] = random.nextFloat()*100-50;
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
	
	public void assertVector4fEquals(Vector4f v1, Vector4f v2, float epsilon)
	{
		assertEquals(v1.x,v2.x,epsilon);
		assertEquals(v1.y,v2.y,epsilon);
		assertEquals(v1.z,v2.z,epsilon);
		assertEquals(v1.w,v2.w,epsilon);
	}
	
	public void assertfloatArrayEquals(float[] array1, float[] array2, float epsilon)
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
	
	public void assertMatrix4fEquals(Matrix4f matrix1, Matrix4f matrix2, float epsilon)
	{
		for(int i = 0; i<4; i++)
		{
			for(int j = 0; j<4; j++)
			{
				assertEquals(matrix1.get(i,j),matrix2.get(i,j),epsilon);
			}
		}
	}
	
	public void assertMatrix4fIsIdentity(Matrix4f matrix,float epsilon)
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
