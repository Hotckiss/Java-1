package ru.spbau.kirilenko;

public class CyclicA {

    public CyclicA cycle;

    public CyclicA(CyclicA cycle) {
        this.cycle = cycle;
    }
}
