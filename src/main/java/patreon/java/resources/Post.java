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

package patreon.java.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.resources.shared.BaseResource;
import patreon.java.resources.shared.Field;

/**
 * Content posted by a creator on a campaign page.
 */
@Type("post")
public class Post extends BaseResource {

	/**
	 * This enum represents the different fields or properties associated with the
	 * Post resource.
	 */
	public enum PostField implements Field {
		/** The ID of the application associated with the post. */
		app_id("app_id"),
		/** The status of the post in the associated app. */
		app_status("app_status"),
		/** The content of the post. */
		content("content"),
		/** Additional data about any embedded content in the post. */
		embed_data("embed_data"),
		/** The URL of any embedded content in the post. */
		embed_url("embed_url"),
		/** Whether the post is behind a paywall. */
		is_paid("is_paid"),
		/** Whether the post is public or private. */
		is_public("is_public"),
		/** The tiers associated with the post. */
		tiers("tiers"),
		/** The date and time the post was published. */
		published_at("published_at"),
		/** The title of the post. */
		title("title"),
		/** The URL of the post. */
		url("url");

		private final String propertyName;

		PostField(String propertyName) {
			this.propertyName = propertyName;
		}

		/**
		 * Returns a collection of all available fields for the Post resource.
		 * 
		 * @return a collection of post fields
		 */
		public static Collection<PostField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private int appId;
	private String appStatus;
	private String content;
	private Object embedData;
	private String embedUrl;
	private boolean isPaid;
	private boolean isPublic;
	private List<String> tiers;
	private String publishedAt;
	private String title;
	private String url;

	@Relationship("user")
	private User user;

	@Relationship("campaign")
	private Campaign campaign;

	public Post(@JsonProperty("app_id") int appId, @JsonProperty("app_status") String appStatus,
			@JsonProperty("content") String content, @JsonProperty("embed_data") Object embedData,
			@JsonProperty("embed_url") String embedUrl, @JsonProperty("is_paid") boolean isPaid,
			@JsonProperty("is_public") boolean isPublic, @JsonProperty("tiers") List<String> tiers,
			@JsonProperty("published_at") String publishedAt, @JsonProperty("title") String title,
			@JsonProperty("url") String url, @JsonProperty("user") User user,
			@JsonProperty("campaign") Campaign campaign) {
		this.appId = appId;
		this.appStatus = appStatus;
		this.content = content;
		this.embedData = embedData;
		this.embedUrl = embedUrl;
		this.isPaid = isPaid;
		this.isPublic = isPublic;
		this.tiers = tiers;
		this.publishedAt = publishedAt;
		this.title = title;
		this.url = url;
		this.user = user;
		this.campaign = campaign;
	}

	/**
	 * Returns the platform application id. Can be null.
	 * 
	 * @return the platform application id or null
	 */
	public int getAppId() {
		return appId;
	}

	/**
	 * Returns the processing status of the post. Can be null.
	 * 
	 * @return the processing status or null
	 */
	public String getAppStatus() {
		return appStatus;
	}

	/**
	 * Returns the content of the post. Can be null.
	 * 
	 * @return the content of the post or null
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Returns an object containing embed data if media is embedded in the post,
	 * null if there is no embed.
	 * 
	 * @return an object containing embed data or null
	 */
	public Object getEmbedData() {
		return embedData;
	}

	/**
	 * Returns the embed media URL Can be null.
	 * 
	 * @return an object containing embed data or null
	 */
	public String getEmbedURL() {
		return embedUrl;
	}

	/**
	 * Returns whether or not the post incurs a bill as part of a pay-per-post
	 * campaign. Can be null.
	 * 
	 * @return true if the post is a paid post, false otherwise
	 */
	public boolean isPaid() {
		return isPaid;
	}

	/**
	 * Returns whether or not the post is viewable by anyone. Can be null.
	 * 
	 * @return true if the post is viewable by anyone, false if only patrons (or a
	 *         subset of patrons) can view
	 */
	public boolean isPublic() {
		return isPublic;
	}

	/**
	 * Returns the tier IDs that allow the patrons from those tiers to view the
	 * post. Empty array if no tiers assigned even if {@link #isPaid()} is true. Can
	 * be null.
	 * 
	 * @return the tier IDs to view the post or null
	 */
	public List<String> getTiers() {
		return tiers;
	}

	/**
	 * Returns the date and time that the creator most recently published (made
	 * publicly visible) the post. Can be null.
	 * 
	 * @return the date and time that the creator published the post (UTC ISO
	 *         format) or null
	 */
	public String getPublishedAt() {
		return publishedAt;
	}

	/**
	 * Returns the title the post. Can be null.
	 * 
	 * @return the title the post or null
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the URL to access this post on Patreon.
	 * 
	 * @return the post URL
	 */
	public String getURL() {
		return url;
	}

	/**
	 * Returns the author of the post.
	 * 
	 * @return the author of the post
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Returns the campaign that the membership is for.
	 * 
	 * @return the campaign that the membership is for
	 */
	public Campaign getCampaign() {
		return campaign;
	}
}
