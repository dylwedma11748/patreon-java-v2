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

	public int amount_cents;
	public String created_at;
	public String description;
	public Object discord_role_ids;
	public String edited_at;
	public String image_url;
	public int patron_count;
	public int post_count;
	public boolean published;
	public String published_at;
	public int remaining;
	public boolean requires_shipping;
	public String title;
	public String unpublished_at;
	public String url;
	public int user_limit;

	@Relationship("benefits")
	public List<Benefit> benefits;

	@Relationship("campaign")
	public Campaign campaign;

	@Relationship("tier_image")
	public Media tier_image;

	public Tier(@JsonProperty("amount_cents") int amount_cents, @JsonProperty("created_at") String created_at,
			@JsonProperty("description") String description, @JsonProperty("discord_role_ids") Object discord_role_ids,
			@JsonProperty("edited_at") String edited_at, @JsonProperty("image_url") String image_url,
			@JsonProperty("patron_count") int patron_count, @JsonProperty("post_count") int post_count,
			@JsonProperty("published") boolean published, @JsonProperty("published_at") String published_at,
			@JsonProperty("remaining") int remaining, @JsonProperty("requires_shipping") boolean requires_shipping,
			@JsonProperty("title") String title, @JsonProperty("unpublished_at") String unpublished_at,
			@JsonProperty("url") String url, @JsonProperty("user_limit") int user_limit,
			@JsonProperty("benefits") List<Benefit> benefits, @JsonProperty("campaign") Campaign campaign,
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
}
