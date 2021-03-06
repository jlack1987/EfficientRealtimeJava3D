import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class Vector4dTest
{
	Random random = new Random();
	int nTests = 10;

	@Test
	public void testConstructors1()
	{
		Vector4d v1 = new Vector4d();
		assertEquals(v1.x, 0.0, 1e-12);
		assertEquals(v1.y, 0.0, 1e-12);
		assertEquals(v1.z, 0.0, 1e-12);
		assertEquals(v1.w, 0.0, 1e-12);

		randomizeVector(v1);

		Vector4d v2 = new Vector4d(v1);

		assertEquals(v2.getX(), v1.getX(), 1e-12);
		assertEquals(v2.getY(), v1.getY(), 1e-12);
		assertEquals(v2.getZ(), v1.getZ(), 1e-12);
		assertEquals(v2.getW(), v1.getW(), 1e-12);

		float[] v3 = new float[4];
		randomizeVector(v3);

		Vector4d v4 = new Vector4d(v3);
		assertEquals(v3[0], v4.getX(), 1e-12);
		assertEquals(v3[1], v4.getY(), 1e-12);
		assertEquals(v3[2], v4.getZ(), 1e-12);
		assertEquals(v3[3], v4.getW(), 1e-12);

		double[] v5 = new double[4];
		randomizeVector(v5);

		Vector4d v6 = new Vector4d(v5);
		assertEquals(v5[0], v6.getX(), 1e-12);
		assertEquals(v5[1], v6.getY(), 1e-12);
		assertEquals(v5[2], v6.getZ(), 1e-12);
		assertEquals(v5[3], v6.getW(), 1e-12);
	}

	@Test
	public void testGetters()
	{
		float[] f = new float[4];
		double[] d = new double[4];
		Vector4d v = new Vector4d();
		Vector4d v2 = new Vector4d();

		randomizeVector(v2);

		v2.get(f);
		v2.get(d);
		v2.get(v);

		assertEquals(v2.x, f[0], 1e-6);
		assertEquals(v2.y, f[1], 1e-6);
		assertEquals(v2.z, f[2], 1e-6);
		assertEquals(v2.w, f[3], 1e-6);
		assertEquals(v2.x, d[0], 1e-12);
		assertEquals(v2.y, d[1], 1e-12);
		assertEquals(v2.z, d[2], 1e-12);
		assertEquals(v2.w, d[3], 1e-12);
		assertEquals(v2.x, v.x, 1e-12);
		assertEquals(v2.y, v.y, 1e-12);
		assertEquals(v2.z, v.z, 1e-12);
		assertEquals(v2.w, v.w, 1e-12);
	}

	@Test
	public void testDotProduct()
	{
		Vector4d v = new Vector4d();
		Vector4d v1 = new Vector4d();

		for (int i = 0; i < nTests; i++)
		{
			randomizeVector(v);
			randomizeVector(v1);

			double vdot = v.x * v1.x + v.y * v1.y + v.z * v1.z + v.w * v1.w;
			assertEquals(vdot, v.dot(v1), 1e-12);
		}
	}

	@Test
	public void testVectorLength()
	{
		Vector4d v = new Vector4d();
		for (int i = 0; i < nTests; i++)
		{
			double x = random.nextDouble();
			double y = random.nextDouble();
			double z = random.nextDouble();
			double w = random.nextDouble();

			v.set(x, y, z, w);

			double lengthSquared = x * x + y * y + z * z + w * w;
			assertEquals(v.length(), Math.sqrt(lengthSquared), 1e-12);
			assertEquals(v.lengthSquared(), lengthSquared, 1e-12);
		}
	}

	@Test
	public void testNormalize()
	{
		Vector4d v = new Vector4d();
		Vector4d v2 = new Vector4d();
		randomizeVector(v);
		v2.set(v);
		double val = 1 / Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z + v.w
				* v.w);

		v2.scale(val);
		v.normalize();

		assertEquals(v.x, v2.x, 1e-12);
		assertEquals(v.y, v2.y, 1e-12);
		assertEquals(v.z, v2.z, 1e-12);
		assertEquals(v.w, v2.w, 1e-12);
	}

	@Test
	public void testNormalize2()
	{
		Vector4d v = new Vector4d();
		Vector4d v2 = new Vector4d();
		randomizeVector(v);
		v2.normalize(v);

		v.normalize();

		assertEquals(v.x, v2.x, 1e-12);
		assertEquals(v.y, v2.y, 1e-12);
		assertEquals(v.z, v2.z, 1e-12);
		assertEquals(v.w, v2.w, 1e-12);
	}

	@Test
	public void testNormalize3()
	{
		Vector4d v = new Vector4d();
		Vector4d v2 = new Vector4d();
		randomizeVector(v);
		v2.set(v);
		v.normalize(v);

		v2.normalize();

		assertEquals(v.x, v2.x, 1e-12);
		assertEquals(v.y, v2.y, 1e-12);
		assertEquals(v.z, v2.z, 1e-12);
		assertEquals(v.w, v2.w, 1e-12);
	}

	private void randomizeVector(Vector4d vector)
	{
		vector.x = random.nextDouble();
		vector.y = random.nextDouble();
		vector.z = random.nextDouble();
		vector.w = random.nextDouble();
	}

	private void randomizeVector(double[] vector)
	{
		vector[0] = random.nextDouble();
		vector[1] = random.nextDouble();
		vector[2] = random.nextDouble();
		vector[3] = random.nextDouble();
	}

	private void randomizeVector(float[] vector)
	{
		vector[0] = random.nextFloat();
		vector[1] = random.nextFloat();
		vector[2] = random.nextFloat();
		vector[3] = random.nextFloat();
	}
}
