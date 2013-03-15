package com.funyou.nativeext.tstore.functions;

import android.content.Intent;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.funyou.nativeext.tstore.IAPExtension;
import com.funyou.nativeext.tstore.TStoreIAPActivity;

public class SendPurchaseDismiss implements FREFunction {
	public static final String TAG = "SendPurchaseDismiss";
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		Log.d(TAG, "in call");
		try {
			String pID = args[0].getAsString();
			String pName = args[1].getAsString();
			Intent in = new Intent(IAPExtension.context.getActivity(), TStoreIAPActivity.class);
			in.putExtra("commond", 5);
			in.putExtra("PID", pID);
			in.putExtra("pName", pName);
			IAPExtension.context.getActivity().startActivity(in);
		} catch (IllegalStateException e) {
			Log.e(TAG, "SendItemAuth ERROR", e);
		} catch (FRETypeMismatchException e) {
			Log.e(TAG, "SendItemAuth ERROR", e);
		} catch (FREInvalidObjectException e) {
			Log.e(TAG, "SendItemAuth ERROR", e);
		} catch (FREWrongThreadException e) {
			Log.e(TAG, "SendItemAuth ERROR", e);
		} catch (Exception e) {
			Log.e(TAG, "ERROR", e);
		}
		return null;
	}

}
