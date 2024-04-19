package com.pdp.backend.model.message;

import com.pdp.backend.enums.MsgType;
import com.pdp.backend.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  09:29
 **/
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class Message extends BaseModel  {
    private UUID fromID;
    private UUID chatID;
    private MsgType msgType;
    private String content;
    private boolean isRead = false;

    public Message(UUID fromID,UUID chatID, String content,MsgType msgType) {
        this.fromID = fromID;
        this.chatID = chatID;
        this.content = content;
        this.msgType = msgType;
    }
}
