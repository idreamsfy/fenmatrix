package org.quant.fenmatrix.convention;

public enum Compounding {
	/**
	 * {@latex$ 1+rt }
	 */
	Simple,

	/**
	 * {@latex$ (1+r)^t }
	 */
	Compounded,

	/**
	 * {@latex$ e^{rt} }
	 */
	Continuous,

	/**
	 * Simple up to the first period then Compounded
	 */
	SimpleThenCompounded;
}
