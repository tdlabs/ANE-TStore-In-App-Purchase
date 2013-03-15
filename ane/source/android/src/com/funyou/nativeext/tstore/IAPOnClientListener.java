package com.funyou.nativeext.tstore;

import com.feelingk.iap.IAPLib;
import com.feelingk.iap.IAPLib.OnClientListener;
import com.feelingk.iap.net.ItemAuth;
import com.feelingk.iap.net.ItemAuthInfo;
import com.feelingk.iap.net.ItemUse;

public class IAPOnClientListener implements OnClientListener {
	private String eventName = "status_change";
	@Override
	public void onDlgAutoPurchaseInfoCancel() {
		IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onDlgAutoPurchaseInfoCancel);
	}

	@Override
	public void onDlgError() {
//		IAPExtension.isPurchasing = false;
		IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onDlgError);
	}

	@Override
	public void onDlgPurchaseCancel() {
//		IAPExtension.isPurchasing = false;
		IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onDlgPurchaseCancel);
	}

	@Override
	public void onError(int arg0, int arg1) {
//		IAPExtension.isPurchasing = false;
		  switch (arg0) {
          // Initialization error
          case IAPLib.HND_ERR_INIT:
        	  IAPExtension.error_messages = ErrorMessages.HND_ERR_INIT;
              break;
          // certification processing error
	        case IAPLib.HND_ERR_AUTH:
	        	  IAPExtension.error_messages = ErrorMessages.HND_ERR_INIT;
              break;
          // item purchase possible processing error
          case IAPLib.HND_ERR_ITEMQUERY:
        	  IAPExtension.error_messages = ErrorMessages.HND_ERR_ITEMQUERY;
              break;
           // item information reception error
          case IAPLib.HND_ERR_ITEMINFO:
        	  IAPExtension.error_messages = ErrorMessages.HND_ERR_ITEMINFO;
              break;
           // item charge processing error
          case IAPLib.HND_ERR_ITEMPURCHASE:
        	  IAPExtension.error_messages = ErrorMessages.HND_ERR_ITEMPURCHASE;
              break;
          }
		  IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onError);
	}

	@Override
	public void onItemAuthInfo(ItemAuthInfo itemAuthInfo) {
		IAPExtension.itemAuthInfo = itemAuthInfo;
		IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onItemAuthInfo);
	}

	@Override
	public void onItemPurchaseComplete() {
//		IAPExtension.isPurchasing = false;
		IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onItemPurchaseComplete);
	}

	@Override
	public Boolean onItemQueryComplete() {
		IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onItemQueryComplete);
		return true;
	}

	@Override
	public void onItemUseQuery(ItemUse itemUse) {
		IAPExtension.itemUse = itemUse;
		IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onItemUseQuery);
	}

	@Override
	public void onJoinDialogCancel() {
		IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onJoinDialogCancel);
	}

	@Override
	public void onPurchaseDismiss() {
		IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onPurchaseDismiss);
	}

	@Override
	public void onWholeQuery(ItemAuth[] itemAuth) {
		IAPExtension.itemAuths = itemAuth;
		IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onWholeQuery);
	}

}
