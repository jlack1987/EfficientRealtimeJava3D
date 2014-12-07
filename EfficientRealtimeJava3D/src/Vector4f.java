
public class Vector4f implements java.io.Serializable
{
	private static final long serialVersionUID = -2540669824625708412L;
	
	float x,y,z,w;
	
	public Vector4f()
	{
		setToZero();
	}
	
	public void setToZero()
	{
		this.x = 0.0f;
		this.y = 0.0f;
		this.z = 0.0f;
		this.w = 0.0f;
	}
}
