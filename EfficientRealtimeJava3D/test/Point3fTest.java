import java.util.Random;

import org.junit.Test;


public class Point3fTest
{
	Random random = new Random();
	
	@Test
	public void testAdd1()
	{
		Point3f P = new Point3f();
		Vector3f V = new Vector3f();
		
		TestingTools.randomizePoint3f(random, P);
		TestingTools.randomizeVector3f(random, V);
	}
}
