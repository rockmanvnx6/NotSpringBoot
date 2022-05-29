package dev.auspham.notspringboot;

import dev.auspham.notspringboot.annotation.Autowired;
import dev.auspham.notspringboot.annotation.Bean;
import dev.auspham.notspringboot.annotation.Controller;
import dev.auspham.notspringboot.annotation.Start;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NotSpringBoot {
    static Map<Class<?>, Object> container = new HashMap<>();
    static Set<Class<?>> entries;
    public static void run(Class<?> targetClass, String[] args) throws Exception {
        Object instance = targetClass.getDeclaredConstructor().newInstance();
        populateContainer(instance, targetClass);
    }

    private static void populateContainer(Object instance, Class<?> targetClass) throws Exception{
        populateBeans(instance, targetClass);
        populateController(instance);
    }

    private static void populateController(Object instance) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections reflection = new Reflections(instance.getClass().getPackage().getName());
        entries = reflection.getTypesAnnotatedWith(Controller.class);
        for (Class<?> entryClass : entries) {
            Object entryInstance = entryClass.getDeclaredConstructor().newInstance();
            List<Field> fields = List.of(entryClass.getDeclaredFields());

            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    field.set(entryInstance, container.get(field.getType()));
                }
            }

            for (Method method : entryClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Start.class)) {
                    method.invoke(entryInstance);
                }
            }
        }
    }

    private static void populateBeans(Object instance, Class<?> targetClass) throws InvocationTargetException, IllegalAccessException {
        for (Method method : targetClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Bean.class)) {
                container.put(method.getReturnType(), method.invoke(instance));
            }
        }
    }
}