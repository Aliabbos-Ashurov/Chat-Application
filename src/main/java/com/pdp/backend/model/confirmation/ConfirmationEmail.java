package com.pdp.backend.model.confirmation;

import com.pdp.backend.enums.MailType;
import com.pdp.backend.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  12:46
 **/
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class ConfirmationEmail extends BaseModel{
    private UUID userID;
    private String email;
    private Integer code;
    private MailType mailType;
}
