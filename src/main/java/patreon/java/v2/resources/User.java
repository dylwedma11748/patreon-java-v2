package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

@Type("user")
public class User extends BaseResource {

	public enum UserField implements Field {
		about("about"), can_see_nsfw("can_see_nsfw"), created("created"), email("email"), first_name("first_name"),
		full_name("full_name"), hide_pledges("hide_pledges"), image_url("image_url"),
		is_email_verified("is_email_verified"), last_name("last_name"), like_count("like_count"),
		social_connections("social_connections"), thumb_url("thumb_url"), url("url"), vanity("vanity");

		private final String propertyName;

		UserField(String propertyName) {
			this.propertyName = propertyName;
		}

		public static Collection<UserField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private String about;
	private boolean can_see_nsfw;
	private String created;
	private String email;
	private String first_name;
	private String full_name;
	private boolean hide_pledges;
	private String image_url;
	private boolean is_email_verified;
	private String last_name;
	private int like_count;
	private Object social_connections;
	private String thumb_url;
	private String url;
	private String vanity;

	@Relationship("campaign")
	private Campaign campaign;

	@Relationship("memberships")
	private Member[] memberships;

	public User(@JsonProperty("about") String about, @JsonProperty("can_see_nsfw") boolean can_see_nsfw,
			@JsonProperty("created") String created, @JsonProperty("email") String email,
			@JsonProperty("first_name") String first_name, @JsonProperty("full_name") String full_name,
			@JsonProperty("hide_pledges") boolean hide_pledges, @JsonProperty("image_url") String image_url,
			@JsonProperty("is_email_verified") boolean is_email_verified, @JsonProperty("last_name") String last_name,
			@JsonProperty("like_count") int like_count, @JsonProperty("social_connections") Object social_connections,
			@JsonProperty("thumb_url") String thumb_url, @JsonProperty("url") String url,
			@JsonProperty("vanity") String vanity, @JsonProperty("campaign") Campaign campaign,
			@JsonProperty("memberships") Member[] memberships) {
		this.about = about;
		this.can_see_nsfw = can_see_nsfw;
		this.created = created;
		this.email = email;
		this.first_name = first_name;
		this.full_name = full_name;
		this.hide_pledges = hide_pledges;
		this.image_url = image_url;
		this.is_email_verified = is_email_verified;
		this.last_name = last_name;
		this.like_count = like_count;
		this.social_connections = social_connections;
		this.thumb_url = thumb_url;
		this.url = url;
		this.vanity = vanity;
		this.campaign = campaign;
		this.memberships = memberships;
	}

	public String getAbout() {
		return about;
	}

	public boolean isCan_see_nsfw() {
		return can_see_nsfw;
	}

	public String getCreated() {
		return created;
	}

	public String getEmail() {
		return email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getFull_name() {
		return full_name;
	}

	public boolean isHide_pledges() {
		return hide_pledges;
	}

	public String getImage_url() {
		return image_url;
	}

	public boolean isIs_email_verified() {
		return is_email_verified;
	}

	public String getLast_name() {
		return last_name;
	}

	public int getLike_count() {
		return like_count;
	}

	public Object getSocial_connections() {
		return social_connections;
	}

	public String getThumb_url() {
		return thumb_url;
	}

	public String getUrl() {
		return url;
	}

	public String getVanity() {
		return vanity;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public Member[] getMemberships() {
		return memberships;
	}
}
