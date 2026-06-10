# Instruções para testes do ray tracer

Primeiramente, o arquivo `autores.hjson` deve ser preenchido. Ele contém
as seguintes informações:

```json
{
    "autores": [
        "Adamastor Carmargo",
        "Custódio Fonseca"
    ],
    "linguagem": "java",

    "implementacoes": {
        "colisoes": {
            "esfera": true,
            "plano": false,
            "circulo": false,
            "triangulo": false,
            "cilindro": false,
            "obj": false
        },

        "sombreamento": {
            "phong": true,
            "reflexao": false,
            "refracao": false,
            "distribuido": false
        }
    }
}
```

...sendo que você deve preencher o(s) nome(s) do(s) autor(es), definir
o nome da linguagem que foi usada (`js`, `java` ou `cpp`) e também
marcar o que foi implementado em cada etapa (`colisoes` e `sombreamento`).

Para executar os testes é necessário ter o [Node.js][node] instalado e,
então, digitar na pasta raiz o comando:

```bash
npm install
```

Em seguida, o script `corrigir.js` deve ser tornado executável e,
então, deve ser executado:

```bash
chmod +x corrigir.js
./corrigir.js
```

Após a execução, o navegador será aberto mostrando o resultado de todos
os testes.


[node]: https://nodejs.org/en/