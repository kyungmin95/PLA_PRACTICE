package com.example.pla_memo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.app.AlertDialog;

public class MemoCont extends Activity {
    Intent conIntent;
    TextView tit, cont;
    int mid;
    MemoDB mhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_content);

        conIntent = getIntent();
        tit = (TextView)findViewById(R.id.memo_title);
        cont = (TextView)findViewById(R.id.memo_cont);
        mid = conIntent.getExtras().getInt("id");
        mhelper = new MemoDB(this);

        setData();

        findViewById(R.id.memo_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MemoCont.this).setTitle("메모 삭제").setMessage("삭제 하시겠습니까?").
                        setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int but) {
                                SQLiteDatabase db = mhelper.getWritableDatabase();
                                db.delete("plamemo_exc", "_id=" + mid, null);
                                db.close();
                                setResult(RESULT_OK, conIntent);
                                finish();
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int but) {
                    }}).show();
            }
        });
    }

    public void editMemo(View v){
        Intent editIntent = new Intent(MemoCont.this, MemoAddEdit.class);
        editIntent.putExtra("CON", 2);
        editIntent.putExtra("id", mid);
        startActivityForResult(editIntent, 1);
    }

    @Override   //intent가 finish 된 뒤 실행.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(data.getExtras().getInt("Del") == 1) {
                setResult(RESULT_OK, conIntent);
                finish();
            }
            else setData();
        }
    }

    //취소 버튼을 누르는 경우 실행. 인텐트가 종료 되었을 때 메인 화면에서 리스트 변환을 시키기 위해 result 값을 주고 인텐트를 종료시킨다.
    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, conIntent);
        finish();
        super.onBackPressed();
    }

    public void setData() {
        SQLiteDatabase db = mhelper.getReadableDatabase();
        Cursor c = db.rawQuery("select title, content from plamemo_exc where _id = '"+ mid + "';", null);
        while(c.moveToNext()) {
            tit.setText(c.getString(0));
            cont.setText(c.getString(1));
        }
        c.close();
        db.close();
    }
}