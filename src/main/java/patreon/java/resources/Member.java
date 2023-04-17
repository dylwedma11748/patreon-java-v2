package patreon.java.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.resources.shared.BaseResource;
import patreon.java.resources.shared.Field;

/**
 * The record of a user's membership to a campaign. Remains consistent across
 * months of pledging.
 */
@Type("member")
public class Member extends BaseResource {

	/**
	 * This enum represents the different fields or properties associated with the
	 * Member resource.
	 */
	public enum MemberField implements Field {
		/**
		 * The total amount of lifetime support in cents that the member has given to
		 * the creator.
		 */
		campaign_lifetime_support_cents("campaign_lifetime_support_cents"),
		/**
		 * The amount in cents that the member is entitled to pledge to the creator's
		 * campaign.
		 */
		currently_entitled_amount_cents("currently_entitled_amount_cents"),
		/** The email address associated with the member's Patreon account. */
		email("email"),
		/** The full name associated with the member's Patreon account. */
		full_name("full_name"),
		/** Whether the member is following the creator. */
		is_follower("is_follower"),
		/** The date of the last charge for the member's pledge. */
		last_charge_date("last_charge_date"),
		/** The status of the last charge for the member's pledge. */
		last_charge_status("last_charge_status"),
		/** The total amount that the member has ever paid to the campaign. */
		lifetime_support_cents("lifetime_support_cents"),
		/** The date of the next expected charge for the member's pledge. */
		next_charge_date("next_charge_date"),
		/** A note associated with the member's pledge. */
		note("note"),
		/** The status of the member's Patreon pledge. */
		patron_status("patron_status"),
		/** The frequency of the member's pledge payments. */
		pledge_cadence("pledge_cadence"),
		/** The start date of the member's pledge. */
		pledge_relationship_start("pledge_relationship_start"),
		/** The amount in cents that the member has agreed to pay for their pledge. */
		will_pay_amount_cents("will_pay_amount_cents");

		private final String propertyName;

		MemberField(String propertyName) {
			this.propertyName = propertyName;
		}

		/**
		 * Returns a collection of all available fields for the Member resource.
		 * 
		 * @return a collection of Member fields
		 */
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
	private Address address;

	@Relationship("campaign")
	private Campaign campaign;

	@Relationship("currently_entitled_tiers")
	private List<Tier> currentlyEntitledTiers;

	@Relationship("pledge_history")
	private List<PledgeEvent> pledgeHistory;

	@Relationship("user")
	public User user;

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
			@JsonProperty("pledge_history") List<PledgeEvent> pledgeHistory, @JsonProperty("user") User user) {
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
		this.user = user;
	}

	/**
	 * Returns the total amount that the member has ever paid to the campaign in
	 * campaign's currency. Zero if never paid.
	 * 
	 * @return the total amount or zero
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
	 * @return one of Paid, Declined, Deleted, Pending, Refunded, Fraud, Other
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
	 * @return creator's notes on the member
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

	/**
	 * Returns the user who is pledging to the campaign.
	 * 
	 * @return the user who is pledging to the campaign
	 */
	public User getUser() {
		return user;
	}
}
