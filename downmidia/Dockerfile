FROM eclipse-temurin:21-jdk

# Instala dependências
RUN apt-get update && apt-get install -y \
    python3 \
    python3-pip \
    ffmpeg \
    curl \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Instala yt-dlp com a flag necessária
RUN pip3 install yt-dlp --break-system-packages

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR da aplicação
COPY target/downmidia-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
