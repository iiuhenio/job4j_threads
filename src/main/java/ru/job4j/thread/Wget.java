package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private String fileName;

    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            long start = System.currentTimeMillis();
            System.out.println("Начало скачивания: " + start);
            int downloadData = 0;
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                downloadData = downloadData + bytesRead;
                if (downloadData == speed) {
                    long finish = System.currentTimeMillis();
                    System.out.println("Конец скачивания: " + finish);
                    long operationTime = finish - start;
                    System.out.println("Время скачивания: " + operationTime);
                    if (operationTime < 1000) {
                        Thread.sleep(1000 - operationTime);
                    }
                    downloadData = 0;
                    start = System.currentTimeMillis();
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            throw new InterruptedException("Должно быть три параметра");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args[2];
        Thread wget = new Thread(new Wget(url, speed, fileName));
        wget.start();
        wget.join();
    }
}