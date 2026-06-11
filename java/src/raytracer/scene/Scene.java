package raytracer.scene;

import raytracer.scene.pygments.Pygment;
import raytracer.scene.objects.Object;
import java.util.ArrayList;

/**
 * Uma cena sendo renderizada pelo raytracer.
 * 
 * @author fegemo
 */
public class Scene {

    /** A configuração da câmera (posição, alvo, up e fov) */
    public Camera camera;
    /** Lista de fontes de luz */
    public ArrayList<Light> lights;
    /** Lista de pigmentos */
    public ArrayList<Pygment> pygments;
    /** Lista de materiais de objetos */
    public ArrayList<Material> materials;
    /** Lista de objetos que compõem a cena */
    public ArrayList<Object> objects;

    public Scene() {
        camera = new Camera();
    }

    public String debugInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Informacoes sobre a cena:\n");
        info.append("\tCamera:\n\t\teye: ").append(camera.eye.debugInfo());
        info.append("\n\t\ttarget: ").append(camera.target.debugInfo());
        info.append("\n\t\tup: ").append(camera.up.debugInfo());
        info.append("\n\t\tfovY: ").append(camera.getFovy());
        info.append("\n");

        info.append("\n\tLuzes: ").append(lights.size()).append("\n");
        for (Light l : lights) {
            info.append("\t\tposition: ").append(l.P_luz.debugInfo()).append("\n");
            info.append("\t\tcolor: ").append(l.L_i.debugInfo()).append("\n");
            info.append("\t\tatenuacoes: ").append(String.format("%f, %f, %f",
                    l.a, l.b,
                    l.c)).append("\n");
        }

        info.append("\n\tPigmentos: ").append(pygments.size()).append("\n");
        for (Pygment p : pygments) {
            info.append("\t\ttipo: ").append(p.getPygmentName()).append("\n");
        }

        info.append("\n\tMateriais: ").append(materials.size()).append("\n");
        for (Material m : materials) {
            info.append("\t\tambiente: ").append(m.p_a).append("\n");
            info.append("\t\tdifuso: ").append(m.p_d).append("\n");
            info.append("\t\tespecular: ").append(m.p_s).append("\n");
            info.append("\t\texpoente espec.: ").append(m.alpha).append("\n");
            info.append("\t\tindice reflex.: ").append(m.p_r).append("\n");
            info.append("\t\tindice refrac.: ").append(m.p_t).append("\n");
            info.append("\t\tsnell: ").append(m.snell).append("\n");
        }

        info.append("\n\tObjetos: ").append(objects.size()).append("\n");
        for (Object o : objects) {
            info.append("\t\ttipo: ").append(o.getGeometryName()).append("\n");
            info.append("\t\tposicao: ").append(o.getCenter().debugInfo()).append("\n");
            info.append("\t\tpigmento: ").append(o.pygment.getPygmentName()).append("\n");
        }

        return info.toString();
    }
}
