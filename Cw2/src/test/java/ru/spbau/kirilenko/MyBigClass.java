package ru.spbau.kirilenko;

public class MyBigClass {

    public SimpleDependency simpleDependencyFirst;
    public SimpleDependency2 simpleDependencySecond;
    public MySuperInterface mySuperInterface;
    public SimpleDependency3 simpleDependencyThird;

    public MyBigClass(SimpleDependency sd, SimpleDependency2 sd2, MySuperInterface msi, SimpleDependency3 sd3) {
        this.simpleDependencyFirst = sd;
        this.simpleDependencySecond = sd2;
        this.mySuperInterface = msi;
        this.simpleDependencyThird = sd3;
    }
}
