package uce.optativa.androidchat.appcomputronik.addcontact;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import uce.optativa.androidchat.appcomputronik.addcontact.events.AddContactEvent;
import uce.optativa.androidchat.appcomputronik.domain.FirebaseHelper;
import uce.optativa.androidchat.appcomputronik.entities.User;
import uce.optativa.androidchat.appcomputronik.lib.EventBus;
import uce.optativa.androidchat.appcomputronik.lib.GreenRobotEventBus;

/**
 * Created by ykro.
 */
public class AddContactRepositoryImpl implements AddContactRepository {
    @Override
    public void addContact(final String email) {
        final String key = email.replace(".","_");

        FirebaseHelper helper = FirebaseHelper.getInstance();
        final DatabaseReference userReference = helper.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                AddContactEvent event = new AddContactEvent();
                if (user != null) {
                    boolean online = user.isOnline();
                    FirebaseHelper helper = FirebaseHelper.getInstance();

                    DatabaseReference userContactsReference = helper.getMyContactsReference();
                    userContactsReference.child(key).setValue(online);

                    String currentUserEmailKey = helper.getAuthUserEmail();
                    currentUserEmailKey = currentUserEmailKey.replace(".","_");
                    DatabaseReference reverseUserContactsReference = helper.getContactsReference(email);
                    reverseUserContactsReference.child(currentUserEmailKey).setValue(true);
                } else {
                    event.setError(true);
                }
                EventBus eventBus = GreenRobotEventBus.getInstance();
                eventBus.post(event);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {}
        });
    }
}
