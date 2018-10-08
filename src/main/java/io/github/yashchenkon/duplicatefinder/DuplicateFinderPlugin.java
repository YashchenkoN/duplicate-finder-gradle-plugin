package io.github.yashchenkon.duplicatefinder;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import static io.github.yashchenkon.duplicatefinder.Constants.DUPLICATE_FINDER;

/**
 * @author Mykola Yashchenko
 */
public class DuplicateFinderPlugin implements Plugin<Project> {

    @Override
    public void apply(final Project project) {
        project.getExtensions().create(DUPLICATE_FINDER, DuplicateFinderExtension.class);
        project.getTasks().create(DUPLICATE_FINDER, DuplicateFinderTask.class);
    }
}
