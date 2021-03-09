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
public interface Battery {

	public float temperature();

	public float stateOfCharge();

	public float chargeRate();

}
