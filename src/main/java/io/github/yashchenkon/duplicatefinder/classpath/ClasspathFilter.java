package io.github.yashchenkon.duplicatefinder.classpath;

import java.util.function.Predicate;

import io.github.yashchenkon.duplicatefinder.DuplicateFinderExtension;

/**
 * @author Mykola Yashchenko
 */
public class ClasspathFilter implements Predicate<String> {

    private static final ClasspathPredicate DEFAULT_IGNORED_RESOURCES_PREDICATE = new ClasspathPredicate(
            "^META-INF/.*",
            "^OSGI-INF/.*",
            "^licenses/.*",
            ".*license(\\.txt)?$",
            ".*notice(\\.txt)?$",
            ".*readme(\\.txt)?$",
            ".*third-party(\\.txt)?$",
            ".*package\\.html$",
            ".*overview\\.html$");

    private static final ClasspathPredicate DEFAULT_IGNORED_CLASS_PREDICATE = new ClasspathPredicate(
            // this regex matches inner classes
            ".*\\$.*",
            "module-info");

    private static final ClasspathPredicate DEFAULT_IGNORED_LOCAL_DIRECTORIES = new ClasspathPredicate(
            "^.git$",
            "^.svn$",
            "^.hg$",
            "^.bzr$");

    private final ClasspathPredicate userPredicate;

    public ClasspathFilter(final DuplicateFinderExtension extension) {
        this.userPredicate = new ClasspathPredicate(extension.getIgnoredClassPatterns());
    }

    @Override
    public boolean test(final String value) {
        return DEFAULT_IGNORED_RESOURCES_PREDICATE
                .and(DEFAULT_IGNORED_CLASS_PREDICATE)
                .and(DEFAULT_IGNORED_LOCAL_DIRECTORIES)
                .and(userPredicate)
                .test(value);
    }
}
