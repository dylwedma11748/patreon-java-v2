package patreon.java.resources.v2;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.resources.shared.BaseResource;
import patreon.java.resources.shared.Field;

@Type("address")
public class Address extends BaseResource {
	
	public enum AddressField implements Field {
		addressee("addressee"), city("city"), country("country"), created_at("created_at"), line_1("line_1"),
		line_2("line_2"), phone_number("phone_number"), postal_code("postal_code"), state("state");

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

	public String addressee;
	public String city;
	public String country;
	public String created_at;
	public String line_1;
    public String line_2;
    public String phone_number;
    public String postal_code;
    public String state;
    
    @Relationship(value = "campaigns", resolve = true)
    private List<Campaign> campaigns;
    
    @Relationship(value = "user", resolve = true)
    private User user;

    public Address(@JsonProperty("addressee") String addressee, @JsonProperty("city") String city,
                   @JsonProperty("country") String country, @JsonProperty("created_at") String created_at,
                   @JsonProperty("line_1") String line_1, @JsonProperty("line_2") String line_2,
                   @JsonProperty("phone_number") String phone_number, @JsonProperty("postal_code") String postal_code,
                   @JsonProperty("state") String state, @JsonProperty("campaigns") List<Campaign> campaigns, @JsonProperty("user") User user) {
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
}