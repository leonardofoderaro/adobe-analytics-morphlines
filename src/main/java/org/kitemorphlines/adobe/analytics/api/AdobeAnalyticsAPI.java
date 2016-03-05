package org.kitemorphlines.adobe.analytics.api;

import java.util.Map;

import org.kitesdk.morphline.annotations.KiteCommand;
import org.kitesdk.morphline.annotations.KiteComponent;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.components.KiteCommandContext;

import com.adobe.analytics.client.AnalyticsClient;
import com.adobe.analytics.client.AnalyticsClientBuilder;
import com.adobe.analytics.client.methods.ReportSuiteMethods;

@KiteComponent
public class AdobeAnalyticsAPI {
	

	private AnalyticsClient client;
	
	private ReportSuiteMethods suiteMethods;
	
	//used to pass the client and suiteMethods objects to other commands
	public static final String ANALYTICS_CLIENT = "AdobeAnalyticsClient";
	private static final String ANALYTICS_SUITE_METHODS = "AdobeAnalyticsSuiteMethods";

	@KiteCommand
	public boolean analyticsClient(Record record, Map<String, Object> params, KiteCommandContext ctx) {
		String endpoint = (String) params.get("endpoint");
		String user = (String) params.get("user");
		String secret = (String) params.get("secret");

		client = new AnalyticsClientBuilder().setEndpoint(endpoint)
		.authenticateWithSecret(user, secret)
		.build();

        suiteMethods = new ReportSuiteMethods(client);

		// if we were using only methods from this class
        // we wouldn't need to pass this to the chain
		record.put(ANALYTICS_CLIENT, this);
		
		return ctx.getChild().process(record);
	}
	
	public AnalyticsClient getAnalyticsClient() {
		return this.client;
	}
	
	public ReportSuiteMethods getReportSuiteMethods() {
		return this.suiteMethods;
	}


}
