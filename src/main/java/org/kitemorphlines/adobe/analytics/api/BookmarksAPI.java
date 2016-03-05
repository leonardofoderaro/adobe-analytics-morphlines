package org.kitemorphlines.adobe.analytics.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.kitesdk.morphline.annotations.KiteCommand;
import org.kitesdk.morphline.annotations.KiteComponent;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.Fields;
import org.kitesdk.morphline.base.components.KiteCommandContext;

import com.adobe.analytics.client.domain.Bookmark;
import com.adobe.analytics.client.domain.BookmarkFolder;

@KiteComponent
public class BookmarksAPI {

	@KiteCommand
	public boolean getBookmarks(Record record, Map<String, Object> params, KiteCommandContext ctx) {
		AdobeAnalyticsAPI client = (AdobeAnalyticsAPI) record.getFirstValue(AdobeAnalyticsAPI.ANALYTICS_CLIENT);

		if (client == null) {
			System.out.println("Adobe Analytics Client object not found.");
			return false;
		}

		Record template = record.copy();
		template.removeAll(Fields.MESSAGE); 

		List<BookmarkFolder> folders = null;

		try {
			folders = client.getReportSuiteMethods().getBookmarksFolders();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (folders != null) {
			for (BookmarkFolder f : folders) {
				for (Bookmark bookmark : f.getBookmarks()) {
					Record outRecord = template.copy();
					outRecord.put(Fields.MESSAGE, bookmark);
					if (!ctx.getChild().process(outRecord)) {
						return false;
					}
				}
			}
		} else {
			return false;
		}

		return true;

	}	


	@KiteCommand
	public boolean processBookmark(Record record, Map<String, Object> params, KiteCommandContext ctx) {
		Bookmark bookmark = (Bookmark) record.getFirstValue(Fields.MESSAGE);

		System.out.println("found Bookmark: " + bookmark.getName());

		return ctx.getChild().process(record);
	}


}
