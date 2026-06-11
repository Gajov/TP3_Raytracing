package raytracer.scene.objects;

import raytracer.Ray;
import raytracer.RayResponse;
import raytracer.math.Constants;
import raytracer.math.Vector3;

/**
 * Uma esfera, que tem uma posição do seu centro e um raio.
 *
 */
public class Sphere extends Object {

    private final Vector3 center;
    private final double radius;

    public Sphere(Vector3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    

    /**
     * *
     * Retorna um objeto do tipo RayResponse com informações sobre uma possível
     * interseção do raio com este objeto.
     *
     * O objeto response deve preencher os seguintes valores: 
     * - response.intersected, true/false indicando se houve interseção ou não
     * - response.intersectionT, o valor t do raio da primeira interseção,
     *   caso haja mais que 1 interseção
     * - response.intersectionPoint, contendo o ponto (x,y,z) de interseção
     * - ray.intersectionNormal, contendo o vetor normal do objeto no ponto 
     *   de interseção. A normal deve ser *normalizada* (norma = 1)
     *
     *
     * @param ray o raio sendo lançado.
     * @return objeto com a resposta da colisão.
     */
    @Override
    public RayResponse intersectsWith(Ray ray) {
        RayResponse response = new RayResponse();
        
        // ---
        // [Semana 1] A primeira parte do TP3 (colisão) deve ser feita neste 
        // arquivo.
        //
        // Está sempre retornando que não houve colisão, sem sequer resolver a
        // equação de segundo grau que a verifica. Por isso, a imagem gerada 
        // tem apenas uma cor (raios não acertam nada).
        //
        // Você deve implementar este método de forma que ele retorne um objeto
        // RayResponse contendo a resposta da colisão: se intersectou ou não,
        // o valor de t, o P (ponto) onde o raio atingiu o objeto e o vetor n
        // (normal) do objeto nesse ponto.
        //
        Vector3 P0 = ray.P0;
        Vector3 u = ray.u;

        Vector3 p = this.center.diff(P0);

        double a = 1;
        double b = -2 * u.dot(p);
        double c = p.dot(p) - this.radius * this.radius;

        double delta = b*b - 4 * a * c;
        double t = 0;
        Vector3 P, n;

        if (delta >= 0){
            double t1 = (-b - Math.sqrt(delta)) / (2*a); 
            double t2 = (-b - Math.sqrt(delta)) / (2*a);
            
            if (t1<0 && t2<0){
                return response;
            } else {
                response.intersected = true;
                if(t1>0){
                    t = t1;
                } else if (t1<0 && t2>0){
                    t = t2; 
                }
                P = P0.add(u.mult(t));
                n = this.center.diff(P);
                
                response.t = t;
                response.P = P;
                response.n = n;
            } 
        } else {
            return response;
        }

        return response;
    }

    @Override
    public Vector3 getCenter() {
        return center;
    }

    @Override
    public String getGeometryName() {
        return "sphere";
    }
}
