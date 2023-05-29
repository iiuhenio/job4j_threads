package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { }
        );
        Thread second = new Thread(
                () -> { }
        );
        System.out.println("Имя первой нити: " + first.getName());
        System.out.println("Имя второй нити: " + second.getName());
        System.out.println(Thread.currentThread().getName());

        System.out.println(first.getState());
        first.start();
        second.start();

        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
        }
        while (second.getState() != Thread.State.TERMINATED) {
            System.out.println(second.getState());
        }
        System.out.println(first.getState());
        System.out.println(second.getState());
        System.out.println("Работа завершена, название нити: " + Thread.currentThread().getName());
    }
}