package patreon.java.resources.v2;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.RelType;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.resources.shared.BaseResource;
import patreon.java.resources.shared.Field;
import patreon.java.resources.shared.SocialConnections;

/**
 * The Patreon user, which can be both patron and creator.
 */
@Type("user")
public class User extends BaseResource {

	/**
	 * This enum represents the different fields or properties associated with the
	 * User resource.
	 */
	public enum UserField implements Field {
		/** The user's "about" information. */
		about("about"),
		/** Whether the user can see NSFW content. */
		can_see_nsfw("can_see_nsfw"),
		/** The date and time the user was created. */
		created("created"),
		/** The user's email address. */
		email("email"),
		/** The user's first name. */
		first_name("first_name"),
		/** The user's full name. */
		full_name("full_name"),
		/** Whether the user has chosen to hide their pledges. */
		hide_pledges("hide_pledges"),
		/** The URL for the user's profile image. */
		image_url("image_url"),
		/** Whether the user's email address has been verified. */
		is_email_verified("is_email_verified"),
		/** The user's last name. */
		last_name("last_name"),
		/** The number of likes the user has received. */
		like_count("like_count"),
		/** The user's social connections. */
		social_connections("social_connections"),
		/** The URL for the user's profile thumbnail image. */
		thumb_url("thumb_url"),
		/** The URL for the user's profile page. */
		url("url"),
		/** The user's vanity name. */
		vanity("vanity");

		private final String propertyName;

		UserField(String propertyName) {
			this.propertyName = propertyName;
		}

		/**
		 * Returns a collection of all available fields for the User resource.
		 * 
		 * @return a collection of User fields
		 */
		public static Collection<UserField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private String about;
	private boolean canSeeNSFW;
	private String created;
	private String email;
	private String firstName;
	private String fullName;
	private boolean hidePledges;
	private String imageURL;
	private boolean isEmailVerified;
	private String lastName;
	private int likeCount;
	private SocialConnections socialConnections;
	private String thumbURL;
	private String url;
	private String vanity;

	@Relationship(value = "campaign", resolve = true, relType = RelType.RELATED)
	private Campaign campaign;

	// Untested
	@Relationship("memberships")
	private List<Member> memberships;

	public User(@JsonProperty("about") String about, @JsonProperty("can_see_nsfw") boolean canSeeNSFW,
			@JsonProperty("created") String created, @JsonProperty("email") String email,
			@JsonProperty("first_name") String firstName, @JsonProperty("full_name") String fullName,
			@JsonProperty("hide_pledges") boolean hidePledges, @JsonProperty("image_url") String imageURL,
			@JsonProperty("is_email_verified") boolean isEmailVerified, @JsonProperty("last_name") String lastName,
			@JsonProperty("like_count") int likeCount,
			@JsonProperty("social_connections") SocialConnections socialConnections,
			@JsonProperty("thumb_url") String thumbURL, @JsonProperty("url") String url,
			@JsonProperty("vanity") String vanity, @JsonProperty("campaign") Campaign campaign,
			@JsonProperty("memberships") List<Member> memberships) {
		this.about = about;
		this.canSeeNSFW = canSeeNSFW;
		this.created = created;
		this.email = email;
		this.firstName = firstName;
		this.fullName = fullName;
		this.hidePledges = hidePledges;
		this.imageURL = imageURL;
		this.isEmailVerified = isEmailVerified;
		this.lastName = lastName;
		this.likeCount = likeCount;
		this.socialConnections = socialConnections;
		this.thumbURL = thumbURL;
		this.url = url;
		this.vanity = vanity;
		this.campaign = campaign;
		this.memberships = memberships;
	}

	/**
	 * Returns the user's about text, which appears on their profile. Can be null.
	 * 
	 * @return the user's about text or null.
	 */
	public String getAbout() {
		return about;
	}

	/**
	 * Returns if this user can view NFSW content. Can be null.
	 * 
	 * @return true if this user can or null.
	 */
	public boolean isCanSeeNSFW() {
		return canSeeNSFW;
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
	 * Returns if the user has chosen to keep private which creators they pledge to.
	 * Can be null.
	 * 
	 * @return true if this user has or null.
	 */
	public boolean isHidePledges() {
		return hidePledges;
	}

	/**
	 * Returns the user's profile picture URL, scaled to width 400px
	 * 
	 * @return the user's profile picture URL.
	 */
	public String getImageURL() {
		return imageURL;
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
	 * Returns how many posts this user has liked.
	 * 
	 * @return the user's last name or null.
	 */
	public int getLikeCount() {
		return likeCount;
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
	public String getThumbURL() {
		return thumbURL;
	}

	/**
	 * Returns the URL of this user's creator or patron profile.
	 * 
	 * @return the URL of this user's profile.
	 */
	public String getURL() {
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

	/**
	 * Returns information about the user’s Campaign.
	 * 
	 * @return the user’s Campaign.
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/**
	 * Returns memberships to ALL campaigns the user is a member of. (Currently
	 * untested)
	 * 
	 * @return memberships to the campaigns the user is a member of.
	 */
	public List<Member> getMemberships() {
		return memberships;
	}
}
