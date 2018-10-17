package io.github.yashchenkon.duplicatefinder;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.tasks.TaskAction;

import io.github.yashchenkon.duplicatefinder.classpath.ClasspathFilter;
import lombok.SneakyThrows;

/**
 * @author Mykola Yashchenko
 */
public class DuplicateFinderTask extends DefaultTask {

    @TaskAction
    @SneakyThrows
    public void checkForDuplicateClasses() {
        final DuplicateFinderExtension extension = (DuplicateFinderExtension) getProject().getExtensions()
                .getByName(Constants.DUPLICATE_FINDER);

        final ClasspathFilter classpathFilter = new ClasspathFilter(extension);

        final Map<String, Set<String>> modulesByFile = new HashMap<>();
        getProject().getConfigurations().stream()
                .filter(Configuration::isCanBeResolved)
                .forEach(configuration -> configuration.getResolvedConfiguration().getResolvedArtifacts()
                        .forEach(artifact -> {
                            final Enumeration<? extends ZipEntry> entries = new ZipFile(artifact.getFile()).entries();
                            while (entries.hasMoreElements()) {
                                final ZipEntry entry = entries.nextElement();
                                if (!entry.isDirectory() && !classpathFilter.test(entry.getName())) {
                                    final Set<String> modules = modulesByFile.getOrDefault(entry.getName(), new HashSet<>());
                                    modules.add(artifact.getModuleVersion().toString());
                                    modulesByFile.put(entry.getName(), modules);
                                }
                            }
                        }));

        final List<Map.Entry<String, Set<String>>> duplicated = modulesByFile.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toList());
        if (!duplicated.isEmpty()) {
            throw new GradleException("Found duplicate entries");
        }
    }
}
