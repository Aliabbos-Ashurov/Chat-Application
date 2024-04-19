package com.pdp.backend.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 11/April/2024  20:19
 **/
@Getter
@ToString(of = {"dateTime"})
public class BaseModel implements Serializable {
    private final UUID id;
    private final String dateTime;
    private boolean isDeleted;
    public BaseModel() {
        this.id = UUID.randomUUID();
        this.dateTime = getCurrentDateTime();
        this.isDeleted = false;
    }
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    private String getCurrentDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yy - HH:mm:ss");
        return LocalDateTime.now().format(dateTimeFormatter);
    }
}
