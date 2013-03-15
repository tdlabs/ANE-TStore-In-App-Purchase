package com.funyou.nativeext.tstore;

import java.lang.reflect.Field;

import android.os.Bundle;
import android.util.Log;

import com.feelingk.iap.PwdActivity;

public class CPwdActivity extends PwdActivity {
	String TAG = "CPwdActivity";
	protected void onCreate(Bundle savedInstanceState){
		setPrivateValue("RES_VERT_FILE_PATH", "/assets/res/");
		super.onCreate(savedInstanceState);
	}
	private void setPrivateValue (String fieldName, Object value){
		Field field;
		try {
			field = getClass().getSuperclass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(this, value);
			field.setAccessible(false);
		} catch (NoSuchFieldException e) {
			Log.i(TAG, e.getMessage());
		} catch (IllegalArgumentException e) {
			Log.i(TAG, e.getMessage());
		} catch (IllegalAccessException e) {
			Log.i(TAG, e.getMessage());
		}
	}
}
