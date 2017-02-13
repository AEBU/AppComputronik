package uce.optativa.androidchat.appcomputronik.model;


import static android.R.attr.id;

/**
 * Created by Alexis on 09/01/2017.
 */
public class User {

    private long usrId;

    private String usrEmail;

    private String usrPassword;


    public User(String usrEmail, String usrPassword) {
        this.usrEmail = usrEmail;
        this.usrPassword = usrPassword;
    }

    public User(long usrId, String usrEmail, String usrPassword) {
        this.usrId = usrId;
        this.usrEmail = usrEmail;
        this.usrPassword = usrPassword;
    }

    public long getUsrId() {
        return usrId;
    }

    public void setUsrId(long usrId) {
        this.usrId = usrId;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }
}




