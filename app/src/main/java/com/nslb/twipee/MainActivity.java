package com.nslb.twipee;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.OAuthProvider;
import com.nslb.twipee.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button logout;
    private Intent intent;

    public String[] mUserInfos;
    public ArrayList<String> mKeywordStrArray;
    SectionsPagerAdapter sectionsPagerAdapter = null;

    private int[] tabIcons={
            R.drawable.ic_web_black_24dp,
            R.drawable.ic_chat_bubble_outline_black_24dp,
            R.drawable.ic_thumb_up_black_24dp,
            R.drawable.ic_account_box_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = (Button)findViewById(R.id.logout);

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        for (int i = 0; i < sectionsPagerAdapter.getCount(); i++)
        {
            tabs.getTabAt(i).setIcon(tabIcons[i]);
        }

        if(getIntent().getExtras() != null){
            intent = getIntent();
            mUserInfos = new String[4];
            mUserInfos = intent.getExtras().getStringArray("LoginInfo");
            mKeywordStrArray = intent.getExtras().getStringArrayList("KeywordStrArray");

            intent = null;

        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), Login.class);
                startActivity(intent2);
                finish();
            }
        });
    }

}











