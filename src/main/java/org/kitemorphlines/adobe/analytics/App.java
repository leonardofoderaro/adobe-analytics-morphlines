package org.kitemorphlines.adobe.analytics;

import java.io.File;
import java.net.URISyntaxException;

import org.kitesdk.morphline.api.Command;
import org.kitesdk.morphline.api.MorphlineContext;
import org.kitesdk.morphline.base.Compiler;

public class App 
{
	public static void main( String[] args ) throws URISyntaxException
	{
		// TODO it should be read from project's resources classpath
		File configFile = new File("/path/to/morphline.conf");
		
		MorphlineContext context = new MorphlineContext.Builder().build();

		Command morphline = new Compiler().compile(configFile, null, context, null);
		
	}
}
