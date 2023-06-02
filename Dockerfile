# Define a imagem base
FROM openjdk:21-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /docker-api-cidadania

# Definir a variável de ambiente
ENV MONGODB_URI mongodb+srv://root:LGpi8711!!@api-cidadania.vjsxyj5.mongodb.net/api-cidadania
ENV ENV_PROFILE dev

# Copia o arquivo JAR da aplicação para o diretório de trabalho dentro do contêiner
COPY target/api-cidadania-0.0.1-SNAPSHOT.jar .

# Define o comando padrão a ser executado quando o contêiner for iniciado
CMD ["java", "-jar", "api-cidadania-0.0.1-SNAPSHOT.jar"]