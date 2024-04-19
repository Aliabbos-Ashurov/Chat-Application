package com.pdp.backend.repository.post;

import com.pdp.backend.model.post.Post;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.UUID;
/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  12:09
 **/
public class PostRepository implements BaseRepository<Post> {
    private final ListFileHandler<Post> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.POSTS);
    private final List<Post> posts;
    public PostRepository() {
        this.posts = listFileHandler.readList();
    }
    @Override
    public boolean add(Post object) {
        posts.add(object);
        listFileHandler.writeList(posts);
        return true;
    }
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = posts.removeIf(post -> post.getId().equals(id));
        if (removeIf) listFileHandler.writeList(posts);
        return removeIf;
    }
    @Override
    public Post findById(UUID id) {
        return posts.stream()
                .filter((post -> post.getId().equals(id)))
                .findFirst().orElse(null);
    }
    @Override
    public List<Post> getAll() {
        return posts;
    }
}
