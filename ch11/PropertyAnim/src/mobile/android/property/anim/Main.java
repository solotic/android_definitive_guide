package mobile.android.property.anim;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity
{
	private Button button;
	private Move move;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button = (Button) findViewById(R.id.button);
		move = new Move();  
   
	}
	
	class Move
	{
		private int y;
		private int x;
		
		public int getY()
		{  
			return y;
		}
		
		public void setY(int y)
		{
			this.y = y;
			button.layout(button.getLeft(), y, button.getRight(),
					y + button.getMeasuredHeight());
		}
		
		public int getX()
		{
			return x;
		}
		
		public void setX(int x)
		{
			this.x = x;
			button.layout(x, button.getTop(), x + button.getMeasuredWidth(),
					button.getBottom());
		}
		
	}
	
	public void onClick_Move(View view)
	{
		
		// 装载属性动画资源
		AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
				R.animator.property_anim);
		// 设置要控制的对象
		set.setTarget(move);
		// 开始动画
		set.start();
		
		
	}
}
