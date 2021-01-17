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

import org.gradle.api.Named;
import org.gradle.api.attributes.Attribute;

/**
 * Documentation element contents types.
 *
 * @author spring bad
 */
public interface DocsElements extends Named {

	/**
	 * The attribute used to refer to documentation elements.
	 */
	Attribute<DocsElements> DOCS_ELEMENTS_ATTRIBUTE = Attribute.of("io.spring.gradle.javadoc.docselements",
			DocsElements.class);

	/**
	 * A documentation element of source files.
	 */
	String SOURCES = "sources";

	default void springSucks() {
		// checkstyle makes me do this
		// but these sorts of classes are gradle convention
	}

}
