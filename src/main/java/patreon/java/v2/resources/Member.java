package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

@Type("member")
public class Member extends BaseResource {

	public enum MemberField implements Field {
		Campaign_Lifetime_Support_Cents("campaign_lifetime_support_cents"),
		Currently_Entitled_Amount_Cents("currently_entitled_amount_cents"), Email("email"), Full_Name("full_name"),
		Is_Follower("is_follower"), Last_Charge_Date("last_charge_date"), Last_Charge_Status("last_charge_status"),
		Lifetime_Support_Cents("lifetime_support_cents"), Next_Charge_Date("next_charge_date"), Note("note"),
		Patron_Status("patron_status"), Pledge_Cadence("pledge_cadence"),
		Pledge_Relationship_Start("pledge_relationship_start"), Will_Pay_Amount_Cents("will_pay_amount_cents");

		private final String propertyName;

		MemberField(String propertyName) {
			this.propertyName = propertyName;
		}

		public static Collection<MemberField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	public int campaign_lifetime_support_cents;
	public int currently_entitled_amount_cents;
	public String email;
	public String full_name;
	public boolean is_follower;
	public String last_charge_date;
	public String last_charge_status;
	public int lifetime_support_cents;
	public String next_charge_date;
	public String note;
	public String patron_status;
	public int pledge_cadence;
	public String pledge_relationship_start;
	public int will_pay_amount_cents;

	@Relationship("address")
	public Address address;

	@Relationship("campaign")
	public Campaign campaign;

	@Relationship("currently_entitled_tiers")
	public List<Tier> currently_entitled_tiers;

	@Relationship("pledge_history")
	public List<PledgeEvent> pledge_history;

	@Relationship("user")
	public User user;

	public Member(@JsonProperty("campaign_lifetime_support_cents") int campaign_lifetime_support_cents,
			@JsonProperty("currently_entitled_amount_cents") int currently_entitled_amount_cents,
			@JsonProperty("email") String email, @JsonProperty("full_name") String full_name,
			@JsonProperty("is_follower") boolean is_follower, @JsonProperty("last_charge_date") String last_charge_date,
			@JsonProperty("last_charge_status") String last_charge_status,
			@JsonProperty("lifetime_support_cents") int lifetime_support_cents,
			@JsonProperty("next_charge_date") String next_charge_date, @JsonProperty("note") String note,
			@JsonProperty("patron_status") String patron_status, @JsonProperty("pledge_cadence") int pledge_cadence,
			@JsonProperty("pledge_relationship_start") String pledge_relationship_start,
			@JsonProperty("will_pay_amount_cents") int will_pay_amount_cents, @JsonProperty("address") Address address,
			@JsonProperty("campaign") Campaign campaign,
			@JsonProperty("currently_entitled_tiers") List<Tier> currently_entitled_tiers,
			@JsonProperty("pledge_history") List<PledgeEvent> pledge_history, @JsonProperty("user") User user) {
		this.campaign_lifetime_support_cents = campaign_lifetime_support_cents;
		this.currently_entitled_amount_cents = currently_entitled_amount_cents;
		this.email = email;
		this.full_name = full_name;
		this.is_follower = is_follower;
		this.last_charge_date = last_charge_date;
		this.last_charge_status = last_charge_status;
		this.lifetime_support_cents = lifetime_support_cents;
		this.next_charge_date = next_charge_date;
		this.note = note;
		this.patron_status = patron_status;
		this.pledge_cadence = pledge_cadence;
		this.pledge_relationship_start = pledge_relationship_start;
		this.will_pay_amount_cents = will_pay_amount_cents;
		this.address = address;
		this.campaign = campaign;
		this.currently_entitled_tiers = currently_entitled_tiers;
		this.pledge_history = pledge_history;
		this.user = user;
	}
}
