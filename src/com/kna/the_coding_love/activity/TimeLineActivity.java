package com.kna.the_coding_love.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kna.the_coding_love.R;
import com.kna.the_coding_love.adapter.GifPostAdapter;
import com.kna.the_coding_love.interfaze.OnResponseGetGifPost;
import com.kna.the_coding_love.model.GifPost;
import com.kna.the_coding_love.net.NetManager;

public class TimeLineActivity extends Activity implements OnResponseGetGifPost {

	private String LOG_TAG = "----->TimeLineActivity";

	private ListView listViewTimeLine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);

		listViewTimeLine = (ListView) findViewById(R.id.listviewTimeLine);

		setBinders();

		// Make request
		NetManager.getInstance(this).getGifPost(this);

		// TODO: parse data

		ArrayList<GifPost> gifPosts = new ArrayList<GifPost>() {
			private static final long serialVersionUID = 1L;
			{
				add(new GifPost("Title " + 1, "Image " + 1, "Username " + 1, "email" + 1 + "@email.com"));
				add(new GifPost("Title " + 2, "Image " + 2, "Username " + 2, "email" + 2 + "@email.com"));
				add(new GifPost("Title " + 3, "Image " + 3, "Username " + 3, "email" + 3 + "@email.com"));
				add(new GifPost("Title " + 4, "Image " + 4, "Username " + 4, "email" + 4 + "@email.com"));
				add(new GifPost("Title " + 5, "Image " + 5, "Username " + 5, "email" + 5 + "@email.com"));
			}
		};

		GifPostAdapter gifPostAdapter = new GifPostAdapter(this, gifPosts);
		listViewTimeLine.setAdapter(gifPostAdapter);

	}

	private void setBinders() {
		listViewTimeLine.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				if(position == 0) return;
				
				GifPost item = (GifPost) parent.getItemAtPosition(position);
				Toast.makeText(TimeLineActivity.this, "Click Item: " + position + " || Text: " + item.getTitle(),
						Toast.LENGTH_LONG).show();
			}

		});
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
