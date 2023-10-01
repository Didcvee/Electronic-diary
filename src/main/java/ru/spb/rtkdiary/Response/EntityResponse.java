package ru.spb.rtkdiary.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class EntityResponse {
    private final HttpStatus status;
    private final String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime time = LocalDateTime.now();

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public EntityResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
