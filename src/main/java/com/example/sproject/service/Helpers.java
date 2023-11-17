package com.example.sproject.service;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class Helpers {
    public static String savePhoto(Part p, String pathDir, String localdir) throws IOException {
        File dir = new File(pathDir);
        if (!dir.exists()) {
            System.out.println(dir.mkdir());
        }
        else {
            System.out.println("Существует");
        }
        String[] filename_data = p.getSubmittedFileName().split("\\.");
        String filename = Math.random() + "." + filename_data[filename_data.length - 1];
        String fullpath = pathDir + File.separator + filename;
        p.write(fullpath);
        String photoPath = "/" + localdir + "/" + filename;
        return photoPath;
    }
}
