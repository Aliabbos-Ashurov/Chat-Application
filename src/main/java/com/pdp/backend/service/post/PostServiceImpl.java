package com.pdp.backend.service.post;

import com.pdp.backend.defaultDB.DefaultDATA;
import com.pdp.backend.model.post.Post;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  12:14
 **/
public class PostServiceImpl implements PostService{
    private static PostServiceImpl instance;
    private PostServiceImpl() {}
    public static PostServiceImpl getInstance() {
        if (instance == null) {
            instance = new PostServiceImpl();
            DefaultDATA.defaultPost(instance);
        }
        return instance;
    }
    @Override
    public boolean add(Post object) {
        repository.add(object);
        return true;
    }
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
