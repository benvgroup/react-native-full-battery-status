
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
  public void pluggedType(final Promise promise) {
    try {
      String type = batteryStatus.getPlugTypeString();
      promise.resolve(type);
    } catch (Exception ex) {
      promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
    }
  }
}