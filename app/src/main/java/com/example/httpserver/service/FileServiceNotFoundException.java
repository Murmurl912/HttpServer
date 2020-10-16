package com.example.httpserver.service;

import java.nio.file.Path;

public class FileServiceNotFoundException extends FileServiceException {
    public FileServiceNotFoundException(int code, Path path) {
        super(code, path);
    }

    public FileServiceNotFoundException(String message, int code, Path path) {
        super(message, code, path);
    }

    public FileServiceNotFoundException(String message, Throwable cause, int code, Path path) {
        super(message, cause, code, path);
    }

    public FileServiceNotFoundException(Throwable cause, int code, Path path) {
        super(cause, code, path);
    }

    public FileServiceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, Path path) {
        super(message, cause, enableSuppression, writableStackTrace, code, path);
    }
}
