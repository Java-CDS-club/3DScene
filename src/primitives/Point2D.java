package primitives;

public class Point2D {
	protected Coordinate x;
	protected Coordinate y;
	// ***************** Constructors ********************** // 
	public Point2D(Coordinate X, Coordinate Y) {
		x=new Coordinate(X);
		y=new Coordinate(Y);
	}
	public Point2D() {
		x=new Coordinate();
		y=new Coordinate();
	}
	public Point2D(Point2D p) {
		x=new Coordinate(p.x);
		y=new Coordinate(p.y);
	}
	
	// ***************** Getters/Setters ********************** // 
	public Coordinate getX() {
		return (x);
	}
	
	public void setX(Coordinate x) {
		this.x = x;
	}
	public Coordinate getY() {
		return (y);
	}
	public void setY(Coordinate y) {
		this.y = y;
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
		Point2D other = (Point2D) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}
	public String toString() {
		return " (" + x +","+y+")";
	
	}
	
}
