package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.RelType;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

@Type("campaign")
public class Campaign extends BaseResource {

	public enum CampaignField implements Field {
		Created_At("created_at"), Creation_Name("creation_name"), Discord_Server_ID("discord_server_id"),
		Google_Analytics_ID("google_analytics_id"), Has_RSS("has_rss"), Has_Sent_RSS_Notify("has_sent_rss_notify"),
		Image_Small_Url("image_small_url"), Image_Url("image_url"), Is_Charged_Immediately("is_charged_immediately"),
		Is_Monthly("is_monthly"), Is_NSFW("is_nsfw"), Main_Video_Embed("main_video_embed"),
		Main_Video_URL("main_video_url"), One_Liner("one_liner"), Patron_Count("patron_count"),
		Pay_Per_Name("pay_per_name"), Pledge_Url("pledge_url"), Published_At("published_at"),
		RSS_Artwork_URL("rss_artwork_url"), RSS_Feed_Title("rss_feed_title"), Show_Earnings("show_earnings"),
		Summary("summary"), Thanks_Embed("thanks_embed"), Thanks_Msg("thanks_msg"),
		Thanks_Video_URL("thanks_video_url"), URL("url"), Vanity("vanity");

		private final String propertyName;

		CampaignField(String propertyName) {
			this.propertyName = propertyName;
		}

		public static Collection<CampaignField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private String createdAt;
	private String creationName;
	private String discordServerID;
	private String googleAnalyticsID;
	private boolean hasRSS;
	private boolean hasSentRSSNotify;
	private String imageSmallURL;
	private String imageURL;
	private boolean isChargedImmediately;
	private boolean isMonthly;
	private boolean isNSFW;
	private String mainVideoEmbed;
	private String mainVideoURL;
	private String oneLiner;
	private int patronCount;
	private String payPerName;
	private String pledgeURL;
	private String publishedAt;
	private String rssArtworkURL;
	private String rssFeedTitle;
	private boolean showEarnings;
	private String summary;
	private String thanksEmbed;
	private String thanksMsg;
	private String thanksVideoURL;
	private String url;
	private String vanity;

	@Relationship("benefits")
	private List<Benefit> benefits;

	@Relationship(value = "creator", resolve = true, relType = RelType.RELATED)
	private User creator;

	@Relationship("goals")
	private List<Goal> goals;

	@Relationship("tiers")
	private List<Tier> tiers;

	public Campaign(@JsonProperty("created_at") String createdAt, @JsonProperty("creation_name") String creationName,
			@JsonProperty("discord_server_id") String discordServerID,
			@JsonProperty("google_analytics_id") String googleAnalyticsID, @JsonProperty("has_rss") boolean hasRSS,
			@JsonProperty("has_sent_rss_notify") boolean hasSentRSSNotify,
			@JsonProperty("image_small_url") String imageSmallURL, @JsonProperty("image_url") String imageURL,
			@JsonProperty("is_charged_immediately") boolean isChargedImmediately,
			@JsonProperty("is_monthly") boolean isMonthly, @JsonProperty("is_nsfw") boolean isNSFW,
			@JsonProperty("main_video_embed") String mainVideoEmbed,
			@JsonProperty("main_video_url") String mainVideoURL, @JsonProperty("one_liner") String oneLiner,
			@JsonProperty("patron_count") int patronCount, @JsonProperty("pay_per_name") String payPerName,
			@JsonProperty("pledge_url") String pledgeURL, @JsonProperty("published_at") String publishedAt,
			@JsonProperty("rss_artwork_url") String rssArtworkURL, @JsonProperty("rss_feed_title") String rssFeedTitle,
			@JsonProperty("show_earnings") boolean showEarnings, @JsonProperty("summary") String summary,
			@JsonProperty("thanks_embed") String thanksEmbed, @JsonProperty("thanks_msg") String thanksMsg,
			@JsonProperty("thanks_video_url") String thanksVideoURL, @JsonProperty("url") String url,
			@JsonProperty("vanity") String vanity, @JsonProperty("benefits") List<Benefit> benefits,
			@JsonProperty("creator") User creator, @JsonProperty("goals") List<Goal> goals,
			@JsonProperty("tiers") List<Tier> tiers) {
		this.createdAt = createdAt;
		this.creationName = creationName;
		this.discordServerID = discordServerID;
		this.googleAnalyticsID = googleAnalyticsID;
		this.hasRSS = hasRSS;
		this.hasSentRSSNotify = hasSentRSSNotify;
		this.imageSmallURL = imageSmallURL;
		this.imageURL = imageURL;
		this.isChargedImmediately = isChargedImmediately;
		this.isMonthly = isMonthly;
		this.isNSFW = isNSFW;
		this.mainVideoEmbed = mainVideoEmbed;
		this.mainVideoURL = mainVideoURL;
		this.oneLiner = oneLiner;
		this.patronCount = patronCount;
		this.payPerName = payPerName;
		this.pledgeURL = pledgeURL;
		this.publishedAt = publishedAt;
		this.rssArtworkURL = rssArtworkURL;
		this.rssFeedTitle = rssFeedTitle;
		this.showEarnings = showEarnings;
		this.summary = summary;
		this.thanksEmbed = thanksEmbed;
		this.thanksMsg = thanksMsg;
		this.thanksVideoURL = thanksVideoURL;
		this.url = url;
		this.vanity = vanity;
		this.benefits = benefits;
		this.creator = creator;
		this.goals = goals;
		this.tiers = tiers;
	}

