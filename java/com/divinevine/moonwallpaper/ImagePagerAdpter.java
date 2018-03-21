package com.divinevine.moonwallpaper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by shriji on 11/3/18.
 */

public class ImagePagerAdpter extends PagerAdapter {
    private List<ImageModel> IMAGES;
    private LayoutInflater inflater;
    private Context context;

    public ImagePagerAdpter(Context context, List<ImageModel> IMAGES) {
        this.context = context;
        this.IMAGES = IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.image_adpter, view, false);
        position = position % (IMAGES.size() - 1);
        assert imageLayout != null;
        final ImageView imageView = imageLayout.findViewById(R.id.imageView);
        final ImageModel modelList = IMAGES.get(position);
        String imageName = modelList.getItemImage();
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(imageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Drawable d = Drawable.createFromStream(inputStream, null);
        imageView.setImageDrawable(d);
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
