package ru.job4j.concurrent;

public class ConsoleProgress {

    public ConsoleProgress() {
    }

    public static void progress() throws InterruptedException {
        var process = new char[] {'-', '\\', '|', '/'};

        Thread thread = new Thread(
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
        /*
   Thread progress = new Thread(new ConsoleProgress());
       progress.start();
       Thread.sleep(5000); // симулируем выполнение параллельной задачи в течение 5 секунд.
       progress.interrupt();
       */
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();

    }

public static void main(String[] args) throws InterruptedException {

ConsoleProgress.progress();
    }
}