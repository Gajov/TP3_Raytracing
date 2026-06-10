package raytracer.scene.objects;

import raytracer.Ray;
import raytracer.RayResponse;
import raytracer.math.Vector3;

/**
 * Um círculo dado por uma posição e um raio.
 * @author fegemo
 */
public class Circle extends Object {

    private final Vector3 center;
    private final double radius;
    private final Plane plane;
    
    public Circle(Vector3 center, Vector3 normal, double radius) {
        this.center = center;
        this.radius = radius;
        this.plane = new Plane(center, normal);
    }
    
    @Override
    public RayResponse intersectsWith(Ray ray) {
        RayResponse response = new RayResponse();
        
        return response;
    }

    @Override
    public Vector3 getCenter() {
        return this.center;
    }

    @Override
    public String getGeometryName() {
        return "circle";
    }
    
}
