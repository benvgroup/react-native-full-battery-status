
# react-native-full-battery-status
*NOTE: For now, it only works on Android*

## Getting started

`$ npm install react-native-full-battery-status --save`

or

`$ yarn add react-native-full-battery-status`

### Mostly automatic installation

`$ react-native link react-native-full-battery-status`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.benvgroup.RNFullBatteryStatusPackage;` to the imports at the top of the file
  - Add `new RNFullBatteryStatusPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-full-battery-status'
  	project(':react-native-full-battery-status').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-full-battery-status/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-full-battery-status')
  	```

#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-full-battery-status` and add `RNFullBatteryStatus.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNFullBatteryStatus.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)

## Usage
```javascript
import RNFullBatteryStatus from 'react-native-full-battery-status';

async () => {
	await getHealthStatus();
	Returns: // COLD, GOOD, DEAD, OVER_VOLTAGE, OVER_HEAT

	await getChargingStatus();
	Returns: // CHARGING, DISCHARGING, FULL, NOT_CHARGING, UNKNOWN

	await getTechnology();
	Returns: // WIRELESS, AC, USB, NONE

	await getBatteryPercent();
	Returns: // eg => 43

	await getTemperature();
	Returns: // eg => 30.5

	await getVoltage();
	Returns: // eg => 3851

	await getChargeCounter();
	Returns: // eg => 1201746 microampere-hours
}

```

## Current progress

- [x] Android interfaces
- [ ] iOS interfaces;
- [ ] Tests
