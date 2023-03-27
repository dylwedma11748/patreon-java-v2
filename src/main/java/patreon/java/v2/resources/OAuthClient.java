package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

public class OAuthClient extends BaseResource {

	public enum OAuthClientField implements Field {
		author_name("author_name"), client_secret("client_secret"), description("description"), domain("domain"),
		icon_url("icon_url"), name("name"), privacy_policy_url("privacy_policy_url"), redirect_uris("redirect_uris"),
		tos_url("tos_url"), version("version");

		private final String propertyName;

		OAuthClientField(String propertyName) {
			this.propertyName = propertyName;
		}

		public static Collection<OAuthClientField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}
}
