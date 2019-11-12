package com.example.hw4birds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextBName, editTextZip, editTextPName;
    Button buttonSubmit, buttonGoToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBName = findViewById(R.id.editTextBName);
        editTextZip = findViewById(R.id.editTextZip);
        editTextPName = findViewById(R.id.editTextPName);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonGoToSearch = findViewById(R.id.buttonGoToSearch);

        buttonSubmit.setOnClickListener(this);
        buttonGoToSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Birds");

        if (view == buttonSubmit) {
            String createBirdName = editTextBName.getText().toString();
            String createPersonName = editTextPName.getText().toString();
            int createZipCode = Integer.parseInt(editTextZip.getText().toString());
            Bird myBird = new Bird(createBirdName, createZipCode, createPersonName);
            myRef.push().setValue(myBird);

        } else if (view == buttonGoToSearch) {
            Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(searchIntent);
        }
    }
}
