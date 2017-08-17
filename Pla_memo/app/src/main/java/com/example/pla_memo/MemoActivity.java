package com.example.pla_memo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MemoActivity extends Activity {
    Intent mIntent;
    ListView memoList;
    MemoDB helper;
    MemoListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.memo_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(MemoActivity.this, MemoAddEdit.class);
                addIntent.putExtra("CON", 1);
                startActivityForResult(addIntent, 1);
            }
        });

        mAdapter = new MemoListAdapter();
        memoList = (ListView)findViewById(R.id.memolist);
        helper = new MemoDB(this);
        makeList();
        memoList.setAdapter(mAdapter);

        memoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MemoListItem mli = mAdapter.getItem(i);
                mIntent = new Intent(MemoActivity.this, MemoCont.class);
                mIntent.putExtra("id", mli.getmId());
                startActivityForResult(mIntent, 1);
            }
        });

        memoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final MemoListItem mli = mAdapter.getItem(i);
                new AlertDialog.Builder(MemoActivity.this).setTitle("메모 삭제").setMessage("삭제 하시겠습니까?").
                        setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int but) {
                                SQLiteDatabase db = helper.getWritableDatabase();
                                db.delete("plamemo_exc", "_id=" + mli.getmId(), null);
                                db.close();
                                makeList();
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int but) {
                    }}).show();
                return true;
            }
        });
    }

    public void makeList() {
        mAdapter.clearItem();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("select _id, date, title, content, time from plamemo_exc order by time desc;", null);
        while(c.moveToNext()) {
            mAdapter.addItem(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
        }
        c.close();
        db.close();
        mAdapter.notifyDataSetChanged();
    }

    @Override   //intent가 finish 된 뒤 실행.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            makeList();
        }
    }
}
