package mobile.android.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.android.chart.demo.AverageTemperatureChart;
import mobile.android.chart.demo.BudgetPieChart;
import mobile.android.chart.demo.IChart;
import mobile.android.chart.demo.SalesComparisonChart;
import mobile.android.chart.demo.SalesStackedBarChart;
import mobile.android.chart.demo.ScatterChart;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ChartDemo extends ListActivity
{
	private IChart[] mCharts = new IChart[]
	{ new AverageTemperatureChart(), new SalesStackedBarChart(),
		 new ScatterChart(),
			new SalesComparisonChart(),
      
			new BudgetPieChart() };
     
	private String[] mMenuText;     

	private String[] mMenuSummary;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		int length = mCharts.length;
		mMenuText = new String[length];
		mMenuSummary = new String[length];

		for (int i = 0; i < length; i++)
		{
			mMenuText[i] = mCharts[i].getName();
			mMenuSummary[i] = mCharts[i].getDesc();
		}

		setListAdapter(new SimpleAdapter(this, getListValues(),
				android.R.layout.simple_list_item_2, new String[]
				{ IChart.NAME, IChart.DESC }, new int[]
				{ android.R.id.text1, android.R.id.text2 }));
	}

	private List<Map<String, String>> getListValues()
	{
		List<Map<String, String>> values = new ArrayList<Map<String, String>>();
		int length = mMenuText.length;
		for (int i = 0; i < length; i++)
		{
			Map<String, String> v = new HashMap<String, String>();
			v.put(IChart.NAME, mMenuText[i]);
			v.put(IChart.DESC, mMenuSummary[i]);
			values.add(v);
		}
		return values;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		Intent intent = null;
		intent = mCharts[position].execute(this);
		startActivity(intent);
	}
}