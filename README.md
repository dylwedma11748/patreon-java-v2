# patreon-java-v2
 A work in progress Java library for handling API calls to Patreon's APIv2 endpoints.
 
 ### Library currently in active development
 In it's current state, all fields and relationships are confirmed to be working so long as you use the identity endpoint. I'm working on achieving this for all other endpoints as well. The library is still not ready for release yet.
 
Webhooks and OAuthClients are currently not supported.
 
## Using the library
To use the library, you'll need your creator access token. You can find it on the [Patreon Platform](https://www.patreon.com/portal/registration/register-clients).

### Initializing
Create a new instance of PatreonAPI with your access token.
```java
PatreonAPI apiClient = new PatreonAPI(PATREON_ACCESS_KEY)
```

## Endpoints
### Identity - /api/oauth2/v2/identity
The Identity endpoint is used for accessing information about the current User with reference to the oauth token.
```java
User user = apiClient.fetchUser();
```
From here you can access information about your campaign.
```java
Campaign campaign = user.getCampaign();

System.out.println("Created at: " + campaign.getCreatedAt());
System.out.println("Patron Count: " + campaign.getPatronCount());
System.out.println("Pledge URL: " + campaign.getPledgeURL());
System.out.println("Publshed At: " + campaign.getPublishedAt());
System.out.println("Summary: " + campaign.getSummary());
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
From here you can get your user information like name, email, pledge amounts, and pledge history.
```java
Member member = memberships.get(0);
List<PledgeEvent> pledgeHistory = member.getPledgeHistory();
```
