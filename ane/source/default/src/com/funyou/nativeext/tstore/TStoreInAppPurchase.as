package com.funyou.nativeext.tstore
{
	import com.funyou.nativeext.signal.Signal0;
	import com.funyou.nativeext.signal.Signal1;
	import com.funyou.nativeext.tstore.vo.ItemAuthInfo;
	import com.funyou.nativeext.tstore.vo.ItemUse;
	import com.funyou.nativeext.tstore.vo.PurchasedItem;
	import flash.events.StatusEvent;
	public class TStoreInAppPurchase
	{
		public function TStoreInAppPurchase()
		{
		}
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
		
		private static function init() : void
		{
			if ( !initialised )
			{
				initialised = true;
				_isSupported = false;
				
			}
		}
		public static var _isSupported:Boolean;
		public static function get isSupported():Boolean {
			return _isSupported;
		}
		public static function encrypt(cleartext:String,key:String=null) : String{
			return null;
		}
		public static function decrypt(encrypted:String,key:String=null) : String{
			return null;
		}
		public static function initIAPLIB(appId:String, bp_ip:String=null, bp_port:String=null):void {
			init();
			
		}
		public static function popPurchaseDlg(pID:String, pName:String=null, pTID:String=null, pBPInfo:String=null ) : void{
			
		}
		public static function sendItemAuth(pID:String) : void{
			
		}
		public static function sendItemWholeAuth() : void{
			
		}
		public static function sendItemUse(pID:String) : void{
			
		}
		public static function sendPurchaseDismiss(pID:String, pName:String) : void{
			
		}
		private static function getError_messages():String{
			return null;
		}
		private static function getItemAuthInfo():ItemAuthInfo{
			return null;
		}
		private static function getItemAuths():Array{
			return null;
		}
		private static function getItemUse():ItemUse{
			return null;
		}
		private static function getPurchasedItem():PurchasedItem {
			return null;
		}
		public static function dispose():void { 
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