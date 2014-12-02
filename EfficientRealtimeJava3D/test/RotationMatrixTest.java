import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;


public class RotationMatrixTest
{
	Random random = new Random();
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
		RotationMatrix matrix = new RotationMatrix();
		double theta = random.nextDouble()*10-5;
		matrix.rotX(theta);
		
		assertEquals(matrix.m00,1,1e-15);
		assertEquals(matrix.m01,0,1e-15);
		assertEquals(matrix.m02,0,1e-15);
		assertEquals(matrix.m10,0,1e-15);
		assertEquals(matrix.m20,0,1e-15);
		assertEquals(matrix.m11,Math.cos(theta),1e-15);
		assertEquals(matrix.m22,Math.cos(theta),1e-15);
		assertEquals(matrix.m12,-Math.sin(theta),1e-15);
		assertEquals(matrix.m21,Math.sin(theta),1e-15);
	}
	
	@Test
	public void testRotY()
	{
		RotationMatrix matrix = new RotationMatrix();
		double theta = random.nextDouble()*10-5;
		matrix.rotY(theta);
		
		assertEquals(matrix.m11,1,1e-15);
		assertEquals(matrix.m01,0,1e-15);
		assertEquals(matrix.m12,0,1e-15);
		assertEquals(matrix.m10,0,1e-15);
		assertEquals(matrix.m21,0,1e-15);
		assertEquals(matrix.m00,Math.cos(theta),1e-15);
		assertEquals(matrix.m22,Math.cos(theta),1e-15);
		assertEquals(matrix.m20,-Math.sin(theta),1e-15);
		assertEquals(matrix.m02,Math.sin(theta),1e-15);
	}
	
	@Test
	public void testRotZ()
	{
		RotationMatrix matrix = new RotationMatrix();
		double theta = random.nextDouble()*10-5;
		matrix.rotZ(theta);
		
		assertEquals(matrix.m22,1,1e-15);
		assertEquals(matrix.m02,0,1e-15);
		assertEquals(matrix.m12,0,1e-15);
		assertEquals(matrix.m20,0,1e-15);
		assertEquals(matrix.m21,0,1e-15);
		assertEquals(matrix.m00,Math.cos(theta),1e-15);
		assertEquals(matrix.m11,Math.cos(theta),1e-15);
		assertEquals(matrix.m01,-Math.sin(theta),1e-15);
		assertEquals(matrix.m10,Math.sin(theta),1e-15);
	}
}
