package com.rahmacom.rimesyarifix.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.MainActivityBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    private AppBarConfiguration mAppBarConfig;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setSupportActionBar(binding.mainAppBar.mainToolbar);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().
                        findFragmentById(R.id.nav_host_fragment_activity_main);
        navController = navHostFragment.getNavController();

        mAppBarConfig = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_status_order,
                R.id.nav_riwayat_transaksi,
                R.id.nav_akun_saya
        ).build();

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.nav_akun_saya
                    || destination.getId() == R.id.nav_home
                    || destination.getId() == R.id.nav_status_order
                    || destination.getId() == R.id.nav_riwayat_transaksi) {
                binding.navView.setVisibility(View.VISIBLE);
            } else {
                binding.navView.setVisibility(View.INVISIBLE);
            }
        });

        // NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfig);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}