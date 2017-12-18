package ru.spbau.kirilenko;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A class that test many possible situations of dependencies between classes with one constructor
 */
public class InjectorTest {

    public ArrayList<String> implementations;

    /**
     * Initialization of implementations list before each test;
     */
    @Before
    public void initImplementations() {
        implementations = new ArrayList<>();
    }

    /**
     * A simple test of initialization class without any fields
     */
    @Test
    public void testInjectorSimple() throws Exception {
        Object initializedClass = Injector.initialize("ru.spbau.kirilenko.SimpleClass", implementations);
        assertTrue(initializedClass instanceof SimpleClass);
    }

    /**
     * A test of initialization class with one dependency
     */
    @Test
    public void testInjectorSimpleDependency() throws Exception {
        implementations.add("ru.spbau.kirilenko.SimpleClass");
        Object initializedClass = Injector.initialize("ru.spbau.kirilenko.SimpleDependency", implementations);
        assertTrue(initializedClass instanceof SimpleDependency);
        SimpleDependency instance = (SimpleDependency) initializedClass;
        assertFalse(instance.simpleClass == null);
    }

    /**
     * A test of initialization class with two dependencies
     */
    @Test
    public void testInjectorSimpleDependency2() throws Exception {
        implementations.add("ru.spbau.kirilenko.SimpleClass");
        Object initializedClass = Injector.initialize("ru.spbau.kirilenko.SimpleDependency2", implementations);
        assertTrue(initializedClass instanceof SimpleDependency2);
        SimpleDependency2 instance = (SimpleDependency2) initializedClass;
        assertFalse(instance.simpleClass1 == null);
        assertFalse(instance.simpleClass2 == null);
    }

    /**
     * A test of initialization class with two dependencies but too many implementations of each class
     */
    @Test(expected = AmbiguousImplementationException.class)
    public void testInjectorSimpleDependency3() throws Exception {
        implementations.add("ru.spbau.kirilenko.SimpleClass");
        implementations.add("ru.spbau.kirilenko.SimpleClass");
        Injector.initialize("ru.spbau.kirilenko.SimpleDependency2", implementations);
    }

    /**
     * A test of initialization class with interface dependency
     */
    @Test
    public void testInjectorInterfaceDependency() throws Exception {
        implementations.add("ru.spbau.kirilenko.MySuperInterfaceImplementation");
        implementations.add("ru.spbau.kirilenko.SimpleClass");
        Object initializedClass = Injector.initialize("ru.spbau.kirilenko.SimpleInterfaceClass", implementations);
        assertTrue(initializedClass instanceof SimpleInterfaceClass);
        SimpleInterfaceClass instance = (SimpleInterfaceClass) initializedClass;
        assertTrue(instance.mySuperInterface instanceof MySuperInterfaceImplementation);
    }

    /**
     * A test of initialization class with cyclic dependency
     */
    @Test(expected = InjectionCycleException.class)
    public void testInjectorCycle() throws Exception {
        implementations.add("ru.spbau.kirilenko.CyclicA");
        Injector.initialize("ru.spbau.kirilenko.CyclicA", implementations);
    }

    /**
     * A test of initialization class but it needs non-existing implementation
     */
    @Test(expected = ImplementationNotFoundException.class)
    public void testInjectorImplementationNotFound() throws Exception {
        implementations.add("ru.spbau.kirilenko.CyclicA");
        Injector.initialize("ru.spbau.kirilenko.SimpleDependency", implementations);
    }

    /**
     * A test of initialization class with many dependencies of length more than one
     */
    @Test
    public void testInjectorBigClass() throws Exception {
        implementations.add("ru.spbau.kirilenko.MySuperInterfaceImplementation");
        implementations.add("ru.spbau.kirilenko.SimpleClass");
        implementations.add("ru.spbau.kirilenko.SimpleDependency");
        implementations.add("ru.spbau.kirilenko.SimpleDependency2");
        implementations.add("ru.spbau.kirilenko.SimpleDependency3");
        implementations.add("ru.spbau.kirilenko.SimpleInterfaceClass2");
        Object initializedClass = Injector.initialize("ru.spbau.kirilenko.MyBigClass", implementations);
        assertTrue(initializedClass instanceof MyBigClass);

        MyBigClass instance = (MyBigClass) initializedClass;

        assertTrue(instance.mySuperInterface instanceof MySuperInterfaceImplementation);
        assertTrue(instance.simpleDependencyThird instanceof SimpleDependency3);
        assertTrue(instance.simpleDependencySecond instanceof SimpleDependency2);
        assertTrue(instance.simpleDependencyFirst instanceof SimpleDependency);
        assertTrue(instance.simpleDependencySecond.simpleClass1 instanceof SimpleClass);
        assertTrue(instance.simpleDependencySecond.simpleClass2 instanceof SimpleClass);
        assertTrue(instance.simpleDependencyFirst.simpleClass instanceof SimpleClass);
        assertTrue(instance.simpleDependencyThird.simpleClass instanceof SimpleClass);
    }

    /**
     * A test of initialization class with two interface dependencies
     */
    @Test
    public void testInjectorInterfaceDependency2() throws Exception {
        implementations.add("ru.spbau.kirilenko.TwoImplementations");
        implementations.add("ru.spbau.kirilenko.MySuperInterfaceImplementation");
        Object initializedClass = Injector.initialize("ru.spbau.kirilenko.SimpleInterfaceClass2", implementations);
        assertTrue(initializedClass instanceof SimpleInterfaceClass2);
        SimpleInterfaceClass2 instance = (SimpleInterfaceClass2) initializedClass;
        assertTrue(instance.mySuperInterface instanceof TwoImplementations);
    }

    /**
     * A test of initialization class with long cyclic dependency
     */
    @Test(expected = InjectionCycleException.class)
    public void testInjectorTriangleCycle() throws Exception {
        implementations.add("ru.spbau.kirilenko.Cycle1");
        implementations.add("ru.spbau.kirilenko.Cycle2");
        implementations.add("ru.spbau.kirilenko.Cycle3");
        Injector.initialize("ru.spbau.kirilenko.Cycle1", implementations);
    }

    /**
     * A test of initialization class with many dependencies but list has ambiguous implementation
     */
    @Test(expected = AmbiguousImplementationException.class)
    public void testInjectorBigClassAmbiguous() throws Exception {
        implementations.add("ru.spbau.kirilenko.MySuperInterfaceImplementation");
        implementations.add("ru.spbau.kirilenko.SimpleClass");
        implementations.add("ru.spbau.kirilenko.SimpleDependency");
        implementations.add("ru.spbau.kirilenko.SimpleDependency2");
        implementations.add("ru.spbau.kirilenko.SimpleDependency3");
        implementations.add("ru.spbau.kirilenko.SimpleClass");
        Injector.initialize("ru.spbau.kirilenko.MyBigClass", implementations);
    }
}