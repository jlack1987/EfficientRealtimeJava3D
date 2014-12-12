
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
}
