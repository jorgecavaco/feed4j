# feed4j

This is a custom improved version. http://www.sauronsoftware.it/projects/feed4j/

## Quickstart
In order to use the feed4j parser in your Java software, you have to make visible the feed4j.jar file to your application adding it to the CLASSPATH. Since the feed4j library depends on third parties jars (placed in the lib directory of the distribution package) you also have to add them to the CLASSPATH.

### The feed parser
The use of the feed parser (class it.sauronsoftware.feed4j.FeedParser) is a one-step operation:

Feed feedRepresentation = FeedParser.parse(feedUrl);
Once you have retrieved a feed representation object (class it.sauronsoftware.feed4j.bean.Feed) you can ask it everything about the parsed XML feed. The feed representation is splitted in two parts: a feed header and a feed items collection. The header contains information about the whole feed, such its source URL, its title, its description and so on. Every feed item represents a news story, and it is mainly composed by a title and a URL, but it can also contains HTML and plain text descriptions, publication and modification dates, several enclosures etc.

The feed object representation provided by feed4j can be mastered in minutes: just take a look to the javadocs that come with the distribution package.

### A quick and complete example:

package it.sauronsoftware.feed4j.example1;

import java.net.URL;

import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedHeader;
import it.sauronsoftware.feed4j.bean.FeedItem;

public class FeedExample {

	public static void main(String[] args) throws Exception {
		
		URL url = new URL("http://www.scarletgothica.com/rss_en.php");
		
		Feed feed = FeedParser.parse(url);
		
		System.out.println("** HEADER **");
		FeedHeader header = feed.getHeader();
		System.out.println("Title: " + header.getTitle());
		System.out.println("Link: " + header.getLink());
		System.out.println("Description: " + header.getDescription());
		System.out.println("Language: " + header.getLanguage());
		System.out.println("PubDate: " + header.getPubDate());
		
		System.out.println("** ITEMS **");
		int items = feed.getItemCount();
		for (int i = 0; i < items; i++) {
			FeedItem item = feed.getItem(i);
			System.out.println("Title: " + item.getTitle());
			System.out.println("Link: " + item.getLink());
			System.out.println("Plain text description: " + item.getDescriptionAsText());
			System.out.println("HTML description: " + item.getDescriptionAsHTML());
			System.out.println("PubDate: " + item.getPubDate());
		}
		
	}

}
A more complex example is in the examples/example2 directory inside the distribution package.

### Catching the exceptions
The FeedParser.parse() method can throw several exceptions, depending on the kind of the encountered problem:

FeedIOException: the feed cannot be retrieved due to an I/O problem.
FeedXMLParseException: the file has been retrieved, but it doesn't look to be valid XML.
UnsupportedFeedException: the feed has been retrieved and parsed as a XML document, but its contents doesn't match one of the feed formats supported by feed4j.
Since all those exceptions extend the FeedException class, you can handle them one by one or all together with a sole catch block:

Feed feed;
try {
	feed = FeedParser.parse(feedUrl);
} catch (FeedIOException e) {
	// one
} catch (FeedXMLParseException e) {
	// by
} catch (UnsupportedFeedException e) {
	// one
}

try {
	feed = FeedParser.parse(feedUrl);
} catch (FeedException e) {
	// all together
}

### Supported feed formats
The feed4j parser can currently handle the following XML feed formats:

RSS 1.0 + Dublin Core tags
RSS 2.0
RSS 0.91 and 0.92, since a valid RSS 0.91 or 0.92 feed is also a valid RSS 2.0 feed
Atom 0.3
Atom 1.0 (IETF standard)
Handling custom tags
XML feeds are flexible and can be extended with custom or third-parties tags. The feed4j parser helps you in handling them giving "raw access" to unhandled tags. In example: if the items in your XML feed are expected to contain a custom tag called "phone-number", whose namespace is "http://www.mysite.org/myCustomTags", you can gain access to its value as follows:

String value = feedItem.getElementValue(
	"http://www.mysite.org/myCustomTags", "phone-number"
);
In the package it.sauronsoftware.feed4j.bean you can find the class RawElement, which is a raw informations container. Since FeedHeader, FeedItem, FeedImage and FeedEnclosure extend RawElement you can ever access raw data. So you will handle standard informations with structured methods like getTitle(), getLink() and getPubDate(), while non-standard informations will be accessed in a raw way, with methods like getElement(), getAttribute() and getNode(). Please refer to the feed4j javadocs for a complete features list.
