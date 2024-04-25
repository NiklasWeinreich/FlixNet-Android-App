package com.example.flixnet_android_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.flixnet_android_app.fragments.MovieFragment;
import com.example.flixnet_android_app.fragments.SearchFragment;
import com.example.flixnet_android_app.fragments.SeriesFragment;
import com.example.flixnet_android_app.fragments.StartFragment;
import com.example.flixnet_android_app.fragments.WatchlistFragment;

public class MainActivity extends AppCompatActivity {

    public static RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rq = Volley.newRequestQueue(getApplicationContext());
        initGui();
        fragmentChanger(StartFragment.class);

    }

    void initGui(){
        findViewById(R.id.nav_movie).setOnClickListener(view -> fragmentChanger(MovieFragment.class));
        findViewById(R.id.nav_series).setOnClickListener(view -> fragmentChanger(SeriesFragment.class));
        findViewById(R.id.nav_watchlist).setOnClickListener(view -> fragmentChanger(WatchlistFragment.class));
        findViewById(R.id.btn_search).setOnClickListener(view -> fragmentChanger(SearchFragment.class));

    }
    private void fragmentChanger(Class c) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, c, null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
    }
}