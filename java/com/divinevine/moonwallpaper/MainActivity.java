package com.divinevine.moonwallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;



import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.xgc1986.parallaxPagerTransformer.ParallaxPagerTransformer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RewardedVideoAdListener {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.menu_Home)
    FloatingActionButton menuHome;
    @BindView(R.id.menu_Lock)
    FloatingActionButton menuLock;
    @BindView(R.id.menu)
    FloatingActionMenu menu;

    List<ImageModel> imageList;
    ShowcaseView sv;
    private ImagePagerAdpter pagerAdpter;
    private int counter = 0;


    public InterstitialAd interstitialAd;
    private RewardedVideoAd rewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MobileAds.initialize(this, "ca-app-pub-4178604897186962~9794408167");
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-4178604897186962/8166944801");
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                StyleableToast.makeText(getApplicationContext(), "Wallpaper set Sucessfully On Home Screen", Toast.LENGTH_LONG, R.style.mytoast).show();
            }
        });
        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        }


        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();


        RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lps.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
        lps.setMargins(margin, margin, margin, margin);

        ViewTarget target = new ViewTarget(R.id.viewPager, this);
        sv = new ShowcaseView.Builder(this)
                .setTarget(target)
                .setContentTitle("Wallpaper Viewer")
                .setContentText("Swipe left or right for more wallpapers")
                .setOnClickListener(this)
                .singleShot(42)
                .build();
        sv.setButtonPosition(lps);

        ParallaxPagerTransformer transformer = new ParallaxPagerTransformer(R.id.imageView);
        transformer.setSpeed(0.8f);
        viewPager.setPageTransformer(false, transformer);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        initViewPager();
        displayDataItem();
        viewPager.setCurrentItem(1003);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


    }

    private void loadRewardedVideoAd() {
        if (!rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.loadAd("ca-app-pub-4178604897186962/8554258938", new AdRequest.Builder().build());
        }
    }

    private void showRewardedVideo() {
        if (rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show();
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        StyleableToast.makeText(this, "Wallpaper set successfully on Lock Screen", Toast.LENGTH_LONG, R.style.mytoast1).show();
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }


    @Override
    public void onClick(View v) {
        switch (counter) {
            case 0:
                ViewTarget target1 = new ViewTarget(R.id.viewPager, this);
                sv.setStyle(R.style.ListItemBigtext);
                sv.setTarget(target1);
                sv.setContentTitle("Set Wallpaper");
                sv.setContentText("Click on + button to set wallpaper as home screen or lock screen");
                sv.overrideButtonClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sv.hide();
                    }
                });
                sv.setHideOnTouchOutside(true);
                break;
        }
    }

    private void initViewPager() {
        imageList = new ArrayList<>();
        pagerAdpter = new ImagePagerAdpter(this, imageList);
        viewPager.setAdapter(pagerAdpter);
    }

    private void displayDataItem() {
        imageList.addAll(SampleDataItem.dataItemList);
        pagerAdpter.notifyDataSetChanged();
    }

    @OnClick({R.id.menu_Home, R.id.menu_Lock})
    public void onViewClicked(View view) {
        InputStream iv = null;
        try {
            iv = getAssets().open(imageList.get(viewPager.getCurrentItem() % (imageList.size() - 1)).getItemImage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (view.getId()) {
            case R.id.menu_Home:


                final Drawable d = Drawable.createFromStream(iv, null);
                final Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(this);
                try {
                    myWallpaperManager.setBitmap(bitmap);
                    StyleableToast.makeText(this, "Wallpaper set successfully on home screen", Toast.LENGTH_LONG, R.style.mytoast).show();
                    //  System.exit(0);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                menu.close(true);
                if (interstitialAd != null && interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                  //  Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_Lock:
                if (interstitialAd != null && interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    //  Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
                }
                try {
                    //  iv = getAssets().open(imageList.get(viewPager.getCurrentItem()%(imageList.size()-1));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        WallpaperManager.getInstance(this).setStream(iv, null, true, WallpaperManager.FLAG_LOCK);
                        StyleableToast.makeText(this, "Wallpaper set successfully on lock screen", Toast.LENGTH_LONG, R.style.mytoast1).show();
                    } else {
                        StyleableToast.makeText(this, "Your device is not compatible for lock screen wallpaper", Toast.LENGTH_LONG, R.style.mytoast1).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showRewardedVideo();
                menu.close(true);
                break;
        }
    }


}
