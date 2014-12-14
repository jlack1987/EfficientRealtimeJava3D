import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class Vector3fTest
{
	Random random = new Random();
	int nTests = 10;

	@Test 
	public void testConstructor()
	{
		for(int i = 0; i<nTests; i++)
		{
			float x = random.nextFloat();
			float y = random.nextFloat();
			float z = random.nextFloat();
			
			Vector3f vector = new Vector3f();
			vector.set(x,y,z);
			Vector3f v = new Vector3f(vector);
			
			assertEquals(v.x,vector.x,1e-12);
			assertEquals(v.y,vector.y,1e-12);
			assertEquals(v.z,vector.z,1e-12);
		}
	}
	@Test
	public void testGetAndSet1()
	{
		Vector3f vector = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			vector.x = random.nextFloat();
			vector.y = random.nextFloat();
			vector.z = random.nextFloat();

			Vector3f v2 = new Vector3f();
			v2.set(vector);

			vector.get(v2);

			assertEquals(v2.x, vector.x, 1e-12);
			assertEquals(v2.y, vector.y, 1e-12);
			assertEquals(v2.z, vector.z, 1e-12);
		}
	}

	@Test
	public void testNormalize()
	{
		Vector3f vector = new Vector3f();
		float[] v = new float[3];
		for (int i = 0; i < nTests; i++)
		{
			vector.x = random.nextFloat();
			vector.y = random.nextFloat();
			vector.z = random.nextFloat();
			vector.normalize();
			vector.get(v);
			assertEquals(Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]),
					1.0, 1e-6);
		}
	}

	@Test
	public void testNormalize2()
	{
		Vector3f vector = new Vector3f();
		Vector3f v = new Vector3f();
		for (int i = 0; i < nTests; i++)
		{
			vector.x = random.nextFloat();
			vector.y = random.nextFloat();
			vector.z = random.nextFloat();
			v.normalize(vector);

			assertEquals(v.length(), 1.0, 1e-6);
		}
	}

	@Test
	public void testNormalize3()
	{
		Vector3f vector = new Vector3f();
		for (int i = 0; i < nTests; i++)
		{
			vector.x = random.nextFloat();
			vector.y = random.nextFloat();
			vector.z = random.nextFloat();
			vector.normalize(vector);

			assertEquals(vector.length(), 1.0, 1e-6);
			assertEquals(vector.lengthSquared(), 1.0, 1e-6);
		}
	}

	@Test
	public void testAngleBetweenVectors()
	{
		Vector3f v1 = new Vector3f(1, 0, 0);
		Vector3f v2 = new Vector3f(0, 1, 0);
		Vector3f v3 = new Vector3f(0, 0, 1);
		Vector3f v4 = new Vector3f(-2, -2, -2);
		Vector3f v5 = new Vector3f(2,2,2);

		assertEquals(v1.angle(v2), Math.PI / 2, 1e-6);
		assertEquals(v2.angle(v3), Math.PI / 2, 1e-6);
		assertEquals(v4.angle(v5), Math.PI, 1e-6);
	}
	
	@Test
	public void testAngleBetweenVectors2()
	{
		Vector3f v1 = new Vector3f(1, 0, 0);
		Vector3f v2 = new Vector3f(0, 1, 0);
		Vector3f v3 = new Vector3f(0, 0, 1);
		Vector3f v4 = new Vector3f(-.2f, -.2f, -.2f);
		Vector3f v5 = new Vector3f(.2f,.2f,.2f);

		assertEquals(v1.angle(v2), Math.PI / 2, 1e-6);
		assertEquals(v2.angle(v3), Math.PI / 2, 1e-6);
		assertEquals(v4.angle(v5), Math.PI, 1e-6);
	}

	@Test
	public void testVector3fEmptyConstructor()
	{
		Vector3f vector = new Vector3f();

		assertEquals(vector.x, 0.0, 1e-12);
		assertEquals(vector.y, 0.0, 1e-12);
		assertEquals(vector.z, 0.0, 1e-12);
	}

	@Test
	public void testVector3fConstructor()
	{
		for (int i = 0; i < nTests; i++)
		{
			float x = random.nextFloat();
			float y = random.nextFloat();
			float z = random.nextFloat();

			Vector3f vector = new Vector3f(x, y, z);

			assertEquals(x, vector.x, 1e-10);
			assertEquals(y, vector.y, 1e-10);
			assertEquals(z, vector.z, 1e-10);
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
			float z1 = random.nextFloat();
			float z2 = random.nextFloat();

			Vector3f vector1 = new Vector3f(x1, y1, z1);
			Vector3f vector2 = new Vector3f(x2, y2, z2);

			vector1.add(vector2);

			assertEquals(x1 + x2, vector1.x, 1e-12);
			assertEquals(y1 + y2, vector1.y, 1e-12);
			assertEquals(z1 + z2, vector1.z, 1e-12);
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
			float z1 = random.nextFloat();
			float z2 = random.nextFloat();

			Vector3f vector1 = new Vector3f(x1, y1, z1);
			Vector3f vector2 = new Vector3f(x2, y2, z2);

			vector1.subtract(vector2);

			assertEquals(x1 - x2, vector1.x, 1e-12);
			assertEquals(y1 - y2, vector1.y, 1e-12);
			assertEquals(z1 - z2, vector1.z, 1e-12);
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
			float z1 = random.nextFloat();
			float z2 = random.nextFloat();

			Vector3f vector1 = new Vector3f(x1, y1, z1);
			Vector3f vector2 = new Vector3f(x2, y2, z2);

			float dotproduct = vector1.dot(vector2);

			assertEquals(x1 * x2 + y1 * y2 + z1 * z2, dotproduct, 1e-12);

		}
	}

	@Test
	public void testVectorCross()
	{
		for (int i = 0; i < nTests; i++)
		{
			float x1 = random.nextFloat();
			float x2 = random.nextFloat();
			float y1 = random.nextFloat();
			float y2 = random.nextFloat();
			float z1 = random.nextFloat();
			float z2 = random.nextFloat();

			Vector3f vector1 = new Vector3f(x1, y1, z1);
			Vector3f vector2 = new Vector3f(x2, y2, z2);
			Vector3f vector3 = new Vector3f();

			vector1.cross(vector2, vector3);

			assertEquals(y1 * z2 - z1 * y2, vector3.x, 1e-12);
			assertEquals(z1 * x2 - x1 * z2, vector3.y, 1e-12);
			assertEquals(x1 * y2 - y1 * x2, vector3.z, 1e-12);
		}
	}

	@Test
	public void testVectorCross2()
	{
		for (int i = 0; i < nTests; i++)
		{
			float x1 = random.nextFloat();
			float x2 = random.nextFloat();
			float y1 = random.nextFloat();
			float y2 = random.nextFloat();
			float z1 = random.nextFloat();
			float z2 = random.nextFloat();

			Vector3f vector1 = new Vector3f(x1, y1, z1);
			Vector3f vector2 = new Vector3f(x2, y2, z2);

			vector1.cross(vector2, vector1);

			assertEquals(y1 * z2 - z1 * y2, vector1.x, 1e-12);
			assertEquals(z1 * x2 - x1 * z2, vector1.y, 1e-12);
			assertEquals(x1 * y2 - y1 * x2, vector1.z, 1e-12);
		}
	}

	@Test
	public void testVectorCross3()
	{
		for (int i = 0; i < nTests; i++)
		{
			float x1 = random.nextFloat();
			float x2 = random.nextFloat();
			float y1 = random.nextFloat();
			float y2 = random.nextFloat();
			float z1 = random.nextFloat();
			float z2 = random.nextFloat();

			Vector3f vector1 = new Vector3f(x1, y1, z1);
			Vector3f vector2 = new Vector3f(x2, y2, z2);

			vector1.cross(vector2, vector2);

			assertEquals(y1 * z2 - z1 * y2, vector2.x, 1e-12);
			assertEquals(z1 * x2 - x1 * z2, vector2.y, 1e-12);
			assertEquals(x1 * y2 - y1 * x2, vector2.z, 1e-12);
		}
	}

	@Test
	public void testVectorLength()
	{
		Vector3f vector = new Vector3f();

		for (int i = 0; i < nTests; i++)
		{
			vector.set(random.nextFloat() * 100, random.nextFloat() * 100,
					random.nextFloat() * 100);
			float vectorMag = (float) Math.sqrt(vector.x * vector.x + vector.y
					* vector.y + vector.z * vector.z);

			assertEquals(vectorMag, vector.length(), 1e-15);
		}
	}
}
