package patreon.java.v2.resources.shared;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

class MetaDeserializer extends JsonDeserializer<PaginationMeta> {

	@Override
	public PaginationMeta deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec codec = jp.getCodec();
		JsonNode node = codec.readTree(jp);
		String nextCursor = node.get("pagination").get("cursors").get("next").asText();
		int total = node.get("pagination").get("total").asInt();
		return new PaginationMeta(nextCursor, total);
	}
}

@JsonDeserialize(using = MetaDeserializer.class)
public class PaginationMeta {

	private String nextCursor;
	private int total;

	public PaginationMeta() {
	}

	public PaginationMeta(@JsonProperty("next") String nextCursor, @JsonProperty("total") int total) {
		this.nextCursor = nextCursor;
		this.total = total;
	}

	public String getNextCursor() {
		return nextCursor;
	}

	public int getTotal() {
		return total;
	}
}
