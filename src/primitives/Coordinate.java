package primitives;

public class Coordinate {
	
	
	protected double c;
	// ***************** Constructors ********************** // 
	
	public Coordinate(double x) {
		this.c = x;
	}
	public Coordinate() {																							
		this.c = 0;
	}
	public Coordinate(Coordinate C) {
		c = C.c;
	}
	
	// ***************** Getters/Setters ********************** // 
	
	public void setC(double C){
		c=C;
	}
	public double getC() {
		
		return c;  //new??
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
		Coordinate other = (Coordinate) obj;
		return (c==other.getC());

	}
	public String toString() {
		return ""+ c ;
	
	}
	public void add(Coordinate C)
	{
		c= c+C.c;
	}
	public void substract(Coordinate C)
	{
		c= c-C.c;
	}
	
	public Coordinate add(double C)
	{
		return new Coordinate( c+C);
	}


		

}
