/**
 * <copyright>
 * 
 * Copyright (c) 2021 ETAS GmbH. All rights reserved.
 * 
 * </copyright>
 */
package vitals;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 */
public class EnumBatteryResolver implements BatteryProvider {

	private final Set<? extends Battery> battery;
	private Map<Float, Battery> temperature;
	private Map<Float, Battery> stateOfCharge;
	private final Map<Float, Battery> chargeRate;

	{
		battery = Collections.unmodifiableSet(EnumSet.allOf(BatteryEnum.class));
		temperature = new HashMap<>();
		stateOfCharge = new HashMap<>();
		chargeRate = new HashMap<>();
		for (final Battery battery : BatteryEnum.values()) {
			temperature.put(battery.temperature(), battery);
			stateOfCharge.put(battery.stateOfCharge(), battery);
			chargeRate.put(battery.chargeRate(), battery);
		}
	}

	@Override
	public boolean isTemperatureOk(final float temperatureValue) {
		final Battery batteryTemperature = temperature.get(temperatureValue);
		if (isLower(batteryTemperature.temperature(), BMSProviderConstants.MINIIMUM_TEMPERATURE)
				|| isHigher(batteryTemperature.temperature(), BMSProviderConstants.MAXIMUM_TEMPERATURE)) {
			printErrorMessage(BMSProviderConstants.TEMPERATURE);
			return false;
		}
		return true;
	}

	/**
	 * @param temperature2
	 * @param maximumTemperature
	 * @return
	 */
	private static boolean isHigher(final float actualValue, final float standardValue) {
		return actualValue > standardValue;
	}

	/**
	 * @param temperature2
	 * @return
	 */
	private static boolean isLower(final float actualValue, final float standardValue) {
		return actualValue < standardValue;
	}

	@Override
	public boolean isStateOfChargeOK(final float soc) {
		final Battery batterySOC = stateOfCharge.get(soc);
		if (isLower(batterySOC.stateOfCharge(), BMSProviderConstants.MINIMUM_STATE_OF_CHARGE)
				|| isHigher(batterySOC.stateOfCharge(), BMSProviderConstants.MAXIMUM_STATE_OF_CHARGE)) {
			printErrorMessage(BMSProviderConstants.STATE_OF_CHARGE);
			return false;
		}
		return true;
	}

	@Override
	public boolean isChargeOfRateOK(final float chargeOfRate) {
		final Battery batteryCR = chargeRate.get(chargeOfRate);
		if (isHigher(batteryCR.chargeRate(), BMSProviderConstants.MAXIMUM_CHARGE_RATE)) {
			printErrorMessage(BMSProviderConstants.CHARGE_RATE);
			return false;
		}
		return true;
	}

	@Override
	public Set<? extends Battery> getBatteryInfo(final Battery battery) {
		final Battery batteryCR = battery;
		return (Set<? extends Battery>) battery;
	}

	public boolean batteryIsOk(final float temperature, final float soc, final float chargeRate) {
		return isTemperatureOk(temperature) && isStateOfChargeOK(soc) && isChargeOfRateOK(chargeRate);
	}

	public static void printErrorMessage(final String parameter) {
		System.out.print(parameter);
		System.out.println(" is out of range!");
	}

}
