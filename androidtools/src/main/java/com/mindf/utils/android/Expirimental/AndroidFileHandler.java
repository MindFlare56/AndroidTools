package com.mindf.utils.android.Expirimental;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;
import com.google.common.base.Charsets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.SneakyThrows;

public class AndroidFileHandler {

    @SneakyThrows
    public static File writeFileOnInternalStorage(Context context, String folderName, String fileName, String data) {
        File file = new File(context.getFilesDir(), folderName);
        if (!file.exists()) {
            file.mkdir();
        }
        File gpxFile = new File(file, fileName);
        FileWriter fileWriter = new FileWriter(gpxFile);
        fileWriter.append(data);
        fileWriter.flush();
        fileWriter.close();
        return file;
    }

    @SneakyThrows
    public static String getInternalStorageFileContent(Context context, String fileName) {
        FileInputStream fileInputStream;
        StringBuilder stringBuilder = new StringBuilder();
        fileInputStream = context.openFileInput(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    public static File getFile(Context context, String fileName) {
        return new File(context.getFilesDir() + "/" + fileName + ".txt");
    }
}
