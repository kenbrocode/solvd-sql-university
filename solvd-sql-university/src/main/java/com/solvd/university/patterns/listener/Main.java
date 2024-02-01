package com.solvd.university.patterns.listener;

public class Main {
    public static void main(String[] args) {
        ListenersHolder.subcribe(new AccountingListener());
        ListenersHolder.subcribe(new HRListener());

    }
}
