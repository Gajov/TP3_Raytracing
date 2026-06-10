package raytracer.io.objects;

import java.util.Scanner;
import raytracer.math.Vector3;
import raytracer.scene.objects.Object;
import raytracer.scene.objects.Plane;

/**
 * Constrói um plano dado por um ponto e um vetor normal.
 * @author fegemo
 */
public class PlaneFactory implements ObjectFactory {

    @Override
    public Object setupObject(Scanner scanner) {
        return new Plane(
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
