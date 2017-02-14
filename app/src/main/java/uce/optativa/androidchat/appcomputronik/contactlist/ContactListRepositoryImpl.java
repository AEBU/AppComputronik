package uce.optativa.androidchat.appcomputronik.contactlist;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import uce.optativa.androidchat.appcomputronik.contactlist.events.ContactListEvent;
import uce.optativa.androidchat.appcomputronik.domain.FirebaseHelper;
import uce.optativa.androidchat.appcomputronik.entities.User;
import uce.optativa.androidchat.appcomputronik.lib.EventBus;
import uce.optativa.androidchat.appcomputronik.lib.GreenRobotEventBus;


/**
 * Created by Alexis on 29/12/2016.
 */
public class ContactListRepositoryImpl implements ContactListRepository {
    private FirebaseHelper helper;
    private ChildEventListener contactEventListener;

    public ContactListRepositoryImpl() {
        this.helper=FirebaseHelper.getInstance();
    }

    @Override
    public void signOff() {
        helper.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail= helper.getAuthUserEmail();
        helper.getOneContactReference(currentUserEmail,email).removeValue();
        helper.getOneContactReference(email,currentUserEmail).removeValue();

    }

    @Override
    public void destroyListener() {
        contactEventListener=null;
    }

    @Override
    public void subscribeToContactListEvents() {

        if (contactEventListener == null) {
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                    String email = dataSnapshot.getKey();
                    email = email.replace("_",".");
                    boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
                    User user = new User(email, online, null);
                    postEvent(ContactListEvent.onContactAdded, user);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {
                    String email = dataSnapshot.getKey();
                    email = email.replace("_",".");
                    boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
                    User user = new User(email, online, null);
                    postEvent(ContactListEvent.onContactChanged, user);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String email = dataSnapshot.getKey();
                    email = email.replace("_",".");
                    boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
                    User user = new User(email, online, null);
                    postEvent(ContactListEvent.onContactRemoved, user);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError firebaseError) {}
            };
        }

        helper.getMyContactsReference().addChildEventListener(contactEventListener);    }

    private void handleContact(DataSnapshot dataSnapshot, int type) {
        String email= dataSnapshot.getKey();
        email = email.replace("_",".");
        boolean online= ((Boolean) dataSnapshot.getValue()).booleanValue();
        User user = new User();
        user.setEmail(email);
        user.setOnline(online);
        post(type,user);
    }

    private void post(int type, User user) {
        ContactListEvent event= new ContactListEvent();
        event.setEventType(type);
        event.setUser(user);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);

    }
    private void postEvent(int type, User user) {
        ContactListEvent contactListEvent = new ContactListEvent();
        contactListEvent.setEventType(type);
        contactListEvent.setUser(user);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(contactListEvent);
    }

    @Override
    public void unsubscribeToContactListEvents() {
        if (contactEventListener != null){
            helper.getMyContactsReference().removeEventListener(contactEventListener);
        }
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }
}
