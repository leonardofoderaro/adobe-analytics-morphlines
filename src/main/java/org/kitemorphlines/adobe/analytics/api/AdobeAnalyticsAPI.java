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

	@KiteCommand
	public boolean analyticsClient(Record record, Map<String, Object> params, KiteCommandContext ctx) {
		String endpoint = (String) params.get("endpoint");
		String user = (String) params.get("user");
		String secret = (String) params.get("secret");

		client = new AnalyticsClientBuilder().setEndpoint(endpoint)
		.authenticateWithSecret(user, secret)
		.build();

        suiteMethods = new ReportSuiteMethods(client);

		// we don't need to pass the analyticsClient object to the chain
		//record.put("analyticsClient", client);
		
		return ctx.getChild().process(record);
	}


}
