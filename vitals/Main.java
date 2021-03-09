package vitals;

public class Main {
	public static void main(final String[] args) {
		EnumBatteryResolver batteryResolver = new EnumBatteryResolver();
		assert batteryResolver.batteryIsOk(25, 70, 0.7f) == true;
		assert batteryResolver.batteryIsOk(50, 85, 0.0f) == false;
		assert batteryResolver.batteryIsOk(-5, 30, 0.5f) == false;
		assert batteryResolver.batteryIsOk(30, 10, 0.5f) == false;
		assert batteryResolver.batteryIsOk(25, 30, 0.9f) == false;
	}
}
