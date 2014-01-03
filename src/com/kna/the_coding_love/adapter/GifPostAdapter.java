package com.kna.the_coding_love.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kna.the_coding_love.R;
import com.kna.the_coding_love.model.GifPost;

public class GifPostAdapter extends ArrayAdapter<GifPost> {

	private final Activity context;
	private final ArrayList<GifPost> gifPosts;

	private String comment;

	private LayoutInflater layoutInflater;

	static class Header {
		public String string;
	}

	static class ViewHolder {
		public TextView title;
		public ImageView image;
		public TextView username;
	}

	public GifPostAdapter(Activity context, ArrayList<GifPost> gifPosts) {
		super(context, R.layout.gif_post, gifPosts);
		this.context = context;
		this.gifPosts = gifPosts;

		comment = context.getResources().getString(R.string._by_1_);

		layoutInflater = context.getLayoutInflater();

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// header
		if (position == 0) {
			if (convertView == null || convertView.findViewById(R.id.relativeLayoutGifPostHeader) == null) {
				convertView = layoutInflater.inflate(R.layout.gif_post_header, null);
			}
		} else {
			ViewHolder holder = null;

			// check if this view contained a header
			if (convertView == null || convertView.findViewById(R.id.relativeLayoutGifPostHeader) != null) {
				holder = new ViewHolder();

				convertView = layoutInflater.inflate(R.layout.gif_post, null);
				holder.title = (TextView) convertView.findViewById(R.id.textViewGifPostTitle);
				holder.image = (ImageView) convertView.findViewById(R.id.imageViewGifPostTimeLineHeadLogo);
				holder.username = (TextView) convertView.findViewById(R.id.textViewGifPostUsername);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			GifPost gifPost = gifPosts.get(position);
			holder.title.setText(gifPost.getTitle());
			// TODO: load image
			holder.image.setImageResource(R.drawable.head_logo);
			holder.username.setText(comment.replace("%1", gifPost.getUsername()));

		}

		return convertView;

		// View rowView = convertView;
		// if (rowView == null) {
		// rowView = layoutInflater.inflate(R.layout.gif_post, null);
		// ViewHolder viewHolder = new ViewHolder();
		// viewHolder.title = (TextView)
		// rowView.findViewById(R.id.textViewGifPostTitle);
		// viewHolder.image = (ImageView)
		// rowView.findViewById(R.id.imageViewGifPostTimeLineHeadLogo);
		// viewHolder.username = (TextView)
		// rowView.findViewById(R.id.textViewGifPostUsername);
		// rowView.setTag(viewHolder);
		// }
		//
		// ViewHolder holder = (ViewHolder) rowView.getTag();
		// GifPost gifPost = gifPosts.get(position);
		// holder.title.setText(gifPost.getTitle());
		// // TODO: load image
		// holder.image.setImageResource(R.drawable.head_logo);
		// holder.username.setText(comment.replace("%1",
		// gifPost.getUsername()));
		//
		// return rowView;
	}

}
