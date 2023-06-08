package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class GetFile {

    private final File file;

    public GetFile(File file) {
        this.file = file;
    }

    public String getContent() throws IOException {
        StringBuilder output = new StringBuilder();
        try {
            InputStream i = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(i);
            int data;
            while ((data = bufferedInputStream.read()) != -1) {
                output.append((char) data);
            }
            i.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public String getContentWithoutUnicode() throws IOException {
        StringBuilder output = new StringBuilder();
        try {
            InputStream i = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(i);
            int data;
            while ((data = bufferedInputStream.read()) != -1) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
            i.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public void check() {
        GetFile getFile = new GetFile(this.file);
        Predicate<Character> filter = x -> x < 0x80;
        try {
            InputStream i = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(i);
            int data;
            while ((data = bufferedInputStream.read()) != -1) {
                filter.test((char) data);
                if (filter.test((char) data)) {
                    getFile.getContentWithoutUnicode();
                } else {
                    getFile.getContent();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
