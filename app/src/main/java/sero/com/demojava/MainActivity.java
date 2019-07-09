package sero.com.demojava;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();


            switch (item.getItemId()) {
                case R.id.navigation_home:
                    ft.replace(R.id.fragment_container, new HomeFragment()).commit();
                    return true;
                case R.id.navigation_dashboard:
                    ft.replace(R.id.fragment_container, new DashBoardFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    ft.replace(R.id.fragment_container, new NotificationFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, new HomeFragment());
        ft.commit();
    }

}
