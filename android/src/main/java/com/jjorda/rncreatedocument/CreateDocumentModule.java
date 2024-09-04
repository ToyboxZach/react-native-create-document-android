package com.jjorda.rncreatedocument;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;

import com.facebook.react.bridge.Callback;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class CreateDocumentModule extends ReactContextBaseJavaModule {

  private ReactApplicationContext reactContext;
  private Callback mCallback;
  private static final int REQUEST_CODE = 800001;

  public CreateDocumentModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    this.reactContext.addActivityEventListener(mActivityEventListener);
  }

  @Override
  public String getName() {
    return "CreateDocumentModule";
  }

  @ReactMethod
  public void createDocument(String name, String type, Callback callback) {
    mCallback = callback;
    Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
    intent.addCategory(Intent.CATEGORY_OPENABLE);
    intent.setType(type);
    intent.putExtra(Intent.EXTRA_TITLE, name);
    Activity currentActivity = getCurrentActivity();
    currentActivity.startActivityForResult(intent, REQUEST_CODE);
  }

   public void pickDirectory(Callback callback) {
    mCallback = callback;
    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
    intent.addCategory(Intent.CATEGORY_OPENABLE);
    Activity currentActivity = getCurrentActivity();
    currentActivity.startActivityForResult(intent, REQUEST_CODE);
  }

  private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
      if (requestCode == REQUEST_CODE && data != null) {
        Uri uri = data.getData();
        mCallback.invoke(uri.toString());
      }
    }
  };

}
