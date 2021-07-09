package com.xadopu4.bankclient.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.xadopu4.bankclient.MainActivityViewModel;
import com.xadopu4.bankclient.R;
import com.xadopu4.bankclient.entities.Payment;
import com.xadopu4.bankclient.repository.Repository;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.util.Pair;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = Repository.getInstance();

        repository.getResponseCodeMessage().observe(this, new Observer<Pair<Integer, String>>() {
            @Override
            public void onChanged(Pair<Integer, String> integerStringPair) {
                Toast.makeText(MainActivity.this, integerStringPair.first.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(R.id.PaymentListFragment);

    }

}