package com.example.homework12;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    Button btn_send;
    EditText ed_content;
    RecyclerView rc;
    List<Message> messages;
    MessageAdapter messageAdapter;
    /*
        문제에 맞게 onCreate 메소드를 정의하시오.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // DBHelper객체를 생성하여 테이블 생성 및 데이터 가져오기
        dbHelper = new DBHelper(this, 1);

        btn_send = findViewById(R.id.sendButton);
        ed_content = findViewById(R.id.contentsEdit);
        rc = findViewById(R.id.recyclerView);

        ed_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                if(s.length() > 0){
                    btn_send.setEnabled(true);
                }else{
                    btn_send.setEnabled(false);
                }
            }
        });
        getData();
    }

    /*
        전송 버튼을 누를 때 동작 되는 메소드
        이 메소드 내용 작성
    */
    public void sendAction(View view) {
        String s = ed_content.getText().toString();
        Message message = dbHelper.selectOne(dbHelper.insert(s, MessageType.RIGHT_CONTENTS));
        messages.add(message);
        messageAdapter.notifyDataSetChanged();
        ed_content.setText("");
    }

    public void getData(){
        messages = dbHelper.selectAll();
        messageAdapter = new MessageAdapter(messages);
        rc.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rc.setAdapter(messageAdapter);
    }

}