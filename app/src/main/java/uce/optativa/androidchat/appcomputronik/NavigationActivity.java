package uce.optativa.androidchat.appcomputronik;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import uce.optativa.androidchat.appcomputronik.contactlist.ContactListPresenter;
import uce.optativa.androidchat.appcomputronik.contactlist.ui.ContactListActivity;

import static uce.optativa.androidchat.appcomputronik.constants.Constants.contact_num;


public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ContactListPresenter presenter;
    private FirebaseAuth mAuth;
    private FloatingActionButton call_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        call_number = (FloatingActionButton) findViewById(R.id.call_number);
        mAuth = FirebaseAuth.getInstance();


        call_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + contact_num));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });



        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.navigation, menu);
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
        //FragmentManager fm = getFragmentManager();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment=null;

        //nuevo

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //homeFragment=new HomeFragment();
            //fm.beginTransaction().replace(R.id.nav_content,homeFragment).commit();
            /*FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
            homeFragment= new HomeFragment();
            transaction.replace(R.id.nav_content,homeFragment);
            transaction.commit();
            */
            fragment=new HomeFragment();
            fragmentTransaction.replace(R.id.nav_content, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_gallery) {
            /*
            FragmentTransaction transaction2= getSupportFragmentManager().beginTransaction();
            chatFragment= new ChatFragment();
            transaction2.replace(R.id.nav_content,chatFragment);
            transaction2.commit();
            */
            fragment=new ChatFragment();
            fragmentTransaction.replace(R.id.nav_content, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(this, ContactListActivity.class));


        } else if (id == R.id.nav_manage) {
            fragment=new ContactViewFragment();
            fragmentTransaction.replace(R.id.nav_content, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_share) {
            //presenter.signOff();
            mAuth.getInstance().signOut();

            Log.d("Error", mAuth.toString());
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }

      //  fragmentTransaction.replace(R.id.nav_content, fragment);
        //fragmentTransaction.addToBackStack(null);
        //fragmentTransaction.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
