package org.kitemorphlines.adobe.analytics.builders;

import java.util.Collection;
import java.util.Collections;

import org.kitemorphlines.adobe.analytics.commands.AnalyticsClientCommand;
import org.kitesdk.morphline.api.Command;
import org.kitesdk.morphline.api.CommandBuilder;
import org.kitesdk.morphline.api.MorphlineContext;

import com.typesafe.config.Config;

public class AnalyticsClientCommandBuilder implements CommandBuilder {

	public Collection<String> getNames() {
		return Collections.singletonList("analyticsClient");
	}

	public Command build(Config config, Command parent, Command child,
			MorphlineContext context) {
		return new AnalyticsClientCommand(this, config, parent, child, context);
	}

}
