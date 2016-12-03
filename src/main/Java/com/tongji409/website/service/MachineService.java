/**
 * Created by 13987 on 2016/11/27.
 */
package com.tongji409.website.service;

import com.tongji409.util.config.StaticConstant;

import java.io.*;

public class MachineService {

    //传入前先确保长度是39，表示39个维度，顺序按照NASA给的顺序
    public int CallPython(double[] inputTest) throws IOException, NumberFormatException {
        String[] cmd = new String[42];
        cmd[0] = "python";
        cmd[1] = StaticConstant.PYTHON_SCRIPT_PATH;
        int StudyResult = 0;
        for (int i = 0; i < 39; i++) {
            cmd[i + 2] = Double.toString(inputTest[i]);
        }
        cmd[41] = "--ml";
// create runtime to execute external command

        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(cmd);
// retrieve output from python script
        BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line = bfr.readLine()) != null) {
// display each output line form python script
            System.out.println(line);
        }
        try {
            StudyResult = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return StudyResult;
    }

}



