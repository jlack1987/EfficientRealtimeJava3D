
public class Vector4d implements java.io.Serializable
{
	private static final long serialVersionUID = 1971788326511970536L;
	
	double x,y,z,w;
	
	public Vector4d()
	{
		setToZero();
	}
	
	public Vector4d(Vector4d vector)
	{
		set(vector);
	}
	
	public Vector4d(double[] vector)
	{
		set(vector[0],vector[1],vector[2],vector[3]);
	}
	
	public Vector4d(float[] vector)
	{
		set(vector[0],vector[1],vector[2],vector[3]);
	}
	
	public Vector4d(double x, double y, double z, double w)
	{
		set(x,y,z,w);
	}
	
	public void setToZero()
	{
		set(0,0,0,0);
	}
	
	public void set(Vector4d vector)
	{
		set(vector.x,vector.y,vector.z,vector.w);
	}
	
	public void scale(double scale)
	{
		x*=scale;
		y*=scale;
		z*=scale;
		w*=scale;
	}
	
	public void set(double x, double y, double z, double w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public void get(Vector4d vector)
	{
		vector.x=x;
		vector.y=y;
		vector.z=z;
		vector.w=w;
	}
	
	public void get(double[] vector)
	{
		vector[0]=x;
		vector[1]=y;
		vector[2]=z;
		vector[3]=w;
	}
	
	public void get(float[] vector)
	{
		vector[0]=(float)x;
		vector[1]=(float)y;
		vector[2]=(float)z;
		vector[3]=(float)w;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getZ()
	{
		return z;
	}
	
	public double getW()
	{
		return w;
	}
	
	public double dot(Vector4d vector)
	{
		return (vector.x*this.x + vector.y*this.y + vector.z*this.z + vector.w*this.w);
	}
	
	public double length()
	{
		return Math.sqrt(x*x + y*y + z*z + w*w);
	}
	
	public double lengthSquared()
	{
		return (x*x+y*y+z*z+w*w);
	}
	
	public void normalize()
	{
		double mag = 1/Math.sqrt(x*x+y*y+z*z+w*w);
		
		x*=mag;
		y*=mag;
		z*=mag;
		w*=mag;
	}
	
	public void normalize(Vector4d vector)
	{
		if(this != vector)
		{
			set(vector);
			normalize();
		}
		else
		{
			normalize();
		}
	}
}
