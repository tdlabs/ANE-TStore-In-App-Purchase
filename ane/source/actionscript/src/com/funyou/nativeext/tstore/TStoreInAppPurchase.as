package com.funyou.nativeext.tstore
{
	import com.funyou.nativeext.signal.Signal0;
	import com.funyou.nativeext.signal.Signal1;
	import com.funyou.nativeext.tstore.vo.ItemAuthInfo;
	import com.funyou.nativeext.tstore.vo.ItemUse;
	import com.funyou.nativeext.tstore.vo.PurchasedItem;
	
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;

	public class TStoreInAppPurchase
	{
		public function TStoreInAppPurchase()
		{
		}
		private static var extensionContext : ExtensionContext = null;
		private static var initialised : Boolean = false;
		public static var onDlgAutoPurchaseInfoCancel:Signal0 = new Signal0();
		public static var onDlgError:Signal0 = new Signal0();
		public static var onDlgPurchaseCancel:Signal0 = new Signal0();
		
		public static var onItemPurchaseComplete:Signal1 = new Signal1(PurchasedItem);
		
		public static var onItemAuthInfo:Signal1 = new Signal1(ItemAuthInfo);
		public static var onError:Signal1 = new Signal1(String);
		public static var onItemUseQuery:Signal1 = new Signal1(ItemUse);
		public static var onItemQueryComplete:Signal0 = new Signal0();
		public static var onJoinDialogCancel:Signal0 = new Signal0();
		public static var onPurchaseDismiss:Signal0 = new Signal0();
		public static var onWholeQuery:Signal1 = new Signal1(Array);
		
//		public static var version:String = "debug";
//		public static var version:String = "debug-eng";
//		public static var version:String = "release";
		private static function init() : void
		{
			if ( !initialised )
			{
				initialised = true;
				extensionContext = ExtensionContext.createExtensionContext( "com.funyou.nativeext.tstore.IAP", null );
				if(extensionContext){
					_isSupported = true;
					extensionContext.addEventListener( StatusEvent.STATUS, handleStatusEvent );
				}else {
					trace("not supported");
				}
				
			}
		}
		public static var _isSupported:Boolean;
		public static function get isSupported():Boolean {
			return _isSupported;
		}
		public static function encrypt(cleartext:String,key:String=null) : String{
			return extensionContext.call(NativeMethods.encrypt, cleartext, key) as String;
		}
		public static function decrypt(encrypted:String,key:String=null) : String{
			return extensionContext.call(NativeMethods.decrypt, encrypted, key) as String;
		}
		public static function initIAPLIB(appId:String, bp_ip:String=null, bp_port:String=null):void {
			init();
			if(_isSupported){
				extensionContext.call( NativeMethods.IAPLIBInit, appId, bp_ip, bp_port);
			}
		}
		public static function popPurchaseDlg(pID:String, pName:String=null, pTID:String=null, pBPInfo:String=null ) : void{
			extensionContext.call(NativeMethods.popPurchaseDlg, pID, pName, pTID, pBPInfo);
		}
		public static function sendItemAuth(pID:String) : void{
			extensionContext.call(NativeMethods.sendItemAuth, pID);
		}
		public static function sendItemWholeAuth() : void{
			extensionContext.call(NativeMethods.sendItemWholeAuth);
		}
		public static function sendItemUse(pID:String) : void{
			extensionContext.call(NativeMethods.sendItemUse, pID);
		}
		public static function sendPurchaseDismiss(pID:String, pName:String) : void{
			extensionContext.call(NativeMethods.sendPurchaseDismiss, pID, pName);
		}
		private static function getError_messages():String{
			return extensionContext.call(NativeMethods.getError_messages) as String;
		}
		private static function getItemAuthInfo():ItemAuthInfo{
			return extensionContext.call(NativeMethods.getItemAuthInfo) as ItemAuthInfo;
		}
		private static function getItemAuths():Array{
			return extensionContext.call(NativeMethods.getItemAuths) as Array;
		}
		private static function getItemUse():ItemUse{
			return extensionContext.call(NativeMethods.getItemUse) as ItemUse;
		}
		private static function getPurchasedItem():PurchasedItem {
			return extensionContext.call(NativeMethods.getPurchasedItem) as PurchasedItem;
		}
		public static function dispose():void { 
			if(extensionContext.hasEventListener(StatusEvent.STATUS)){
				extensionContext.removeEventListener( StatusEvent.STATUS, handleStatusEvent );
			}
			extensionContext.dispose();
		}
		private static function handleStatusEvent( event : StatusEvent ) : void
		{
			switch( event.level )
			{
				case InternalMessages.onDlgAutoPurchaseInfoCancel :
					onDlgAutoPurchaseInfoCancel.dispatch();
					break;
				case InternalMessages.onDlgError :
					onDlgError.dispatch();
					break;
				case InternalMessages.onDlgPurchaseCancel :
					onDlgPurchaseCancel.dispatch();
					break;
				case InternalMessages.onError:
					var error:String = getError_messages();
					onError.dispatch(error)
					break;
				case InternalMessages.onItemAuthInfo :
					var itemAuthInfo:ItemAuthInfo = getItemAuthInfo();
					onItemAuthInfo.dispatch(itemAuthInfo);
					break;
				case InternalMessages.onItemPurchaseComplete :
					var purchasedItem:PurchasedItem = getPurchasedItem();
					onItemPurchaseComplete.dispatch(purchasedItem);
					break;
				case InternalMessages.onItemQueryComplete :
					onItemQueryComplete.dispatch();
					break;
				case InternalMessages.onItemUseQuery :
					var itemUse:ItemUse = getItemUse();
					onItemUseQuery.dispatch(itemUse);
					break;
				case InternalMessages.onJoinDialogCancel :
					onJoinDialogCancel.dispatch();
					break;
				case InternalMessages.onPurchaseDismiss :
					onPurchaseDismiss.dispatch();
					break;
				case InternalMessages.onWholeQuery :
					var itemAuths:Array = getItemAuths();
					onWholeQuery.dispatch(itemAuths);
					break;
			}
		}
	}
}