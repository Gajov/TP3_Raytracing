#!/usr/bin/env node
import { exec } from 'node:child_process';
import { performance } from 'node:perf_hooks';
import fs from 'node:fs';
import http from 'node:http';
import handler from 'serve-handler';
import open from 'open';
import ProgressBar from 'progress';
import chalk from 'chalk';
import portfinder from 'portfinder';
import Hjson from 'hjson';

// linguagens suportadas
class Linguagem {
    constructor(pasta, comandoCompilar, comandoExecutar) {
        this.pasta = pasta;
        this.comandoCompilar = comandoCompilar;
        this.comandoExecutar = comandoExecutar;
    }
}
const linguagens = {
    cpp: new Linguagem(
        'cpp',
        'cd cpp/Makefile && make',
        c => `cd cpp/Makefile && make run-${c}`
    ),
    java: new Linguagem(
        'java',
        'cd java && ./gradlew -b projeto.gradle jar && chmod +x dist/raytracer.jar',
        c => `java -jar java/dist/raytracer.jar cenas_txt/cena-${c}.txt`
    ),
    js: new Linguagem(
        'js',
        'cd js && npm install',
        c => `node js/main.js cena-${c}.txt`
    )
};

// todos os casos de teste
const cenas = [
    'simples',
    'primitivas',
    '2-fontes-luz',
    'arvore',
    'empilhadas',
    'whitted',
    'cornell-box'
].map(c => `cena-${c}`);

class Resultado {
    constructor(sucesso, mensagemDoPrograma) {
        this.sucesso = sucesso;
        this.mensagemDoPrograma = mensagemDoPrograma;
    }
}

const progresso = new ProgressBar('Arquivos de teste: [:bar] :percent -- :elapseds decorridos', { total: cenas.length });


// pega a configuração: qual linguagem foi usada
let config;
try {
    const conteudoAutores = fs.readFileSync('./autores.hjson', 'utf8');
    config = Hjson.parse(conteudoAutores);
} catch (erro) {
    let mensagem;
    if (erro.name === 'SyntaxError') {
        mensagem = chalk.red(`Erro de sintaxe`) + ` no arquivo ${chalk.yellow('autores.hjson')}: ${erro}`;
    } else if (erro.code === 'ENOENT') {
        mensagem = chalk.red(`Erro`) + `: arquivo ${chalk.yellow('autores.hjson')} nao encontrado.`;
    } else {
        mensagem = chalk.red(`Erro desconhecido`) + ` ao abrir ${chalk.yellow('autores.hjson')}: ${erro}`;
    }
    console.error(mensagem);
    process.exit(1);
}

const autores = config.autores;
const linguagem = linguagens[config.linguagem];
// se a linguagem não for suportada, lançar erro
if (!linguagem) {
    const linguagensDisponiveis = Object.keys(linguagens).map(l => chalk.magenta(l)).join(chalk.reset(' ou ')); 
    console.error(
        `${chalk.red('Erro')}: a linguagem está incorreta no arquivo ${chalk.yellow('autores.hjson')}: ${chalk.red(config.linguagem || '""')}.\n      Deveria ser ${linguagensDisponiveis}.`);
    process.exit(1);
}

// compila o raytracer na linguagem estipulada em autores.hjson
let promessaCompilacao = new Promise((resolve, reject) => {
    exec(linguagem.comandoCompilar, (err, stdout, stderr) => {
        if (err) {
            reject({
                descricao: `Erro ao compilar o projeto '${linguagem.pasta}'.`,
                err
            });
        } else {
            resolve();
        }
    })
});

// executa o raytracer da linguagem usada para cada caso de teste
let promessasDeExecucao = cenas.map(c => {
    const nomeDaCena = `${c.substr(c.indexOf('cena-') + 'cena-'.length)}`;
    const comandoExecutar = linguagem.comandoExecutar(nomeDaCena);

    return _ => new Promise((resolve, reject) => {
        exec(comandoExecutar, (err, stdout, stderr) => {
            if (err) {
                reject({
                    descricao: `Erro ao executar o raytracer para ${c}.txt, com comando "${comandoExecutar}".`,
                    err
                });
            } else {
                progresso.tick();
                resolve();
            }
        })
    });
});

// executa as promessas em sequencialmente
(async () => {
    const resultados = [];
    try {
        const primeirosNomesAutores = autores.map(a => a.substr(0, a.indexOf(' ') || a.length)).map(a => chalk.blue(a)).join(chalk.reset(' e '));
        console.log(`Iniciando compilação do projeto "${chalk.magenta(linguagem.pasta)}" de ${primeirosNomesAutores}...`);
        await promessaCompilacao;
    
        console.log('Iniciando execução dos testes do ray tracer...');
        for (let teste of promessasDeExecucao) {
            const resultado = new Resultado(true);
            try {
                const t0 = performance.now();
                await teste();
                const t1 = performance.now();
                resultado.tempo = (t1 - t0) / 1000;
            } catch(erro) {
                resultado.sucesso = false;
                resultado.mensagemDoPrograma = erro;
            } finally {
                resultados.push(resultado);
            }
        }
        
        console.log('Resultados dos testes:');
        mostraRelatorio(resultados);

        // abre servidor para servir index.html
        serve();
    } catch(erro) {
        mostraErro(erro);
    }
})();
            

function mostraErro({ descricao, err }) {
    console.log(descricao);
    console.error(`Detalhes:`, err);
}

// mostra o resultado da execução de cada teste
function mostraRelatorio(resultados) {
    resultados.forEach((resultado, i) => {
        console.log(`${resultado.sucesso ? chalk.green('✓') : chalk.red('⨉')} ${cenas[i]} ${resultado.tempo ? (chalk.gray(resultado.tempo.toFixed(2) + 's')) : ''}`)
        if (resultado.mensagemDoPrograma) {
            console.error(' ', resultado.mensagemDoPrograma.descricao ? resultado.mensagemDoPrograma.descricao + '\n  ' + resultado.mensagemDoPrograma.err : resultado.mensagemDoPrograma);
        }
    });
}

// abre o navegador servindo index.html
function serve() {
    const servidor = http.createServer((request, response) => {
        return handler(request, response, {
            headers: [
                {
                    'source': '*',
                    'headers': [
                        {
                            'key': 'Cache-Control',
                            'value': 'no-cache, no-store, must-revalidate'
                        }, {
                            'key': 'Pragma',
                            'value': 'no-cache'
                        }, {
                            'key': 'Expires',
                            'value': '0'    
                        }
                    ]
                }
            ]
        });
    });
    
    portfinder.basePort = 8085;
    portfinder.highestPort = 8300;
    portfinder.getPortPromise().then(porta => {
        servidor.listen(porta, () => {
            const url = `http://localhost:${porta}`;
            console.log(`Veja os resultados em ${chalk.blue(url)}`);
            open(url);
        });
    });
}