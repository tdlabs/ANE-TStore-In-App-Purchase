package com.funyou.nativeext.tstore.functions;

import android.util.Log;

import com.adobe.fre.FREASErrorException;
import com.adobe.fre.FREArray;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FRENoSuchNameException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.feelingk.iap.net.ItemAuth;
import com.funyou.nativeext.tstore.IAPExtension;

public class GetItemAuths implements FREFunction {
	public static final String TAG = "GetItemAuths";
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		
		Log.d(TAG, "in call");
		if(IAPExtension.itemAuths!=null){
			FREArray array;
			try {
				array = FREArray.newArray(IAPExtension.itemAuths.length);
				for(int i=0;i<IAPExtension.itemAuths.length;i++){
					array.setObjectAt(i, newItemAuth(IAPExtension.itemAuths[i]));
				}
				return array;
			} catch (IllegalStateException e) {
				Log.d(TAG, "ERROR", e);
			} catch (FREASErrorException e) {
				Log.d(TAG, "ERROR", e);
			} catch (FREWrongThreadException e) {
				Log.d(TAG, "ERROR", e);
			} catch (FREInvalidObjectException e) {
				Log.d(TAG, "ERROR", e);
			} catch (FRETypeMismatchException e) {
				Log.d(TAG, "ERROR", e);
			} catch (FRENoSuchNameException e) {
				Log.d(TAG, "ERROR", e);
			} catch (Exception e) {
				Log.e(TAG, "ERROR", e);
			}
		}
		return null;		
	}
	
	private FREObject newItemAuth(ItemAuth itemAuth) throws FREWrongThreadException, IllegalStateException, FRETypeMismatchException, FREInvalidObjectException, FREASErrorException, FRENoSuchNameException{
		FREObject[] c_args = new FREObject[2];
		c_args[0] = FREObject.newObject(itemAuth.pId);
		c_args[1] = FREObject.newObject(itemAuth.pName);
		return FREObject.newObject("com.funyou.nativeext.tstore.vo.ItemAuth", c_args);
	}

}
