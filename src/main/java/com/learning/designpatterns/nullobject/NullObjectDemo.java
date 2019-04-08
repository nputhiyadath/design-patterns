package com.learning.designpatterns.nullobject;


class NullObjectDemo {
    public static void main(String[] args) {
        //ConsoleLog log = new ConsoleLog();
        //Log log = null;
        NullLog log = new NullLog();

        BankAccount ba = new BankAccount(log);
        ba.deposit(100);
        ba.withdraw(200);
    }
}