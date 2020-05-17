package com.saket.weatherapp_attempt2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.saket.weatherapp_attempt2.ui.MasterListFragment;

/*
So this is my 2nd attempt at the weather info app.
Following are things i did differently this time -

1. Share data between fragments using livedata and viewmodel.
2. Due to above, did not use parceleable.
3. Rxjava, used merge instead of zip. As it seems better suited.
4. Understood that flatmap/map is good to update existing data but it is difficult to convert to another data type using such.
5.  The aim was to complete this within a day. But it still took almost a week.
6. Understood how to properly handle item click listener in recyclerview adapter.
7. Understood that Runnable and Callable do not create separate thread by themselves they are simply functional interfaces.
8. Runnable and Callable can be used with ExecutorService to send background api requests.
9. Data-binding dependency related build failure. it got resolved by changing java sdk path to embedded jdk in project structure.
10. Handling actionbar navigation.
 */

//Single activity architecture
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add list fragment to container
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, MasterListFragment.newInstance())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getSupportFragmentManager().popBackStack();
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayBackArrow() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
