package com.kna.the_coding_love.interfaze;

import java.util.ArrayList;

import com.kna.the_coding_love.model.GifPost;

public interface OnResponseGetGifPost {

	public void onResponseGetGifPost(ArrayList<GifPost> gifPosts);
	public void onResponseGetGifPostError(Exception e);
	
}
