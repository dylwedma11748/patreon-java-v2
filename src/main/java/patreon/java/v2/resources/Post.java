package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

@Type("post")
public class Post extends BaseResource {

	public enum PostField implements Field {
		app_id("app_id"), app_status("app_status"), content("content"), embed_data("embed_data"),
		embed_url("embed_url"), is_paid("is_paid"), is_public("is_public"), tiers("tiers"),
		published_at("published_at"), title("title"), url("url");

		private final String propertyName;

		PostField(String propertyName) {
			this.propertyName = propertyName;
		}

		public static Collection<PostField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	public int app_id;
	public String app_status;
	public String content;
	public Object embed_data;
	public String embed_url;
	public boolean is_paid;
	public boolean is_public;
	public Tier[] tiers;
	public String published_at;
	public String title;
	public String url;

	@Relationship("user")
	private User user;

	@Relationship("campaign")
	private Campaign campaign;

	public Post(@JsonProperty("app_id") int app_id, @JsonProperty("app_status") String app_status,
			@JsonProperty("content") String content, @JsonProperty("embed_data") Object embed_data,
			@JsonProperty("embed_url") String embed_url, @JsonProperty("is_paid") boolean is_paid,
			@JsonProperty("is_public") boolean is_public, @JsonProperty("tiers") Tier[] tiers,
			@JsonProperty("published_at") String published_at, @JsonProperty("title") String title,
			@JsonProperty("url") String url, @JsonProperty("user") User user,
			@JsonProperty("campaign") Campaign campaign) {
		this.app_id = app_id;
		this.app_status = app_status;
		this.content = content;
		this.embed_data = embed_data;
		this.embed_url = embed_url;
		this.is_paid = is_paid;
		this.is_public = is_public;
		this.tiers = tiers;
		this.published_at = published_at;
		this.title = title;
		this.url = url;
		this.user = user;
		this.campaign = campaign;
	}
}