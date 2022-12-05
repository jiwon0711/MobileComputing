package com.example.mobilecomputing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CancelPopup extends Activity {

    String key;
    int position;
    TextView txtText;
    Button confirm_btn;
    Button cancel_btn;
    PreferenceManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_cancel_popup);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cancel_popup);

        pref = new PreferenceManager();

        //UI 객체생성
        txtText = (TextView)findViewById(R.id.txtText);
        confirm_btn = findViewById(R.id.confirm_btn);
        cancel_btn = findViewById(R.id.cancel_btn);

        //데이터 가져오기
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        int position = intent.getIntExtra("position", 0);
        Log.d("CancelPopup", key+"/"+position);

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 전달하기
                Intent intent = new Intent();

                intent.putExtra("key", key);
                intent.putExtra("position", position);
                setResult(RESULT_OK, intent);

                //액티비티 팝업 닫기
                finish();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event){
        //바깥 레이어 클릭시 안닫히게
       if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
        return false;
    }
    return true;
}
public void onBackPressed(){
        //안드로이드 백버튼 막기
        return;
}
}
