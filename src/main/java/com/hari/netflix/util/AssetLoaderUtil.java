package com.hari.netflix.util;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class AssetLoaderUtil {
    private final int CHUNK_SIZE = 1_000_000;
    public ResponseEntity<byte[]> streamVideo(String videoId, Long start, Long end) throws Exception {
        if(end == null) end = start + CHUNK_SIZE;
        return readVideoFile(videoId, start, end);
    }

    private ResponseEntity<byte[]> readVideoFile(String filename, Long start, Long end) throws Exception {
        String currentRelativePath = Paths.get("").toAbsolutePath().toString();
        String filePath = currentRelativePath+"/Media/Movie/{fileName}.mp4";
        filePath = filePath.replace("{fileName}", filename);

        Long fileByteSize = getFileSize(filePath);
        end = Math.min(end, fileByteSize - 1);
        Long contentLength = end-start+1;

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Accept-Ranges", "bytes");
        responseHeader.set("Content-Type", "video/mp4");
        responseHeader.set("Content-Length", contentLength.toString());
        responseHeader.set("Content-Range", "bytes "+start+"-"+end+"/"+fileByteSize);

        HttpStatus status = end >= fileByteSize ? HttpStatus.OK : HttpStatus.PARTIAL_CONTENT;
        byte[] buffer = readByteRange(filePath, start, end);
        return new ResponseEntity(buffer, responseHeader, status);
    }

    public byte[] getAssetFile(String assetId) throws Exception {
        String currentRelativePath = Paths.get("").toAbsolutePath().toString();
        String filePath = currentRelativePath+"/Media/Thumbnail/{fileName}.jpg";
        filePath = filePath.replace("{fileName}", assetId);
        File imageFile = new FileSystemResource(filePath).getFile();
        byte[] data = Files.readAllBytes(imageFile.toPath());
        return data;
    }

    private File getResourceFilePath(String filename) {
        URL url = this.getClass().getClassLoader().getResource(filename);
        if(url == null) {
            throw new IllegalArgumentException(filename + " not found");
        }
        File file = new File(url.getFile());
        return file;
    }

    public byte[] readByteRange(String filePath, long start, long end) throws IOException {
        File file = new FileSystemResource(filePath).getFile();
        int contentLength = (int) (end - start + 1);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] buffer = new byte[contentLength];
        randomAccessFile.seek(start);
        randomAccessFile.readFully(buffer);
        return buffer;
    }

    public Long getFileSize(String filePath) throws IOException {
        File file = new FileSystemResource(filePath).getFile();
        Long fileSize = Files.size(file.toPath());
        return fileSize;
    }

}


