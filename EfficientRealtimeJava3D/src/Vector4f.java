
public class Vector4f implements java.io.Serializable
{
	private static final long serialVersionUID = -2540669824625708412L;
	
	float x,y,z,w;
	
	public Vector4f()
	{
		setToZero();
	}
	
	public void set(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public void setToZero()
	{
		set(0.0f,0.0f,0.0f,0.0f);
	}
}
