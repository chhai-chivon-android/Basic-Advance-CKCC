package com.nuon.peter.demoapp.rest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.app.App;
import com.nuon.peter.demoapp.firebase.DatabaseUtils;
import com.nuon.peter.demoapp.firebase.entity.Post;
import com.nuon.peter.demoapp.rest.holder.SocialHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by manithnuon on 6/22/17.
 */

public class SocialFragment extends Fragment {

    @BindView(R.id.social_recyclerview)
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter adapter;
    LinearLayoutManager manager;
    private OnPostSelectedListener mListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_social,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        manager = new LinearLayoutManager(App.getContext());
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        Query query = DatabaseUtils.getInstance().getPostRef();
        adapter = new FirebaseRecyclerAdapter<Post,SocialHolder>(Post.class, R.layout.layout_social_item,SocialHolder.class,query) {
            @Override
            protected void populateViewHolder(SocialHolder viewHolder, Post model, int position) {
                setupData(viewHolder,model,position,null);
            }
        };
        recyclerView.setAdapter(adapter);

    }

    private void setupData(final SocialHolder postViewHolder, final Post model, final int position, String inPostKey) {
        postViewHolder.binding.setPost(model);
        final String postKey;
        if (adapter instanceof FirebaseRecyclerAdapter) {
            postKey = adapter.getRef(position).getKey();
        } else {
            postKey = inPostKey;
        }
        ValueEventListener likeListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(getActivity()!=null) {
                    postViewHolder.setNumLikes(dataSnapshot.getChildrenCount());
                    if (dataSnapshot.hasChild(DatabaseUtils.getInstance().getCurrentUserId())) {
                        postViewHolder.setLikeStatus(SocialHolder.LikeStatus.LIKED, getActivity());
                        postViewHolder.setLikeColorStatus(SocialHolder.LikeStatus.LIKED,getContext());
                    } else {
                        postViewHolder.setLikeStatus(SocialHolder.LikeStatus.NOT_LIKED, getActivity());
                        postViewHolder.setLikeColorStatus(SocialHolder.LikeStatus.NOT_LIKED,getContext());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ValueEventListener echoListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postViewHolder.setNumEchos(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ValueEventListener commentListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postViewHolder.setNumCommment(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        DatabaseUtils.getInstance().getCommentsRef().child(postKey).addValueEventListener(commentListener);
        DatabaseUtils.getInstance().getEchoRef().child(postKey).addValueEventListener(echoListener);
        DatabaseUtils.getInstance().getLikesRef().child(postKey).addValueEventListener(likeListener);
        postViewHolder.setPostClickListener(new SocialHolder.PostClickListener() {
            @Override
            public void showComments() {
                mListener.onPostComment(postKey,model);
            }

            @Override
            public void toggleLike() {
                mListener.onPostLike(postKey);
            }

            @Override
            public void toggleEcho() {
                mListener.onPostEcho(postKey);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null && adapter instanceof FirebaseRecyclerAdapter) {
            adapter.cleanup();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPostSelectedListener) {
            mListener = (OnPostSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPostSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface OnPostSelectedListener {
        void onPostComment(String postKey, Post post);

        void onPostLike(String postKey);

        void onPostEcho(String postKey);
    }
}
