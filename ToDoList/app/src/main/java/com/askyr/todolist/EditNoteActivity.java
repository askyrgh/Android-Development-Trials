package com.askyr.todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {

    EditText edtTitle, edtDescription;

    Button btnCancel, btnSave;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        final Intent intent = getIntent();

        linearLayout = findViewById(R.id.btn_holder);
        edtDescription = findViewById(R.id.edt_edit_description);
        edtTitle = findViewById(R.id.edt_edit_title);

        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Note note = new Note(edtTitle.getText().toString(), edtDescription.getText().toString());
                note.setId(intent.getIntExtra("id", 1));

                if(new NoteHandler(EditNoteActivity.this).updateNote(note)) {
                    Toast.makeText(EditNoteActivity.this, "Note updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditNoteActivity.this, "Note update failed", Toast.LENGTH_SHORT).show();
                }
                onBackPressed();
            }
        });

        edtTitle.setText(intent.getStringExtra("title"));
        edtDescription.setText(intent.getStringExtra("description"));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBackPressed() {
        btnSave.setVisibility(View.GONE);
        btnCancel.setVisibility((View.GONE));
        TransitionManager.beginDelayedTransition(linearLayout);
        super.onBackPressed();
    }
}