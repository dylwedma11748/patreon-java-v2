package patreon.java;

import static patreon.java.PatreonAPI.BASE_URI;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestUtil {

	public InputStream request(String pathSuffix, String accessToken) throws IOException {
		URL url = buildUrl(pathSuffix);
		System.out.println(url.toString());
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
		java.util.Properties prop = new java.util.Properties();
		prop.load(resourceAsStream);
		return prop.getProperty("version");
	}
}
