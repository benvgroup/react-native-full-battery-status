
package com.benvgroup;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNFullBatteryStatusModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private BatteryStatus batteryStatus;

  public RNFullBatteryStatusModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;

    loadBatterySection();
  }

  private void loadBatterySection() {
    batteryStatus = new BatteryStatus();

    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
    intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
    intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

    LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(reactContext);
    localBroadcastManager.registerReceiver(batteryStatus, intentFilter);
  }

  @Override
  public String getName() {
    return "RNFullBatteryStatus";
  }

  /**
   * PUBLIC REACT API
   *
   *  pluggedType() Returns the plugged type. USB, AC, NONE
   */
  @ReactMethod
  public void getPluggedStatus(final Promise promise) {
    try {
      String type = batteryStatus.getPluggedStatus();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getHealthStatus(final Promise promise) {
    try {
      String type = batteryStatus.getHealthStatus();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getChargingStatus(final Promise promise) {
    try {
      String type = batteryStatus.getChargingStatus();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getTechnology(final Promise promise) {
    try {
      String type = batteryStatus.getTechnology();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getBatteryPercent(final Promise promise) {
    try {
      int type = batteryStatus.getBatteryPercent();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getTemperature(final Promise promise) {
    try {
      float type = batteryStatus.getTemperatureResult();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getVoltage(final Promise promise) {
    try {
      int type = batteryStatus.getVoltage();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }

  @ReactMethod
  public void getCapacity(final Promise promise) {
    try {
      long type = batteryStatus.getCapacity();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }
}