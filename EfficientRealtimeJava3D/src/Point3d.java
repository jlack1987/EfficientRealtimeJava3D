public class Point3d implements java.io.Serializable
{
	private static final long serialVersionUID = -58222232046391492L;

	double x, y, z;

	public Point3d()
	{
		set(0, 0, 0);
	}

	public Point3d(double[] array)
	{
		set(array);
	}

	public Point3d(float[] array)
	{
		set(array);
	}

	public void set(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void set(double[] array)
	{
		set(array[0], array[1], array[2]);
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

	public void add(Point3d p1, Point3d p2)
	{
		this.x = p1.x + p2.x;
		this.y = p1.y + p2.y;
		this.z = p1.z + p2.z;
	}

	public void subtract(Point3d point)
	{
		this.x -= point.x;
		this.y -= point.y;
		this.z -= point.z;
	}

	public void subtract(Point3d p1, Point3d p2)
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

	public void scale(double scale)
	{
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
	}

	public boolean equals(Point3d point)
	{
		return (this.x == point.x && this.y == point.y && this.z == point.z);
	}

	public boolean epsilonEquals(Point3d point, double epsilon)
	{
		return (Math.abs(point.x - this.x) < epsilon
				&& Math.abs(point.y - this.y) < epsilon && Math.abs(point.z
				- this.z) < epsilon);
	}

	public String toString()
	{
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
