package com.example.nativeformmakerandroid;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import com.example.nativeformmakerandroid.helper.LogHelper;


public class ActivityDisplayImage extends TenthActivity {
    View loading, close_btn;

    @Override
    public String getName() {
        return "Display Image";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_nowhere);

        String url = getIntent().getExtras().getString("address");
        String mode = getIntent().getExtras().getString("mode");


        setUpElements();
        setUpOnClicks();
        DisplayImageOnScreen(mode, url);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public int getLayoutResourceID() {
        return R.layout.act_display_image;
    }

    @Override
    public void setUpElements() {

        loading = findViewById(R.id.progressBar1);
        close_btn = findViewById(R.id.close_btn);
    }

    @Override
    public void setUpElementsWithData() {
    }

    @Override
    public void setUpOnClicks() {
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.slide_nowhere, R.anim.slide_out_bottom);
        super.onPause();
    }

    public void DisplayImageOnScreen(String Mode, String address) {

        try {
            if (Mode.contentEquals("url")) {
                Picasso.with(this).load(address).into((ImageView) findViewById(R.id.act_display_img_image));
            } else {
                File f = new File(address);
              //  int file_size = Integer.parseInt(String.valueOf(f.length() ));
                LogHelper.d("address_path:", address /*+ "  size : " + String.valueOf(file_size)*/);

                Uri uri = Uri.fromFile(new File(address));
                Picasso.with(this).load(uri)
                        /*.resize(90, 90).centerCrop()*/.into((ImageView) findViewById(R.id.act_display_img_image));

                //Picasso.with(this).load(new File(adderss)).into((ImageView) findViewById(R.id.act_display_img_image));

            }
        } catch (Exception e) {
            loading.setVisibility(View.GONE);
            e.printStackTrace();
        }
        loading.setVisibility(View.GONE);

    }
}
