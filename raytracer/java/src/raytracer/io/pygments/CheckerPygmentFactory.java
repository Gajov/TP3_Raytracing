package raytracer.io.pygments;

import java.util.Scanner;
import raytracer.math.Vector3;
import raytracer.scene.pygments.CheckerPygment;
import raytracer.scene.pygments.Pygment;

/**
 *
 * @author fegemo
 */
public class CheckerPygmentFactory implements PygmentFactory {

    @Override
    public Pygment setupPygment(Scanner scanner) {
        return new CheckerPygment(
                new Vector3(
                        scanner.nextDouble(),
                        scanner.nextDouble(),
                        scanner.nextDouble()
                ), new Vector3(
                        scanner.nextDouble(),
                        scanner.nextDouble(),
                        scanner.nextDouble()
                ), scanner.nextDouble()
        );
    }

}
