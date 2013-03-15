package com.funyou.nativeext.tstore;

import android.content.Context;
import android.view.View;

import com.feelingk.iap.gui.parser.ParserXML;

public class CustomParserXML extends ParserXML {
	  public CustomParserXML(Context c, ParserYesNoResultCallback cb, int isTelecomCarrier, String type, boolean checkFlag)
	  {
		  super(c, cb, isTelecomCarrier, type, checkFlag);
	  }

	  public CustomParserXML(Context c, ParserImageResultCallback cb, int isTelecomCarrier, String type, boolean checkFlag)
	  {
		  super(c, cb, isTelecomCarrier, type, checkFlag);
	  }

	  public CustomParserXML(Context c, ParserAutoPurchaseFormResultCallback cb, int isTelecomCarrier, String type, boolean checkFlag)
	  {
	    super(c, cb, isTelecomCarrier, type, checkFlag);
	  }

	  public CustomParserXML(Context c, ParserIMEIAuthCallback cb, String type, boolean CheckFlag)
	  {
	    super(c, cb, type, CheckFlag);
	  }

	  public CustomParserXML(Context c, ParserJoinResultCallback cb, int isTelecomCarrier, String type, boolean checkFlag)
	  {
		  super(c, cb, isTelecomCarrier, type, checkFlag);
	  }

	  public CustomParserXML(Context c) {
	    super(c);
	  }

	  public CustomParserXML(Context c, ParserAuthResultCallback cb, boolean bJuminPopupMode) {
	    super(c, cb, bJuminPopupMode);
	  }

	  public CustomParserXML(Context c, ParserForeignInputMDNResultCallback cb)
	  {
	    super(c, cb);
	  }

	  public CustomParserXML(Context c, ParserResultCallback callback)
	  {
	   super(c, callback);
	  }

	  public CustomParserXML(Context c, ParserResultCallback callback, int isTelecomCarrier, boolean isDeviceTab) {
	    super(c, callback, isTelecomCarrier, isDeviceTab);
	  }

	  public CustomParserXML(Context c, ParserResultCallback callback, int isTelecomCarrier, boolean isDeviceTab, boolean bJuminPopupMode)
	  {
	    super(c, callback, isTelecomCarrier, isDeviceTab, bJuminPopupMode);
	  }

	  public CustomParserXML(Context c, ParserOtpCallback callback, boolean popupMode) {
		  super(c, callback, popupMode);
	  }

	  public CustomParserXML(Context c, ParserLguSmsAuthCallback callback, boolean popupMode) {
	    super(c, callback, popupMode);
	  }

	  public CustomParserXML(Context c, ParserOCBCallback callback, boolean popupMode)
	  {
	    super(c, callback, popupMode);
	  }

	  public CustomParserXML(Context c, ParserCultureLandCallback callback, boolean popupMode)
	  {
	    super(c, callback, popupMode);
	  }

	  public CustomParserXML(Context c, ParserDotoriSmsAuthCallback callback, boolean popupMode)
	  {
	    super(c, callback, popupMode);
	  }
	  private String correctXMLFileFname(String xmlFileFname){
		  if(xmlFileFname.startsWith("/xml/")){
			  return "/assets"+xmlFileFname;
		  }else if(xmlFileFname.startsWith("/xml_kt_lg/")){
			  return "/assets"+xmlFileFname;
		  }else {
			  return xmlFileFname;
		  }
	  }
	  @Override
	  public View Start(String xmlFileFname, String message, Object obj)
	  {
		  xmlFileFname = correctXMLFileFname(xmlFileFname);
		  return super.Start(xmlFileFname, message, obj);
	  }
	  @Override
	  public View Start(String xmlFileFname, String formName, String message, Object obj, int orientation) {
		  xmlFileFname = correctXMLFileFname(xmlFileFname);
		  return super.Start(xmlFileFname, formName, message, obj, orientation);
	  }
	  @Override
	  public View StartIMEIAuth(String xmlFileFname, Object obj, int orientation)
	  {
		  xmlFileFname = correctXMLFileFname(xmlFileFname);
		  return super.Start(xmlFileFname, obj, orientation);
	  }
	  @Override
	  public View Start(String xmlFileFname, String message, Object obj, int orientation)
	  {
		  xmlFileFname = correctXMLFileFname(xmlFileFname);
		  return super.Start(xmlFileFname, message, obj, orientation);
	  }
	  @Override
	  public View Start(String xmlFileFname, Object obj, int orientation)
	  {
		  xmlFileFname = correctXMLFileFname(xmlFileFname);
		  return super.Start(xmlFileFname, obj, orientation);
	  }
}
