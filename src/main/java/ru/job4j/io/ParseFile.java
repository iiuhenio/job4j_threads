package ru.job4j.io;

import java.io.*;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        OutputStream o = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }
}