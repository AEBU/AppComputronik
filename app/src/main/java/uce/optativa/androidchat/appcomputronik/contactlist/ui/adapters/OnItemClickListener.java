package uce.optativa.androidchat.appcomputronik.contactlist.ui.adapters;


import uce.optativa.androidchat.appcomputronik.entities.User;

/**
 * Created by Alexis on 29/12/2016.
 */

public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);

}
