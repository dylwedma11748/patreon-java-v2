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
