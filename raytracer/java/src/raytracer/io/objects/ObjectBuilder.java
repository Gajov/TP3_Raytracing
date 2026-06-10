package raytracer.io.objects;

import java.util.HashMap;
import java.util.Scanner;
import raytracer.scene.Scene;
import raytracer.scene.objects.Object;

/**
 * O ObjectBuilder tem a responsabilidade de delegar a uma fábrica a 
 * construção (instanciação) de um objeto geométrico de um respectivo tipo.
 * 
 * Como não faz sentido ter mais que 01 instâncias de ObjectBuilder, ele tem
 * um construtor privado (para evitar "new ObjectBuilder()") e foi implementado
 * como um "singleton": padrão de projeto que faz com que exista apenas 01
 * instância de certa classe.
 */
public final class ObjectBuilder {
    
    /**
     * A instância privada e estática que é o singleton desta classe.
     */
    private static ObjectBuilder INSTANCE = null;
    /**
     * Um mapa de fábricas que, dada uma string com o nome do que deve ser
     * construído (instanciado), cria uma instância do respectivo objeto 
     * geométrico.
     */
    private final HashMap<String, ObjectFactory> factories;
    
    private ObjectBuilder() {
        factories = new HashMap<>();
        factories.put("sphere", new SphereFactory());
        factories.put("plane", new PlaneFactory());
        factories.put("circle", new CircleFactory());
        factories.put("triangle", new TriangleFactory());
        factories.put("cylinder", new CylinderFactory());
        // adicionar novos objetos e suas fábricas aqui
    }
    
    public static ObjectBuilder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ObjectBuilder();
        }
        return INSTANCE;
    }
    
    public Object buildFromDescription(Scanner scanner, Scene scene) {
        int pygmentId = scanner.nextInt();
        int materialId = scanner.nextInt();
        String type = scanner.next();

        if (!factories.containsKey(type)) {
            throw new RuntimeException("O programa não sabe o que é um '" + type + "'! Crie um factory e registre-o no construtor de ObjectBuilder.");
        }
        
        // determina qual fábrica de objetos usar, dado o tipo escrito
        // no arquivo texto. Depois o constrói, deixando-o consumir os 
        // parâmetros daquela linha conforme sua fábrica precisa
        // eg, esfera precisa de x, y, z e raio
        // eg, cilindro precisa x, y, z (do centro), altura e raio
        // eg, triângulo precisa de x, y, z de cada vértice
        ObjectFactory factory = factories.get(type);
        Object object = factory.setupObject(scanner);
        
        // propriedades comuns a todos os objetos
        object.pygment = scene.pygments.get(pygmentId);
        object.material = scene.materials.get(materialId);

        return object;
    }
}
