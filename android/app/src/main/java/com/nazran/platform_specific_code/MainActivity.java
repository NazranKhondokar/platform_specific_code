package com.nazran.platform_specific_code;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "flutter.native/helper";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);
    new MethodChannel(getFlutterView(), CHANNEL).setMethodCallHandler(
            new MethodChannel.MethodCallHandler() {
              @Override
              public void onMethodCall(MethodCall call, MethodChannel.Result result) {
                if (call.method.equals("helloFromNativeCode")) {

                  String greetings = helloFromNativeCode();
                  result.success(greetings);
                }
                if (call.method.equals("samples.flutter.io/contact")) {
                  result.success(listFromNativeCode());
                } else {
                  result.notImplemented();
                }
              }});
  }
  private String helloFromNativeCode() {
    return "Hello from Native Android Code";
  }
  private List<String> listFromNativeCode() {
    final List<String> list = new ArrayList<>();
    list.add("Phone number 1");
    list.add("Phone number 2");
    list.add("Phone number 3");
    return list;
  }
}
