package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

@Type("pledge_event")
public class PledgeEvent extends BaseResource {

	public enum PledgeEventField implements Field {
		amount_cents("amount_cents"), currency_code("currency_code"), date("date"), payment_status("payment_status"),
		tier_id("tier_id"), tier_title("tier_title"), type("type");

		private final String propertyName;

		PledgeEventField(String propertyName) {
			this.propertyName = propertyName;
		}

		public static Collection<PledgeEventField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	public int amount_cents;
	public String currency_code;
	public String date;
	public String payment_status;
	public String tier_id;
	public String tier_title;
	public String type;

	@Relationship("campaign")
	private Campaign campaign;

	@Relationship("patron")
	private User patron;

	@Relationship("tier")
	private Tier tier;

	public PledgeEvent(@JsonProperty("amount_cents") int amount_cents,
			@JsonProperty("currency_code") String currency_code, @JsonProperty("date") String date,
			@JsonProperty("payment_status") String payment_status, @JsonProperty("tier_id") String tier_id,
			@JsonProperty("tier_title") String tier_title, @JsonProperty("type") String type,
			@JsonProperty("campaign") Campaign campaign, @JsonProperty("patron") User patron,
			@JsonProperty("tier") Tier tier) {
		this.amount_cents = amount_cents;
		this.currency_code = currency_code;
		this.date = date;
		this.payment_status = payment_status;
		this.tier_id = tier_id;
		this.tier_title = tier_title;
		this.type = type;
		this.campaign = campaign;
		this.patron = patron;
		this.tier = tier;
	}
}
