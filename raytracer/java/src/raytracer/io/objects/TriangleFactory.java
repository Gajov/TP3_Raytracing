package raytracer.io.objects;

import java.util.Scanner;
import raytracer.math.Vector3;
import raytracer.scene.objects.Object;
import raytracer.scene.objects.Triangle;

/**
 * Constrói um triângulo dados três pontos.
 * @author fegemo
 */
public class TriangleFactory implements ObjectFactory {

    @Override
    public Object setupObject(Scanner scanner) {
        return new Triangle(
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
                new Vector3(
                        scanner.nextDouble(),
                        scanner.nextDouble(),
                        scanner.nextDouble()
                )
        );
    }
    
}
