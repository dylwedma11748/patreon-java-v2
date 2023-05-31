/*

Patreon-Java-V2 - A Java library for Patreon's APIv2 endpoints

Copyright (C) 2023 Dylan Wedman

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License along
with this program; if not, write to the Free Software Foundation, Inc.,
51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

 */

package patreon.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import com.github.jasminb.jsonapi.DeserializationFeature;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;

import patreon.java.resources.Address;
import patreon.java.resources.Benefit;
import patreon.java.resources.Campaign;
import patreon.java.resources.Deliverable;
import patreon.java.resources.Goal;
import patreon.java.resources.Media;
import patreon.java.resources.Member;
import patreon.java.resources.PledgeEvent;
import patreon.java.resources.Post;
import patreon.java.resources.Tier;
import patreon.java.resources.User;
import patreon.java.resources.Address.AddressField;
import patreon.java.resources.Benefit.BenefitField;
import patreon.java.resources.Campaign.CampaignField;
import patreon.java.resources.Deliverable.DeliverableField;
import patreon.java.resources.Goal.GoalField;
import patreon.java.resources.Media.MediaField;
import patreon.java.resources.Member.MemberField;
import patreon.java.resources.PledgeEvent.PledgeEventField;
import patreon.java.resources.Post.PostField;
import patreon.java.resources.Tier.TierField;
import patreon.java.resources.User.UserField;
import patreon.java.resources.shared.BaseResource;
import patreon.java.resources.shared.Field;

/**
 * An instance of the Patreon API. You only need <b>one</b> of these.
 */
public class PatreonAPI {

