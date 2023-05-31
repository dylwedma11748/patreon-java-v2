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

package patreon.java.resources;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

import patreon.java.resources.shared.BaseResource;
import patreon.java.resources.shared.Field;

/**
 * A file uploaded to patreon.com, usually an image.
 */
@Type("media")
public class Media extends BaseResource {

	/**
	 * This enum represents the different fields or properties associated with the
	 * Media resource.
	 */
	public enum MediaField implements Field {
		/** The date and time the media was created. */
		created_at("created_at"),
		/** The URL for downloading the media file. */
		download_url("download_url"),
		/** The name of the media file. */
		file_name("file_name"),
		/** The URLs for different sizes of the media image, if applicable. */
		image_urls("image_urls"),
		/** Additional metadata about the media. */
		metadata("metadata"),
		/** The MIME type of the media file. */
		mimetype("mimetype"),
		/** The ID of the user or entity that owns the media. */
		owner_id("owner_id"),
		/** The relationship of the owner to the media, if applicable. */
		owner_relationship("owner_relationship"),
		/** The type of the entity that owns the media. */
		owner_type("owner_type"),
		/** The size of the media file in bytes. */
		size_bytes("size_bytes"),
		/** The current state of the media file. */
		state("state"),
		/** The date and time the upload for the media file expires. */
		upload_expires_at("upload_expires_at"),
		/** The parameters for uploading the media file. */
		upload_parameters("upload_parameters"),
		/** The URL for uploading the media file. */
		upload_url("upload_url");

		private final String propertyName;

		MediaField(String propertyName) {
			this.propertyName = propertyName;
		}

		/**
		 * Returns a collection of all available fields for the Media resource.
		 * 
		 * @return a collection of Media fields
		 */
		public static Collection<MediaField> getAllFields() {
			return List.of(values());
		}

		@Override
		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private String createdAt;
	private String downloadURL;
	private String fileName;
	private Object imageURLs;
	private Object metadata;
	private String mimetype;
	private String ownerID;
	private String ownerRelationship;
	private String ownerType;
	private int sizeBytes;
	private String state;
	private String uploadExpiresAt;
	private Object uploadParameters;
	private String uploadURL;

	public Media(@JsonProperty("created_at") String createdAt, @JsonProperty("download_url") String downloadURL,
			@JsonProperty("file_name") String fileName, @JsonProperty("image_urls") Object imageURLs,
			@JsonProperty("metadata") Object metadata, @JsonProperty("mimetype") String mimetype,
			@JsonProperty("owner_id") String ownerID, @JsonProperty("owner_relationship") String ownerRelationship,
			@JsonProperty("owner_type") String ownerType, @JsonProperty("size_bytes") int sizeBytes,
			@JsonProperty("state") String state, @JsonProperty("upload_expires_at") String uploadExpiresAt,
			@JsonProperty("upload_parameters") Object uploadParameters, @JsonProperty("upload_url") String uploadURL) {
		this.createdAt = createdAt;
		this.downloadURL = downloadURL;
		this.fileName = fileName;
		this.imageURLs = imageURLs;
		this.metadata = metadata;
		this.mimetype = mimetype;
		this.ownerID = ownerID;
		this.ownerRelationship = ownerRelationship;
		this.ownerType = ownerType;
		this.sizeBytes = sizeBytes;
		this.state = state;
		this.uploadExpiresAt = uploadExpiresAt;
		this.uploadParameters = uploadParameters;
		this.uploadURL = uploadURL;
	}

	/**
	 * Returns the date and time that the file was created.
	 * 
	 * @return the date and time that the file was created (UTC ISO format)
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * Returns the URL to download this media. Valid for 24 hours.
	 * 
	 * @return the URL to download this media
	 */
	public String getDownloadURL() {
		return downloadURL;
	}

	/**
	 * Returns the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Returns the resized image URLs for this media. Valid for 2 weeks.
	 * 
	 * @return the resized image URLs for this media
	 */
	public Object getImageURLs() {
		return imageURLs;
	}

	/**
	 * Returns the metadata related to the file. Can be null.
	 * 
	 * @return the file metadata or null
	 */
	public Object getMetadata() {
		return metadata;
	}

	/**
	 * Returns the mimetype of the uploaded file, eg: "application/jpeg".
	 * 
	 * @return the mimetype of the uploaded file
	 */
	public String getMimetype() {
		return mimetype;
	}

	/**
	 * Returns the ownership id.
	 * 
	 * @return the ownership id
	 * 
	 * @see #getOwnerType()
	 */
	public String getOwnerID() {
		return ownerID;
	}

	/**
	 * Returns the ownership relationship type for multi-relationship medias.
	 * 
	 * @return the ownership relationship type
	 */
	public String getOwnerRelationship() {
		return ownerRelationship;
	}

	/**
	 * Returns the type of the resource that owns the file.
	 * 
	 * @return the owner resource type
	 */
	public String getOwnerType() {
		return ownerType;
	}

	/**
	 * Returns the size of the file in bytes.
	 * 
	 * @return the owner resource type
	 */
	public int getSizeBytes() {
		return sizeBytes;
	}

	/**
	 * Returns the state of the file.
	 * 
	 * @return the file state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Returns the date and time that the upload URL expires.
	 * 
	 * @return the date and time that the upload URL (UTC ISO format)
	 */
	public String getUploadExpiresAt() {
		return uploadExpiresAt;
	}

	/**
	 * Returns all the parameters that have to be added to the upload form request.
	 * 
	 * @return all the upload parameters
	 */
	public Object getUploadParameters() {
		return uploadParameters;
	}

	/**
	 * Returns the URL to perform a POST request to in order to upload the media
	 * file.
	 * 
	 * @return the upload URL
	 */
	public String getUploadURL() {
		return uploadURL;
	}
}
