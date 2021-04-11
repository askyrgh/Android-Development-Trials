package com.askyr.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText edtMessageInput;
    private TextView txtRecipient;
    private ProgressBar progressBar;
    private ImageView imgRecipient, imgSendIcon;

    private MessageAdapter messageAdapter;
    private ArrayList<Message> messages;

    String roommateUsername, roommateEmail, chatRoomID, roommateImageURL, myImageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //assigning the values obtained through intent
        roommateUsername = getIntent().getStringExtra("username_of_roommate");
        roommateEmail = getIntent().getStringExtra("email_of_roommate");
        roommateImageURL = getIntent().getStringExtra("roommate_image_URL");
        myImageURL = getIntent().getStringExtra("my_image_URL");

        recyclerView = findViewById(R.id.recyclerMessages);
        edtMessageInput = findViewById(R.id.edtMessageInput);
        txtRecipient = findViewById(R.id.txtRecipient);
        progressBar = findViewById(R.id.progressMessages);
        imgRecipient = findViewById(R.id.imgRecipient);
        imgSendIcon = findViewById(R.id.imgSendIcon);

        txtRecipient.setText(roommateUsername);

        messages = new ArrayList<>();

        imgSendIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sending the parsed text form EditText as message(by creating a new sub-node of the messages node)
                FirebaseDatabase.getInstance()
                        .getReference("messages/" + chatRoomID)
                        .push()
                        .setValue(new Message(
                                FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                                roommateEmail,
                                edtMessageInput.getText().toString()
                                ));

                // resetting the text on EditText
                edtMessageInput.setText("");
            }
        });

        // initializing messageAdapter so the messages can be displayed on the RecyclerView with proper user info
        messageAdapter = new MessageAdapter(messages, myImageURL, roommateImageURL, MessageActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // setting the Adapter for RecyclerView
        recyclerView.setAdapter(messageAdapter);

        Glide.with(MessageActivity.this).load(roommateImageURL).error(R.drawable.account_img).placeholder(R.drawable.account_img).into(imgRecipient);

        setUpChatRoom();
    }

    private void setUpChatRoom() {
        // this method is responsible for generating room id for the chat room
        FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // assigning our own username
                String myUsername = snapshot.getValue(User.class).getUserName();

                if(roommateUsername.compareTo(myUsername) > 0) {
                    chatRoomID = myUsername + roommateUsername;
                }
                else if(roommateUsername.compareTo(myUsername) == 0) {
                    chatRoomID = myUsername + roommateUsername;
                }
                else {
                    chatRoomID = roommateUsername + myUsername;
                }

                // fetch the messages
                attachMessageListener(chatRoomID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void attachMessageListener(String chatRoomID) {
        // this method is responsible for reflecting the changes on the activity once we fetch new messages from firebase

        // if we put addListenerForSingleValueEvent we won't be notified about the changes reflected
        // but this way onDataChange will be triggered every time a new message is received through the messages node in database
        FirebaseDatabase.getInstance().getReference("messages/" + chatRoomID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // clearing out previous messages
                messages.clear();

                // updating them with new entry on every fetch
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    messages.add(dataSnapshot.getValue(Message.class));
                }

                // notifying adapter that data set has changed
                messageAdapter.notifyDataSetChanged();

                // scrolling down to the most recent message entry to the messages ArrayList
                recyclerView.scrollToPosition(messages.size() - 1);

                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}