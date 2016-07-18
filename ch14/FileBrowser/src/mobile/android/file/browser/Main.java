package mobile.android.file.browser;

import mobile.android.widget.file.browser.FileBrowser;
import mobile.android.widget.file.browser.OnFileBrowserListener;
import android.app.Activity;
import android.os.Bundle;

public class Main extends Activity implements OnFileBrowserListener
{

	@Override
	public void onFileItemClick(String filename)
	{
		setTitle(filename);
		
	}

	@Override
	public void onDirItemClick(String path)
	{
		setTitle(path);
		
	}
 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FileBrowser fileBrowser = (FileBrowser)findViewById(R.id.filebrowser);
		fileBrowser.setOnFileBrowserListener(this);
	}

}
