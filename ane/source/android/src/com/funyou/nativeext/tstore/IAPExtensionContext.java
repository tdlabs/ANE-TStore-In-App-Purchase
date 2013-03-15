package com.funyou.nativeext.tstore;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.funyou.nativeext.tstore.functions.Decrypt;
import com.funyou.nativeext.tstore.functions.Eecrypt;
import com.funyou.nativeext.tstore.functions.GetError_messages;
import com.funyou.nativeext.tstore.functions.GetItemAuthInfo;
import com.funyou.nativeext.tstore.functions.GetItemAuths;
import com.funyou.nativeext.tstore.functions.GetItemUse;
import com.funyou.nativeext.tstore.functions.GetPurchasedItem;
import com.funyou.nativeext.tstore.functions.IAPLIBInit;
import com.funyou.nativeext.tstore.functions.PopPurchaseDlg;
import com.funyou.nativeext.tstore.functions.SendItemAuth;
import com.funyou.nativeext.tstore.functions.SendItemUse;
import com.funyou.nativeext.tstore.functions.SendItemWholeAuth;
import com.funyou.nativeext.tstore.functions.SendPurchaseDismiss;

public class IAPExtensionContext extends FREContext
{
	public static final String TAG = "IAPExtensionContext";
	@Override
	public void dispose() {
		Log.d(TAG, "IAPExtensionContext.dispose");
		IAPExtension.context = null;
	}
	public Activity getActivity() throws IllegalStateException{
			    Log.i("AndroidDialogExtensionContext", "getActivity.........");
		return super.getActivity();
	}
	@Override
	public Map<String, FREFunction> getFunctions()
	{
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
		functionMap.put( "IAPLIBInit", new IAPLIBInit() );
		functionMap.put( "popPurchaseDlg", new PopPurchaseDlg() );
		functionMap.put( "sendItemAuth", new SendItemAuth() );
		functionMap.put( "sendItemWholeAuth", new SendItemWholeAuth() );
		functionMap.put( "sendItemUse", new SendItemUse() );
		functionMap.put( "sendPurchaseDismiss", new SendPurchaseDismiss() );
		functionMap.put( "getError_messages", new GetError_messages() );
		functionMap.put( "getItemAuthInfo", new GetItemAuthInfo() );
		functionMap.put( "getItemUse", new GetItemUse());
		functionMap.put( "getItemAuths", new GetItemAuths());
		functionMap.put( "getPurchasedItem", new GetPurchasedItem() );
		functionMap.put( "encrypt", new Eecrypt() );
		functionMap.put( "decrypt", new Decrypt() );
		Log.d(TAG, "IAPExtensionContext.getFunctions");
		return functionMap;
	}

}
