package firstapp.demo.org.capgemini;

/**
 * Created by Prashu on 28/02/2018.
 */
import java.io.InputStream;
import java.lang.ref.WeakReference;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.ImageView;

public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;

    public ImageDownloaderTask(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    // Actual download method, run in the task thread
    protected Bitmap doInBackground(String... params) {
        // params comes from the execute() call: params[0] is the url.
        return downloadBitmap(params[0]);
    }

    @Override
    // Once the image is downloaded, associates it to the imageView
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {

                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    //do nothing
                }
            }

        }
    }

    static Bitmap downloadBitmap(String url) {
        if(URLUtil.isValidUrl(url)){

            final HttpClient client = new DefaultHttpClient();
            final HttpGet getRequest = new HttpGet(url);
            try {
                HttpResponse response = client.execute(getRequest);
                final int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    Log.w("ImageDownloader", "Error " + statusCode
                            + " while retrieving bitmap from " + url);
                    return null;
                }

                final HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream inputStream = null;
                    try {
                        inputStream = entity.getContent();
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        return bitmap;
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        entity.consumeContent();
                    }
                }
            } catch (Exception e) {
                // Could provide a more explicit error message for IOException or
                // IllegalStateException
                getRequest.abort();
                Log.w("ImageDownloader", "Error while retrieving bitmap from " + url);
            } finally {
                if (client != null) {
                    Log.w("ImageDownloader", "Error while retrieving bitmap from " + url);
                }
            }
            return null;

        }
        return null;
    }

}