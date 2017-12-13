package ru.spbau.kirilenko;

public class SimpleInterfaceClass2 {
    public TwoImplementations mySuperInterface;
    public MySuperInterfaceImplementation mySuperInterfaceImplementation;

    public SimpleInterfaceClass2(TwoImplementations mySuperInterface, MySuperInterfaceImplementation mySuperInterfaceImplementation) {
        this.mySuperInterface = mySuperInterface;
        this.mySuperInterfaceImplementation = mySuperInterfaceImplementation;
    }
}
