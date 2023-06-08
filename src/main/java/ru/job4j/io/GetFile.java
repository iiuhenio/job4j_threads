package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class GetFile {

    private final File file;

    public GetFile(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try {
            InputStream i = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(i);
            int data;
            while ((data = bufferedInputStream.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
            i.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
