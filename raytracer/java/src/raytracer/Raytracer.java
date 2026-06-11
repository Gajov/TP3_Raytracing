package raytracer;

import raytracer.math.Vector3;
import raytracer.scene.Camera;
import raytracer.scene.Light;
import raytracer.scene.Material;
import raytracer.scene.objects.Object;
import raytracer.scene.pygments.Pygment;
import raytracer.scene.Scene;

public class Raytracer {

    /**
     * *
     * Cria um raio que vai da posição da câmera e passa pelo pixel indicado por
     * (column, row).
     *
     * @param camera A câmera com as configurações de eye, target e up.
     * @param row A coordenada y do pixel por onde o raio deve passar.
     * @param column A coordenada x do pixel por onde o raio deve passar.
     * @param height O número de linhas totais da imagem.
     * @param width O número de colunas totais da image.
     * @return Um raio que sai da origem da câmera e passa pelo pixel (column,
     * row).
     */
    private Ray generateInitialRay(Camera camera, double row, double column,
            int height, int width) {

        double aspectRatio = ((double) width) / height;
        double heightInCameraSpace = 2 * Math.tan(camera.getFovy() / 2);
        double widthInCameraSpace = heightInCameraSpace * aspectRatio;

        double ur = heightInCameraSpace * (((double) row) / height) - heightInCameraSpace / 2;
        double uc = widthInCameraSpace * (((double) column) / width) - widthInCameraSpace / 2;

        Vector3 gridPoint = new Vector3(camera.eye);
        gridPoint = gridPoint.add(camera.cameraBaseX.mult(uc));
        gridPoint = gridPoint.add(camera.cameraBaseY.mult(ur));
        gridPoint = gridPoint.diff(camera.cameraBaseZ);

        Vector3 direction = new Vector3(gridPoint.diff(camera.eye));

        return new Ray(camera.eye, direction.normalized());
    }

    /**
     * *
     * Lança um raio para a cena (camera) que passa por um certo pixel da cena.
     * Retorna a cor desse pixel.
     *
     * @param scene A cena onde o raio será lançado.
     * @param ray O raio a ser lançado.
     * @return A cor com que o pixel (acertado pelo raio) deve ser colorido.
     */
    private Vector3 castRay(Scene scene, Ray ray) {
        // Para todos os objetos da cena, verifica se o raio o acerta e pega aquele
        // que foi atingido primeiro (menor "t")
        RayResponse intersection = new RayResponse();
        Object closestObjectHit = null;
        for (Object obj : scene.objects) {
            RayResponse response = obj.intersectsWith(ray);
            if (response.intersected) {
                if (response.t < intersection.t) {
                    intersection = response;
                    closestObjectHit = obj;
                }
            }
        }

        // Verifica se um objeto foi atingido
        if (closestObjectHit == null) {
            // nada foi atingido. Retorna uma cor padrão (de fundo)
            return new Vector3(0, 0, 0);
        }

        // Algum objeto (closestObjectHit) foi atingido! Vamos determinar
        // qual é a sua cor no ponto atingido (intersection.P)...
        Material material = closestObjectHit.material;
        Pygment pygment = closestObjectHit.pygment;
        Vector3 C = pygment.getColorAt(intersection.P);

        // Esta é a variável contendo a COR RESULTANTE do pixel,
        // que deve ser devidamente calculada e retornada ao final
        // deste método (castRay). É a variável Cor(P) da equação de Phong.
        // (seu lado esquerdo)
        Vector3 CorP = C;

        // ------------------------------------------------------------------
        // A segunda parte do TP3 (sombreamento) deve ser feita neste arquivo.
        //
        // Aqui começamos a implementar a equaçăo de Phong (e armazenar o
        // resultado parcial na variável CorP)
        // ---
        // [Semana 2, exercício 1]: coloque a componente ambiente na 
        // cor resultante (variável CorP). É este trecho da equação:
        // 
        //   ambiente: p_a * L_a * C
        //
        // Obs: suponha que L_a é a cor branca (1,1,1). Logo, como é uma
        // multiplicação, você pode desconsiderá-la da fórmula.
        // (1 linha)

        
        // Verificamos se as fontes de luz estão iluminando este objeto.
        // Este trecho (for) é o somatório da equação de Phong.
        for (Light light : scene.lights) {

            // Verificamos se o raio atinge algum objeto ANTES da fonte de luz
            // Se for o caso, esta fonte de luz não contribui para a luz do objeto
            // Esta é a função vis da equação de Phong. Só que em vez de
            // retornar 0/1, retorna false/true. É este trecho da equação:
            //
            //   Vis(P, i)
            //
            boolean isObjectIlluminated = vis(scene, light, closestObjectHit, intersection);

            if (isObjectIlluminated) {
                // ---
                // [Semana 2, exercício 2]: calcular a componente difusa e
                // somá-la à CorP (resultante). É este trecho da equação:
                //
                //   difusa = cor_luz_atenuada * p_d * C * max(0, n.l)
                //
                // Sugestão: crie uma variável "corDaLuzAtenuada", porque ela
                // será usada tanto para a componente difusa quanto especular
                // (~8 linhas)

                
                
                
                
                
                
                
                
                // ---
                // [Semana 2, exercício 3]: calcular a componente especular e
                // somá-la à CorP (resultante). É este trecho da equação:
                //
                //   especular = cor_luz_atenuada * p_s * max(0, r.v)^alpha
                // 
                // Obs: é possível usar os vetores n.h em vez de r.v (modelo
                // de Blinn-Phong). Contudo, as imagens objetivo foram geradas
                // usando r.v (modelo de Phong original). Logo, sugiro usar
                // r.v mesmo.
                // (~5 linhas)
                





            }
        }

        // [EXTRA - Semana 2, exercício 5]: se o material for reflexivo,
        // lançar raio de reflexão chamando castRay recursivamente (~15 linhas)


        
        // [EXTRA - Semana 2, exercício 6]: se o material for transparente,
        // lançar raio de refração chamando castRay recursivamente (~25 linhas)

        
        
        // trunca a cor: faz r, g e b ficarem entre 0 e 1, caso tenha excedido
        CorP.truncate();

        return CorP;
    }

