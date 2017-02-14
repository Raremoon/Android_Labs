package com.frankmeana.androidlabs;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    ListView listView;
    EditText messageBox;
    Button sendButton;
    ArrayList<String> list;
    ChatAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        listView = (ListView) findViewById(R.id.listView);
        messageBox = (EditText) findViewById(R.id.editText);
        sendButton = (Button) findViewById(R.id.sendButton);
        list = new ArrayList<String>();

        messageAdapter = new ChatAdapter(this);  //set the data source of the listView to be a new ChatAdapter object:
        listView.setAdapter(messageAdapter);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(messageBox.getText().toString());
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView() - to update the listView whenever there is new data to display
                messageBox.setText("");
            }
        });
    }


    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);  //You can pass 0 as the int resource parameter because you will not be using the default layout:
        }

        public int getCount() {
            return list.size(); //number of strings in the array list object
        }

        public String getItem(int position) {
            return list.get(position);   //returns the item to show in the list at the specified position
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            //returns the layout that will be positioned at the specified position in the list.
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater(); //recreates your View that you made in the resource file
            View result = null;
            if (position % 2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = (TextView) result.findViewById(R.id.message_text); //From the resulting view, get the TextView which holds the string message:
            message.setText(getItem(position)); // get the string at position
            return result;


        }
    }


}

