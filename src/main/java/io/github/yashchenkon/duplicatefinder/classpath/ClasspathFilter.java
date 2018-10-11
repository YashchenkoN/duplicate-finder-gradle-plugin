package io.github.yashchenkon.duplicatefinder.classpath;

import java.util.function.Predicate;

/**
 * @author Mykola Yashchenko
 */
public class ClasspathFilter implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return false;
    }
}
