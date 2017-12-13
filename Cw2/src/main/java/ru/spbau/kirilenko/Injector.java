package ru.spbau.kirilenko;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * A class that allows to make dependency injection
 */
public class Injector {

    private static ArrayList<Class<?>> allAllowedImplementations = new ArrayList<>();
    private static HashSet<Class<?>> dependencies = new HashSet<>();
    private static HashMap<Class<?>, Object> instances = new HashMap<>();

    /**
     * Create and initialize object of `rootClassName` class using classes from
     * `implementationClassNames` for concrete dependencies.
     */
    public static Object initialize(String rootClassName, List<String> implementationClassNames) throws Exception {

        clearAllCollections();

        for (String name : implementationClassNames) {
            allAllowedImplementations.add(Class.forName(name));
        }

        Class<?> rootClass = Class.forName(rootClassName);

        if (!implementationClassNames.contains(rootClassName)) {
            allAllowedImplementations.add(rootClass);
        }

        return getInstance(rootClass);
    }

    private static Object getInstance(Class<?> toInit) throws InjectionCycleException, IllegalAccessException, InvocationTargetException, InstantiationException, ImplementationNotFoundException, AmbiguousImplementationException {

        if (dependencies.contains(toInit)) {
            throw new InjectionCycleException();
        }

        if (instances.containsKey(toInit)) {
            return instances.get(toInit);
        }

        dependencies.add(toInit);

        Constructor<?> singleConstructor = getSingleConstructor(toInit);
        Class<?>[] parameterTypes = singleConstructor.getParameterTypes();
        Object[] args = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> type = parameterTypes[i];
            Class<?> implementationType = null;

            for (Class<?> implementation : allAllowedImplementations) {
                if (type.isAssignableFrom(implementation)) {
                    if (implementationType == null) {
                        implementationType = implementation;
                    } else {
                        throw new AmbiguousImplementationException();
                    }
                }
            }

            if (implementationType == null) {
                throw new ImplementationNotFoundException();
            } else {
                args[i] = getInstance(implementationType);
            }
        }

        dependencies.remove(toInit);
        Object instance = singleConstructor.newInstance(args);
        instances.put(toInit, instance);

        return instance;
    }

    private static Constructor<?> getSingleConstructor(Class<?> from) {
        return from.getDeclaredConstructors()[0];
    }

    private static void clearAllCollections() {
        allAllowedImplementations.clear();
        dependencies.clear();
        instances.clear();
    }
}
