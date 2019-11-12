package com.example.hw4birds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editTextZip;
    TextView textViewBName, textViewPName;
    Button buttonSearch, buttonGoToReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextZip = findViewById(R.id.editTextZip);
        textViewBName = findViewById(R.id.textViewBName);
        textViewPName = findViewById(R.id.textViewPName);
        buttonGoToReport = findViewById(R.id.buttonGoToReport);
        buttonSearch = findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(this);
        buttonGoToReport.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Birds");
        if (view == buttonSearch) {
            int findZipCode = Integer.parseInt(editTextZip.getText().toString());
            myRef.orderByChild("zipCode").equalTo(findZipCode).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                   String findKey = dataSnapshot.getKey();
                   Bird foundBird = dataSnapshot.getValue(Bird.class);
                   String findBName = foundBird.birdName;
                   String findPName = foundBird.personName;
                   textViewBName.setText(findBName);
                   textViewPName.setText(findPName);






                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        } else if (view == buttonGoToReport) {
            Intent mainIntent = new Intent(SearchActivity.this, MainActivity.class);
            startActivity(mainIntent);
        }
    }
}
