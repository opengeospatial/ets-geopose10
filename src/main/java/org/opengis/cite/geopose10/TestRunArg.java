package org.opengis.cite.geopose10;

/**
 * An enumerated type defining all recognized test run arguments.
 */
public enum TestRunArg {

	/**
	 * An absolute URI that refers to a representation of the test subject or metadata
	 * about it.
	 */
	IUT,
	/**
	 * An absolute URI that refers to a representation of the test subject for
	 * streamRecord tests or metadata about it.
	 */
	STREAMRECORD,
	/**
	 * An absolute URI that refers to a representation of the test subject for
	 * streamHeader tests or metadata about it.
	 */
	STREAMHEADER,
	/**
	 * An absolute URI that refers to a representation of the test subject for
	 * streamElement tests or metadata about it.
	 */
	STREAMELEMENT,
	/**
	 * An absolute URI that refers to a representation of the test subject for
	 * seriesRegular tests or metadata about it.
	 */
	SERIESREGULAR,
	/**
	 * An absolute URI that refers to a representation of the test subject for graph tests
	 * or metadata about it.
	 */
	GRAPH,
	/**
	 * An absolute URI that refers to a representation of the test subject for chain tests
	 * or metadata about it.
	 */
	CHAIN,
	/**
	 * An absolute URI that refers to a representation of the test subject for advanced
	 * tests or metadata about it.
	 */
	ADVANCED,
	/**
	 * An absolute URI that refers to a representation of the test subject for
	 * basicQuaternion tests or metadata about it.
	 */
	BASICQuaternion,
	/**
	 * An absolute URI that refers to a representation of the test subject for basicYPR
	 * tests or metadata about it.
	 */
	BASICYPR;

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return name().toLowerCase();
	}

}
