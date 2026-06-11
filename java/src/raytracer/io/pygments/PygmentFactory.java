package raytracer.io.pygments;

import java.util.Scanner;
import raytracer.scene.pygments.Pygment;

/**
 *
 * @author fegemo
 */
public interface PygmentFactory {
    Pygment setupPygment(Scanner scanner) throws Exception;
}
