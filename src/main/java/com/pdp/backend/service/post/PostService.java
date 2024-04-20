package com.pdp.backend.service.post;

import com.pdp.backend.model.post.Post;
import com.pdp.backend.repository.post.PostRepository;
import com.pdp.backend.service.BaseService;
import java.util.List;
import java.util.UUID;

/**
 * The PostService interface defines operations related to posts.
 * It extends the BaseService interface and specifies additional methods for post-specific functionality.
 *
 * @see com.pdp.backend.service.BaseService
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 **/
public interface PostService extends BaseService<Post> {
    PostRepository repository = new PostRepository();

    /**
     * Retrieves posts from a specified channel.
     *
     * @see com.pdp.backend.service.post.PostService#getChannelPost(UUID)
     * @param channelID The UUID of the channel from which posts are to be retrieved
     * @return A list of posts from the specified channel
     */
    List<Post> getChannelPost(UUID channelID);
}
