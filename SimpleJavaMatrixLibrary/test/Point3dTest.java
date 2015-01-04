import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;


public class Point3dTest
{
	Random random = new Random();
	
	@Test
	public void testAdd1()
	{
		double[] d1 = TestingTools.createRandomDoubleArray(random, 3);
		double[] d2 = TestingTools.createRandomDoubleArray(random, 3);
		Point3d p1 = new Point3d(d1);
		Point3d p2 = new Point3d(d2);
		Point3d p3 = new Point3d();
		
		p3.add(p1,p2);
		p1.add(p2);
		
		TestingTools.assertPoint3dEquals("", p1, p3, 1e-12);
	}
	
	@Test
	public void testAdd2()
	{
		double[] d1 = TestingTools.createRandomDoubleArray(random, 3);
		Vector3d v1 = TestingTools.createRandomVector3d(random);
		Point3d p1 = new Point3d(v1);
		Point3d p2 = new Point3d(d1);
		
		p1.add(d1);
		p2.add(v1);
		
		TestingTools.assertPoint3dEquals("", p1, p2, 1e-12);
	}
	
	@Test
	public void testAdd3()
	{
		double[] d1 = TestingTools.createRandomDoubleArray(random, 3);
		Vector3f v1 = TestingTools.createRandomVector3f(random);
		Point3d p1 = new Point3d(v1);
		Point3d p2 = new Point3d(d1);
		
		p1.add(d1);
		p2.add(v1);
		
		TestingTools.assertPoint3dEquals("", p1, p2, 1e-12);
	}
	
	@Test
	public void testSubtract1()
	{
		float[] d1 = TestingTools.createRandomFloatArray(random, 3);
		float[] d2 = TestingTools.createRandomFloatArray(random, 3);
		Point3d p1 = new Point3d(d1);
		Point3d p2 = new Point3d(d2);
		Point3d p3 = new Point3d();
		
		p3.subtract(p1,p2);
		p1.subtract(p2);
		
		TestingTools.assertPoint3dEquals("", p1, p3, 1e-12);
	}
	
	@Test
	public void testSubtract2()
	{
		Vector3d v1 = TestingTools.createRandomVector3d(random);
		double[] d1 = new double[3];
		d1[0] = v1.x;
		d1[1] = v1.y;
		d1[2] = v1.z;
		Point3d p1 = new Point3d(v1);
		Point3d p2 = new Point3d(p1);
		
		p1.subtract(d1);
		p2.subtract(v1);
		
		TestingTools.assertPoint3dEquals("", p1, p2, 1e-12);
	}
	
	@Test
	public void testSubtract3()
	{
		float[] d1 = new float[3];
		Vector3f v1 = TestingTools.createRandomVector3f(random);
		d1[0] = v1.x;
		d1[1] = v1.y;
		d1[2] = v1.z;
		Point3d p1 = new Point3d(v1);
		Point3d p2 = new Point3d(p1);
		
		p1.subtract(d1);
		p2.subtract(v1);
		
		TestingTools.assertPoint3dEquals("", p1, p2, 1e-12);
	}
	
	@Test
	public void testNegate()
	{
		Point3d p = TestingTools.createRandomPoint3d(random);
		Point3d p2 = new Point3d(p);
		p2.x = -p2.x;
		p2.y = -p2.y;
		p2.z = -p2.z;
		
		p.negate();
		TestingTools.assertPoint3dEquals("", p, p2, 1e-12);
	}
	
	@Test
	public void testScale()
	{
		Point3d p = TestingTools.createRandomPoint3d(random);
		double scale = random.nextDouble();
		Point3d p2 = new Point3d(p);
		p2.x = scale*p2.x;
		p2.y = scale*p2.y;
		p2.z = scale*p2.z;
		
		p.scale(scale);
		TestingTools.assertPoint3dEquals("", p, p2, 1e-12);
	}
	
	@Test
	public void testDistance()
	{
		Point3d p1 = TestingTools.createRandomPoint3d(random);
		Point3d p2 = TestingTools.createRandomPoint3d(random);
		Point3d p3 = new Point3d(p1);
		p3.toString();
		p3.subtract(p2);
		Vector3d v = new Vector3d();
		v.x = p3.x;
		v.y = p3.y;
		v.z = p3.z;
		assertEquals(v.length(),p1.distance(p2),1e-8);
		assertEquals(Math.pow(v.length(),2),p1.distanceSquared(p2),1e-8);
	}
	
	@Test
	public void testEquals()
	{
		Point3d p1 = TestingTools.createRandomPoint3d(random);
		Point3d p2 = new Point3d(p1);
		
		assertTrue(p1.equals(p2));
		p2.x += 1e-10;
		p2.y += 1e-10;
		p2.z += 1e-10;
		
		assertFalse(p1.equals(p2));
		assertTrue(p1.epsilonEquals(p2, 1e-9));
	}
	
	@Test
	public void testClipMinMax()
	{
		Point3d p1 = new Point3d(1,2,3);
		Point3d p2 = new Point3d(-1,-2,-3);
		Point3d p3 = new Point3d(1,2,3);
		
		p1.clipMinMax(0, 0, p1);
		p2.clipMinMax(0, 0, p2);
		p3.clipMinMax(-100, 100, p3);
		
		assertEquals(p1.x,0,1e-12);
		assertEquals(p1.z,0,1e-12);
		assertEquals(p1.y,0,1e-12);
		
		assertEquals(p2.x,0,1e-12);
		assertEquals(p2.z,0,1e-12);
		assertEquals(p2.y,0,1e-12);
		
		assertEquals(p3.x,1,1e-12);
		assertEquals(p3.y,2,1e-12);
		assertEquals(p3.z,3,1e-12);
	}
	
	@Test
	public void testInfinityNorm()
	{
		Point3d p1 = TestingTools.createRandomPoint3d(random);
		Point3d p2 = TestingTools.createRandomPoint3d(random);
		
		double dx = p1.x - p2.x;
		double dy = p1.y - p2.y;
		double dz = p1.z - p2.z;
		
		double tmp = Math.max(Math.abs(dx),Math.abs(dy));
		
		double infNorm = p1.infinityNorm(p2);
		assertEquals(Math.max(tmp,Math.abs(dz)),infNorm,1e-10);
	}
	
	@Test
	public void testL1Norm()
	{
		Point3d p1 = TestingTools.createRandomPoint3d(random);
		Point3d p2 = TestingTools.createRandomPoint3d(random);
		
		double dx = Math.abs(p1.x - p2.x);
		double dy = Math.abs(p1.y - p2.y);
		double dz = Math.abs(p1.z - p2.z);
		
		assertEquals(p1.L1Norm(p2),dx+dy+dz,1e-10);
	}
}
