
public class Vector4f implements java.io.Serializable
{
	private static final long serialVersionUID = -2540669824625708412L;
	
	float x,y,z,w;
	
	public Vector4f()
	{
		setToZero();
	}
	
	public Vector4f(Vector4f vector)
	{
		set(vector);
	}
	
	public Vector4f(double[] vector)
	{
		x=(float)vector[0];
		y=(float)vector[1];
		z=(float)vector[2];
		w=(float)vector[3];
	}
	
	public Vector4f(float[] vector)
	{
		x=vector[0];
		y=vector[1];
		z=vector[2];
		w=vector[3];
	}
	
	public void set(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public void set(Vector4f vector)
	{
		set(vector.x,vector.y,vector.z,vector.w);
	}
	
	public void setToZero()
	{
		set(0.0f,0.0f,0.0f,0.0f);
	}
	
	public void get(Vector4f vector)
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
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getZ()
	{
		return z;
	}
	
	public float getW()
	{
		return w;
	}
	
	public void scale(float scale)
	{
		x*=scale;
		y*=scale;
		z*=scale;
		w*=scale;
	}
	
	public double dot(Vector4f vector)
	{
		return (vector.x*this.x + vector.y*this.y + vector.z*this.z + vector.w*this.w);
	}
	
	public float length()
	{
		return (float)Math.sqrt(x*x+y*y+z*z+w*w);
	}
	
	public float lengthSquared()
	{
		return (x*x+y*y+z*z+w*w);
	}
	
	public void normalize()
	{
		float mag = (float) (1/Math.sqrt(x*x+y*y+z*z+w*w));
		
		x*=mag;
		y*=mag;
		z*=mag;
		w*=mag;
	}
	
	public void normalize(Vector4f vector)
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
