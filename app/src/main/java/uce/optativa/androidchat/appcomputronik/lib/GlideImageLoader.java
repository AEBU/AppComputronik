package uce.optativa.androidchat.appcomputronik.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * Created by Alexis on 29/12/2016.
 */

public class GlideImageLoader implements  ImageLoader{

    private RequestManager requestManager;

    public GlideImageLoader(Context context) {

        this.requestManager = Glide.with(context);
    }

    @Override
    public void load(ImageView imgAvatar, String url) {
        requestManager.load(url).into(imgAvatar);

    }
}
