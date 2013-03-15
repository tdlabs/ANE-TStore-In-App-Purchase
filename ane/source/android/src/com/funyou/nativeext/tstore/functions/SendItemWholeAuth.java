package com.funyou.nativeext.tstore.functions;

import android.content.Intent;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.funyou.nativeext.tstore.IAPExtension;
import com.funyou.nativeext.tstore.TStoreIAPActivity;

public class SendItemWholeAuth implements FREFunction {
	public static final String TAG = "SendItemWholeAuth";
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		Log.d(TAG, "in call");
		Intent in = new Intent(IAPExtension.context.getActivity(), TStoreIAPActivity.class);
		in.putExtra("commond", 4);
		IAPExtension.context.getActivity().startActivity(in);
		return null;
	}

}
