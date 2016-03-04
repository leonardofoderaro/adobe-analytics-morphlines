package org.kitemorphlines.adobe.analytics.api;

import com.adobe.analytics.client.AnalyticsClient;
import com.adobe.analytics.client.methods.ReportSuiteMethods;

public abstract class AdobeAnalyticsBaseAPI {
	
	protected AnalyticsClient client;

	protected ReportSuiteMethods suiteMethods;
	
	public void setClient(AnalyticsClient client) {
		this.client = client;
		suiteMethods = new ReportSuiteMethods(client); //client is created as above
	}

}
