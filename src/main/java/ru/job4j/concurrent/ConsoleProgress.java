package ru.job4j.concurrent;

public class ConsoleProgress {

    static Thread progress;

    public static void progress() {
        var process = new char[]{'-', '\\', '|', '/'};

        progress = new Thread(
                () -> {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            for (char index : process) {
                                Thread.sleep(500);
                                System.out.print("\r load: " + index);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    public static void main(String[] args) throws InterruptedException {

        ConsoleProgress.progress();

        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}