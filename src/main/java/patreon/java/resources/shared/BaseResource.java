/*

Patreon-Java-V2 - A Java library for Patreon's APIv2 endpoints

Copyright (C) 2023 Dylan Wedman

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License along
with this program; if not, write to the Free Software Foundation, Inc.,
51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

 */

package patreon.java.resources.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Links;
import com.github.jasminb.jsonapi.annotations.Type;

/**
 * A superclass made to handle JSON deserialization for all the resources.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResource {
	@Id
	private String id;

	@Links
	private com.github.jasminb.jsonapi.Links links;

	/**
	 * Returns the unique ID for this resource. This is constant and doesn't change.
	 * 
	 * @return the class type or null
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the JSON type for the specified class.
	 * 
	 * @param resourceClass the class to get the type for
	 * 
	 * @return the class type or null
	 */
	public static String getType(Class<? extends BaseResource> resourceClass) {
		Type type = resourceClass.getAnnotation(Type.class);

		if (type != null) {
			return type.value();
		} else {
			return null;
		}
	}

	/**
	 * Returns the JSON type for this class.
	 * 
	 * @return the JSON type for this class
	 */
	public String getType() {
		return getType(this.getClass());
	}

	/**
	 * Returns the links from the JSON response.
	 * 
	 * @return the links from the JSON response
	 */
	public com.github.jasminb.jsonapi.Links getLinks() {
		return links;
	}

	@Override
	public int hashCode() {
		String typeAndId = getType().concat(getId());
		return typeAndId.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		return this.hashCode() == o.hashCode();
	}
}
