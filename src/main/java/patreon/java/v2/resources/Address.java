package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

@Type("address")
public class Address extends BaseResource {

	public enum AddressField implements Field {
		Addressee("addressee"), City("city"), Country("country"), Created_At("created_at"), Line_1("line_1"),
		Line_2("line_2"), Phone_Number("phone_number"), Postal_Code("postal_code"), State("state");

		private final String propertyName;

		AddressField(String propertyName) {
			this.propertyName = propertyName;
		}
		
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
    private String created_at;
    private String line_1;
    private String line_2;
    private String phone_number;
    private String postal_code;
    private String state;
    
    @Relationship("campaigns")
    private Campaign[] campaigns;
    
    @Relationship("user")
    private User user;

    public Address(@JsonProperty("addressee") String addressee, @JsonProperty("city") String city,
                   @JsonProperty("country") String country, @JsonProperty("created_at") String created_at,
                   @JsonProperty("line_1") String line_1, @JsonProperty("line_2") String line_2,
                   @JsonProperty("phone_number") String phone_number, @JsonProperty("postal_code") String postal_code,
                   @JsonProperty("state") String state, @JsonProperty("campaigns") Campaign[] campaigns, @JsonProperty("user") User user) {
        this.addressee = addressee;
        this.city = city;
        this.country = country;
        this.created_at = created_at;
        this.line_1 = line_1;
        this.line_2 = line_2;
        this.phone_number = phone_number;
        this.postal_code = postal_code;
        this.state = state;
        this.campaigns = campaigns;
        this.user = user;
    }

	public String getAddressee() {
		return addressee;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getLine_1() {
		return line_1;
	}

	public String getLine_2() {
		return line_2;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public String getState() {
		return state;
	}

	public Campaign[] getCampaigns() {
		return campaigns;
	}

	public User getUser() {
		return user;
	}
}