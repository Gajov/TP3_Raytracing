package raytracer.io.pygments;

import java.util.HashMap;
import java.util.Scanner;
import raytracer.scene.Scene;
import raytracer.scene.pygments.Pygment;

/**
 *
 * @author fegemo
 */
public class PygmentBuilder {
    
    /**
     * A instância privada e estática que é o singleton desta classe.
     */
    private static PygmentBuilder INSTANCE = null;
    /**
     * Um mapa de fábricas que, dada uma string com o nome do que deve ser
     * construído (instanciado), cria uma instância do respectivo objeto 
     * geométrico.
     */
    private final HashMap<String, PygmentFactory> factories;
    
    private PygmentBuilder() {
        factories = new HashMap<>();
        factories.put("solid", new SolidPygmentFactory());
        factories.put("checker", new CheckerPygmentFactory());
        factories.put("texture", new TexturePygmentFactory());
        // adicionar novos objetos e suas fábricas aqui
    }
    
    public static PygmentBuilder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PygmentBuilder();
        }
        return INSTANCE;
    }
    
    public Pygment buildFromDescription(Scanner scanner, Scene scene) throws Exception {
        String type = scanner.next();
        
        // determina qual fábrica de objetos usar, dado o tipo escrito
        // no arquivo texto. Depois o constrói, deixando-o consumir os 
        // parâmetros daquela linha conforme sua fábrica precisa
        // eg, esfera precisa de x, y, z e raio
        // eg, cilindro precisa x, y, z (do centro), altura e raio
        // eg, triângulo precisa de x, y, z de cada vértice
        PygmentFactory factory = factories.get(type);
        
        return factory.setupPygment(scanner);
    }
}
