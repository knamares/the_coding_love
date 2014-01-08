package com.kna.the_coding_love.net;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kna.the_coding_love.interfaze.OnResponseGetGifPost;
import com.kna.the_coding_love.interfaze.OnResponseGetImageUrl;
import com.kna.the_coding_love.model.GifPost;
import com.kna.the_coding_love.parser.ParserHelper;

public class NetManager {

	private static String LOG_TAG = "----->NetManager";
	private static final String BASE_URL = "http://thecodinglove.com/";

	private static RequestQueue queue;

	public static NetManager getInstance(Context context) {
		Log.d(LOG_TAG, "getInstance");

		if (queue == null) {
			Log.d(LOG_TAG, "getInstance queue null. Create one.");
			queue = Volley.newRequestQueue(context);
		}

		return new NetManager();

	}

	public void getGifPost(final OnResponseGetGifPost onResponseGetGifPost) {

		Log.d(LOG_TAG, "getGifPost");

		final ArrayList<GifPost> gifPosts = new ArrayList<GifPost>();

		// Set the listener
		Listener<String> listener = new Listener<String>() {
			@Override
			public void onResponse(final String response) {
				Log.d(LOG_TAG, "getGifPost onResponse");

				new Thread(new Runnable() {
					public void run() {
						gifPosts.add(new GifPost("Header", null, null, null, null));

						// Parse request
						gifPosts.addAll(ParserHelper.getGifPosts(response));

						// TODO: Persistence request

						onResponseGetGifPost.onResponseGetGifPost(gifPosts);
					}
				}).start();

			}
		};

		ErrorListener errorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.d(LOG_TAG, "getGifPost onErrorResponse");
				onResponseGetGifPost.onResponseGetGifPostError(error);
			}
		};

		// Make request
		getStringRequest(BASE_URL, listener, errorListener);

	}

	private void getStringRequest(String url, Listener<String> listener, ErrorListener errorListener) {
		Log.d(LOG_TAG, "getStringRequest");
		StringRequest stringRequest = new StringRequest(Method.GET, url, listener, errorListener){
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = super.getHeaders();
				
				if (headers == null
			            || headers.equals(Collections.emptyMap())) {
			        headers = new HashMap<String, String>();
			    }
				
				headers.put("User-agent", "Chrome");
				
				return headers;
			}
		};
		
		queue.add(stringRequest);
	}

	public void getImageFromUrl(String url, final OnResponseGetImageUrl onResponseGetImageUrl) {
		Log.d(LOG_TAG, "getImageFromUrl");
		
		Listener<Bitmap> listener = new Listener<Bitmap>() {
			@Override
			public void onResponse(final Bitmap bitmap) {
				Log.d(LOG_TAG, "getImageFromUrl onResponse");

				new Thread(new Runnable() {
					public void run() {
						// TODO: Persistence request

						onResponseGetImageUrl.onResponseGetImageUrl(bitmap);
					}
				}).start();
			}
		};

		ErrorListener errorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.d(LOG_TAG, "getImageFromUrl onErrorResponse");
				onResponseGetImageUrl.onResponseGetImageUrlError(error);
			}
		};

		ImageRequest imageRequest = new ImageRequest(url, listener, 0, 0, Config.ARGB_8888, errorListener){
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = super.getHeaders();
				
				if (headers == null
			            || headers.equals(Collections.emptyMap())) {
			        headers = new HashMap<String, String>();
			    }
				
				headers.put("User-agent", "Chrome");
				
				return headers;
			}
		};

		queue.add(imageRequest);

	}
}
