package patreon.java.v2;

import java.io.IOException;
import java.util.List;

import com.github.jasminb.jsonapi.JSONAPIDocument;

import patreon.java.v2.resources.Campaign;
import patreon.java.v2.resources.Member;
import patreon.java.v2.resources.PledgeEvent;
import patreon.java.v2.resources.Tier;
import patreon.java.v2.resources.User;
import patreon.java.v2.resources.shared.PaginationMeta;

public class PatreonAPITest {

	private static final String PATREON_ACCESS_KEY = "YOUR_ACCESS_KEY";
	private static final PatreonAPI apiClient = new PatreonAPI(PATREON_ACCESS_KEY);
	
	public static void main(String[] args) {
		testUser();
		testCampaign();
	}
	
	private static void testMember() {
		JSONAPIDocument<Member> memberResponse = null;
		
		try {
			memberResponse = apiClient.fetchMember("CAMPAIGN_ID");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Member member = memberResponse.get();
		System.out.println(member.getCampaign().getCreatedAt());
	}
	
	private static void testUser() {
		JSONAPIDocument<User> userResponse = null;
		
		try {
			userResponse = apiClient.fetchUser();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		User user = userResponse.get();
		Campaign campaign = user.getCampaign();
		
		System.out.println(campaign.getCreatedAt());
		
	}
	
	private static void testCampaign() {
		JSONAPIDocument<List<Campaign>> campaginResponse = null;
		
		try {
			campaginResponse = apiClient.fetchCampaigns();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Campaign> campagins = campaginResponse.get();
		
		for (Campaign campaign : campagins) {
			List<Tier> tiers = campaign.getTiers();
			
			for (Tier tier : tiers) {
				System.out.println(tier.getTitle());
			}
			
			testMember(campaign.getId());
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
		System.out.println(meta.getTotal());
		
		for (Member member : members) {
			Campaign c = member.getCampaign();
			System.out.println("Campaign Date: " + c.getPublishedAt());
			
			System.out.println("Member patron status: " + member.getPatronStatus());
			
			List<Tier> tiers = member.getCurrentlyEntitledTiers();
			
			for (Tier tier : tiers) {
				System.out.println(tier.getAmountCents());
			}
			
			List<PledgeEvent> pledgeHistory = member.getPledgeHistory();
			
			for (PledgeEvent event : pledgeHistory) {
				System.out.println("Pledge event tier: " + event.getTier());
			}
		}
	}

}
