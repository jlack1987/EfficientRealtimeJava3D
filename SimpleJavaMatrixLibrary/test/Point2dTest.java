import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class Point2dTest
{
	Random random = new Random();

	@Test
	public void testAdd1()
	{
		double[] d1 = TestingTools.createRandomDoubleArray(random, 2);
		double[] d2 = TestingTools.createRandomDoubleArray(random, 2);
		Point2d p1 = new Point2d(d1);
		Point2d p2 = new Point2d(d2);
		Point2d p3 = new Point2d();

		p3.add(p1, p2);
		p1.add(p2);

		TestingTools.assertPoint2dEquals(p1, p3, 1e-12);
	}

	@Test
	public void testAdd2()
	{
		double[] d1 = TestingTools.createRandomDoubleArray(random, 2);
		Vector2d v1 = TestingTools.createRandomVector2d(random);
		Point2d p1 = new Point2d(v1);
		Point2d p2 = new Point2d(d1);

		p1.add(d1);
		p2.add(v1);

		TestingTools.assertPoint2dEquals(p1, p2, 1e-12);
	}

	@Test
	public void testAdd3()
	{
		double[] d1 = TestingTools.createRandomDoubleArray(random, 2);
		Vector2f v1 = TestingTools.createRandomVector2f(random);
		Point2d p1 = new Point2d(v1);
		Point2d p2 = new Point2d(d1);

		p1.add(d1);
		p2.add(v1);

		TestingTools.assertPoint2dEquals(p1, p2, 1e-12);
	}

	@Test
	public void testSubtract1()
	{
		float[] d1 = TestingTools.createRandomFloatArray(random, 2);
		float[] d2 = TestingTools.createRandomFloatArray(random, 2);
		Point2d p1 = new Point2d(d1);
		Point2d p2 = new Point2d(d2);
		Point2d p3 = new Point2d();

		p3.subtract(p1, p2);
		p1.subtract(p2);

		TestingTools.assertPoint2dEquals(p1, p3, 1e-12);
	}

	@Test
	public void testSubtract2()
	{
		Vector2d v1 = TestingTools.createRandomVector2d(random);
		double[] d1 = new double[2];
		d1[0] = v1.x;
		d1[1] = v1.y;
		Point2d p1 = new Point2d(v1);
		Point2d p2 = new Point2d(p1);

		p1.subtract(d1);
		p2.subtract(v1);

		TestingTools.assertPoint2dEquals(p1, p2, 1e-12);
	}

	@Test
	public void testSubtract3()
	{
		float[] d1 = new float[2];
		Vector2f v1 = TestingTools.createRandomVector2f(random);
		d1[0] = v1.x;
		d1[1] = v1.y;
		Point2d p1 = new Point2d(v1);
		Point2d p2 = new Point2d(p1);

		p1.subtract(d1);
		p2.subtract(v1);

		TestingTools.assertPoint2dEquals(p1, p2, 1e-12);
	}

	@Test
	public void testNegate()
	{
		Point2d p = TestingTools.createRandomPoint2d(random);
		Point2d p2 = new Point2d(p);
		p2.x = -p2.x;
		p2.y = -p2.y;

		p.negate();
		TestingTools.assertPoint2dEquals(p, p2, 1e-12);
	}

	@Test
	public void testScale()
	{
		Point2d p = TestingTools.createRandomPoint2d(random);
		double scale = random.nextDouble();
		Point2d p2 = new Point2d(p);
		p2.x = scale * p2.x;
		p2.y = scale * p2.y;

		p.scale(scale);
		TestingTools.assertPoint2dEquals(p, p2, 1e-12);
	}

	@Test
	public void testDistance()
	{
		Point2d p1 = TestingTools.createRandomPoint2d(random);
		Point2d p2 = TestingTools.createRandomPoint2d(random);
		Point2d p3 = new Point2d(p1);
		p3.toString();
		p3.subtract(p2);
		Vector2d v = new Vector2d();
		v.x = p3.x;
		v.y = p3.y;
		assertEquals(v.length(), p1.distance(p2), 1e-8);
		assertEquals(Math.pow(v.length(), 2), p1.distanceSquared(p2), 1e-8);
	}

	@Test
	public void testEquals()
	{
		Point2d p1 = TestingTools.createRandomPoint2d(random);
		Point2d p2 = new Point2d(p1);

		assertTrue(p1.equals(p2));
		p2.x += 1e-10;
		p2.y += 1e-10;

		assertFalse(p1.equals(p2));
		assertTrue(p1.epsilonEquals(p2, 1e-9));
	}

	@Test
	public void testClipMinMax()
	{
		Point2d p1 = new Point2d(1, 2);
		Point2d p2 = new Point2d(-1, -2);
		Point2d p3 = new Point2d(1, 2);

		p1.clipMinMax(0, 0, p1);
		p2.clipMinMax(0, 0, p2);
		p3.clipMinMax(-100, 100, p3);

		assertEquals(p1.x, 0, 1e-12);
		assertEquals(p1.y, 0, 1e-12);

		assertEquals(p2.x, 0, 1e-12);
		assertEquals(p2.y, 0, 1e-12);

		assertEquals(p3.x, 1, 1e-12);
		assertEquals(p3.y, 2, 1e-12);
	}

	@Test
	public void testInfinityNorm()
	{
		Point2d p1 = TestingTools.createRandomPoint2d(random);
		Point2d p2 = TestingTools.createRandomPoint2d(random);

		double dx = p1.x - p2.x;
		double dy = p1.y - p2.y;

		double infNorm = p1.infinityNorm(p2);
		assertEquals(Math.max(Math.abs(dx), Math.abs(dy)), infNorm, 1e-10);
	}

	@Test
	public void testL1Norm()
	{
		Point2d p1 = TestingTools.createRandomPoint2d(random);
		Point2d p2 = TestingTools.createRandomPoint2d(random);

		double dx = Math.abs(p1.x - p2.x);
		double dy = Math.abs(p1.y - p2.y);

		assertEquals(p1.L1Norm(p2), dx + dy, 1e-10);
	}
}
