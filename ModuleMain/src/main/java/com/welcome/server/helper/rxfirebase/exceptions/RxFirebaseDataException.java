package com.welcome.server.helper.rxfirebase.exceptions;


import com.google.firebase.database.DatabaseError;
import com.google.firebase.internal.NonNull;

/**
 * Created by @mistreckless on 16/05/2016.!
 */
public class RxFirebaseDataException extends Exception {

    protected DatabaseError error;

    public RxFirebaseDataException(@NonNull DatabaseError error) {
        this.error = error;
    }

    public DatabaseError getError() {
        return error;
    }

    @Override
    public String toString() {
        return "RxFirebaseDataException{" +
                "error=" + error +
                '}';
    }
}
