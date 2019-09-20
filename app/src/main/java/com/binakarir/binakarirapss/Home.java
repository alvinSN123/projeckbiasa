package com.binakarir.binakarirapss;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bk.binakarir.binakarir.Fragment.BerandaFragment;
import com.bk.binakarir.binakarir.Fragment.HistoryFragment;
import com.bk.binakarir.binakarir.Fragment.InboxFragment;
import com.bk.binakarir.binakarir.Fragment.NotifikasiFragment;
import com.bk.binakarir.binakarir.Fragment.ProfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // kita set default nya Home Fragment
        loadFragment(new BerandaFragment());

// inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_bawah);

// beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    // method listener untuk logika pemilihan
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.home_menu:
                fragment = new BerandaFragment();
                break;
            case R.id.history_menu:
                fragment = new HistoryFragment();
                break;
            case R.id.notif_menu:
                fragment = new NotifikasiFragment();
                break;
            case R.id.inbox_menu:
                fragment = new InboxFragment();
                break;
            case R.id.user_menu:
                fragment = new ProfilFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
