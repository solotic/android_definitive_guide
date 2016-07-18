package mobile.android.json;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.view.View;
import android.widget.Toast;

public class JSONActivity extends Activity
{
	private String jsonFile = "/sdcard/test.json";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);
	}

	private static class Product
	{
		public String id;
		public String name;

		public Product(String id, String name)
		{
			this.id = id;
			this.name = name;
		}
	}

	public void writeProduct(JsonWriter writer, Product product)
			throws IOException
	{
		writer.beginObject();
		writer.name("id").value(product.id);
		writer.name("name").value(product.name);
		writer.endObject();
	}

	public void writeProductArray(JsonWriter writer, List<Product> products)
			throws IOException
	{
		writer.beginArray();
		for (Product product : products)
		{
			writeProduct(writer, product);
		}
		writer.endArray();
	}

	public void writeJsonStream(OutputStream out, List<Product> products)
			throws IOException
	{
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
		writer.setIndent("  ");
		writeProductArray(writer, products);
		writer.close();
	}

	public void onClick_WriteJSON(View view)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(jsonFile);
			List<Product> products = new ArrayList<Product>();
			products.add(new Product("0001", "Nexus S"));
			products.add(new Product("0002", "谷歌眼镜"));
			writeJsonStream(fos, products);
			Toast.makeText(this, "成功保存JSON数据", Toast.LENGTH_LONG).show();
		}
		catch (Exception e)
		{

		}

	}

	public List<Product> readJsonStream(InputStream in) throws IOException
	{
		JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
		try
		{
			return readProductArray(reader);
		}

		finally
		{
			reader.close();
		}
	}

	public List readProductArray(JsonReader reader) throws IOException
	{
		List<Product> products = new ArrayList<Product>();

		reader.beginArray();
		while (reader.hasNext())
		{
			products.add(readProduct(reader));
		}
		reader.endArray();
		return products;
	}

	public Product readProduct(JsonReader reader) throws IOException
	{
		String id = null;
		String name = null;

		reader.beginObject();
		while (reader.hasNext())
		{
			String field = reader.nextName();
					
			if (field.equals("id"))
			{
				id = reader.nextString();
			}
			else if (field.equals("name"))
			{
				name = reader.nextString();

			}
			else
			{
				reader.skipValue();
			}
		}
		reader.endObject();
		return new Product(id, name);
	}

	public void onClick_ReadJSON(View view)
	{
		try
		{
			FileInputStream fis = new FileInputStream(jsonFile);
			List<Product> products = readJsonStream(fis);
			
			if(products != null)
			{
				String result = "";
				for(Product product: products)
				{
					result += "id:" + product.id + "  name:" + product.name + "\n";
				}
				Toast.makeText(this, result, Toast.LENGTH_LONG).show();
			}
		}
		catch(Exception e)
		{
			
		}
	}
}
