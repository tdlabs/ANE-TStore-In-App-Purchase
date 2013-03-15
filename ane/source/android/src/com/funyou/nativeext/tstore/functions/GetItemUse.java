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

public class GetItemUse implements FREFunction {
	public static final String TAG = "GetItemUse";
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		Log.d(TAG, "in call");
		if(IAPExtension.itemUse!=null){
			FREObject[] c_args = new FREObject[3];
			try {
				c_args[0] = FREObject.newObject(IAPExtension.itemUse.pCount);
				c_args[1] = FREObject.newObject(IAPExtension.itemUse.pId);
				c_args[2] = FREObject.newObject(IAPExtension.itemUse.pName);
				return FREObject.newObject("com.funyou.nativeext.tstore.vo.ItemUse", c_args);
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
		}
		return null;
	}

}
