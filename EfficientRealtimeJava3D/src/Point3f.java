
public class Point3f implements java.io.Serializable
{
	private static final long serialVersionUID = 6641372550906361200L;

	float x,y,z;
	
	public Point3f()
	{
		set(0, 0, 0);
	}

	public void set(Point3f point)
	{
		set(point.x, point.y, point.z);
	}

	public Point3f(double[] array)
	{
		set(array);
	}

	public Point3f(float[] array)
	{
		set(array);
	}

	public void set(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void set(double[] array)
	{
		set((float)array[0], (float)array[1], (float)array[2]);
	}

	public void set(float[] array)
	{
		set(array[0], array[1], array[2]);
	}

	public void add(Point3d point)
	{
		this.x += point.x;
		this.y += point.y;
		this.z += point.z;
	}
	
	public void add(Vector3d vector)
	{
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
	}
	
	public void add(Vector3f vector)
	{
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
	}

	public void add(Point3f p1, Point3f p2)
	{
		this.x = p1.x + p2.x;
		this.y = p1.y + p2.y;
		this.z = p1.z + p2.z;
	}

	public void subtract(Point3f point)
	{
		this.x -= point.x;
		this.y -= point.y;
		this.z -= point.z;
	}
	
	public void subtract(Vector3d vector)
	{
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
	}
	
	public void subtract(Vector3f vector)
	{
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
	}

	public void subtract(Point3f p1, Point3f p2)
	{
		this.x = p1.x - p2.x;
		this.y = p1.y - p2.y;
		this.z = p1.z - p2.z;
	}

	public void negate()
	{
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
	}

	public void scale(float scale)
	{
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
	}

	public float distance(Point3f point)
	{
		float dx = this.x - point.x;
		float dy = this.y - point.y;
		float dz = this.z - point.z;

		return (float)Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public float distanceSquared(Point3f point)
	{
		float dx = this.x - point.x;
		float dy = this.y - point.y;
		float dz = this.z - point.z;

		return (dx * dx + dy * dy + dz * dz);
	}

	public final float infinityNorm(Point3f point)
	{
		return Math.max(Math.max(Math.abs(this.x - point.x),
				Math.abs(this.y - point.y)), Math.abs(this.z - point.z));
	}

	public final float L1Norm(Point3f point)
	{
		return (Math.abs(this.x - point.x) + Math.abs(this.y - point.y) + Math
				.abs(this.z - point.z));
	}

	public boolean equals(Point3f point)
	{
		return (this.x == point.x && this.y == point.y && this.z == point.z);
	}

	public boolean epsilonEquals(Point3f point, float epsilon)
	{
		return (Math.abs(point.x - this.x) < epsilon
				&& Math.abs(point.y - this.y) < epsilon && Math.abs(point.z
				- this.z) < epsilon);
	}

	public void clampMinMax(float min, float max, Point3f point)
	{
		if (point.x > max)
		{
			x = max;
		}
		else if (point.x < min)
		{
			x = min;
		}
		else
		{
			x = point.x;
		}

		if (point.y > max)
		{
			y = max;
		}
		else if (point.y < min)
		{
			y = min;
		}
		else
		{
			y = point.y;
		}

		if (point.z > max)
		{
			z = max;
		}
		else if (point.z < min)
		{
			z = min;
		}
		else
		{
			z = point.z;
		}
	}

	public String toString()
	{
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
