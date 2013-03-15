package com.funyou.nativeext.tstore;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.feelingk.iap.IAPActivity;
import com.feelingk.iap.IAPLib;
import com.feelingk.iap.IAPLib.OnClientListener;
import com.feelingk.iap.IAPLibSetting;
import com.feelingk.iap.gui.parser.ParserXML.ParserAuthResultCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserAutoPurchaseFormResultCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserCultureLandCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserDotoriSmsAuthCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserForeignInputMDNResultCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserIMEIAuthCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserImageResultCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserJoinResultCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserLguSmsAuthCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserOCBCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserOtpCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserPopupDlgResultCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserResultCallback;
import com.feelingk.iap.gui.parser.ParserXML.ParserYesNoResultCallback;
import com.feelingk.iap.gui.view.PopJuminNumberAuth;
import com.feelingk.iap.gui.view.PopupAutoPurchaseFormDialog;
import com.feelingk.iap.gui.view.PopupDialog;
import com.feelingk.iap.gui.view.PopupImageDialog;
import com.feelingk.iap.gui.view.PopupYesNoDialog;
import com.feelingk.iap.gui.view.PurchaseDialog;
import com.feelingk.iap.net.ItemAuth;
import com.feelingk.iap.net.ItemAuthInfo;
import com.feelingk.iap.net.ItemUse;
import com.feelingk.iap.util.CommonF;
import com.feelingk.iap.util.CommonString;

public class TStoreIAPActivity extends IAPActivity {
	public static String AppID = "OA00310593";
	
	public static String BP_IP = null;
	public static int BP_Port = 0;
	
