package raytracer.io.objects;

import java.util.Scanner;
import raytracer.math.Vector3;
import raytracer.scene.objects.Cylinder;
import raytracer.scene.objects.Object;

/**
 * Um cilindro que cresce ao longo de um vetor direção.
 *
 * @author fegemo
 */
public class CylinderFactory implements ObjectFactory {

    @Override
    public Object setupObject(Scanner scanner) {
        return new Cylinder(
                new Vector3(
                        scanner.nextDouble(),
                        scanner.nextDouble(),
                        scanner.nextDouble()
                ),
                new Vector3(
                        scanner.nextDouble(),
                        scanner.nextDouble(),
                        scanner.nextDouble()
                ),
                scanner.nextDouble(),
                scanner.nextDouble()
        );
    }
}
