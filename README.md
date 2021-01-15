# bancoSangue WK

Este aplicativo foi gerado utilizando JHipster 6.10.5.

## Definições importantes

Projeto possui API e front-end Angular de forma monolítica (fullstack on server).
A base de dados do projeto foi configurada para dois logins: Admin e User com perfis diferentes.

Antes de executar os passos a seguir o banco de dados MySQL deve existir na máquina e configurado com um usuário chamado root e senha root (privilégios admin) nesta instância local.

1. Crie um diretorio onde deseje configurar o projeto
2. Clone o projeto usando git:
   https://github.com/jeffjras/api-banco-sangue.git
3. Assim que concluir o clone, via linha de comando no diretório do projeto chamado api-banco-sangue, execute na raiz do projeto
   ```
   ./mvnw
   ```
4. Aguarde até a finalização e acesse o projeto por um navegador de sua preferência:
   http://localhost:8080
   
## Importação dos dados de Candidato

Para importar os dados de Candidatos acesse o sistema como administrador e escolhar a opção do item de menu API, em seguida localize e clique no método POST chamado /api/candidatoes/importar e logo em seguida clique no botão "Try Out" para habilitar a edição e então insira os dados do arquivo json, e por fim, clique em executar. Em seguida pelo menu Entidades -> Candidatos é possível visualizar os dados carregados de Candidatos 

Observação: Os Relatórios estão disponíveis no menu Entidades com o nome de cada um deles.   

## Desenvolvimento (caso não possua configuração node, execute os passos com npm )

Antes de construir este projeto, você deve instalar e configurar as seguintes dependências na máquina:

1. [Node.js][]: Usamos o Node para executar um servidor web de desenvolvimento e construir o projeto.
   Dependendo do seu sistema, você pode instalar o Node a partir da fonte ou como um pacote pré-empacotado.

Depois de instalar o Node, você deve ser capaz de executar o seguinte comando para instalar as ferramentas de desenvolvimento.
Você só precisará executar este comando quando as dependências forem alteradas em [package.json] (package.json).

```
npm install
```

Usamos scripts npm e [Webpack] [] como nosso sistema de construção.

Execute os seguintes comandos em dois terminais separados para criar uma experiência de desenvolvimento feliz onde seu navegador
atualiza automaticamente quando os arquivos mudam em seu disco rígido.
```

./mvnw


npm start
```

O Npm também é usado para gerenciar dependências CSS e JavaScript usadas neste aplicativo. Você pode atualizar dependências por
especificando uma versão mais recente em [package.json] (package.json). Você também pode executar `npm update` and `npm install` para gerenciar as dependências.
Adicione `help` sinalize em qualquer comando para ver como você pode usá-lo. Por exemplo, `npm help update`.

O comando `npm run` irá listar todos os scripts disponíveis para rodar neste projeto.


