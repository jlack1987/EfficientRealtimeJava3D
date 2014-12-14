import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import javax.vecmath.Quat4d;

import org.junit.Test;


public class QuaternionfTest
{
	Random random = new Random();
	double nTests = 10;

	public void setUp()
	{

	}

	public void tearDown()
	{
		System.gc();
	}
	
	@Test
	public void testCreateQuaternionFromVector4f()
	{
		Vector4f V = new Vector4f();
		
		for(int i = 0; i<nTests; i++)
		{
			V.set(random.nextFloat(),random.nextFloat(),random.nextFloat(),random.nextFloat());
			V.normalize();
			Quaternionf Q = new Quaternionf(V);
			
			assertEquals(V.x,Q.x,1e-5f);
			assertEquals(V.y,Q.y,1e-5f);
			assertEquals(V.z,Q.z,1e-5f);
			assertEquals(V.w,Q.w,1e-5f);
		}
	}

	@Test
	public void testCreateQuaternionFromRotationMatrixf()
	{
		Quaternionf quat1 = new Quaternionf();

		for (int i = 0; i < nTests; i++)
		{
			createRandomQuaternion(random, quat1);
			RotationMatrixf mat = new RotationMatrixf(quat1);
			Quaternionf quat2 = new Quaternionf(mat);

			assertQuaternionfEquals(quat1, quat2, 1e-5f);
		}
	}
	
	@Test
	public void testCreateQuaternionFromAxisAnglef()
	{
		Quaternionf quat1 = new Quaternionf();
		Quaternionf quat2 = new Quaternionf();

		for (int i = 0; i < nTests; i++)
		{
			createRandomQuaternion(random, quat1);
			AxisAnglef A = new AxisAnglef(quat1);
			quat2.set(A);

			assertQuaternionfEquals(quat1, quat2, 1e-5f);
		}
	}
	
	@Test
	public void testCreateQuaternionFromAxisAnglef2()
	{
		Quaternionf quat1 = new Quaternionf();
		
		quat1.x = 0; quat1.y = 0; quat1.z = 0; quat1.w = 0;
		AxisAnglef A = new AxisAnglef(0,0,0,0);
		Quaternionf quat2 = new Quaternionf(A);

		assertQuaternionfEquals(quat1, quat2, 1e-5f);
	}

	@Test
	public void testQuaternionConjugate()
	{
		Quaternionf quat = new Quaternionf();
		Quaternionf quat2 = new Quaternionf();

		for (int i = 0; i < nTests; i++)
		{
			createRandomQuaternion(random, quat);
			quat2.set(quat);
			quat.conjugate();

			assertEquals(quat.x, -quat2.x, 1e-10);
			assertEquals(quat.y, -quat2.y, 1e-10);
			assertEquals(quat.z, -quat2.z, 1e-10);
			assertEquals(quat.w, quat2.w, 1e-10);
		}
	}

	@Test
	public void testMultiply()
	{
		Quaternionf q1 = new Quaternionf();
		Quaternionf q2 = new Quaternionf();

		for(int i = 0; i<nTests; i++)
		{
			createRandomQuaternion(random, q1);
			createRandomQuaternion(random, q2);
	
			Quat4d q3 = new Quat4d();
			Quat4d q4 = new Quat4d();
	
			q3.set(q1.x, q1.y, q1.z, q1.w);
			q4.set(q2.x, q2.y, q2.z, q2.w);
	
			q1.multiply(q2);
			q3.mul(q4);
	
			assertQuaternionfEquals(q1, q3, 1e-5);
		}
	}
	
	@Test
	public void testMultiply2()
	{
		Quaternionf q1 = new Quaternionf();
		Quaternionf q2 = new Quaternionf();
		Quaternionf q3 = new Quaternionf();

		for(int i = 0; i<nTests; i++)
		{
			createRandomQuaternion(random, q1);
			q3.set(q1);
			createRandomQuaternion(random, q2);
	
			q3.multiply(q3,q2);
			q1.multiply(q2);
	
			assertQuaternionfEquals(q1, q3, 1e-5);
		}
	}
	