	/**
     * Returns the date and time that the creator first began the campaign creation process.
     * @return     the date and time that the campaign was first created. (UTC ISO format)
     * @see    #Campaign.getPublishedAt()
     */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
     * Returns the type of content the creator is creating, as in "creator is creating ...". Can be null.
     * @return     the type of content the creator is creating
     */
	public String getCreationName() {
		return creationName;
	}

	/**
     * Returns the ID of the external Discord server that is linked to this campaign. Can be null.
     * @return     the Discord server ID or null
     */
	public String getDiscordServerID() {
		return discordServerID;
	}

	/**
     * Returns the ID of the Google Analytics tracker that the creator wants metrics to be sent to. Can be null.
     * @return     the Google Analytics ID or null
     */
	public String getGoogleAnalyticsID() {
		return googleAnalyticsID;
	}

	/**
     * Returns whether this user has opted-in to RSS feeds.
     * @return     true or false
     */
	public boolean hasRSS() {
		return hasRSS;
	}

	/**
     * Returns whether or not the creator has sent a one-time RSS notification email.
     * @return     true or false
     */
	public boolean hasSentRSSNotify() {
		return hasSentRSSNotify;
	}

	/**
     * Returns the URL for the campaign's profile image.
     * @return     the campaign's profile image URL
     */
	public String getImageSmallURL() {
		return imageSmallURL;
	}

	/**
     * Returns the banner image URL for the campaign.
     * @return     the campaign's banner image URL
     */
	public String getImageURL() {
		return imageURL;
	}

	/**
     * Returns whether the campaign charges upfront. Can be null.
     * @return     true if the campaign charges upfront, false otherwise
     */
	public boolean isChargedImmediately() {
		return isChargedImmediately;
	}

	/**
     * Returns whether the campaign charges per month.
     * @return     true if the campaign charges per month, false if the campaign charges per-post
     */
	public boolean isMonthly() {
		return isMonthly;
	}

	/**
     * Returns whether if the creator has marked the campaign as containing NSFW content.
     * @return     true if the creator has marked the campaign as containing NSFW content.
     */
	public boolean isNSFW() {
		return isNSFW;
	}

	/**
     * Can be null. (That's all it says in Patreon's API docs. Blame them, not me.)
     */
	public String getMainVideoEmbed() {
		return mainVideoEmbed;
	}

	/**
     * Can be null. (That's all it says in Patreon's API docs. Blame them, not me.)
     */
	public String getMainVideoURL() {
		return mainVideoURL;
	}

	/**
     * Returns the pithy one-liner for this campaign, displayed on the creator page. Can be null.
     * @return     one-liner for this campaign or null
     */
	public String getOneLiner() {
		return oneLiner;
	}

	/**
     * Returns the number of patrons pledging to this creator.
     * @return     the number of patrons
     */
	public int getPatronCount() {
		return patronCount;
	}

	/**
     * Returns the thing which patrons are paying per, as in "Creator is making $1000 per ...". Can be null.
     * @return     the thing or null
     */
	public String getPayPerName() {
		return payPerName;
	}

	/**
     * Returns the relative (to patreon.com) URL for the pledge checkout flow for this campaign.
     * @return     the relative URL for the pledge checkout flow
     */
	public String getPledgeURL() {
		return pledgeURL;
	}

	/**
     * Returns the date and time that the creator most recently published (made publicly visible) the campaign. Can be null.
     * @return     the most recent date and time or null. (UTC ISO format)
     */
	public String getPublishedAt() {
		return publishedAt;
	}

	/**
     * Returns the URL for the RSS album artwork. Can be null.
     * @return     the URL for the RSS album artwork or null.
     */
	public String getRssArtworkURL() {
		return rssArtworkURL;
	}

	/**
     * Returns the title of the campaign's RSS feed.
     * @return     the title of the campaign's RSS feed.
     */
	public String getRssFeedTitle() {
		return rssFeedTitle;
	}

	/**
     * Returns whether the campaign's total earnings are shown publicly.
     * @return     true if campaign's earnings are public, false otherwise
     */
	public boolean isShowEarnings() {
		return showEarnings;
	}

	/**
     * Returns the creator's summary of their campaign. Can be null.
     * @return     the creator's summary or null
     */
	public String getSummary() {
		return summary;
	}

	/**
     * Can be null. (That's all it says in Patreon's API docs. Blame them, not me.)
     */
	public String getThanksEmbed() {
		return thanksEmbed;
	}

	/**
     * Returns the thank you message shown to patrons after they pledge to this campaign. Can be null.
     * @return     the thank you message or null
     */
	public String getThanksMsg() {
		return thanksMsg;
	}

	/**
     * Returns the URL for the video shown to patrons after they pledge to this campaign. Can be null.
     * @return     the video URL or null
     */
	public String getThanksVideoURL() {
		return thanksVideoURL;
	}

	/**
     * Returns a URL to access this campaign on patreon.com
     * @return     this campaign's URL
     */
	public String getURL() {
		return url;
	}

	/**
     * Returns the campaign's vanity. Can be null.
     * @return     this campaign's vanity or null
     */
	public String getVanity() {
		return vanity;
	}

	/**
     * Returns the campaign's benefits.
     * @return     the campaign's benefits
     */
	public List<Benefit> getBenefits() {
		return benefits;
	}

	/**
     * Returns the campaign owner.
     * @return     the campaign owner
     */
	public User getCreator() {
		return creator;
	}

	/**
     * Returns the campaign's goals.
     * @return     the campaign's goals.
     */
	public List<Goal> getGoals() {
		return goals;
	}

	/**
     * Returns the campaign's tiers.
     * @return     the campaign's tiers.
     */
	public List<Tier> getTiers() {
		return tiers;
	}
}
