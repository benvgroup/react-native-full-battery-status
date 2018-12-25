package com.benvgroup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;

public class BatteryStatus extends BroadcastReceiver {
  public static final String ACTION_RESP = "com.benvgroup.intent.action.MESSAGE_PROCESSED";

  private String healthStatus;
  private String pluggedStatus = "NONE";
  private String chargingStatus;
  private String technology;
  private int batteryPercent;
  private int temperature;
  private int voltage;
  private float temperatureResult;
  private long capacity;
  private Context context;

  @Override
  public void onReceive(Context context, Intent intent) {
    this.context = context;
    updateBatteryData(intent);
  }

  private void updateBatteryData(Intent intent) {
    boolean present = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);

    if (present) {
      int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);

      switch (health) {
        case BatteryManager.BATTERY_HEALTH_COLD:
          healthStatus = "COLD";
          break;

        case BatteryManager.BATTERY_HEALTH_DEAD:
          healthStatus = "DEAD";
          break;

        case BatteryManager.BATTERY_HEALTH_GOOD:
          healthStatus = "GOOD";
          break;

        case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
          healthStatus = "OVER_VOLTAGE";
          break;

        case BatteryManager.BATTERY_HEALTH_OVERHEAT:
          healthStatus = "OVER_HEAT";
          break;

        case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
          healthStatus = "UNSPECIFIED_FAILURE";
          break;

        case BatteryManager.BATTERY_HEALTH_UNKNOWN:
        default:
          break;
      }

      int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
      int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

      if (level != -1 && scale != -1) {
        batteryPercent = (int) ((level / (float) scale) * 100f);
      }

      int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);

      switch (plugged) {
        case BatteryManager.BATTERY_PLUGGED_WIRELESS:
          pluggedStatus = "WIRELESS";
          break;

        case BatteryManager.BATTERY_PLUGGED_USB:
          pluggedStatus = "USB";
          break;

        case BatteryManager.BATTERY_PLUGGED_AC:
          pluggedStatus = "AC";
          break;

        default:
          pluggedStatus = "NONE";
          break;
      }

      int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

      switch (status) {
        case BatteryManager.BATTERY_STATUS_CHARGING:
          chargingStatus = "CHARGING";
          break;

        case BatteryManager.BATTERY_STATUS_DISCHARGING:
          chargingStatus = "DISCHARGING";
          break;

        case BatteryManager.BATTERY_STATUS_FULL:
          chargingStatus = "FULL";
          break;

        case BatteryManager.BATTERY_STATUS_UNKNOWN:
          chargingStatus = "UNKNOWN";
          break;

        case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
        default:
          chargingStatus = "NOT_CHARGING";
          break;
      }

      if (intent.getExtras() != null) {
        technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
      }

      temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);

      if (temperature > 0) {
        temperatureResult = ((float) temperature) / 10f;
      }

      voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);

      capacity = getBatteryCapacity(this.context);
    }

  }

  public long getBatteryCapacity(Context ctx) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      BatteryManager mBatteryManager = (BatteryManager) ctx.getSystemService(Context.BATTERY_SERVICE);
      Long chargeCounter = mBatteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
      Long capacity = mBatteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

      if (chargeCounter != null && capacity != null) {
        long value = (long) (((float) chargeCounter / (float) capacity) * 100f);
        return value;
      }
    }

    return 0;
  }

  public String getHealthStatus() {
    return healthStatus;
  }

  public String getPluggedStatus() {
    return pluggedStatus;
  }

  public String getChargingStatus() {
    return chargingStatus;
  }

  public String getTechnology() {
    return technology;
  }

  public int getBatteryPercent() {
    return batteryPercent;
  }

  public int getTemperature() {
    return temperature;
  }

  public int getVoltage() {
    return voltage;
  }

  public float getTemperatureResult() {
    return temperatureResult;
  }

  public long getCapacity() {
    return capacity;
  }

}