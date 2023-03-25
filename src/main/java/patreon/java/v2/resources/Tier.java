package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

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

	private int amount_cents;
	private String created_at;
	private String description;
	private Object discord_role_ids;
	private String edited_at;
	private String image_url;
	private int patron_count;
	private int post_count;
	private boolean published;
	private String published_at;
	private int remaining;
	private boolean requires_shipping;
	private String title;
	private String unpublished_at;
	private String url;
	private int user_limit;

	@Relationship("benefits")
	private Benefit[] benefits;

	@Relationship("campaign")
	private Campaign campaign;

	@Relationship("tier_image")
	private Media tier_image;

	public Tier(@JsonProperty("amount_cents") int amount_cents, @JsonProperty("created_at") String created_at,
			@JsonProperty("description") String description, @JsonProperty("discord_role_ids") Object discord_role_ids,
			@JsonProperty("edited_at") String edited_at, @JsonProperty("image_url") String image_url,
			@JsonProperty("patron_count") int patron_count, @JsonProperty("post_count") int post_count,
			@JsonProperty("published") boolean published, @JsonProperty("published_at") String published_at,
			@JsonProperty("remaining") int remaining, @JsonProperty("requires_shipping") boolean requires_shipping,
			@JsonProperty("title") String title, @JsonProperty("unpublished_at") String unpublished_at,
			@JsonProperty("url") String url, @JsonProperty("user_limit") int user_limit,
			@JsonProperty("benefits") Benefit[] benefits, @JsonProperty("campaign") Campaign campaign,
			@JsonProperty("tier_image") Media tier_image) {
		this.amount_cents = amount_cents;
		this.created_at = created_at;
		this.description = description;
		this.discord_role_ids = discord_role_ids;
		this.edited_at = edited_at;
		this.image_url = image_url;
		this.patron_count = patron_count;
		this.post_count = post_count;
		this.published = published;
		this.published_at = published_at;
		this.remaining = remaining;
		this.requires_shipping = requires_shipping;
		this.title = title;
		this.unpublished_at = unpublished_at;
		this.url = url;
		this.user_limit = user_limit;
		this.benefits = benefits;
		this.campaign = campaign;
		this.tier_image = tier_image;
	}

	public int getAmount_cents() {
		return amount_cents;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getDescription() {
		return description;
	}

	public Object getDiscord_role_ids() {
		return discord_role_ids;
	}

	public String getEdited_at() {
		return edited_at;
	}

	public String getImage_url() {
		return image_url;
	}

	public int getPatron_count() {
		return patron_count;
	}

	public int getPost_count() {
		return post_count;
	}

	public boolean isPublished() {
		return published;
	}

	public String getPublished_at() {
		return published_at;
	}

	public int getRemaining() {
		return remaining;
	}

	public boolean isRequires_shipping() {
		return requires_shipping;
	}

	public String getTitle() {
		return title;
	}

	public String getUnpublished_at() {
		return unpublished_at;
	}

	public String getUrl() {
		return url;
	}

	public int getUser_limit() {
		return user_limit;
	}

	public Benefit[] getBenefits() {
		return benefits;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public Media getTier_image() {
		return tier_image;
	}
}
