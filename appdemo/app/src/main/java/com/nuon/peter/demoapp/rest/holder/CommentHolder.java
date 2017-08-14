package com.nuon.peter.demoapp.rest.holder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nuon.peter.demoapp.R;
import com.nuon.peter.demoapp.databinding.LayoutCommentItemBinding;
import com.nuon.peter.demoapp.databinding.LayoutSocialItemBinding;

/**
 * Created by manithnuon on 6/22/17.
 */

public class CommentHolder extends RecyclerView.ViewHolder {
    public LayoutCommentItemBinding binding;
    private CommentClickListener mListener;

    public enum LikeStatus { LIKED, NOT_LIKED }

    public CommentHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        binding.likeComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toggleLike();
            }
        });

    }

    public void setLikeStatus(LikeStatus status, Context context) {
        binding.likeComment.setImageDrawable(ContextCompat.getDrawable(context,
                status == LikeStatus.LIKED ? R.drawable.ic_like_white : R.drawable.ic_like));
    }

    public void setNumLikes(long numLikes) {
        String suffix = " like";
        if(numLikes == 0) {

            binding.likeCommentTx.setVisibility(View.GONE);
        } else if(numLikes == 1){
            binding.likeCommentTx.setVisibility(View.VISIBLE);
            binding.likeCommentTx.setText(numLikes + suffix);
        } else if(numLikes >1){
            suffix =  " likes";
            binding.likeCommentTx.setVisibility(View.VISIBLE);
            binding.likeCommentTx.setText(numLikes + suffix);
        }

    }


    public void setCommentClickListener(CommentClickListener listener) {
        mListener = listener;
    }

    public interface CommentClickListener {
        void replayComments();
        void toggleLike();
    }


}