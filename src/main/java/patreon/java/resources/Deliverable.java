package patreon.java.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.resources.shared.BaseResource;
import patreon.java.resources.shared.Field;

/**
 * The record of whether or not a patron has been delivered the benefit they are
 * owed because of their member tier.
 */
@Type("deliverable")
public class Deliverable extends BaseResource {

	/**
	 * This enum represents the different fields or properties associated with the
	 * Deliverable resource.
	 */
	public enum DeliverableField implements Field {
		/** The date and time the deliverable was completed. */
		completed_at("completed_at"),
		/** The status of the deliverable's delivery. */
		delivery_status("delivery_status"),
		/** The due date and time for the deliverable. */
		due_at("due_at");

		private final String propertyName;

		DeliverableField(String propertyName) {
			this.propertyName = propertyName;
		}

		/**
		 * Returns a collection of all available fields for the Deliverable resource.
		 * 
		 * @return a collection of Deliverable fields
		 */
		public static Collection<DeliverableField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private String completedAt;
	private String deliveryStatus;
	private String dueAt;

	@Relationship("benefit")
	private Benefit benefit;

	@Relationship("campaign")
	private Campaign campaign;

	@Relationship("member")
	private Member member;

	@Relationship("user")
	private User user;

	public Deliverable(@JsonProperty("completed_at") String completedAt,
			@JsonProperty("delivery_status") String deliveryStatus, @JsonProperty("due_at") String dueAt,
			@JsonProperty("benefit") Benefit benefit, @JsonProperty("campaign") Campaign campaign,
			@JsonProperty("member") Member member, @JsonProperty("user") User user) {
		this.completedAt = completedAt;
		this.deliveryStatus = deliveryStatus;
		this.dueAt = dueAt;
		this.benefit = benefit;
		this.campaign = campaign;
		this.member = member;
		this.user = user;
	}

	/**
	 * Returns the date and time of when the creator marked the deliverable as
	 * completed or fulfilled to the patron. Can be null.
	 * 
	 * @return the date and time of completion (UTC ISO format)
	 */
	public String getCompletedAt() {
		return completedAt;
	}

	/**
	 * Returns the status of the delivery.
	 * 
	 * @return one of delivered, not_delivered, or wont_deliver
	 */
	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	/**
	 * Returns the date and time of when the deliverable is due to the patron.
	 * 
	 * @return the due date and time (UTC ISO format)
	 */
	public String getDueAt() {
		return dueAt;
	}

	/**
	 * Returns the Benefit the Deliverables were generated for.
	 * 
	 * @return the Benefit the Deliverables were generated for
	 */
	public Benefit getBenefit() {
		return benefit;
	}

	/**
	 * Returns the Campaign the Deliverables were generated for.
	 * 
	 * @return the Campaign the Deliverables were generated for
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/**
	 * Returns the member who has been granted the deliverable.
	 * 
	 * @return the member who has been granted the deliverable
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * Returns the user who has been granted the deliverable. This user is the same
	 * as the member user.
	 * 
	 * @return the user who has been granted the deliverable
	 */
	public User getUser() {
		return user;
	}
}
