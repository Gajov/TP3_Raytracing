package raytracer.io.objects;

import java.util.Scanner;
import raytracer.scene.objects.Object;

/**
 * Deve ser implementada por classes responsáveis por construir (configurar
 * com seus devidos parâmetros) objetos geométricos.
 * 
 * Por exemplo, uma esfera é descrita por um ponto (x,y,z) referente a seu
 * centro e seu raio. Um cilindro, por sua posição (x,y,z), raio e altura.
 * Um triângulo, pelas coordenadas de seus vértices; etc.
 */
public interface ObjectFactory {
    Object setupObject(Scanner scanner);
}
