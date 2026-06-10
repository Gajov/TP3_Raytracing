package raytracer.scene.pygments;

import raytracer.math.Vector3;

/**
 * O pigmento sólido é como se fosse um baldinho de tinta de uma cor.
 * @author fegemo
 */
public class SolidPygment extends Pygment {

    private final Vector3 color;
    
    public SolidPygment(Vector3 color) {
        this.color = color;
    }
    
    @Override
    public Vector3 getColorAt(Vector3 position) {
        return color;
    }    

    @Override
    public String getPygmentName() {
        return "solid";
    }
}
