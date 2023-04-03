package patreon.java.resources.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.resources.shared.BaseResource;
import patreon.java.resources.shared.SocialConnections;
import patreon.java.resources.v2.Campaign;

/**
 * A basic implementation of the V1 User resource. Used in substitution for the
 * V2 User resource when it's related link doesn't work.
 */
@Type("user")
public class User extends BaseResource {
	
	private String created;
	private String email;
	private String firstName;
	private String fullName;
	private String imageUrl;
	private boolean isEmailVerified;
	private String lastName;
	private String patronCurrency;
	private SocialConnections socialConnections;
	private String thumbUrl;
	private String url;
	private String vanity;

	public User(@JsonProperty("created") String created, @JsonProperty("email") String email,
			@JsonProperty("first_name") String firstName, @JsonProperty("full_name") String fullName,
			@JsonProperty("image_url") String imageUrl, @JsonProperty("is_email_verified") boolean isEmailVerified,
			@JsonProperty("last_name") String lastName, @JsonProperty("patron_currency") String patronCurrency,
			@JsonProperty("social_connections") SocialConnections socialConnections,
			@JsonProperty("thumb_url") String thumbUrl, @JsonProperty("url") String url, @JsonProperty("vanity") String vanity) {
		this.created = created;
		this.email = email;
		this.firstName = firstName;
		this.fullName = fullName;
		this.imageUrl = imageUrl;
		this.isEmailVerified = isEmailVerified;
		this.lastName = lastName;
		this.patronCurrency = patronCurrency;
		this.socialConnections = socialConnections;
		this.thumbUrl = thumbUrl;
		this.url = url;
		this.vanity = vanity;
	}

	/**
	 * Returns date and time of this user's account creation.
	 * 
	 * @return date and time of this user's account creation. (UTC ISO format)
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * Returns the user's email address.
	 * 
	 * @return the user's email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the user's first name. Can be null.
	 * 
	 * @return the user's first name or null.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the user's combined first and last name.
	 * 
	 * @return the user's combined first and last name.
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Returns the user's profile picture URL.
	 * 
	 * @return the user's profile picture URL.
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Returns if the user has confirmed their email.
	 * 
	 * @return true if the user's email is confirmed.
	 */
	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	/**
	 * Returns the user's last name. Can be null.
	 * 
	 * @return the user's last name or null.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns the user's patron currency.
	 * 
	 * @return the user's patron currency
	 */
	public String getPatronCurrency() {
		return patronCurrency;
	}

	/**
	 * Returns a mapping from user's connected app names to external user id on the
	 * respective app.
	 * 
	 * @return a mapping from user's connected apps.
	 * @see patreon.java.resources.shared.SocialConnections
	 */
	public SocialConnections getSocialConnections() {
		return socialConnections;
	}

	/**
	 * Returns the user's profile picture URL, scaled to a square of size 100x100px.
	 * 
	 * @return the user's profile picture URL.
	 */
	public String getThumbUrl() {
		return thumbUrl;
	}

	/**
	 * Returns the URL of this user's creator or patron profile.
	 * 
	 * @return the URL of this user's profile.
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Returns the public "username" of the user. patreon.com/ goes to this user's
	 * creator page. Non-creator users might not have a vanity.
	 * 
	 * @return The public "username" of the user.
	 * 
	 * @deprecated Use {@link Campaign#getVanity()} instead.
	 */
	public String getVanity() {
		return vanity;
	}
}
