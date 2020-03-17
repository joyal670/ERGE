package com.example.erge.resource.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.jar.Attributes;

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {

    public static final String NAME = "test";
    public static final int VERSION = 4;
    public String getDBName()
    {
        return NAME;
    }
}
