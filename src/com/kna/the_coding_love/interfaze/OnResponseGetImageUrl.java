package com.kna.the_coding_love.interfaze;

import android.graphics.Bitmap;

public interface OnResponseGetImageUrl {

	public void onResponseGetImageUrl(Bitmap bitmap);
	public void onResponseGetImageUrlError(Exception e);
	
}
