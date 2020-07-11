package scene;
import java.awt.Color;
import geometry.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import elements.*;

public class Scene
{
	protected String sceneName;
	protected AmbientLight Iam;
	protected Color background;
	protected List<Geometry> geometries;
	protected List<Light> lights;
	protected Camara camara;
	protected double screenDistance;
	// ***************** Constructors ********************** //
	
	public Scene(String sceneName, AmbientLight iam, Color background, List<Geometry> geometries, List<Light> lights,
			Camara camara, double screenDistance) 
	{
		this.sceneName = sceneName;
		Iam = iam;
		this.background = background;
		this.geometries = geometries;
		this.lights = lights;
		this.camara = camara;
		this.screenDistance = screenDistance;
	}

	public Scene()
	{
		this.sceneName = "";
		Iam = new AmbientLight(new Color(255,0,255),0.1);
		this.background =new Color(0,0,0);
		this.geometries = new ArrayList<Geometry>();
		this.lights = new ArrayList<Light>();
		this.camara = new Camara();
		this.screenDistance = 100;
	}
	public Scene(Scene s) {
		this.sceneName = s.sceneName;
		Iam = s.Iam;
		this.background =s.background;
		this.geometries = (s.geometries);
		this.lights = s.lights;
		this.camara = (s.camara);
		this.screenDistance = s.screenDistance;
	}

	

	// ***************** Getters/Setters ********************** // 
	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public List<Geometry> getGeometry() {
		return (geometries);
	}

	public void setGeometry(List<Geometry> geometry) {
		this.geometries = geometry;
	}

	public Camara getCamara() {
		return (camara);
	}

	public void setCamara(Camara camara) {
		this.camara = camara;
	}

	public double getScreenDistance() {
		return screenDistance;
	}

	public void setScreenDistance(double screenDistance) {
		this.screenDistance = screenDistance;
	}
	public AmbientLight getAmbientLight() {
		return Iam;
	}

	public void setAmbientLight(AmbientLight iam) {
		Iam = iam;
	}

	public List<Light> getLights() {
		return lights;
	}

	public void setLights(List<Light> lights) {
		this.lights = lights;
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
		Scene other = (Scene) obj;
		if (Iam == null) {
			if (other.Iam != null)
				return false;
		} else if (!Iam.equals(other.Iam))
			return false;
		if (background == null) {
			if (other.background != null)
				return false;
		} else if (!background.equals(other.background))
			return false;
		if (camara == null) {
			if (other.camara != null)
				return false;
		} else if (!camara.equals(other.camara))
			return false;
		if (geometries == null) {
			if (other.geometries != null)
				return false;
		} else if (!geometries.equals(other.geometries))
			return false;
		if (lights == null) {
			if (other.lights != null)
				return false;
		} else if (!lights.equals(other.lights))
			return false;
		if (sceneName == null) {
			if (other.sceneName != null)
				return false;
		} else if (!sceneName.equals(other.sceneName))
			return false;
		if (Double.doubleToLongBits(screenDistance) != Double.doubleToLongBits(other.screenDistance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Scene [sceneName=" + sceneName + ", Iam=" + Iam + ", background=" + background + ", geometries="
				+ geometries + ", lights=" + lights + ", camara=" + camara + ", screenDistance=" + screenDistance + "]";
	}

	public void addGeometry(Geometry g)
	{
		geometries.add(g);
	}
	
	public void addLight(Light l)
	{
		lights.add(l);
	}
	
	
	public Iterator<Geometry>getGeometriesIterator()
	{
		return geometries.iterator();

	}
	

	public Iterator<Light>getLightIterator()
	{
		return lights.iterator();

	}
	
	


}
