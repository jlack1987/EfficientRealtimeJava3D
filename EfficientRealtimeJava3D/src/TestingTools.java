import static org.junit.Assert.assertEquals;

import java.util.Random;

public class TestingTools
{
	public static boolean checkOrthogonality(RigidBodyTransform transform)
	{
		RotationMatrixd matrix = new RotationMatrixd();
		transform.getRotation(matrix);

		Vector3d tmpVecX = new Vector3d(matrix.m00, matrix.m10, matrix.m20);
		Vector3d tmpVecY = new Vector3d(matrix.m01, matrix.m11, matrix.m21);
		Vector3d tmpVecZ = new Vector3d(matrix.m02, matrix.m12, matrix.m22);

		return (tmpVecX.lengthSquared() - 1 < 1e-8)
				&& (tmpVecY.lengthSquared() - 1 < 1e-8)
				&& (tmpVecZ.lengthSquared() - 1 < 1e-8);
	}

	public static void randomizeVector(Random random, Vector3d vector)
	{
		vector.x = random.nextDouble();
		vector.y = random.nextDouble();
		vector.z = random.nextDouble();
	}

	public static void randomizeVector(Random random, Vector3f vector)
	{
		vector.x = random.nextFloat();
		vector.y = random.nextFloat();
		vector.z = random.nextFloat();
	}

	public static void randomizePoint3d(Random random, Point3d point)
	{
		point.x = random.nextDouble();
		point.y = random.nextDouble();
		point.z = random.nextDouble();
	}

	public static void randomizePoint3f(Random random, Point3f point)
	{
		point.x = random.nextFloat();
		point.y = random.nextFloat();
		point.z = random.nextFloat();
	}

	public static void createRandomTransformationMatrix(Matrix4d matrix, Random random)
	{
		RotationMatrixd rotX = new RotationMatrixd();
		RotationMatrixd rotY = new RotationMatrixd();
		RotationMatrixd rotZ = new RotationMatrixd();
		Vector3d trans = new Vector3d();

		randomizeVector(random, trans);
		createRandomRotationMatrixX(random, rotX);
		createRandomRotationMatrixY(random, rotY);
		createRandomRotationMatrixZ(random, rotZ);

		rotX.multiply(rotY);
		rotX.multiply(rotZ);

		matrix.set(0, 0, rotX.m00);
		matrix.set(0, 1, rotX.m01);
		matrix.set(0, 2, rotX.m02);
		matrix.set(0, 3, trans.x);
		matrix.set(1, 0, rotX.m10);
		matrix.set(1, 1, rotX.m11);
		matrix.set(1, 2, rotX.m12);
		matrix.set(1, 3, trans.y);
		matrix.set(2, 0, rotX.m20);
		matrix.set(2, 1, rotX.m21);
		matrix.set(2, 2, rotX.m22);
		matrix.set(2, 3, trans.z);
		matrix.set(3, 0, 0);
		matrix.set(3, 1, 0);
		matrix.set(3, 2, 0);
		matrix.set(3, 3, 1);
	}

