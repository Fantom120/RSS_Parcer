package com.example.XML_Pars;

import android.graphics.Bitmap;
import com.squareup.picasso.Picasso;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PostItem {
    private URL url;
    private Bitmap image;

    private String title; // Название поста
    private URL link; // Ссылка на пост на сайте (в формате URL для использования в Java)
    private String linkText; // Ссылка на пост на сайте (в виде строки текста для вывода)
    private String description; // Описание поста
    private Date date; // Дата публикации поста (в формате Date для обработки в программе)
    static SimpleDateFormat FORMATTER = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.US);



    public String setTitle(String title) {
        this.title = title.trim();
        return title;
    }


    public String getTitle() {
        return title;
    }


    public void setDescription(String description) {
        this.description = description.trim();
    }


    public String getDescription() {

        return description;
    }


    public void setLink(String link) {
        this.linkText = link.trim();
        try {
            this.link = new URL(link);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public URL getLink() {
        return link;
    }

    public String getLinkText() {
        return linkText;
    }


    public void setDate(String date) {
        try {
            this.date = FORMATTER.parse(date.trim());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public String getDate() {
        return FORMATTER.format(this.date);
    }

    public PostItem copy() {
        PostItem copy = new PostItem();
        copy.title = title;
        copy.link = link;
        copy.linkText = linkText;
        copy.description = description;
        copy.date = date;
        return copy;
    }

    public Bitmap getImage() {
//      Matcher matcher = Pattern.compile("http:.+jpg|http:.+png").matcher(getDescription());
//
//        String str = null;
//
//
//
//
//        while (matcher.find()) {
//
//            Log.d("my", "img uri: " + matcher.group());
//            str = matcher.group();
//            if (matcher.find()) {
//                str = matcher.group();
//            }
//        }
//
//            try {
//                url = new URL(str);
//
//                image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//

       return image;
    }



    public void setImage(Bitmap image) {
    this.image = image;

    }



}
