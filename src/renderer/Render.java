package renderer;

import java.awt.Color;

import primitives.*;
import geometry.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import scene.*;
import elements.*;

import java.util.Map;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;


public class Render
{
	protected Scene scene;
	protected ImageWriter imageWriter;
	
	private final int RECURSION_LEVEL = 3;
	
	private static class Entry
	{
		public Geometry geometry;
		public Point3D point;

		public Entry(Geometry g, Point3D p) {
			geometry = g;
			point = p;
		}
	}
	// ***************** Constructors ********************** // 
	public Render(Scene scene, ImageWriter imageWriter) {
		this.scene = (scene);
		this.imageWriter = (imageWriter);
	}
	public Render(Render r) {
		this.scene = (r.scene);
		this.imageWriter = (r.imageWriter);
	}
	
	// ***************** Getters/Setters ********************** // 
	public Scene getScene() {
		return (scene);
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public ImageWriter getImageWriter() {
		return (imageWriter);
	}
	public void setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
	}
	
	
	// ***************** Administration  ******************** // 
	@Override
	public String toString() {
		return "Render [scene=" + scene + ", imageWriter=" + imageWriter + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Render other = (Render) obj;
		if (imageWriter == null) {
			if (other.imageWriter != null)
				return false;
		} else if (!imageWriter.equals(other.imageWriter))
			return false;
		if (scene == null) {
			if (other.scene != null)
				return false;
		} else if (!scene.equals(other.scene))
			return false;
		return true;
	}
	
	public void printGrid(int interval)
	{
		for(int i=0;i<imageWriter.getNx();i+= interval)
			for(int j=0;j<imageWriter.getNy();j++)
					imageWriter.writePixel(i, j, new Color(255,255,255));
		
		for (int j = 0; j < imageWriter.getNy(); j += interval)
		{
			for (int i = 0; i < imageWriter.getNx(); i++) 
			{
				imageWriter.writePixel(i, j, Color.WHITE);
			}
		}
	}
	
	private Color addColor (Color c1,Color c2)
	{
		int red=c1.getRed()+c2.getRed();
		int green=c1.getGreen()+c2.getGreen();
		int blue=c1.getBlue()+c2.getBlue();
		if(red>255)
			red=255;
		if(green>255)
			green=255;
		if(blue>255)
			blue=255;
		if((int)red<0)
			red=0;
		if((int)green<0)
			green=0;
		if((int)blue<0)
			blue=0;
		return new Color(red, green, blue);
	}
	
	private Color timesColor (Color c1,double c2)
	{
		double red1=c1.getRed()*c2;
		double green1=c1.getGreen()*c2;
		double blue1=c1.getBlue()*c2;
		if((int)red1>255)
			red1=255;
		if((int)green1>255)
			green1=255;
		if((int)blue1>255)
			blue1=255;
		if((int)red1<0)
			red1=0;
		if((int)green1<0)
			green1=0;
		if((int)blue1<0)
			blue1=0;
		return new Color((int)red1, (int)green1, (int)blue1);
	}
	
