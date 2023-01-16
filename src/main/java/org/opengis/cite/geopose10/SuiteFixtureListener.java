package org.opengis.cite.geopose10;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.logging.Level;

import org.opengis.cite.geopose10.util.ClientUtils;
import org.opengis.cite.geopose10.util.TestSuiteLogger;
import org.opengis.cite.geopose10.util.URIUtils;
import org.opengis.cite.geopose10.util.XMLUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.w3c.dom.Document;

import com.sun.jersey.api.client.Client;

/**
 * A listener that performs various tasks before and after a test suite is run,
 * usually concerned with maintaining a shared test suite fixture. Since this
 * listener is loaded using the ServiceLoader mechanism, its methods will be
 * called before those of other suite listeners listed in the test suite
 * definition and before any annotated configuration methods.
 *
 * Attributes set on an ISuite instance are not inherited by constituent test
 * group contexts (ITestContext). However, suite attributes are still accessible
 * from lower contexts.
 *
 * @see org.testng.ISuite ISuite interface
 */
public class SuiteFixtureListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        processSuiteParameters(suite);
        registerClientComponent(suite);
    }

    @Override
    public void onFinish(ISuite suite) {
        if (null != System.getProperty("deleteSubjectOnFinish")) {
            deleteTempFiles(suite);
            System.getProperties().remove("deleteSubjectOnFinish");
        }
    }

    /**
     * Processes test suite arguments and sets suite attributes accordingly. The
     * entity referenced by the {@link TestRunArg#IUT iut} argument is retrieved
     * and written to a File that is set as the value of the suite attribute
     * {@link SuiteAttribute#TEST_SUBJ_FILE testSubjectFile}.
     * 
     * @param suite
     *            An ISuite object representing a TestNG test suite.
     */
    void processSuiteParameters(ISuite suite) {
        Map<String, String> params = suite.getXmlSuite().getParameters();
        TestSuiteLogger.log(Level.CONFIG, "Suite parameters\n" + params.toString());
  

        //------Basic YPR
        
        String basicYPRParam = params.get(TestRunArg.BASICYPR.toString());
        if ((null == basicYPRParam) || basicYPRParam.isEmpty()) {
            throw new IllegalArgumentException("Required test run parameter not found: " + TestRunArg.BASICYPR.toString());
        }
        URI basicYPRRef = URI.create(basicYPRParam.trim());
        File basicYPREntityFile = null;
        try {
            basicYPREntityFile = URIUtils.dereferenceURI( basicYPRRef );
        } catch ( IOException ioxc ) {
            throw new RuntimeException( "Failed to dereference resource located at " + basicYPRRef, ioxc );
        }
        TestSuiteLogger.log( Level.FINE, String.format( "Wrote test subject to file: %s (%d bytes)",
                                                        basicYPREntityFile.getAbsolutePath(), basicYPREntityFile.length() ) );
        suite.setAttribute(SuiteAttribute.BASICYPR_TEST_SUBJ_FILE.getName(), basicYPREntityFile);
        
      //------Basic Quaternion

        String basicQuaternionParam = params.get(TestRunArg.BASICQuaternion.toString());
        if ((null == basicQuaternionParam) || basicQuaternionParam.isEmpty()) {
            throw new IllegalArgumentException("Required test run parameter not found: " + TestRunArg.BASICQuaternion.toString());
        }
        URI basicQuaternionRef = URI.create(basicQuaternionParam.trim());
        File basicQuaternionEntityFile = null;
        try {
            basicQuaternionEntityFile = URIUtils.dereferenceURI( basicQuaternionRef );
        } catch ( IOException ioxc ) {
            throw new RuntimeException( "Failed to dereference resource located at " + basicQuaternionRef, ioxc );
        }
        TestSuiteLogger.log( Level.FINE, String.format( "Wrote test subject to file: %s (%d bytes)",
                                                        basicQuaternionEntityFile.getAbsolutePath(), basicQuaternionEntityFile.length() ) );
        suite.setAttribute(SuiteAttribute.BASICQUATERNION_TEST_SUBJ_FILE.getName(), basicQuaternionEntityFile);      
        
      //------Advanced

        String advancedParam = params.get(TestRunArg.ADVANCED.toString());
        if ((null == advancedParam) || advancedParam.isEmpty()) {
            throw new IllegalArgumentException("Required test run parameter not found: " + TestRunArg.ADVANCED.toString());
        }
        URI advancedRef = URI.create(advancedParam.trim());
        File advancedEntityFile = null;
        try {
            advancedEntityFile = URIUtils.dereferenceURI( advancedRef );
        } catch ( IOException ioxc ) {
            throw new RuntimeException( "Failed to dereference resource located at " + advancedRef, ioxc );
        }
        TestSuiteLogger.log( Level.FINE, String.format( "Wrote test subject to file: %s (%d bytes)",
                                                        advancedEntityFile.getAbsolutePath(), advancedEntityFile.length() ) );
        suite.setAttribute(SuiteAttribute.ADVANCED_TEST_SUBJ_FILE.getName(), advancedEntityFile);        
    }

    /**
     * A client component is added to the suite fixture as the value of the
     * {@link SuiteAttribute#CLIENT} attribute; it may be subsequently accessed
     * via the {@link org.testng.ITestContext#getSuite()} method.
     *
     * @param suite
     *            The test suite instance.
     */
    void registerClientComponent(ISuite suite) {
        Client client = ClientUtils.buildClient();
        if (null != client) {
            suite.setAttribute(SuiteAttribute.CLIENT.getName(), client);
        }
    }

    /**
     * Deletes temporary files created during the test run if TestSuiteLogger is
     * enabled at the INFO level or higher (they are left intact at the CONFIG
     * level or lower).
     *
     * @param suite
     *            The test suite.
     */
    void deleteTempFiles(ISuite suite) {
        if (TestSuiteLogger.isLoggable(Level.CONFIG)) {
            return;
        }
        File testSubjFile = (File) suite.getAttribute(SuiteAttribute.TEST_SUBJ_FILE.getName());
        if (testSubjFile.exists()) {
            testSubjFile.delete();
        }
    }
}
