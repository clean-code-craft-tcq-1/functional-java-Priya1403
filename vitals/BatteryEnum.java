/**
 * <copyright>
 * 
 * Copyright (c) 2021 ETAS GmbH. All rights reserved.
 * 
 * </copyright>
 */
package vitals;

/**
 * 
 */
public enum BatteryEnum implements Battery {

	TEMPERATURE(BMSProviderConstants.MAXIMUM_TEMPERATURE, BMSProviderConstants.MINIIMUM_TEMPERATURE), STATEOFCHARGE(
			BMSProviderConstants.MAXIMUM_STATE_OF_CHARGE,
			BMSProviderConstants.MINIMUM_STATE_OF_CHARGE), CHARGEOFRATE(BMSProviderConstants.MAXIMUM_CHARGE_RATE);

	private float lower;
	private float higher;

	private BatteryEnum(final float lower, final float higher) {
		this.lower = lower;
		this.higher = higher;

	}

	private BatteryEnum(final float higher) {
		this.higher = higher;

	}

	@Override
	public float temperature() {
		return higher;
	}

	@Override
	public float stateOfCharge() {
		return higher;
	}

	@Override
	public float chargeRate() {
		return higher;
	}

}
