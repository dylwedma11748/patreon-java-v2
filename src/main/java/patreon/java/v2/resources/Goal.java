package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

@Type("goal")
public class Goal extends BaseResource {

	public enum GoalField implements Field {
		amount_cents("amount_cents"), completed_percentage("completed_percentage"), created_at("created_at"),
		description("description"), reached_at("reached_at"), title("title");

		private final String propertyName;

		GoalField(String propertyName) {
			this.propertyName = propertyName;
		}

		public static Collection<GoalField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private int amount_cents;
	private int completed_percentage;
	private String created_at;
	private String description;
	private String reached_at;
	private String title;

	@Relationship("campaign")
	private Campaign campaign;

	public Goal(@JsonProperty("amount_cents") int amount_cents,
			@JsonProperty("completed_percentage") int completed_percentage,
			@JsonProperty("created_at") String created_at, @JsonProperty("description") String description,
			@JsonProperty("reached_at") String reached_at, @JsonProperty("title") String title,
			@JsonProperty("campaign") Campaign campaign) {
		this.amount_cents = amount_cents;
		this.completed_percentage = completed_percentage;
		this.created_at = created_at;
		this.description = description;
		this.reached_at = reached_at;
		this.title = title;
		this.campaign = campaign;
	}

	public int getAmount_cents() {
		return amount_cents;
	}

	public int getCompleted_percentage() {
		return completed_percentage;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getDescription() {
		return description;
	}

	public String getReached_at() {
		return reached_at;
	}

	public String getTitle() {
		return title;
	}

	public Campaign getCampaign() {
		return campaign;
	}
}
