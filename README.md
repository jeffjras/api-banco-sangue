# bancoSangue WK

Este aplicativo foi gerado utilizando JHipster 6.10.5.

## Definições importantes

Projeto possui API e front-end Angular de forma monolítica (fullstack on server).
Sistema possui dois logins: Admin e User com perfis diferentes

### Para importar os dados basta acessar como admin e acionar a opção API e selecionar o método POST /api/candidatoes/importar e clicar no botão "Try Out" e após habitar a edição, inserir os dados do arquivo json,por fim, clicar em executar. Em seguida pelo menu Entidades -> Candidatos é possível visualizar os dados carregados de Candidatos 

Obs.: Os Relatórios estão disponíveis no menu Entidades com o nome de cada um deles.

Antes de executar os passos a seguir o banco de dados MySQL deve existir na máquina que será executada o projeto e tem que ter um usuário root e senha root (com privilégios adm) nesta instância local.

1. Crie um diretorio onde deseje configurar o projeto
2. Clone o projeto usando git:
   https://github.com/jeffjras/api-banco-sangue.git
3. Assim que concluir o clone, via linha de comando no diretório do projeto chamado api-banco-sangue,
   Execute ./mvnw
4. Aguarde até a finalização e acesse o projeto por um navegador de sua preferência:
   http://localhost:8080

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


