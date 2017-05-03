package com.soomla.soomlatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAppOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.soomla.traceback.SoomlaTraceback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ad_button)
    Button adButton;
    @BindView(R.id.ad_layout)
    FrameLayout adLayout;

    AdColonyInterstitial _ad = null;
    AdColonyInterstitialListener _listener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        setupSoomla();

        setupAdColony();
    }

    private void setupAdColony() {
        configure();
        request();
    }

    private void setupSoomla() {    // The appKey from the App's Settings page.
        String appKey = "06a38aec-0807-4faf-becb-03e8e48ca71f";

        // Can be IDFA, GAID or anything else you decide to identify the user by.
        String userId = "soomla.test.com";

        SoomlaTraceback.getInstance().initialize(this, appKey, userId);

    }

    @OnClick(R.id.ad_button)
    public void adButtonClicked(View view) {
        show();
    }

    void configure() {
        AdColonyAppOptions adColonyAppOptions = new AdColonyAppOptions();
        AdColony.configure(this,adColonyAppOptions , "app81597651263d4bd0b1", "vzd0ecdbcad1f74feb90");

        _listener = new AdColonyInterstitialListener() {
            @Override
            public void onRequestFilled(AdColonyInterstitial ad) {
                _ad = ad;
            }
        };
    }

    void request() {
        AdColony.requestInterstitial("vzd0ecdbcad1f74feb90", _listener, null);
    }

    void show() {
        if (_ad != null) {
            _ad.show();
        } else
            Toast.makeText(this, "No ad served", Toast.LENGTH_SHORT).show();
    }

}
