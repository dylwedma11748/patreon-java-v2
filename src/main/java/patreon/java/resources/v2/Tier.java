package patreon.java.resources.v2;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.resources.shared.BaseResource;
import patreon.java.resources.shared.Field;

/**
 * A membership level on a campaign, which can have benefits attached to it.
 */
@Type("tier")
public class Tier extends BaseResource {

	/**
	 * This enum represents the different fields or properties associated with the
	 * Tier resource.
	 */
	public enum TierField implements Field {
		/** The amount in cents of the tier. */
		amount_cents("amount_cents"),
		/** The date and time the tier was created. */
		created_at("created_at"),
		/** The description of the tier. */
		description("description"),
		/** The Discord role IDs associated with the tier. */
		discord_role_ids("discord_role_ids"),
		/** The date and time the tier was last edited. */
		edited_at("edited_at"),
		/** The URL for the image associated with the tier. */
		image_url("image_url"),
		/** The number of patrons for the tier. */
		patron_count("patron_count"),
		/** The number of posts for the tier. */
		post_count("post_count"),
		/** Whether the tier is published or not. */
		published("published"),
		/** The date and time the tier was published. */
		published_at("published_at"),
		/** The remaining number of patrons required to reach the tier. */
		remaining("remaining"),
		/** Whether the tier requires shipping. */
		requires_shipping("requires_shipping"),
		/** The title of the tier. */
		title("title"),
		/** The date and time the tier was unpublished. */
		unpublished_at("unpublished_at"),
		/** The URL for the tier. */
		url("url"),
		/** The maximum number of patrons allowed for the tier. */
		user_limit("user_limit");

		private final String propertyName;

		TierField(String propertyName) {
			this.propertyName = propertyName;
		}

		/**
		 * Returns a collection of all available fields for the Tier resource.
		 * 
		 * @return a collection of Tier fields
		 */
		public static Collection<TierField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private int amountCents;
	private String createdAt;
	private String description;
	private List<String> discordRoleIDs;
	private String editedAt;
	private String imageUrl;
	private int patronCount;
	private int postCount;
	private boolean published;
	private String publishedAt;
	private int remaining;
	private boolean requiresShipping;
	private String title;
	private String unpublishedAt;
	private String url;
	private int userLimit;

	@Relationship("benefits")
	private List<Benefit> benefits;

	@Relationship("campaign")
	private Campaign campaign;

	@Relationship("tier_image")
	private Media tierImage;

	public Tier(@JsonProperty("amount_cents") int amountCents, @JsonProperty("created_at") String createdAt,
			@JsonProperty("description") String description,
			@JsonProperty("discord_role_ids") List<String> discordRoleIDs, @JsonProperty("edited_at") String editedAt,
			@JsonProperty("image_url") String imageUrl, @JsonProperty("patron_count") int patronCount,
			@JsonProperty("post_count") int postCount, @JsonProperty("published") boolean published,
			@JsonProperty("published_at") String publishedAt, @JsonProperty("remaining") int remaining,
			@JsonProperty("requires_shipping") boolean requiresShipping, @JsonProperty("title") String title,
			@JsonProperty("unpublished_at") String unpublishedAt, @JsonProperty("url") String url,
			@JsonProperty("user_limit") int user_limit, @JsonProperty("benefits") List<Benefit> benefits,
			@JsonProperty("campaign") Campaign campaign, @JsonProperty("tier_image") Media tierImage) {
		this.amountCents = amountCents;
		this.createdAt = createdAt;
		this.description = description;
		this.discordRoleIDs = discordRoleIDs;
		this.editedAt = editedAt;
		this.imageUrl = imageUrl;
		this.patronCount = patronCount;
		this.postCount = postCount;
		this.published = published;
		this.publishedAt = publishedAt;
		this.remaining = remaining;
		this.requiresShipping = requiresShipping;
		this.title = title;
		this.unpublishedAt = unpublishedAt;
		this.url = url;
		this.userLimit = user_limit;
		this.benefits = benefits;
		this.campaign = campaign;
		this.tierImage = tierImage;
	}

	/**
	 * Returns the monetary amount associated with this tier (in US cents).
	 * 
	 * @return the amount associated with this tier
	 */
	public int getAmountCents() {
		return amountCents;
	}

	/**
	 * Returns the date and time that this tier was created.
	 * 
	 * @return the date and time that this tier was created (UTC ISO format)
	 * 
	 * @see Tier#getPublishedAt()
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * Returns the tier display description.
	 * 
	 * @return the tier display description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the Discord role IDs granted by this tier. Can be null.
	 * 
	 * @return the Discord role IDs or null
	 */
	public List<String> getDiscordRoleIDs() {
		return discordRoleIDs;
	}

	/**
	 * Returns the date and time that this tier was edited.
	 * 
	 * @return the date and time that this tier was edited (UTC ISO format)
	 */
	public String getEditedAt() {
		return editedAt;
	}

	/**
	 * Returns the full qualified image URL associated with this tier. Can be null.
	 * 
	 * @return the image URL or null
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Returns the number of patrons currently registered for this tier.
	 * 
	 * @return the number of patrons
	 */
	public int getPatronCount() {
		return patronCount;
	}

	/**
	 * Returns the number of posts published to this tier. Can be null.
	 * 
	 * @return the number of posts or null
	 */
	public int getPostCount() {
		return postCount;
	}

	/**
	 * Returns whether or not the tier is currently published.
	 * 
	 * @return true if the tier is currently published
	 */
	public boolean isPublished() {
		return published;
	}

	/**
	 * Returns the date and time that this tier was last published. Can be null.
	 * 
	 * @return the date and time that this tier was last published or null (UTC ISO
	 *         format)
	 */
	public String getPublishedAt() {
		return publishedAt;
	}

	/**
	 * Returns the remaining number of patrons who may subscribe, if there is a user
	 * limit. Can be null.
	 * 
	 * @return the remaining number of patrons who may subscribe or null
	 * 
	 * @see Tier#getUserLimit()
	 */
	public int getRemaining() {
		return remaining;
	}

	/**
	 * Returns whether or not this tier requires a shipping address from patrons.
	 * 
	 * @return true if this tier requires a shipping address
	 */
	public boolean isRequiresShipping() {
		return requiresShipping;
	}

	/**
	 * Returns the tier display title.
	 * 
	 * @return the tier display title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the date and time that this tier was unpublished, while applicable.
	 * Can be null.
	 * 
	 * @return the date and time that this tier was unpublished or null (UTC ISO
	 *         format)
	 */
	public String getUnpublishedAt() {
		return unpublishedAt;
	}

	/**
	 * Returns the fully qualified URL associated with this tier.
	 * 
	 * @return the URL associated with this tier
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Returns the maximum number of patrons this tier is limited to, if applicable.
	 * Can be null.
	 * 
	 * @return the maximum number of patrons or null
	 */
	public int getUserLimit() {
		return userLimit;
	}

	/**
	 * Returns the benefits attached to the tier, which are used for generating
	 * deliverables. (Currently untested)
	 * 
	 * @return the benefits attached to the tier
	 */
	public List<Benefit> getBenefits() {
		return benefits;
	}

	/**
	 * Returns the campaign the tier belongs to. (Currently untested)
	 * 
	 * @return the campaign the tier belongs to
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/**
	 * Returns the image file associated with the tier. (Currently untested)
	 * 
	 * @return the image file associated with the tier
	 */
	public Media getTierImage() {
		return tierImage;
	}
}
