package io.github.yashchenkon.duplicatefinder;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author Mykola Yashchenko
 */
public class DuplicateFinderPlugin implements Plugin<Project> {

    @Override
    public void apply(final Project project) {
        project.getTasks().create("duplicateClassesCheck", DuplicateFinderTask.class);
    }
}
