package com.example.filterinterceptor.builderpattern;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Hamburger2 {
    private int bun;
    private int patty;
    private int cheese;
    private int lettuce;
    private int tomato;
    private int bacon;

}
