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

        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
            System.out.println(second.getState());
        }

        System.out.println("Состояние первой нити " + first.getState());
        System.out.println("Состояние второй нити " + second.getState());
        System.out.println("Работа завершена. Название нити: " + Thread.currentThread().getName());
    }
}