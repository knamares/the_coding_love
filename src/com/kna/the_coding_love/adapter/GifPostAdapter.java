package com.kna.the_coding_love.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kna.the_coding_love.R;
import com.kna.the_coding_love.model.GifPost;

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
		public WebView image;
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
				holder.image = (WebView) convertView.findViewById(R.id.imageViewGifPostTimeLineHeadLogo);

				holder.username = (TextView) convertView.findViewById(R.id.textViewGifPostUsername);
				holder.progressBarImageLoader = (ProgressBar) convertView.findViewById(R.id.progressBarImageLoader);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			GifPost gifPost = gifPosts.get(position);
			holder.title.setText(gifPost.getTitle());
			holder.progressBarImageLoader.setVisibility(View.VISIBLE);

			// // Load image
			// NetManager.getInstance(activity).getImageFromUrl(gifPost.getImageUrl(),
			// new OnResponseGetImageUrl() {
			//
			// @Override
			// public void onResponseGetImageUrlError(Exception e) {
			// Log.d(LOG_TAG, "onResponseGetImageUrlError error: " +
			// e.getMessage());
			//
			// activity.runOnUiThread(new Runnable() {
			// @Override
			// public void run() {
			// holder.progressBarImageLoader.setVisibility(View.GONE);
			// }
			// });
			//
			// }
			//
			// @Override
			// public void onResponseGetImageUrl(final Bitmap bitmap) {
			// Log.d(LOG_TAG, "onResponseGetImageUrl");
			//
			// activity.runOnUiThread(new Runnable() {
			// @Override
			// public void run() {
			// holder.progressBarImageLoader.setVisibility(View.GONE);
			// }
			// });
			// }
			// });

			holder.image.getSettings().setUserAgentString("Chrome");
			holder.image.loadUrl(gifPost.getImageUrl());
			holder.image.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
			holder.image.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
			holder.image.setHorizontalScrollBarEnabled(false);
			holder.image.setVerticalScrollBarEnabled(false);
			holder.image.getSettings().setJavaScriptEnabled(true);
			holder.image.loadUrl("javascript:alert('Yeah')");
		
			holder.image.setWebViewClient(new WebViewClient() {
				@Override
				public void onPageFinished(WebView view, String url) {
					
					holder.progressBarImageLoader.setVisibility(View.GONE);
					holder.image.setVisibility(View.VISIBLE);
					
				}
				
				@Override
				public void onPageStarted(WebView view, String url, Bitmap favicon) {
					holder.progressBarImageLoader.setVisibility(View.VISIBLE);
//					holder.image.setVisibility(View.GONE);
				}
					
				});

			holder.username.setText(gifPost.getUsername());

		}

		return convertView;

	}

}
