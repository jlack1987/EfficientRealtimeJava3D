public class Vector3d implements java.io.Serializable
{
	private static final long serialVersionUID = 3014418817837714975L;
	double x;
	double y;
	double z;

	public Vector3d()
	{
		set(0,0,0);
	}

	public Vector3d(double x, double y, double z)
	{
		set(x,y,z);
	}

	public Vector3d(Vector3d vector)
	{
		set(vector);
	}

	public void set(Vector3d vector)
	{
		set(vector.x,vector.y,vector.z);
	}

	public void set(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void get(Vector3d vector)
	{
		vector.x = x;
		vector.y = y;
		vector.z = z;
	}

	public void get(double[] array)
	{
		array[0] = x;
		array[1] = y;
		array[2] = z;
	}

	public void add(Vector3d vector)
	{
		this.x = this.x + vector.x;
		this.y = this.y + vector.y;
		this.z = this.z + vector.z;
	}

	public void subtract(Vector3d vector)
	{
		this.x = this.x - vector.x;
		this.y = this.y - vector.y;
		this.z = this.z - vector.z;
	}

	public double dot(Vector3d vector)
	{
		return this.x * vector.x + this.y * vector.y + this.z * vector.z;
	}

	public void cross(Vector3d vector, Vector3d vectorToPack)
	{
		if (this == vector || this == vectorToPack || vector == vectorToPack)
		{
			double tempx1 = this.x;
			double tempy1 = this.y;
			double tempz1 = this.z;
			double tempx2 = vector.x;
			double tempy2 = vector.y;
			double tempz2 = vector.z;
			vectorToPack.x = tempy1 * tempz2 - tempz1 * tempy2;
			vectorToPack.y = tempz1 * tempx2 - tempx1 * tempz2;
			vectorToPack.z = tempx1 * tempy2 - tempy1 * tempx2;
		}
		else
		{
			vectorToPack.x = this.y * vector.z - this.z * vector.y;
			vectorToPack.y = this.z * vector.x - this.x * vector.z;
			vectorToPack.z = this.x * vector.y - this.y * vector.x;
		}
	}

	public double length()
	{
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public void normalize()
	{
		double length = length();
		x /= length;
		y /= length;
		z /= length;
	}
	
	public void normalize(Vector3d vector)
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

	public double lengthSquared()
	{
		return (x * x + y * y + z * z);
	}

	public double angle(Vector3d vector1)
	{
		double vDot = this.dot(vector1) / (this.length() * vector1.length());
		if (vDot < -1.0)
			vDot = -1.0;
		if (vDot > 1.0)
			vDot = 1.0;
		return ((double) (Math.acos(vDot)));
	}
	
	public String toString()
	{
		return "(" + x + "," + y + "," + z + ")";
	}
}
