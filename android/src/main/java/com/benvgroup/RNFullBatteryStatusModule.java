
package com.benvgroup;

import android.content.Intent;
import android.content.IntentFilter;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNFullBatteryStatusModule extends ReactContextBaseJavaModule implements LifecycleEventListener {

  private final ReactApplicationContext reactContext;
  private BatteryStatus batteryStatusReceiver;

  public RNFullBatteryStatusModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  private void loadBatterySection() {
    IntentFilter intentFilter = new IntentFilter("RNFullBatteryStatus");
    intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
    intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
    intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

    this.getReactApplicationContext().registerReceiver(batteryStatusReceiver, intentFilter);
  }

  @Override
  public String getName() {
    return "RNFullBatteryStatus";
  }

  @Override
  public void initialize() {
    batteryStatusReceiver = new BatteryStatus();
    getReactApplicationContext().addLifecycleEventListener(this);

    loadBatterySection();
  }

  @ReactMethod
  public void startService() {

  }

  @ReactMethod
  public void stopService() {

  }

  /**
   * PUBLIC REACT API
   *
   *  pluggedType() Returns the plugged type. USB, AC, NONE
   */
  @ReactMethod
  public void getPluggedStatus(final Promise promise) {
    try {
      String type = batteryStatusReceiver.getPluggedStatus();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getHealthStatus(final Promise promise) {
    try {
      String type = batteryStatusReceiver.getHealthStatus();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getChargingStatus(final Promise promise) {
    try {
      String type = batteryStatusReceiver.getChargingStatus();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getTechnology(final Promise promise) {
    try {
      String type = batteryStatusReceiver.getTechnology();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getBatteryPercent(final Promise promise) {
    try {
      int type = batteryStatusReceiver.getBatteryPercent();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getTemperature(final Promise promise) {
    try {
      float type = batteryStatusReceiver.getTemperatureResult();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getVoltage(final Promise promise) {
    try {
      int type = batteryStatusReceiver.getVoltage();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getCapacity(final Promise promise) {
    try {
      float type = (float)batteryStatusReceiver.getCapacity();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }
  @ReactMethod
  public void getChargeCounter(final Promise promise){
    try {
        float type = (float) batteryStatusReceiver.getChargeCounter();
        promise.resolve(type);
      } catch (Exception ex) {
        promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
      }
  }

  @Override
  public void onHostResume() {
    IntentFilter filter = new IntentFilter(BatteryStatus.ACTION_RESP);
    filter.addCategory(Intent.CATEGORY_DEFAULT);
    this.getReactApplicationContext().registerReceiver(batteryStatusReceiver, filter);
  }

  @Override
  public void onHostPause() {
    this.getReactApplicationContext().unregisterReceiver(batteryStatusReceiver);
  }

  @Override
  public void onHostDestroy() {

  }
}