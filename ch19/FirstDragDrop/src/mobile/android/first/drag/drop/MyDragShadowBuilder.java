package mobile.android.first.drag.drop;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class MyDragShadowBuilder extends View.DragShadowBuilder
{

	private static Drawable shadow;
	private static Bitmap newBitmap;

	public MyDragShadowBuilder(View v)
	{

		super(v);

		shadow = new ColorDrawable(Color.LTGRAY);
	}

	@Override
	public void onProvideShadowMetrics(Point size, Point touch)
	{

		int width, height;

		width = (int) (getView().getWidth() * 1.5);
		height = (int) (getView().getHeight() * 1.5);

		shadow.setBounds(0, 0, width, height);

		size.set(width, height);

		touch.set(width / 2, height / 2);

		if (getView() instanceof ImageView)
		{
			ImageView imageView = (ImageView) getView();

			Drawable drawable = imageView.getDrawable();
			Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

			newBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);

			canvas.drawBitmap(bitmap,
					new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
					new Rect(0, 0, width, height), null);

		}
	}

	@Override
	public void onDrawShadow(Canvas canvas)
	{

		// shadow.draw(canvas);
		canvas.drawBitmap(newBitmap, 0, 0, new Paint());
	}
}
