package firstapp.demo.org.capgemini;

import firstapp.demo.org.capgemini.R;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    Button click ;
   public static TextView data ;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = (Button) findViewById(R.id.button);
        data = (TextView) findViewById(R.id.data);

        click.setOnClickListener(new View.OnClickListener(){

            @Override
                    public void onClick (View view) {
                fetchData process = new fetchData(context);
                process.execute();
            }

        });
    }


}
