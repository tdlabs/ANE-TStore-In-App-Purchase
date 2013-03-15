package com.funyou.nativeext.tstore.functions;

import android.util.Log;

import com.adobe.fre.FREASErrorException;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FRENoSuchNameException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.funyou.nativeext.tstore.IAPExtension;

public class GetPurchasedItem implements FREFunction {
	public static final String TAG = "GetPurchasedItem";
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		Log.d(TAG, "in call");
		FREObject[] c_args = new FREObject[4];
		try {
			if(IAPExtension.iap_activity.PID!=null){
				c_args[0] = FREObject.newObject(IAPExtension.iap_activity.PID);
			}
			if(IAPExtension.iap_activity.pName!=null){
				c_args[1] = FREObject.newObject(IAPExtension.iap_activity.pName);
			}
			if(IAPExtension.iap_activity.pTID!=null){
				c_args[2] = FREObject.newObject(IAPExtension.iap_activity.pTID);
			}
			if(IAPExtension.iap_activity.pBPInfo!=null){
				c_args[3] = FREObject.newObject(IAPExtension.iap_activity.pBPInfo);
			}
			return FREObject.newObject("com.funyou.nativeext.tstore.vo.PurchasedItem", c_args);
		} catch (FREWrongThreadException e) {
			Log.d(TAG, "ERROR", e);
		} catch (IllegalStateException e) {
			Log.d(TAG, "ERROR", e);
		} catch (FRETypeMismatchException e) {
			Log.d(TAG, "ERROR", e);
		} catch (FREInvalidObjectException e) {
			Log.d(TAG, "ERROR", e);
		} catch (FREASErrorException e) {
			Log.d(TAG, "ERROR", e);
		} catch (FRENoSuchNameException e) {
			Log.d(TAG, "ERROR", e);
		} catch (Exception e) {
			Log.e(TAG, "ERROR", e);
		}
		Log.i(TAG, "null return?");
		return null;
	}

}
