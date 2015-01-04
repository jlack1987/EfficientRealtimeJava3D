import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class Point2fTest
{
	Random random = new Random();

	@Test
	public void testAdd1()
	{
		float[] d1 = TestingTools.createRandomFloatArray(random, 2);
		float[] d2 = TestingTools.createRandomFloatArray(random, 2);
		Point2f p1 = new Point2f(d1);
		Point2f p2 = new Point2f(d2);
		Point2f p3 = new Point2f();

		p3.add(p1, p2);
		p1.add(p2);

		TestingTools.assertPoint2fEquals(p1, p3, 1e-6f);
	}

	@Test
	public void testAdd2()
	{
		float[] d1 = TestingTools.createRandomFloatArray(random, 2);
		Vector2f v1 = TestingTools.createRandomVector2f(random);
		Point2f p1 = new Point2f(v1);
		Point2f p2 = new Point2f(d1);

		p1.add(d1);
		p2.add(v1);

		TestingTools.assertPoint2fEquals(p1, p2, 1e-6f);
	}

	@Test
	public void testAdd3()
	{
		float[] d1 = TestingTools.createRandomFloatArray(random, 2);
		Vector2d v1 = TestingTools.createRandomVector2d(random);
		Point2f p1 = new Point2f(v1);
		Point2f p2 = new Point2f(d1);

		p1.add(d1);
		p2.add(v1);

		TestingTools.assertPoint2fEquals(p1, p2, 1e-6f);
	}

	@Test
	public void testSubtract1()
	{
		double[] d1 = TestingTools.createRandomDoubleArray(random, 2);
		double[] d2 = TestingTools.createRandomDoubleArray(random, 2);
		Point2f p1 = new Point2f(d1);
		Point2f p2 = new Point2f(d2);
		Point2f p3 = new Point2f();

		p3.subtract(p1, p2);
		p1.subtract(p2);

		TestingTools.assertPoint2fEquals(p1, p3, 1e-6f);
	}

	@Test
	public void testSubtract2()
	{
		Vector2f v1 = TestingTools.createRandomVector2f(random);
		float[] d1 = new float[2];
		d1[0] = v1.x;
		d1[1] = v1.y;
		Point2f p1 = new Point2f(v1);
		Point2f p2 = new Point2f(p1);

		p1.subtract(d1);
		p2.subtract(v1);

		TestingTools.assertPoint2fEquals(p1, p2, 1e-6f);
	}

	@Test
	public void testSubtract3()
	{
		double[] d1 = new double[2];
		Vector2d v1 = TestingTools.createRandomVector2d(random);
		d1[0] = v1.x;
		d1[1] = v1.y;
		Point2f p1 = new Point2f(v1);
		Point2f p2 = new Point2f(p1);

		p1.subtract(d1);
		p2.subtract(v1);

		TestingTools.assertPoint2fEquals(p1, p2, 1e-6f);
	}

	@Test
	public void testNegate()
	{
		Point2f p = TestingTools.createRandomPoint2f(random);
		Point2f p2 = new Point2f(p);
		p2.x = -p2.x;
		p2.y = -p2.y;

		p.negate();
		TestingTools.assertPoint2fEquals(p, p2, 1e-6f);
	}

	@Test
	public void testScale()
	{
		Point2f p = TestingTools.createRandomPoint2f(random);
		float scale = random.nextFloat();
		Point2f p2 = new Point2f(p);
		p2.x = scale * p2.x;
		p2.y = scale * p2.y;

		p.scale(scale);
		TestingTools.assertPoint2fEquals(p, p2, 1e-6f);
	}

	@Test
	public void testDistance()
	{
		Point2f p1 = TestingTools.createRandomPoint2f(random);
		Point2f p2 = TestingTools.createRandomPoint2f(random);
		Point2f p3 = new Point2f(p1);
		p3.toString();
		p3.subtract(p2);
		Vector2f v = new Vector2f();
		v.x = p3.x;
		v.y = p3.y;
		assertEquals(v.length(), p1.distance(p2), 1e-6f);
		assertEquals(Math.pow(v.length(), 2), p1.distanceSquared(p2), 1e-6f);
	}

	@Test
	public void testEquals()
	{
		Point2f p1 = TestingTools.createRandomPoint2f(random);
		Point2f p2 = new Point2f(p1);

		assertTrue(p1.equals(p2));
		p2.x += 1e-6f;
		p2.y += 1e-6f;

		assertFalse(p1.equals(p2));
		assertTrue(p1.epsilonEquals(p2, 1e-5f));
	}

	@Test
	public void testClipMinMax()
	{
		Point2f p1 = new Point2f(1, 2);
		Point2f p2 = new Point2f(-1, -2);
		Point2f p3 = new Point2f(1, 2);

		p1.clipMinMax(0, 0, p1);
		p2.clipMinMax(0, 0, p2);
		p3.clipMinMax(-100, 100, p3);

		assertEquals(p1.x, 0, 1e-6f);
		assertEquals(p1.y, 0, 1e-6f);
		assertEquals(p2.x, 0, 1e-6f);
		assertEquals(p2.y, 0, 1e-6f);
		assertEquals(p3.x, 1, 1e-6f);
		assertEquals(p3.y, 2, 1e-6f);
	}

	@Test
	public void testInfinityNorm()
	{
		Point2f p1 = TestingTools.createRandomPoint2f(random);
		Point2f p2 = TestingTools.createRandomPoint2f(random);

		float dx = p1.x - p2.x;
		float dy = p1.y - p2.y;

		float infNorm = p1.infinityNorm(p2);
		assertEquals(Math.max(Math.abs(dx), Math.abs(dy)), infNorm, 1e-6f);
	}

	@Test
	public void testL1Norm()
	{
		Point2f p1 = TestingTools.createRandomPoint2f(random);
		Point2f p2 = TestingTools.createRandomPoint2f(random);

		float dx = Math.abs(p1.x - p2.x);
		float dy = Math.abs(p1.y - p2.y);

		assertEquals(p1.L1Norm(p2), dx + dy, 1e-6f);
	}
}
