package freedom.chancecon.chanceconapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import freedom.chancecon.chanceconapp.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CircularProgressBar circularProgressBar, circularProgressBarInside;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("HOME");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int animationDuration = 5000;
        //Circular Progressbar Outside
        circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgressbar);
        circularProgressBar.setProgressWithAnimation(65,animationDuration);
        circularProgressBar.setColor(ContextCompat.getColor(this,R.color.progressBarColor));
        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this,R.color.backgroundProgressBarColor));
        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));

        //Circular Progressbar Inside
        circularProgressBarInside = (CircularProgressBar) findViewById(R.id.circularProgressbarInside);
        circularProgressBarInside.setProgressWithAnimation(55, animationDuration);
        circularProgressBarInside.setColor(ContextCompat.getColor(this,R.color.progressBarColorInside));
        circularProgressBarInside.setBackgroundColor(ContextCompat.getColor(this,R.color.backgroundProgressBarColor));
        circularProgressBarInside.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        circularProgressBarInside.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.nav_home) {
        } else if (id == R.id.nav_project) {
        } else if (id == R.id.nav_news) {
        } else if (id == R.id.nav_contact) {
//            startActivity(new Intent(this, Contact.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
