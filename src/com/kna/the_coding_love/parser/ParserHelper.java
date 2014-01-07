package com.kna.the_coding_love.parser;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kna.the_coding_love.model.GifPost;

public class ParserHelper {

	public static ArrayList<GifPost> getGifPosts(String response) {
		ArrayList<GifPost> gifPosts = new ArrayList<GifPost>();
		
		// get html document structure
        Document document = Jsoup.parse(response);
        
        // selector query
        Elements elementsPosts = document.select("div.post");
        
        // check results
        if(elementsPosts.size() > 0) {
            String title;
            String imageUrl;
            String username;
            String gifPostUrl;
            GifPost gifPost;
        	for (Element element : elementsPosts) {
        		title = element.select("h3 a[href]").get(0).text();
        		gifPostUrl = element.select("h3 a[href]").get(0).attr("href");
        		imageUrl = element.select("p.c1 img").get(0).attr("src");
        		username = element.select("p.c1 em").get(0).text();
        		
        		gifPost = new GifPost(title, imageUrl, username, null, gifPostUrl);
        		
        		gifPosts.add(gifPost);
			}
        }		
		
		return gifPosts;
	}

}
