package org.opengis.cite.geopose10.encodings.json;

import org.opengis.cite.geopose10.BaseJsonSchemaValidatorTest;
import org.opengis.cite.geopose10.CommonFixture;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import org.opengis.cite.geopose10.CommonFixture;
import org.opengis.cite.geopose10.ErrorMessage;
import org.opengis.cite.geopose10.ErrorMessageKeys;
import org.opengis.cite.geopose10.SuiteAttribute;
import org.opengis.cite.geopose10.util.Samples;
import org.opengis.cite.validation.RelaxNGValidator;
import org.opengis.cite.validation.ValidationErrorHandler;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.SpecVersionDetector;
import com.networknt.schema.ValidationMessage;

/**
 * <p>
 * StreamRecord class.
 * </p>
 *
 */
public class StreamRecord extends CommonFixture {

	private File testSubject;

	/**
	 * Obtains the test subject from the ISuite context. The suite attribute
	 * {@link org.opengis.cite.geopose10.SuiteAttribute#TEST_SUBJECT} should evaluate to a
	 * DOM Document node.
	 * @param testContext The test (group) context.
	 */
	@BeforeClass
	public void obtainTestSubject(ITestContext testContext) {

		Object obj = testContext.getSuite().getAttribute(SuiteAttribute.STREAMRECORD_TEST_SUBJ_FILE.getName());

		this.testSubject = (File) obj;

	}

	/**
	 * Sets the test subject. This method is intended to facilitate unit testing.
	 * @param testSubject A Document node representing the test subject or metadata about
	 * it.
	 */
	public void setTestSubject(File testSubject) {
		this.testSubject = testSubject;
	}

	/**
	 * Implements Conformance test A.47: Verify Stream Record conformance to JSON schema
	 * Validate the JSON data against the GeoPose Stream Record JSON-Schema 2019-9
	 * definition
	 */
	@Test(description = "Implements Conformance test A.47: Verify Stream Record conformance to JSON schema (/conf/stream-encoding-json/record)")
	public void validateByStreamRecordSchema() {

		if (!testSubject.isFile()) {
			Assert.assertTrue(testSubject.isFile(), "No file selected. ");
		}

		BaseJsonSchemaValidatorTest tester = new BaseJsonSchemaValidatorTest();
		String schemaToApply = "/org/opengis/cite/geopose10/jsonschema/GeoPose.Composite.Sequence.Stream.Schema.json";

		boolean valid = false;
		StringBuffer sb = new StringBuffer();

		InputStream inputStream = tester.getClass().getResourceAsStream(schemaToApply);

		try {
			JsonNode schemaNode = tester
				.getJsonNodeFromStringContent(tester.otherConvertInputStreamToString(inputStream));
			JsonSchema schema = tester.getJsonSchemaFromJsonNodeAutomaticVersion(schemaNode);

			schema.initializeValidators();

			JsonNode node = tester
				.getJsonNodeFromStringContent(tester.otherConvertInputStreamToString(new FileInputStream(testSubject)));
			Set<ValidationMessage> errors = schema.validate(node);

			Iterator it = errors.iterator();
			while (it.hasNext()) {
				sb.append(" " + it.next() + ".\n");

			}

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertTrue(sb.toString().length() == 0, sb.toString());
	}

}
