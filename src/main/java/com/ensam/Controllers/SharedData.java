package com.ensam.Controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SharedData{
    private static SharedData instance;

    private final ObservableList<String> imagePaths;

    private SharedData() {
        imagePaths = FXCollections.observableArrayList();
    }

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public ObservableList<String> getImagePaths() {
        return imagePaths;
    }

    public void addImagePath(String path) {
        imagePaths.add(path);
    }
}

