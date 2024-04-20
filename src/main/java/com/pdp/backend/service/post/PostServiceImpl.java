package com.pdp.backend.service.post;

import com.pdp.backend.model.post.Post;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * The PostServiceImpl class provides the implementation for the PostService interface.
 * It handles operations related to posts.
 *
 * @see com.pdp.backend.service.post.PostService
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 **/
public class PostServiceImpl implements PostService{
    private static PostServiceImpl instance;
    private PostServiceImpl() {}

    /**
     * Retrieves the singleton instance of the PostServiceImpl class.
     *
     * @return The singleton instance of the PostServiceImpl class
     */
    public static PostServiceImpl getInstance() {
        if (instance == null) {
            instance = new PostServiceImpl();
        }
        return instance;
    }

    /**
     * Adds a new post to the repository.
     *
     * @param object The post to add
     * @return True if the post is successfully added, otherwise false
     */
    @Override
    public boolean add(Post object) {
        repository.add(object);
        return true;
    }

    /**
     * Retrieves all posts belonging to a specific channel.
     * @see com.pdp.backend.service.post.PostService#getChannelPost(UUID) 
     * @param channelID The UUID of the channel whose posts are to be retrieved
     * @return The list of posts belonging to the specified channel
     */
    @Override
    public List<Post> getChannelPost(UUID channelID) {
        List<Post> posts = repository.getAll();
        return posts.stream()
                .filter((post -> post.getChannelID().equals(channelID)))
                .collect(Collectors.toList());
    }
    @Override
    public List<Post> search(String query) {
        return null;
    }
    @Override
    public boolean update(Post object) {
        Post post = repository.findById(object.getId());
        if (Objects.isNull(post)) return false;
        post.setContent(object.getContent());
        return true;
    }
    @Override
    public boolean delete(UUID id) {
        return repository.remove(id);
    }
    @Override
    public List<Post> getAll() {
        return repository.getAll();
    }


    @Override
    public Post getById(UUID id) {
        return repository.findById(id);
    }
}
