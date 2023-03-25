package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

@Type("benefit")
public class Benefit extends BaseResource {

	public enum BenefitField implements Field {
		APP_EXTERNAL_ID("app_external_id"), APP_META("app_meta"), BENEFIT_TYPE("benefit_type"),
		CREATED_AT("created_at"), DELIVERABLES_DUE_TODAY_COUNT("deliverables_due_today_count"),
		DELIVERED_DELIVERABLES_COUNT("delivered_deliverables_count"), DESCRIPTION("description"),
		IS_DELETED("is_deleted"), IS_ENDED("is_ended"), IS_PUBLISHED("is_published"),
		NEXT_DELIVERABLE_DUE_DATE("next_deliverable_due_date"),
		NOT_DELIVERED_DELIVERABLES_COUNT("not_delivered_deliverables_count"), RULE_TYPE("rule_type"),
		TIERS_COUNT("tiers_count"), TITLE("title");

		private final String propertyName;

		BenefitField(String propertyName) {
			this.propertyName = propertyName;
		}

		public static Collection<BenefitField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private String appExternalId;
	private Object appMeta;
	private String benefitType;
	private String createdAt;
	private int deliverablesDueTodayCount;
	private int deliveredDeliverablesCount;
	private String description;
	private boolean isDeleted;
	private boolean isEnded;
	private boolean isPublished;
	private String nextDeliverableDueDate;
	private int notDeliveredDeliverablesCount;
	private String ruleType;
	private int tiersCount;
	private String title;

	@Relationship("campaign")
	private Campaign campaign;

	@Relationship("deliverables")
	private Deliverable[] deliverables;

	@Relationship("tiers")
	private Tier[] tiers;

	public Benefit(@JsonProperty("app_external_id") String appExternalId, @JsonProperty("app_meta") Object appMeta,
			@JsonProperty("benefit_type") String benefitType, @JsonProperty("created_at") String createdAt,
			@JsonProperty("deliverables_due_today_count") int deliverablesDueTodayCount,
			@JsonProperty("delivered_deliverables_count") int deliveredDeliverablesCount,
			@JsonProperty("description") String description, @JsonProperty("is_deleted") boolean isDeleted,
			@JsonProperty("is_ended") boolean isEnded, @JsonProperty("is_published") boolean isPublished,
			@JsonProperty("next_deliverable_due_date") String nextDeliverableDueDate,
			@JsonProperty("not_delivered_deliverables_count") int notDeliveredDeliverablesCount,
			@JsonProperty("rule_type") String ruleType, @JsonProperty("tiers_count") int tiersCount,
			@JsonProperty("title") String title, @JsonProperty("campaign") Campaign campaign,
			@JsonProperty("deliverables") Deliverable[] deliverables, @JsonProperty("tiers") Tier[] tiers) {
		this.appExternalId = appExternalId;
		this.appMeta = appMeta;
		this.benefitType = benefitType;
		this.createdAt = createdAt;
		this.deliverablesDueTodayCount = deliverablesDueTodayCount;
		this.deliveredDeliverablesCount = deliveredDeliverablesCount;
		this.description = description;
		this.isDeleted = isDeleted;
		this.isEnded = isEnded;
		this.isPublished = isPublished;
		this.nextDeliverableDueDate = nextDeliverableDueDate;
		this.notDeliveredDeliverablesCount = notDeliveredDeliverablesCount;
		this.ruleType = ruleType;
		this.tiersCount = tiersCount;
		this.title = title;
		this.campaign = campaign;
		this.deliverables = deliverables;
		this.tiers = tiers;
	}

	public String getAppExternalId() {
		return appExternalId;
	}

	public Object getAppMeta() {
		return appMeta;
	}

	public String getBenefitType() {
		return benefitType;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public int getDeliverablesDueTodayCount() {
		return deliverablesDueTodayCount;
	}

	public int getDeliveredDeliverablesCount() {
		return deliveredDeliverablesCount;
	}

	public String getDescription() {
		return description;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public boolean isEnded() {
		return isEnded;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public String getNextDeliverableDueDate() {
		return nextDeliverableDueDate;
	}

	public int getNotDeliveredDeliverablesCount() {
		return notDeliveredDeliverablesCount;
	}

	public String getRuleType() {
		return ruleType;
	}

	public int getTiersCount() {
		return tiersCount;
	}

	public String getTitle() {
		return title;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public Deliverable[] getDeliverables() {
		return deliverables;
	}

	public Tier[] getTiers() {
		return tiers;
	}
}
