package com.nuon.peter.demoapp.rest.holder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.databinding.LayoutSocialItemBinding;

/**
 * Created by manithnuon on 6/22/17.
 */

public class SocialHolder extends RecyclerView.ViewHolder {
    public LayoutSocialItemBinding binding;
    private PostClickListener mListener;

    public enum LikeStatus { LIKED, NOT_LIKED }

    public SocialHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        binding.loveContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toggleLike();
            }
        });
        binding.echoContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toggleEcho();
            }
        });

        binding.commentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.showComments();
            }
        });

    }

    public void setLikeStatus(LikeStatus status, Context context) {
        binding.heart.setImageDrawable(ContextCompat.getDrawable(context,
                status == LikeStatus.LIKED ? R.drawable.ic_favorite_pink_24dp : R.drawable.ic_favorite_black_24dp));
    }

    public void setNumLikes(long numLikes) {
        String suffix = " like";
        if(numLikes == 0) {
            binding.loved.setText(numLikes + suffix);
        } else if(numLikes == 1){
            binding.loved.setText(numLikes + suffix);
        } else if(numLikes >1){
            suffix =  " likes";
            binding.loved.setText(numLikes + suffix);
        }

    }
    public void setNumEchos(long numLikes) {
        String suffix = " echo";
        if(numLikes == 0) {
            binding.echoMe.setText(numLikes + suffix);
        } else if(numLikes == 1){
            binding.echoMe.setText(numLikes + suffix);
        } else if(numLikes >1){
            suffix =  " echos";
            binding.echoMe.setText(numLikes + suffix);
        }

    }

    public void setNumCommment(long numLikes) {
        String suffix = " comment";
        if(numLikes == 0) {
            binding.comment.setText(numLikes + suffix);
        } else if(numLikes == 1){
            binding.comment.setText(numLikes + suffix);
        } else if(numLikes >1){
            suffix =  " comments";
            binding.comment.setText(numLikes + suffix);
        }

    }

    public void setLikeColorStatus(LikeStatus status, Context context) {
        binding.likeMe.setTextColor(ContextCompat.getColor(context,
                status == LikeStatus.LIKED ? R.color.md_pink_A400 : R.color.md_black_1000));
    }


    public void setPostClickListener(PostClickListener listener) {
        mListener = listener;
    }

    public interface PostClickListener {
        void showComments();
        void toggleLike();
        void toggleEcho();
    }


}