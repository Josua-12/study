package com.example.filterinterceptor.builderpattern;

import lombok.Builder;

public class HamburgerApp2 {
    public static void main(String[] args) {
        Hamburger2 hamburger2_1 = Hamburger2.builder().bun(1).patty(1).cheese(1).lettuce(2).tomato(1).bacon(1).build();
        Hamburger2 hamburger2_2 = Hamburger2.builder()
                .bun(2)
                .bacon(6)
                .build();
        System.out.println(hamburger2_1);
        System.out.println(hamburger2_2);
    }
}
