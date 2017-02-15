package uce.optativa.androidchat.appcomputronik.chat.ui;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import uce.optativa.androidchat.appcomputronik.AndroidChatApplication;
import uce.optativa.androidchat.appcomputronik.R;
import uce.optativa.androidchat.appcomputronik.chat.ChatPresenter;
import uce.optativa.androidchat.appcomputronik.chat.ChatPresenterImpl;
import uce.optativa.androidchat.appcomputronik.chat.adapters.ChatAdapter;
import uce.optativa.androidchat.appcomputronik.chat.entities.ChatMessage;
import uce.optativa.androidchat.appcomputronik.domain.AvatarHelper;
import uce.optativa.androidchat.appcomputronik.lib.ImageLoader;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class ChatActivity extends AppCompatActivity
        implements ChatView{

        //SwipeRefreshLayout.OnRefreshListener

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtUser)
    TextView txtUser;
    @BindView(R.id.txtStatus)
    TextView txtStatus;
    @BindView(R.id.editTxtMessage)
    EditText inputMessage;
    @BindView(R.id.messageRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;

    public final static String EMAIL_KEY = "email";
    public final static String ONLINE_KEY = "online";

    private ChatAdapter adapter;
    private ChatPresenter chatPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        //swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.load_rest);
        //swipeRefreshLayout.setOnRefreshListener(this);
        chatPresenter = new ChatPresenterImpl(this);
        chatPresenter.onCreate();

        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        setToolbarData(intent);

        setupAdapter();
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        chatPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        chatPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        chatPresenter.onDestroy();
        super.onDestroy();
    }

    private void setToolbarData(Intent i) {
        String recipient = i.getStringExtra(EMAIL_KEY);
        chatPresenter.setChatRecipient(recipient);

        boolean online = i.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);

       // Application app =  getApplication();
        //ImageLoader imageLoader = app.getClassLoader();
        //imageLoader.load(imgAvatar, AvatarHelper.getAvatarUrl(recipient));
    }

    private void setupAdapter() {
        adapter = new ChatAdapter(this, new ArrayList<ChatMessage>());
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    @OnClick(R.id.btnSendMessage)
    public void sendMessage() {
        chatPresenter.sendMessage(inputMessage.getText().toString());
        inputMessage.setText("");
    }

    @Override
    public void onMessageReceived(ChatMessage msg) {
        adapter.add(msg);
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }

    /*@Override
    public void onRefresh() {
        Toast.makeText(getApplicationContext(),
                "Toast por defecto", Toast.LENGTH_SHORT);
    }
    */
}