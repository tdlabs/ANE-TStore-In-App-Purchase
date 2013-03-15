package com.funyou.nativeext.tstore.vo
{
	public class ItemAuthInfo
	{
		public function ItemAuthInfo(pCount:int, pExpireDate:String, pToken:String)
		{
			this.pCount = pCount;
			this.pExpireDate = pExpireDate;
			this.pToken = pToken;
		}
		public var pCount:int;
		public var pExpireDate:String;
		public var pToken:String;
	}
}