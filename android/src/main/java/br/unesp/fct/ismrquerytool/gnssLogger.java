package br.unesp.fct.ismrquerytool;


import android.app.Activity;
import android.util.Log;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GnssMeasurement;
import android.location.GnssMeasurementsEvent;
import android.location.GnssStatus;
import android.location.GnssStatus.Callback;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

public class gnssLogger {

  private LocationManager locationManager;
  private GnssStatus.Callback gnssStatusListener;
  private GnssMeasurementsEvent.Callback gnssMeasurementsCallback;

  public String echo(String value) {
    Log.i("Echo", value);
    return value;
  }

  public String startLog(String value, Context context) {
    locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

    gnssStatusListener = new GnssStatus.Callback() {
      @Override
      public void onStarted() {
        Log.i("GNSSPlugin", "GNSS Started");
      }

      @Override
      public void onStopped() {
        Log.i("GNSSPlugin", "GNSS Stopped");
      }

      @Override
      public void onFirstFix(int ttffMillis) {
        Log.i("GNSSPlugin", "First Fix: " + ttffMillis + "ms");
      }

      @Override
      public void onSatelliteStatusChanged(GnssStatus status) {
        Log.i("GNSSPlugin", "Satellite Status Changed");
        // You can access satellite information from the 'status' object
      }
    };

    gnssMeasurementsCallback = new GnssMeasurementsEvent.Callback() {
      @Override
      public void onGnssMeasurementsReceived(GnssMeasurementsEvent event) {
        Log.i("GNSSPlugin", event.toString());
        // You can access raw GNSS measurements from the 'event' object
      }
    };

    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      return value;
    }

    locationManager.registerGnssStatusCallback(gnssStatusListener);
    locationManager.registerGnssMeasurementsCallback(gnssMeasurementsCallback);
    return value;
  }

  public String stopLog(String value) {
    if (locationManager != null) {
      locationManager.unregisterGnssStatusCallback(gnssStatusListener);
      locationManager.unregisterGnssMeasurementsCallback(gnssMeasurementsCallback);
    }
    return value;
  }
}
