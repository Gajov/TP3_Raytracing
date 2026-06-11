package raytracer.io.pygments;

import java.util.Scanner;
import raytracer.math.Vector4;
import raytracer.scene.pygments.Pygment;
import raytracer.scene.pygments.TexturePygment;

/**
 * Instancia um pigmento.
 * @author fegemo
 */
public class TexturePygmentFactory implements PygmentFactory {

    @Override
    public Pygment setupPygment(Scanner scanner) throws Exception {
        return new TexturePygment(
                scanner.next(),
                new Vector4(
                        scanner.nextDouble(),
                        scanner.nextDouble(),
                        scanner.nextDouble(),
                        scanner.nextDouble()
                ),
                new Vector4(
                        scanner.nextDouble(),
                        scanner.nextDouble(),
                        scanner.nextDouble(),
                        scanner.nextDouble()
                ));
    }

}
