package com.pdp.backend.model.user;

import com.pdp.backend.model.BaseModel;
import com.pdp.backend.model.support.Displayable;
import lombok.*;
/**
 * @author Aliabbos Ashurov
 * Date: 10/April/2024  21:57
 **/
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseModel implements Displayable {
    private String fullname;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private boolean isPremium;
    private boolean isActive;
    private boolean isEmailVerified;
    private boolean isNumberVerified;

    public User(String fullname, String username,String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = null;
        this.phoneNumber = null;
        this.isPremium = false;
        this.isActive = true;
        this.isNumberVerified = false;
        this.isEmailVerified = false;
    }

    @Override
    public String getName() {
        return username;
    }
}
