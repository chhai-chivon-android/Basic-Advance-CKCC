package com.nuon.peter.demoapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.nuon.peter.demoapp.firebase.DatabaseUtils;
import com.nuon.peter.demoapp.firebase.entity.Comment;
import com.nuon.peter.demoapp.firebase.entity.People;
import com.nuon.peter.demoapp.rest.holder.CommentHolder;
import com.nuon.peter.demoapp.rest.holder.SocialHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by manithnuon on 6/23/17.
 */

public class CommentActivity extends AppCompatActivity {

    @BindView(R.id.comment_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.send_comment)
    LinearLayout sendBtn;
    @BindView(R.id.comment_ed)
    EditText mEditText;
    @BindView(R.id.toolbar_app)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title_item)
    TextView title;
    private FirebaseRecyclerAdapter adapter;
    private String postKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(R.anim.bottom_in,R.anim.bottom_out);
        postKey = getIntent().getStringExtra("key");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_comment_movies);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        title.setText("Comment");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        final DatabaseReference query = DatabaseUtils.getInstance().getCommentsRef().child(postKey);
        adapter = new FirebaseRecyclerAdapter<Comment,CommentHolder>(Comment.class, R.layout.layout_comment_item,CommentHolder.class,query) {
            @Override
            protected void populateViewHolder(final CommentHolder viewHolder, Comment model, int position) {
                viewHolder.binding.setComment(model);
                final String postKey = adapter.getRef(position).getKey();
                ValueEventListener listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        viewHolder.setNumLikes(dataSnapshot.getChildrenCount());
                        if (dataSnapshot.hasChild(DatabaseUtils.getInstance().getCurrentUserId())) {
                            viewHolder.setLikeStatus(CommentHolder.LikeStatus.LIKED, getBaseContext());
                        } else {
                            viewHolder.setLikeStatus(CommentHolder.LikeStatus.NOT_LIKED, getBaseContext());
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                DatabaseUtils.getInstance().getLikeCommentRef().child(postKey).addValueEventListener(listener);
                viewHolder.setCommentClickListener(new CommentHolder.CommentClickListener() {
                    @Override
                    public void replayComments() {

                    }

                    @Override
                    public void toggleLike() {
                        final String userKey = DatabaseUtils.getInstance().getCurrentUserId();
                        final DatabaseReference commentLikesRef = DatabaseUtils.getInstance().getLikeCommentRef();
                        commentLikesRef.child(postKey).child(userKey).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    // User already liked this post, so we toggle like off.
                                    commentLikesRef.child(postKey).child(userKey).removeValue();
                                } else {
                                    commentLikesRef.child(postKey).child(userKey).setValue(ServerValue.TIMESTAMP);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError firebaseError) {

                            }
                        });
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

        mEditText.setHint("Enter Comment");
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter
                (256)});
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    sendBtn.setEnabled(true);
                } else {
                    sendBtn.setEnabled(false);
                }
            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear input box and hide keyboard.
                final Editable commentText = mEditText.getText();
                mEditText.setText("");
                InputMethodManager inputManager =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(
                        mEditText.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null) {
                    Toast.makeText(v.getContext(), "Login required",
                            Toast.LENGTH_SHORT).show();
                }


                Comment comment = new Comment(commentText.toString(),ServerValue.TIMESTAMP,
                        new People(user.getEmail(),user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : null,user.getDisplayName(),user.getUid()));
                query.push().setValue(comment, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError error, DatabaseReference firebase) {
                        if (error != null) {
                            Toast.makeText(getBaseContext(), "Error posting comment.", Toast
                                    .LENGTH_SHORT).show();
                            mEditText.setText(commentText);
                        }
                    }
                });
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.bottom_in,R.anim.bottom_out);
    }
}
