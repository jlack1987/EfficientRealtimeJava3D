import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;


public class Vector2dTest
{
	Random random = new Random();
	int nTests = 10;
	
	@Test
	public void testGetAndSet1()
	{
		Vector2d vector = new Vector2d();
		vector.toString();
		for(int i = 0; i<nTests; i++)
		{
			vector.x = random.nextDouble();
			vector.y = random.nextDouble();
			
			Vector2d v2 = new Vector2d(vector);
			
			vector.get(v2);
			
			assertEquals(v2.x, vector.x,1e-12);
			assertEquals(v2.y, vector.y,1e-12);
		}
	}
	
	@Test
	public void testGetAndSet2()
	{
		Vector2d vector = new Vector2d();
		float[] f = new float[2];
		vector.toString();
		for(int i = 0; i<nTests; i++)
		{
			vector.x = random.nextDouble();
			vector.y = random.nextDouble();
			
			Vector2d v2 = new Vector2d(vector);
			
			vector.get(f);
			
			assertEquals(f[0], vector.x,1e-6);
			assertEquals(f[1], vector.y,1e-6);
		}
	}
	
	@Test
	public void testNormalize()
	{
		Vector2d vector = new Vector2d();
		double[] v = new double[3];
		for(int i = 0; i<nTests; i++)
		{
			vector.x = random.nextDouble();
			vector.y = random.nextDouble();
			vector.normalize();
			vector.get(v);
			assertEquals(Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]),1.0,1e-12);
		}
	}
	
	@Test
	public void testNormalize2()
	{
		Vector2d vector = new Vector2d();
		Vector2d v = new Vector2d();
		for(int i = 0; i<nTests; i++)
		{
			vector.x = random.nextDouble();
			vector.y = random.nextDouble();
			v.normalize(vector);
			
			assertEquals(v.length(),1.0,1e-12);
		}
	}
	
	@Test
	public void testNormalize3()
	{
		Vector2d vector = new Vector2d();
		for(int i = 0; i<nTests; i++)
		{
			vector.x = random.nextDouble();
			vector.y = random.nextDouble();
			vector.normalize(vector);
			
			assertEquals(vector.length(),1.0,1e-12);
			assertEquals(vector.lengthSquared(),1.0,1e-12);
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
		Vector2d v1 = new Vector2d(d1);
		Vector2d v2 = new Vector2d(f1);
		Vector2d v3 = new Vector2d(-1,-1);
		Vector2d v4 = new Vector2d(2,2);
		Vector2d v5 = new Vector2d(2,2);
		
		assertEquals(v1.angle(v2),Math.PI/2,1e-6);
		assertEquals(v3.angle(v4),Math.PI,1e-6);
		assertEquals(v4.angle(v5),0,1e-6);
		
		Vector2d v6 = TestingTools.createRandomVector2d(random);
		Vector2d v7 = TestingTools.createRandomVector2d(random);
		
		double v6Length = v6.length();
		double v7Length = v7.length();
		double angle = Math.acos(v6.dot(v7)/(v6Length*v7Length));
		
		assertEquals(v6.angle(v7),angle,1e-8);
	}
	
	@Test
	public void testVector2dEmptyConstructor()
	{
		Vector2d vector = new Vector2d();

		assertEquals(vector.x, 0.0, 1e-12);
		assertEquals(vector.y, 0.0, 1e-12);
	}

	@Test
	public void testVector2dConstructor()
	{
		for (int i = 0; i < nTests; i++)
		{
			double x = random.nextDouble();
			double y = random.nextDouble();

			Vector2d vector = new Vector2d(x, y);

			assertEquals(x, vector.x, 1e-10);
			assertEquals(y, vector.y, 1e-10);
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

			Vector2d vector1 = new Vector2d(x1, y1);
			Vector2d vector2 = new Vector2d(x2, y2);

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
			double x1 = random.nextDouble();
			double x2 = random.nextDouble();
			double y1 = random.nextDouble();
			double y2 = random.nextDouble();
	
			Vector2d vector1 = new Vector2d(x1, y1);
			Vector2d vector2 = new Vector2d(x2, y2);

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
			double x1 = random.nextDouble();
			double x2 = random.nextDouble();
			double y1 = random.nextDouble();
			double y2 = random.nextDouble();

			Vector2d vector1 = new Vector2d(x1, y1);
			Vector2d vector2 = new Vector2d(x2, y2);

			double dotproduct = vector1.dot(vector2);

			assertEquals(x1 * x2 + y1 * y2, dotproduct, 1e-12);

		}
	}

	@Test
	public void testVectorLength()
	{
		Vector2d vector = new Vector2d();
		
		for(int i = 0; i<nTests; i++)
		{
			vector.set(random.nextDouble()*100, random.nextDouble()*100);
			double vectorMag = Math.sqrt(vector.x*vector.x+vector.y*vector.y);
			
			assertEquals(vectorMag, vector.length(),1e-15);
		}
	}
}
