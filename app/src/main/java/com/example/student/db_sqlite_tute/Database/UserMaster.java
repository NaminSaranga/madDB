package com.example.student.db_sqlite_tute.Database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public  final class UserMaster {



        private UserMaster() {}


            protected static class Users implements BaseColumns {
                public static final String TABLE_NAME = "users";
                public static final String COLUMN_NAME_USERNAME = "username";
                public static final String COLUMN_NAME_PASSWORD = "password";
            }



}
