package com.nuon.peter.demoapp.firebase;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nuon.peter.demoapp.firebase.entity.People;

/**
 * Created by manithnuon on 6/21/17.
 */

public class DatabaseUtils {

    //private static final DatabaseUtils ourInstance = new DatabaseUtils();
    //public static DatabaseUtils getInstance() { return ourInstance; }

    private static DatabaseUtils instance;

    public static DatabaseUtils getInstance() {
        synchronized (DatabaseUtils.class){
            if(instance == null){
                FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                instance = new DatabaseUtils();
            }

        }
        return instance;
    }

    public DatabaseReference getDatabaseRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public String getCurrentUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            return user.getUid();
        }
        return null;
    }

    public People getCurrentPeople() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) return null;
        return new People(user.getEmail(),user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : null,user.getDisplayName(), user.getUid());
    }

    public DatabaseReference getCurrentUserRef() {
        String uid = getCurrentUserId();
        if(uid != null) {
            return getDatabaseRef().child("people").child(uid);
        }
        return null;
    }

    public DatabaseReference getPeopleRef() {
        return getDatabaseRef().child("people");
    }

    public String getPeoplePath() {
        return "people/";
    }

    public DatabaseReference getPostRef() {
        return getDatabaseRef().child("posts");
    }
    public String getPostPath() {
        return "posts/";
    }

    public  DatabaseReference getLikesRef() {
        return getDatabaseRef().child("likes");
    }

    public  DatabaseReference getEchoRef() {
        return getDatabaseRef().child("echos");
    }

    public  DatabaseReference getCommentsRef() {
        return getDatabaseRef().child("comments");
    }

    public  DatabaseReference getLikeCommentRef() {
        return getDatabaseRef().child("likecomm");
    }


}
