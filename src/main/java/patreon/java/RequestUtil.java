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

import static patreon.java.PatreonAPI.BASE_URI;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * A utility for making GET requests to Patreon's REST API. You only need
 * <b>one</b> of these.
 */
public class RequestUtil {

	/**
	 * Sends a request to Patreon's REST API.
	 * 
	 * @param pathSuffix  the path after /api/oauth2/v2/
	 * 
	 * @param accessToken the access token
	 * 
	 * @return the input stream
	 * 
	 * @throws IOException if the request fails
	 */
	public InputStream request(String pathSuffix, String accessToken) throws IOException {
		URL url = buildUrl(pathSuffix);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestProperty("Authorization", "Bearer ".concat(accessToken));
		connection.setRequestProperty("User-Agent", String.format("Patreon-Java-v2, version %s, platform %s %s",
				getVersion(), System.getProperty("os.name"), System.getProperty("os.version")));

		return connection.getInputStream();
	}

	private URL buildUrl(String pathSuffix) throws MalformedURLException {
		if (pathSuffix.startsWith("/")) {
			pathSuffix = pathSuffix.substring(1, pathSuffix.length());
		}

		String prefix = BASE_URI + "/api/oauth2/v2/";
		URL url = new URL(prefix.concat(pathSuffix));

		return url;
	}

	private String getVersion() throws IOException {
		InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
		Properties prop = new java.util.Properties();

		prop.load(resourceAsStream);

		return prop.getProperty("version");
	}
}
