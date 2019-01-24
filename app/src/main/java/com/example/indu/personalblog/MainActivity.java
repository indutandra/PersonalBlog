package com.example.indu.personalblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendPost(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String title = editText1.getText().toString();
        String description = editText2.getText().toString();
        Bundle extras = new Bundle();
        extras.putString("EXTRA_TITLE", title);
        extras.putString("EXTRA_DESCRIPTION", description);
        intent.putExtras(extras);
        startActivity(intent);
        DatabaseHandler db = new DatabaseHandler(this);
        db.addFeed(new Feed(title, description));
        List<Feed> contacts = db.getAllFeeds();

        for (Feed cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Title: " + cn.getTitle() + " ,Description: " +
                    cn.getDescription();
            // Writing Contacts to log
            Log.d("Title: ", log);
        }
    }
}
