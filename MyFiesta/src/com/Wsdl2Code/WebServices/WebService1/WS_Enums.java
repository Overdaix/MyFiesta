package com.Wsdl2Code.WebServices.WebService1;

//------------------------------------------------------------------------------
// <wsdl2code-generated>
//    This code was generated by http://www.wsdl2code.com version  2.5
//
// Date Of Creation: 6/24/2014 12:17:06 PM
//    Please dont change this code, regeneration will override your changes
//</wsdl2code-generated>
//
//------------------------------------------------------------------------------
//
//This source code was auto-generated by Wsdl2Code  Version
//
public class WS_Enums {

    public enum SoapProtocolVersion{
        	Default(0),
        	Soap11(1),
        	Soap12(2);
        
        private int code;
        
        SoapProtocolVersion(int code){
            this.code = code;
        }
        
        public int getCode(){
            return code;
        }
        
        public static SoapProtocolVersion fromString(String str)
        {
            if (str.equals("Default"))
                return Default;
            if (str.equals("Soap11"))
                return Soap11;
            if (str.equals("Soap12"))
                return Soap12;
            return null;
        }
    }
}
