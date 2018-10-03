package io.github.yashchenkon.duplicatefinder;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author Mykola Yashchenko
 */
public class DuplicateFinderPlugin implements Plugin<Project> {

    private static final String DUPLICATE_FINDER = "duplicateFinder";

    @Override
    public void apply(final Project project) {
        project.getExtensions().create(DUPLICATE_FINDER, DuplicateFinderExtension.class);
        project.getTasks().create(DUPLICATE_FINDER, DuplicateFinderTask.class);
    }
}
