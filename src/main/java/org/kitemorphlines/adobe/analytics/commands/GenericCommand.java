package org.kitemorphlines.adobe.analytics.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import org.kitemorphlines.adobe.analytics.annotations.AdobeAnalyticsAPIMethod;
import org.kitemorphlines.adobe.analytics.api.AdobeAnalyticsBaseAPI;
import org.kitesdk.morphline.api.Command;
import org.kitesdk.morphline.api.CommandBuilder;
import org.kitesdk.morphline.api.MorphlineContext;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.AbstractCommand;

import com.adobe.analytics.client.AnalyticsClient;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigObject;

public class GenericCommand extends AbstractCommand {
	
	private AdobeAnalyticsBaseAPI api;
	
	private Map<String, Object> settings;
	
	private ConfigObject root;
	
	public GenericCommand(CommandBuilder builder, Config config,
			Command parent, Command child, MorphlineContext context) {
		super(builder, config, parent, child, context);
		root = config.root();
		
		this.settings = context.getSettings();
	}

	public void setApiImplementor(AdobeAnalyticsBaseAPI api) {
		this.api = api;
	}
	
	
	@Override
	protected boolean doProcess(Record record) {
		
		AnalyticsClient client = (AnalyticsClient) record.getFirstValue("analyticsClient");
		
		api.setClient(client);
	
		String methodName = (String) this.root.keySet().toArray()[0];
		
		for (Method m : api.getClass().getMethods()) {
			if (m.isAnnotationPresent(AdobeAnalyticsAPIMethod.class)) {
				AdobeAnalyticsAPIMethod ann = (AdobeAnalyticsAPIMethod) m.getAnnotationsByType(AdobeAnalyticsAPIMethod.class)[0];
				
		        if (methodName.equals(ann.name())) {
		            try {
						m.invoke(api, record, this);
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
			}
		}
		
	    return getChild().process(record);
	}
	
	@Override
	public Command getChild() {
		return super.getChild();
	}

}
