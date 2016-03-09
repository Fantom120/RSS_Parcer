package com.example.XML_Pars;


import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class NewParser  {


    /**
     * Переводим строку адреса RSS ленты в URL, соединяемся с сервером по этому адресу и
     * получаем поток данных с RSS лентой.
     *
     * @return
     */

    protected InputStream getInputStream() {
        URL feedUrl = null;
        try {
            feedUrl = new URL("http://siliconrus.com/feed/");
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        try {
            return feedUrl.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<PostItem> parse() {
        final PostItem currentPost = new PostItem();

        final List<PostItem> messages = new ArrayList<PostItem>();
        RootElement root = new RootElement("rss");
        Element channel = root.getChild("channel");
        Element item = channel.getChild("item");
        item.setEndElementListener(new EndElementListener() {
            public void end() {
                messages.add(currentPost.copy());
            }
        });

         item.getChild("title").setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
           currentPost.setTitle(body);
            }
        });
        item.getChild("link").setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                currentPost.setLink(body);
            }
        });
        item.getChild("description").setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                currentPost.setDescription(body);
               }
        });

        item.getChild("pubDate").setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                currentPost.setDate(body);
            }
        });
        item.getChild("img src").setEndTextElementListener(new EndTextElementListener() {

            public void end(String body) {
               currentPost.setLink(body);
            }
        });
        try {
            Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return messages;
    }


}