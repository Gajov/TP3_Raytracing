package raytracer.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import raytracer.io.objects.ObjectBuilder;
import raytracer.io.pygments.PygmentBuilder;
import raytracer.math.Vector3;
import raytracer.scene.Light;
import raytracer.scene.Material;
import raytracer.scene.objects.Object;
import raytracer.scene.pygments.Pygment;
import raytracer.scene.Scene;

public class Input {

    public static Scene readSceneFromFile(String fileName) throws IOException {
        System.out.println("Carregando arquivo: " + fileName);
        Scene scene = new Scene();
        Locale.setDefault(Locale.ENGLISH);

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        Scanner scanner;

        //-----------------------------------------------------------------
        // lendo a CÂMERA
        scanner = new Scanner(br.readLine());
        scene.camera.eye = new Vector3(
                scanner.nextDouble(),
                scanner.nextDouble(),
                scanner.nextDouble()
        );
        scanner = new Scanner(br.readLine());
        scene.camera.target = new Vector3(
                scanner.nextDouble(),
                scanner.nextDouble(),
                scanner.nextDouble()
        );
        scanner = new Scanner(br.readLine());
        scene.camera.up = new Vector3(
                scanner.nextDouble(),
                scanner.nextDouble(),
                scanner.nextDouble()
        );
        scanner = new Scanner(br.readLine());
        scene.camera.setFovy(scanner.nextDouble());

        //-----------------------------------------------------------------
        // lendo as FONTES DE LUZ
        scanner = new Scanner(br.readLine());
        int numOfLights = scanner.nextInt();
        scene.lights = new ArrayList<>(numOfLights);
        while (numOfLights > 0) {
            scanner = new Scanner(br.readLine());
            Light light = new Light();
            light.P_luz = new Vector3(
                    scanner.nextDouble(),
                    scanner.nextDouble(),
                    scanner.nextDouble()
            );
            light.L_i = new Vector3(
                    scanner.nextDouble(),
                    scanner.nextDouble(),
                    scanner.nextDouble()
            );
            light.a = scanner.nextDouble();
            light.b = scanner.nextDouble();
            light.c = scanner.nextDouble();

            scene.lights.add(light);
            numOfLights--;
        }

        //-----------------------------------------------------------------
        // lendo os PIGMENTOS
        scanner = new Scanner(br.readLine());
        int numOfPygments = scanner.nextInt();
        scene.pygments = new ArrayList<>(numOfPygments);
        try {
            while (numOfPygments > 0) {
                scanner = new Scanner(br.readLine());
                Pygment pygment = PygmentBuilder.getInstance().buildFromDescription(scanner, scene);
                scene.pygments.add(pygment);
                numOfPygments--;
            }
        } catch (Exception ex) {
            System.out.println("Erro ao carregar pigmentos: " + ex.getLocalizedMessage());
            System.exit(1);
        }

        //-----------------------------------------------------------------
        // lendo os MATERIAIS
        scanner = new Scanner(br.readLine());
        int numOfMaterials = scanner.nextInt();
        scene.materials = new ArrayList<>(numOfMaterials);
        while (numOfMaterials > 0) {
            scanner = new Scanner(br.readLine());
            Material material = new Material();
            material.p_a = scanner.nextDouble();
            material.p_d = scanner.nextDouble();
            material.p_s = scanner.nextDouble();
            material.alpha = scanner.nextDouble();
            material.p_r = scanner.nextDouble();
            material.p_t = scanner.nextDouble();
            material.snell = scanner.nextDouble();

            scene.materials.add(material);
            numOfMaterials--;
        }

        //-----------------------------------------------------------------
        // lendo os OBJETOS DA CENA
        scanner = new Scanner(br.readLine());
        int numOfObjects = scanner.nextInt();
        scene.objects = new ArrayList<>(numOfObjects);
        while (numOfObjects > 0) {
            scanner = new Scanner(br.readLine());
            Object object = ObjectBuilder.getInstance().buildFromDescription(scanner, scene);
            scene.objects.add(object);
            numOfObjects--;
        }

        scene.camera.calculateBase();
        
        System.out.println(scene.debugInfo());

        return scene;
    }
}
