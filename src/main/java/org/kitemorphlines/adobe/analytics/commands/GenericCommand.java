package org.kitemorphlines.adobe.analytics.commands;

import org.kitemorphlines.adobe.analytics.api.AdobeAnalyticsBaseAPI;
import org.kitesdk.morphline.api.Command;
import org.kitesdk.morphline.api.CommandBuilder;
import org.kitesdk.morphline.api.MorphlineContext;
import org.kitesdk.morphline.base.AbstractCommand;

import com.typesafe.config.Config;

public class GenericCommand extends AbstractCommand {
	
	private AdobeAnalyticsBaseAPI api;

	public GenericCommand(CommandBuilder builder, Config config,
			Command parent, Command child, MorphlineContext context) {
		super(builder, config, parent, child, context);
	}

	public void setApiImplementor(AdobeAnalyticsBaseAPI api) {
		this.api = api;
	}

}
