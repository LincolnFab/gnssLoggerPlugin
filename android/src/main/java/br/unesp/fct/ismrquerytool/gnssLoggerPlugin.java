package br.unesp.fct.ismrquerytool;

// Android location library
import android.location.GnssMeasurement;
import android.location.GnssMeasurementsEvent;
import android.location.GnssStatus;
import android.location.LocationManager;

// Capacitor libraries
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

// Android libraries
import android.util.Log;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

// Object utils
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Time util
import java.util.concurrent.TimeUnit;

@CapacitorPlugin(name = "gnssLogger")
public class gnssLoggerPlugin extends Plugin {

  private LocationManager locationManager;
  private GnssStatus.Callback gnssStatusListener;
  private GnssMeasurementsEvent.Callback gnssMeasurementsCallback;

  private gnssLogger implementation = new gnssLogger();

  @PluginMethod
  public void startGNSS(PluginCall call) {
    Context context = getContext();
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
        //Log.i("Measurement", event.toString());

        JSONArray measurementsArray = new JSONArray();
        for (GnssMeasurement measurement : event.getMeasurements()) {
          JSONObject measurementObj = new JSONObject();

          try {
            measurementObj.put("svid", measurement.getSvid());
            measurementObj.put("constellationType", measurement.getConstellationType());
            measurementObj.put("cn0DbHz", measurement.getCn0DbHz());
            measurementObj.put("timeUnix", TimeUnit.NANOSECONDS.toSeconds(event.getClock().getTimeNanos()));
            measurementsArray.put(measurementObj);
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }

        JSObject result = new JSObject();
        result.put("value", measurementsArray);
        notifyListeners("gnssDataListenner", result);
      }
    };

    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
      locationManager.registerGnssStatusCallback(gnssStatusListener);
      locationManager.registerGnssMeasurementsCallback(gnssMeasurementsCallback);

      JSObject ret = new JSObject();
      ret.put("value", "Log started");
      call.resolve(ret);
    }

    JSObject ret = new JSObject();
    ret.put("value", "Error to start log");
    call.resolve(ret);
  }

  @PluginMethod
  public void stopGNSS(PluginCall call) {
    JSObject ret = new JSObject();

    if (locationManager != null) {
      locationManager.unregisterGnssStatusCallback(gnssStatusListener);
      locationManager.unregisterGnssMeasurementsCallback(gnssMeasurementsCallback);

      ret.put("value", "Log stopped");
      call.resolve(ret);
    }

    ret.put("value", "Error to stop log");
    call.resolve(ret);
  }
}
