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

package patreon.java.resources.shared;

import java.util.List;

/**
 * A mapping from the user's connected application names to external user id on
 * the respective application.
 */
public class SocialConnections {

	private UserIDObject youtube;
	private UserIDObject twitter;
	private UserIDObject deviantart;
	private UserIDObject discord;
	private UserIDObject twitch;
	private UserIDObject facebook;
	private UserIDObject spotify;

	/**
	 * Returns the YouTube application information if applicable. Can be null.
	 * 
	 * @return the YouTube application information or null
	 */
	public UserIDObject getYoutube() {
		return youtube;
	}

	/**
	 * Returns the Twitter application information if applicable. Can be null.
	 * 
	 * @return the Twitter application information or null
	 */
	public UserIDObject getTwitter() {
		return twitter;
	}

	/**
	 * Returns the DeviantArt application information if applicable. Can be null.
	 * 
	 * @return the DeviantArt application information or null
	 */
	public UserIDObject getDeviantart() {
		return deviantart;
	}

	/**
	 * Returns the Discord application information if applicable. Can be null.
	 * 
	 * @return the Discord application information or null
	 */
	public UserIDObject getDiscord() {
		return discord;
	}

	/**
	 * Returns the Twitch application information if applicable. Can be null.
	 * 
	 * @return the Twitch application information or null
	 */
	public UserIDObject getTwitch() {
		return twitch;
	}

	/**
	 * Returns the Facebook application information if applicable. Can be null.
	 * 
	 * @return the Facebook application information or null
	 */
	public UserIDObject getFacebook() {
		return facebook;
	}

	/**
	 * Returns the Spotify application information if applicable. Can be null.
	 * 
	 * @return the Spotify application information or null
	 */
	public UserIDObject getSpotify() {
		return spotify;
	}

	/**
	 * An object containing the properties of the social connection.
	 */
	public static class UserIDObject {
		private String user_id;
		private List<String> scopes;
		private String url;

		/**
		 * Returns the user ID if applicable. Can be null.
		 * 
		 * @return the user ID or null
		 */
		public String getUser_id() {
			return user_id;
		}

		/**
		 * Returns the scopes if applicable. Can be null.
		 * 
		 * @return the scopes or null
		 */
		public List<String> getScopes() {
			return scopes;
		}

		/**
		 * Returns the URL if applicable. Can be null.
		 * 
		 * @return the URL or null
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * Returns a String representation containing the properties and values.
		 * 
		 * @return a String representation of the UserIDObject
		 */
		@Override
		public String toString() {
			return "UserIDObject [user_id=" + user_id + ", scopes=" + scopes + ", url=" + url + "]";
		}
	}

	/**
	 * Returns a String representation containing the entire mapping.
	 * 
	 * @return a String representation of the SocalConnections
	 */
	@Override
	public String toString() {
		return "SocialConnections [youtube=" + youtube + ", twitter=" + twitter + ", deviantart=" + deviantart
				+ ", discord=" + discord + ", twitch=" + twitch + ", facebook=" + facebook + ", spotify=" + spotify
				+ "]";
	}

}
