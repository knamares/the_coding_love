package com.kna.the_coding_love.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kna.the_coding_love.R;
import com.kna.the_coding_love.interfaze.OnResponseGetGifPost;
import com.kna.the_coding_love.model.GifPost;
import com.kna.the_coding_love.net.NetManager;

public class TimeLineActivity extends Activity implements OnResponseGetGifPost {

	private String LOG_TAG = "----->TimeLineActivity";

	private TextView textView;

	private LinearLayout linearLayoutScroll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);

		textView = (TextView) findViewById(R.id.textViewTimeLine);
		linearLayoutScroll = (LinearLayout) findViewById(R.id.linearLayoutScroll);

		// Make request
		NetManager.getInstance(this).getGifPost(this);

		for (int i = 0; i < 5; i++) {
			RelativeLayout viewGifPost = (RelativeLayout) getLayoutInflater().inflate(R.layout.gif_post, null);
			linearLayoutScroll.addView(viewGifPost);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_line, menu);
		return true;
	}

	// Callbacks
	@Override
	public void onResponseGetGifPost(ArrayList<GifPost> gifPosts) {
		Log.d(LOG_TAG, "onResponseGetGifPost gifPosts size: " + gifPosts.size());
	}

	@Override
	public void onResponseGetGifPostError(Exception e) {
		Log.d(LOG_TAG, "onResponseGetGifPostError Exception size: " + e.getMessage() + " | " + e.getCause());
	}

	@Override
	public void onResponseGetGifPostRaw(String response) {
		Log.d(LOG_TAG, "onResponseGetGifPost response");
	}

}
