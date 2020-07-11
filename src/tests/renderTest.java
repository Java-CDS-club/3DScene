package tests;
import geometry.Geometry;
import geometry.Sphere;
import geometry.Triangle;
import primitives.*;
import renderer.*;
import elements.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import scene.*;
import static org.junit.Assert.*;

import org.junit.Test;

import scene.Scene;

public class renderTest {

	@Test
	public void testRenderImage() 
	{
		Scene scene = new Scene();
		
		List<Geometry> geo=new ArrayList<Geometry>();
		geo.add(new Triangle(new Color(0,255,0),new Point3D(100,0,-149),new Point3D(0,100,-149),new Point3D(100,100,-149)));
		geo.add(new Triangle(new Color(145,0,255),new Point3D(100,0,-149),new Point3D(0,-100,-149),new Point3D(100,-100,-149)));
		geo.add(new Triangle(new Color(255,0,65),new Point3D(-100,0,-149),new Point3D(0,100,-149),new Point3D(-100,100,-149)));
		geo.add(new Triangle(new Color(255,0,0),new Point3D(-100,0,-149),new Point3D(0,-100,-149),new Point3D(-100,-100,-149)));
		geo.add(new Sphere(new Color(0,0,255),50,new Point3D(0,0,-150)));
		scene.setSceneName("AvigailAndNechamaScene1");
		scene.setScreenDistance(100);
		scene.setGeometry(geo);
		ImageWriter imageWriter= new ImageWriter("image_1",501,501,501,501);
		
		Render render = new Render(scene,imageWriter);
		render.printGrid(50);
		render.renderImage();
		
		render.getImageWriter().writeToimage();
		
	
	
	}
	

	@Test
	public void testRenderImage2()
	{
		Sphere _sphere_1=new Sphere(new Color(0,255,0),30,new Point3D(100,100,-100));
		Sphere _sphere_2=new Sphere(new Color(255,0,0),30,new Point3D(-100,100,-100));
		Sphere _sphere_3=new Sphere(new Color(0,0,255),30,new Point3D(100,-100,-100));
		Sphere _sphere_4=new Sphere(new Color(55,25,0),30,new Point3D(-100,-100,-100));
		Sphere _sphere_5=new Sphere(new Color(255,55,0),45,new Point3D(0,0,-150));
		Sphere _sphere_6=new Sphere(new Color(255,255,45),40,new Point3D(0,100,-90));
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
			
		ImageWriter _imageWriter =new ImageWriter("image_2",501, 501,501,501);
			
		Render _render= new Render(_scene,_imageWriter);
			
		_render.renderImage();
		_render.getImageWriter().writeToimage();
		
	}

}
