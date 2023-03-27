package patreon.java.v2;

import java.io.IOException;
import java.util.List;

import com.github.jasminb.jsonapi.JSONAPIDocument;

import patreon.java.v2.resources.Campaign;
import patreon.java.v2.resources.Member;
import patreon.java.v2.resources.User;
import patreon.java.v2.resources.shared.PaginationMeta;

public class PatreonAPITest {

	private static final String PATREON_ACCESS_KEY = "YOUR_ACCESS_KEY";
	
	public static void main(String[] args) {
		PatreonAPI apiClient = new PatreonAPI(PATREON_ACCESS_KEY);
		JSONAPIDocument<List<Member>> memberResponse = null;
		
		try {
			memberResponse = apiClient.fetchMembers("CAMPAIGN_ID");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Member> members = memberResponse.get();
		
		PaginationMeta meta = memberResponse.getMeta(PaginationMeta.class);
		System.out.println(meta.getTotal());
		
		for (Member member : members) {
			System.out.println(member.full_name);
		}
	}

}
