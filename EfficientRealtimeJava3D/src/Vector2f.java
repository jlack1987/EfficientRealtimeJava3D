
public class Vector2f implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6960394875343338569L;
	
	float x,y;

	public Vector2f(float x, float y)
	{
		set(x, y);
	}
	
	public Vector2f()
	{
		set(0,0);
	}

	public Vector2f(Vector2f v)
	{
		set(v);
	}

	public Vector2f(double[] d)
	{
		set(d);
	}

	public Vector2f(float[] f)
	{
		set(f);
	}

	public void set(double[] d)
	{
		if (d.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}

		set((float)d[0], (float)d[1]);
	}

	public void set(float[] f)
	{
		if (f.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}

		set(f[0], f[1]);
	}

	public void set(Vector2f v)
	{
		set(v.x, v.y);
	}

	public void set(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public void add(Vector3f vector)
	{
		this.x = this.x + vector.x;
		this.y = this.y + vector.y;
	}

	public void subtract(Vector3f vector)
	{
		this.x = this.x - vector.x;
		this.y = this.y - vector.y;
	}

	public float dot(Vector3f vector)
	{
		return this.x * vector.x + this.y * vector.y;
	}

	public float length()
	{
		return (float)Math.sqrt(x * x + y * y);
	}

	public void normalize()
	{
		float length = length();
		x /= length;
		y /= length;
	}

	public void normalize(Vector2f vector)
	{
		if (this != vector)
		{
			set(vector);
			normalize();
		}
		else
		{
			normalize();
		}
	}

	public float lengthSquared()
	{
		return (x * x + y * y);
	}

	public double angle(Vector3f vector1)
	{
		double vDot = dot(vector1) / (this.length() * vector1.length());
		if (vDot < -1.0)
			vDot = -1.0;
		if (vDot > 1.0)
			vDot = 1.0;
		return ((double) (Math.acos(vDot)));
	}

	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
}
