package galaxy.geek.nfc.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class NFCTag extends Composite
{
	public Canvas canvas;
	public Label label;
	public String nfcImagePath = "res/images/nfc.png";
	public String name;
	public String text;
	public int maxSize;
	public int contentSize;

	public NFCTag(Composite parent, int style, String name)
	{
		this(parent, style, name, 4096);
	}

	public NFCTag(Composite parent, int style, String name, int maxSize)
	{
		super(parent, style);
		this.name = name;
		this.maxSize = maxSize;
		initGUI();
	}

	public void setText(String text)
	{
		this.text = text;

		if (text == null)
		{
			nfcImagePath = "res/images/nfc.png";
		}
		else
		{
			nfcImagePath = "res/images/nfc_data.png";
		}

		canvas.redraw();
		layout();

	}

	public void setName(String name)
	{
		this.name = name;
		if (name != null)
			label.setText(name);
	}

	public void clearText()
	{
		nfcImagePath = "res/images/nfc.png";
		this.text = null;
		canvas.redraw();

	}

	private void initGUI()
	{

		
		try
		{

			canvas = new Canvas(this, SWT.NONE);
			canvas.setBounds(0, 0, 80, 80);

			canvas.addPaintListener(new PaintListener()
			{

				@Override
				public void paintControl(PaintEvent pe)
				{
					Image image = new Image(getShell().getDisplay(),
							nfcImagePath);
					
					pe.gc.drawImage(image, 0, 0, image.getImageData().width,
							image.getImageData().height, 0, 0,
							canvas.getBounds().width, canvas.getBounds().height);
				}
			});
			label = new Label(this, SWT.NONE);
			label.setBounds(0, canvas.getBounds().height + 2,
					canvas.getBounds().width, 25);

			label.setFont(new Font(getDisplay(), "Monaco", 14, SWT.BOLD));
			label.setAlignment(SWT.CENTER);
			if (name != null)
				label.setText(name);

			layout();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
