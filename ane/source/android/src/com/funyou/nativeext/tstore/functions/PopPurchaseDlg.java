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

public class PopPurchaseDlg implements FREFunction {
	public static final String TAG = "PopPurchaseDlg";
	protected byte[] lock = new byte[0];
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		Log.d(TAG, "in call");
		try {
			String pID = args[0].getAsString();
			String pName = null;
			if(args[1]!=null){
				pName = args[1].getAsString();
			}
			String pTID = null;
			if(args[2]!=null){
				pTID = args[2].getAsString();
			}
			String pBPInfo = null;
			if(args[3]!=null){
				pBPInfo = args[3].getAsString();
			}
			
//			IAPExtension.purchasingPID = pID;
//			IAPExtension.purchasingPName = pName;
//			IAPExtension.purchasingPTID = pTID;
//			IAPExtension.purchasingPBPInfo = pBPInfo;
			
//			IAPExtension.purchasingStart = SystemClock.currentThreadTimeMillis();
			Intent in = new Intent(IAPExtension.context.getActivity(), TStoreIAPActivity.class);
			in.putExtra("commond", 1);
			in.putExtra("PID", pID);
			in.putExtra("pName", pName);
			in.putExtra("pTID", pTID);
			in.putExtra("pBPInfo", pBPInfo);
			IAPExtension.context.getActivity().startActivity(in);
//			IAPExtension.iap_activity.publicPopPurchaseDlg(pID, pName, pTID, pBPInfo);
		} catch (IllegalStateException e) {
//			IAPExtension.isPurchasing = false;
			Log.e(TAG, "PopPurchaseDlg ERROR", e);
		} catch (FRETypeMismatchException e) {
//			IAPExtension.isPurchasing = false;
			Log.e(TAG, "PopPurchaseDlg ERROR", e);
		} catch (FREInvalidObjectException e) {
//			IAPExtension.isPurchasing = false;
			Log.e(TAG, "PopPurchaseDlg ERROR", e);
		} catch (FREWrongThreadException e) {
//			IAPExtension.isPurchasing = false;
			Log.e(TAG, "PopPurchaseDlg ERROR", e);
		} catch (Exception e) {
//			IAPExtension.isPurchasing = false;
			Log.e(TAG, "ERROR", e);
		}
		return null;
	}

}