	/**
	 * The base URI for requests to the Patreon API. This may be overridden (e.g.
	 * for testing) by passing -Dpatreon.rest.uri="https://my.other.server.com" as
	 * JVM arguments. The default URL is "https://www.patreon.com".
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
	}

	/**
	 * Returns the user in relation with the access token.
	 * 
	 * @return the authenticated user
	 * 
	 * @throws IOException if the request fails
	 */
	public User fetchUser() throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("identity").addParameter("include",
				"campaign.benefits.campaign," + "campaign.benefits.deliverables.benefit,"
						+ "campaign.benefits.deliverables.campaign,"
						+ "campaign.benefits.deliverables.member.address.campaigns,"
						+ "campaign.benefits.deliverables.member.address.user.campaign,"
						+ "campaign.benefits.deliverables.member.address.user.memberships,"
						+ "campaign.benefits.deliverables.member.campaign,"
						+ "campaign.benefits.deliverables.member.currently_entitled_tiers,"
						+ "campaign.benefits.deliverables.member.pledge_history.campaign,"
						+ "campaign.benefits.deliverables.member.pledge_history.patron,"
						+ "campaign.benefits.deliverables.member.pledge_history.tier,"
						+ "campaign.benefits.deliverables.member.user.campaign,"
						+ "campaign.benefits.deliverables.member.user.memberships,"
						+ "campaign.benefits.deliverables.user.campaign,"
						+ "campaign.benefits.deliverables.user.memberships," + "campaign.benefits.tiers.benefits,"
						+ "campaign.benefits.tiers.campaign," + "campaign.benefits.tiers.tier_image,"
						+ "campaign.creator.campaign," + "campaign.creator.memberships," + "campaign.goals.campaign,"
						+ "campaign.tiers.benefits.campaign," + "campaign.tiers.benefits," + "campaign.tiers.campaign,"
						+ "campaign.tiers.tier_image," + "memberships.campaign," + "memberships.address,"
						+ "memberships.currently_entitled_tiers.benefits," + "memberships.pledge_history.campaign,"
						+ "memberships.pledge_history.patron," + "memberships.pledge_history.tier,"
						+ "memberships.user.campaign," + "memberships.user.memberships");
		addAllFields(pathBuilder);
		return converter.readDocument(getDataStream(pathBuilder.toString()), User.class).get();
	}

	/**
	 * Returns a list of campaigns owned by the authenticated user.
	 * 
	 * @return a list of campaigns
	 * 
	 * @throws IOException if the request fails
	 */
	public List<Campaign> fetchCampaigns() throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("campaigns").addParameter("include",
				"benefits.campaign," + "benefits.deliverables.campaign," + "benefits.deliverables.benefit,"
						+ "benefits.deliverables.member.user," + "benefits.deliverables.user," + "benefits.tiers,"
						+ "creator.memberships.address.campaigns," + "creator.memberships.address.user,"
						+ "creator.memberships.campaign.benefits.campaign,"
						+ "creator.memberships.campaign.benefits.tiers.benefits,"
						+ "creator.memberships.campaign.benefits.tiers.campaign,"
						+ "creator.memberships.campaign.benefits.tiers.tier_image,"
						+ "creator.memberships.campaign.tiers,"
						+ "creator.memberships.currently_entitled_tiers.benefits," + "goals.campaign,"
						+ "tiers.benefits," + "tiers.campaign," + "tiers.tier_image");
		addAllFields(pathBuilder);
		return converter.readDocumentCollection(getDataStream(pathBuilder.toString()), Campaign.class).get();
	}

	/**
	 * Returns the campaign specified by it's ID.
	 *
	 * @return the campaign specified by it's ID
	 *
	 * @param campaignID the ID for the campaign
	 * 
	 * @throws IOException if the request fails
	 */
	public Campaign fetchCampaign(String campaignID) throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("campaigns/" + campaignID).addParameter("include",
				"benefits.campaign," + "benefits.deliverables.campaign," + "benefits.deliverables.benefit,"
						+ "benefits.deliverables.member.user," + "benefits.deliverables.user," + "benefits.tiers,"
						+ "creator.memberships.address.campaigns," + "creator.memberships.address.user,"
						+ "creator.memberships.campaign.benefits.campaign,"
						+ "creator.memberships.campaign.benefits.tiers.benefits,"
						+ "creator.memberships.campaign.benefits.tiers.campaign,"
						+ "creator.memberships.campaign.benefits.tiers.tier_image,"
						+ "creator.memberships.campaign.tiers,"
						+ "creator.memberships.currently_entitled_tiers.benefits," + "goals.campaign,"
						+ "tiers.benefits," + "tiers.campaign," + "tiers.tier_image");
		addAllFields(pathBuilder);
		return converter.readDocument(getDataStream(pathBuilder.toString()), Campaign.class).get();
	}

	/**
	 * Returns a list of members from the specified campaign. If you request pledge
	 * events, the list will only contain 500 members. Otherwise, it will contain
	 * 1000 members. If you exceed these numbers, you will need to use the
	 * pagination meta to get the rest of your members.
	 * 
	 * @return a list of members from the specified campaign
	 *
	 * @param campaignID   the ID for the campaign
	 * 
	 * @param pledgeEvents request pledge history for each member
	 * 
	 * @throws IOException if the request fails
	 * 
	 * @see #fetchPageOfMembers(String, boolean, int, String)
	 */
	public JSONAPIDocument<List<Member>> fetchMembers(String campaignID, boolean pledgeEvents) throws IOException {
		String includes = "address.campaigns," + "address.user," + "campaign.benefits.campaign,"
				+ "campaign.benefits.deliverables.benefit," + "campaign.benefits.deliverables.campaign,"
				+ "campaign.benefits.deliverables.member.address.campaigns,"
				+ "campaign.benefits.deliverables.member.address.user.campaign,"
				+ "campaign.benefits.deliverables.member.address.user.memberships,"
				+ "campaign.benefits.deliverables.member.campaign,"
				+ "campaign.benefits.deliverables.member.currently_entitled_tiers,";

		if (pledgeEvents) {
			includes = includes.concat("campaign.benefits.deliverables.member.pledge_history.campaign,"
					+ "campaign.benefits.deliverables.member.pledge_history.patron,"
					+ "campaign.benefits.deliverables.member.pledge_history.tier,");
		}

		includes = includes.concat("campaign.benefits.deliverables.member.user.campaign,"
				+ "campaign.benefits.deliverables.member.user.memberships,"
				+ "campaign.benefits.deliverables.user.campaign," + "campaign.benefits.deliverables.user.memberships,"
				+ "campaign.benefits.tiers.benefits," + "campaign.benefits.tiers.campaign,"
				+ "campaign.benefits.tiers.tier_image," + "campaign.goals.campaign,"
				+ "currently_entitled_tiers.benefits.campaign," + "currently_entitled_tiers.campaign,"
				+ "currently_entitled_tiers.tier_image,");

		if (pledgeEvents) {
			includes = includes.concat("pledge_history.campaign," + "pledge_history.patron," + "pledge_history.tier,");
		}

		includes = includes.concat("user.memberships.user");

		URIBuilder pathBuilder = new URIBuilder().setPath("campaigns/" + campaignID + "/members")
				.addParameter("include", includes);
		addAllFields(pathBuilder);
		return converter.readDocumentCollection(getDataStream(pathBuilder.toString()), Member.class);
	}

	/**
	 * Returns a list of members from the specified campaign. This is made for use
	 * with the pagination meta if your previous response didn't contain all of your
	 * members. If this response doesn't contain all of your members, it may also
	 * have some pagination meta.
	 * 
	 * @return a list of members from the specified campaign
	 *
	 * @param campaignID   the ID for the campaign
	 * 
	 * @param pledgeEvents request pledge history for each member
	 * 
	 * @param pageSize     the number of members to request
	 * 
	 * @param cursor       the next cursor from the pagination meta
	 * 
	 * @throws IOException if the request fails
	 */
	public JSONAPIDocument<List<Member>> fetchPageOfMembers(String campaignID, boolean pledgeEvents, int pageSize,
			String cursor) throws IOException {
		String includes = "address.campaigns," + "address.user," + "campaign.benefits.campaign,"
				+ "campaign.benefits.deliverables.benefit," + "campaign.benefits.deliverables.campaign,"
				+ "campaign.benefits.deliverables.member.address.campaigns,"
				+ "campaign.benefits.deliverables.member.address.user.campaign,"
				+ "campaign.benefits.deliverables.member.address.user.memberships,"
				+ "campaign.benefits.deliverables.member.campaign,"
				+ "campaign.benefits.deliverables.member.currently_entitled_tiers,";

		if (pledgeEvents) {
			includes = includes.concat("campaign.benefits.deliverables.member.pledge_history.campaign,"
					+ "campaign.benefits.deliverables.member.pledge_history.patron,"
					+ "campaign.benefits.deliverables.member.pledge_history.tier,");
		}

		includes = includes.concat("campaign.benefits.deliverables.member.user.campaign,"
				+ "campaign.benefits.deliverables.member.user.memberships,"
				+ "campaign.benefits.deliverables.user.campaign," + "campaign.benefits.deliverables.user.memberships,"
				+ "campaign.benefits.tiers.benefits," + "campaign.benefits.tiers.campaign,"
				+ "campaign.benefits.tiers.tier_image," + "campaign.goals.campaign,"
				+ "currently_entitled_tiers.benefits.campaign," + "currently_entitled_tiers.campaign,"
				+ "currently_entitled_tiers.tier_image,");

		if (pledgeEvents) {
			includes = includes.concat("pledge_history.campaign," + "pledge_history.patron," + "pledge_history.tier,");
		}

		includes = includes.concat("user.memberships.user");

		URIBuilder pathBuilder = new URIBuilder().setPath("campaigns/" + campaignID + "/members")
				.addParameter("page[size]", String.valueOf(pageSize));

		if (cursor != null) {
			pathBuilder.addParameter("page[cursor]", cursor);
		}

		pathBuilder.addParameter("include", includes);
		addAllFields(pathBuilder);

		return converter.readDocumentCollection(getDataStream(pathBuilder.toString()), Member.class);
	}

	/**
	 * Returns a member specified by their member ID. Pledge events are requested by
	 * default.
	 *
	 * @return a member specified by their member ID
	 *
	 * @param memberID the ID for the member
	 * 
	 * @throws IOException if the request fails
	 */
	public Member fetchMember(String memberID) throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("members/" + memberID).addParameter("include",
				"address.campaigns," + "address.user," + "campaign.benefits.campaign,"
						+ "campaign.benefits.deliverables.benefit," + "campaign.benefits.deliverables.campaign,"
						+ "campaign.benefits.deliverables.member.address.campaigns,"
						+ "campaign.benefits.deliverables.member.address.user.campaign,"
						+ "campaign.benefits.deliverables.member.address.user.memberships,"
						+ "campaign.benefits.deliverables.member.campaign,"
						+ "campaign.benefits.deliverables.member.currently_entitled_tiers,"
						+ "campaign.benefits.deliverables.member.pledge_history.campaign,"
						+ "campaign.benefits.deliverables.member.pledge_history.patron,"
						+ "campaign.benefits.deliverables.member.pledge_history.tier,"
						+ "campaign.benefits.deliverables.member.user.campaign,"
						+ "campaign.benefits.deliverables.member.user.memberships,"
						+ "campaign.benefits.deliverables.user.campaign,"
						+ "campaign.benefits.deliverables.user.memberships," + "campaign.benefits.tiers.benefits,"
						+ "campaign.benefits.tiers.campaign," + "campaign.benefits.tiers.tier_image,"
						+ "campaign.goals.campaign," + "currently_entitled_tiers.benefits.campaign,"
						+ "currently_entitled_tiers.campaign," + "currently_entitled_tiers.tier_image,"
						+ "pledge_history.campaign," + "pledge_history.patron," + "pledge_history.tier,"
						+ "user.memberships.user");
		addAllFields(pathBuilder);
		return converter.readDocument(getDataStream(pathBuilder.toString()), Member.class).get();
	}

	/**
	 * Returns a list of posts from the specified campaign. Patreon's API
	 * documentation doesn't say how many posts are in a response. If this response
	 * doesn't contain all of your posts, you will need to use the pagination meta
	 * to get the rest of your posts.
	 * 
	 * @return a list of posts from the campaign
	 * 
	 * @param campaignID the ID for the campaign
	 * 
	 * @throws IOException if the request fails
	 * 
	 * @see #fetchPageOfPosts(String, int, String)
	 */
	public JSONAPIDocument<List<Post>> fetchPosts(String campaignID) throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("campaigns/" + campaignID + "/posts").addParameter("include",
				"campaign,user.campaign");
		addAllFields(pathBuilder);
		return converter.readDocumentCollection(getDataStream(pathBuilder.toString()), Post.class);
	}

	/**
	 * Returns a list of posts from the specified campaign. This is made for use
	 * with the pagination meta if your previous response didn't contain all of your
	 * posts. If this response doesn't contain all of your posts, it may also have
	 * some pagination meta.
	 * 
	 * @return a list of posts from the campaign
	 * 
	 * @param campaignID the ID for the campaign
	 * 
	 * @param pageSize   the number of posts to request
	 * 
	 * @throws IOException if the request fails
	 * 
	 * @param cursor     the next cursor from the pagination meta
	 */
	public JSONAPIDocument<List<Post>> fetchPageOfPosts(String campaignID, int pageSize, String cursor)
			throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("campaigns/" + campaignID + "/posts")
				.addParameter("page[size]", String.valueOf(pageSize));

		if (cursor != null) {
			pathBuilder.addParameter("page[cursor]", cursor);
		}

		pathBuilder.addParameter("include", "campaign,user.campaign");
		addAllFields(pathBuilder);

		return converter.readDocumentCollection(getDataStream(pathBuilder.toString()), Post.class);
	}

	/**
	 * Returns a post specified by it's ID.
	 * 
	 * @return a post specified by it's ID
	 * 
	 * @param postID the ID for the post
	 * 
	 * @throws IOException if the request fails
	 */
	public Post fetchPost(String postID) throws IOException {
		URIBuilder pathBuilder = new URIBuilder().setPath("posts/" + postID).addParameter("include",
				"campaign,user.campaign");
		addAllFields(pathBuilder);
		return converter.readDocument(getDataStream(pathBuilder.toString()), Post.class).get();
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
