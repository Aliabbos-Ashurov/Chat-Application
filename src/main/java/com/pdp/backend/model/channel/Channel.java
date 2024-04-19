package com.pdp.backend.model.channel;

import com.pdp.backend.model.BaseModel;
import com.pdp.backend.model.support.Displayable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;
/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  08:23
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class Channel extends BaseModel implements Displayable {
    private UUID ownerID;
    private String name;
    private String description;
    private boolean isPrivate;
    private Integer subscriberCount;
    private String link;
    public Channel(UUID ownerID, String name, String description,boolean isPrivate) {
        this.ownerID = ownerID;
        this.name = name;
        this.description = description;
        this.subscriberCount = 0;
        this.isPrivate = isPrivate;
        this.link = createLink();
    }
    private String createLink() {
        String name = String.valueOf(this.name);
        return "@" + name + "/chatapp";
    }
}
