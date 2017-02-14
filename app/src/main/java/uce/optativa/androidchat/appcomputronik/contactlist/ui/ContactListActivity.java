package uce.optativa.androidchat.appcomputronik.contactlist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uce.optativa.androidchat.appcomputronik.R;
import uce.optativa.androidchat.appcomputronik.addcontact.ui.AddContactFragment;
import uce.optativa.androidchat.appcomputronik.chat.ui.ChatActivity;
import uce.optativa.androidchat.appcomputronik.contactlist.ContactListPresenter;
import uce.optativa.androidchat.appcomputronik.contactlist.ContactListPresenterImpl;
import uce.optativa.androidchat.appcomputronik.contactlist.ui.adapters.ContactListAdapter;
import uce.optativa.androidchat.appcomputronik.contactlist.ui.adapters.OnItemClickListener;
import uce.optativa.androidchat.appcomputronik.entities.User;
import uce.optativa.androidchat.appcomputronik.lib.GlideImageLoader;
import uce.optativa.androidchat.appcomputronik.lib.ImageLoader;

public class ContactListActivity extends AppCompatActivity implements ContactListView,OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewContacts)
    RecyclerView recyclerViewContacts;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private ContactListPresenter presenter;
    private ContactListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();

        presenter = new ContactListPresenterImpl(this);

        presenter.onCreate();
        //toolbar.setTitle(presenter.getCurrentUserEmail());
        //setSupportActionBar(toolbar);
        setupToolbar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_contactlist,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*if (item.getItemId() == R.id.action_logout){
            presenter.signOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        */
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(adapter);
    }

    private void setupToolbar(){
        toolbar.setTitle(presenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);

    }
    private void setupAdapter(){
        ImageLoader imageLoader = new GlideImageLoader(this.getApplicationContext());
/*
        User user= new User();
        user.setOnline(false);
        user.setEmail("alexis23210@gmail.com");
*/
        adapter = new ContactListAdapter(new ArrayList<User>(), imageLoader, this);
    }


    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }
    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @OnClick(R.id.fab)
    public void addContact(){
        new AddContactFragment().show(getSupportFragmentManager(),getString(R.string.addcontact_message_title));

     }
    @Override
    public void onContactAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);
    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
       // Toast.makeText(this,user.getEmail(),Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.EMAIL_KEY,user.getEmail());
        intent.putExtra(ChatActivity.ONLINE_KEY,user.isOnline());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(User user) {
        presenter.removeContact(user.getEmail());
    }

}
