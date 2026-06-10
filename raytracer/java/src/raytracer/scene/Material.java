package raytracer.scene;

public class Material {

    /** Coeficiente ambiente do material */
    public double p_a;
    /** Coeficiente difuso do material */
    public double p_d;
    /** Coeficiente especular do material */
    public double p_s;
    /** Expoente de especularidade do material */
    public double alpha;

    
    /** Coeficiente reflexivo do material. Apenas para versão recursiva. */
    public double p_r;
    /** Coeficiente refratário do material. Apenas para versão recursiva. */
    public double p_t;
    /** Coeficinete de snell do material. Indica a razão da densidade dos 
     * meios envolvidos. Apenas para versão recursiva. */
    public double snell;
}
