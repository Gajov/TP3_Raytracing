package raytracer.io.pygments;

import java.util.Scanner;
import raytracer.math.Vector3;
import raytracer.scene.pygments.Pygment;
import raytracer.scene.pygments.SolidPygment;

/**
 *
 * @author fegemo
 */
public class SolidPygmentFactory implements PygmentFactory {

    @Override
    public Pygment setupPygment(Scanner scanner) {
        return new SolidPygment(
                new Vector3(
                        scanner.nextDouble(),
                        scanner.nextDouble(),
                        scanner.nextDouble()
                ));
    }

}
