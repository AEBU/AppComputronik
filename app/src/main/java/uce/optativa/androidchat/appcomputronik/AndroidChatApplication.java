package uce.optativa.androidchat.appcomputronik;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

import uce.optativa.androidchat.appcomputronik.lib.GlideImageLoader;
import uce.optativa.androidchat.appcomputronik.lib.ImageLoader;


/**
 * Created by Alexis on 26/12/2016.
 */

public class AndroidChatApplication  extends Application {
    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
        setupImageLoader();
    }

    private void setupImageLoader() {
        imageLoader = new GlideImageLoader(this);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private void setupFirebase(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }


}
