package raytracer.scene.pygments;

import raytracer.math.Vector3;

/**
 * Um pigmento define como um objeto deve ser pintado - como pegar uma cor.
 * @author fegemo
 */
public abstract class Pygment {
    /**
     * Deve retornar uma cor que deve ser aplicada ao objeto na dada posição.
     * @param position posição tendo sua cor determinada.
     * @return cor.
     */
    public abstract Vector3 getColorAt(Vector3 position);
    
    /**
     * Deve retornar o tipo do objeto (eg, "sphere").
     * @return uma string com o formato do objeto. É usado somente para
     * depuração.
     */
    public abstract String getPygmentName();
}
