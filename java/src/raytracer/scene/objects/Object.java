package raytracer.scene.objects;

import raytracer.Ray;
import raytracer.RayResponse;
import raytracer.math.Vector3;
import raytracer.scene.Material;
import raytracer.scene.pygments.Pygment;

/**
 * Um objeto da cena.
 * 
 * @author fegemo <coutinho@decom.cefetmg.br>
 */
public abstract class Object {

    public Pygment pygment;
    public Material material;

    /**
     * *
     * Retorna **true** se o objeto tem interseção com o raio ou **false**, do
     * contrário.
     *
     * Além do valor retornado, caso exista interseção, este método preenche
     * _ray_ com informações cosbre o ponto de colisão: - ray.intersectionPoint,
     * contendo o ponto (x,y,z) de interseção - ray.intersectionNormal, contendo
     * o vetor normal do objeto no ponto de interseção. A normal deve ser
     * **normalizada** (norma = 1)
     *
     * @param ray
     * @return
     */
    public abstract RayResponse intersectsWith(Ray ray);
    
    /**
     * Deve retornar uma posição representando o centróide do objeto.
     * Este método é usado apenas para depuração.
     * @return uma posição com o centróide do objeto.
     */
    public abstract Vector3 getCenter();
    
    /**
     * Deve retornar o tipo do objeto (eg, "sphere").
     * @return uma string com o formato do objeto. É usado somente para
     * depuração.
     */
    public abstract String getGeometryName();
}
