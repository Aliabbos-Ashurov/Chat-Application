package com.pdp.backend.nio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * The ListFileHandler class provides functionality to read from and write to a file a list of objects of generic type T.
 * It is a utility class designed to handle serialization and deserialization of lists of objects.
 *
 * Instances of ListFileHandler are initialized with a directory path and a child path.
 * The directory path specifies the directory where the file will be stored, and the child path specifies the filename.
 *
 * This class allows for writing a list of objects to a file and reading a list of objects from a file.
 * It utilizes Java's serialization mechanism for object persistence.
 *
 * @param <T> the type of objects in the list
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 19/April/2024
 */
public class ListFileHandler<T> {
    private final String directory;
    private final String childPath;
    /**
     * Constructs a new ListFileHandler object with the specified directory and child path.
     *
     * @param directory the directory path where the file will be stored
     * @param childPath the child path specifying the filename
     */
    public ListFileHandler(String directory,String childPath) {
        this.directory = directory;
        this.childPath = childPath;
    }
    /**
     * Writes the provided list of objects to the file.
     *
     * @param list the list of objects to be written to the file
     */
    public void writeList(List<T> list) {
        String path = directory + childPath;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            for (T element : list) {
                outputStream.writeObject(element);
            }
        } catch (IOException e) {}
    }
    /**
     * Reads a list of objects from the file.
     *
     * @return the list of objects read from the file
     */
    @SuppressWarnings("unchecked")
    public List<T> readList(){
        String path = directory + childPath;
        List<T> list = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
            T element;
            while ((element = (T) inputStream.readObject()) != null) {
                list.add(element);
            }
        } catch (IOException | ClassNotFoundException e) {}
        return list;
    }
}
