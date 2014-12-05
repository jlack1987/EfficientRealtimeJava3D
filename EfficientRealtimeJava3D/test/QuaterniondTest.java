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
	public void testCreateQuaternionFromAxisAngled()
	{
		Quaterniond quat1 = new Quaterniond();
		Quaterniond quat2 = new Quaterniond();
		RotationMatrixd mat = new RotationMatrixd();

		for (int i = 0; i < nTests; i++)
		{
			createRandomQuaternion(random, quat1);
			mat.set(quat1);
			quat2.set(mat);

			assertQuaterniondEquals(quat1, quat2, 1e-8);
		}
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

	public static Vector3d rotateVector(Quat4d q, Vector3d v)
	{
		Quat4d w = new Quat4d();
		w.x = v.x;
		w.y = v.y;
		w.z = v.z;
		w.w = 0;

		Quat4d qTmp = new Quat4d();

		// q * w * q^-1

		qTmp.mul(q, w);
		qTmp.mulInverse(q);

		return new Vector3d(qTmp.x, qTmp.y, qTmp.z);
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

	private void assertAxisAngledEquals(AxisAngled axisAngle1,
			AxisAngled axisAngle2, double epsilon)
	{
		assertEquals(axisAngle1.x, axisAngle2.x, epsilon);
		assertEquals(axisAngle1.y, axisAngle2.y, epsilon);
		assertEquals(axisAngle2.z, axisAngle2.z, epsilon);
		assertEquals(axisAngle1.angle, axisAngle2.angle, epsilon);
	}

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
