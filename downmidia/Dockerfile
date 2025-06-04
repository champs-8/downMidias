# Imagem base com Java 21 e Python para instalar yt-dlp
FROM eclipse-temurin:21-jdk

# Atualiza pacotes e instala dependências do sistema
RUN apt-get update && apt-get install -y \
    python3 \
    python3-pip \
    ffmpeg \
    curl \
    && apt-get clean

# Instala yt-dlp com pipz
RUN pip3 install yt-dlp

# Cria diretório do app
WORKDIR /app

# Copia o JAR da aplicação
COPY target/downmidia-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta (ajuste se necessário)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
