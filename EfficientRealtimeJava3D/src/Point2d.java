
public class Point2d implements java.io.Serializable
{
	private static final long serialVersionUID = -5764459503857837272L;

	double x,y;
	
	public Point2d()
	{
		set(0, 0);
	}
	
	public Point2d(double x, double y)
	{
		set(x,y);
	}
	
	public Point2d(Point2d p)
	{
		set(p);
	}

	public void set(Point2d point)
	{
		set(point.x, point.y);
	}

	public Point2d(double[] array)
	{
		if(array.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}
		
		set(array);
	}

	public Point2d(float[] array)
	{
		if(array.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}
		
		set(array);
	}
	
	public Point2d(Vector2d v)
	{
		set(v);
	}
	
	public Point2d(Vector2f v)
	{
		set(v);
	}

	public void set(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector2d v)
	{
		set(v.x,v.y);
	}
	
	public void set(Vector2f v)
	{
		set(v.x,v.y);
	}

	public void set(double[] array)
	{
		if(array.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}
		
		set(array[0], array[1]);
	}

	public void set(float[] array)
	{
		if(array.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}
		set(array[0], array[1]);
	}

	public void add(Point2d point)
	{
		this.x += point.x;
		this.y += point.y;
	}
	
	public void add(Vector2d vector)
	{
		this.x += vector.x;
		this.y += vector.y;
	}
	
	public void add(double[] d)
	{
		this.x += d[0];
		this.y += d[1];
	}
	
	public void add(Vector2f vector)
	{
		this.x += vector.x;
		this.y += vector.y;
	}

	public void add(Point2d p1, Point2d p2)
	{
		this.x = p1.x + p2.x;
		this.y = p1.y + p2.y;
	}

	public void subtract(Point2d point)
	{
		this.x -= point.x;
		this.y -= point.y;
	}
	
	public void subtract(double[] d)
	{
		this.x -= d[0];
		this.y -= d[1];
	}
	
	public void subtract(float[] d)
	{
		this.x -= d[0];
		this.y -= d[1];
	}
	
	public void subtract(Vector2d vector)
	{
		this.x -= vector.x;
		this.y -= vector.y;
	}
	
	public void subtract(Vector2f vector)
	{
		this.x -= vector.x;
		this.y -= vector.y;
	}

	public void subtract(Point2d p1, Point2d p2)
	{
		this.x = p1.x - p2.x;
		this.y = p1.y - p2.y;
	}

	public void negate()
	{
		this.x = -this.x;
		this.y = -this.y;
	}

	public void scale(double scale)
	{
		this.x *= scale;
		this.y *= scale;
	}

	public double distance(Point2d point)
	{
		double dx = this.x - point.x;
		double dy = this.y - point.y;

		return Math.sqrt(dx * dx + dy * dy);
	}

	public double distanceSquared(Point2d point)
	{
		double dx = this.x - point.x;
		double dy = this.y - point.y;

		return (dx * dx + dy * dy);
	}

	public final double infinityNorm(Point2d point)
	{
		return Math.max(Math.abs(this.x - point.x),
				Math.abs(this.y - point.y));
	}

	public final double L1Norm(Point2d point)
	{
		return (Math.abs(this.x - point.x) + Math.abs(this.y - point.y));
	}

	public final boolean equals(Point2d point)
	{
		return (this.x == point.x && this.y == point.y);
	}

	public final boolean epsilonEquals(Point2d point, double epsilon)
	{
		return (Math.abs(point.x - this.x) < epsilon
				&& Math.abs(point.y - this.y) < epsilon);
	}

	public void clipMinMax(double min, double max, Point2d point)
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
