
public class Vector2d implements java.io.Serializable
{
	private static final long serialVersionUID = 1376445751990551719L;
	
	double x, y;
	
	public Vector2d(double x, double y)
	{
		set(x,y);
	}
	
	public Vector2d(Vector2d v)
	{
		set(v);
	}
	
	public Vector2d(double[] d)
	{
		set(d);
	}
	
	public Vector2d(float[] f)
	{
		set(f);
	}
	
	public void set(double[] d)
	{
		if(d.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}
		
		set(d[0],d[1]);
	}
	
	public void set(float[] f)
	{
		if(f.length != 2)
		{
			throw new RuntimeException("Array must contain exactly 2 elements.");
		}
		
		set(f[0],f[1]);
	}
	
	public void set(Vector2d v)
	{
		set(v.x,v.y);
	}
	
	public void set(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
}
