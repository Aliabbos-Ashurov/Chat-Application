package com.pdp.backend.repository.post;

import com.pdp.backend.model.post.Post;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.UUID;
/**
 * The PostRepository class provides functionality to manage Post objects in a repository.
 * It implements the BaseRepository interface for basic CRUD operations.
 * PostRepository utilizes ListFileHandler for reading from and writing to files.
 *
 * Instances of PostRepository are initialized with a ListFileHandler for managing Post objects stored in files.
 *
 * This class allows for adding, removing, finding by ID, and retrieving all Post objects.
 * It provides methods to interact with Post objects persisted in files.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public class PostRepository implements BaseRepository<Post> {
    private final ListFileHandler<Post> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.POSTS);
    private final List<Post> posts;
    public PostRepository() {
        this.posts = listFileHandler.readList();
    }

    /**
     * Adds a Post object to the repository.
     * Writes the updated list of posts to the file.
     *
     * @param object the Post object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(Post object) {
        posts.add(object);
        listFileHandler.writeList(posts);
        return true;
    }
    /**
     * Removes a Post object from the repository by ID.
     * Writes the updated list of posts to the file.
     *
     * @param id the ID of the Post object to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = posts.removeIf(post -> post.getId().equals(id));
        if (removeIf) listFileHandler.writeList(posts);
        return removeIf;
    }
    /**
     * Finds a Post object in the repository by ID.
     *
     * @param id the ID of the Post object to find
     * @return the Post object if found, or null if not found
     */
    @Override
    public Post findById(UUID id) {
        return posts.stream()
                .filter((post -> post.getId().equals(id)))
                .findFirst().orElse(null);
    }
    /**
     * Retrieves all Post objects stored in the repository.
     *
     * @return the list of all Post objects stored in the repository
     */
    @Override
    public List<Post> getAll() {
        return posts;
    }
}
