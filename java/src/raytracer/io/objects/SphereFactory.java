package raytracer.io.objects;

import java.util.Scanner;
import raytracer.math.Vector3;
import raytracer.scene.objects.Object;
import raytracer.scene.objects.Sphere;

/**
 * Constrói uma esfera dada por um ponto e um raio.
 * @author fegemo
 */
public class SphereFactory implements ObjectFactory {

    @Override
    public Object setupObject(Scanner scanner) {
        return new Sphere(
                new Vector3(
                        scanner.nextDouble(),
                        scanner.nextDouble(),
                        scanner.nextDouble()
                ), scanner.nextDouble()
        );
    }

}
