package raytracer;

import raytracer.math.Vector3;

public class Ray {

    /** Ponto de origem do raio */
    public Vector3 P0;
    /** Vetor de direção do raio. Deve estar normalizado. */
    public Vector3 u;

    public Ray(Vector3 P0, Vector3 u) {
        this.P0 = P0;
        this.u = u;
    }
    
    public Ray() {

    }
}
