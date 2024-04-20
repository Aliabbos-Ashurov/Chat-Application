package com.pdp.backend.model.post;

import com.pdp.backend.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;
/**
 * The Post class represents a post in a social media or messaging platform.
 * It extends the BaseModel class.
 * Each Post instance is associated with content and a channel ID where it is posted.
 *
 * This class provides a structured representation of posts in the platform.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
@Getter
@Setter
@AllArgsConstructor
@ToString(callSuper = true)
public class Post extends BaseModel  {
    private String content;
    private UUID channelID;
}
