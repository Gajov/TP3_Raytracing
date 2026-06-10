package raytracer.math;

/**
 *
 * @author fegemo
 */
public class Vector4 {

    public double x, y, z, w;

    /// ------------------------------------------------------------------------
    /// Construtores
    /// ------------------------------------------------------------------------
    public Vector4() {
    }

    /**
     * *
     * CONSTRUTOR que recebe 4 doubles como parâmetro Como usar: Vector4 v = new
     * Vector4(10.0, 0, 1.0, 1);
     *
     * @param x valor de x.
     * @param y valor de y.
     * @param z valor de z.
     * @param w valor de w.
     */
    public Vector4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    public Vector4(Vector3 from, double w) {
        this.x = from.x;
        this.y = from.y;
        this.z = from.z;
        this.w = w;
    }
    
    public double dot(Vector4 other) {
        return x * other.x + y * other.y + z * other.z + w * other.w;
    }
}
