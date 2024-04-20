package com.pdp.backend.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * The BaseModel class serves as the base class for other model classes in the application.
 * It provides common functionality and fields such as ID, creation date and time, and deletion status.
 * This class is designed to be extended by other model classes in the application.
 *
 * Instances of BaseModel are uniquely identified by their ID, which is generated using UUID.
 * Each instance also records the date and time of its creation.
 * Additionally, it maintains a flag indicating whether the instance has been deleted.
 *
 * This class is serializable to allow instances to be easily serialized and deserialized.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 11/April/2024
 */
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
    /**
     * Sets the deleted status of the BaseModel instance.
     *
     * @param deleted true if the instance is deleted, false otherwise
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    /**
     * Retrieves the current date and time in the format "dd-MM-yy - HH:mm:ss".
     *
     * @return the current date and time as a formatted string
     */
    private String getCurrentDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yy - HH:mm:ss");
        return LocalDateTime.now().format(dateTimeFormatter);
    }
}
