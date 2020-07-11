package tests;
import primitives.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class Point3DTest {

	@Test
	public void testAdd() {
		
		Point3D p1=new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0));
		Point3D p2=new Point3D(new Coordinate(1),new Coordinate(2),new Coordinate(3));
		Vector v=new Vector(p2);
		p1.add(v);
		double d=p1.getX().getC();	
		assertEquals(2, d, 0);
	}
	@Test
	public void testSubtract() {
		Point3D p1=new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0));
		Point3D p2=new Point3D(new Coordinate(1),new Coordinate(2),new Coordinate(3));
		Vector v=new Vector(p2);
		p1.substract(v);
		double d=p1.getX().getC();	
		assertEquals(0, d, 0);
	}
	
	@Test
	public void testEquals(){
		Coordinate x=new Coordinate(10);
		Coordinate y=new Coordinate(10);
		Point3D p1=new Point3D(1,2,3);
		Point3D p2=new Point3D(1,2,3);

		assertEquals(x,y);
		assertEquals(p1,p2);
	}

}
