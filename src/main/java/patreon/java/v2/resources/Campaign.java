package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
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

	private String created_at;
	private String creation_name;
	private String discord_server_id;
	private String google_analytics_id;
	private boolean has_rss;
	private boolean has_sent_rss_notify;
	private String image_small_url;
	private String image_url;
	private boolean is_charged_immediately;
	private boolean is_monthly;
	private boolean is_nsfw;
	private String main_video_embed;
	private String main_video_url;
	private String one_liner;
	private int patron_count;
	private String pay_per_name;
	private String pledge_url;
	private String published_at;
	private String rss_artwork_url;
	private String rss_feed_title;
	private boolean show_earnings;
	private String summary;
	private String thanks_embed;
	private String thanks_msg;
	private String thanks_video_url;
	private String url;
	private String vanity;

	@Relationship("benefits")
	private Benefit[] benefits;

	@Relationship("creator")
	private User creator;

	@Relationship("goals")
	private Goal[] goals;

	@Relationship("tiers")
	private Tier[] tiers;

	public Campaign(@JsonProperty("created_at") String created_at, @JsonProperty("creation_name") String creation_name,
			@JsonProperty("discord_server_id") String discord_server_id,
			@JsonProperty("google_analytics_id") String google_analytics_id, @JsonProperty("has_rss") boolean has_rss,
			@JsonProperty("has_sent_rss_notify") boolean has_sent_rss_notify,
			@JsonProperty("image_small_url") String image_small_url, @JsonProperty("image_url") String image_url,
			@JsonProperty("is_charged_immediately") boolean is_charged_immediately,
			@JsonProperty("is_monthly") boolean is_monthly, @JsonProperty("is_nsfw") boolean is_nsfw,
			@JsonProperty("main_video_embed") String main_video_embed,
			@JsonProperty("main_video_url") String main_video_url, @JsonProperty("one_liner") String one_liner,
			@JsonProperty("patron_count") int patron_count, @JsonProperty("pay_per_name") String pay_per_name,
			@JsonProperty("pledge_url") String pledge_url, @JsonProperty("published_at") String published_at,
			@JsonProperty("rss_artwork_url") String rss_artwork_url,
			@JsonProperty("rss_feed_title") String rss_feed_title, @JsonProperty("show_earnings") boolean show_earnings,
			@JsonProperty("summary") String summary, @JsonProperty("thanks_embed") String thanks_embed,
			@JsonProperty("thanks_msg") String thanks_msg, @JsonProperty("thanks_video_url") String thanks_video_url,
			@JsonProperty("url") String url, @JsonProperty("vanity") String vanity,
			@JsonProperty("benefits") Benefit[] benefits, @JsonProperty("creator") User creator,
			@JsonProperty("goals") Goal[] goals, @JsonProperty("tiers") Tier[] tiers) {
		this.created_at = created_at;
		this.creation_name = creation_name;
		this.discord_server_id = discord_server_id;
		this.google_analytics_id = google_analytics_id;
		this.has_rss = has_rss;
		this.has_sent_rss_notify = has_sent_rss_notify;
		this.image_small_url = image_small_url;
		this.image_url = image_url;
		this.is_charged_immediately = is_charged_immediately;
		this.is_monthly = is_monthly;
		this.is_nsfw = is_nsfw;
		this.main_video_embed = main_video_embed;
		this.main_video_url = main_video_url;
		this.one_liner = one_liner;
		this.patron_count = patron_count;
		this.pay_per_name = pay_per_name;
		this.pledge_url = pledge_url;
		this.published_at = published_at;
		this.rss_artwork_url = rss_artwork_url;
		this.rss_feed_title = rss_feed_title;
		this.show_earnings = show_earnings;
		this.summary = summary;
		this.thanks_embed = thanks_embed;
		this.thanks_msg = thanks_msg;
		this.thanks_video_url = thanks_video_url;
		this.url = url;
		this.vanity = vanity;
		this.benefits = benefits;
		this.creator = creator;
		this.goals = goals;
		this.tiers = tiers;
	}

	public String getCreatedAt() {
		return created_at;
	}

	public String getCreationName() {
		return creation_name;
	}

	public String getDiscord_server_id() {
		return discord_server_id;
	}

	public String getGoogle_analytics_id() {
		return google_analytics_id;
	}

	public boolean isHas_rss() {
		return has_rss;
	}

	public boolean isHas_sent_rss_notify() {
		return has_sent_rss_notify;
	}

	public String getImage_small_url() {
		return image_small_url;
	}

	public String getImage_url() {
		return image_url;
	}

	public boolean isIs_charged_immediately() {
		return is_charged_immediately;
	}

	public boolean isIs_monthly() {
		return is_monthly;
	}

	public boolean isIs_nsfw() {
		return is_nsfw;
	}

	public String getMain_video_embed() {
		return main_video_embed;
	}

	public String getMainVideoURL() {
		return main_video_url;
	}

	public String getOneLiner() {
		return one_liner;
	}

	public int getPatronCount() {
		return patron_count;
	}

	public String getPayPerName() {
		return pay_per_name;
	}

	public String getPledgeUrl() {
		return pledge_url;
	}

	public String getPublishedAt() {
		return published_at;
	}

	public String getRssArtworkUrl() {
		return rss_artwork_url;
	}

	public String getRssFeedTitle() {
		return rss_feed_title;
	}

	public boolean isShowEarnings() {
		return show_earnings;
	}

	public String getSummary() {
		return summary;
	}

	public String getThanksEmbed() {
		return thanks_embed;
	}

	public String getThanksMsg() {
		return thanks_msg;
	}

	public String getThanksVideoURL() {
		return thanks_video_url;
	}

	public String getUrl() {
		return url;
	}

	public String getVanity() {
		return vanity;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getCreation_name() {
		return creation_name;
	}

	public String getMain_video_url() {
		return main_video_url;
	}

	public String getOne_liner() {
		return one_liner;
	}

	public int getPatron_count() {
		return patron_count;
	}

	public String getPay_per_name() {
		return pay_per_name;
	}

	public String getPledge_url() {
		return pledge_url;
	}

	public String getPublished_at() {
		return published_at;
	}

	public String getRss_artwork_url() {
		return rss_artwork_url;
	}

	public String getRss_feed_title() {
		return rss_feed_title;
	}

	public boolean isShow_earnings() {
		return show_earnings;
	}

	public String getThanks_embed() {
		return thanks_embed;
	}

	public String getThanks_msg() {
		return thanks_msg;
	}

	public String getThanks_video_url() {
		return thanks_video_url;
	}

	public Benefit[] getBenefits() {
		return benefits;
	}

	public User getCreator() {
		return creator;
	}

	public Goal[] getGoals() {
		return goals;
	}

	public Tier[] getTiers() {
		return tiers;
	}
}
