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
 * A benefit added to the campaign, which can be added to a tier to be delivered
 * to the patron.
 */
@Type("benefit")
public class Benefit extends BaseResource {

	/**
	 * This enum represents the different fields or properties associated with the
	 * Benefit resource.
	 */
	public enum BenefitField implements Field {
		/** The external ID of the benefit in the application. */
		app_external_id("app_external_id"),
		/** The metadata associated with the benefit in the application. */
		app_meta("app_meta"),
		/** The type of the benefit. */
		benefit_type("benefit_type"),
		/** The date and time the benefit was created. */
		created_at("created_at"),
		/** The number of deliverables due today for the benefit. */
		deliverables_due_today_count("deliverables_due_today_count"),
		/** The number of delivered deliverables for the benefit. */
		delivered_deliverables_count("delivered_deliverables_count"),
		/** The description of the benefit. */
		description("description"),
		/** Whether the benefit has been deleted. */
		is_deleted("is_deleted"),
		/** Whether the benefit has ended. */
		is_ended("is_ended"),
		/** Whether the benefit has been published. */
		is_published("is_published"),
		/** The next due date of the deliverable for the benefit. */
		next_deliverable_due_date("next_deliverable_due_date"),
		/** The number of due deliverables for the benefit. */
		not_delivered_deliverables_count("not_delivered_deliverables_count"),
		/** The type of rule for the benefit. */
		rule_type("rule_type"),
		/** The number of tiers for the benefit. */
		tiers_count("tiers_count"),
		/** The title of the benefit. */
		title("title");

		private final String propertyName;

		BenefitField(String propertyName) {
			this.propertyName = propertyName;
		}

		/**
		 * Returns a collection of all available fields for the Benefit resource.
		 * 
		 * @return a collection of Benefit fields
		 */
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
	private List<Deliverable> deliverables;

	@Relationship("tiers")
	private List<Tier> tiers;

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
			@JsonProperty("deliverables") List<Deliverable> deliverables, @JsonProperty("tiers") List<Tier> tiers) {
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

	/**
	 * Returns the third-party external ID this reward is associated with, if any.
	 * Can be null.
	 * 
	 * @return the third-party external ID or null
	 */
	public String getAppExternalId() {
		return appExternalId;
	}

	/**
	 * Returns any metadata the third-party application included with this benefit
	 * on creation. Can be null.
	 * 
	 * @return the metadata created by the external application or null
	 */
	public Object getAppMeta() {
		return appMeta;
	}

	/**
	 * Returns the type of benefit, such as custom for creator-defined benefits. Can
	 * be null.
	 * 
	 * @return the type of benefit or null
	 */
	public String getBenefitType() {
		return benefitType;
	}

	/**
	 * Returns the date and time that this benefit was created.
	 * 
	 * @return the date and time that this benefit was created. (UTC ISO format)
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * Returns the number of deliverables for this benefit that are due today
	 * specifically.
	 * 
	 * @return the number of deliverables due today
	 */
	public int getDeliverablesDueTodayCount() {
		return deliverablesDueTodayCount;
	}

	/**
	 * Returns the number of deliverables for this benefit that have been marked
	 * complete.
	 * 
	 * @return the number of completed deliverables
	 */
	public int getDeliveredDeliverablesCount() {
		return deliveredDeliverablesCount;
	}

	/**
	 * Returns the benefit display description. Can be null.
	 * 
	 * @return the benefit description or null
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns whether or not this benefit has been deleted.
	 * 
	 * @return true if has been deleted or false otherwise
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * Returns whether or not this benefit is no longer available to new patrons.
	 * 
	 * @return true if this benefit is no longer available
	 */
	public boolean isEnded() {
		return isEnded;
	}

	/**
	 * Returns whether or not this benefit is ready to be fulfilled to patrons.
	 * 
	 * @return true if this benefit is published
	 */
	public boolean isPublished() {
		return isPublished;
	}

	/**
	 * Returns the next due date (after end of day) for this benefit. Can be null.
	 * 
	 * @return the next due date or null
	 */
	public String getNextDeliverableDueDate() {
		return nextDeliverableDueDate;
	}

	/**
	 * Returns the number of deliverables for this benefit that are due, for all
	 * dates.
	 * 
	 * @return the number of all due deliverables
	 */
	public int getNotDeliveredDeliverablesCount() {
		return notDeliveredDeliverablesCount;
	}

	/**
	 * Returns a rule type designation, such as eom_monthly or one_time_immediate.
	 * Can be null.
	 * 
	 * @return a rule type designation or null
	 */
	public String getRuleType() {
		return ruleType;
	}

	/**
	 * Returns the number of tiers containing this benefit.
	 * 
	 * @return a rule type designation or null
	 */
	public int getTiersCount() {
		return tiersCount;
	}

	/**
	 * Returns the benefit display title.
	 * 
	 * @return the benefit display title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the campaign the benefit belongs to.
	 * 
	 * @return the campaign the benefit belongs to
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/**
	 * Returns the deliverables that have been generated by the Benefit.
	 * 
	 * @return the deliverables generated by the Benefit
	 */
	public List<Deliverable> getDeliverables() {
		return deliverables;
	}

	/**
	 * Returns the tiers the benefit has been added to.
	 * 
	 * @return the tiers the benefit has been added to
	 */
	public List<Tier> getTiers() {
		return tiers;
	}
}
