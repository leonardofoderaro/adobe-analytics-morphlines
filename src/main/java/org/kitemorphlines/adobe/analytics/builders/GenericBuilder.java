package org.kitemorphlines.adobe.analytics.builders;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.kitemorphlines.adobe.analytics.api.AdobeAnalyticsBaseAPI;
import org.kitemorphlines.adobe.analytics.commands.GenericCommand;
import org.kitesdk.morphline.api.Command;
import org.kitesdk.morphline.api.CommandBuilder;
import org.kitesdk.morphline.api.MorphlineContext;

import com.typesafe.config.Config;

public class GenericBuilder implements CommandBuilder {
	
	String commandName = "";

	@Override
	public Collection<String> getNames() {
		return Arrays.asList("Bookmark", "Report");
	}

	@Override
	public Command build(Config config, Command parent, Command child,
			MorphlineContext context) {
		
		Map<String, Class<AdobeAnalyticsBaseAPI>> classes = (Map<String, Class<AdobeAnalyticsBaseAPI>>) context.getSettings().get("classes");
		
		String commandName = (String) context.getSettings().get("currentCommandName");
		
		Class<AdobeAnalyticsBaseAPI> apiClass = classes.get(commandName);
		
		AdobeAnalyticsBaseAPI api = null;
		
		try {
			api = apiClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		GenericCommand cmd = new GenericCommand(this, config, parent, child, context);
		
		cmd.setApiImplementor(api);
		
		return cmd;
		
	}

	
}