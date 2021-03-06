package com.Wsdl2Code.WebServices.WebService1;

//------------------------------------------------------------------------------
// <wsdl2code-generated>
//    This code was generated by http://www.wsdl2code.com version  2.5
//
// Date Of Creation: 6/26/2014 2:42:04 PM
//    Please dont change this code, regeneration will override your changes
//</wsdl2code-generated>
//
//------------------------------------------------------------------------------
//
//This source code was auto-generated by Wsdl2Code  Version
//
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

public class Drink implements KvmSerializable, Serializable {
    
    public int id;
    public String naam;
    public String image;
    public String description;
    public int favorite;
    
    public Drink(){}
    
    public Drink(SoapObject soapObject)
    {
        if (soapObject == null)
            return;
        if (soapObject.hasProperty("id"))
        {
            Object obj = soapObject.getProperty("id");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                id = Integer.parseInt(j.toString());
            }else if (obj!= null && obj instanceof Number){
                id = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("naam"))
        {
            Object obj = soapObject.getProperty("naam");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                naam = j.toString();
            }else if (obj!= null && obj instanceof String){
                naam = (String) obj;
            }
        }
        if (soapObject.hasProperty("image"))
        {
            Object obj = soapObject.getProperty("image");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                image = j.toString();
            }else if (obj!= null && obj instanceof String){
                image = (String) obj;
            }
        }
        if (soapObject.hasProperty("description"))
        {
            Object obj = soapObject.getProperty("description");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                description = j.toString();
            }else if (obj!= null && obj instanceof String){
                description = (String) obj;
            }
        }
        if (soapObject.hasProperty("favorite"))
        {
            Object obj = soapObject.getProperty("favorite");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                favorite = Integer.parseInt(j.toString());
            }else if (obj!= null && obj instanceof Number){
                favorite = (Integer) obj;
            }
        }
    }
    @Override
    public Object getProperty(int arg0) {
        switch(arg0){
            case 0:
                return id;
            case 1:
                return naam;
            case 2:
                return image;
            case 3:
                return description;
            case 4:
                return favorite;
        }
        return null;
    }
    
    @Override
    public int getPropertyCount() {
        return 5;
    }
    
    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch(index){
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "id";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "naam";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "image";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "description";
                break;
            case 4:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "favorite";
                break;
        }
    }
    
    @Override
    public void setProperty(int arg0, Object arg1) {
    }
    
}
