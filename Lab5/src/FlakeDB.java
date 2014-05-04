import java.util.Map;
import java.util.TreeMap;

public class FlakeDB {
	public Map<String,Flake> theFlakes = new TreeMap<String,Flake>();
	
	public FlakeDB() {
		theFlakes.put("Convex Koch",new ConvexKoch());
		theFlakes.put("Mandelbrot PS",new MandelbrotPScurve());
		theFlakes.put("Konkav Koch",new KonkavKoch());
		theFlakes.put("Penta",new Penta());
		theFlakes.put("Sierpinskitriangel",new Sierpinskitriangel());
		
	}
}
