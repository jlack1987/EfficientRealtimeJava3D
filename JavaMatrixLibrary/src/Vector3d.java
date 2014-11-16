
public class Vector3d 
{
	double x;
	double y;
	double z;
	
	public Vector3d()
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vector3d(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3d(Vector3d vector)
	{
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	/**
	 * Add method in which the vector argument 
	 * is added to this and the result is stored 
	 * in this, i.e. this = this + vector. The 
	 * Vector3d argument is not modified.
	 * 
	 * @param vector
	 */
	public void add(Vector3d vector)
	{
		this.x = this.x + vector.x;
		this.y = this.y + vector.y;
		this.z = this.z + vector.z;
	}
	
	/**
	 * Subtract method in which the vector argument 
	 * is subtracted to this and the result is stored 
	 * in this, i.e. this = this - vector. The 
	 * Vector3d argument is not modified.
	 * 
	 * @param vector
	 */
	public void subtract(Vector3d vector)
	{
		this.x = this.x - vector.x;
		this.y = this.y - vector.y;
		this.z = this.z - vector.z;
	}
	
	/**
	 * Dot method in which the elements of this
	 * are multiplied by the corresponding 
	 * elements of the vector and summed up.
	 * Vector3d argument is not modified.
	 * 
	 * @param vector
	 * @return
	 */
	public double dot(Vector3d vector)
	{
		return this.x*vector.x+this.y*vector.y+this.z*vector.z;
	}
	
	
	public void cross(Vector3d vector, Vector3d vectorToPack)
	{
		vectorToPack.x = this.y*vector.z-this.z*vector.y;
		vectorToPack.y = this.z*vector.x-this.x*vector.z;
		vectorToPack.z = this.x*vector.y-this.y*vector.x;
	}
	
}
