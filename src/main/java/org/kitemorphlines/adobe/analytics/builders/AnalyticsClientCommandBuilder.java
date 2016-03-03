package org.kitemorphlines.adobe.analytics.builders;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.kitemorphlines.adobe.analytics.annotations.AdobeAnalyticsAPI;
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
		Map<String, Class<?>> classes = scanPackage("org.kitemorphlines.adobe.analytics.api");

		context.getSettings().put("classes", classes);
		
		return new AnalyticsClientCommand(this, config, parent, child, context);
	}
	
	private Map<String, Class<?>> scanPackage(String packageName) {

		URL root = this.getClass().getClassLoader().getResource(packageName.replace(".", "/"));

		if (root == null) {
			//logger.error("unable to find classes in " + packageName + ". Please check if jars are present.");
			return null;
		}
		
		List<String> fileNames = getFilenames(root);

		Map<String, Class<?>> classes = new HashMap<String, Class<?>>();
		
		for (String f : fileNames) {
			
			String className = f.replaceAll(".class$", "").replaceAll("/", ".");

			Class<?> cls = null;
			
			try {
				cls = Class.forName(className);
			} catch (ClassNotFoundException e) {
				try {
					cls = Class.forName(packageName + "." + className, true, ClassLoader.getSystemClassLoader());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
				   e1.printStackTrace();
				}

				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			if (cls != null) {
				if (cls.isAnnotationPresent(AdobeAnalyticsAPI.class)) {
					//System.out.println(cls.getCanonicalName() + " is a good class!");
					classes.put(className, cls);
				}
			}

			
		}

		//List<Class<?>> classes = getClassesByInterface(packageName, fileNames, c);

		return classes;
	}
	
	private List<String> getFilenames(URL root) {

		List<String> results = new ArrayList<String>();

		if (root.getFile().contains("!")) {
			JarFile jarFile;
			try {
				jarFile = new JarFile(root.getFile().replaceAll("!.*", "").replace("file:", ""));
				Enumeration allEntries = jarFile.entries();
				while (allEntries.hasMoreElements()) {
					JarEntry entry = (JarEntry) allEntries.nextElement();
					String name = entry.getName();

					if (name.contains(".class")) {
						results.add(name);
					}


				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//logger.error("err", e);
			}

		} else {

			File[] fl = new File(root.getFile()).listFiles();


			// Filter .class files.
			File[] files = new File(root.getFile()).listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".class");
				}
			});

			for (File f : files) {
				results.add(f.getName());
			}

		}

		return results;
	}

}
