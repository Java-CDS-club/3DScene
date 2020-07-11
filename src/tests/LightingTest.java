package tests;

import java.awt.Color;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elements.PointLight;
import elements.SpotLight;
import geometry.*;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class LightingTest {
/*
	@Test 
	public void pointTest()
	{
	    Scene scene = new Scene();
		scene.setScreenDistance(200);
		Material m=new Material();
		m.set_n(80);
		Sphere sphere = new Sphere(new Color(255,255,200),500, new Point3D(0, 0, -1000));
		sphere.setEmission(new Color(0, 0, 100));
		sphere.setMaterial(m);
		scene.addGeometry(sphere);
		Triangle triangle = new Triangle(new Color(0,0,0),new Point3D(  3500,  3500, -2000),new Point3D( -3500, -3500, -1000),
				 						 new Point3D(  3500, -3500, -2000));

		Triangle triangle2 = new Triangle(new Color(0,0,0),new Point3D(  3500,  3500, -2000),
				  						  new Point3D( -3500,  3500, -1000),
				  						  new Point3D( -3500, -3500, -1000));
		
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);
		
		scene.addLight(new PointLight( new Color(255, 100, 100),new Point3D(200, 200, -100), 
					   0, 0.000001, 0.0000005));
	
		
		ImageWriter imageWriter = new ImageWriter("shadow", 500, 500, 500, 500);
		
		Render render = new Render( scene,imageWriter);
		
		render.renderImage();
		imageWriter.writeToimage();
		
	}
	@Test
	public void pointAndSpotTest() {

		Scene scene = new Scene();
		
		Material m = new Material(1,1, 20);
		Material m1 = new Material( 0.8,1, 20);
		Material m2 = new Material( 0.8,1, 20);
      
		scene.addGeometry(new Plane(Color.darkGray, m, new Point3D(0,5,-150),new Vector(new Point3D(0,0,1))));
		scene.addGeometry(new Sphere( Color.BLUE, m2,15.0, new Point3D(50,0,-50)));
        scene.addGeometry(new Sphere( Color.RED, m2,15.0, new Point3D(-50,0,-50)));
        scene.addGeometry(new Sphere( Color.YELLOW, m1,15.0, new Point3D(0,-50,-50)));
        scene.addGeometry(new Sphere( Color.GREEN, m1,15.0, new Point3D(0,50,-50)));
		
		

		PointLight pointLight = new PointLight(new Color(0, 255, 200), new Point3D(-30, -30, 0), 1, 0.0001, 0.0001);
		SpotLight spotLight = new SpotLight(Color.ORANGE, new Point3D(-20, -20, 0), 1.0, 0.005, 0.001,
				new Vector(new Point3D(0, 0, -1)));

		scene.addLight(pointLight);
		scene.addLight(spotLight);

		ImageWriter imageWriter = new ImageWriter("pointAndSpot", 500, 500, 500, 500);


		Render render = new Render(scene, imageWriter);
		
		render.renderImage();
		
		imageWriter.writeToimage();
		
	}

	@Test
	public void testRenderImage123() 
	{
		Scene scene = new Scene();
		
		List<Geometry> geo=new ArrayList<Geometry>();
		Material m=new Material();
		m.set_n(0);
		m.set_Kd(0);
		m.set_Ks(0);
		Material m1=new Material();
		m1.set_Kd(0);
		m1.set_Ks(1);
		m1.set_n(20);
		geo.add(new Sphere(new Color(220,255,0),150,new Point3D(0,0,-250)));

		geo.add(new Sphere(new Color(150,75,0),60,new Point3D(-10,0,-150))); 
		geo.add(new Sphere(new Color(255,255,153),m1,37,new Point3D(0,0,-100))); 
		//geo.add(new Sphere(new Color(220,255,0),m1,20,new Point3D(-22,0,-100))); 
		//geo.add(new Sphere(new Color(220,255,0),m1,20,new Point3D(-21,12,-100))); 
		//geo.add(new Sphere(new Color(220,255,0),m1,20,new Point3D(-21,-12,-100)));
		
		
		geo.add(new Sphere(new Color(255,0,0),m,4,new Point3D(8,6,-40)));
		geo.add(new Sphere(new Color(255,0,0),m,4,new Point3D(6,13,-40)));
		geo.add(new Sphere(new Color(255,0,0),m,4,new Point3D(5,10,-40)));
		geo.add(new Sphere(new Color(255,0,0),m,4,new Point3D(5,9,-40)));

		geo.add(new Sphere(new Color(255,0,0),m,4,new Point3D(8,-6,-40)));
		geo.add(new Sphere(new Color(255,0,0),m,4,new Point3D(6,-13,-40)));
		geo.add(new Sphere(new Color(255,0,0),m,4,new Point3D(5,-10,-40)));
		geo.add(new Sphere(new Color(255,0,0),m,4,new Point3D(5,-9,-40)));

		geo.add(new Triangle(new Color(255,0,0),m,new Point3D(5,5,-40),new Point3D(5,14,-40),new Point3D(-2,9,-40)));
		geo.add(new Triangle(new Color(255,0,0),m,new Point3D(5,-5,-40),new Point3D(5,-14,-40),new Point3D(-2,-9,-40)));

		
		scene.setSceneName("smile123");
		scene.setScreenDistance(200);
		scene.setGeometry(geo);
		
		scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(0, 0,1500), 
				  0, 0.00001, 0.000005,  new Vector(new Point3D(0, 0, -1))));
		
		ImageWriter imageWriter= new ImageWriter("smile123",501,501,501,501);
		
		Render render = new Render(scene,imageWriter);
		//render.printGrid(50);
		render.renderImage();
		
		render.getImageWriter().writeToimage();
	}
	
	@Test 
	public void emmissionTest(){
		
		Scene scene = new Scene();
		
		scene.addGeometry(new Sphere(new Color(255,0,0),50, new Point3D(0.0, 0.0, -149)));
		
		Triangle triangle = new Triangle(new Color(0,255,0),new Point3D( 100, 0, -149),
				 						 new Point3D(  0, 100, -149),
				 						 new Point3D( 100, 100, -149)
				 						 );
		
		Triangle triangle2 = new Triangle(	 new Color(0,0,255),new Point3D( 100, 0, -149),
				 			 			  new Point3D(  0, -100, -149),
				 			 			  new Point3D( 100,-100, -149)
					 					);
		
		Triangle triangle3 = new Triangle(new Color(255,255,0),new Point3D(-100, 0, -149),
				 						  new Point3D(  0, 100, -149),
				 						  new Point3D(-100, 100, -149)
				 						  );
		
		Triangle triangle4 = new Triangle( new Color(255,0,255),new Point3D(-100, 0, -149),
				 			 			  new Point3D(  0,  -100, -149),
				 			 			  new Point3D(-100, -100, -149)
					 						);
		
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);
		scene.addGeometry(triangle3);
		scene.addGeometry(triangle4);
		
		ImageWriter imageWriter = new ImageWriter("Emmition test", 500, 500, 500, 500);
		
		Render render = new Render( scene,imageWriter);
		
		render.renderImage();
		render.printGrid(50);
		imageWriter.writeToimage();
	}
	
	@Test
	public void spotLightTest(){
		
		Scene scene = new Scene();
		Sphere sphere = new Sphere(new Color(0, 0, 100),800, new Point3D(0.0, 0.0, -1000));
		Material m=new Material();
		m.set_n(20);
		sphere.setMaterial(m);
		scene.addGeometry(sphere);
		scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -100), 
					  0, 0.00001, 0.000005,  new Vector(new Point3D(2, 2, -3))));
	
		ImageWriter imageWriter = new ImageWriter("Spot test", 500, 500, 500, 500);
		
		Render render = new Render( scene,imageWriter);
		
		render.renderImage();
		
	}
	
	@Test
	public void spotLightTest2()
	{
		
		Scene scene = new Scene();
		scene.setScreenDistance(200);
		Sphere sphere = new Sphere(new Color(0, 0, 100),500, new Point3D(0.0, 0.0, -1000));
		Material m=new Material();
		m.set_n(20);
		sphere.setMaterial(m);
		scene.addGeometry(sphere);
		
		Triangle triangle = new Triangle(new Color (0, 0, 100),new Point3D(-125, -225, -260),
										 new Point3D(-225, -125, -260),
										 new Point3D(-225, -225, -270)
										 );
		
		Material m1=new Material();
		m1.set_n(4);
		triangle.setMaterial(m);
		scene.addGeometry(triangle);
		
		scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150), 
					  0.1, 0.00001, 0.000005,  new Vector(new Point3D(2, 2, -3))));
	
		ImageWriter imageWriter = new ImageWriter("Spot test 2", 500, 500, 500, 500);
		
		Render render = new Render( scene,imageWriter);
		
		render.renderImage();
		
	}
	
	@Test
	public void spotLightTest3(){
		
		Scene scene = new Scene();
		
		Triangle triangle = new Triangle( new Color(0,0,0),new Point3D(  3500,  3500, -2000),
				 						 new Point3D( -3500, -3500, -1000),
				 						 new Point3D(  3500, -3500, -2000)
				 						);

		Triangle triangle2 = new Triangle( new Color(0,0,0),new Point3D(  3500,  3500, -2000),
				  						  new Point3D( -3500,  3500, -1000),
				  						  new Point3D( -3500, -3500, -1000));
		
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);
		
		scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100)
					   , 0, 0.000001, 0.0000005,new Vector(new Point3D(-2, -2, -3))));
	
		
		ImageWriter imageWriter = new ImageWriter("Spot test 3", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		
	}
	
	

	 
	@Test
	public void pointLightTest()
	{
		
		Scene scene = new Scene();
		Sphere sphere = new Sphere (new Color(0, 0, 100),800, new Point3D(0.0, 0.0, -1000));
		Material m=new Material();
		m.set_n(20);
		sphere.setMaterial(m);
		scene.addGeometry(sphere);
		
		scene.addLight(new PointLight(new Color(255,100,100), new Point3D(-200, -200, -100), 
					   0, 0.00001, 0.000005));
	
		ImageWriter imageWriter = new ImageWriter("Point test", 500, 500, 500, 500);
		
		Render render = new Render( scene,imageWriter);
		
		render.renderImage();
		
		
	}
	
	
	@Test
	public void pointLightTest2(){
		
		Scene scene = new Scene();
		Sphere sphere = new Sphere(new Color(0, 0, 100),800, new Point3D(0.0, 0.0, -1000));
		Material m=new Material();
		m.set_n(20);
		sphere.setMaterial(m);
		
		Triangle triangle = new Triangle( new Color(0,0,0),new Point3D(  3500,  3500, -2000),
				 						 new Point3D( -3500, -3500, -1000),
				 						 new Point3D(  3500, -3500, -2000)
				 						);

		Triangle triangle2 = new Triangle(new Color(0,0,0),new Point3D(  3500,  3500, -2000),
				  						  new Point3D( -3500,  3500, -1000),
				  						  new Point3D( -3500, -3500, -1000)
					 						 );
		
		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);
		
		scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(200, 200, -100), 
					   0, 0.000001, 0.0000005));
	
		
		ImageWriter imageWriter = new ImageWriter("Point test 2", 500, 500, 500, 500);
		
		Render render = new Render( scene,imageWriter);
		
		render.renderImage();
		
	}
	
	@Test
	public void testFlower()
	{
		Sphere _sphere_1=new Sphere(new Color(0,255,0),30,new Point3D(100,100,-100));
		Sphere _sphere_2=new Sphere(new Color(255,0,0),30,new Point3D(-100,100,-100));
		Sphere _sphere_3=new Sphere(new Color(0,0,255),30,new Point3D(100,-100,-100));
		Sphere _sphere_4=new Sphere( Color.PINK,30,new Point3D(-100,-100,-100));
		Sphere _sphere_5=new Sphere( Color.YELLOW,45,new Point3D(0,0,-150));
		Sphere _sphere_6=new Sphere( Color.GRAY,40,new Point3D(0,100,-90));
		Sphere _sphere_7=new Sphere(new Color(99,85,0),40,new Point3D(100,0,-90));
		Sphere _sphere_8=new Sphere(new Color(155,155,0),40,new Point3D(-100,0,-90));
		Sphere _sphere_9=new Sphere(new Color(0,155,30),40,new Point3D(0,-100,-90));
		List<Geometry> _geometries=new ArrayList<Geometry>();
		
		_geometries.add(_sphere_1);
		_geometries.add(_sphere_2);
		_geometries.add(_sphere_3);
		_geometries.add(_sphere_4);
		_geometries.add(_sphere_5);
		_geometries.add(_sphere_6);
		_geometries.add(_sphere_7);
		_geometries.add(_sphere_8);
		_geometries.add(_sphere_9);

		Scene _scene=new Scene();
		_scene.setGeometry(_geometries);
			
		_scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(0, 0, 0), 
				  0.1, 0.00001, 0.000005,  new Vector(new Point3D(0, 0, -1))));
		ImageWriter _imageWriter =new ImageWriter("Flower3D",501, 501,501,501);
			
		Render _render= new Render(_scene,_imageWriter);
			
		_render.renderImage();
		_render.getImageWriter().writeToimage();
		
	}
*/
}
