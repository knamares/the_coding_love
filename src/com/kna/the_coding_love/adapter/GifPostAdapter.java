package com.kna.the_coding_love.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kna.the_coding_love.R;
import com.kna.the_coding_love.interfaze.OnResponseGetImageUrl;
import com.kna.the_coding_love.model.GifPost;
import com.kna.the_coding_love.net.NetManager;

public class GifPostAdapter extends ArrayAdapter<GifPost> {

	private String LOG_TAG = "----->TimeLineActivity";

	private final Activity activity;
	private final ArrayList<GifPost> gifPosts;

	private LayoutInflater layoutInflater;

	static class Header {
		public String string;
	}

	static class ViewHolder {
		public TextView title;
		public ImageView image;
		public TextView username;
		public ProgressBar progressBarImageLoader;
	}

	public GifPostAdapter(Activity activity, ArrayList<GifPost> gifPosts) {
		super(activity, R.layout.gif_post, gifPosts);
		this.gifPosts = gifPosts;
		this.activity = activity;

		layoutInflater = activity.getLayoutInflater();

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// header
		if (position == 0) {
			if (convertView == null || convertView.findViewById(R.id.relativeLayoutGifPostHeader) == null) {
				convertView = layoutInflater.inflate(R.layout.gif_post_header, null);
			}
		} else {
			final ViewHolder holder;

			// check if this view contained a header
			if (convertView == null || convertView.findViewById(R.id.relativeLayoutGifPostHeader) != null) {
				holder = new ViewHolder();

				convertView = layoutInflater.inflate(R.layout.gif_post, null);
				holder.title = (TextView) convertView.findViewById(R.id.textViewGifPostTitle);
				holder.image = (ImageView) convertView.findViewById(R.id.imageViewGifPostTimeLineHeadLogo);
				holder.username = (TextView) convertView.findViewById(R.id.textViewGifPostUsername);
				holder.progressBarImageLoader = (ProgressBar) convertView.findViewById(R.id.progressBarImageLoader);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			GifPost gifPost = gifPosts.get(position);
			holder.title.setText(gifPost.getTitle());
			holder.progressBarImageLoader.setVisibility(View.VISIBLE);
			holder.image.setImageDrawable(getContext().getResources().getDrawable(R.drawable.gif_post_default));
			
			// Load image
			NetManager.getInstance(activity).getImageFromUrl(gifPost.getImageUrl(), new OnResponseGetImageUrl() {

				@Override
				public void onResponseGetImageUrlError(Exception e) {
					Log.d(LOG_TAG, "onResponseGetImageUrlError error: " + e.getMessage());

					activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							holder.progressBarImageLoader.setVisibility(View.GONE);
						}
					});

				}

				@Override
				public void onResponseGetImageUrl(final Bitmap bitmap) {
					Log.d(LOG_TAG, "onResponseGetImageUrl");

					activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							holder.image.setImageBitmap(bitmap);
							holder.image.setVisibility(View.VISIBLE);
							holder.progressBarImageLoader.setVisibility(View.GONE);
						}
					});
				}
			});

			holder.username.setText(gifPost.getUsername());

		}

		return convertView;

	}

}
