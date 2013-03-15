package com.funyou.nativeext.tstore.functions;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;
import com.funyou.nativeext.tstore.IAPExtension;

public class GetError_messages implements FREFunction {
	public static final String TAG = "GetError_messages";
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		Log.d(TAG, "in call");
		FREObject returnedValue = null;
		try {
			returnedValue = FREObject.newObject(IAPExtension.error_messages);
		} catch (FREWrongThreadException e) {
			Log.e(TAG, "ERROR",e);
			returnedValue = null;
		} catch (Exception e) {
			Log.e(TAG, "GetError_messages ERROR", e);
		}
		return returnedValue;
	}

}
