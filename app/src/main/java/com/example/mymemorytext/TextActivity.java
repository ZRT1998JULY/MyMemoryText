package com.example.mymemorytext;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class TextActivity extends AppCompatActivity {

    private EditText editTitle;

    private EditText editContent;

    private String inputTitle;

    private String inputContent;

    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        editTitle=(EditText)findViewById(R.id.mem_title);
        editContent=(EditText)findViewById(R.id.mem_content);
        saveButton=(Button)findViewById(R.id.save_mem);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTitle=editTitle.getText().toString();
                inputContent=editContent.getText().toString();
                save(inputTitle,inputContent);
            }
        });
    }

    private void save(String title,String content){
        Memory memory=new Memory();
        memory.setTitle(inputTitle);
        memory.setContent(inputContent);
        memory.save();
        finish();
    }
}
