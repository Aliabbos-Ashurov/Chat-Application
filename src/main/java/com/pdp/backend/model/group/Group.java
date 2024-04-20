package com.pdp.backend.model.group;

import com.pdp.backend.model.BaseModel;
import com.pdp.backend.model.support.Displayable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;
/**
 * The Group class represents a group in a chat application.
 * It extends the BaseModel class and implements the Displayable interface.
 * Groups are used to organize users into specific chat groups.
 * Each group has an owner, a name, a description, member count, and privacy status.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
@Getter
@Setter
@ToString(callSuper = true)
public class Group extends BaseModel implements  Displayable {
    private UUID ownerID;
    private String name;
    private String description;
    private Integer memberCount;
    private boolean isPrivate;

    /**
     * Constructs a new Group object with the specified parameters.
     *
     * @param ownerID     the UUID of the owner of the group
     * @param name        the name of the group
     * @param description the description of the group
     * @param isPrivate   indicates whether the group is private or public
     */
    public Group(UUID ownerID, String name, String description,boolean isPrivate) {
        this.ownerID = ownerID;
        this.name = name;
        this.description = description;
        this.memberCount = 0;
        this.isPrivate = isPrivate;
    }
}
