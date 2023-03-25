package patreon.java.v2.resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static patreon.java.v2.PatreonAPI.BASE_URI;

public class RequestUtil {
	public InputStream request(String pathSuffix, String accessToken) throws IOException {
		URL url = buildUrl(pathSuffix);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization", "Bearer ".concat(accessToken));
		connection.setRequestProperty("User-Agent", String.format("Patreon-Java, version %s, platform %s %s",
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
//		InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
//		java.util.Properties prop = new java.util.Properties();
//		prop.load(resourceAsStream);
//		return prop.getProperty("version");
		return "0.4.2";
	}
}
