package galaxy.geek.nfc.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class MobileDevice extends Composite
{
	private Canvas mCanvas;
	private Label mLabel;
	private String mdeviceImagePath;
	private String mDeviceName;

	public MobileDevice(Composite parent, int style, String deviceImagePath, String deviceName)
	{
		super(parent, style);
		mdeviceImagePath = deviceImagePath;
		mDeviceName = deviceName;
		initGUI();
	}
    public String getName()
    {
    	return mDeviceName;
    }
	private void initGUI()
	{

		try
		{

			mCanvas = new Canvas(this, SWT.NONE);
			mCanvas.setBounds(0, 0, 90,160);
			
			final Image image = new Image(getShell().getDisplay(), mdeviceImagePath);
			mCanvas.addPaintListener(new PaintListener()
			{
				
				@Override
				public void paintControl(PaintEvent pe)
				{
					pe.gc.drawImage(image, 0, 0, image.getImageData().width,
							image.getImageData().height, 0, 0,
							mCanvas.getBounds().width, mCanvas.getBounds().height);
				}
			});
			mLabel = new Label(mCanvas, SWT.NONE);
			mLabel.setBounds(0,mCanvas.getBounds().height /3, mCanvas.getBounds().width,25);
			
			mLabel.setFont(new Font(getDisplay(), "Monaco", 14, SWT.BOLD));
			mLabel.setAlignment(SWT.CENTER);
			mLabel.setText(mDeviceName);
	
			layout();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
