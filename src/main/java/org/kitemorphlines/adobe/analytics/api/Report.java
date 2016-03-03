package org.kitemorphlines.adobe.analytics.api;

import org.kitemorphlines.adobe.analytics.annotations.AdobeAnalyticsAPI;
import org.kitemorphlines.adobe.analytics.annotations.AdobeAnalyticsAPIMethod;

import com.adobe.analytics.client.AnalyticsClient;


@AdobeAnalyticsAPI(name="Report")
public class Report {
	

	@AdobeAnalyticsAPIMethod(name="Queue")
	public void queue() {
		
	}
	
	public Report() {
	}

}
