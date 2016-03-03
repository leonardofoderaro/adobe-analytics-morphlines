package org.kitemorphlines.adobe.analytics.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AdobeAnalyticsAPI {
	String name();
}
