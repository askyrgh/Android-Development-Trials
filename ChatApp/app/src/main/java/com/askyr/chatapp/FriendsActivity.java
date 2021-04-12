package com.askyr.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private ProgressBar progressBar;
    private UsersAdapter usersAdapter;
    private UsersAdapter.OnUserClickListener onUserClickListener;
    private SwipeRefreshLayout swipeRefreshLayout;

    private String myImageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        progressBar = findViewById(R.id.progressBar);
        users = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);

        onUserClickListener = new UsersAdapter.OnUserClickListener() {
            @Override
            public void onUserClicked(int position) {
                startActivity(new Intent(FriendsActivity.this, MessageActivity.class)
                        .putExtra("username_of_roommate", users.get(position).getUserName())
                        .putExtra("email_of_roommate", users.get(position).getEmail())
                        .putExtra("roommate_image_URL", users.get(position).getProfilePicture())
                        .putExtra("my_image_URL", myImageURL)
                );

                // Toast.makeText(FriendsActivity.this, "Tapped on user " + users.get(position).getUserName(), Toast.LENGTH_SHORT).show();
            }
        };

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // refreshing on pull
                getUsers();
                // disabling the refresh to prevent indefinite spinning of the indicator
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        getUsers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menu will be displayed on clicking the profile menu option
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemProfile) {
            // on pressing the profile(menu item) we are redirected to ProfileActivity
            startActivity(new Intent(FriendsActivity.this, ProfileActivity.class).putExtra("profile_image_URL", myImageURL));
        }
        return super.onOptionsItemSelected(item);
    }

    private void getUsers() {
        // clearing the previously populated array and repopulating it with updated entries
        users.clear();
        // fetching the users present in the users table in database
        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // adding User instances to the ArrayList
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    users.add(dataSnapshot.getValue(User.class));
                }
                // initializing the UserAdapter instance so RecyclerView can show the users
                usersAdapter = new UsersAdapter(users, FriendsActivity.this, onUserClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(FriendsActivity.this));
                recyclerView.setAdapter(usersAdapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                // traversing the users list to locate our own user data in the list
                for (User user : users) {
                    // if emails match than write the myImageURL
                    if(user.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                        myImageURL = user.getProfilePicture();
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}