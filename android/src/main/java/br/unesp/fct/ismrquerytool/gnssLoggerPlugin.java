package br.unesp.fct.ismrquerytool;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "gnssLogger")
public class gnssLoggerPlugin extends Plugin {

  private gnssLogger implementation = new gnssLogger();

  @PluginMethod
  public void echo(PluginCall call) {
    String value = call.getString("value");

    JSObject ret = new JSObject();
    ret.put("value", implementation.echo(value));
    call.resolve(ret);
  }

  @PluginMethod
  public void startGNSS(PluginCall call) {
    String value = call.getString("value");

    JSObject ret = new JSObject();
    ret.put("value", implementation.startLog("Start Log", getContext()));
    call.resolve(ret);
  }

  @PluginMethod
  public void stopGNSS(PluginCall call) {
    String value = call.getString("value");

    JSObject ret = new JSObject();
    ret.put("value", implementation.stopLog("Stop Log"));
    call.resolve(ret);
  }
}
