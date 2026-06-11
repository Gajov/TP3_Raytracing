package raytracer.scene.pygments;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import raytracer.math.Vector3;
import raytracer.math.Vector4;

/**
 * O pigmento de textura abre uma imagem e a usa para pegar a cor.
 * @author fegemo
 */
public class TexturePygment extends Pygment {

    public String texturePath;
    public Vector4 u, v;
    private final BufferedImage image;
    
    public TexturePygment(String texturePath, Vector4 u, Vector4 v) throws IOException {
        image = ImageIO.read(new File(texturePath));
        this.u = u;
        this.v = v;
    }
    
    @Override
    public Vector3 getColorAt(Vector3 position) {
        Vector4 positionHomogeneous = new Vector4(position, 1);
        double s = u.dot(positionHomogeneous);
        double t = v.dot(positionHomogeneous);
        int sInt = (int) ((Math.floor(s * image.getWidth()) + 1000000000 * image.getWidth()) % image.getWidth());
        int tInt = (int) ((Math.floor(t * image.getHeight()) + 1000000000 * image.getHeight()) % image.getHeight());

        int color =  image.getRGB(sInt, tInt); 
        double red   = ((color & 0x00ff0000) >> 16) / 255.0;
        double green = ((color & 0x0000ff00) >> 8) / 255.0;
        double blue  =  (color & 0x000000ff) / 255.0;
        return new Vector3(red, green, blue);
    }

    @Override
    public String getPygmentName() {
        return "texture";
    }
    
}
