import static org.junit.Assert.*;

import java.util.Random;

import javax.vecmath.AxisAngle4d;
import javax.vecmath.Quat4d;

import org.junit.Test;

public class QuaterniondTest
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
	public void testCreateQuaternionFromVector4d()
	{
		Vector4d V = new Vector4d();
		
		for(int i = 0; i<nTests; i++)
		{
			V.set(random.nextDouble(),random.nextDouble(),random.nextDouble(),random.nextDouble());
			V.normalize();
			Quaterniond Q = new Quaterniond(V);
			
			assertEquals(V.x,Q.x,1e-12);
			assertEquals(V.y,Q.y,1e-12);
			assertEquals(V.z,Q.z,1e-12);
			assertEquals(V.w,Q.w,1e-12);
		}
	}

	@Test
	public void testCreateQuaternionFromRotationMatrixd()
	{
		Quaterniond quat1 = new Quaterniond();

		for (int i = 0; i < nTests; i++)
		{
			createRandomQuaternion(random, quat1);
			RotationMatrixd mat = new RotationMatrixd(quat1);
			Quaterniond quat2 = new Quaterniond(mat);

			assertQuaterniondEquals(quat1, quat2, 1e-8);
		}
	}
	
	@Test
	public void testCreateQuaternionFromAxisAngled()
	{
		Quaterniond quat1 = new Quaterniond();
		Quaterniond quat2 = new Quaterniond();

		for (int i = 0; i < nTests; i++)
		{
			createRandomQuaternion(random, quat1);
			AxisAngled A = new AxisAngled(quat1);
			quat2.set(A);

			assertQuaterniondEquals(quat1, quat2, 1e-8);
		}
	}
	
	@Test
	public void testCreateQuaternionFromAxisAngled2()
	{
		Quaterniond quat1 = new Quaterniond();
		
		quat1.x = 0; quat1.y = 0; quat1.z = 0; quat1.w = 0;
		AxisAngled A = new AxisAngled(0,0,0,0);
		Quaterniond quat2 = new Quaterniond(A);

		assertQuaterniondEquals(quat1, quat2, 1e-8);
	}

	@Test
	public void testQuaternionConjugate()
	{
		Quaterniond quat = new Quaterniond();
		Quaterniond quat2 = new Quaterniond();

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
		Quaterniond q1 = new Quaterniond();
		Quaterniond q2 = new Quaterniond();

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
	
			assertQuaterniondEquals(q1, q3, 1e-5);
		}
	}
	
	@Test
	public void testMultiply2()
	{
		Quaterniond q1 = new Quaterniond();
		Quaterniond q2 = new Quaterniond();
		Quaterniond q3 = new Quaterniond();

		for(int i = 0; i<nTests; i++)
		{
			createRandomQuaternion(random, q1);
			q3.set(q1);
			createRandomQuaternion(random, q2);
	
			q3.multiply(q3,q2);
			q1.multiply(q2);
	
			assertQuaterniondEquals(q1, q3, 1e-5);
		}
	}
	
	@Test
	public void testNormalize()
	{
		double[] f = new double[4];
		
		f[0] = random.nextDouble();
		f[1] = random.nextDouble();
		f[2] = random.nextDouble();
		f[3] = random.nextDouble();
		Quaterniond Q = new Quaterniond(f);
		Q.normalize();
		
		double mag = Math.sqrt(Q.x*Q.x+Q.y*Q.y+Q.z*Q.z+Q.w*Q.w);
		Q.x/=mag;
		Q.y/=mag;
		Q.z/=mag;
		Q.w/=mag;
		
		assertEquals(Math.sqrt(Q.x*Q.x+Q.y*Q.y+Q.z*Q.z+Q.w*Q.w),1.0,1e-12);
	}
	
	@Test
	public void testNormalize2()
	{
		double[] f = new double[4];
		Quaterniond Q1 = new Quaterniond();
		Q1.toString();
		for(int i = 0; i<nTests; i++)
		{
			f[0] = random.nextDouble();
			f[1] = random.nextDouble();
			f[2] = random.nextDouble();
			f[3] = random.nextDouble();
			Quaterniond Q = new Quaterniond(f);
			Q1.normalize(Q);
			
			double mag = Math.sqrt(Q1.x*Q1.x+Q1.y*Q1.y+Q1.z*Q1.z+Q1.w*Q1.w);
			Q1.x/=mag;
			Q1.y/=mag;
			Q1.z/=mag;
			Q1.w/=mag;
			
			assertEquals(Math.sqrt(Q1.x*Q1.x+Q1.y*Q1.y+Q1.z*Q1.z+Q1.w*Q1.w),1.0,1e-12);
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
		Quaterniond Q = new Quaterniond(f);
		Q.normalize();
		
		assertEquals(Math.sqrt(Q.x*Q.x+Q.y*Q.y+Q.z*Q.z+Q.w*Q.w),0,1e-12);
	}

	@Test
	public void testMultiplyInverse()
	{
		Quaterniond q1 = new Quaterniond();
		Quaterniond q2 = new Quaterniond();

		createRandomQuaternion(random, q1);
		createRandomQuaternion(random, q2);

		Quat4d q3 = new Quat4d();
		Quat4d q4 = new Quat4d();

		q3.set(q1.x, q1.y, q1.z, q1.w);
		q4.set(q2.x, q2.y, q2.z, q2.w);

		q1.multiplyInverse(q2);
		q3.mulInverse(q4);

		assertQuaterniondEquals(q1, q3, 1e-5);
	}

	@Test
	public void testMultiplyInverse2()
	{
		Quaterniond q1 = new Quaterniond();
		Quaterniond q2 = new Quaterniond();

		createRandomQuaternion(random, q1);
		createRandomQuaternion(random, q2);

		Quat4d q3 = new Quat4d();
		Quat4d q4 = new Quat4d();

		q3.set(q1.x, q1.y, q1.z, q1.w);
		q4.set(q2.x, q2.y, q2.z, q2.w);

		q1.multiplyInverse(q2, q2);
		q3.mulInverse(q4, q4);

		assertQuaterniondEquals(q1, q3, 1e-5);
	}

	@Test
	public void testMultiplyInverse3()
	{
		Quaterniond q1 = new Quaterniond();
		Quaterniond q2 = new Quaterniond();

		createRandomQuaternion(random, q1);
		createRandomQuaternion(random, q2);

		Quat4d q3 = new Quat4d();
		Quat4d q4 = new Quat4d();

		q3.set(q1.x, q1.y, q1.z, q1.w);
		q4.set(q2.x, q2.y, q2.z, q2.w);

		q1.multiplyInverse(q1, q2);
		q3.mulInverse(q3, q4);

		assertQuaterniondEquals(q1, q3, 1e-5);
	}

	@Test
	public void testRotations()
	{
		Quaterniond q = new Quaterniond();
		q.set(new AxisAngled(new Vector3d(1, 0, -1), Math.PI));

		Vector3d v = new Vector3d(1, 2, 3);
		RotationMatrixd m = new RotationMatrixd();

		Vector3d vResult = new Vector3d(-3, -2, -1);

		String rotMethod = "";

		for (int i = 0; i < nTests; i++)
		{
			rotMethod += "q";
			Vector3d vp = rotate(q, v);
			System.out.println(rotMethod + ": " + vp + " ==> "
					+ vector3dEquals(vResult, vp, 1e-8));
			assertTrue(vector3dEquals(vResult, vp, 1e-8));
			m.set(q);

			rotMethod += "m";
			Vector3d vm = rotate(m, v);
			System.out.println(rotMethod + ": " + vm + " ==> "
					+ vector3dEquals(vResult, vm, 1e-8));
			assertTrue(vector3dEquals(vResult, vm, 1e-8));

			q.set(m);
		}
	}
	
	@Test
	public void testConjugate()
	{
		Quaterniond Q = new Quaterniond();
		
		createRandomQuaternion(random, Q);
		Quaterniond Q2 = new Quaterniond();
		Q.conjugate(Q2);
		
		assertEquals(Q.x,-Q2.x,1e-12);
		assertEquals(Q.y,-Q2.y,1e-12);
		assertEquals(Q.z,-Q2.z,1e-12);
	}
	
	@Test
	public void testRotateVector()
	{
		Quaterniond Q = new Quaterniond();
		Vector3d V = new Vector3d();
		Vector3d V2 = new Vector3d();
		
		createRandomQuaternion(random, Q);
		V.set(random.nextDouble(),random.nextDouble(),random.nextDouble());
		V2.set(V);
		RotationMatrixd R = new RotationMatrixd(Q);
		
		Q.rotate(V);
		R.rotate(V2);
		
		System.out.println(V + "\n");
		System.out.println(V2 + "\n");
		
		assertEquals(V.x,V2.x,1e-12);
		assertEquals(V.y,V2.y,1e-12);
		assertEquals(V.z,V2.z,1e-12);
	}
	
	@Test
	public void testInverse()
	{
		Quaterniond Q = new Quaterniond();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomQuaternion(random, Q);
			Quaterniond Q2 = new Quaterniond(Q);
			Quaterniond Q3 = new Quaterniond(Q);
			
			Q2.inverse();
			
			Q3.multiplyInverse(Q);
			Q.multiply(Q2);
			assertQuaterniondEquals(Q, Q3, 1e-12);
		}
	}
	
	@Test
	public void testInverse2()
	{
		Quaterniond Q = new Quaterniond();
		
		for(int i = 0; i<nTests; i++)
		{
			createRandomQuaternion(random, Q);
			Quaterniond Q2 = new Quaterniond();
			Quaterniond Q3 = new Quaterniond(Q);
			
			Q2.inverse(Q);
			
			Q3.multiplyInverse(Q);
			Q.multiply(Q2);
			assertQuaterniondEquals(Q, Q3, 1e-12);
		}
	}

	private Vector3d rotate(RotationMatrixd m, Vector3d vector)
	{
		Vector3d tmpV = new Vector3d(vector);
		m.rotate(tmpV);
		return tmpV;
	}

	private Vector3d rotate(Quaterniond q, Vector3d vector)
	{
		Quaterniond q2 = new Quaterniond();
		q2.x = vector.x;
		q2.y = vector.y;
		q2.z = vector.z;
		q2.w = 0.0;

		Quaterniond tmpQ = new Quaterniond();

		tmpQ.multiply(q, q2);
		tmpQ.multiplyInverse(q);

		return new Vector3d(tmpQ.x, tmpQ.y, tmpQ.z);
	}

	@SuppressWarnings("unused")
	private void assertQuaterniondEquals(Quaterniond q1, Quaterniond q2,
			double epsilon)
	{
		assertEquals(q1.x, q2.x, epsilon);
		assertEquals(q1.y, q2.y, epsilon);
		assertEquals(q1.z, q2.z, epsilon);
		assertEquals(q1.w, q2.w, epsilon);
	}

	@SuppressWarnings("unused")
	private void assertQuaterniondEquals(Quaterniond q1, Quat4d q2,
			double epsilon)
	{
		assertEquals(q1.x, q2.x, epsilon);
		assertEquals(q1.y, q2.y, epsilon);
		assertEquals(q1.z, q2.z, epsilon);
		assertEquals(q1.w, q2.w, epsilon);
	}

	@SuppressWarnings("unused")
	private void assertAxisAngledEquals(AxisAngled axisAngle1,
			AxisAngled axisAngle2, double epsilon)
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

	private boolean vector3dEquals(Vector3d v1, Vector3d v2, double epsilon)
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
		double theta = random.nextDouble();
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
		double theta = random.nextDouble();
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

	private void createRandomAxisAngle(Random random, AxisAngled axisAngle)
	{
		axisAngle.x = random.nextDouble();
		axisAngle.y = random.nextDouble();
		axisAngle.z = random.nextDouble();
		double mag = Math.sqrt(axisAngle.x * axisAngle.x + axisAngle.y
				* axisAngle.y + axisAngle.z * axisAngle.z);

		axisAngle.x *= 1 / mag;
		axisAngle.y *= 1 / mag;
		axisAngle.z *= 1 / mag;

		axisAngle.angle = random.nextDouble() * Math.PI;
	}

	@SuppressWarnings("unused")
	private void createRandomQuaternion(Random random, Quaterniond q)
	{
		q.x = random.nextDouble();
		q.y = random.nextDouble();
		q.z = random.nextDouble();
		q.w = random.nextDouble();

		double val = 1 / (Math.sqrt(q.x * q.x + q.y * q.y + q.z * q.z + q.w
				* q.w));

		q.x *= val;
		q.y *= val;
		q.z *= val;
		q.w *= val;
	}

	private void createRandomRotationMatrixZ(Random random, Matrix3d matrix)
	{
		double theta = random.nextDouble();
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
