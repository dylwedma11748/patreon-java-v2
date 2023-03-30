package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

/**
 * The record of a pledging action taken by the user, or that action's failure.
 */
@Type("pledge-event")
public class PledgeEvent extends BaseResource {

	/**
	 * This enum represents the different fields or properties associated with the
	 * PledgeEvent resource.
	 */
	public enum PledgeEventField implements Field {
	    /**
	     * The amount of the pledge event in cents.
	     */
	    amount_cents("amount_cents"),
	    /**
	     * The currency code for the pledge event.
	     */
	    currency_code("currency_code"),
	    /**
	     * The date and time of the pledge event.
	     */
	    date("date"),
	    /**
	     * The payment status of the pledge event.
	     */
	    payment_status("payment_status"),
	    /**
	     * The ID of the tier associated with the pledge event.
	     */
	    tier_id("tier_id"),
	    /**
	     * The title of the tier associated with the pledge event.
	     */
	    tier_title("tier_title"),
	    /**
	     * The type of the pledge event.
	     */
	    type("type");

	    private final String propertyName;

	    PledgeEventField(String propertyName) {
	        this.propertyName = propertyName;
	    }

	    /**
	     * Returns a collection containing all fields.
	     *
	     * @return a collection containing all fields
	     */
	    public static Collection<PledgeEventField> getAllFields() {
	        return List.of(values());
	    }

	    @Override
	    public String getPropertyName() {
	        return this.propertyName;
	    }
	}

	private int amountCents;
	private String currencyCode;
	private String date;
	private String paymentStatus;
	private String tierId;
	private String tierTitle;
	private String type;

	// Currently a dead end
	@Relationship(value = "campaign", resolve = true)
	private Campaign campaign;

	// Currently a dead end
	@Relationship(value = "patron", resolve = true)
	private User patron;

	// Currently a dead end
	@Relationship(value = "tier", resolve = true)
	private Tier tier;

	public PledgeEvent(@JsonProperty("amount_cents") int amountCents,
			@JsonProperty("currency_code") String currencyCode, @JsonProperty("date") String date,
			@JsonProperty("payment_status") String paymentStatus, @JsonProperty("tier_id") String tierId,
			@JsonProperty("tier_title") String tierTitle, @JsonProperty("type") String type,
			@JsonProperty("campaign") Campaign campaign, @JsonProperty("patron") User patron,
			@JsonProperty("tier") Tier tier) {
		this.amountCents = amountCents;
		this.currencyCode = currencyCode;
		this.date = date;
		this.paymentStatus = paymentStatus;
		this.tierId = tierId;
		this.tierTitle = tierTitle;
		this.type = type;
		this.campaign = campaign;
		this.patron = patron;
		this.tier = tier;
	}

	/**
	 * Returns the amount (in the currency in which the patron paid) of the
	 * underlying event.
	 * 
	 * @return the amount of the underlying event
	 */
	public int getAmountCents() {
		return amountCents;
	}

	/**
	 * Returns the ISO code of the currency of the event.
	 * 
	 * @return the ISO code of the event
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * Returns the date and time that this event occurred.
	 * 
	 * @return the date and time that this event occurred (UTC ISO format)
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Returns the status of the underlying payment.
	 * 
	 * @return one of Paid, Declined, Deleted, Pending, Refunded, Fraud, Other
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * Returns the ID of the tier associated with the pledge. Can be null.
	 * 
	 * @return the tier ID or null
	 */
	public String getTierId() {
		return tierId;
	}

	/**
	 * Returns the ID of the tier associated with the pledge. Can be null.
	 * 
	 * @return the tier ID or null
	 */
	public String getTierTitle() {
		return tierTitle;
	}

	/**
	 * Returns the event type
	 * 
	 * @return one of pledge_start, pledge_upgrade, pledge_downgrade, pledge_delete,
	 *         subscription
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the campaign being pledged to. (Currently a dead end)
	 * 
	 * @return the campaign being pledged to
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/**
	 * Returns the pledging user. (Currently a dead end)
	 * 
	 * @return the pledging user
	 */
	public User getPatron() {
		return patron;
	}

	/**
	 * Returns the tier associated with this pledge event. (Currently a dead end)
	 * 
	 * @return the associated tier
	 */
	public Tier getTier() {
		return tier;
	}
}
