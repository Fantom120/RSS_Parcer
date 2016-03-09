package com.example.XML_Pars;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ArrAdapter extends BaseAdapter {
    LayoutInflater inflater;
   private List<PostItem> messages;




    public ArrAdapter(Context context, List<PostItem> messages) {
        this.messages = messages;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.row, parent, false);
        }
        PostItem parser = getParce(position);
                TextView textView = (TextView) view.findViewById(R.id.textTitle);
                       textView.setText(parser.getTitle());
                    textView.getPaint().setAntiAlias(true);
                ((TextView) view.findViewById(R.id.textDate)).setText(parser.getDate());
//        ((ImageView) view.findViewById(R.id.imageView)).setImageBitmap(parser.getImage());
        Matcher matcher = Pattern.compile("http:.+jpg|http:.+png").matcher( messages.get(position).getDescription());
        String str = null;
        while (matcher.find()) {

            Log.d("my", "img uri: " + matcher.group());
            str = matcher.group();
            if (matcher.find()) {
                str = matcher.group();
            }
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Picasso.with(view.getContext()).load(str).resizeDimen(R.dimen.image_size, R.dimen.image_size).centerInside().into(imageView);


        return view;
    }

   PostItem getParce(int position)
    {
  return (PostItem) getItem(position);
}





}
