import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class Vector3dTest
{

	Random random = new Random();
	int nTests = 10;

	@Test
	public void testGetAndSet1()
	{
		Vector3d vector = new Vector3d();
		vector.toString();
		for(int i = 0; i<nTests; i++)
		{
			vector.x = random.nextDouble();
			vector.y = random.nextDouble();
			vector.z = random.nextDouble();
			
			Vector3d v2 = new Vector3d(vector);
			
			vector.get(v2);
			
			assertEquals(v2.x, vector.x,1e-12);
			assertEquals(v2.y, vector.y,1e-12);
			assertEquals(v2.z, vector.z,1e-12);
		}
	}
	
	@Test
	public void testNormalize()
	{
		Vector3d vector = new Vector3d();
		double[] v = new double[3];
		for(int i = 0; i<nTests; i++)
		{
			vector.x = random.nextDouble();
			vector.y = random.nextDouble();
			vector.z = random.nextDouble();
			vector.normalize();
			vector.get(v);
			assertEquals(Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]),1.0,1e-12);
		}
	}
	
	@Test
	public void testNormalize2()
	{
		Vector3d vector = new Vector3d();
		Vector3d v = new Vector3d();
		for(int i = 0; i<nTests; i++)
		{
			vector.x = random.nextDouble();
			vector.y = random.nextDouble();
			vector.z = random.nextDouble();
			v.normalize(vector);
			
			assertEquals(v.length(),1.0,1e-12);
		}
	}
	
	@Test
	public void testNormalize3()
	{
		Vector3d vector = new Vector3d();
		for(int i = 0; i<nTests; i++)
		{
			vector.x = random.nextDouble();
			vector.y = random.nextDouble();
			vector.z = random.nextDouble();
			vector.normalize(vector);
			
			assertEquals(vector.length(),1.0,1e-12);
			assertEquals(vector.lengthSquared(),1.0,1e-12);
		}
	}
	
	@Test
	public void testAngleBetweenVectors()
	{
		Vector3d v1 = new Vector3d(1,0,0);
		Vector3d v2 = new Vector3d(0,1,0);
		Vector3d v3 = new Vector3d(0,0,1);
		Vector3d v4 = new Vector3d(-1,-1,-1);
		Vector3d v5 = new Vector3d(2,2,2);
		Vector3d v6 = new Vector3d(2,2,2);
		
		assertEquals(v1.angle(v2),Math.PI/2,1e-12);
		assertEquals(v2.angle(v3),Math.PI/2,1e-12);
		assertEquals(v4.angle(v5),Math.PI,1e-12);
		assertEquals(v5.angle(v6),0,1e-8);
	}
	
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
	
	@Test
	public void testVectorLength()
	{
		Vector3d vector = new Vector3d();
		
		for(int i = 0; i<nTests; i++)
		{
			vector.set(random.nextDouble()*100, random.nextDouble()*100, random.nextDouble()*100);
			double vectorMag = Math.sqrt(vector.x*vector.x+vector.y*vector.y+vector.z*vector.z);
			
			assertEquals(vectorMag, vector.length(),1e-15);
		}
	}
}
