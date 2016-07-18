package mobile.android.lower.to.upper;

public class LowerToUpper
{  
	
	//  filename1表示原文件名，filename2表示目标文件名
	public native void convert(String filename1, String filename2);
	static  
	{  
		System.loadLibrary("LowerToUpper"); 
	}    
}         
            