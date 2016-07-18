package mobile.android.chart.demo;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class BudgetPieChart extends AbstractDemoChart
{

	public String getName()
	{
		return "饼图";
	}

	public String getDesc()
	{
		return "每个工程占总预算的比例";
	}

	public Intent execute(Context context)
	{
		double[] values = new double[]
		{ 12, 14, 11, 10 };
		int[] colors = new int[]
		{ Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW };
		List<double[]> valueList = new ArrayList<double[]>();
		for (int i = 0; i < colors.length; i++)
		{
			valueList.add(new double[]
			{ values[i] });
		}
		DefaultRenderer renderer = buildCategoryRenderer(colors);

		renderer.setLabelsTextSize(14);

		return ChartFactory.getPieChartIntent(context,
				buildCategoryDataset("工程预算", values), renderer, "预算");
	}

}