	public static void createRandomRotationMatrix(RotationMatrixd matrix, Random random)
	{
		RotationMatrixd rotX = new RotationMatrixd();
		RotationMatrixd rotY = new RotationMatrixd();
		RotationMatrixd rotZ = new RotationMatrixd();
		Vector3d trans = new Vector3d();

		randomizeVector(random, trans);
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

	public static void createRandomRotationMatrix(RotationMatrixf matrix, Random random)
	{
		RotationMatrixf rotX = new RotationMatrixf();
		RotationMatrixf rotY = new RotationMatrixf();
		RotationMatrixf rotZ = new RotationMatrixf();
		Vector3f trans = new Vector3f();

		randomizeVector(random, trans);
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

	public static void createRandomTransformationMatrix(Matrix4f matrix, Random random)
	{
		RotationMatrixd rotX = new RotationMatrixd();
		RotationMatrixd rotY = new RotationMatrixd();
		RotationMatrixd rotZ = new RotationMatrixd();
		Vector3d trans = new Vector3d();

		randomizeVector(random, trans);
		createRandomRotationMatrixX(random, rotX);
		createRandomRotationMatrixY(random, rotY);
		createRandomRotationMatrixZ(random, rotZ);

		rotX.multiply(rotY);
		rotX.multiply(rotZ);

		matrix.m00 = (float) rotX.m00;
		matrix.m01 = (float) rotX.m01;
		matrix.m02 = (float) rotX.m02;
		matrix.m03 = (float) trans.x;
		matrix.m10 = (float) rotX.m10;
		matrix.m11 = (float) rotX.m11;
		matrix.m12 = (float) rotX.m12;
		matrix.m13 = (float) trans.y;
		matrix.m20 = (float) rotX.m20;
		matrix.m21 = (float) rotX.m21;
		matrix.m22 = (float) rotX.m22;
		matrix.m23 = (float) trans.z;
		matrix.m30 = 0;
		matrix.m31 = 0;
		matrix.m32 = 0;
		matrix.m33 = 1;
	}

	public static void createRandomRotationMatrixX(Random random,
			RotationMatrixd matrix)
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

	public static void createRandomRotationMatrixX(Random random,
			RotationMatrixf matrix)
	{
		double theta = random.nextDouble();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = 1;
		matrix.m01 = 0;
		matrix.m02 = 0;
		matrix.m10 = 0;
		matrix.m11 = (float) cTheta;
		matrix.m12 = (float) -sTheta;
		matrix.m20 = 0;
		matrix.m21 = (float) sTheta;
		matrix.m22 = (float) cTheta;
	}

	public static void createRandomRotationMatrixY(Random random,
			RotationMatrixf matrix)
	{
		double theta = random.nextDouble();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = (float) cTheta;
		matrix.m01 = 0;
		matrix.m02 = (float) sTheta;
		matrix.m10 = 0;
		matrix.m11 = 1;
		matrix.m12 = 0;
		matrix.m20 = (float) -sTheta;
		matrix.m21 = 0;
		matrix.m22 = (float) cTheta;
	}

	public static void createRandomRotationMatrixY(Random random,
			RotationMatrixd matrix)
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

	public static void createRandomRotationMatrixZ(Random random,
			RotationMatrixd matrix)
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

	public static void createRandomRotationMatrixZ(Random random,
			RotationMatrixf matrix)
	{
		double theta = random.nextDouble();
		double cTheta = Math.cos(theta);
		double sTheta = Math.sin(theta);
		matrix.m00 = (float) cTheta;
		matrix.m01 = (float) -sTheta;
		matrix.m02 = 0;
		matrix.m10 = (float) sTheta;
		matrix.m11 = (float) cTheta;
		matrix.m12 = 0;
		matrix.m20 = 0;
		matrix.m21 = 0;
		matrix.m22 = 1;
	}

	public static void createRandomTransform4Vector(Vector4d vector, Random random)
	{
		vector.x = random.nextDouble();
		vector.y = random.nextDouble();
		vector.z = random.nextDouble();
		vector.w = 1;
	}

	public static void createRandomTransform4Vector(Vector4f vector, Random random)
	{
		vector.x = random.nextFloat();
		vector.y = random.nextFloat();
		vector.z = random.nextFloat();
		vector.w = 1;
	}

	public static float[] putDoublesInFloatArray(double[] matrixAsDoubleArray)
	{
		float[] ret = new float[matrixAsDoubleArray.length];
		for (int i = 0; i < matrixAsDoubleArray.length; i++)
		{
			ret[i] = (float) matrixAsDoubleArray[i];
		}

		return ret;
	}

	public static void createFloatArrayFromMatrix4d(float[] floatArray, Matrix4f matrix)
	{
		floatArray[0] = matrix.m00;
		floatArray[1] = matrix.m01;
		floatArray[2] = matrix.m02;
		floatArray[3] = matrix.m03;
		floatArray[4] = matrix.m10;
		floatArray[5] = matrix.m11;
		floatArray[6] = matrix.m12;
		floatArray[7] = matrix.m13;
		floatArray[8] = matrix.m20;
		floatArray[9] = matrix.m21;
		floatArray[10] = matrix.m22;
		floatArray[11] = matrix.m23;
		floatArray[12] = matrix.m30;
		floatArray[13] = matrix.m31;
		floatArray[14] = matrix.m32;
		floatArray[15] = matrix.m33;
	}

	public static void messWithRotationMatrixOrthogonality(Matrix4d matrix,
			Random random)
	{
		matrix.m00 += random.nextDouble() * 1e-3;
		matrix.m01 += random.nextDouble() * 1e-3;
		matrix.m02 += random.nextDouble() * 1e-3;
		matrix.m10 += random.nextDouble() * 1e-3;
		matrix.m11 += random.nextDouble() * 1e-3;
		matrix.m12 += random.nextDouble() * 1e-3;
		matrix.m20 += random.nextDouble() * 1e-3;
		matrix.m21 += random.nextDouble() * 1e-3;
		matrix.m22 += random.nextDouble() * 1e-3;
	}

	public static void assertRotationMatrixdEquals(RotationMatrixd matrix1,
			RotationMatrixd matrix2, double epsilon)
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

	public static void assertVector3dEquals(String string, Vector3d vector1,
			Vector3d vector2, double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
	}

	public static void assertVector3fEquals(String string, Vector3f vector1,
			Vector3f vector2, double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
	}

	public static void assertPoint3dEquals(String string, Point3d vector1,
			Point3d vector2, double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
	}

	public static void assertPoint3fEquals(String string, Point3f vector1,
			Point3f vector2, double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
	}

	public static void assertVector4dEquals(String string, Vector4d vector1,
			Vector4d vector2, double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
		assertEquals(vector1.w, vector2.w, epsilon);
	}

	public static void assertVector4fEquals(String string, Vector4f vector1,
			Vector4f vector2, double epsilon)
	{
		assertEquals(vector1.x, vector2.x, epsilon);
		assertEquals(vector1.y, vector2.y, epsilon);
		assertEquals(vector1.z, vector2.z, epsilon);
		assertEquals(vector1.w, vector2.w, epsilon);
	}

	public static void assertMatrix4dEquals(String string, Matrix4d m1, Matrix4d m2,
			double epsilon)
	{
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(m1.get(i, j), m2.get(i, j), epsilon);
			}
		}
	}

