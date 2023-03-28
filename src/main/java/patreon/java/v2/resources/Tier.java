package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

/**
 * A membership level on a campaign, which can have benefits attached to it.
 */
@Type("tier")
public class Tier extends BaseResource {

	public enum TierField implements Field {
		amount_cents("amount_cents"), created_at("created_at"), description("description"),
		discord_role_ids("discord_role_ids"), edited_at("edited_at"), image_url("image_url"),
		patron_count("patron_count"), post_count("post_count"), published("published"), published_at("published_at"),
		remaining("remaining"), requires_shipping("requires_shipping"), title("title"),
		unpublished_at("unpublished_at"), url("url"), user_limit("user_limit");

		private final String propertyName;

		TierField(String propertyName) {
			this.propertyName = propertyName;
		}

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
	public List<Benefit> benefits;

	@Relationship("campaign")
	public Campaign campaign;

	@Relationship("tier_image")
	public Media tierImage;

	public Tier(@JsonProperty("amount_cents") int amountCents, @JsonProperty("created_at") String createdAt,
			@JsonProperty("description") String description, @JsonProperty("discord_role_ids") List<String> discordRoleIDs,
			@JsonProperty("edited_at") String editedAt, @JsonProperty("image_url") String imageUrl,
			@JsonProperty("patron_count") int patronCount, @JsonProperty("post_count") int postCount,
			@JsonProperty("published") boolean published, @JsonProperty("published_at") String publishedAt,
			@JsonProperty("remaining") int remaining, @JsonProperty("requires_shipping") boolean requiresShipping,
			@JsonProperty("title") String title, @JsonProperty("unpublished_at") String unpublishedAt,
			@JsonProperty("url") String url, @JsonProperty("user_limit") int user_limit,
			@JsonProperty("benefits") List<Benefit> benefits, @JsonProperty("campaign") Campaign campaign,
			@JsonProperty("tier_image") Media tierImage) {
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
	 * Returns the monetary amount associated with this tier (in U.S. cents).
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

	public List<String> getDiscordRoleIDs() {
		return discordRoleIDs;
	}

	public String getEditedAt() {
		return editedAt;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public int getPatronCount() {
		return patronCount;
	}

	public int getPostCount() {
		return postCount;
	}

	public boolean isPublished() {
		return published;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public int getRemaining() {
		return remaining;
	}

	public boolean isRequiresShipping() {
		return requiresShipping;
	}

	public String getTitle() {
		return title;
	}

	public String getUnpublishedAt() {
		return unpublishedAt;
	}

	public String getUrl() {
		return url;
	}

	public int getUserLimit() {
		return userLimit;
	}

	public List<Benefit> getBenefits() {
		return benefits;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public Media getTierImage() {
		return tierImage;
	}
}
