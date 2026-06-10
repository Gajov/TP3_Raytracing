package raytracer.scene.objects;

import raytracer.Ray;
import raytracer.RayResponse;
import raytracer.math.Constants;
import raytracer.math.Vector3;

/**
 * Um cilindro que cresce ao longo um vetor direção. Possui um raio e uma altura.
 * @author fegemo
 */
public class Cylinder extends Object {

    private final double radius;
    private final Vector3 direction;
    private final Vector3 baseCenter;
    private final Vector3 topCenter;
    private final Circle bottom;
    private final Circle top;
    
    public Cylinder(Vector3 baseCenter, Vector3 direction, double radius, double height) {
        this.radius = radius;
        this.baseCenter = baseCenter;
        this.direction = direction.normalized();
        this.topCenter = baseCenter.add(this.direction.mult(height));
        this.bottom = new Circle(baseCenter, direction.mult(-1), radius);
        this.top = new Circle(topCenter, direction.mult(1), radius);
    }
    
    @Override
    public RayResponse intersectsWith(Ray ray) {
        RayResponse response = new RayResponse();
        
        return response;
    }

    @Override
    public Vector3 getCenter() {
        return baseCenter.add(topCenter).mult(0.5);
    }

    @Override
    public String getGeometryName() {
        return "cylinder";
    }
    
}
