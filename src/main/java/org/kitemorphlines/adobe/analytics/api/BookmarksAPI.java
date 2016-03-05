package org.kitemorphlines.adobe.analytics.api;

import java.io.IOException;
import java.util.List;

import org.kitemorphlines.adobe.analytics.annotations.AdobeAnalyticsAPI;
import org.kitemorphlines.adobe.analytics.annotations.AdobeAnalyticsAPIMethod;
import org.kitemorphlines.adobe.analytics.commands.GenericCommand;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.Fields;

import com.adobe.analytics.client.domain.BookmarkFolder;

@AdobeAnalyticsAPI(name="Bookmark")
public class Bookmark extends AdobeAnalyticsBaseAPI {
	
	@AdobeAnalyticsAPIMethod(name="GetBookmarks")
	public boolean getBookmarks(Record record, GenericCommand cmd) {
		
		Record template = record.copy();
		template.removeAll(Fields.MESSAGE); 

		List<BookmarkFolder> folders = null;
		
		try {
			folders = suiteMethods.getBookmarksFolders();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (folders != null) {
			int i = 1;
			for (BookmarkFolder f : folders) {
			    Record outRecord = template.copy();
			    outRecord.put(Fields.MESSAGE, f);
		        if (!cmd.getChild().process(outRecord)) {
		            return false;
		        }
		        i++;
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	public Bookmark() {

	}
	
}
