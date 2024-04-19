package com.pdp.backend.nio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Aliabbos Ashurov
 * Date: 19/April/2024  09:42
 **/
public class ListFileHandler<T> {
    private final String directory;
    private final String childPath;
    public ListFileHandler(String directory,String childPath) {
        this.directory = directory;
        this.childPath = childPath;
    }

    public void writeList(List<T> list) {
        String path = directory + childPath;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            for (T element : list) {
                outputStream.writeObject(element);
            }
        } catch (IOException e) {}
    }
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
