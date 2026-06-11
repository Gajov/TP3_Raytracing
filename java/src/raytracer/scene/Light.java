package raytracer.scene;

import raytracer.math.Vector3;

public class Light {

    /** Posição da fonte de luz. */
    public Vector3 P_luz;

    /** Cor da fonte de luz. É a constante Li da equação de Phong. */
    public Vector3 L_i;

    /** Coeficiente de atenuação constante. É a constante a da equação de Phong. */
    public double a;
    /** Coeficiente de atenuação linear. É a constante b da equação de Phong */
    public double b;
    /** Coeficiente de atenuação quadrática. É a constante c da equação de Phong */
    public double c;
}