	@Test
	public void testNormalize()
	{
		double[] f = new double[4];
		
		f[0] = random.nextFloat();
		f[1] = random.nextFloat();
		f[2] = random.nextFloat();
		f[3] = random.nextFloat();
		Quaternionf Q = new Quaternionf(f);
		Q.normalize();
		
		double mag = Math.sqrt(Q.x*Q.x+Q.y*Q.y+Q.z*Q.z+Q.w*Q.w);
		Q.x/=mag;
		Q.y/=mag;
		Q.z/=mag;
		Q.w/=mag;
		
		assertEquals(Math.sqrt(Q.x*Q.x+Q.y*Q.y+Q.z*Q.z+Q.w*Q.w),1.0,1e-5f);
	}
	
	@Test
	public void testNormalize2()
	{
		double[] f = new double[4];
		Quaternionf Q1 = new Quaternionf();
		Q1.toString();
		for(int i = 0; i<nTests; i++)
		{
			f[0] = random.nextFloat();
			f[1] = random.nextFloat();
			f[2] = random.nextFloat();
			f[3] = random.nextFloat();
			Quaternionf Q = new Quaternionf(f);
			Q1.normalize(Q);
			
			double mag = Math.sqrt(Q1.x*Q1.x+Q1.y*Q1.y+Q1.z*Q1.z+Q1.w*Q1.w);
			Q1.x/=mag;
			Q1.y/=mag;
			Q1.z/=mag;
			Q1.w/=mag;
			
			assertEquals(Math.sqrt(Q1.x*Q1.x+Q1.y*Q1.y+Q1.z*Q1.z+Q1.w*Q1.w),1.0,1e-5f);
		}
	}
	
	@Test
	public void testNormalize3()
	{
		float[] f = new float[4];
		
		f[0] = 0.0f;
		f[1] = 0.0f;
		f[2] = 0.0f;
		f[3] = 0.0f;
		Quaternionf Q = new Quaternionf(f);
		Q.normalize();
		
		assertEquals(Math.sqrt(Q.x*Q.x+Q.y*Q.y+Q.z*Q.z+Q.w*Q.w),0,1e-12);
	}

	@Test
	public void testMultiplyInverse()
	{
		Quaternionf q1 = new Quaternionf();
		Quaternionf q2 = new Quaternionf();

		createRandomQuaternion(random, q1);
		createRandomQuaternion(random, q2);

		Quat4d q3 = new Quat4d();
		Quat4d q4 = new Quat4d();

		q3.set(q1.x, q1.y, q1.z, q1.w);
		q4.set(q2.x, q2.y, q2.z, q2.w);

		q1.multiplyInverse(q2);
		q3.mulInverse(q4);

		assertQuaternionfEquals(q1, q3, 1e-5);
	}

	@Test
	public void testMultiplyInverse2()
	{
		Quaternionf q1 = new Quaternionf();
		Quaternionf q2 = new Quaternionf();

		createRandomQuaternion(random, q1);
		createRandomQuaternion(random, q2);

		Quat4d q3 = new Quat4d();
		Quat4d q4 = new Quat4d();

		q3.set(q1.x, q1.y, q1.z, q1.w);
		q4.set(q2.x, q2.y, q2.z, q2.w);

		q1.multiplyInverse(q2, q2);
		q3.mulInverse(q4, q4);

		assertQuaternionfEquals(q1, q3, 1e-5);
	}

	@Test
	public void testMultiplyInverse3()
	{
		Quaternionf q1 = new Quaternionf();
		Quaternionf q2 = new Quaternionf();

		createRandomQuaternion(random, q1);
		createRandomQuaternion(random, q2);

		Quat4d q3 = new Quat4d();
		Quat4d q4 = new Quat4d();

		q3.set(q1.x, q1.y, q1.z, q1.w);
		q4.set(q2.x, q2.y, q2.z, q2.w);

		q1.multiplyInverse(q1, q2);
		q3.mulInverse(q3, q4);

		assertQuaternionfEquals(q1, q3, 1e-5);
	}

	@Test
	public void testRotations()
	{
		Quaternionf q = new Quaternionf();
		q.set(new AxisAnglef(new Vector3f(1.0f, 0.0f, -1.0f), (float)Math.PI));

		Vector3f v = new Vector3f(1, 2, 3);
		RotationMatrixf m = new RotationMatrixf();

		Vector3f vResult = new Vector3f(-3, -2, -1);

		String rotMethod = "";

		for (int i = 0; i < nTests; i++)
		{
			rotMethod += "q";
			Vector3f vp = rotate(q, v);
			System.out.println(rotMethod + ": " + vp + " ==> "
					+ Vector3fEquals(vResult, vp, 1e-5f));
			assertTrue(Vector3fEquals(vResult, vp, 1e-5f));
			m.set(q);

			rotMethod += "m";
			Vector3f vm = rotate(m, v);
			System.out.println(rotMethod + ": " + vm + " ==> "
					+ Vector3fEquals(vResult, vm, 1e-5f));
			assertTrue(Vector3fEquals(vResult, vm, 1e-5f));

			q.set(m);
		}
	}
	
