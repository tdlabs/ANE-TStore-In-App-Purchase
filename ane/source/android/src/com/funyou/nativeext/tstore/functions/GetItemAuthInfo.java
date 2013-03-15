package com.funyou.nativeext.tstore.functions;

import java.io.UnsupportedEncodingException;

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

public class GetItemAuthInfo implements FREFunction {
	public static final String TAG = "GetItemAuthInfo";
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		Log.d(TAG, "in call");
		if(IAPExtension.itemAuthInfo!=null){
			FREObject[] c_args = new FREObject[3];
			try {
				c_args[0] = FREObject.newObject(IAPExtension.itemAuthInfo.pCount);
				if(IAPExtension.itemAuthInfo.pExpireDate!=null){
					c_args[1] = FREObject.newObject(new String(IAPExtension.itemAuthInfo.pExpireDate, "UTF-8"));
				}else {
					c_args[1] = null;
				}
				if(IAPExtension.itemAuthInfo.pToken!=null){
					c_args[2] = FREObject.newObject(new String(IAPExtension.itemAuthInfo.pToken, "UTF-8"));
				}else {
					c_args[2] = null;
				}
				return FREObject.newObject("com.funyou.nativeext.tstore.vo.ItemAuthInfo", c_args);
			} catch (FREWrongThreadException e) {
				Log.d(TAG, "ERROR", e);
			} catch (UnsupportedEncodingException e) {
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
