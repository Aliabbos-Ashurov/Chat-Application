package com.pdp.backend.model.subscribers;

import com.pdp.backend.enums.Role;
import com.pdp.backend.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  12:44
 **/
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class Subscribers extends BaseModel  {
    private UUID userID;
    private UUID channelID;
    private Role role;
}
