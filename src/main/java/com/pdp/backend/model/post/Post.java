package com.pdp.backend.model.post;

import com.pdp.backend.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;
/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  12:07
 **/
@Getter
@Setter
@AllArgsConstructor
@ToString(callSuper = true)
public class Post extends BaseModel  {
    private String content;
    private UUID channelID;
}
