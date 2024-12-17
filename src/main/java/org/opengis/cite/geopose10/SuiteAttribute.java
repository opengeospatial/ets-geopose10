package org.opengis.cite.geopose10;

import java.io.File;

import org.w3c.dom.Document;

import jakarta.ws.rs.client.Client;

/**
 * An enumerated type defining ISuite attributes that may be set to constitute a
 * shared test fixture.
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
    STREAMRECORD_TEST_SUBJ_FILE("streamRecordTestSubjectFile", File.class),
    STREAMHEADER_TEST_SUBJ_FILE("streamHeaderTestSubjectFile", File.class), 
    STREAMELEMENT_TEST_SUBJ_FILE("streamElementTestSubjectFile", File.class),  
    SERIESREGULAR_TEST_SUBJ_FILE("seriesRegularTestSubjectFile", File.class),  
    GRAPH_TEST_SUBJ_FILE("graphTestSubjectFile", File.class),    
    CHAIN_TEST_SUBJ_FILE("chainTestSubjectFile", File.class),    
    ADVANCED_TEST_SUBJ_FILE("advancedTestSubjectFile", File.class),    
    BASICQUATERNION_TEST_SUBJ_FILE("basicQuaternionTestSubjectFile", File.class),
    BASICYPR_TEST_SUBJ_FILE("basicYPRTestSubjectFile", File.class);
	
    private final Class attrType;
    private final String attrName;

    private SuiteAttribute(String attrName, Class attrType) {
        this.attrName = attrName;
        this.attrType = attrType;
    }

    public Class getType() {
        return attrType;
    }

    public String getName() {
        return attrName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(attrName);
        sb.append('(').append(attrType.getName()).append(')');
        return sb.toString();
    }
}
