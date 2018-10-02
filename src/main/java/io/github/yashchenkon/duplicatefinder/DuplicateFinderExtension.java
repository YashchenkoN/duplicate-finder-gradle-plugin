package io.github.yashchenkon.duplicatefinder;

import lombok.Data;

/**
 * @author Mykola Yashchenko
 */
@Data
public class DuplicateFinderExtension {
    private String[] ignoredResourcePatterns = new String[0];
    private String[] ignoredClassPatterns = new String[0];
}
