package org.opengis.cite.geopose10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.SpecVersionDetector;

/**
 * <p>
 * BaseJsonSchemaValidatorTest class.
 * </p>
 *
 */
public class BaseJsonSchemaValidatorTest {

	/**
	 * int DEFAULT_BUFFER_SIZE = 8192
	 */
	public final int DEFAULT_BUFFER_SIZE = 8192;

	private ObjectMapper mapper = new ObjectMapper();

	// from
	// https://github.com/networknt/json-schema-validator/blob/master/doc/quickstart.md

	/**
	 * <p>
	 * getJsonNodeFromClasspath.
	 * </p>
	 * @param name a {@link java.lang.String} object
	 * @return a {@link com.fasterxml.jackson.databind.JsonNode} object
	 * @throws java.io.IOException if any.
	 */
	public JsonNode getJsonNodeFromClasspath(String name) throws IOException {
		InputStream is1 = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
		return mapper.readTree(is1);
	}

	/**
	 * <p>
	 * getJsonNodeFromStringContent.
	 * </p>
	 * @param content a {@link java.lang.String} object
	 * @return a {@link com.fasterxml.jackson.databind.JsonNode} object
	 * @throws java.io.IOException if any.
	 */
	public JsonNode getJsonNodeFromStringContent(String content) throws IOException {
		return mapper.readTree(content);
	}

	/**
	 * <p>
	 * getJsonNodeFromUrl.
	 * </p>
	 * @param url a {@link java.lang.String} object
	 * @return a {@link com.fasterxml.jackson.databind.JsonNode} object
	 * @throws java.io.IOException if any.
	 */
	public JsonNode getJsonNodeFromUrl(String url) throws IOException {
		return mapper.readTree(new URL(url));
	}

	/**
	 * <p>
	 * getJsonSchemaFromClasspath.
	 * </p>
	 * @param name a {@link java.lang.String} object
	 * @return a {@link com.networknt.schema.JsonSchema} object
	 */
	public JsonSchema getJsonSchemaFromClasspath(String name) {
		JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
		return factory.getSchema(is);
	}

	/**
	 * <p>
	 * getJsonSchemaFromStringContent.
	 * </p>
	 * @param schemaContent a {@link java.lang.String} object
	 * @return a {@link com.networknt.schema.JsonSchema} object
	 */
	public JsonSchema getJsonSchemaFromStringContent(String schemaContent) {
		JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
		return factory.getSchema(schemaContent);
	}

	/**
	 * <p>
	 * getJsonSchemaFromUrl.
	 * </p>
	 * @param uri a {@link java.lang.String} object
	 * @return a {@link com.networknt.schema.JsonSchema} object
	 * @throws java.net.URISyntaxException if any.
	 */
	public JsonSchema getJsonSchemaFromUrl(String uri) throws URISyntaxException {
		JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
		return factory.getSchema(new URI(uri));
	}

	/**
	 * <p>
	 * getJsonSchemaFromJsonNode.
	 * </p>
	 * @param jsonNode a {@link com.fasterxml.jackson.databind.JsonNode} object
	 * @return a {@link com.networknt.schema.JsonSchema} object
	 */
	public JsonSchema getJsonSchemaFromJsonNode(JsonNode jsonNode) {
		JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
		return factory.getSchema(jsonNode);
	}

	// Automatically detect version for given JsonNode
	/**
	 * <p>
	 * getJsonSchemaFromJsonNodeAutomaticVersion.
	 * </p>
	 * @param jsonNode a {@link com.fasterxml.jackson.databind.JsonNode} object
	 * @return a {@link com.networknt.schema.JsonSchema} object
	 */
	public JsonSchema getJsonSchemaFromJsonNodeAutomaticVersion(JsonNode jsonNode) {
		JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersionDetector.detect(jsonNode));
		return factory.getSchema(jsonNode);
	}

	// from https://mkyong.com/java/how-to-convert-inputstream-to-string-in-java/
	/**
	 * <p>
	 * otherConvertInputStreamToString.
	 * </p>
	 * @param is a {@link java.io.InputStream} object
	 * @return a {@link java.lang.String} object
	 * @throws java.io.IOException if any.
	 */
	public String otherConvertInputStreamToString(InputStream is) throws IOException {

		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int length;
		while ((length = is.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}

		return result.toString("UTF-8");

	}

}
