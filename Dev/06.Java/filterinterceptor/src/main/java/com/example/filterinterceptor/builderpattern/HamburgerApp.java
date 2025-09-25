package com.example.filterinterceptor.builderpattern;

public class HamburgerApp {
    public static void main(String[] args) {
        Hamburger hamburger1 = new Hamburger(1, 1, 1, 1, 1, 1);
        Hamburger hamburger2 = new Hamburger(1, 1, 1, 1, 1);
        Hamburger hamburger3 = new Hamburger(1, 1, 1, 1);
        Hamburger hamburger4 = new Hamburger(1, 1, 1);

        System.out.println(hamburger1);
        System.out.println(hamburger2);
        System.out.println(hamburger3);
        System.out.println(hamburger4);
    }
}
