package com.example.recyclerviewtodolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button insertButton;
    EditText todoEdit;
    private ArrayList<Todo> todoArrayList;
    private TodoAdapter todoAdapter; //어뎁터를 사용하기 위해 정의




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler1);

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //상하로 움직이는 리사이클러뷰, 반드시 지정해야 함



        // 리사이클러뷰에 TodoAdapter 객체 지정.
        todoArrayList = new ArrayList<>();
        todoAdapter = new TodoAdapter(todoArrayList); //어뎁터 안에 ArrayList 넣기
        recyclerView.setAdapter(todoAdapter); //어뎁터를 셋팅

        insertButton = (Button) findViewById(R.id.submit_button);
        todoEdit = (EditText) findViewById(R.id.edit_todo_main);


        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEdit = todoEdit.getText().toString();
                if(getEdit.isEmpty()) {
                        Toast.makeText(MainActivity.this, "메세지를 입력하세요", Toast.LENGTH_SHORT).show();
                    } else {
                        Todo newTodo = new Todo(todoEdit.getText().toString()); //입력한 문자열로 Todo 객체 생성
                        todoArrayList.add(newTodo); //생성한 객체를 ArrayList<Todo> 타입의 TodoArrayList에 추가
                        todoAdapter.notifyDataSetChanged(); //어뎁터에게 데이터 셋이 변경되었음을 알린다.
                        todoEdit.setText(null);
                    }
                }
        });

        todoEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            insertButton.callOnClick();
                        }
                    }, 1500);
                    return true;
                    }
                return false;
            }
        });
    }
}