	@Test
	public void testConjugate()
	{
		Quaternionf Q = new Quaternionf();
		
		createRandomQuaternion(random, Q);
		Quaternionf Q2 = new Quaternionf();
		Q.conjugate(Q2);
		
		assertEquals(Q.x,-Q2.x,1e-12);
		assertEquals(Q.y,-Q2.y,1e-12);
		assertEquals(Q.z,-Q2.z,1e-12);
	}
	
	@Test
	public void testRotateVector()
	{
		Quaternionf Q = new Quaternionf();
		Vector3f V = new Vector3f();
		Vector3f V2 = new Vector3f();
		
		createRandomQuaternion(random, Q);
		V.set(random.nextFloat(),random.nextFloat(),random.nextFloat());
		V2.set(V);
		RotationMatrixf R = new RotationMatrixf(Q);
		
		Q.rotate(V);
		R.rotate(V2);
		
		System.out.println(V + "\n");
		System.out.println(V2 + "\n");
		
		assertEquals(V.x,V2.x,1e-5f);
		assertEquals(V.y,V2.y,1e-5f);
		assertEquals(V.z,V2.z,1e-5f);
	}
	
	@Test
	public void testInverse()
	{
		Quaternionf Q = new Quaternionf();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomQuaternion(random, Q);
			Quaternionf Q2 = new Quaternionf(Q);
			Quaternionf Q3 = new Quaternionf(Q);
			
			Q2.inverse();
			
			Q3.multiplyInverse(Q);
			Q.multiply(Q2);
			assertQuaternionfEquals(Q, Q3, 1e-5f);
		}
	}
	
	@Test
	public void testInverse2()
	{
		Quaternionf Q = new Quaternionf();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomQuaternion(random, Q);
			Quaternionf Q2 = new Quaternionf();
			Quaternionf Q3 = new Quaternionf(Q);
			
			Q2.inverse(Q);
			
			Q3.multiplyInverse(Q);
			Q.multiply(Q2);
			assertQuaternionfEquals(Q, Q3, 1e-5f);
		}
	}

	private Vector3f rotate(RotationMatrixf m, Vector3f vector)
	{
		Vector3f tmpV = new Vector3f(vector);
		m.rotate(tmpV);
		return tmpV;
	}

	private Vector3f rotate(Quaternionf q, Vector3f vector)
	{
		Quaternionf q2 = new Quaternionf();
		q2.x = vector.x;
		q2.y = vector.y;
		q2.z = vector.z;
		q2.w = 0.0f;

		Quaternionf tmpQ = new Quaternionf();

		tmpQ.multiply(q, q2);
		tmpQ.multiplyInverse(q);

		return new Vector3f(tmpQ.x, tmpQ.y, tmpQ.z);
	}

	@SuppressWarnings("unused")
	private void assertQuaternionfEquals(Quaternionf q1, Quaternionf q2,
			double epsilon)
	{
		assertEquals(q1.x, q2.x, epsilon);
		assertEquals(q1.y, q2.y, epsilon);
		assertEquals(q1.z, q2.z, epsilon);
		assertEquals(q1.w, q2.w, epsilon);
	}

	@SuppressWarnings("unused")
	private void assertQuaternionfEquals(Quaternionf q1, Quat4d q2,
			double epsilon)
	{
		assertEquals(q1.x, q2.x, epsilon);
		assertEquals(q1.y, q2.y, epsilon);
		assertEquals(q1.z, q2.z, epsilon);
		assertEquals(q1.w, q2.w, epsilon);
	}

	@SuppressWarnings("unused")
	private void assertAxisAnglefEquals(AxisAnglef axisAngle1,
			AxisAnglef axisAngle2, double epsilon)
	{
		assertEquals(axisAngle1.x, axisAngle2.x, epsilon);
		assertEquals(axisAngle1.y, axisAngle2.y, epsilon);
		assertEquals(axisAngle2.z, axisAngle2.z, epsilon);
		assertEquals(axisAngle1.angle, axisAngle2.angle, epsilon);
	}

	@SuppressWarnings("unused")
	private void assertMatrixEquals(Matrix3d matrix1, Matrix3d matrix2,
			double epsilon)
	{
		assertEquals(matrix1.m00, matrix2.m00, epsilon);
		assertEquals(matrix1.m01, matrix2.m01, epsilon);
		assertEquals(matrix1.m02, matrix2.m02, epsilon);
		assertEquals(matrix1.m10, matrix2.m10, epsilon);
		assertEquals(matrix1.m11, matrix2.m11, epsilon);
		assertEquals(matrix1.m12, matrix2.m12, epsilon);
		assertEquals(matrix1.m20, matrix2.m20, epsilon);
		assertEquals(matrix1.m21, matrix2.m21, epsilon);
		assertEquals(matrix1.m22, matrix2.m22, epsilon);
	}

	private boolean Vector3fEquals(Vector3f v1, Vector3f v2, double epsilon)
	{
		return (Math.abs(v1.x - v2.x) < epsilon
				&& Math.abs(v1.y - v2.y) < epsilon && Math.abs(v1.z - v2.z) < epsilon);
	}

	@SuppressWarnings("unused")
	private void createRandomRotationMatrix(Matrix3d matrix, Random random)
	{
		Matrix3d rotX = new Matrix3d();
		Matrix3d rotY = new Matrix3d();
		Matrix3d rotZ = new Matrix3d();

		createRandomRotationMatrixX(random, rotX);
		createRandomRotationMatrixY(random, rotY);
		createRandomRotationMatrixZ(random, rotZ);

		rotX.multiply(rotY);
		rotX.multiply(rotZ);

		matrix.m00 = rotX.m00;
		matrix.m01 = rotX.m01;
		matrix.m02 = rotX.m02;
		matrix.m10 = rotX.m10;
		matrix.m11 = rotX.m11;
		matrix.m12 = rotX.m12;
		matrix.m20 = rotX.m20;
		matrix.m21 = rotX.m21;
		matrix.m22 = rotX.m22;
	}

	private void createRandomRotationMatrixX(Random random, Matrix3d matrix)
	{
		double theta = random.nextFloat();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = 1;
		matrix.m01 = 0;
		matrix.m02 = 0;
		matrix.m10 = 0;
		matrix.m11 = cTheta;
		matrix.m12 = -sTheta;
		matrix.m20 = 0;
		matrix.m21 = sTheta;
		matrix.m22 = cTheta;
	}

	private void createRandomRotationMatrixY(Random random, Matrix3d matrix)
	{
		double theta = random.nextFloat();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = cTheta;
		matrix.m01 = 0;
		matrix.m02 = sTheta;
		matrix.m10 = 0;
		matrix.m11 = 1;
		matrix.m12 = 0;
		matrix.m20 = -sTheta;
		matrix.m21 = 0;
		matrix.m22 = cTheta;
	}

	private void createRandomAxisAngle(Random random, AxisAnglef axisAngle)
	{
		axisAngle.x = random.nextFloat();
		axisAngle.y = random.nextFloat();
		axisAngle.z = random.nextFloat();
		double mag = Math.sqrt(axisAngle.x * axisAngle.x + axisAngle.y
				* axisAngle.y + axisAngle.z * axisAngle.z);

		axisAngle.x *= 1 / mag;
		axisAngle.y *= 1 / mag;
		axisAngle.z *= 1 / mag;

		axisAngle.angle = (float)(random.nextFloat() * Math.PI);
	}

	@SuppressWarnings("unused")
	private void createRandomQuaternion(Random random, Quaternionf q)
	{
		q.x = random.nextFloat();
		q.y = random.nextFloat();
		q.z = random.nextFloat();
		q.w = random.nextFloat();

		double val = 1 / (Math.sqrt(q.x * q.x + q.y * q.y + q.z * q.z + q.w
				* q.w));

		q.x *= val;
		q.y *= val;
		q.z *= val;
		q.w *= val;
	}

	private void createRandomRotationMatrixZ(Random random, Matrix3d matrix)
	{
		double theta = random.nextFloat();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = cTheta;
		matrix.m01 = -sTheta;
		matrix.m02 = 0;
		matrix.m10 = sTheta;
		matrix.m11 = cTheta;
		matrix.m12 = 0;
		matrix.m20 = 0;
		matrix.m21 = 0;
		matrix.m22 = 1;
	}
}
