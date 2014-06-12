package com.Wsdl2Code.WebServices.WebService1;

//------------------------------------------------------------------------------
// <wsdl2code-generated>
//    This code was generated by http://www.wsdl2code.com version  2.5
//
// Date Of Creation: 6/12/2014 3:02:55 PM
//    Please dont change this code, regeneration will override your changes
//</wsdl2code-generated>
//
//------------------------------------------------------------------------------
//
//This source code was auto-generated by Wsdl2Code  Version
//
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import java.util.Hashtable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

public class Mix implements KvmSerializable {
    
    public int mixid;
    public int ingredientid;
    public String amount;
    public int optional;
    
    public Mix(){}
    
    public Mix(SoapObject soapObject)
    {
        if (soapObject == null)
            return;
        if (soapObject.hasProperty("mixid"))
        {
            Object obj = soapObject.getProperty("mixid");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                mixid = Integer.parseInt(j.toString());
            }else if (obj!= null && obj instanceof Number){
                mixid = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("ingredientid"))
        {
            Object obj = soapObject.getProperty("ingredientid");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                ingredientid = Integer.parseInt(j.toString());
            }else if (obj!= null && obj instanceof Number){
                ingredientid = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("amount"))
        {
            Object obj = soapObject.getProperty("amount");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                amount = j.toString();
            }else if (obj!= null && obj instanceof String){
                amount = (String) obj;
            }
        }
        if (soapObject.hasProperty("optional"))
        {
            Object obj = soapObject.getProperty("optional");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                optional = Integer.parseInt(j.toString());
            }else if (obj!= null && obj instanceof Number){
                optional = (Integer) obj;
            }
        }
    }
    @Override
    public Object getProperty(int arg0) {
        switch(arg0){
            case 0:
                return mixid;
            case 1:
                return ingredientid;
            case 2:
                return amount;
            case 3:
                return optional;
        }
        return null;
    }
    
    @Override
    public int getPropertyCount() {
        return 4;
    }
    
    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch(index){
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "mixid";
                break;
            case 1:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "ingredientid";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "amount";
                break;
            case 3:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "optional";
                break;
        }
    }
    
    @Override
    public void setProperty(int arg0, Object arg1) {
    }
    
}