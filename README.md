# Cidadania Italiana API

Esta é uma API RESTful para consultar informações sobre a cidadania italiana. Ela fornece endpoints para obter dados sobre documentos necessários, processos, requisitos e outras informações relevantes relacionadas à cidadania italiana para a Startup.

## Funcionalidades

- Consulta de documentos necessários para a cidadania italiana
- Informações sobre processos de obtenção da cidadania
- Requisitos para solicitar a cidadania italiana
- Perguntas frequentes e respostas sobre a cidadania italiana
- Consulta de fluxo de início e fim de cada processo

## Pré-requisitos

- Java 17 ou superior
- Maven 3.x
- Banco de dados NoSQL
- Variáveis de ambiente configuradas ${ENV_PROFILE} e ${MONGODB_URI} 

## Configuração

1. Clone este repositório em sua máquina local.
2. Configure o banco de dados MongoDB e atualize as configurações de conexão no arquivo `application.properties`.
3. Execute o comando `mvn install` para baixar as dependências do projeto.
4. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.

## Endpoints

A API oferece os seguintes endpoints principais:

- `GET /api-cidadania/cliente`: Retorna a lista paginada de clientes
- `GET /api-cidadania/cliente/{cpf}`: Retorna um modelo de cliente de acordo com o CPF informado do tipo String
- `POST /api-cidadania/cliente`: Insere novos clientes 
- `SpringDoc`: http://localhost:8080/api-cidadania/swagger-ui.html

Consulte a documentação da API para obter mais detalhes sobre os parâmetros de consulta e os formatos de resposta.

## Contribuição

Contribuições são bem-vindas! Se você encontrar algum problema, tiver sugestões ou quiser adicionar novos recursos, sinta-se à vontade para abrir uma issue ou enviar um pull request.


