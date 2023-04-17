package patreon.java.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.resources.shared.BaseResource;
import patreon.java.resources.shared.Field;

/**
 * A patron's shipping address.
 */
@Type("address")
public class Address extends BaseResource {

	/**
	 * This enum represents the different fields or properties associated with the
	 * Address resource.
	 */
	public enum AddressField implements Field {
		/** The name of the person or organization receiving the mail. */
		addressee("addressee"),
		/** The name of the city where the address is located. */
		city("city"),
		/** The name of the country where the address is located. */
		country("country"),
		/** The date and time the address was created. */
		created_at("created_at"),
		/** The first line of the address (e.g. street number and name). */
		line_1("line_1"),
		/** The second line of the address (e.g. apartment number or floor). */
		line_2("line_2"),
		/** The phone number associated with the address. */
		phone_number("phone_number"),
		/** The postal code or ZIP code of the address. */
		postal_code("postal_code"),
		/** The name of the state or province where the address is located. */
		state("state");

		private final String propertyName;

		AddressField(String propertyName) {
			this.propertyName = propertyName;
		}

		/**
		 * Returns a collection of all available fields for the Address resource.
		 * 
		 * @return a collection of Address fields
		 */
		public static Collection<AddressField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private String addressee;
	private String city;
	private String country;
	private String createdAt;
	private String line1;
	private String line2;
	private String phoneNumber;
	private String postalCode;
	private String state;

	@Relationship("campaigns")
	private List<Campaign> campaigns;

	@Relationship("user")
	private User user;

	public Address(@JsonProperty("addressee") String addressee, @JsonProperty("city") String city,
			@JsonProperty("country") String country, @JsonProperty("created_at") String createdAt,
			@JsonProperty("line_1") String line1, @JsonProperty("line_2") String line2,
			@JsonProperty("phone_number") String phoneNumber, @JsonProperty("postal_code") String postalCode,
			@JsonProperty("state") String state, @JsonProperty("campaigns") List<Campaign> campaigns,
			@JsonProperty("user") User user) {
		this.addressee = addressee;
		this.city = city;
		this.country = country;
		this.createdAt = createdAt;
		this.line1 = line1;
		this.line2 = line2;
		this.phoneNumber = phoneNumber;
		this.postalCode = postalCode;
		this.state = state;
		this.campaigns = campaigns;
		this.user = user;
	}

	/**
	 * Returns the full recipient name. Can be null.
	 * 
	 * @return the recipient name or null
	 */
	public String getAddressee() {
		return addressee;
	}

	/**
	 * Returns the city.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Returns the country.
	 * 
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Returns the date and time that this address was first created.
	 * 
	 * @return the date and time that this address was first created (UTC ISO
	 *         format)
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * Returns the first line of street address. Can be null.
	 * 
	 * @return the first line of street address or null
	 */
	public String getLine1() {
		return line1;
	}

	/**
	 * Returns the second line of street address. Can be null.
	 * 
	 * @return the second line of street address or null
	 */
	public String getLine2() {
		return line2;
	}

	/**
	 * Returns the telephone number. Specified for non-US addresses. Can be null.
	 * 
	 * @return the telephone number or null
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Returns the second line of street address. Can be null.
	 * 
	 * @return the second line of street address or null
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Returns the postal or ZIP code. Can be null.
	 * 
	 * @return the postal or ZIP code or null
	 */
	public String getState() {
		return state;
	}

	/**
	 * Returns the campaigns that have access to the address.
	 * 
	 * @return the campaigns that have access to the address
	 */
	public List<Campaign> getCampaigns() {
		return campaigns;
	}

	/**
	 * Returns the user this address belongs to.
	 * 
	 * @return the user this address belongs to
	 */
	public User getUser() {
		return user;
	}
}