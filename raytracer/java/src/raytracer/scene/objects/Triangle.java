package raytracer.scene.objects;

import raytracer.Ray;
import raytracer.RayResponse;
import raytracer.math.Vector3;

/**
 * Um triângulo dado por seus 3 vértices.
 * @author fegemo
 */
public class Triangle extends Object {

    private final Vector3 A;
    private final Vector3 B;
    private final Vector3 C;
    
    private final Vector3 AB;
    private final Vector3 AC;
    private final Plane plane;
    
    public Triangle(Vector3 A, Vector3 B, Vector3 C) {
        this.A = A;
        this.B = B;
        this.C = C;
        
        this.AB = B.diff(A);
        this.AC = C.diff(A);
        this.plane = new Plane(this.A, AB.cross(AC).normalized());
    }

    @Override
    public RayResponse intersectsWith(Ray ray) {
        RayResponse response = new RayResponse();
        
        return response;
    }

    @Override
    public Vector3 getCenter() {
        return A.add(B).add(C).mult(1/3.0);
    }

    @Override
    public String getGeometryName() {
        return "triangle";
    }
    
}
