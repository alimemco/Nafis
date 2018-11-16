package com.ali.rnp.nafis.view;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ali.rnp.nafis.view.DataModel.Question;

import java.util.List;

public class DbManager extends SQLiteOpenHelper {

    private static final String TAG = "DbManager";

    private static final String DATABASE_NAME="FromQuestions";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME_QUESTION="question";

    private static final String COL_ID="id";
    private static final String COL_QUESTION="question";
    private static final String COL_ANSWER_1="FirstAnswer";
    private static final String COL_ANSWER_2="SecondAnswer";
    private static final String COL_ANSWER_3="ThirdAnswer";
    private static final String COL_ANSWER_4="FourthAnswer";
    private static final String COL_ANSWER_5="FifthAnswer";
    private static final String COL_ANSWER_6="SixthAnswer";
    private static final String COL_ANSWER_7="SeventhAnswer";
    private static final String COL_ANSWER_8="EighthAnswer";
    private static final String COL_ANSWER_9="NinthAnswer";

    private static final String CREATE_QUESTION_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_QUESTION+"("+
            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_QUESTION+" TEXT, "+
            COL_ANSWER_1+" TEXT NOT NULL DEFAULT 'free', "+
            COL_ANSWER_2+" TEXT NOT NULL DEFAULT 'free', "+
            COL_ANSWER_3+" TEXT NOT NULL DEFAULT 'free', "+
            COL_ANSWER_4+" TEXT NOT NULL DEFAULT 'free', "+
            COL_ANSWER_5+" TEXT NOT NULL DEFAULT 'free', "+
            COL_ANSWER_6+" TEXT NOT NULL DEFAULT 'free', "+
            COL_ANSWER_7+" TEXT NOT NULL DEFAULT 'free', "+
            COL_ANSWER_8+" TEXT NOT NULL DEFAULT 'free', "+
            COL_ANSWER_9+" TEXT NOT NULL DEFAULT 'free');";

    Context context;
    public DbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_QUESTION_TABLE);
            Log.i(TAG, "Created successfully");
        }catch (SQLException e){
            Log.i(TAG, "error in create database ");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(COL_ID,question.getId());
        cv.put(COL_QUESTION,question.getQuestion());
        cv.put(COL_ANSWER_1,question.getAnswer1());
        cv.put(COL_ANSWER_2,question.getAnswer2());
        cv.put(COL_ANSWER_3,question.getAnswer3());
        cv.put(COL_ANSWER_4,question.getAnswer4());
        cv.put(COL_ANSWER_5,question.getAnswer5());
        cv.put(COL_ANSWER_6,question.getAnswer6());
        cv.put(COL_ANSWER_7,question.getAnswer7());
        cv.put(COL_ANSWER_8,question.getAnswer8());
        cv.put(COL_ANSWER_9,question.getAnswer9());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long IsInserted = sqLiteDatabase.insert(TABLE_NAME_QUESTION,null,cv);

        if (IsInserted!=-1){
            Log.i(TAG, "addQuestion: ");
        }else {
            Log.i(TAG, "Error add: ");
            //
        }
    }


    public void addQuestions(List<Question> questions){
        for (int i = 0; i < questions.size(); i++) {
            if (!checkExistsQuestion(questions.get(i).getId())){
                addQuestion(questions.get(i));
            }

        }
    }

    public boolean checkExistsQuestion(int questionId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "
                        +TABLE_NAME_QUESTION
                        +" WHERE "
                        +COL_ID
                        +" = ?",
                new String[] {String.valueOf(questionId)}
        );

        return cursor.moveToFirst();
    }
}
