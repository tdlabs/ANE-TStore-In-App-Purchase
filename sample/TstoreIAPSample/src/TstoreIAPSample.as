package
{
	
	import com.funyou.nativeext.tstore.TStoreInAppPurchase;
	import com.funyou.nativeext.tstore.vo.ItemAuth;
	import com.funyou.nativeext.tstore.vo.ItemAuthInfo;
	import com.funyou.nativeext.tstore.vo.ItemUse;
	import com.funyou.nativeext.tstore.vo.PurchasedItem;
	
	import flash.display.Shape;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.text.TextField;
	import flash.text.TextFormat;
	import flash.text.TextFormatAlign;
	
	[SWF(width='320', height='480', frameRate='30', backgroundColor='#000000')]
	public class TstoreIAPSample extends Sprite
	{
		private var direction : int = 1;
		private var shape : Shape;
		private var feedback : TextField;
		
		private var buttonFormat : TextFormat;
		public function TstoreIAPSample()
		{
			shape = new Shape();
			shape.graphics.beginFill( 0x666666 );
			shape.graphics.drawCircle( 0, 0, 100 );
			shape.graphics.endFill();
			shape.x = 0;
			shape.y = 240;
			addChild( shape );
			
			feedback = new TextField();
			var format : TextFormat = new TextFormat();
			format.font = "_sans";
			format.size = 16;
			format.color = 0xFFFFFF;
			feedback.defaultTextFormat = format;
			feedback.width = 320;
			feedback.height = 220;
			feedback.x = 10;
			feedback.y = 250;
			feedback.multiline = true;
			feedback.wordWrap = true;
			feedback.text = "Hello";
			addChild( feedback );
			addEventListener( Event.ENTER_FRAME, animate );
			createButtons();
		}
		private var appId:String = "應用id";
		private var bp_ip:String = null;
		private var bp_port:String = null;
		
		private var pID:String = "測試用的商品id";
		private var pID_invalid:String = "測試用的無效商品id";
		private var pName:String = "item1";
		private var pTID:String = "tidD";
		private var pBPInfo:String = "helpful item";
		private function IAPLIBInit(evt:MouseEvent):void {
			feedback.text = "初始化..";
			try {
				TStoreInAppPurchase.initIAPLIB(appId, bp_ip, bp_port);
				
				if(TStoreInAppPurchase.isSupported){
					inited = true;
					feedback.text = "初始化完毕";
				}else {
					inited = false;
					feedback.text = "初始化未完毕";
				}
				
			}catch (e:Error){
				feedback.text = e.getStackTrace();
			}
			
		}
		
		/**
		 * 
		 * 接口 popPurchaseDlg 测试
		 */ 
		private function popPurchaseDlg(evt:MouseEvent):void {
			if(!inited){
				feedback.text = "先点击IAPLIBInit";
				return;
			}
			feedback.text = "popPurchaseDlg";
			TStoreInAppPurchase.popPurchaseDlg(pID, pName, pTID, pBPInfo);
			TStoreInAppPurchase.onItemQueryComplete.add(popPurchaseDlg_onItemQueryComplete);
			TStoreInAppPurchase.onItemPurchaseComplete.add(popPurchaseDlg_onItemPurchaseComplete);
			TStoreInAppPurchase.onDlgPurchaseCancel.add(popPurchaseDlg_onDlgPurchaseCancel);
			TStoreInAppPurchase.onError.add(popPurchaseDlg_onError);
			TStoreInAppPurchase.onDlgError.add(popPurchaseDlg_onDlgError);
		}
		
		private function popPurchaseDlg_onItemQueryComplete() : void
		{
			feedback.appendText( "\n  onItemQueryComplete" );
		}
		private function popPurchaseDlg_onItemPurchaseComplete(purchasedItem:PurchasedItem) : void
		{
			TStoreInAppPurchase.onItemQueryComplete.remove(popPurchaseDlg_onItemQueryComplete);
			TStoreInAppPurchase.onItemPurchaseComplete.remove(popPurchaseDlg_onItemPurchaseComplete);
			TStoreInAppPurchase.onDlgPurchaseCancel.remove(popPurchaseDlg_onDlgPurchaseCancel);
			TStoreInAppPurchase.onError.remove(popPurchaseDlg_onError);
			TStoreInAppPurchase.onDlgError.remove(popPurchaseDlg_onDlgError);
			
			feedback.appendText( "\n onItemPurchaseComplete" );
			
			if(purchasedItem){
				feedback.appendText( "\n  PurchasedItem:--------");
				feedback.appendText( "\n  pID:"+ purchasedItem.pID);
				feedback.appendText( "\n  pName:"+ purchasedItem.pName);
				feedback.appendText( "\n  pTID:"+ purchasedItem.pTID);
				feedback.appendText( "\n  pBPInfo:"+ purchasedItem.pBPInfo);
			}else {
				feedback.appendText( "\n  PurchasedItem: null");
			}
			
		}
		private function popPurchaseDlg_onDlgPurchaseCancel() : void
		{
			TStoreInAppPurchase.onItemQueryComplete.remove(popPurchaseDlg_onItemQueryComplete);
			TStoreInAppPurchase.onItemPurchaseComplete.remove(popPurchaseDlg_onItemPurchaseComplete);
			TStoreInAppPurchase.onDlgPurchaseCancel.remove(popPurchaseDlg_onDlgPurchaseCancel);
			TStoreInAppPurchase.onError.remove(popPurchaseDlg_onError);
			TStoreInAppPurchase.onDlgError.remove(popPurchaseDlg_onDlgError);
			
			feedback.appendText( "\n  popPurchaseDlg_onDlgPurchaseCancel" );
		}
		private function popPurchaseDlg_onError(error:String) : void
		{
			TStoreInAppPurchase.onItemQueryComplete.remove(popPurchaseDlg_onItemQueryComplete);
			TStoreInAppPurchase.onItemPurchaseComplete.remove(popPurchaseDlg_onItemPurchaseComplete);
			TStoreInAppPurchase.onDlgPurchaseCancel.remove(popPurchaseDlg_onDlgPurchaseCancel);
			TStoreInAppPurchase.onError.remove(popPurchaseDlg_onError);
			TStoreInAppPurchase.onDlgError.remove(popPurchaseDlg_onDlgError);
			
			feedback.appendText( "\n  onError"+error );
		}
		private function popPurchaseDlg_onDlgError() : void
		{
			TStoreInAppPurchase.onItemQueryComplete.remove(popPurchaseDlg_onItemQueryComplete);
			TStoreInAppPurchase.onItemPurchaseComplete.remove(popPurchaseDlg_onItemPurchaseComplete);
			TStoreInAppPurchase.onDlgPurchaseCancel.remove(popPurchaseDlg_onDlgPurchaseCancel);
			TStoreInAppPurchase.onError.remove(popPurchaseDlg_onError);
			TStoreInAppPurchase.onDlgError.remove(popPurchaseDlg_onDlgError);
			
			feedback.appendText( "\n  onDlgError" );
		}
		
		/**
		 * 
		 * 接口 sendItemAuth 测试，传入正确pID
		 */ 
		private function sendItemAuth(evt:MouseEvent):void {
			if(!inited){
				feedback.text = "先点击IAPLIBInit";
				return;
			}
			feedback.text = "sendItemAuth";
			TStoreInAppPurchase.sendItemAuth(pID);
			
			
			TStoreInAppPurchase.onItemAuthInfo.add(sendItemAuth_onItemAuthInfo);
			TStoreInAppPurchase.onError.add(sendItemAuth_onError);
			TStoreInAppPurchase.onDlgError.add(sendItemAuth_onDlgError);
			
		}
		private function sendItemAuth_onItemAuthInfo(itemAuthInfo:ItemAuthInfo) : void
		{
			TStoreInAppPurchase.onItemAuthInfo.remove(sendItemAuth_onItemAuthInfo);
			TStoreInAppPurchase.onError.remove(sendItemAuth_onError);
			TStoreInAppPurchase.onDlgError.remove(sendItemAuth_onDlgError);
			
			feedback.appendText( "\n  onItemAuthInfo" );
			if(itemAuthInfo){
				feedback.appendText( "\n  ItemAuthInfo:--------");
				feedback.appendText( "\n  pCount:"+ itemAuthInfo.pCount);
				feedback.appendText( "\n  pExpireDate:"+ itemAuthInfo.pExpireDate);
				feedback.appendText( "\n  pToken:"+ itemAuthInfo.pToken);
			}else {
				feedback.appendText( "\n  ItemAuthInfo: null");
			}
		}
		private function sendItemAuth_onError(error:String) : void
		{
			TStoreInAppPurchase.onItemAuthInfo.remove(sendItemAuth_onItemAuthInfo);
			TStoreInAppPurchase.onError.remove(sendItemAuth_onError);
			TStoreInAppPurchase.onDlgError.remove(sendItemAuth_onDlgError);
			
			feedback.appendText( "\n  onError:"+error );
		}
		private function sendItemAuth_onDlgError() : void
		{
			TStoreInAppPurchase.onItemAuthInfo.remove(sendItemAuth_onItemAuthInfo);
			TStoreInAppPurchase.onError.remove(sendItemAuth_onError);
			TStoreInAppPurchase.onDlgError.remove(sendItemAuth_onDlgError);
			
			feedback.appendText( "\n  onDlgError" );
		}
		/**
		 * 
		 * 接口 sendItemAuth 测试，传入错误pID
		 */ 
		private function sendItemAuthInvalid(evt:MouseEvent):void {
			if(!inited){
				feedback.text = "先点击IAPLIBInit";
				return;
			}
			feedback.text = "sendItemAuthInvalid";
			TStoreInAppPurchase.sendItemAuth(pID_invalid);
			TStoreInAppPurchase.onItemAuthInfo.add(sendItemAuth_onItemAuthInfo);
			TStoreInAppPurchase.onError.add(sendItemAuth_onError);
			TStoreInAppPurchase.onDlgError.add(sendItemAuth_onDlgError);
		}
		
		/**
		 * 
		 * 接口 sendItemWholeAuth 测试
		 */ 
		private function sendItemWholeAuth(evt:MouseEvent):void {
			if(!inited){
				feedback.text = "先点击IAPLIBInit";
				return;
			}
			feedback.text = "sendItemWholeAuth";
			TStoreInAppPurchase.sendItemWholeAuth();
			
			TStoreInAppPurchase.onWholeQuery.add(sendItemWholeAuth_onWholeQuery);
			TStoreInAppPurchase.onError.add(sendItemWholeAuth_onError);
			TStoreInAppPurchase.onDlgError.add(sendItemWholeAuth_onDlgError);
		}
		private function sendItemWholeAuth_onWholeQuery(itemAuths:Array) : void
		{
			TStoreInAppPurchase.onWholeQuery.remove(sendItemWholeAuth_onWholeQuery);
			TStoreInAppPurchase.onError.remove(sendItemWholeAuth_onError);
			TStoreInAppPurchase.onDlgError.remove(sendItemWholeAuth_onDlgError);
			
			feedback.appendText( "\n  onWholeQuery" );
			if(itemAuths&&itemAuths.length>0){
				for(var i:int=0;i<itemAuths.length;i++){
					var itemAuth:ItemAuth = itemAuths[i];
					feedback.appendText( "\n  ItemAuth:---------");
					feedback.appendText( "\n  pId:"+ itemAuth.pId);
					feedback.appendText( "\n  pName:"+ itemAuth.pName);
				}
				
			}else {
				feedback.appendText( "\n  ItemAuths: null");
			}
		}
		private function sendItemWholeAuth_onError(error:String) : void
		{
			TStoreInAppPurchase.onWholeQuery.remove(sendItemWholeAuth_onWholeQuery);
			TStoreInAppPurchase.onError.remove(sendItemWholeAuth_onError);
			TStoreInAppPurchase.onDlgError.remove(sendItemWholeAuth_onDlgError);
			
			feedback.appendText( "\n  onError:"+error );
		}
		private function sendItemWholeAuth_onDlgError() : void
		{
			TStoreInAppPurchase.onWholeQuery.remove(sendItemWholeAuth_onWholeQuery);
			TStoreInAppPurchase.onError.remove(sendItemWholeAuth_onError);
			TStoreInAppPurchase.onDlgError.remove(sendItemWholeAuth_onDlgError);
			
			feedback.appendText( "\n  onDlgError" );
		}
		/**
		 * 
		 * 接口 sendItemUse 测试 正确pID;
		 */ 
		private function sendItemUse(evt:MouseEvent):void {
			if(!inited){
				feedback.text = "先点击IAPLIBInit";
				return;
			}
			
			feedback.text = "sendItemUse";
			TStoreInAppPurchase.sendItemUse(pID);
			
			TStoreInAppPurchase.onItemUseQuery.add(sendItemUse_onItemUseQuery);
			TStoreInAppPurchase.onError.add(sendItemUse_onError);
			TStoreInAppPurchase.onDlgError.add(sendItemUse_onDlgError);
		}
		private function sendItemUse_onItemUseQuery(itemUse:ItemUse) : void
		{
			TStoreInAppPurchase.onItemUseQuery.remove(sendItemUse_onItemUseQuery);
			TStoreInAppPurchase.onError.remove(sendItemUse_onError);
			TStoreInAppPurchase.onDlgError.remove(sendItemUse_onDlgError);
			
			feedback.appendText( "\n  onItemUseQuery" );
			if(itemUse){
				feedback.appendText( "\n  ItemUse:--------");
				feedback.appendText( "\n  pCount:"+ itemUse.pCount);
				feedback.appendText( "\n  pId:"+ itemUse.pId);
				feedback.appendText( "\n  pName:"+ itemUse.pName);
			}else {
				feedback.appendText( "\n  ItemUse: null");
			}
		}
		private function sendItemUse_onError(error:String) : void
		{
			TStoreInAppPurchase.onItemUseQuery.remove(sendItemUse_onItemUseQuery);
			TStoreInAppPurchase.onError.remove(sendItemUse_onError);
			TStoreInAppPurchase.onDlgError.remove(sendItemUse_onDlgError);
			
			feedback.appendText( "\n  onError:"+error );
		}
		private function sendItemUse_onDlgError() : void
		{
			TStoreInAppPurchase.onItemUseQuery.remove(sendItemUse_onItemUseQuery);
			TStoreInAppPurchase.onError.remove(sendItemUse_onError);
			TStoreInAppPurchase.onDlgError.remove(sendItemUse_onDlgError);
			
			feedback.appendText( "\n  onDlgError" );
		}
		
		/**
		 * 
		 * 接口 sendItemUse 测试 错误pID;
		 */
		private function sendItemUseInvalid(evt:MouseEvent):void {
			if(!inited){
				feedback.text = "先点击IAPLIBInit";
				return;
			}
			feedback.text = "sendItemUseInvalid";
			TStoreInAppPurchase.sendItemUse(pID_invalid);
			TStoreInAppPurchase.onItemUseQuery.add(sendItemUse_onItemUseQuery);
			TStoreInAppPurchase.onError.add(sendItemUse_onError);
			TStoreInAppPurchase.onDlgError.add(sendItemUse_onDlgError);
		}
		/**
		 * 
		 * 接口 sendPurchaseDismiss
		 */
		private function sendPurchaseDismiss(evt:MouseEvent):void {
			if(!inited){
				feedback.text = "先点击IAPLIBInit";
				return;
			}
			feedback.text = "sendPurchaseDismiss";
			TStoreInAppPurchase.sendPurchaseDismiss(pID, pName);
			TStoreInAppPurchase.onPurchaseDismiss.add(sendPurchaseDismiss_onPurchaseDismiss);
			TStoreInAppPurchase.onDlgAutoPurchaseInfoCancel.add(sendPurchaseDismiss_onDlgAutoPurchaseInfoCancel);
			TStoreInAppPurchase.onError.add(sendPurchaseDismiss_onError);
			TStoreInAppPurchase.onDlgError.add(sendPurchaseDismiss_onDlgError);
		}
		
		private function sendPurchaseDismiss_onPurchaseDismiss() : void
		{
			TStoreInAppPurchase.onPurchaseDismiss.remove(sendPurchaseDismiss_onPurchaseDismiss);
			TStoreInAppPurchase.onDlgAutoPurchaseInfoCancel.remove(sendPurchaseDismiss_onDlgAutoPurchaseInfoCancel);
			TStoreInAppPurchase.onError.remove(sendPurchaseDismiss_onError);
			TStoreInAppPurchase.onDlgError.remove(sendPurchaseDismiss_onDlgError);
			
			feedback.appendText( "\n  onPurchaseDismiss" );
		}
		private function sendPurchaseDismiss_onDlgAutoPurchaseInfoCancel() : void
		{
			TStoreInAppPurchase.onPurchaseDismiss.remove(sendPurchaseDismiss_onPurchaseDismiss);
			TStoreInAppPurchase.onDlgAutoPurchaseInfoCancel.remove(sendPurchaseDismiss_onDlgAutoPurchaseInfoCancel);
			TStoreInAppPurchase.onError.remove(sendPurchaseDismiss_onError);
			TStoreInAppPurchase.onDlgError.remove(sendPurchaseDismiss_onDlgError);
			
			feedback.appendText( "\n  onDlgAutoPurchaseInfoCancel" );
		}
		private function sendPurchaseDismiss_onError(error:String) : void
		{
			TStoreInAppPurchase.onPurchaseDismiss.remove(sendPurchaseDismiss_onPurchaseDismiss);
			TStoreInAppPurchase.onDlgAutoPurchaseInfoCancel.remove(sendPurchaseDismiss_onDlgAutoPurchaseInfoCancel);
			TStoreInAppPurchase.onError.remove(sendPurchaseDismiss_onError);
			TStoreInAppPurchase.onDlgError.remove(sendPurchaseDismiss_onDlgError);
			
			feedback.appendText( "\n  onError: " +error);
		}
		private function sendPurchaseDismiss_onDlgError() : void
		{
			TStoreInAppPurchase.onPurchaseDismiss.remove(sendPurchaseDismiss_onPurchaseDismiss);
			TStoreInAppPurchase.onDlgAutoPurchaseInfoCancel.remove(sendPurchaseDismiss_onDlgAutoPurchaseInfoCancel);
			TStoreInAppPurchase.onError.remove(sendPurchaseDismiss_onError);
			TStoreInAppPurchase.onDlgError.remove(sendPurchaseDismiss_onDlgError);
			
			feedback.appendText( "\n  onDlgError" );
		}
		
		
		protected var inited:Boolean;
		protected var cleartext:String = "cleartextvalue";
		protected var key:String = "123456";
		protected var encrypted:String = "maybe here need other formated text";
		/**
		 * 
		 * 接口 encrypt
		 */
		private function encrypt(evt:MouseEvent):void {
			if(!inited){
				feedback.text = "先点击IAPLIBInit";
				return;
			}
			feedback.text = "encrypt";
			var encryptValue:String = TStoreInAppPurchase.encrypt(cleartext, key);	
			feedback.appendText( "\n  "+encryptValue );
		}
		/**
		 * 
		 * 接口 encrypt
		 */
		private function decrypt(evt:MouseEvent):void {
			if(!inited){
				feedback.text = "先点击IAPLIBInit";
				return;
			}
			feedback.text = "decrypt";
			var decryptValue:String = TStoreInAppPurchase.decrypt(encrypted, key);	
			feedback.appendText( "\n  "+decryptValue );
		}
		private function createButtons() : void
		{
			var tf : TextField = createButton( "IAPLIBInit" );
			tf.x = 10;
			tf.y = 10;
			tf.addEventListener( MouseEvent.MOUSE_DOWN, IAPLIBInit );
			addChild( tf );
			
			tf = createButton( "popPurchaseDlg" );
			tf.x = 170;
			tf.y = 10;
			tf.addEventListener( MouseEvent.MOUSE_DOWN, popPurchaseDlg );
			addChild( tf );
			
			tf = createButton( "sendItemAuth" );
			tf.x = 10;
			tf.y = 50;
			tf.addEventListener( MouseEvent.MOUSE_DOWN, sendItemAuth );
			addChild( tf );
			
			tf = createButton( "sendItemWholeAuth" );
			tf.x = 170;
			tf.y = 50;
			tf.addEventListener( MouseEvent.MOUSE_DOWN, sendItemWholeAuth );
			addChild( tf );
			
			tf = createButton( "sendItemUse" );
			tf.x = 10;
			tf.y = 90;
			tf.addEventListener( MouseEvent.MOUSE_DOWN, sendItemUse );
			addChild( tf );
			
			tf = createButton( "sendItemAuthInvalid" );
			tf.x = 170;
			tf.y = 90;
			tf.addEventListener( MouseEvent.MOUSE_DOWN, sendItemAuthInvalid );
			addChild( tf );
			
			tf = createButton( "sendItemUseInvalid" );
			tf.x = 10;
			tf.y = 130;
			tf.addEventListener( MouseEvent.MOUSE_DOWN, sendItemUseInvalid );
			addChild( tf );
			
			tf = createButton( "sendPurchaseDismiss" );
			tf.x = 170;
			tf.y = 130;
			tf.addEventListener( MouseEvent.MOUSE_DOWN, sendPurchaseDismiss );
			addChild( tf );
			
			tf = createButton( "encrypt" );
			tf.x = 10;
			tf.y = 170;
			tf.addEventListener( MouseEvent.MOUSE_DOWN, encrypt );
			addChild( tf );
			
			tf = createButton( "decrypt" );
			tf.x = 170;
			tf.y = 170;
			tf.addEventListener( MouseEvent.MOUSE_DOWN, decrypt );
			addChild( tf );
		}
		
		private function createButton( label : String ) : TextField
		{
			if( !buttonFormat )
			{
				buttonFormat = new TextFormat();
				buttonFormat.font = "_sans";
				buttonFormat.size = 14;
				buttonFormat.bold = true;
				buttonFormat.color = 0xFFFFFF;
				buttonFormat.align = TextFormatAlign.CENTER;
			}
			
			var textField : TextField = new TextField();
			textField.defaultTextFormat = buttonFormat;
			textField.width = 140;
			textField.height = 30;
			textField.text = label;
			textField.backgroundColor = 0xCC0000;
			textField.background = true;
			textField.selectable = false;
			textField.multiline = false;
			textField.wordWrap = false;
			return textField;
		}
		
		private function animate( event : Event ) : void
		{
			shape.x += direction;
			if( shape.x <= 0 )
			{
				direction = 1;
			}
			if( shape.x > 320 )
			{
				direction = -1;
			}
		}
	}
}