package raytracer.scene.objects;

import raytracer.Ray;
import raytracer.RayResponse;
import raytracer.math.Vector3;
import raytracer.math.Constants;

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
        double denom = normal.dot(ray.u);

        if (Math.abs(denom) < Constants.TINY) {
            return response;
        }

        double t = normal.dot(samplePoint.diff(ray.P0)) / denom;

        // interseção atrás da origem do raio
        if (t < Constants.TINY) {
            return response;
        }

        Vector3 P = ray.P0.add(ray.u.mult(t));

        Vector3 n = normal;

        if (n.dot(ray.u) > 0) {
            n = n.mult(-1);
        }

        response.intersected = true;
        response.t = t;
        response.P = P;
        response.n = n;

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
