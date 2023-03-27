package patreon.java.v2.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.v2.resources.shared.BaseResource;
import patreon.java.v2.resources.shared.Field;

@Type("media")
public class Media extends BaseResource {

	public enum MediaField implements Field {
		created_at("created_at"), download_url("download_url"), file_name("file_name"), image_urls("image_urls"),
		metadata("metadata"), mimetype("mimetype"), owner_id("owner_id"), owner_relationship("owner_relationship"),
		owner_type("owner_type"), size_bytes("size_bytes"), state("state"), upload_expires_at("upload_expires_at"),
		upload_parameters("upload_parameters"), upload_url("upload_url");

		private final String propertyName;

		MediaField(String propertyName) {
			this.propertyName = propertyName;
		}

		public static Collection<MediaField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	public String created_at;
	public String download_url;
	public String file_name;
	public Object image_urls;
	public Object metadata;
	public String mimetype;
	public String owner_id;
	public String owner_relationship;
	public String owner_type;
	public int size_bytes;
	public String state;
	public String upload_expires_at;
	public Object upload_parameters;
	public String upload_url;

	public Media(@JsonProperty("created_at") String created_at, @JsonProperty("download_url") String download_url,
			@JsonProperty("file_name") String file_name, @JsonProperty("image_urls") Object image_urls,
			@JsonProperty("metadata") Object metadata, @JsonProperty("mimetype") String mimetype,
			@JsonProperty("owner_id") String owner_id, @JsonProperty("owner_relationship") String owner_relationship,
			@JsonProperty("owner_type") String owner_type, @JsonProperty("size_bytes") int size_bytes,
			@JsonProperty("state") String state, @JsonProperty("upload_expires_at") String upload_expires_at,
			@JsonProperty("upload_parameters") Object upload_parameters,
			@JsonProperty("upload_url") String upload_url) {
		this.created_at = created_at;
		this.download_url = download_url;
		this.file_name = file_name;
		this.image_urls = image_urls;
		this.metadata = metadata;
		this.mimetype = mimetype;
		this.owner_id = owner_id;
		this.owner_relationship = owner_relationship;
		this.owner_type = owner_type;
		this.size_bytes = size_bytes;
		this.state = state;
		this.upload_expires_at = upload_expires_at;
		this.upload_parameters = upload_parameters;
		this.upload_url = upload_url;
	}
}
