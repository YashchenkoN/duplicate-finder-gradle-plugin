package io.github.yashchenkon.duplicatefinder.classpath;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Mykola Yashchenko
 */
public class ClasspathPredicate implements Predicate<String> {

    private final List<Pattern> patterns;

    public ClasspathPredicate(final String... patterns) {
        this(Arrays.asList(patterns));
    }

    public ClasspathPredicate(final Collection<String> patterns) {
        this.patterns = patterns.stream()
                .map(s -> Pattern.compile(s, Pattern.CASE_INSENSITIVE))
                .collect(Collectors.toList());
    }

    @Override
    public boolean test(final String value) {
        return patterns.stream()
                .anyMatch(p -> p.matcher(value).matches());
    }
}
