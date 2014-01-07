package com.kna.the_coding_love.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
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

	private AlertDialog dialogWait;

	private GifPostAdapter gifPostAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);

		listViewTimeLine = (ListView) findViewById(R.id.listviewTimeLine);

		setBinders();

		// Init data
		ArrayList<GifPost> gifPosts = new ArrayList<GifPost>();

		gifPostAdapter = new GifPostAdapter(this, gifPosts);
		listViewTimeLine.setAdapter(gifPostAdapter);

		// Make request
		getGifPost(this, this);

	}

	private void setBinders() {
		listViewTimeLine.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				if (position == 0)
					return;

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
	public void onResponseGetGifPost(final ArrayList<GifPost> gifPosts) {
		Log.d(LOG_TAG, "onResponseGetGifPost gifPosts size: " + gifPosts.size());

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				gifPostAdapter.addAll(gifPosts);
				hideWait();
			}
		});
	}

	@Override
	public void onResponseGetGifPostError(final Exception e) {
		Log.d(LOG_TAG, "onResponseGetGifPostError Exception size: " + e.getMessage() + " | " + e.getCause());

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
				hideWait();
			}
		});

	}

	private void showWait(Context context) {
		// 1. Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		// 2. Chain together various setter methods to set the dialog
		// characteristics
		builder.setMessage(R.string.retrieving_data).setTitle(R.string.please_wait);

		builder.setView(getLayoutInflater().inflate(R.layout.dialog_wait, null));

		// 3. Get the AlertDialog from create()
		dialogWait = builder.create();

		dialogWait.show();
	}

	private void hideWait() {
		dialogWait.cancel();
	}

	private void getGifPost(Context context, OnResponseGetGifPost onResponseGetGifPost) {
		showWait(context);
		NetManager.getInstance(context).getGifPost(onResponseGetGifPost);
	}

}
