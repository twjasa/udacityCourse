package com.example.android.musicapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fragmentManager = getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_artist);
        fragmentManager.beginTransaction().replace(R.id.content_frame,new artistFragment()).commit();
//        final RelativeLayout playingNow = (RelativeLayout) findViewById(R.id.playingNowRL);
//        //listener "playing now"
//        playingNow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent playingNowIntent = new Intent(MainActivity.this, PlayingNowFragment.class);
//                startActivity(playingNowIntent);
//            }
//        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_artist) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new artistFragment()).commit();
            this.setTitle("Artist");
        } else if (id == R.id.nav_playlist) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new playlistFragment()).commit();
            this.setTitle("Playlist");
        } else if (id == R.id.nav_albums) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new albumsFragment()).commit();
            this.setTitle("Albums");
        } else if (id == R.id.nav_songs) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new songsFragment()).commit();
            this.setTitle("Songs");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void openPlayingNow (View view) {
        fragmentManager.beginTransaction().replace(R.id.content_frame,new PlayingNowFragment()).commit();
        this.setTitle("Playing Now");
    }
    public void openAlbum (View view) {
        fragmentManager.beginTransaction().replace(R.id.content_frame,new albumsFragment()).commit();
        this.setTitle("Albums");
    }
}
