package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

@Type("deliverable")
public class Deliverable extends BaseResource {

	public enum DeliverableField implements Field {
		Completed_At("completed_at"), Delivery_Status("delivery_status"), Due_At("due_at");

		private final String propertyName;

		DeliverableField(String propertyName) {
			this.propertyName = propertyName;
		}

		public static Collection<DeliverableField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private String completed_at;
	private String delivery_status;
	private String due_at;

	@Relationship("benefit")
	private Benefit benefit;

	@Relationship("campaign")
	private Campaign campaign;

	@Relationship("member")
	private Member member;

	@Relationship("user")
	private User user;

	public Deliverable(@JsonProperty("completed_at") String completed_at,
			@JsonProperty("delivery_status") String delivery_status, @JsonProperty("due_at") String due_at,
			@JsonProperty("benefit") Benefit benefit, @JsonProperty("campaign") Campaign campaign,
			@JsonProperty("member") Member member, @JsonProperty("user") User user) {
		this.completed_at = completed_at;
		this.delivery_status = delivery_status;
		this.due_at = due_at;
		this.benefit = benefit;
		this.campaign = campaign;
		this.member = member;
		this.user = user;
	}

	public String getCompleted_at() {
		return completed_at;
	}

	public String getDelivery_status() {
		return delivery_status;
	}

	public String getDue_at() {
		return due_at;
	}

	public Benefit getBenefit() {
		return benefit;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public Member getMember() {
		return member;
	}

	public User getUser() {
		return user;
	}
}
