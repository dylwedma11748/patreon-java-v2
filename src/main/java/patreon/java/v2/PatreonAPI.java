package patreon.java.v2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.github.jasminb.jsonapi.DeserializationFeature;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.SerializationFeature;

import patreon.java.v2.resources.Address;
import patreon.java.v2.resources.Benefit;
import patreon.java.v2.resources.Campaign;
import patreon.java.v2.resources.Campaign.CampaignField;
import patreon.java.v2.resources.Deliverable;
import patreon.java.v2.resources.Goal;
import patreon.java.v2.resources.Media;
import patreon.java.v2.resources.Member;
import patreon.java.v2.resources.Member.MemberField;
import patreon.java.v2.resources.PledgeEvent;
import patreon.java.v2.resources.Post;
import patreon.java.v2.resources.RequestUtil;
import patreon.java.v2.resources.Tier;
import patreon.java.v2.resources.User;
import patreon.java.v2.resources.User.UserField;
import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

public class PatreonAPI {
	
	class Resolver implements com.github.jasminb.jsonapi.RelationshipResolver  {
		@Override
		public byte[] resolve(String relationshipURL) {
			System.out.println(relationshipURL);
			return null;
		}
		
	}
	
	/**
	 * The base URI for requests to the patreon API. This may be overridden (e.g.
	 * for testing) by passing -Dpatreon.rest.uri="https://my.other.server.com" as
	 * jvm arguments
	 */
	public static final String BASE_URI = System.getProperty("patreon.rest.uri", "https://www.patreon.com");

	private static final Logger LOG = LoggerFactory.getLogger(PatreonAPI.class);
	private final String accessToken;
	private final RequestUtil requestUtil;
	private ResourceConverter converter;

	/**
	 * Create a new instance of the Patreon API. You only need <b>one</b> of these
	 * objects unless you are using the API with multiple tokens
	 *
	 * @param accessToken The "Creator's Access Token" found on <a href=
	 *                    "https://www.patreon.com/platform/documentation/clients">the
	 *                    patreon client page</a> <b>OR</b> OAuth access token
	 */
	public PatreonAPI(String accessToken) {
		this(accessToken, new RequestUtil());
	}

	PatreonAPI(String accessToken, RequestUtil requestUtil) {
		this.accessToken = accessToken;
		this.requestUtil = requestUtil;

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.converter = new ResourceConverter(objectMapper, Address.class, Benefit.class, Campaign.class, Deliverable.class, Goal.class, Media.class, Member.class, PledgeEvent.class, Post.class, Tier.class, User.class);
		
		this.converter.enableDeserializationOption(DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
		this.converter.enableSerializationOption(SerializationFeature.INCLUDE_RELATIONSHIP_ATTRIBUTES);
		this.converter.setGlobalResolver(new Resolver());
	}

	public JSONAPIDocument<User> fetchUser() throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("identity").addParameter("include", "memberships,campaign");
		
		Collection<UserField> userFields = User.UserField.getAllFields();
		addFieldsParam(pathBuilder, User.class, userFields);
		
		return converter.readDocument(getDataStream(pathBuilder.toString()), User.class);
	}
	
	public JSONAPIDocument<User> fetchUser(Collection<User.UserField> userFields) throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("identity");
		addFieldsParam(pathBuilder, User.class, userFields);
		
		return converter.readDocument(getDataStream(pathBuilder.toString()), User.class);
	}
	
	public JSONAPIDocument<List<Campaign>> fetchCampaigns() throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("campaigns").addParameter("include", "tiers,creator,benefits,goals");
		Collection<CampaignField> campaignFields = Campaign.CampaignField.getAllFields();
		addFieldsParam(pathBuilder, Campaign.class, campaignFields);
		System.out.println(pathBuilder.toString());
		return converter.readDocumentCollection(getDataStream(pathBuilder.toString()), Campaign.class);
	}
	
	public JSONAPIDocument<List<Member>> fetchMembers(String campaignID) throws IOException  {
		URIBuilder pathBuilder = new URIBuilder().setPath("campaigns/" + campaignID + "/members").addParameter("include", "address,campaign,currently_entitled_tiers,user");
		Collection<MemberField> memberFields = Member.MemberField.getAllFields();
		addFieldsParam(pathBuilder, Member.class, memberFields);
		System.out.println(pathBuilder.toString());
		return converter.readDocumentCollection(getDataStream(pathBuilder.toString()), Member.class);
	}
	
	private InputStream getDataStream(String suffix) throws IOException {
		return this.requestUtil.request(suffix, this.accessToken);
	}
	
	private URIBuilder addFieldsParam(URIBuilder builder, Class<? extends BaseResource> type,
			Collection<? extends Field> fields) {
		List<String> fieldNames = new ArrayList<>();
		
		for (Field f : fields) {
			fieldNames.add(f.getPropertyName());
		}
		
		String typeStr = BaseResource.getType(type);
		builder.addParameter("fields[" + typeStr + "]", String.join(",", fieldNames));

		return builder;
	}
}
