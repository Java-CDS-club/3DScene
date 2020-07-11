package primitives;

public class Ray 
{
	
	protected Point3D start;
	protected Vector direction;
	// ***************** Constructors ********************** // 
	public Ray(Point3D start, Vector direction) {
		super();
		this.start = new Point3D(start);
		this.direction = new Vector (direction);
		direction.normalize();
	}
	public Ray()
	{
		start=new Point3D();
		direction=new Vector();
	}
	public Ray(Ray r) {
		this.start = new Point3D(r.start);
		this.direction = new Vector (r.direction);
		direction.normalize();
	}
	// ***************** Getters/Setters ********************** // 
	public Point3D getStart() {
		return (start);
	}
	public void setStart(Point3D start) {
		this.start = start;
	}
	public Vector getDirection() {
		return (direction);
	}
	public void setDirection(Vector direction) {
		this.direction = direction;
	}
	
 // ***************** Administration  ******************** // 
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "start"+start.toString()+" direction:"+direction.toString();
	}
	
}
