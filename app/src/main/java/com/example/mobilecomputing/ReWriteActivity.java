package com.example.mobilecomputing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReWriteActivity extends AppCompatActivity {
    PreferenceManager pref;
    Button back_btn;
    Button save_btn;
    EditText title;
    EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);


        pref = new PreferenceManager();
        back_btn = findViewById(R.id.back_btn);
        save_btn = findViewById(R.id.save_btn);
        // editText 할당
        title = findViewById(R.id.memo_title_edit);
        content = findViewById(R.id.memo_content_edit);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent get_intent = getIntent();
        String get_key = get_intent.getStringExtra("key");
        String get_title = get_intent.getStringExtra("title");
        String get_content = get_intent.getStringExtra("content");
        Log.d("ReWrite", get_title+"/"+get_content);

        title.setText(get_title);
        content.setText(get_content);

        save_btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // 저장 버튼을 눌러
                                            // 작성한 editText를 저장
                                            String edit_title = title.getText().toString();
                                            String edit_content = content.getText().toString();
                                            String save_form = "{\"title\":\"" + edit_title + "\",\"content\":\"" + edit_content + "\"}";

                                            Log.d("WriteActivity", "제목 : " + edit_title + ", 내용 : " + edit_content + ", 현재시간 : " + get_key);
                                            pref.setString(getApplication(), get_key, save_form);

                                            Intent intent = new Intent();
                                            intent.putExtra("data", get_key);
                                            intent.putExtra("data", edit_title);
                                            intent.putExtra("data", edit_content);

                                            setResult(RESULT_OK, intent);
                                            finish();
                                        }
                                    });

            }
}
