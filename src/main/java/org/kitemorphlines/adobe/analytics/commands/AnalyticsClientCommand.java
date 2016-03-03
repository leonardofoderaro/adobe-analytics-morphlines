package org.kitemorphlines.adobe.analytics.commands;

import org.kitesdk.morphline.api.Command;
import org.kitesdk.morphline.api.CommandBuilder;
import org.kitesdk.morphline.api.MorphlineContext;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.AbstractCommand;

import com.adobe.analytics.client.AnalyticsClient;
import com.adobe.analytics.client.AnalyticsClientBuilder;
import com.typesafe.config.Config;

public class AnalyticsClientCommand extends AbstractCommand {

	private String endpoint;
	private String user;
	private String secret;

	private AnalyticsClient client;

	public AnalyticsClientCommand(CommandBuilder builder, Config config,
			Command parent, Command child, MorphlineContext context) {
		super(builder, config, parent, child, context);

		this.endpoint = getConfigs().getString(config, "endpoint");
		this.user = getConfigs().getString(config, "user");
		this.secret = getConfigs().getString(config, "secret");

	}

	@Override
	protected boolean doProcess(Record record) {
		buildAnalictsClient();
		
		record.put("analyticsClient", client);

		return true;
	}

	private void buildAnalictsClient() {
		client = new AnalyticsClientBuilder().setEndpoint(this.endpoint)
				.authenticateWithSecret(this.user, this.secret)
				.build();	
	}

	public String toString() {
		return this.endpoint + " - " + this.user + " - " + this.secret;
	}


}