	private Color calcColor(Geometry geometry, Point3D point, Ray ray)
	{
		return calcColor(geometry, point, ray, 0);
	}
	
	
	private Color calcColor(Geometry geometry,Point3D point, Ray inRay, int level)
	{
		
		if (level == RECURSION_LEVEL)
		{
			return new Color(0, 0, 0);
		}
		
		Vector v= new Vector();
		v=new Vector(point, scene.getCamara().getMiddle());
		
		
		Color ambientLight = scene.getAmbientLight().getIntensity(point);
		Color emissionLight = geometry.getEmission();
		Color specularLight=new Color(0,0,0);
		Color diffuseLight=new Color(0,0,0);
		
		Iterator<Light> lights = scene.getLightIterator();
		while (lights.hasNext())
		{
			
			Light light=lights.next();
			if (!occluded(light, point, geometry))
			{
			diffuseLight=addColor(diffuseLight,calcDiffusiveComp(geometry.getMaterial().get_Kd(),
															  geometry.getNormal(point),
															  light.getL(point),
															 light.getIntensity(point)));
			specularLight=addColor(specularLight, calcSpecularComp(geometry.getMaterial().get_Ks(),
																v,
																geometry.getNormal(point),
																light.getL(point),
																geometry.getMaterial().get_n(),
																light.getIntensity(point)));
			}		 
		}
		
		Color I0=addColor(ambientLight, emissionLight);
		Color I1=addColor(I0, diffuseLight);
		Color I2=addColor(I1, specularLight);
		
		/*
		Color refracted = new Color(0,0 ,0);
		double kt = geometry.getMaterial().get_Kt();
		if (kt>0)
		{
			
			refracted=scene.getBackground();
			Ray refractedRay = constructRefractedRay(geometry, point, inRay);
			Map<Geometry, List<Point3D>> intersectionPoints2 =getSceneRayIntersections(refractedRay) ;
			if (intersectionPoints2!=null && intersectionPoints2.size()!=0)
			{
				intersectionPoints2.remove(geometry);
				Map<Geometry, Point3D> closestPoint2 = getClosestPoint(intersectionPoints2);
				for(Map.Entry<Geometry, Point3D> refractedEntry:closestPoint2.entrySet())
				{ 
					Geometry g=(Geometry)(refractedEntry.getKey());
					if (!g.equals(geometry))
					{	
						refracted = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);			
						refracted = new Color ((int)(refracted.getRed() * kt), (int)(refracted.getGreen() * kt),(int)(refracted.getBlue() * kt));
						
					}
				}
			}
            else
            {
                refracted=calcColor(null,null,null,RECURSION_LEVEL);
                I2 = addColor(I2,refracted);
            }
		}	 */
		Color refracted = new Color(0, 0, 0);
		Color reflected = new Color(0, 0, 0);
		
		double kr = geometry.getMaterial().get_Kr();
		if (kr>0)
		{
			
			reflected=scene.getBackground();
			 Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
			 Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(reflectedRay);
			 if (intersectionPoints!=null && intersectionPoints.size()!= 0) 
			      {
			          intersectionPoints.remove(geometry);
			          Map<Geometry, Point3D> closestPoint = getClosestPoint1(intersectionPoints);
			          for (Map.Entry<Geometry, Point3D> reflectedEntry : closestPoint.entrySet()) 
			          {
			        	  Geometry g = (Geometry) (reflectedEntry.getKey());
			              if (!(g.equals(geometry)))
			              {
			            	  reflected = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
			            	  reflected = new Color ((int)(reflected.getRed() * kr), (int)(reflected.getGreen() * kr),(int)(reflected.getBlue() * kr));
			            	 
			              }
			          }
			      }
			    else
			     {
			         reflected=calcColor(null,null,null,RECURSION_LEVEL);
			         I2 = addColor(I2,reflected);
			     }
		}
		double kt = geometry.getMaterial().get_Kt();
		if (kt>0)
		{
		Ray refractedRay = constructRefractedRay(geometry, point, inRay);
		Entry refractedEntry = findClosestIntersection(refractedRay);
		
		if (refractedEntry != null)
			{
			
			refracted = calcColor(refractedEntry.geometry, refractedEntry.point, refractedRay, level + 1);
			kt = geometry.getMaterial().get_Kt();
			refracted = new Color ((int)(refracted.getRed() * kt), (int)(refracted.getGreen() * kt),(int)(refracted.getBlue() * kt));
			}	
		}
		I2 = addColor(I2,reflected);
		I2 = addColor(I2,refracted);
		
		return I2;		

	}
	

	private boolean occluded(Light light, Point3D point,Geometry geometry)
	{
		Vector lightDirection = light.getL(point);
		lightDirection.scale(-1);
		
		Point3D geometryPoint = new Point3D(point);
		
		Vector epsVector = new Vector(geometry.getNormal(point));
		epsVector.scale(2);
		geometryPoint.add(epsVector);
		
		Ray lightRay = new Ray(geometryPoint, lightDirection);
		
		Map<Geometry, List<Point3D>> intersectionPoints =getSceneRayIntersectionsForOcludded(lightRay,light,geometryPoint);
		
		if (geometry instanceof flatGeometry)
		{
			 intersectionPoints.remove(geometry);
		}

		//return !intersectionPoints.isEmpty();
		for (Map.Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet())
			if (entry.getKey().getMaterial().get_Kt() == 0)
				return true;
			return false;
	}
	
