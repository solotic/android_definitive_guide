package mobile.android.first.drag.drop;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstDragDropActivity extends Activity implements OnDragListener
{
	private FrameLayout dragdropRegion;
	private ImageView imageview;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_drag_drop);
		dragdropRegion = (FrameLayout) findViewById(R.id.framelayout_dragdrop_region);
		imageview = (ImageView) findViewById(R.id.imageview);
		dragdropRegion.setOnDragListener(this);

		imageview.setOnLongClickListener(new View.OnLongClickListener()
		{

			public boolean onLongClick(View v)
			{

				View.DragShadowBuilder myShadow = new MyDragShadowBuilder(
						imageview);

				v.startDrag(null, myShadow, null, 0);
				return true;

			}

		});
		
	}

	@Override
	public boolean onDrag(View view, DragEvent event)
	{
		int action = event.getAction();
		switch (action)
		{
			
			case DragEvent.ACTION_DRAG_STARTED:
				
				System.out.println("drag_started");
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				System.out.println("drag_entered");
				break;
			case DragEvent.ACTION_DRAG_LOCATION:
				System.out.println("drag_location x=" + event.getX() + " y="
						+ event.getY());
				
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				System.out.println("drag_exited");
				
				break;
			case DragEvent.ACTION_DROP:
				System.out.println("drag_drop");
				ImageView imageView = (ImageView) getLayoutInflater().inflate(
						R.layout.image, null);
				LayoutParams layoutParams = new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				layoutParams.leftMargin = (int) (event.getX());
				layoutParams.topMargin = (int) (event.getY());
				
				dragdropRegion.addView(imageView, layoutParams);
				break;
			case DragEvent.ACTION_DRAG_ENDED:
				System.out.println("drag_ended");

				break;
			default:
				return false;
		}
		
		return true;
	}

}