	public static final String TAG = "TStoreIAPActivity";
	protected int commond = 1;
	public String PID = "0900674567";
	public String pName = null;
	public String pTID = null;
	public String pBPInfo = null;
	protected TStoreIAPActivity this_activity;
	final int cancelBasic = 100;
	final int errorBasic = 101;
	public void onCreate(Bundle savedInstanceState) {
		this_activity = this;
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		CustomParserXML parserXML = new CustomParserXML(this, (ParserResultCallback)getPrivateValue("onPurchasePopupCallback"), 0, ((Boolean)getPrivateValue("mTabDevice")).booleanValue());
		updatePath(parserXML, getPrivateValue("mPurchaseDlg"));
		
		parserXML = new CustomParserXML(this, (ParserAuthResultCallback)getPrivateValue("onJuminDialogPopupCallback"), true);
		updatePath(parserXML, getPrivateValue("mJuminAuth"));
	    
		parserXML = new CustomParserXML(this);
		updatePath(parserXML, getPrivateValue("mPopupDlg"));
		
		parserXML = new CustomParserXML(this, (ParserYesNoResultCallback)getPrivateValue("onPopupCallback"), 0, "YesNo", true);
		updatePath(parserXML, getPrivateValue("mYesNoDlg"));
		
		parserXML = new CustomParserXML(this, (ParserImageResultCallback)getPrivateValue("onImageResultCallback"), 0, "PermissionPopup", true);
		updatePath(parserXML, getPrivateValue("mImageDlg"));
		
		parserXML = new CustomParserXML(this, (ParserAutoPurchaseFormResultCallback)getPrivateValue("onAutoPurchaseFormResultCallback"), 0, "AutoPurchaseForm", true);
		updatePath(parserXML, getPrivateValue("mAutoPurchaseFormDlg"));
		
		parserXML = new CustomParserXML(this, (ParserJoinResultCallback)getPrivateValue("onJoinResultCallback"), 0, "Join", true);
		updatePath(parserXML, getPrivateValue("mJoinDlg"));
		
		parserXML = new CustomParserXML(this, (ParserOtpCallback)getPrivateValue("onOtpCallback"), true);
		updatePath(parserXML, getPrivateValue("mOtpDlg"));
		
		parserXML = new CustomParserXML(this, (ParserLguSmsAuthCallback)getPrivateValue("onLguSmsCallback"), true);
		updatePath(parserXML, getPrivateValue("mLguSmsDlg"));
		
		parserXML = new CustomParserXML(this, (ParserIMEIAuthCallback)getPrivateValue("onImeiAuthCallback"), "IMEIAuthForm", true);
		updatePath(parserXML, getPrivateValue("mImeiAuthDlg"));
		
		parserXML = new CustomParserXML(this, (ParserOCBCallback)getPrivateValue("onOcbCallback"), true);
		updatePath(parserXML, getPrivateValue("mOCBDlg"));
		
		parserXML = new CustomParserXML(this, (ParserCultureLandCallback)getPrivateValue("onCultureLandCallback"), true);
		updatePath(parserXML, getPrivateValue("mCultureLandLoginDlg"));
		
		parserXML = new CustomParserXML(this, (ParserDotoriSmsAuthCallback)getPrivateValue("onDotoriSmsAuthCallback"), true);
		updatePath(parserXML, getPrivateValue("mDotoriSmsAuthDlg"));
		
		parserXML = new CustomParserXML(this, (ParserForeignInputMDNResultCallback)getPrivateValue("onForeignInputMDNCallback"));
		updatePath(parserXML, getPrivateValue("mForeignInputMDN"));

//	    FrameLayout layout = new FrameLayout(this);
//	    setContentView(layout);
	    IAPLibSetting setting = new IAPLibSetting();
	    setting.AppID = AppID;
	    setting.BP_IP = BP_IP;
	    setting.BP_Port = BP_Port;
	    setting.ClientListener = this.clientListener;
	    try
	    {
	      IAPLibInit(setting);
	    } catch (Exception e) {
	      Log.i(TAG, "error", e);
	    }
	    commond = (Integer)getIntent().getExtras().get("commond");
	    switch(commond){
	    case 1:
		    PID = ((String)getIntent().getExtras().get("PID"));
		    pName = ((String)getIntent().getExtras().get("pName"));
		    pTID = ((String)getIntent().getExtras().get("pTID"));
		    pBPInfo = ((String)getIntent().getExtras().get("pBPInfo"));
		    popPurchaseDlg(PID, pName, pTID, pBPInfo);
	    	break;
	    case 2:
		    PID = ((String)getIntent().getExtras().get("PID"));
		    sendItemAuth(PID);
	    	break;
	    case 3:
		    PID = ((String)getIntent().getExtras().get("PID"));
		    sendItemUse(PID);
	    	break;
	    case 4:
		   sendItemWholeAuth();
	    	break;
	    case 5:
		    PID = ((String)getIntent().getExtras().get("PID"));
		    pName = ((String)getIntent().getExtras().get("pName"));
			this.sendPurchaseDismiss(PID, pName);
		    break;
	    }
		IAPExtension.iap_activity=this;
	}
	public void startActivityForResult(Intent intent, int requestCode) {
//		intent.setClassName("com.funyou.iap", PwdAc)
		
		ComponentName comp = intent.getComponent();
		if(comp!=null){
			Log.i(TAG, comp.getClassName());
			Log.i(TAG, comp.getShortClassName());
		}
		if(comp!=null&&"com.feelingk.iap.PwdActivity".equals(comp.getShortClassName())){
			String pack = getPackageName();
			Log.i(TAG, pack);
			intent.setComponent(new ComponentName(pack, "com.funyou.nativeext.tstore.CPwdActivity"));
//			intent.setClassName("com.funyou.nativeext.tstore", "CPwdActivity");
			super.startActivityForResult(intent, requestCode);
		}else {
			super.startActivityForResult(intent, requestCode);
		}
	} 
//	private String getpPackageName(){
//		Intent intent = getIntent();
//		if(intent!=null){
//			ComponentName comp = intent.getComponent();
//			if(comp!=null){
//				return comp.getPackageName();
//			}
//		}
//		
//		return null;
//	}
    public void updatePath(CustomParserXML parserXML, Object target) {
		try {
			Field RES_VERT_FILE_PATH_field = parserXML.getClass().getSuperclass().getDeclaredField("RES_VERT_FILE_PATH");
	        Field XML_FILE_PATH_field = parserXML.getClass().getSuperclass().getDeclaredField("XML_FILE_PATH");
	        Field XML_FILE_PATH_KTLG_field = parserXML.getClass().getSuperclass().getDeclaredField("XML_FILE_PATH_KTLG");
	        RES_VERT_FILE_PATH_field.setAccessible(true);
	        XML_FILE_PATH_field.setAccessible(true);
	        XML_FILE_PATH_KTLG_field.setAccessible(true);
	        
	        Log.i(TAG, RES_VERT_FILE_PATH_field.get(parserXML).toString());
	        RES_VERT_FILE_PATH_field.set(parserXML, "/assets/res/");
	        Log.i(TAG, RES_VERT_FILE_PATH_field.get(parserXML).toString());
	        Log.i(TAG, XML_FILE_PATH_field.get(parserXML).toString());
	        XML_FILE_PATH_field.set(parserXML, "/assets/xml");
	        Log.i(TAG, XML_FILE_PATH_field.get(parserXML).toString());
	        Log.i(TAG, XML_FILE_PATH_KTLG_field.get(parserXML).toString());
	        XML_FILE_PATH_KTLG_field.set(parserXML, "/assets/xml_kt_lg");
	        Log.i(TAG, XML_FILE_PATH_KTLG_field.get(parserXML).toString());
	        RES_VERT_FILE_PATH_field.setAccessible(false);
	        XML_FILE_PATH_field.setAccessible(false);
	        XML_FILE_PATH_KTLG_field.setAccessible(false);
	       
	        Field field = target.getClass().getDeclaredField("mGUIParser");
			field.setAccessible(true);
			field.set(target, parserXML);
			field.setAccessible(false);
		} catch (NoSuchFieldException e) {
			Log.i(TAG, e.getMessage());
		} catch (IllegalArgumentException e) {
			Log.i(TAG, e.getMessage());
		} catch (IllegalAccessException e) {
			Log.i(TAG, e.getMessage());
		}

    }
	private Object invokPrivateMethod (String method){
		 Class targetRef = getClass().getSuperclass();
		 try {
			Method vm = targetRef.getDeclaredMethod(method);
			vm.setAccessible(true);
			Object result = vm.invoke(this);
			vm.setAccessible(false);
			return result;
		} catch (NoSuchMethodException e) {
			Log.i(TAG, "error", e);
		} catch (IllegalArgumentException e) {
			Log.i(TAG, "error", e);
		} catch (IllegalAccessException e) {
			Log.i(TAG, "error", e);
		} catch (InvocationTargetException e) {
			Log.i(TAG, "error", e);
		}
		return null;
	}
	private void setPrivateValue (String fieldName, Object value){
		Field field;
		try {
			field = getClass().getSuperclass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(this, value);
			field.setAccessible(false);
		} catch (NoSuchFieldException e) {
			Log.i(TAG, e.getMessage());
		} catch (IllegalArgumentException e) {
			Log.i(TAG, e.getMessage());
		} catch (IllegalAccessException e) {
			Log.i(TAG, e.getMessage());
		}
	}
	private Object getPrivateValue (String fieldName){
		Field field;
		try {
			field = getClass().getSuperclass().getDeclaredField(fieldName);
			field.setAccessible(true);
			Object obj = field.get(this);
			field.setAccessible(false);
			return obj;
		} catch (NoSuchFieldException e) {
			Log.i(TAG, e.getMessage());
		} catch (IllegalArgumentException e) {
			Log.i(TAG, e.getMessage());
		} catch (IllegalAccessException e) {
			Log.i(TAG, e.getMessage());
		}
		return null;
	}

