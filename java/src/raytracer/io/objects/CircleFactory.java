package raytracer.io.objects;

import java.util.Scanner;
import raytracer.math.Vector3;
import raytracer.scene.objects.Circle;
import raytracer.scene.objects.Object;

/**
 * Constrói um círculo, dado seu centro, vetor normal e raio.
 * @author fegemo
 */
public class CircleFactory implements ObjectFactory {

    @Override
    public Object setupObject(Scanner scanner) {
        return new Circle(
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
                scanner.nextDouble()
        );    
    }
    
}
