package com.pdp.backend.service.post;

import com.pdp.backend.model.post.Post;
import com.pdp.backend.repository.post.PostRepository;
import com.pdp.backend.service.BaseService;

import java.util.List;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  12:13
 **/
public interface PostService extends BaseService<Post> {
    PostRepository repository = new PostRepository();
    List<Post> getChannelPost(UUID channelID);
}