	   @Override
	public Dialog onCreateDialog(int id) {
		Dialog dlg = null;
		switch (id) {
		case cancelBasic:
			AlertDialog.Builder adb1 = new AlertDialog.Builder(this_activity);
			adb1.setTitle("T-Store 구매 취소");
			adb1.setMessage("구매가 취소 되었습니다.");
			adb1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					finish();
				}
			});
			dlg = adb1.create();
			break;
		case errorBasic:
			AlertDialog.Builder adb2 = new AlertDialog.Builder(this_activity);
			adb2.setTitle("");
			adb2.setMessage("");
			adb2.setPositiveButton("close", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					finish();
				}
			});
			dlg = adb2.create();
			break;
		}
		
		return dlg;
	}
//    public void updatePath(Object instance, String fieldName) {
//		try {
//			Field field = instance.getClass().getSuperclass().getDeclaredField(fieldName);
//	        field.setAccessible(true);
//	        Object dialog = field.get(instance);
//	        if(dialog==null){
//	        	return;
//	        }
//	        field.setAccessible(false);
//	        Field mGUIParser_field = dialog.getClass().getDeclaredField("mGUIParser");
//	        mGUIParser_field.setAccessible(true);
//	        Object mGUIParser = mGUIParser_field.get(dialog);
//	        mGUIParser_field.setAccessible(false);
//	        Field RES_VERT_FILE_PATH_field = mGUIParser.getClass().getDeclaredField("RES_VERT_FILE_PATH");
//	        Field XML_FILE_PATH_field = mGUIParser.getClass().getDeclaredField("XML_FILE_PATH");
//	        Field XML_FILE_PATH_KTLG_field = mGUIParser.getClass().getDeclaredField("XML_FILE_PATH_KTLG");
//	        RES_VERT_FILE_PATH_field.setAccessible(true);
//	        XML_FILE_PATH_field.setAccessible(true);
//	        XML_FILE_PATH_KTLG_field.setAccessible(true);
//	        
//	        Log.i(TAG, RES_VERT_FILE_PATH_field.get(mGUIParser).toString());
//	        RES_VERT_FILE_PATH_field.set(mGUIParser, "/assets/res/");
//	        Log.i(TAG, RES_VERT_FILE_PATH_field.get(mGUIParser).toString());
//	        Log.i(TAG, XML_FILE_PATH_field.get(mGUIParser).toString());
//	        XML_FILE_PATH_field.set(mGUIParser, "/assets/xml");
//	        Log.i(TAG, XML_FILE_PATH_field.get(mGUIParser).toString());
//	        Log.i(TAG, XML_FILE_PATH_KTLG_field.get(mGUIParser).toString());
//	        XML_FILE_PATH_KTLG_field.set(mGUIParser, "/assets/xml_kt_lg");
//	        Log.i(TAG, XML_FILE_PATH_KTLG_field.get(mGUIParser).toString());
//	        RES_VERT_FILE_PATH_field.setAccessible(false);
//	        XML_FILE_PATH_field.setAccessible(false);
//	        XML_FILE_PATH_KTLG_field.setAccessible(false);
//		} catch (NoSuchFieldException e) {
//			Log.i(TAG, e.getMessage());
//		} catch (IllegalArgumentException e) {
//			Log.i(TAG, e.getMessage());
//		} catch (IllegalAccessException e) {
//			Log.i(TAG, e.getMessage());
//		}
//
//    }
	public void publicPopPurchaseDlg(String pID, String pName, String pTID, String pBPInfo){
		popPurchaseDlg(pID, pName, pTID, pBPInfo);
	}
	public void publicSendItemAuth(String pID) {
		sendItemAuth(pID);
	}
	public void publicSendItemWholeAuth() {
		sendItemWholeAuth();
	}
	public void publicSendItemUse(String pID) {
		sendItemUse(pID);
	}
	public void publicSendPurchaseDismiss(String pID, String pName) {
		sendPurchaseDismiss(pID, pName);
	}
	protected OnClientListener clientListener = new OnClientListener() {
		
		private String eventName = "status_change";
		@Override
		public void onDlgAutoPurchaseInfoCancel() {
			IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onDlgAutoPurchaseInfoCancel);
			showDialog(cancelBasic);
//			finish();
		}

		@Override
		public void onDlgError() {
//			IAPExtension.isPurchasing = false;
			IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onDlgError);
			finish();
		}

		@Override
		public void onDlgPurchaseCancel() {
//			IAPExtension.isPurchasing = false;
			IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onDlgPurchaseCancel);
			showDialog(cancelBasic);
		}

		@Override
		public void onError(int arg0, int arg1) {
//			IAPExtension.isPurchasing = false;
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
//			  showDialog(errorBasic);
//			  finish();
		}

		@Override
		public void onItemAuthInfo(ItemAuthInfo itemAuthInfo) {
			IAPExtension.itemAuthInfo = itemAuthInfo;
			IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onItemAuthInfo);
			finish();
		}

		@Override
		public void onItemPurchaseComplete() {
//			IAPExtension.isPurchasing = false;
			IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onItemPurchaseComplete);
			finish();
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
			finish();
		}

		@Override
		public void onJoinDialogCancel() {
			IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onJoinDialogCancel);
			finish();
		}

		@Override
		public void onPurchaseDismiss() {
			IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onPurchaseDismiss);
			finish();
		}

		@Override
		public void onWholeQuery(ItemAuth[] itemAuth) {
			IAPExtension.itemAuths = itemAuth;
			IAPExtension.context.dispatchStatusEventAsync(eventName, InternalMessages.onWholeQuery);
			finish();
		}
	};
}
