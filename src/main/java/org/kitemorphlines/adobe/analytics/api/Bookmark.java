package org.kitemorphlines.adobe.analytics.api;

import org.kitemorphlines.adobe.analytics.annotations.AdobeAnalyticsAPI;
import org.kitemorphlines.adobe.analytics.annotations.AdobeAnalyticsAPIMethod;

import com.adobe.analytics.client.AnalyticsClient;


@AdobeAnalyticsAPI(name="Bookmark")
public class Bookmark extends AdobeAnalyticsBaseAPI {
	
	private AnalyticsClient client;

	@AdobeAnalyticsAPIMethod(name="GetBookmarks")
	public void getBookmarks() {

	}
	
	public Bookmark() {
	}

}
