package com.kna.the_coding_love.net;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kna.the_coding_love.interfaze.OnResponseGetGifPost;
import com.kna.the_coding_love.model.GifPost;

public class NetManager {
	
	private static String LOG_TAG = "----->NetManager";
	private static final String BASE_URL = "http://thecodinglove.com/";
	
	private static RequestQueue queue;
	
	public static NetManager getInstance(Context context){
		Log.d(LOG_TAG, "getInstance");
		
		if(queue == null) {
			Log.d(LOG_TAG, "getInstance queue null. Create one.");			
			queue = Volley.newRequestQueue(context);
		}
		
		return new NetManager();
		
	}
	
	public void getGifPost(final OnResponseGetGifPost onResponseGetGifPost){
		
		Log.d(LOG_TAG, "getGifPost");
		
		ArrayList<GifPost> gifPosts = new ArrayList<GifPost>();
		
		//Set the listener
		Listener<String> listener = new Listener<String>() {
			@Override
			public void onResponse(String response) {

				Log.d(LOG_TAG, "getGifPost onResponse");
				
				//TODO: Parse request
				onResponseGetGifPost.onResponseGetGifPostRaw(response);
				
				//TODO: Persistence request

			}
		};
		
		ErrorListener errorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.d(LOG_TAG, "getGifPost onErrorResponse");
				onResponseGetGifPost.onResponseGetGifPostError(error);
			}
		};		

		//Make request
		getStringRequest(BASE_URL, listener, errorListener);
		
	}
	
	private void getStringRequest(String url, Listener<String> listener, ErrorListener errorListener){
		Log.d(LOG_TAG, "getStringRequest");
		StringRequest stringRequest = new StringRequest(Method.GET, url, listener , errorListener);
		queue.add(stringRequest);
	}
}
