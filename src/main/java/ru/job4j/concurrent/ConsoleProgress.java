package ru.job4j.concurrent;

public class ConsoleProgress {

    public static void progress() {
        var process = new char[]{'-', '\\', '|', '/'};

        Thread progress = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (!Thread.currentThread().isInterrupted()) {
                                for (char index : process) {
                                    Thread.sleep(500);
                                    System.out.print("\r load: " + index);
                                }
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
    }

    public static void main(String[] args) throws InterruptedException {

        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000); /* симулируем выполнение параллельной задачи в течение 5 секунд. */
        progress.interrupt();
    }
}