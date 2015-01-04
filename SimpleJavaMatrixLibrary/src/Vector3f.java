
public class Vector3f implements java.io.Serializable
{
	private static final long serialVersionUID = -2143999136415453779L;

	float x,y,z;
	
	public Vector3f()
	{
		set(0.0f,0.0f,0.0f);
	}

	public Vector3f(float x, float y, float z)
	{
		set(x,y,z);
	}

	public Vector3f(Vector3f vector)
	{
		set(vector.x,vector.y,vector.z);
	}
	
	public void set(Vector3f vector)
	{
		set(vector.x,vector.y,vector.z);
	}
	
	public void set(float x,float y,float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void get(Vector3f vector)
	{
		vector.x = x;
		vector.y = y;
		vector.z = z;
	}
	
	public void get(float[] array)
	{
		array[0] = x;
		array[1] = y;
		array[2] = z;
	}

	public void add(Vector3f vector)
	{
		this.x = this.x + vector.x;
		this.y = this.y + vector.y;
		this.z = this.z + vector.z;
	}

	public void subtract(Vector3f vector)
	{
		this.x = this.x - vector.x;
		this.y = this.y - vector.y;
		this.z = this.z - vector.z;
	}

	public void normalize()
	{
		float length = length();
		x /= length;
		y /= length;
		z /= length;
	}
	
	public void normalize(Vector3f vector)
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
	
	public float dot(Vector3f vector)
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
	
	public float lengthSquared()
	{
		return (x*x + y*y + z*z);
	}
	
	public float angle(Vector3f vector1)
	{
		float vDot = this.dot(vector1) / (this.length() * vector1.length());
		if (vDot < -1.0f)
			vDot = -1.0f;
		if (vDot > 1.0f)
			vDot = 1.0f;
		return ((float) (Math.acos(vDot)));
	}
}
