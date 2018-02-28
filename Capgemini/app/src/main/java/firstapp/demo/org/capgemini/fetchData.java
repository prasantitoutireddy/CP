package firstapp.demo.org.capgemini;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import org.json.JSONArray;
import org.json.JSONObject;
import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import firstapp.demo.org.capgemini.DisplayListView;
import static android.R.attr.start;
import static android.widget.Toast.*;

/**
 * Created by Prashu on 27/02/2018.
 */

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data = "";
    String json_string="";
    public Context mcontext;


    public fetchData (Context context)
    {
        this.mcontext = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
      try {
          URL url = new URL("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json");
          HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
          InputStream inputStream =  httpURLConnection.getInputStream();
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
          while (line != null)
          {
              line=bufferedReader.readLine();
              data = data+line;

          }
      }
        catch(MalformedURLException e)
          {
              e.printStackTrace();
          }
        catch(Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(this.data);
        json_string = this.data;

    }

    public void parseJSON (View view) {

        if (json_string == null)
            makeText(mcontext.getApplicationContext(),"GET JSON first", LENGTH_LONG).show();
        else
        {
            Intent intent = new Intent(mcontext.getApplicationContext(),DisplayListView.class);
            intent.putExtra("json_data",json_string);
            mcontext.startActivity(intent);


        }
    }
}
