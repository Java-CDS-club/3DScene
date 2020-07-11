package elements;
import java.awt.Color;
import primitives.*;

public abstract class Light
{
	Color l0;
	
	// ***************** Constructors ********************** //
	 public Light(Color color) 
	 {	
			this.l0 = color;
	 }

	 public Light(Light l) 
	 {
			this.l0 = l.l0;
	 }
	 public Light() 
	 {
			l0=new Color(255,255,255);
	 }
	// ***************** Getters/Setters ********************** // 

		public Color getl0()
		{
		return l0;
	    }

	public void setl0(Color l0) 
	{
		this.l0 = l0;
	}
	// ***************** Administration  ******************** //
	   
		@Override
	public String toString() 
		{
		return "Light [l0=" + l0 + "]";
	    }
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Light other = (Light) obj;
			if (l0 == null) {
				if (other.l0 != null)
					return false;
			} else if (!l0.equals(other.l0))
				return false;
			return true;
		}

		public abstract Vector getL(Point3D point);
		
		public abstract double distance(Point3D p);
		public abstract Color  getIntensity(Point3D point);
}
		
