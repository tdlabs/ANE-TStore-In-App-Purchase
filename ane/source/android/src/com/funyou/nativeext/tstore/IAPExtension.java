package com.funyou.nativeext.tstore;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;
import com.feelingk.iap.net.ItemAuth;
import com.feelingk.iap.net.ItemAuthInfo;
import com.feelingk.iap.net.ItemUse;

public class IAPExtension implements FREExtension
{
	public static final String TAG = "IAPExtension";
	public static FREContext context;
	public static TStoreIAPActivity iap_activity;
	public static String error_messages;
	public static ItemAuthInfo itemAuthInfo;
	public static ItemUse itemUse;
	public static ItemAuth[] itemAuths;
//	public static boolean isPurchasing;
//	public static String purchasingPID;
//	public static String purchasingPName;
//	public static String purchasingPTID;
//	public static String purchasingPBPInfo;
//	public static long purchasingStart;
	

	@Override
	public FREContext createContext( String contextType )
	{
		Log.i(TAG, "Extension createContext.");
		context = new IAPExtensionContext();
		return context;
	}

	@Override
	public void dispose()
	{
		context = null;
		Log.i(TAG, "Extension disposed.");
	}

	@Override
	public void initialize()
	{
		Log.i(TAG, "Extension initialized.");
	}
}
