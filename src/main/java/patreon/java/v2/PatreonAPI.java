package patreon.java.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import com.github.jasminb.jsonapi.DeserializationFeature;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;

import patreon.java.v2.resources.Address;
import patreon.java.v2.resources.Address.AddressField;
import patreon.java.v2.resources.Benefit;
import patreon.java.v2.resources.Benefit.BenefitField;
import patreon.java.v2.resources.Campaign;
import patreon.java.v2.resources.Campaign.CampaignField;
import patreon.java.v2.resources.Deliverable;
import patreon.java.v2.resources.Deliverable.DeliverableField;
import patreon.java.v2.resources.Goal;
import patreon.java.v2.resources.Goal.GoalField;
import patreon.java.v2.resources.Media;
import patreon.java.v2.resources.Media.MediaField;
import patreon.java.v2.resources.Member;
import patreon.java.v2.resources.Member.MemberField;
import patreon.java.v2.resources.PledgeEvent.PledgeEventField;
import patreon.java.v2.resources.PledgeEvent;
import patreon.java.v2.resources.Post;
import patreon.java.v2.resources.Post.PostField;
import patreon.java.v2.resources.Tier;
import patreon.java.v2.resources.Tier.TierField;
import patreon.java.v2.resources.User;
import patreon.java.v2.resources.User.UserField;
import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

public class PatreonAPI {

	class RelationshipResolver implements com.github.jasminb.jsonapi.RelationshipResolver {
		private PatreonAPI api;

		public RelationshipResolver(PatreonAPI api) {
			this.api = api;
		}

		@Override
		public byte[] resolve(String relationshipURL) {
			String pathPrefix = relationshipURL.substring(relationshipURL.indexOf("v2/") + 3);

			if (pathPrefix.contains("campaigns/")) {
				String campaignID = pathPrefix.substring(10);

				try (BufferedReader reader = new BufferedReader(
						new InputStreamReader(api.fetchCampaignInputStreamFromID(campaignID)))) {
					return reader.readLine().getBytes();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			return null;
		}

	}

	/**
	 * The base URI for requests to the patreon API. This may be overridden (e.g.
	 * for testing) by passing -Dpatreon.rest.uri="https://my.other.server.com" as
	 * jvm arguments
	 */
	public static final String BASE_URI = System.getProperty("patreon.rest.uri", "https://www.patreon.com");

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

		this.converter = new ResourceConverter(objectMapper, Address.class, Benefit.class, Campaign.class,
				Deliverable.class, Goal.class, Media.class, Member.class, PledgeEvent.class, Post.class, Tier.class,
				User.class);
		this.converter.enableDeserializationOption(DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
		this.converter.setGlobalResolver(new RelationshipResolver(this));
	}

	public JSONAPIDocument<User> fetchUser() throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("identity").addParameter("include", "memberships,campaign");
		addAllFields(pathBuilder);
		return converter.readDocument(getDataStream(pathBuilder.toString()), User.class);
	}

	public JSONAPIDocument<List<Campaign>> fetchCampaigns() throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("campaigns").addParameter("include",
				"tiers,creator,benefits,goals");
		addAllFields(pathBuilder);
		return converter.readDocumentCollection(getDataStream(pathBuilder.toString()), Campaign.class);
	}

	public JSONAPIDocument<Campaign> fetchCampaignFromID(String ID) throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("campaigns/" + ID).addParameter("include",
				"tiers,creator,benefits,goals");
		addAllFields(pathBuilder);
		return converter.readDocument(getDataStream(pathBuilder.toString()), Campaign.class);
	}

	private InputStream fetchCampaignInputStreamFromID(String ID) throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("campaigns/" + ID).addParameter("include",
				"tiers,creator,benefits,goals");
		addAllFields(pathBuilder);
		return getDataStream(pathBuilder.toString());
	}

	public JSONAPIDocument<List<Member>> fetchMembers(String campaignID) throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("campaigns/" + campaignID + "/members")
				.addParameter("include", "address,campaign,currently_entitled_tiers,pledge_history,user");
		addAllFields(pathBuilder);
		return converter.readDocumentCollection(getDataStream(pathBuilder.toString()), Member.class);
	}

	public JSONAPIDocument<Member> fetchMember(String memberID) throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("members/" + memberID).addParameter("include",
				"address,campaign,currently_entitled_tiers,pledge_history,user");
		addAllFields(pathBuilder);
		return converter.readDocument(getDataStream(pathBuilder.toString()), Member.class);
	}

	private InputStream getDataStream(String suffix) throws IOException {
		return this.requestUtil.request(suffix, this.accessToken);
	}

	private URIBuilder addAllFields(URIBuilder builder) {
		addFieldsParam(builder, Address.class, AddressField.getAllFields());
		addFieldsParam(builder, Benefit.class, BenefitField.getAllFields());
		addFieldsParam(builder, Campaign.class, CampaignField.getAllFields());
		addFieldsParam(builder, Deliverable.class, DeliverableField.getAllFields());
		addFieldsParam(builder, Goal.class, GoalField.getAllFields());
		addFieldsParam(builder, Media.class, MediaField.getAllFields());
		addFieldsParam(builder, Member.class, MemberField.getAllFields());
		addFieldsParam(builder, PledgeEvent.class, PledgeEventField.getAllFields());
		addFieldsParam(builder, Post.class, PostField.getAllFields());
		addFieldsParam(builder, Tier.class, TierField.getAllFields());
		addFieldsParam(builder, User.class, UserField.getAllFields());

		return builder;
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
