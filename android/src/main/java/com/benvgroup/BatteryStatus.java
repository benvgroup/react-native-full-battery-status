package com.benvgroup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class BatteryStatus extends BroadcastReceiver {

  private int plugged;
  private String batteryStatus;

  @Override
  public void onReceive(Context context, Intent intent) {
    boolean isPresent = intent.getBooleanExtra("present", false);
    plugged = intent.getIntExtra("plugged", -1);

    if (isPresent) {
      setBatteryStatus("Battery present");
    } else {
      setBatteryStatus("Battery not present!");
    }
  }

  public String getPlugTypeString() {
    String plugType = "NONE";

    switch (plugged) {
      case BatteryManager.BATTERY_PLUGGED_AC:
        plugType = "AC";
        break;
      case BatteryManager.BATTERY_PLUGGED_USB:
        plugType = "USB";
        break;
    }

    return plugType;
  }

  private void setBatteryStatus(String text) {
    batteryStatus = text;
  }

  private String getBatteryStatus() {
    return batteryStatus;
  }

}