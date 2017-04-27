package com.welcome.server.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.tasks.Task;

/**
 * Created by @mistreckless on 24.12.2016.!
 */
public class FirebaseHelper {

    public static String createCustomToken(String uid) throws Exception {
        String token=null;
        Task<String> task=FirebaseAuth.getInstance().createCustomToken(uid);
        while (!task.isComplete()){
            Thread.sleep(10);
        }
        if (task.isSuccessful())
            token=task.getResult();
        else throw new Exception("cannot create token");
        return token;
    }

}
