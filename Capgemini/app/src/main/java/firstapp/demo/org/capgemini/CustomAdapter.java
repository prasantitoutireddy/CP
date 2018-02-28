package firstapp.demo.org.capgemini;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prashu on 28/02/2018.
 */

public class CustomAdapter extends ArrayAdapter {

    List list = new ArrayList();
    private Context mcontext;

    public CustomAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        mcontext = context;

            }

    public void add (Contacts object){
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row ;
        row = convertView;
        ContactHolder contactHolder;
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.tx_name = (TextView) row.findViewById(R.id.tx_name);
            contactHolder.tx_desc = (TextView) row.findViewById(R.id.tx_desc);
            contactHolder.tx_image = (ImageView) row.findViewById(R.id.tx_image);

            row.setTag(contactHolder);
        }
        else contactHolder = (ContactHolder) row.getTag();

        Contacts contacts = (Contacts) this.getItem(position);
        contactHolder.tx_name.setText(contacts.getTitle());
        contactHolder.tx_desc.setText(contacts.getDesc());

        if(contactHolder.tx_image != null)
        new ImageDownloaderTask(contactHolder.tx_image).execute(Contacts.getURL());
        return row;
    }

    static class ContactHolder {
        TextView tx_name;
        TextView tx_desc;
        ImageView tx_image;
    }
}
