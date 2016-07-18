package mobile.android.interceptor;

import android.view.animation.Interpolator;

public class MyInterceptor implements Interpolator
{

	@Override
	public float getInterpolation(float input)
	{
        //  º”ÀŸ
		if (input <= 0.5)
			return input * input;
		else  //  ºıÀŸ
			return (1 - input) * (1 - input) ;

	}

}
