package patreon.java.v2;

import java.io.IOException;
import java.util.List;

import com.github.jasminb.jsonapi.JSONAPIDocument;

import patreon.java.PatreonAPI;
import patreon.java.resources.shared.PaginationMeta;
import patreon.java.resources.v2.Benefit;
import patreon.java.resources.v2.Campaign;
import patreon.java.resources.v2.Goal;
import patreon.java.resources.v2.Member;
import patreon.java.resources.v2.PledgeEvent;
import patreon.java.resources.v2.Post;
import patreon.java.resources.v2.Tier;
import patreon.java.resources.v2.User;

public class PatreonAPITest {

	private static final String PATREON_ACCESS_KEY = "YOUR_ACCESS_KEY";
	private static final PatreonAPI apiClient = new PatreonAPI(PATREON_ACCESS_KEY);
	
	public static void main(String[] args) {
		testCampaign();
	}
	
	private static void testUser() {
		User user = null;
		
		try {
			user = apiClient.fetchUser();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("[Identity endpoint test]\n");
		
		System.out.println("[User fields]");
		
		System.out.println(user.getAbout());
		System.out.println(user.getCreated());
		System.out.println(user.getEmail());
		System.out.println(user.getFirstName());
		System.out.println(user.getFullName());
		System.out.println(user.getImageURL());
		System.out.println(user.getLastName());
		System.out.println(user.getLikeCount());
		System.out.println(user.getThumbURL());
		System.out.println(user.getURL());
		System.out.println(user.getVanity()); // The field works, but you should use user.getCampaign().getVanity() in this case instead.
		System.out.println(user.getSocialConnections());
		
		System.out.println("\n[Campaign fields]");
		Campaign campaign = user.getCampaign();
		
		System.out.println("[Campaign]");
		System.out.println("Created at: " + campaign.getCreatedAt());
		System.out.println("Creation name: " + campaign.getCreationName());
		System.out.println("Discord Server ID: " + campaign.getDiscordServerID());
		System.out.println("Google Analytics ID: " + campaign.getGoogleAnalyticsID());
		System.out.println("Image Small URL: " + campaign.getImageSmallURL());
		System.out.println("Main Video Embed: " + campaign.getMainVideoEmbed());
		System.out.println("Main Video URL: " + campaign.getMainVideoURL());
		System.out.println("One Liner: " + campaign.getOneLiner());
		System.out.println("Patron Count: " + campaign.getPatronCount());
		System.out.println("Pay Per Name: " + campaign.getPayPerName());
		System.out.println("Pledge URL: " + campaign.getPledgeURL());
		System.out.println("Publshed At: " + campaign.getPublishedAt());
		System.out.println("RSS Artwork URL: " + campaign.getRssArtworkURL());
		System.out.println("RSS Feed Title: " + campaign.getRssFeedTitle());
		System.out.println("Summary: " + campaign.getSummary());
		System.out.println("Thanks Embed: " + campaign.getThanksEmbed());
		System.out.println("Thanks Msg: " + campaign.getThanksMsg());
		System.out.println("Thanks Video URL: " + campaign.getThanksVideoURL());
		System.out.println("URL: " + campaign.getURL());
		System.out.println("Vanity: " + campaign.getVanity());
		
		testCampaign();
	}
	
	private static void testCampaign() {
		System.out.println("[Campaign endpoint test]\n");
		
		JSONAPIDocument<List<Campaign>> campaginResponse = null;
		
		try {
			campaginResponse = apiClient.fetchCampaigns();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Campaign> campagins = campaginResponse.get();
		
		for (Campaign campaign : campagins) {
			System.out.println("[Campaign]");
			System.out.println("Created at: " + campaign.getCreatedAt());
			System.out.println("Creation name: " + campaign.getCreationName());
			System.out.println("Discord Server ID: " + campaign.getDiscordServerID());
			System.out.println("Google Analytics ID: " + campaign.getGoogleAnalyticsID());
			System.out.println("Image Small URL: " + campaign.getImageSmallURL());
			System.out.println("Image URL: " + campaign.getImageURL());
			System.out.println("Is Charged Immediately: " + campaign.isChargedImmediately());
			System.out.println("Is Monthly: " + campaign.isMonthly());
			System.out.println("Is NSFW: " + campaign.isNSFW());
			System.out.println("Is Show Earnings: " + campaign.isShowEarnings());
			System.out.println("Main Video Embed: " + campaign.getMainVideoEmbed());
			System.out.println("Main Video URL: " + campaign.getMainVideoURL());
			System.out.println("One Liner: " + campaign.getOneLiner());
			System.out.println("Patron Count: " + campaign.getPatronCount());
			System.out.println("Pay Per Name: " + campaign.getPayPerName());
			System.out.println("Pledge URL: " + campaign.getPledgeURL());
			System.out.println("Publshed At: " + campaign.getPublishedAt());
			System.out.println("RSS Artwork URL: " + campaign.getRssArtworkURL());
			System.out.println("RSS Feed Title: " + campaign.getRssFeedTitle());
			System.out.println("Summary: " + campaign.getSummary());
			System.out.println("Thanks Embed: " + campaign.getThanksEmbed());
			System.out.println("Thanks Msg: " + campaign.getThanksMsg());
			System.out.println("Thanks Video URL: " + campaign.getThanksVideoURL());
			System.out.println("URL: " + campaign.getURL());
			System.out.println("Vanity: " + campaign.getVanity());
			
			List<Benefit> benefits = campaign.getBenefits();
			
			for (Benefit benefit : benefits) {
				System.out.println("\n[Benefit]");
				System.out.println("App External ID: " + benefit.getAppExternalId());
				System.out.println("App Meta: " + benefit.getAppMeta());
				System.out.println("Benfit Type: " + benefit.getBenefitType());
				System.out.println("Created At: " + benefit.getCreatedAt());
				System.out.println("Deliverables Due Today: " + benefit.getDeliverablesDueTodayCount());
				System.out.println("Delivered Deliverables: " + benefit.getDeliveredDeliverablesCount());
				System.out.println("Description: " + benefit.getDescription());
				System.out.println("Is Deleted: " + benefit.isDeleted());
				System.out.println("Is Ended: " + benefit.isEnded());
				System.out.println("Is Published: " + benefit.isPublished());
				System.out.println("Next Deliverable Due Date: " + benefit.getNextDeliverableDueDate());
				System.out.println("Not Delivered Deliverables Count: " + benefit.getNotDeliveredDeliverablesCount());
				System.out.println("Rule type: " + benefit.getRuleType());
				System.out.println("Tiers Count: " + benefit.getTiersCount());
				System.out.println("Title: " + benefit.getTitle());
			}
			
			for (Tier tier : campaign.getTiers()) {
				System.out.println(tier.getAmountCents());
			}
			
			for (Goal goal: campaign.getGoals()) {
				System.out.println("Goal description: " + goal.getCampaign());
			}
			
			testMember(campaign.getId());
			//testPost(campaign.getId());
		}
	}
	
	private static void testMember(String id) {
		JSONAPIDocument<List<Member>> memberResponse = null;
		
		try {
			memberResponse = apiClient.fetchMembers(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Member> members = memberResponse.get();
		
		PaginationMeta meta = memberResponse.getMeta(PaginationMeta.class);
		StringBuilder metaString = new StringBuilder().append("\n[meta]").append("\ntotal: " + meta.getTotal()).append("\nnext: " + meta.getNextCursor() + "\n");
		System.out.println(metaString);
		
		for (Member member : members) {
			patreon.java.resources.v1.User user = member.getUser();
			System.out.println(user.getLastName());
			Campaign c = member.getCampaign();
			System.out.println("Campaign Date: " + c.getPublishedAt());
			
			System.out.println("Member patron status: " + member.getPatronStatus());
			
			List<Tier> tiers = member.getCurrentlyEntitledTiers();
			
			for (Tier tier : tiers) {
				System.out.println(tier.getAmountCents());
			}
			
			List<PledgeEvent> pledgeHistory = member.getPledgeHistory();
			
			for (PledgeEvent event : pledgeHistory) {
				//System.out.println("Pledge event tier: " + event.getTier());
			}
		}
	}
	
	private static void testPost(String ID) {
		try {
			System.out.println("\n[Post endpoint test]");
			
			for (Post post : apiClient.fetchPosts(ID).get()) {
				System.out.println("\n[Post]");
				
				System.out.println("App ID: " + post.getAppId());
				System.out.println("App Status: " + post.getAppStatus());
				System.out.println("Content: " + post.getContent());
				System.out.println("Embed Data" + post.getEmbedData());
				System.out.println("Embed URL: " + post.getEmbedURL());
				System.out.println("Is Paid: " + post.isPaid());
				System.out.println("Is Public: " + post.isPublic());
				System.out.println("Tiers: " + post.getTiers());
				System.out.println("Published At: " + post.getPublishedAt());
				System.out.println("Title: " + post.getTitle());
				System.out.println("URL: " + post.getURL());
				
				System.out.println(post.getCampaign());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
