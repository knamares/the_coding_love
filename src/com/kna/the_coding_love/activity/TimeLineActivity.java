package com.kna.the_coding_love.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.kna.the_coding_love.R;
import com.kna.the_coding_love.interfaze.OnResponseGetGifPost;
import com.kna.the_coding_love.model.GifPost;
import com.kna.the_coding_love.net.NetManager;

public class TimeLineActivity extends Activity implements OnResponseGetGifPost{

	private String LOG_TAG = "----->TimeLineActivity";
	
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);

		textView = (TextView) findViewById(R.id.textViewTimeLine);
		
		//Make request
		NetManager.getInstance(this).getGifPost(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_line, menu);
		return true;
	}

	
	
	
	
	
	
	//Callbacks
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
		textView.setText(response);
		Log.d(LOG_TAG, "onResponseGetGifPost response");
	}

}