	public static void assertMatrix4fEquals(String string, Matrix4f m1, Matrix4f m2,
			double epsilon)
	{
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(m1.get(i, j), m2.get(i, j), epsilon);
			}
		}
	}

	public static void assertMatrix3dEquals(String string, Matrix3d m1, Matrix3d m2,
			double epsilon)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				assertEquals(m1.get(i, j), m2.get(i, j), epsilon);
			}
		}
	}

	public static void assertMatrix3fEquals(String string, Matrix3f m1, Matrix3f m2,
			double epsilon)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				assertEquals(m1.get(i, j), m2.get(i, j), epsilon);
			}
		}
	}

	public static Vector4d multiplyMatrix4dByVector4d(Matrix4d M, Vector4d V)
	{
		Vector4d ret = new Vector4d();

		ret.x = V.x * M.m00 + V.y * M.m01 + V.z * M.m02 + V.w * M.m03;
		ret.y = V.x * M.m10 + V.y * M.m11 + V.z * M.m12 + V.w * M.m13;
		ret.z = V.x * M.m20 + V.y * M.m21 + V.z * M.m22 + V.w * M.m23;
		ret.w = V.x * M.m30 + V.y * M.m31 + V.z * M.m32 + V.w * M.m33;

		return ret;
	}

	public static Vector4f multiplyMatrix4dByVector4d(Matrix4d M, Vector4f V)
	{
		Vector4f ret = new Vector4f();

		ret.x = (float) (V.x * M.m00 + V.y * M.m01 + V.z * M.m02 + V.w * M.m03);
		ret.y = (float) (V.x * M.m10 + V.y * M.m11 + V.z * M.m12 + V.w * M.m13);
		ret.z = (float) (V.x * M.m20 + V.y * M.m21 + V.z * M.m22 + V.w * M.m23);
		ret.w = (float) (V.x * M.m30 + V.y * M.m31 + V.z * M.m32 + V.w * M.m33);

		return ret;
	}

	public static Vector4f multiplyMatrix4dByVector4d(Matrix4f M, Vector4f V)
	{
		Vector4f ret = new Vector4f();

		ret.x = (float) (V.x * M.m00 + V.y * M.m01 + V.z * M.m02 + V.w * M.m03);
		ret.y = (float) (V.x * M.m10 + V.y * M.m11 + V.z * M.m12 + V.w * M.m13);
		ret.z = (float) (V.x * M.m20 + V.y * M.m21 + V.z * M.m22 + V.w * M.m23);
		ret.w = (float) (V.x * M.m30 + V.y * M.m31 + V.z * M.m32 + V.w * M.m33);

		return ret;
	}
}
