package com.pdp.backend.model.chat;

import com.pdp.backend.model.BaseModel;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;
/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  09:31
 **/
@Getter
@ToString(callSuper = true)
public class Chat extends BaseModel {
    private UUID user1;
    private UUID user2;
    public Chat(UUID user1,UUID user2) {
        this.user1 = user1;
        this.user2 = user2;
    }
}
