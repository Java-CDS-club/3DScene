package tests;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import elements.*;
import geometry.*;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class RecursiveTest
{

	
	@Test
	public void recursiveTest()
	{
		
		Scene scene = new Scene();
		scene.setScreenDistance(300);
		
		Sphere sphere = new Sphere(new Color(0, 0, 100),500, new Point3D(0.0, 0.0, -1000));
		Material m=new Material();
		m.set_n(20);
		m.set_Kt(1);
		sphere.setMaterial(m);
		scene.addGeometry(sphere);
		
		Sphere sphere2 = new Sphere(new Color(100, 20, 20),250, new Point3D(0.0, 0.0, -1000));
		Material m2=new Material();
		m2.set_n(20);
		m2.set_Kt(0);
		sphere2.setMaterial(m2);
		scene.addGeometry(sphere2);
		
		scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -180), 
					   0.1, 0.00001, 0.000005, new Vector(new Point3D(2, 2, -3))));
	
		ImageWriter imageWriter = new ImageWriter("Recursive Test", 500, 500, 500, 500);
		
		Render render = new Render( scene,imageWriter);
		
		render.renderImage();
		
	}

	@Test
	public void recursiveTest2(){
		
		Scene scene = new Scene();
		scene.setScreenDistance(300);
		
		Sphere sphere = new Sphere(new Color(0, 0, 100),300, new Point3D(-550, -500, -1000));
		Material m=new Material();
		m.set_n(20);
		m.set_Kt(0.5);
		sphere.setMaterial(m);
		scene.addGeometry(sphere);
		
		Sphere sphere2 = new Sphere(new Color(100, 20, 20),150, new Point3D(-550, -500, -1000));
		Material m2=new Material();
		m2.set_n(20);
		m2.set_Kt(0);
		sphere2.setMaterial(m2);
		scene.addGeometry(sphere2);
		
		Triangle triangle = new Triangle(new Color(20, 20, 20),new Point3D(  1500, -1500, -1500),
				 						 new Point3D( -1500,  1500, -1500),
				 						 new Point3D(  200,  200, -375));
		
		Triangle triangle2 = new Triangle(new Color(20, 20, 20),new Point3D(  1500, -1500, -1500),
										  new Point3D( -1500,  1500, -1500),
										  new Point3D( -1500, -1500, -1500)
										  );
		
		Material m3=new Material();
		Material m4=new Material();
		m3.set_Kr(1);
		m4.set_Kr(0.5);
		triangle.setMaterial(m3);
		triangle2.setMaterial(m4);
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);

		scene.addLight(new SpotLight(new Color(255, 100, 100),  new Point3D(200, 200, -150), 
				    0, 0.00001, 0.000005,new Vector(new Point3D(-2, -2, -3))));
	
		ImageWriter imageWriter = new ImageWriter("Recursive Test 2", 500, 500, 500, 500);
		
		Render render = new Render( scene,imageWriter);
		
		render.renderImage();
		
	}
	
	
	@Test
	public void basicRendering() {

		Scene scene = new Scene("scene2",new AmbientLight(), new Color(0, 0, 0),  new ArrayList<Geometry>(),
				new ArrayList<Light>(), new Camara(), 100.0);

		Material m = new Material(0.4, 1, 20, 1, 0);
		Material m1 = new Material(0.8, 1, 20, 0, 0);
        scene.addGeometry(new Triangle(new Color (0,0,0), m,new Point3D(-10,-10,-20),new Point3D(-10,20,-20),new Point3D(20,-10,-40)));
        scene.addGeometry(new Sphere((Color.MAGENTA), m1, 5.0,new Point3D(10,10,-25)));
        
		//scene.addGeometry(new Plane(new Color(0, 0, 128), new Point3D(0, 80, 0), new Vector(new Point3D(0, 1, -1)), m));

		PointLight pointLight = new PointLight(new Color(255, 200, 200), new Point3D(-20, -20, 0), 1, 0.0001, 0.0001);
		SpotLight spotLight = new SpotLight(new Color(255, 155, 255), new Point3D(-20, -20, 0), 1.0, 0.005, 0.0000001,
				new Vector(new Point3D(0, 0, -1)));

		scene.addLight(pointLight);
		scene.addLight(spotLight);

		ImageWriter imageWriter = new ImageWriter("Reflection test", 501, 501, 501, 501);

		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.getImageWriter().writeToimage();
	}

	
	@Test
	public void ourTest()
	{
		Scene scene = new Scene();
		scene.setScreenDistance(100);
		Material m3 = new Material(0.5, 1, 20, 1, 0);
		scene.addGeometry(new Triangle(new Color (0,0,0), m3,new Point3D(-50,-50,-20),new Point3D(50,50,-20),new Point3D(50,-50,-60)));
		scene.addGeometry(new Triangle( Color.pink, m3,new Point3D(-50,-50,-20),new Point3D(50,50,-20),new Point3D(-50,50,-40)));
		 
		PointLight pointLight = new PointLight(new Color(255, 200, 200), new Point3D(-20, -20, 0), 1, 0.0001, 0.0001);

		scene.addLight(pointLight);
	
		ImageWriter imageWriter = new ImageWriter("Final Test", 500, 500, 500, 500);
		
		Render render = new Render( scene,imageWriter);
		
		render.renderImage();
		
	}
	
}