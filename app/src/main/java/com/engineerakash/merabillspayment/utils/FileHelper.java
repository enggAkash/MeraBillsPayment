package com.engineerakash.merabillspayment.utils;

import android.content.Context;

import com.engineerakash.merabillspayment.data.pojo.Payment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    private String filename = "LastPayment.txt";

    private Context context;

    public FileHelper(Context context) {
        this.context = context;
    }

    public void savePayments(List<Payment> payment) throws RuntimeException {
        Type type = new TypeToken<List<Payment>>() {
        }.getType();

        String jsonString = new Gson().toJson(payment, type);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not save payment", e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public List<Payment> getPayments() {
        List<Payment> payments = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fileInputStream = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(isr);

            String oneLine;
            while ((oneLine = br.readLine()) != null) {
                sb.append(oneLine).append('\n');
            }

            Type type = new TypeToken<List<Payment>>() {
            }.getType();
            payments = new Gson().fromJson(sb.toString(), type);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return payments;
    }
}
