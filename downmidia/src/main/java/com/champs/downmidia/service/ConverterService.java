package com.champs.downmidia.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ConverterService {
public File downloadAndConvert(String url, String format, String quality) throws Exception {
        String outputName = UUID.randomUUID().toString();
        Path downloadDir = Paths.get("downloads");
        
        if (!Files.exists(downloadDir)) {
            Files.createDirectories(downloadDir);
        }

        String outputTemplate = downloadDir.toAbsolutePath() + "/" + outputName + ".%(ext)s";
    
        ProcessBuilder pb;

        if ("mp3".equalsIgnoreCase(format)) {
            pb = new ProcessBuilder(
                "yt-dlp",
                "-f", "bestaudio",
                "--extract-audio",
                "--audio-format", "mp3",
                "--audio-quality", quality,
                "-o", outputTemplate,
                url
            );
        } else if ("mp4".equalsIgnoreCase(format)) {
            pb = new ProcessBuilder(
                "yt-dlp",
                "-f", "bestvideo[ext=mp4]+bestaudio[ext=m4a]/best[ext=mp4]/best",
                "-o", outputTemplate,
                url
            );
        } else {
            throw new IllegalArgumentException("Formato não suportado: " + format);
        }

        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            reader.lines().forEach(System.out::println); // log
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Erro ao converter a mídia.");
        }

        // Buscar arquivo convertido
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(downloadDir)) {
            for (Path path : stream) {
                if (path.getFileName().toString().startsWith(outputName)) {
                    return path.toFile();
                }
            }
        }

        throw new FileNotFoundException("Arquivo convertido não encontrado.");
    }
}
