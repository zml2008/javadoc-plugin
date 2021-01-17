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

import org.gradle.api.Action;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.attributes.AttributeContainer;
import org.gradle.api.attributes.Category;
import org.gradle.api.attributes.DocsType;
import org.gradle.api.attributes.Usage;
import org.gradle.api.model.ObjectFactory;

/**
 * Configurations for aggregate javadoc classpaths.
 *
 * @author spring bad
 */
final class AggregateJavadocConfigurations {

	private AggregateJavadocConfigurations() {
	}

	static void configureAggregateSourcesAttributes(ObjectFactory objects, Configuration aggregateSources) {
		aggregateSources.attributes(new Action<AttributeContainer>() {
			@Override
			public void execute(AttributeContainer attributes) {
				attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.class, Usage.JAVA_RUNTIME));
				attributes.attribute(Category.CATEGORY_ATTRIBUTE,
						objects.named(Category.class, Category.DOCUMENTATION));
				attributes.attribute(DocsType.DOCS_TYPE_ATTRIBUTE, objects.named(DocsType.class, DocsType.SOURCES));
				attributes.attribute(DocsElements.DOCS_ELEMENTS_ATTRIBUTE,
						objects.named(DocsElements.class, DocsElements.SOURCES));
			}
		});
	}

}
