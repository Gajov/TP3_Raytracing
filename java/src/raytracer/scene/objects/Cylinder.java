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
        
        RayResponse bottomResponse = bottom.intersectsWith(ray);
        RayResponse topResponse = top.intersectsWith(ray);  
        RayResponse sideResponse = this.sideRayResponse(ray);


        if (bottomResponse.t < topResponse.t && bottomResponse.t < sideResponse.t && bottomResponse.intersected) {
            response = bottomResponse;
        } else if (topResponse.t < bottomResponse.t && topResponse.t < sideResponse.t && topResponse.intersected) {
            response = topResponse;
        } else if (sideResponse.intersected) {
            response = sideResponse;
        }




        return response;
    }

    private RayResponse sideRayResponse(Ray ray) {
        RayResponse response = new RayResponse();

        Vector3 axis = direction; // já normalizado

        Vector3 w = ray.P0.diff(baseCenter);

        Vector3 uPerp = ray.u.diff(axis.mult(ray.u.dot(axis)));
        Vector3 wPerp = w.diff(axis.mult(w.dot(axis)));

        double a = uPerp.dot(uPerp);
        double b = 2.0 * uPerp.dot(wPerp);
        double c = wPerp.dot(wPerp) - radius * radius;

        if (Math.abs(a) > Constants.TINY) {

            double delta = b * b - 4 * a * c;

            if (delta >= 0) {

                double sqrtDelta = Math.sqrt(delta);

                double t1 = (-b - sqrtDelta) / (2 * a);
                double t2 = (-b + sqrtDelta) / (2 * a);

                double t = Double.POSITIVE_INFINITY;

                if (t1 > Constants.TINY) {
                    t = t1;
                } else if (t2 > Constants.TINY) {
                    t = t2;
                }

                if (t < Double.POSITIVE_INFINITY) {

                    Vector3 P = ray.P0.add(ray.u.mult(t));

                    double h = P.diff(baseCenter).dot(axis);

                    double height = topCenter.diff(baseCenter).dot(axis);

                    if (h >= 0 && h <= height) {

                        Vector3 axisPoint =
                            baseCenter.add(axis.mult(h));

                        Vector3 normal =
                            P.diff(axisPoint).normalized();

                        response.intersected = true;
                        response.t = t;
                        response.P = P;
                        response.n = normal;
                    }
                }
            }
        }

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
