import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class Point3fTest
{
	Random random = new Random();

	@Test
	public void testAdd1()
	{
		float[] d1 = TestingTools.createRandomFloatArray(random, 3);
		float[] d2 = TestingTools.createRandomFloatArray(random, 3);
		Point3f p1 = new Point3f(d1);
		Point3f p2 = new Point3f(d2);
		Point3f p3 = new Point3f();

		p3.add(p1, p2);
		p1.add(p2);

		TestingTools.assertPoint3fEquals("", p1, p3, 1e-8);
	}

	@Test
	public void testAdd2()
	{
		float[] d1 = TestingTools.createRandomFloatArray(random, 3);
		Vector3f v1 = TestingTools.createRandomVector3f(random);
		Point3f p1 = new Point3f(v1);
		Point3f p2 = new Point3f(d1);

		p1.add(d1);
		p2.add(v1);

		TestingTools.assertPoint3fEquals("", p1, p2, 1e-8);
	}

	@Test
	public void testAdd3()
	{
		float[] d1 = TestingTools.createRandomFloatArray(random, 3);
		Vector3d v1 = TestingTools.createRandomVector3d(random);
		Point3f p1 = new Point3f(v1);
		Point3f p2 = new Point3f(d1);

		p1.add(d1);
		p2.add(v1);

		TestingTools.assertPoint3fEquals("", p1, p2, 1e-6);
	}

	@Test
	public void testSubtract1()
	{
		double[] d1 = TestingTools.createRandomDoubleArray(random, 3);
		double[] d2 = TestingTools.createRandomDoubleArray(random, 3);
		Point3f p1 = new Point3f(d1);
		Point3f p2 = new Point3f(d2);
		Point3f p3 = new Point3f();

		p3.subtract(p1, p2);
		p1.subtract(p2);

		TestingTools.assertPoint3fEquals("", p1, p3, 1e-8);
	}

	@Test
	public void testSubtract2()
	{
		Vector3f v1 = TestingTools.createRandomVector3f(random);
		float[] d1 = new float[3];
		d1[0] = v1.x;
		d1[1] = v1.y;
		d1[2] = v1.z;
		Point3f p1 = new Point3f(v1);
		Point3f p2 = new Point3f(p1);

		p1.subtract(d1);
		p2.subtract(v1);

		TestingTools.assertPoint3fEquals("", p1, p2, 1e-12);
	}

	@Test
	public void testSubtract3()
	{
		double[] d1 = new double[3];
		Vector3d v1 = TestingTools.createRandomVector3d(random);
		d1[0] = v1.x;
		d1[1] = v1.y;
		d1[2] = v1.z;
		Point3f p1 = new Point3f(v1);
		Point3f p2 = new Point3f(p1);

		p1.subtract(d1);
		p2.subtract(v1);

		TestingTools.assertPoint3fEquals("", p1, p2, 1e-8);
	}

	@Test
	public void testNegate()
	{
		Point3f p = TestingTools.createRandomPoint3f(random);
		Point3f p2 = new Point3f(p);
		p2.x = -p2.x;
		p2.y = -p2.y;
		p2.z = -p2.z;

		p.negate();
		TestingTools.assertPoint3fEquals("", p, p2, 1e-8);
	}

	@Test
	public void testScale()
	{
		Point3f p = TestingTools.createRandomPoint3f(random);
		float scale = random.nextFloat();
		Point3f p2 = new Point3f(p);
		p2.x = scale * p2.x;
		p2.y = scale * p2.y;
		p2.z = scale * p2.z;

		p.scale(scale);
		TestingTools.assertPoint3fEquals("", p, p2, 1e-8);
	}

	@Test
	public void testDistance()
	{
		Point3f p1 = TestingTools.createRandomPoint3f(random);
		Point3f p2 = TestingTools.createRandomPoint3f(random);
		Point3f p3 = new Point3f(p1);
		p3.toString();
		p3.subtract(p2);
		Vector3f v = new Vector3f();
		v.x = p3.x;
		v.y = p3.y;
		v.z = p3.z;
		assertEquals(v.length(), p1.distance(p2), 1e-8);
		assertEquals(Math.pow(v.length(), 2), p1.distanceSquared(p2), 1e-6);
	}

	@Test
	public void testEquals()
	{
		Point3f p1 = TestingTools.createRandomPoint3f(random);
		Point3f p2 = new Point3f(p1);

		assertTrue(p1.equals(p2));
		p2.x += 1e-6;
		p2.y += 1e-6;
		p2.z += 1e-6;

		assertFalse(p1.equals(p2));
		assertTrue(p1.epsilonEquals(p2, 1e-5f));
	}

	@Test
	public void testClipMinMax()
	{
		Point3f p1 = new Point3f(1.0f, 2.0f, 3.0f);
		Point3f p2 = new Point3f(-1, -2, -3);
		Point3f p3 = new Point3f(1, 2, 3);

		p1.clipMinMax(0, 0, p1);
		p2.clipMinMax(0, 0, p2);
		p3.clipMinMax(-100, 100, p3);

		assertEquals(p1.x, 0, 1e-12);
		assertEquals(p1.z, 0, 1e-12);
		assertEquals(p1.y, 0, 1e-12);

		assertEquals(p2.x, 0, 1e-12);
		assertEquals(p2.z, 0, 1e-12);
		assertEquals(p2.y, 0, 1e-12);

		assertEquals(p3.x, 1, 1e-12);
		assertEquals(p3.y, 2, 1e-12);
		assertEquals(p3.z, 3, 1e-12);
	}

	@Test
	public void testInfinityNorm()
	{
		Point3f p1 = TestingTools.createRandomPoint3f(random);
		Point3f p2 = TestingTools.createRandomPoint3f(random);

		float dx = p1.x - p2.x;
		float dy = p1.y - p2.y;
		float dz = p1.z - p2.z;

		float tmp = Math.max(Math.abs(dx), Math.abs(dy));

		float infNorm = p1.infinityNorm(p2);
		assertEquals(Math.max(tmp, Math.abs(dz)), infNorm, 1e-8);
	}

	@Test
	public void testL1Norm()
	{
		Point3f p1 = TestingTools.createRandomPoint3f(random);
		Point3f p2 = TestingTools.createRandomPoint3f(random);

		float dx = Math.abs(p1.x - p2.x);
		float dy = Math.abs(p1.y - p2.y);
		float dz = Math.abs(p1.z - p2.z);

		assertEquals(p1.L1Norm(p2), dx + dy + dz, 1e-8);
	}
}
