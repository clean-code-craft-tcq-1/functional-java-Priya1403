/**
 * <copyright>
 * 
 * Copyright (c) 2021 ETAS GmbH. All rights reserved.
 * 
 * </copyright>
 */
package vitals;

import java.util.Set;

/**
 * 
 */
public interface BatteryProvider {

	boolean isTemperatureOk(final float temperature);

	boolean isStateOfChargeOK(final float soc);

	boolean isChargeOfRateOK(final float chargeOfRate);

	Set<? extends Battery> getBatteryInfo(final Battery battery);

}
