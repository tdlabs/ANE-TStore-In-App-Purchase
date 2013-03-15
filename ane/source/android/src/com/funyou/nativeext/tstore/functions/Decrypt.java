package com.funyou.nativeext.tstore.functions;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.feelingk.iap.encryption.CryptoManager;

public class Decrypt implements FREFunction {
	public static final String TAG = "Decrypt";
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		Log.d(TAG, "in call");
		try {
			String encrypted = args[0].getAsString();
			String key = null;
			if(args[1]!=null){
				key = args[1].getAsString();
			}
			if(key==null){
				return FREObject.newObject(CryptoManager.decrypt(encrypted));
			}else {
				return FREObject.newObject(CryptoManager.decrypt(key, encrypted));
			}
		} catch (IllegalStateException e1) {
			Log.e(TAG, "Decrypt ERROR", e1);
		} catch (FRETypeMismatchException e1) {
			Log.e(TAG, "Decrypt ERROR", e1);
		} catch (FREInvalidObjectException e1) {
			Log.e(TAG, "Decrypt ERROR", e1);
		} catch (FREWrongThreadException e1) {
			Log.e(TAG, "Decrypt ERROR", e1);
		} catch (Exception e1) {
			Log.e(TAG, "Decrypt ERROR", e1);
		}
		return null;
	}

}
