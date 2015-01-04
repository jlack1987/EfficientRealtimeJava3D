public class Point2f implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1839242958606973983L;

	float x, y;

	public Point2f()
	{
		set(0, 0);
	}

	public Point2f(float x, float y)
	{
		set(x, y);
	}

	public Point2f(Point2f p)
	{
		set(p);
	}

	public Point2f(float[] array)
	{
		if (array.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}

		set(array);
	}

	public Point2f(double[] array)
	{
		if (array.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}

		set(array);
	}

	public void set(Point2f point)
	{
		set(point.x, point.y);
	}

	public Point2f(Vector2f v)
	{
		set(v);
	}

	public Point2f(Vector2d v)
	{
		set(v);
	}

	public void set(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public void set(Vector2f v)
	{
		set(v.x, v.y);
	}

	public void set(Vector2d v)
	{
		set((float)v.x, (float)v.y);
	}

	public void set(double[] array)
	{
		if (array.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}

		set((float)array[0], (float)array[1]);
	}

	public void set(float[] array)
	{
		if (array.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}
		set(array[0], array[1]);
	}

	public void add(Point2f point)
	{
		this.x += point.x;
		this.y += point.y;
	}

	public void add(Vector2f vector)
	{
		this.x += vector.x;
		this.y += vector.y;
	}

	public void add(float[] d)
	{
		this.x += d[0];
		this.y += d[1];
	}

	public void add(Vector2d vector)
	{
		this.x += vector.x;
		this.y += vector.y;
	}

	public void add(Point2f p1, Point2f p2)
	{
		this.x = p1.x + p2.x;
		this.y = p1.y + p2.y;
	}

	public void subtract(Point2f point)
	{
		this.x -= point.x;
		this.y -= point.y;
	}

	public void subtract(float[] d)
	{
		this.x -= d[0];
		this.y -= d[1];
	}

	public void subtract(double[] d)
	{
		this.x -= d[0];
		this.y -= d[1];
	}

	public void subtract(Vector2f vector)
	{
		this.x -= vector.x;
		this.y -= vector.y;
	}

	public void subtract(Vector2d vector)
	{
		this.x -= vector.x;
		this.y -= vector.y;
	}

	public void subtract(Point2f p1, Point2f p2)
	{
		this.x = p1.x - p2.x;
		this.y = p1.y - p2.y;
	}

	public void negate()
	{
		this.x = -this.x;
		this.y = -this.y;
	}

	public void scale(float scale)
	{
		this.x *= scale;
		this.y *= scale;
	}

	public float distance(Point2f point)
	{
		float dx = this.x - point.x;
		float dy = this.y - point.y;

		return (float)Math.sqrt(dx * dx + dy * dy);
	}

	public float distanceSquared(Point2f point)
	{
		float dx = this.x - point.x;
		float dy = this.y - point.y;

		return (dx * dx + dy * dy);
	}

	public final float infinityNorm(Point2f point)
	{
		return (float)(Math.max(Math.abs(this.x - point.x), Math.abs(this.y - point.y)));
	}

	public final float L1Norm(Point2f point)
	{
		return (float)((Math.abs(this.x - point.x) + Math.abs(this.y - point.y)));
	}

	public final boolean equals(Point2f point)
	{
		return (this.x == point.x && this.y == point.y);
	}

	public final boolean epsilonEquals(Point2f point, float epsilon)
	{
		return (Math.abs(point.x - this.x) < epsilon && Math.abs(point.y
				- this.y) < epsilon);
	}

	public final void clipMinMax(float min, float max, Point2f point)
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
	}

	public String toString()
	{
		return "(" + this.x + ", " + this.y + ")";
	}
}
