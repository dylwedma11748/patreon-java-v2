package patreon.java.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.resources.shared.BaseResource;
import patreon.java.resources.shared.Field;

/**
 * A funding goal in USD set by a creator on a campaign (Earnings-based goal).
 * Community-based goals are not included.
 */
@Type("goal")
public class Goal extends BaseResource {

	/**
	 * This enum represents the different fields or properties associated with the
	 * Goal resource.
	 */
	public enum GoalField implements Field {
		/** The amount of money needed to achieve the goal, in cents. */
		amount_cents("amount_cents"),
		/** The percentage of completion for the goal. */
		completed_percentage("completed_percentage"),
		/** The date and time the goal was created. */
		created_at("created_at"),
		/** The goal description. */
		description("description"),
		/** The date and time the goal was reached. */
		reached_at("reached_at"),
		/** The title of the goal. */
		title("title");

		private final String propertyName;

		GoalField(String propertyName) {
			this.propertyName = propertyName;
		}

		/**
		 * Returns a collection of all available fields for the Goal resource.
		 * 
		 * @return a collection of Goal fields
		 */
		public static Collection<GoalField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private int amountCents;
	private int completedPercentage;
	private String createdAt;
	private String description;
	private String reachedAt;
	private String title;

	@Relationship("campaign")
	private Campaign campaign;

	public Goal(@JsonProperty("amount_cents") int amountCents,
			@JsonProperty("completed_percentage") int completedPercentage, @JsonProperty("created_at") String createdAt,
			@JsonProperty("description") String description, @JsonProperty("reached_at") String reachedAt,
			@JsonProperty("title") String title, @JsonProperty("campaign") Campaign campaign) {
		this.amountCents = amountCents;
		this.completedPercentage = completedPercentage;
		this.createdAt = createdAt;
		this.description = description;
		this.reachedAt = reachedAt;
		this.title = title;
		this.campaign = campaign;
	}

	/**
	 * Returns the goal amount in USD cents.
	 * 
	 * @return the goal amount in cents
	 */
	public int getAmountCents() {
		return amountCents;
	}

	/**
	 * Returns the completion percentage of this goal. Equal to (pledge_sum/goal
	 * amount) * 100, helpful when a creator.
	 * 
	 * @return the goal amount in cents
	 */
	public int getCompletedPercentage() {
		return completedPercentage;
	}

	/**
	 * Returns the goal description. Can be null.
	 * 
	 * @return the goal description or null
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * Returns the date and time that this goal was created for the campaign.
	 * 
	 * @return the date and time that this goal was created (UTC ISO format)
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the date and time that the campaign reached the goal. Can be null.
	 * 
	 * @return the date and time that the campaign reached the goal (UTC ISO format)
	 *         or null
	 */
	public String getReachedAt() {
		return reachedAt;
	}

	/**
	 * Returns the goal title.
	 * 
	 * @return the goal title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the campaign trying to reach the goal.
	 * 
	 * @return the campaign trying to reach the goal
	 */
	public Campaign getCampaign() {
		return campaign;
	}
}
