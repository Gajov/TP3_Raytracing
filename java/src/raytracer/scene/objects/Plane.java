package raytracer.scene.objects;

import raytracer.Ray;
import raytracer.RayResponse;
import raytracer.math.Vector3;

/**
 * Um plano dado por um ponto e um vetor normal.
 * @author fegemo
 */
public class Plane extends Object {

    private final Vector3 samplePoint;
    private final Vector3 normal;

    public Plane(Vector3 samplePoint, Vector3 normal) {
        this.samplePoint = samplePoint;
        this.normal = normal.normalized();
    }
    
    @Override
    public RayResponse intersectsWith(Ray ray) {
        RayResponse response = new RayResponse();
        
        return response;
    }

    @Override
    public Vector3 getCenter() {
        return samplePoint;
    }

    @Override
    public String getGeometryName() {
        return "plane";
    }
    
}
