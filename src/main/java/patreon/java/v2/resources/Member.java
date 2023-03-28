package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.RelType;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

/**
 * The record of a user's membership to a campaign. Remains consistent across months of pledging.
 */
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

	private int campaignLifetimeSupportCents;
	private int currentlyEntitledAmountCents;
	private String email;
	private String fullName;
	private boolean isFollower;
	private String lastChargeDate;
	private String lastChargeStatus;
	private int lifetimeSupportCents;
	private String nextChargeDate;
	private String note;
	private String patronStatus;
	private int pledgeCadence;
	private String pledgeRelationshipStart;
	private int willPayAmountCents;

	@Relationship("address")
	public Address address;

	@Relationship(value = "campaign", resolve = true, relType = RelType.RELATED)
	public Campaign campaign;

	@Relationship(value = "currently_entitled_tiers", resolve = true)
	public List<Tier> currentlyEntitledTiers;

	@Relationship(value = "pledge_history", resolve = true)
	public List<PledgeEvent> pledgeHistory;

	// Related link for user returns a classic 404, not sure what to do here yet.

	// @Relationship(value = "user", resolve = true, relType = RelType.RELATED)
	// public User user;

	// @JsonProperty("creator") User user, <- For the constructor

	public Member(@JsonProperty("campaign_lifetime_support_cents") int campaignLifetimeSupportCents,
			@JsonProperty("currently_entitled_amount_cents") int currentlyEntitledAmountCents,
			@JsonProperty("email") String email, @JsonProperty("full_name") String fullName,
			@JsonProperty("is_follower") boolean isFollower, @JsonProperty("last_charge_date") String lastChargeDate,
			@JsonProperty("last_charge_status") String lastChargeStatus,
			@JsonProperty("lifetime_support_cents") int lifetimeSupportCents,
			@JsonProperty("next_charge_date") String nextChargeDate, @JsonProperty("note") String note,
			@JsonProperty("patron_status") String patronStatus, @JsonProperty("pledge_cadence") int pledgeCadence,
			@JsonProperty("pledge_relationship_start") String pledgeRelationshipStart,
			@JsonProperty("will_pay_amount_cents") int willPayAmountCents, @JsonProperty("address") Address address,
			@JsonProperty("campaign") Campaign campaign,
			@JsonProperty("currently_entitled_tiers") List<Tier> currentlyEntitledTiers,
			@JsonProperty("pledge_history") List<PledgeEvent> pledgeHistory) {
		this.campaignLifetimeSupportCents = campaignLifetimeSupportCents;
		this.currentlyEntitledAmountCents = currentlyEntitledAmountCents;
		this.email = email;
		this.fullName = fullName;
		this.isFollower = isFollower;
		this.lastChargeDate = lastChargeDate;
		this.lastChargeStatus = lastChargeStatus;
		this.lifetimeSupportCents = lifetimeSupportCents;
		this.nextChargeDate = nextChargeDate;
		this.note = note;
		this.patronStatus = patronStatus;
		this.pledgeCadence = pledgeCadence;
		this.pledgeRelationshipStart = pledgeRelationshipStart;
		this.willPayAmountCents = willPayAmountCents;
		this.address = address;
		this.campaign = campaign;
		this.currentlyEntitledTiers = currentlyEntitledTiers;
		this.pledgeHistory = pledgeHistory;
	}

	/**
	 * Returns the total amount that the member has ever paid to the campaign in
	 * campaign's currency. 0 if never paid.
	 * 
	 * @return the total amount or 0
	 */
	public int getCampaignLifetimeSupportCents() {
		return campaignLifetimeSupportCents;
	}

	/**
	 * Returns the amount in cents that the member is entitled to. This includes a
	 * current pledge, or payment that covers the current payment period.
	 * 
	 * @return the current pledge amount
	 */
	public int getCurrentlyEntitledAmountCents() {
		return currentlyEntitledAmountCents;
	}

	/**
	 * Returns the member's email address.
	 * 
	 * @return the member's email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the full name of the member user.
	 * 
	 * @return the full name of the member user
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Returns whether or not the user is not a pledging patron but has subscribed
	 * to updates about public posts.
	 * 
	 * @return true if non-pledging follower
	 */
	public boolean isFollower() {
		return isFollower;
	}

	/**
	 * Returns the date and time of last attempted charge. Can be null if never
	 * charged.
	 * 
	 * @return the date and time of last attempted charge (UTC ISO format) or null
	 */
	public String getLastChargeDate() {
		return lastChargeDate;
	}

	/**
	 * Returns the result of the last attempted charge. The only successful status
	 * is Paid. Can be null if never charged.
	 * 
	 * @return one of Paid, Declined, Deleted, Pending, Refunded, Fraud, Other.
	 */
	public String getLastChargeStatus() {
		return lastChargeStatus;
	}

	/**
	 * Returns the total amount that the member has ever paid to the campaign. 0 if
	 * never paid.
	 * 
	 * @return the total amount or 0
	 */
	public int getLifetimeSupportCents() {
		return lifetimeSupportCents;
	}

	/**
	 * Returns the date and time of next charge. Can be null if annual pledge
	 * downgrade.
	 * 
	 * @return the date and time of next charge (UTC ISO format) or null
	 */
	public String getNextChargeDate() {
		return nextChargeDate;
	}

	/**
	 * Returns the creator's notes on the member.
	 * 
	 * @return the date and time of next charge (UTC ISO format) or null
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Returns the member's current patron status
	 * 
	 * @return one of active_patron, declined_patron, former_patron
	 */
	public String getPatronStatus() {
		return patronStatus;
	}

	/**
	 * Returns the number of months between charges.
	 * 
	 * @return number of months between charges
	 */
	public int getPledgeCadence() {
		return pledgeCadence;
	}

	/**
	 * Returns the date and time of the beginning of the most recent pledge chain
	 * from this member to the campaign. Pledge updates do not change this value.
	 * Can be null.
	 * 
	 * @return the date and time of most recent pledge chain (UTC ISO format) or
	 *         null
	 */
	public String getPledgeRelationshipStart() {
		return pledgeRelationshipStart;
	}

	/**
	 * Returns the amount in cents the user will pay at the next pay cycle.
	 * 
	 * @return the amount in cents the user will pay
	 */
	public int getWillPayAmountCents() {
		return willPayAmountCents;
	}

	/**
	 * Returns the member's shipping address that they entered for the campaign.
	 * (Currently untested)
	 * 
	 * @return the member's shipping address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Returns The campaign that the membership is for.
	 * 
	 * @return the campaign that the membership is for
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/**
	 * Returns the tiers that the member is entitled to. This includes a current
	 * pledge, or payment that covers the current payment period.
	 * 
	 * @return the tiers that the member is entitled to
	 */
	public List<Tier> getCurrentlyEntitledTiers() {
		return currentlyEntitledTiers;
	}

	/**
	 * Returns the pledge history of the member.
	 * 
	 * @return the pledge history of the member
	 */
	public List<PledgeEvent> getPledgeHistory() {
		return pledgeHistory;
	}

//	/**
//	 * Returns the user who is pledging to the campaign.
//	 * 
//	 * @return the user who is pledging to the campaign
//	 */
//	public User getUser() {
//		return user;
//	}
}
