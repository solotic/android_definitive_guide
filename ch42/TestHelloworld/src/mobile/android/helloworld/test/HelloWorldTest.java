package mobile.android.helloworld.test;


import mobile.android.helloworld.Main;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class HelloWorldTest extends ActivityInstrumentationTestCase2<Main>
{
	private Activity activity;
	private TextView textView;

	public HelloWorldTest()
	{
		super("mobile.android.ch22.helloworld", Main.class);
	}
	@Override
	protected void setUp() throws Exception
	{
		// TODO Auto-generated method stub
		super.setUp();
		activity = this.getActivity();

		textView = (TextView) activity
				.findViewById(mobile.android.helloworld.R.id.textview);

	}
	public void testText()
	{
		assertEquals("�������", (String) textView.getText());
	}
	public void testPreconditions()
	{
		assertNotNull(textView);
	}


}
