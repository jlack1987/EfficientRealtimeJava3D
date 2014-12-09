
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
		set(0,0,0,0);
	}
	
	public void set(Vector4d vector)
	{
		set(vector.x,vector.y,vector.z,vector.w);
	}
	
	public void set(double x, double y, double z, double w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
}
