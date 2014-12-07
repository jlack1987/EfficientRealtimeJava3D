
public class Vector4d implements java.io.Serializable
{
	private static final long serialVersionUID = 1971788326511970536L;
	
	double x,y,z,w;
	
	public Vector4d()
	{
		setToZero();
	}
	
	public void setToZero()
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 0;
	}
}
