package firstapp.demo.org.capgemini;

import java.io.Serializable;

/**
 * Created by Prashu on 28/02/2018.
 */

public class Contacts implements Serializable{

    private String title,desc,url;

    public Contacts(String title, String desc, String url)
    {
        this.setTitle(title);
        this.setDesc(desc);
        this.setURL(url);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setURL(String URL) {
        this.url = url;
    }
    public String getURL() {
        return url;
    }
}
