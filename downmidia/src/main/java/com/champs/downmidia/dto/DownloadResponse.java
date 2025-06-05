package com.champs.downmidia.dto;

public class DownloadResponse {
    private String id;
    private String originalUrl;
    private String format;
    private String fileName;
    private String filePath;
    private long fileSizeBytes;
    private String message;

    //construtor
    public DownloadResponse(
        String id,
        String originalUrl,
        String format,
        String fileName,
        String filePath,
        long fileSizeBytes,
        String message) {

        this.id = id;
        this.originalUrl = originalUrl;
        this.format = format;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSizeBytes = fileSizeBytes;
        this.message = message;
    }

    // Getters e Setters
    public String getId() {return id;}
    public String getOriginalUrl() {return originalUrl;}
    public String getFormat() {return format;}
    public String getFileName() {return fileName;}
    public String getFilePath() {return filePath;}
    public long getFileSizeBytes() {return fileSizeBytes;}
    public String getMessage() {return message;}

    public void setId(String id) {this.id = id;}
    public void setOriginalUrl(String originalUrl) {this.originalUrl = originalUrl;}
    public void setFormat(String format) {this.format = format;}
    public void setFileName(String fileName) {this.fileName = fileName;}
    public void setFilePath(String filePath) {this.filePath = filePath;}
    public void setFileSizeBytes(long fileSizeBytes) {this.fileSizeBytes = fileSizeBytes;}
    public void setMessage(String message) {this.message = message;}
}
