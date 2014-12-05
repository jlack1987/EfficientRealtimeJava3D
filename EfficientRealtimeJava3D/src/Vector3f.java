
public class Vector3f implements java.io.Serializable
{
	private static final long serialVersionUID = -2143999136415453779L;

	float x,y,z;
	
	public Vector3f()
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Vector3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3f(Vector3f vector)
	{
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	public void set(Vector3f vector)
	{
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	public void set(float x,float y,float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Add method in which the vector argument is added to this and the result
	 * is stored in this, i.e. this = this + vector. The Vector3d argument is
	 * not modified.
	 * 
	 * @param vector
	 */
	public void add(Vector3f vector)
	{
		this.x = this.x + vector.x;
		this.y = this.y + vector.y;
		this.z = this.z + vector.z;
	}

	/**
	 * Subtract method in which the vector argument is subtracted to this and
	 * the result is stored in this, i.e. this = this - vector. The Vector3d
	 * argument is not modified.
	 * 
	 * @param vector
	 */
	public void subtract(Vector3f vector)
	{
		this.x = this.x - vector.x;
		this.y = this.y - vector.y;
		this.z = this.z - vector.z;
	}

	/**
	 * Dot method in which the elements of this are multiplied by the
	 * corresponding elements of the vector and summed up. Vector3d argument is
	 * not modified.
	 * 
	 * @param vector
	 * @return
	 */
	public double dot(Vector3f vector)
	{
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}

	public void cross(Vector3f vector, Vector3f vectorToPack)
	{
		if (this == vector || this == vectorToPack || vector == vectorToPack)
		{
			float tempx1 = this.x;
			float tempy1 = this.y;
			float tempz1 = this.z;
			float tempx2 = vector.x;
			float tempy2 = vector.y;
			float tempz2 = vector.z;
			vectorToPack.x = tempy1 * tempz2 - tempz1 * tempy2;
			vectorToPack.y = tempz1 * tempx2 - tempx1 * tempz2;
			vectorToPack.z = tempx1 * tempy2 - tempy1 * tempx2;
		} else
		{
			vectorToPack.x = this.y * vector.z - this.z * vector.y;
			vectorToPack.y = this.z * vector.x - this.x * vector.z;
			vectorToPack.z = this.x * vector.y - this.y * vector.x;
		}
	}
	
	public float length()
	{
		return (float)Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
	}
}
