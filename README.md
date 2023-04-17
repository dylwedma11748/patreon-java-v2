# patreon-java-v2
 A work in progress Java library for handling API calls to Patreon's APIv2 endpoints.
 
 ### Library currently in active development
 In it's current state, all fields and relationships are confirmed to be working so long as you use the identity endpoint. I'm working on achieving this for all other endpoints as well. The library is still not ready for release yet.
 
Webhooks and OAuthClients are currently not supported.
 
## Using the library
Full documentation for the library is available [here](https://dylwedma11748.github.io/patreon-java-v2/index.html).

To use the library, you'll need your creator access token. You can find it on the [Patreon Platform](https://www.patreon.com/portal/registration/register-clients). Do **NOT** share your token with anyone else.

### Initializing
Create a new instance of PatreonAPI with your access token.
```java
PatreonAPI apiClient = new PatreonAPI(PATREON_ACCESS_KEY)
```

## Endpoints

### Identity - /api/oauth2/v2/identity
The Identity endpoint is used for fetching information about the current User with reference to the oauth token.
```java
User user = apiClient.fetchUser();
```
From here you can access information about your campaign.
```java
Campaign campaign = user.getCampaign();

System.out.println("Created at: " + campaign.getCreatedAt());
System.out.println("Creation name: " + campaign.getCreationName());
System.out.println("Patron Count: " + campaign.getPatronCount());
System.out.println("Pledge URL: " + campaign.getPledgeURL());
System.out.println("Publshed At: " + campaign.getPublishedAt());
System.out.println("URL: " + campaign.getURL());
System.out.println("Vanity: " + campaign.getVanity();
```
You can also access relationships to the campaign.
```java
List<Benefit> benefits = campaign.getBenefits(); // All benefits from the campaign
User creator = campaign.getCreator(); // The campaign's creator, kind of redundant for the identity endpoint
List<Goal> goals = campaign.getGoals(); // The campaign's goals
List<Tier> tiers = campaign.getTiers(); // The campaign's tiers
```
The identity endpoint also lets you access information about your memberships to other campaigns.
```java
List<Member> memberships = user.getMemberships();
```
From here you can get your member information like name, email, pledge cadence, and pledge history.
```java
Member member = memberships.get(0);

System.out.println("Name: " + member.getFullName());
System.out.println("Email: " + member.getEmail());
System.out.println("Pledge Cadence: " + member.getPledgeCadence() + " months");

List<PledgeEvent> pledgeHistory = member.getPledgeHistory();
```

### Campaigns - /api/oauth2/v2/campaigns or /api/oauth2/v2/campaigns/xxxxx
These endpoints are for fetching information about campaigns. If you specify a campaign ID, it will retrieve information about that specific campaign. Otherwise it will return a list of campaigns owned by the authorized user.
```java
String ID = "CAMPAIGN_ID";
Campaign campaign = apiClient.fetchCampaign(ID);
```
```java
List<Campaigns> campaigns = apiClient.fetchCampaigns();
Campaign campaign = campaigns.get(0);
```

### Members - /api/oauth2/v2/campaigns/xxxxx/members or /api/oauth2/v2/members/xxxxx
These endpoints are for fetching information about members. If you specify a campaign ID, you will get a list of members for that campaign. If you specify a member ID, you will get information about that specific member.
```java
JSONAPIDocument<List<Member>> response = apiClient.fetchMembers(campaign.getID());
List<Member> members = response.get();

for (Member member : members) {
    System.out.println("Name: " + member.getFullName());
}
```
```java
String ID = "MEMBER_ID";
Member member = apiClient.fetchMember(ID);
```
The response from the client does also have some pagination meta.
```json
"meta": {
    "pagination": {
        "cursors": {
             "next": null
        },
        "total": 1
    }
}
```
Returns from this endpoint have 500 results in one page. This is because the library requests pledge history for each member. This pagination meta is supposed to return a next page cursor. The library does has a resource that can deserialize this.
```java
PaginationMeta meta = response.getMeta(PaginationMeta.class);
StringBuilder metaString = new StringBuilder().append("\n[meta]").append("\ntotal: " + meta.getTotal()).append("\nnext: " + meta.getNextCursor() + "\n");
System.out.println(metaString);
```
But I don't have a campaign with more than 500 members in order to test it.

### Posts - /api/oauth2/v2/campaigns/xxxxx/posts or /api/oauth2/v2/posts/xxxxx
These endpoints are for fetching posts from a campaign. If you specify a campaign ID, it will pull the posts from that campaign. If you specify a post ID, it will fetch that specific post.
```java
JSONAPIDocument<List<Post>> response = apiClient.fetchPosts(campaign.getID());
List<Post> posts = response.get();

for (Post post : posts) {
    System.out.println("Content: " + post.getContent());
}
```
```java
String postID = "POST_ID";
Post post = apiClient.fetchPost(postID);
```
Like the members endpoint, this endpoint also has some pagination meta. The API docs doesn't say how many results are in one return. But the meta can also still be deserialized.
