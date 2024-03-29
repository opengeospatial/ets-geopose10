package org.opengis.cite.geopose10;

/**
 * An enumerated type defining all recognized test run arguments.
 */
public enum TestRunArg {

    /**
     * An absolute URI that refers to a representation of the test subject or
     * metadata about it.
     */
    IUT,
    STREAMRECORD,
    STREAMHEADER,
    STREAMELEMENT,
    SERIESREGULAR,
    GRAPH,
    CHAIN,
    ADVANCED,
    BASICQuaternion,
	BASICYPR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
