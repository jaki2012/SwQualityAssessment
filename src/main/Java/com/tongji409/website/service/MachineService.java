/**
 * Created by 13987 on 2016/11/27.
 */
package com.tongji409.website.service;

import com.tongji409.util.config.StaticConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MachineService {

    //传入前先确保长度是39，表示39个维度，顺序按照NASA给的顺序
    public boolean CallPython(String inputTest) throws IOException, NumberFormatException {
        String[] cmd = new String[4];
        cmd[0] = "python";
        cmd[1] = StaticConstant.PYTHON_SCRIPT_PATH;
        int studyResult = 0;
        cmd[2] = inputTest;
//        for (int i = 0; i < 39; i++) {
//            cmd[i + 2] = Double.toString(inputTest[i]);
//        }
        cmd[3] = "--ml";
// create runtime to execute external command

        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(cmd);
// retrieve output from python script
        BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = bfr.readLine()) != null) {
// display each output line form python script
            System.out.println(getClass().getName() + ":ML Result:" + line);
        }
        try {
            studyResult = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return studyResult != 0;
    }

}



