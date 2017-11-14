# API de Comunicação

Esta API busca solucionar problemas de sistemas que precisam tornar transparente a conexão com cliente.
Ela gerencia ID's de usuários com seus devidos IP de forma paralela e também provê uma interface de comunicação para envio e recebimento de dados.

## Usando

Para usar a API de Comunicação, basta importar para o seu projeto o arquivo *.jar

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
make install
```

Finalmente, basta compilar o projeto e já pode usá-lo.

```
make setup
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

- **Daniel Prett Campagna** - [PurpleBooth](https://github.com/danielpcampagna)

## Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE.md](LICENSE.md) para detalhes.
