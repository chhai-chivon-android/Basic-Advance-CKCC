package com.nuon.peter.demoapp.rest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.pwittchen.prefser.library.Prefser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.nuon.peter.demoapp.AppFragment;
import com.nuon.peter.demoapp.CommentActivity;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.firebase.DatabaseUtils;
import com.nuon.peter.demoapp.firebase.entity.Post;

/**
 * Created by manithnuon on 4/21/17.
 */

public class RestActivityWithFragment extends AppCompatActivity implements SocialFragment.OnPostSelectedListener{
  Prefser prefser;
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    overridePendingTransition(R.anim.right_in,R.anim.right_out);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_activity_with_fragment);
    prefser = new Prefser(this);
    getSupportFragmentManager().beginTransaction()
        .add(R.id.container, new RestFragment())
        .commit();
  }


  @Override
  public void onPostComment(String postKey, Post post) {
    Intent intent = new Intent(this, CommentActivity.class);
    intent.putExtra("key",postKey);
    startActivity(intent);
  }

  @Override
  public void onPostLike(final String postKey) {
    final String userKey = DatabaseUtils.getInstance().getCurrentUserId();
    final DatabaseReference postLikesRef = DatabaseUtils.getInstance().getLikesRef();
    postLikesRef.child(postKey).child(userKey).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.exists()) {
          // User already liked this post, so we toggle like off.
          postLikesRef.child(postKey).child(userKey).removeValue();
        } else {
          postLikesRef.child(postKey).child(userKey).setValue(ServerValue.TIMESTAMP);
        }
      }

      @Override
      public void onCancelled(DatabaseError firebaseError) {

      }
    });

  }

  @Override
  public void onPostEcho(final String postKey) {
    final String userKey = DatabaseUtils.getInstance().getCurrentUserId();
    final DatabaseReference postEchoRef = DatabaseUtils.getInstance().getEchoRef();
    final String key = postEchoRef.child(postKey).push().getKey();
    postEchoRef.child(postKey).child(key).child(userKey).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        postEchoRef.child(postKey).child(key).child(userKey).setValue(ServerValue.TIMESTAMP);
      }

      @Override
      public void onCancelled(DatabaseError firebaseError) {

      }
    });
  }
}
