package org.kitemorphlines.utils;

import java.util.Map;

import org.kitesdk.morphline.annotations.KiteCommand;
import org.kitesdk.morphline.annotations.KiteComponent;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.components.KiteCommandContext;

import com.adobe.analytics.client.AnalyticsClient;
import com.adobe.analytics.client.AnalyticsClientBuilder;

@KiteComponent
public class AdobeAnalyticsAPI {
	
	AnalyticsClient client;

	@KiteCommand
	public boolean analyticsClient(Record record, Map<String, Object> params, KiteCommandContext ctx) {
		String endpoint = (String) params.get("endpoint");
		String user = (String) params.get("user");
		String secret = (String) params.get("secret");

		client = new AnalyticsClientBuilder().setEndpoint(endpoint)
		.authenticateWithSecret(user, secret)
		.build();

		record.put("analyticsClient", client);
		
		return ctx.getChild().process(record);

	}
	
}
