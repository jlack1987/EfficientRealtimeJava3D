import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class Vector2fTest
{
	Random random = new Random();
	int nTests = 10;

	@Test
	public void testGetAndSet1()
	{
		Vector2f vector = new Vector2f();
		vector.toString();
		for (int i = 0; i < nTests; i++)
		{
			vector.x = random.nextFloat();
			vector.y = random.nextFloat();

			Vector2f v2 = new Vector2f(vector);

			vector.get(v2);

			assertEquals(v2.x, vector.x, 1e-12);
			assertEquals(v2.y, vector.y, 1e-12);
		}
	}

	@Test
	public void testGetAndSet2()
	{
		Vector2f vector = new Vector2f();
		float[] f = new float[2];
		vector.toString();
		for (int i = 0; i < nTests; i++)
		{
			vector.x = random.nextFloat();
			vector.y = random.nextFloat();

			Vector2f v2 = new Vector2f(vector);

			vector.get(f);

			assertEquals(f[0], vector.x, 1e-6);
			assertEquals(f[1], vector.y, 1e-6);
		}
	}

	@Test
	public void testNormalize()
	{
		Vector2f vector = new Vector2f();
		double[] v = new double[3];
		for (int i = 0; i < nTests; i++)
		{
			vector.x = random.nextFloat();
			vector.y = random.nextFloat();
			vector.normalize();
			vector.get(v);
			assertEquals(Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]),
					1.0, 1e-6);
		}
	}

	@Test
	public void testNormalize2()
	{
		Vector2f vector = new Vector2f();
		Vector2f v = new Vector2f();
		for (int i = 0; i < nTests; i++)
		{
			vector.x = random.nextFloat();
			vector.y = random.nextFloat();
			v.normalize(vector);

			assertEquals(v.length(), 1.0, 1e-6);
		}
	}

	@Test
	public void testNormalize3()
	{
		Vector2f vector = new Vector2f();
		for (int i = 0; i < nTests; i++)
		{
			vector.x = random.nextFloat();
			vector.y = random.nextFloat();
			vector.normalize(vector);

			assertEquals(vector.length(), 1.0, 1e-6);
			assertEquals(vector.lengthSquared(), 1.0, 1e-6);
		}
	}

	@Test
	public void testAngleBetweenVectors()
	{
		double[] d1 = new double[2];
		d1[0] = 1;
		d1[1] = 0;

		float[] f1 = new float[2];
		f1[0] = 0;
		f1[1] = 1;
		Vector2f v1 = new Vector2f(d1);
		Vector2f v2 = new Vector2f(f1);
		Vector2f v3 = new Vector2f(-1, -1);
		Vector2f v4 = new Vector2f(2, 2);
		Vector2f v5 = new Vector2f(2, 2);

		assertEquals(v1.angle(v2), Math.PI / 2, 1e-6);
		assertEquals(v3.angle(v4), Math.PI, 1e-6);
		assertEquals(v4.angle(v5), 0, 1e-6);

		Vector2f v6 = TestingTools.createRandomVector2f(random);
		Vector2f v7 = TestingTools.createRandomVector2f(random);

		float v6Length = v6.length();
		float v7Length = v7.length();
		float angle = (float)Math.acos(v6.dot(v7) / (v6Length * v7Length));

		assertEquals(v6.angle(v7), angle, 1e-6);
	}

	@Test
	public void testVector2fEmptyConstructor()
	{
		Vector2f vector = new Vector2f();

		assertEquals(vector.x, 0.0, 1e-12);
		assertEquals(vector.y, 0.0, 1e-12);
	}

	@Test
	public void testVector2fConstructor()
	{
		for (int i = 0; i < nTests; i++)
		{
			float x = random.nextFloat();
			float y = random.nextFloat();

			Vector2f vector = new Vector2f(x, y);

			assertEquals(x, vector.x, 1e-10);
			assertEquals(y, vector.y, 1e-10);
		}
	}

	@Test
	public void testVectorAdd()
	{
		for (int i = 0; i < nTests; i++)
		{
			float x1 = random.nextFloat();
			float x2 = random.nextFloat();
			float y1 = random.nextFloat();
			float y2 = random.nextFloat();

			Vector2f vector1 = new Vector2f(x1, y1);
			Vector2f vector2 = new Vector2f(x2, y2);

			vector1.add(vector2);

			assertEquals(x1 + x2, vector1.x, 1e-12);
			assertEquals(y1 + y2, vector1.y, 1e-12);
		}
	}

	@Test
	public void testVectorSubtract()
	{
		for (int i = 0; i < nTests; i++)
		{
			float x1 = random.nextFloat();
			float x2 = random.nextFloat();
			float y1 = random.nextFloat();
			float y2 = random.nextFloat();

			Vector2f vector1 = new Vector2f(x1, y1);
			Vector2f vector2 = new Vector2f(x2, y2);

			vector1.subtract(vector2);

			assertEquals(x1 - x2, vector1.x, 1e-12);
			assertEquals(y1 - y2, vector1.y, 1e-12);
		}
	}

	@Test
	public void testVectorDot()
	{
		for (int i = 0; i < nTests; i++)
		{
			float x1 = random.nextFloat();
			float x2 = random.nextFloat();
			float y1 = random.nextFloat();
			float y2 = random.nextFloat();

			Vector2f vector1 = new Vector2f(x1, y1);
			Vector2f vector2 = new Vector2f(x2, y2);

			float dotproduct = vector1.dot(vector2);

			assertEquals(x1 * x2 + y1 * y2, dotproduct, 1e-12);

		}
	}

	@Test
	public void testVectorLength()
	{
		Vector2f vector = new Vector2f();

		for (int i = 0; i < nTests; i++)
		{
			vector.set(random.nextFloat() * 100, random.nextFloat() * 100);
			float vectorMag = (float)Math.sqrt(vector.x * vector.x + vector.y
					* vector.y);

			assertEquals(vectorMag, vector.length(), 1e-15);
		}
	}
}
