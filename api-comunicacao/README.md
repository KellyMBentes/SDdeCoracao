# API de Comunicação

Esta API busca solucionar problemas de sistemas que precisam tornar transparente a conexão com cliente.
Ela gerencia ID's de usuários com seus devidos IP de forma paralela e também provê uma interface de comunicação para envio e recebimento de dados.

## Usando

Para usar a API de Comunicação, basta importar para o seu projeto o arquivo api_comunicacao_(versão).jar
1. Importe as classes `APIComunicacao` e `ObjetoComunicacao`.
```java
import api_comunicacao.APIComunicacao;
import api_comunicacao.ObjetoComunicacao;
```

2. Para ligar o servidor, basta usar o método `ligarServidor` da classe `APIComunicacao`. Passe como parâmetro uma instância de `ObjetoComunicacao` informando os parâmetros **ip do servidor**, **porta do servidor** e **timeout** (tempo de espera).
Além disso, implemente as funções:

* `sucesso`: Que será chamada quando o servidor receber uma mensagem de algum cliente. Essa função espera receber como parâmetro uma `String` enviada pelo cliente.
  
* `erro`: Que será chamada quando ocorrer algum erro. Espera receber uma exceção (`Exception`).
  
* `fimEscuta`: Que é chamada quando o servidor fica esperando por um tempo maior que o tempo definido pelo atributo `timeout` do objeto. Não recebe nenhum parâmetro.
  

```java
APIComunicacao.ligarServidor(new ObjetoComunicacao(ip, porta, 10000){
	public void sucesso(String resultado){
		System.out.println("Recebida a requisição de "+this.getIpCliente());
		System.out.println(resultado);
		System.out.println("fim");
	}
	public void erro(Exception e){
		System.out.println("Falha:"+e.getMessage());
	}
	public void fimEscuta(){
		System.out.println("Fim da escuta.");
		System.out.println("Nenhuma requisição foi estabelecida.");
	}
}
```

3. Para enviar uma requisição, basta chamar a função `enviar` da classe `APIComunicacao`. Passe como parâmetro uma instância de `ObjetoComunicacao` informando os parâmetros **ip do cliente**, **porta do cliente**, **ip do servidor**, **porta do servidor** e **timeout** (tempo de espera).
  Semelhantemente, é necessária a implementação das funções `sucesso`, `erro`, `fimEscuta`.
  
```java
APIComunicacao.enviar(new ObjetoComunicacao(ip, porta, "192.168.0.1", 5000, "Olá, sou o cliente com IP "+ip, 10000){
	public void sucesso(String resultado){
		System.out.println("Requisição enviada");
		System.out.println(resultado);
		System.out.println("fim");
  	}
	public void erro(Exception e){
		System.out.println("Falha:"+e.getMessage());
	}
	public void fimEscuta(){
		System.out.println("Fim da escuta.");
		System.out.println("Nenhuma requisição foi estabelecida.");
	}
});
```

4. No momento de compilar, adicione o jar:

```
javac -cp api_comunicacao_1.0.0.jar [demais arquivos do seu projeto *.java]

```
5. e também no momento de executar

```
java -cp .:api_comunicacao_1.0.0.jar [demais arquivos do seu projeto]

```

## Contribuindo

Estas instruções irão levá-lo a uma cópia do projeto em funcionamento em sua máquina local para fins de desenvolvimento ou teste.

### Pré-requisitos

Para ter o projeto rodando na máquina local, são necessários:

- **Java (jdk) 1.8** ou superior

### Instalando

Obtenha o projeto do repositório

```
git clone git@github.com:KellyMBentes/SDdeCoracao.git
```

Acesse o diretório do projeto

```
cd SDdeCoracao/api-comunicacao/
```

Instale as dependências

```
make instalar
```

Finalmente, basta compilar o projeto e já pode usá-lo.

```
make compilar
```

### Testes

Caso deseja rodar alguns "testes" implementados, basta compilá-los e executá-los.
Na atual versão, foram implementados um teste servidor e cliente. Antes, é necessário criar a interface de rede com os ip's _192.168.0.1_ e _192.168.0.2_.

```
make compilar-testes
```

Abra um terminal e execute o servidor:

```
make executar-teste-servidor
```

 Num outro terminal, inicie o cliente:
 
 ```
make executar-teste-cliente
```

### Contribuindo

Para contribuir com o projeto, siga os passos

1. A partir do último conteúdo mais atualizado da master, gere um branch no formato **feature/[nome-da-feature]**

```
git checkout master
git pull origin master
git checkout -b feature/[nome-da-feature]
```

2. Após fazer as alterações necessárias, **NÂO FAÇA O MERGE PARA MASTER!** Abra um **[pull request](https://github.com/KellyMBentes/SDdeCoracao/compare)** da sua branch para a master e aguarde a revisão do código de algum desenvolvedor.

OB.: Lembre-se de atualizar a versão do projeto!


## Autores

- **Daniel Prett Campagna** - [danielpcampagna](https://github.com/danielpcampagna)

## Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE.md](LICENSE.md) para detalhes.
