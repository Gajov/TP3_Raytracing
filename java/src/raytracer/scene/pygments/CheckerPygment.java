package raytracer.scene.pygments;

import raytracer.math.Vector3;

/**
 * Um pigmento que é um padrão de xadrez em 3D.
 * @author fegemo
 */
public class CheckerPygment extends Pygment {

    private final Vector3 color1, color2;
    private final double cubeSize;
    
    public CheckerPygment(Vector3 color1, Vector3 color2, double cubeSize) {
        this.color1 = color1;
        this.color2 = color2;
        this.cubeSize = cubeSize;
    }
    
    @Override
    public Vector3 getColorAt(Vector3 position) {
        return (Math.floor(position.x / cubeSize) + Math.floor(position.y / cubeSize) + Math.floor(position.z / cubeSize)) % 2 == 0 ? color1 : color2;
    }

    @Override
    public String getPygmentName() {
        return "checker";
    }
    
}
