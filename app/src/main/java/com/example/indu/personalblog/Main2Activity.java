package com.example.indu.personalblog;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class FeedsAdapter extends ArrayAdapter<Feed> {
    /**
     *
     * @param context context of the app
     * @param feeds Arraylist of the users
     */
    FeedsAdapter(Context context, List<Feed> feeds) {
        super(context, 0, feeds);
    }

    /**
     *
     * @param position of type int
     * @param convertView of type View
     * @param parent of type ViewGroup
     * @return of type view
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Feed feed = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population

        TextView title = convertView.findViewById(R.id.title);
        TextView description = convertView.findViewById(R.id.description);

        // Populate the data into the template view using the data object
        assert feed != null;
        title.setText(feed._title);
        description.setText(feed._description);
        // Return the completed view to render on screen
        return convertView;
    }
}



public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        String title = extras.getString("EXTRA_TITLE");
//        String description = extras.getString("EXTRA_DESCRIPTION");
//
//        TextView textView1 = findViewById(R.id.textView);
//        textView1.setText(title);
//        TextView textView2 = findViewById(R.id.textView2);
//        textView2.setText(description);

        ListView listView = findViewById(R.id.feed_list_view);
        DatabaseHandler ac = new DatabaseHandler(getApplicationContext());
        List<Feed> feeds = ac.getAllFeeds();
        Collections.reverse(feeds);
        ArrayAdapter<Feed> adapter = new FeedsAdapter(this, feeds);
        listView.setAdapter(adapter);
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }
}
