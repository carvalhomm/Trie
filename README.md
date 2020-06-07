# Projeto de Monitoria 2020

## Build with Maven

## Projeto
Com orientação do meu amigo e professor Pedro Nuno de Souza Moura foi feito uma implementação da estrutura de dados Trie para auxiliar no apredizado de alunos da matéria de Estruturas de Dados 1 (ED1) da UNIRIO, Universidade Federal do Estado do Rio Janeiro. A árvore Trie é uma estrutura muito usada para dicionários em diversas plataformas. Como exemplo, as sugestões de palavras a serem escritas nos teclados de smartphones são feitas usando a estrutura Trie.
Para fins didáticos, será feito um site para o acesso dos alunos para visualizar a estrutura da árvore em tempo real conforme os dados são adicionados.

### Trie

### Estrutura do Projeto

#### TrieInterface

Responsável pela comunicação entre o front e a estrutura da árvore Trie.

#### Trie

Classe com toda a estrutura de dados da árvore Trie, responsável pela lógica da aplicação e gerar a estrutura correta.


## Informações de Deploy

### Gerar Versão de Produção

A versão do serviço fica localizado no arquivo principal `TrieInterface` na anotação `@Api` na flag `version` e no `Pom.xml` na tag `<deploy.version>`

### Subir para o Repositório com a Tag de Release

Para adicionar os arquivos da alteração no commit:

    git add .
	
Para o commit:

    git commit -m "release:$VERSION-TAG"
	
Gerando tag:

	git tag $VERSION-TAG
	
Subindo para o repositório no bitbucket:

	git push
	
Subindo a tag gerada para o repositório:

	git push --tag
	
	
### Cloud Shell
Tudo feito a partir de agora será feito no Cloud Shell.
No projeto em que for fazer o deploy da solução, abrir o `Cloud Shell`

No terminal do `Cloud Shell` para exibir os diretórios da raiz:

	ls -la
	
Caso não tenha a pasta `trie`, criar essa pasta:

	mkdir trie
	
Dentro dessa pasta, caso não tivesse `trie` clonar o repositório com o protocolo HTTP

Caso já tenha a pasta, entrar na pasta `trie`:

	cd trie

Atualizar o repositório:

	git pull


### Compilando o Projeto

Para compilar o projeto:

    mvn clean package

### Gerando o arquivo openapi.json

É preciso um arquivo de configuração do GCP, `openapi.json`. Pode ser gerado com o comando:

    mvn endpoints-framework:openApiDocs

### Deploy da Plataforma para o App Engine

Para o deploy:

0. Usar o comando `gcloud` para o deploy do arquivo das configurações de API:

         gcloud endpoints services deploy target/openapi-docs/openapi.json

0. Deploy da plataforma:

         mvn appengine:deploy
		