    /**
     * Verifica se a fonte de luz (light) está contribuindo diretamente para
     * iluminar o objeto atingido (objectHit) ou se há algum outro objeto da
     * cena obstruindo a luz de chegar até ele.
     *
     * @param scene a cena.
     * @param light a fonte de luz.
     * @param objectHit o objeto que foi atingido e queremos identificar se ele
     * está recebendo luz diretamente ou não.
     * @return true/false indicando se o objeto está recebendo luz diretamente
     * (true) ou se há outro objeto obstruindo a luz.
     */
    private boolean vis(Scene scene, Light light, Object objectHit, RayResponse intersection) {
        // [Semana 2, exercício 4]: implemente a função vis para determinar se
        // o objeto atingido está na sombra (false) ou recebe luz (true).
        // Crie um raio (Ray shadowRay) que vai do ponto de interseção 
        // com o objeto até a fonte de luz 
        // (basta instanciar devidamente um Ray, 1-3 linhas)

        
        // Percorra os objetos da cena, lançando o raio para cada objeto, 
        // verificando se ele foi atingido. Em caso afirmativo, verificar
        // se esse objeto atingido está posicionado entre a luz e o próprio
        // objeto, retornando true em caso afirmativo ou false, do contrário.
        // 
        // Obs: é importante verificar se o objeto atingido antes de chegar na
        // fonte de luz é o próprio objeto de onde o raio de sombra partiu.
        // Afinal, se o raio parte dele, o pode ser que a colisão do raio 
        // com o próprio objeto seja true, indicando que o próprio objeto está
        // obstruindo a luz de chegar nele, o que não deve acontecer.
        // (~10 linhas)

        
        
        
        
        
        
        
        // Está sempre retornando true, ou seja, sombras nunca são geradas...
        return true;
    }

    /**
     * *
     * MÉTODO que renderiza uma cena, gerando uma matriz de cores.
     *
     * @param scene um objeto do tipo Scene contendo a descrição da cena (ver
     * Scene.java)
     * @param height altura da imagem que estamos gerando (e.g., 600px)
     * @param width largura da imagem que estamos gerando (e.g., 800px)
     * @return uma matriz de cores (representadas em Vector3 - r,g,b)
     */
    public Vector3[][] renderScene(Scene scene, int height, int width) {
        Vector3[][] pixels = new Vector3[height][width];

        // Para cada pixel, lança um raio
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // [EXTRA - Semana 2, exercício 7]: em vez de lançar apenas 1
                // raio por pixel, lance n raios, sendo que cada um passa por
                // uma posição diferente dentro do pixel
                // Ao final, a cor do pixel é a média das cores de todos raios
                // lançados através dele

                // cria um raio primário
                Ray ray = generateInitialRay(scene.camera, i, j, height, width);

                // lança o raio e recebe a cor
                Vector3 color = castRay(scene, ray);

                // salva a cor na matriz de cores
                pixels[i][j] = color;
            }
        }

        return pixels;
    }
}
