package com.pdp.backend.model.members;

import com.pdp.backend.enums.Role;
import com.pdp.backend.model.BaseModel;
import com.pdp.backend.model.support.Displayable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  12:43
 **/
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class Members extends BaseModel {
    private UUID userID;
    private UUID groupID;
    private Role role;
}
