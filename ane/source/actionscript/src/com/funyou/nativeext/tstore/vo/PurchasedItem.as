package com.funyou.nativeext.tstore.vo
{
	public class PurchasedItem
	{
		public var pID:String;
		public var pName:String;
		public var pTID:String;
		public var pBPInfo:String;
		
		public function PurchasedItem(pID:String, pName:String, pTID:String, pBPInfo:String)
		{
			this.pID = pID;
			this.pName = pName;
			this.pTID = pTID;
			this.pBPInfo = pBPInfo;
		}
	}
}