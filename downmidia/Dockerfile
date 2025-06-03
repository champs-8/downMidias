# Dockerfile para construir a imagem do DownMidia
FROM eclipse-temurin:21-alpine

# Instalar dependências do sistema para ffmpeg e yt-dlp
RUN apk add --no-cache ffmpeg python3 py3-pip curl

# Instalar yt-dlp via pip
RUN pip3 install yt-dlp --break-system-packages

# Criar diretório para app
WORKDIR /app

# Copiar jar do projeto para dentro do container
COPY target/downmidia-0.0.1-SNAPSHOT.jar app.jar

# Criar pasta downloads para arquivos temporários
RUN mkdir downloads

# Expor porta 8080
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
