package org.kitemorphlines.utils;

import java.util.Map;

import org.kitesdk.morphline.annotations.KiteCommand;
import org.kitesdk.morphline.annotations.KiteComponent;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.components.KiteCommandContext;

import com.adobe.analytics.client.AnalyticsClient;

@KiteComponent
public class CsvUtils {

	@KiteCommand
	public boolean writeCsv(Record record,
			                Map<String, Object> params,
			                KiteCommandContext ctx) {

		AnalyticsClient c = (AnalyticsClient) record.getFirstValue("analyticsClient");

		System.out.println("found client " + c);
		
		return ctx.getChild().process(record);
	}
	
	public CsvUtils() {
		System.out.println("OOOOOK");
	}

}
