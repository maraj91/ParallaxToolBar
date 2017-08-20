package com.example.mdibrahimhassan.parallaxtoolbarscrolling;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    CollapsingToolbarLayout mCtl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCtl = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mCtl.setTitle("Parallax Demo");
        mCtl.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_image_2);
        Palette.generateAsync(bitmap,
                new Palette.PaletteAsyncListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch vibrant =
                                palette.getVibrantSwatch();
                        int mutedColor = palette.getVibrantSwatch().getRgb();
                        if (vibrant != null) {
                            // If we have a vibrant color
                            // update the title TextView
                            mCtl.setBackgroundColor(mutedColor);
                            mCtl.setStatusBarScrimColor(palette.getDarkMutedColor(mutedColor));
                            mCtl.setContentScrimColor(palette.getMutedColor(mutedColor));

                            //change appbar color
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                            window.setStatusBarColor(palette.getMutedColor(mutedColor));
                        }
                    }
                });
    }

}
