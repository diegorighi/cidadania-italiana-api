# Cidadania Italiana API
Esta é uma API RESTful para consultar informações sobre a cidadania italiana. Ela fornece endpoints para obter dados sobre documentos necessários, processos, requisitos e outras informações relevantes relacionadas à cidadania italiana para a Startup.
Para métodos create e listar não foram adicionados ROLES aos usuários por questões de comodidade nos testes com SpringSecurity. Porém ao realizar o deploy em PRODUÇÃO deve-se especificar as roles ao JWT fornecido deixando apenas exposto o `/login` e talvez `/actuator` com SpringDoc.

## Funcionalidades
- Consulta de documentos necessários para a cidadania italiana
- Informações sobre processos de obtenção da cidadania
- Requisitos para solicitar a cidadania italiana
- Perguntas frequentes e respostas sobre a cidadania italiana
- Consulta de fluxo de início e fim de cada processo

## Running on Docker
- Entre no diretório raiz do projeto com powershell ou terminal/unix
- Realize o comando `mvn clean install -U`
- Realize o comando `mvn package`
- Realize o comando `docker build -t seuDir/tagNomeDaImagemDocker:1.0.0`
- Copie o ID da imagem através do comando `docker ps` 
- Realize o comando `docker run ID` OBS: No lugar o ID, cole o valor encontrado na instrução anterior. No Dockerfile já há a instsrução de `EXPOSE 8080`, logo não é necessário o comando `-p 8080:8080` após a instrução `docker`
- Poderá verificar na URI `http://localhost:8080/api-cidadania/cliente?size=3&page=0&sort=pessoa,primeiroNome,cpf,asc` se funcionou

## Running on Localhost 
- Java 17 ou superior 
- Maven 3.x
- IP externo configurado no Whitelist do MongoDB Atlas (enviar email pedindo inclusão)
- Variáveis de ambiente configuradas ${ENV_PROFILE} e ${MONGODB_URI} 
- Preferencialmente SpringToolSuite ou Eclipse

## Instruções para subir a aplicação localhost
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
- `Actuator`: http://localhost:8080/api-cidadania/actuator
- `Prometheus`: http://localhost:8080/api-cidadania/actuator/prometheus

Consulte a documentação da API para obter mais detalhes sobre os parâmetros de consulta e os formatos de resposta.

## Contribuição
Contribuições são bem-vindas! Se você encontrar algum problema, tiver sugestões ou quiser adicionar novos recursos, sinta-se à vontade para abrir uma issue ou enviar um pull request.


