import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class Vector3dTest
{

	Random random = new Random();
	int nTests = 10;

	@Test
	public void testVector3dEmptyConstructor()
	{
		Vector3d vector = new Vector3d();

		assertEquals(vector.x, 0.0, 1e-12);
		assertEquals(vector.y, 0.0, 1e-12);
		assertEquals(vector.z, 0.0, 1e-12);
	}

	@Test
	public void testVector3dConstructor()
	{
		for (int i = 0; i < nTests; i++)
		{
			double x = random.nextDouble();
			double y = random.nextDouble();
			double z = random.nextDouble();

			Vector3d vector = new Vector3d(x, y, z);

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
			double x1 = random.nextDouble();
			double x2 = random.nextDouble();
			double y1 = random.nextDouble();
			double y2 = random.nextDouble();
			double z1 = random.nextDouble();
			double z2 = random.nextDouble();

			Vector3d vector1 = new Vector3d(x1, y1, z1);
			Vector3d vector2 = new Vector3d(x2, y2, z2);

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
			double x1 = random.nextDouble();
			double x2 = random.nextDouble();
			double y1 = random.nextDouble();
			double y2 = random.nextDouble();
			double z1 = random.nextDouble();
			double z2 = random.nextDouble();

			Vector3d vector1 = new Vector3d(x1, y1, z1);
			Vector3d vector2 = new Vector3d(x2, y2, z2);

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
			double x1 = random.nextDouble();
			double x2 = random.nextDouble();
			double y1 = random.nextDouble();
			double y2 = random.nextDouble();
			double z1 = random.nextDouble();
			double z2 = random.nextDouble();

			Vector3d vector1 = new Vector3d(x1, y1, z1);
			Vector3d vector2 = new Vector3d(x2, y2, z2);

			double dotproduct = vector1.dot(vector2);

			assertEquals(x1 * x2 + y1 * y2 + z1 * z2, dotproduct, 1e-12);

		}
	}

	@Test
	public void testVectorCross()
	{
		for (int i = 0; i < nTests; i++)
		{
			double x1 = random.nextDouble();
			double x2 = random.nextDouble();
			double y1 = random.nextDouble();
			double y2 = random.nextDouble();
			double z1 = random.nextDouble();
			double z2 = random.nextDouble();

			Vector3d vector1 = new Vector3d(x1, y1, z1);
			Vector3d vector2 = new Vector3d(x2, y2, z2);
			Vector3d vector3 = new Vector3d();

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
			double x1 = random.nextDouble();
			double x2 = random.nextDouble();
			double y1 = random.nextDouble();
			double y2 = random.nextDouble();
			double z1 = random.nextDouble();
			double z2 = random.nextDouble();

			Vector3d vector1 = new Vector3d(x1, y1, z1);
			Vector3d vector2 = new Vector3d(x2, y2, z2);

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
			double x1 = random.nextDouble();
			double x2 = random.nextDouble();
			double y1 = random.nextDouble();
			double y2 = random.nextDouble();
			double z1 = random.nextDouble();
			double z2 = random.nextDouble();

			Vector3d vector1 = new Vector3d(x1, y1, z1);
			Vector3d vector2 = new Vector3d(x2, y2, z2);

			vector1.cross(vector2, vector2);

			assertEquals(y1 * z2 - z1 * y2, vector2.x, 1e-12);
			assertEquals(z1 * x2 - x1 * z2, vector2.y, 1e-12);
			assertEquals(x1 * y2 - y1 * x2, vector2.z, 1e-12);
		}
	}
}
