package it.sauronsoftware.feed4j;

import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedHeader;
import it.sauronsoftware.feed4j.bean.FeedItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;


public class FeedExample {

    @Test
    public void testDivide() throws Exception {
        URL rssDemoFileUrl = FeedExample.class.getClassLoader().getResource("demo.rss");

        Feed feed = FeedParser.parse(rssDemoFileUrl);

        FeedHeader header = feed.getHeader();
        Assertions.assertNotNull(header);

        Assertions.assertEquals("ScarletGothica", header.getTitle());
        //Assertions.assertEquals("http://www.scarletgothica.com/", header.getURL());
        Assertions.assertEquals("ScarletGothica.com is a Web site totally dedicated to the illustrations, the drawings and the sketches made by ScarletGothica. All works are inspired by fantasy, dark and gothic themes.", header.getDescription());
        Assertions.assertEquals("en", header.getLanguage());
        Assertions.assertNull(header.getPubDate());

        Assertions.assertEquals(20, feed.getItemCount());

        FeedItem item = feed.getItem(0);
        Assertions.assertEquals("Lunedì 25 Novembre 2013 11:23 (GMT)", item.getTitle());
        Assertions.assertEquals("http://www.scarletgothica.com/view_news.php?item=237", item.getLink().toString());
        Assertions.assertEquals("", item.getDescriptionAsText());
        Assertions.assertEquals("<p />", item.getDescriptionAsHTML());
        Assertions.assertNull(item.getPubDate());

    }

}
