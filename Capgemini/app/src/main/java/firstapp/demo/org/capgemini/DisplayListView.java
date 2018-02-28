package firstapp.demo.org.capgemini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string = "";
    JSONObject jsonObject;
    JSONArray jsonArray;
    CustomAdapter customAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        customAdapter = new CustomAdapter(this,R.layout.row_layout);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(customAdapter);
        json_string = getIntent().getExtras().getString(json_string);
        String title, desc, image ;
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("rows");

            int count = 0;
            while (count < jsonArray.length())
            {
                JSONObject jo = jsonArray.getJSONObject(count);
                title = jsonObject.getString("title");
                desc = jsonObject.getString("desc");
                image = jsonObject.getString("image");

                Contacts contacts = new Contacts(title,desc,image);
                customAdapter.add(contacts);
                count++;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
