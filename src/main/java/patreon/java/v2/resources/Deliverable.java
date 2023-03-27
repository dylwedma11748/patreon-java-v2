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

	public String completed_at;
	public String delivery_status;
	public String due_at;

	@Relationship("benefit")
	public Benefit benefit;

	@Relationship("campaign")
	public Campaign campaign;

	@Relationship("member")
	public Member member;

	@Relationship("user")
	public User user;

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
}
