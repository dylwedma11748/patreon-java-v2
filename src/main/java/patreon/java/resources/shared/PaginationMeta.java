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

/**
 * A class representing the pagination meta for some responses.
 */
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

	/**
	 * Returns the next cursor if it exists. Can be null.
	 * 
	 * @return the next cursor or null
	 */
	public String getNextCursor() {
		return nextCursor;
	}

	/**
	 * Returns the total if it exists. Can be null.
	 * 
	 * @return the total or null
	 */
	public int getTotal() {
		return total;
	}
}
