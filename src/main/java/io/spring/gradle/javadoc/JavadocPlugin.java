/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.gradle.javadoc;

import java.io.File;
import java.util.function.Consumer;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ConfigurationPublications;
import org.gradle.api.plugins.JavaBasePlugin;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;

/**
 * Configures a project to be included in aggregate Javadoc.
 *
 * @author Rob Winch
 */
public class JavadocPlugin implements Plugin<Project> {

	/**
	 * The configuration holding sources that should be part of aggregate javadocs.
	 */
	public static final String JAVADOC_SOURCES_CONFIGURATION_NAME = "javadocSourcesElements";

	@Override
	public void apply(Project project) {
		project.getPlugins().withType(JavaBasePlugin.class).all((javaPlugin) -> withSourcesElements(project));
	}

	private void withSourcesElements(Project project) {
		// The configuration that actually provides the sources for javadocs
		project.getConfigurations().create(JAVADOC_SOURCES_CONFIGURATION_NAME, new Action<Configuration>() {
			@Override
			public void execute(Configuration config) {
				config.setCanBeResolved(false);
				config.setCanBeConsumed(true);
				AggregateJavadocConfigurations.configureAggregateSourcesAttributes(project.getObjects(), config);
				config.outgoing(new Action<ConfigurationPublications>() {
					@Override
					public void execute(ConfigurationPublications publications) {
						JavaPluginConvention javaPlugin = project.getConvention().getPlugin(JavaPluginConvention.class);
						SourceSet mainSrc = javaPlugin.getSourceSets().getByName(SourceSet.MAIN_SOURCE_SET_NAME);
						mainSrc.getAllJava().forEach(new Consumer<File>() {
							@Override
							public void accept(File file) {
								publications.artifact(file);
							}
						});
					}
				});
			}
		});
	}

}
