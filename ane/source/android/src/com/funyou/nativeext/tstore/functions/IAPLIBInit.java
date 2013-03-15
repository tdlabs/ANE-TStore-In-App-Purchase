package com.funyou.nativeext.tstore.functions;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.funyou.nativeext.tstore.TStoreIAPActivity;

public class IAPLIBInit implements FREFunction {
	public static final String TAG = "IAPLIBInit";
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		Log.d(TAG, "in call");
		try {
			String AppID;
			String BP_IP=null;
			int BP_Port=0;
			AppID = args[0].getAsString();
			if(args[1]!=null){
				BP_IP = args[1].getAsString();
			}
			if(args[2]!=null){
				BP_Port = args[2].getAsInt();
			}
			TStoreIAPActivity.AppID = AppID;
			TStoreIAPActivity.BP_IP = BP_IP;
			TStoreIAPActivity.BP_Port = BP_Port;
		} catch (IllegalStateException e1) {
			Log.e(TAG, "IAPLibInit ERROR", e1);
		} catch (FRETypeMismatchException e1) {
			Log.e(TAG, "IAPLibInit ERROR", e1);
		} catch (FREInvalidObjectException e1) {
			Log.e(TAG, "IAPLibInit ERROR", e1);
		} catch (FREWrongThreadException e1) {
			Log.e(TAG, "IAPLibInit ERROR", e1);
		} catch (Exception e) {
			Log.e(TAG, "IAPLibInit ERROR", e);
		}
		return null;
		
	}

}
