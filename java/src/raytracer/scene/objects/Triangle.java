package raytracer.scene.objects;

import raytracer.Ray;
import raytracer.RayResponse;
import raytracer.math.Constants;
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

        response = plane.intersectsWith(ray);
        if (!response.intersected) {
            return response;
        }

        Vector3 P = response.P;

        Vector3 v0 = AB;      // B - A
        Vector3 v1 = AC;      // C - A
        Vector3 v2 = P.diff(A);

        double d00 = v0.dot(v0);
        double d01 = v0.dot(v1);
        double d11 = v1.dot(v1);
        double d20 = v2.dot(v0);
        double d21 = v2.dot(v1);
        
        double denom = d00 * d11 - d01 * d01;
        double v = (d11 * d20 - d01 * d21) / denom;
        double w = (d00 * d21 - d01 * d20) / denom;
        double u = 1.0 - v - w;

        if (u < Constants.TINY || v < Constants.TINY || w < Constants.TINY) {
            return new RayResponse();
        }

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
