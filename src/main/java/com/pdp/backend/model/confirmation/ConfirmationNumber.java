package com.pdp.backend.model.confirmation;

import com.pdp.backend.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  12:48
 **/
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class ConfirmationNumber extends BaseModel  {
    private UUID userID;
    private String phoneNumber;
    private String code;
}
