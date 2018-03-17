package com.example.mymemorytext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class LookActivity extends AppCompatActivity {

    private TextView title;

    private TextView content;

    private String myTitle;

    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);
        title=(TextView)findViewById(R.id.look_title);
        content=(TextView)findViewById(R.id.look_content);
        deleteButton=(Button)findViewById(R.id.delete_mem) ;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDelete();
            }
        });
        Intent intent=getIntent();
        myTitle=intent.getStringExtra("title");
        init(myTitle);


    }

    private void init(String strTitle){
        List<Memory> memories=DataSupport.findAll(Memory.class);
        title.setText("标题："+strTitle);
        for(Memory memory:memories) {
            if(memory.getTitle().equals(strTitle)) {
                content.setText("内容："+memory.getContent());
                break;
            }
        }
    }

    private void myDelete(){
        List<Memory> memories=DataSupport.findAll(Memory.class);
        for(Memory memory:memories) {
            if(memory.getTitle().equals(myTitle)) {
                memory.delete();
                break;
            }
        }
        if(memories.size()<=0)
        {
            Toast.makeText(LookActivity.this,"暂无备忘录，请新建",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(LookActivity.this,TextActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
