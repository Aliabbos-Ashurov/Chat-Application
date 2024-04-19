package com.pdp.backend.model.group;

import com.pdp.backend.model.BaseModel;
import com.pdp.backend.model.support.Displayable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.flogger.Flogger;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  09:16
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class Group extends BaseModel implements  Displayable {
    private UUID ownerID;
    private String name;
    private String description;
    private Integer memberCount;
    private boolean isPrivate;

    public Group(UUID ownerID, String name, String description,boolean isPrivate) {
        this.ownerID = ownerID;
        this.name = name;
        this.description = description;
        this.memberCount = 0;
        this.isPrivate = isPrivate;
    }
}
