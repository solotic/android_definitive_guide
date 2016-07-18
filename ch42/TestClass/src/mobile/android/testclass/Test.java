package mobile.android.testclass;

import android.test.AndroidTestCase;

public class Test extends AndroidTestCase
{    
	private MyClass myClass;
	@Override
	protected void setUp() throws Exception
	{		
		super.setUp();
		myClass = new MyClass();
	}
	
	public void testName()
	{
		assertEquals("Android", myClass.getName());
	}
}
