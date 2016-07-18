package mobile.android.complex.type.aidl;
import mobile.android.complex.type.aidl.Product;

interface IMyService  
{  
    Map getMap(in String country, in Product product);
    Product getProduct();     
}          