	private  Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray)
	{
			Iterator<Geometry> geometries = scene.getGeometriesIterator();
			 Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry,List<Point3D>>();

			while (geometries.hasNext())
			{
				Geometry geometry = geometries.next();
				List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
					
				if (!geometryIntersectionPoints.isEmpty())
				{
				 intersectionPoints.put(geometry,geometryIntersectionPoints);
				}
			}
			return intersectionPoints; 	
	}
	
	private  Map<Geometry, List<Point3D>> getSceneRayIntersectionsForOcludded(Ray ray,Light l,Point3D geometryPoint)
	{
			Iterator<Geometry> geometries = scene.getGeometriesIterator();
			 Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry,List<Point3D>>();

			while (geometries.hasNext())
			{
				Geometry geometry = geometries.next();
				List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
				List<Point3D> geoIntersectionPoints =new ArrayList<Point3D>();
				
				for (Point3D point: geometryIntersectionPoints)
				{
					if (point.distance(geometryPoint) > l.distance(point))
					//if (point.distance(geometryPoint) < l.distance(geometryPoint))
					{
						geoIntersectionPoints.add(point);
					}
				}
					
				if (!geoIntersectionPoints.isEmpty())
				{
				 intersectionPoints.put(geometry,geometryIntersectionPoints);
				}
			}
			return intersectionPoints; 	
	}
		
	
	private Color calcSpecularComp(double ks, Vector vector, Vector normal, Vector l, double n,Color intensity) 
	{
		
		double d=l.dotProduct(normal);
		d*=-2;
		Vector no=new Vector(normal);
		no.scale(d);
		Vector v1=new Vector(l);
		v1.add(no);
		Vector R= new Vector(v1);
		R.normalize();
		Vector v2=new Vector(vector);
		v2.normalize();
		double temp=v2.dotProduct(R);
		if (temp >= 0.0)
			return new Color(0,0,0);
		double m=Math.pow(temp,n);
		m*=ks;
		return timesColor(intensity,Math.abs(m) );
	}
	
	private Color calcDiffusiveComp(double d, Vector normal, Vector l, Color intensity)
	{
		double nl=normal.dotProduct(l);
		nl=nl*d;
		if (nl >= 0.0)
			return new Color(0,0,0);
		return timesColor(intensity,Math.abs(nl));
		
	}
	
	private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay) 
	{
		Vector normal = geometry.getNormal(point);
		normal.scale(-2);
		point.add(normal);
		
		if (geometry instanceof flatGeometry)
		{
			return new Ray (point, inRay.getDirection());
		} 
		else 
		{
			return new Ray (point, inRay.getDirection());
		}	
	}

	private Ray constructReflectedRay(Vector normal, Point3D point, Ray ray) 
	{
		Vector r = ray.getDirection().substract(normal.multiplyScalar(2 * ray.getDirection().dotProduct(normal)));
		return new Ray(point.add(r.multiplyScalar(2).getHead()), r);
	}
	
	/*
	private Entry<Geometry, Point3D> findClosestIntersection(Ray ray) 
	{
		
		Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
		
		if (intersectionPoints.size() == 0)
			return null;
		
		Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
		Map.Entry<Geometry, Point3D> entry= closestPoint.entrySet().iterator().next();
		return entry;
		
	}*/
	private Entry findClosestIntersection(Ray ray) 
	{
		
		Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
		
		if (intersectionPoints.size() == 0)
			return null;
		
		//Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
		//Map.Entry<Geometry, Point3D> entry= closestPoint.entrySet().iterator().next();
		//return entry;
		return getClosestPoint(intersectionPoints);
		
	}

	private  Map<Geometry, Point3D> getClosestPoint1( Map<Geometry, List<Point3D>> intersectionPoints)
	{
		double distance = Double.MAX_VALUE;
		Point3D P0 = scene.getCamara().getMiddle();
		 Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();

		
		for (Map.Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet())
		{
			for (Point3D point: entry.getValue())
			{
				if (P0.distance(point) < distance)
				{
				minDistancePoint.clear(); 
				minDistancePoint.put(entry.getKey(), new Point3D(point));
				distance = P0.distance(point);
				}
		   }
		}
		
		return minDistancePoint; 
	}
	
	private  Entry getClosestPoint( Map<Geometry, List<Point3D>> intersectionPoints)
	{
		double distance = Double.MAX_VALUE;
		Point3D P0 = scene.getCamara().getMiddle();
	    Entry minDistancePoint=null;
		
		for (Map.Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet())
		{
			for (Point3D point: entry.getValue())
			{
				if (P0.distance(point) < distance)
				{
				minDistancePoint = new Entry(entry.getKey(), new Point3D(point));
				distance = P0.distance(point);
				}
		   }
		}		
		return minDistancePoint; 	
	}
	
	
	public void renderImage()
	{
		for(int i=0;i<imageWriter.getNx();i++)
		{
			for(int j=0;j<imageWriter.getNy();j++)
			{
				int pixelColorRed = 0;
				int pixelColorGreen = 0;
				int pixelColorBlue = 0;
				Ray ray1 = scene.getCamara().constructRayThroughAPixel(imageWriter.getNx(), imageWriter.getNy(), j-0.5,i-0.5,scene.getScreenDistance(), imageWriter.getWidth(),imageWriter.getHeight()) ;
				Ray ray2 = scene.getCamara().constructRayThroughAPixel(imageWriter.getNx(), imageWriter.getNy(), j-0.5,i+0.5,scene.getScreenDistance(), imageWriter.getWidth(),imageWriter.getHeight()) ;
				Ray ray3 = scene.getCamara().constructRayThroughAPixel(imageWriter.getNx(), imageWriter.getNy(), j,i,scene.getScreenDistance(), imageWriter.getWidth(),imageWriter.getHeight()) ;
				Ray ray4 = scene.getCamara().constructRayThroughAPixel(imageWriter.getNx(), imageWriter.getNy(), j+0.5,i-0.5,scene.getScreenDistance(), imageWriter.getWidth(),imageWriter.getHeight()) ;
				Ray ray5 = scene.getCamara().constructRayThroughAPixel(imageWriter.getNx(), imageWriter.getNy(), j+0.5,i+0.5,scene.getScreenDistance(), imageWriter.getWidth(),imageWriter.getHeight()) ;
				Ray rayList[] = {ray1,ray2,ray3,ray4,ray5};
				for (int k = 0; k < 5; k++) {
					Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(rayList[k]);
					if (intersectionPoints.isEmpty()) {
						pixelColorRed += scene.getBackground().getRed();
						pixelColorGreen += scene.getBackground().getGreen();
						pixelColorBlue += scene.getBackground().getBlue();
					} else {
						Entry closestPoint = getClosestPoint(intersectionPoints);
						
						pixelColorRed += calcColor(closestPoint.geometry, closestPoint.point, rayList[k]).getRed();
						pixelColorGreen += calcColor(closestPoint.geometry, closestPoint.point, rayList[k]).getGreen();
						pixelColorBlue += calcColor(closestPoint.geometry, closestPoint.point, rayList[k]).getBlue();
					    }  
					}
				imageWriter.writePixel(j,i,new Color((int)pixelColorRed/5,(int)pixelColorGreen/5,(int)pixelColorBlue/5));
				}
			}
		imageWriter.writeToimage();
	}

}
		
	/*public void renderImage()
	{
		for(int i=0;i<imageWriter.getNx();i++)
		{
			for(int j=0;j<imageWriter.getNy();j++)
			{
				Ray ray = scene.getCamara().constructRayThroughAPixel(imageWriter.getNx(), imageWriter.getNy(),j,i,scene.getScreenDistance(), imageWriter.getWidth(),imageWriter.getHeight()) ;
				Map<Geometry, List<Point3D>> intersectionPoints =getSceneRayIntersections(ray);
				//if (intersectionPoints.isEmpty())
				//	imageWriter.writePixel(j, i, scene.getBackground());
				//else                                               takes off the grid
				if (!intersectionPoints.isEmpty())
		 		{
					Map<Geometry, Point3D> closestPoint = getClosestPoint1(intersectionPoints);
					for (Map.Entry<Geometry, Point3D> entry: closestPoint.entrySet())
		 				imageWriter.writePixel(j,i, calcColor( entry.getKey(),entry.getValue(),ray));	
		 		}
			}
		}
		imageWriter.writeToimage();
	}
}*/

	
	

