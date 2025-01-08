package org.opengis.cite.geopose10;

import java.io.File;

import org.w3c.dom.Document;

import jakarta.ws.rs.client.Client;

/**
 * An enumerated type defining ISuite attributes that may be set to constitute a shared
 * test fixture.
 */
@SuppressWarnings("rawtypes")
public enum SuiteAttribute {

	/**
	 * A client component for interacting with HTTP endpoints.
	 */
	CLIENT("httpClient", Client.class),
	/**
	 * A DOM Document that represents the test subject or metadata about it.
	 */
	TEST_SUBJECT("testSubject", Document.class),
	/**
	 * A File containing the test subject or a description of it.
	 */
	TEST_SUBJ_FILE("testSubjectFile", File.class),
	/**
	 * A File containing the test subject for streamRecord tests or a description of it.
	 */
	STREAMRECORD_TEST_SUBJ_FILE("streamRecordTestSubjectFile", File.class),
	/**
	 * A File containing the test subject for streamHeader tests or a description of it.
	 */
	STREAMHEADER_TEST_SUBJ_FILE("streamHeaderTestSubjectFile", File.class),
	/**
	 * A File containing the test subject for streamElement tests or a description of it.
	 */
	STREAMELEMENT_TEST_SUBJ_FILE("streamElementTestSubjectFile", File.class),
	/**
	 * A File containing the test subject for seriesRegular tests or a description of it.
	 */
	SERIESREGULAR_TEST_SUBJ_FILE("seriesRegularTestSubjectFile", File.class),
	/**
	 * A File containing the test subject for graph tests or a description of it.
	 */
	GRAPH_TEST_SUBJ_FILE("graphTestSubjectFile", File.class), CHAIN_TEST_SUBJ_FILE("chainTestSubjectFile", File.class),
	/**
	 * A File containing the test subject for advanced tests or a description of it.
	 */
	ADVANCED_TEST_SUBJ_FILE("advancedTestSubjectFile", File.class),
	/**
	 * A File containing the test subject for basicQuaternion tests or a description of
	 * it.
	 */
	BASICQUATERNION_TEST_SUBJ_FILE("basicQuaternionTestSubjectFile", File.class),
	/**
	 * A File containing the test subject for basicYPR tests or a description of it.
	 */
	BASICYPR_TEST_SUBJ_FILE("basicYPRTestSubjectFile", File.class);

	private final Class attrType;

	private final String attrName;

	private SuiteAttribute(String attrName, Class attrType) {
		this.attrName = attrName;
		this.attrType = attrType;
	}

	/**
	 * <p>
	 * getType.
	 * </p>
	 * @return a {@link java.lang.Class} object
	 */
	public Class getType() {
		return attrType;
	}

	/**
	 * <p>
	 * getName.
	 * </p>
	 * @return a {@link java.lang.String} object
	 */
	public String getName() {
		return attrName;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(attrName);
		sb.append('(').append(attrType.getName()).append(')');
		return sb.toString();
	}

}
