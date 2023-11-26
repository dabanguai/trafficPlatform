package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 15968
 * @version 1.0
 * @description: TODO
 * @date 2023/11/26 8:18
 */
@RestController
@RequestMapping("/python")
public class PythonController {

    @GetMapping("/run")
    public String runPythonScript() {
        try {
            // 指定Python脚本的路径
            String pythonScriptPath = "D:\\python\\projects\\traffic\\TrafficGPT\\DataProcessBot.py";

            // 使用ProcessBuilder执行Python脚本
            ProcessBuilder processBuilder = new ProcessBuilder("D:\\anaconda\\anaconda3\\envs\\trafficSystem\\python.exe", pythonScriptPath);
            Process process = processBuilder.start();

            // 用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream())); // 新增这一行

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line).append("\n");
            }
            in.close();

            // 读取错误流
            StringBuilder errorResult = new StringBuilder();
            while ((line = error.readLine()) != null) {
                errorResult.append(line).append("\n");
            }
            error.close();

            // 等待Python脚本执行完成
            int exitCode = process.waitFor();

            // 返回结果或错误信息
            if (exitCode == 0) {
                return "Python脚本执行成功\n" + result.toString();
            } else {
                return "Python脚本执行失败，错误信息：\n" + result.toString() + "\n错误详情：\n" + errorResult.toString();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "执行过程中出现异常：" + e.getMessage();
        }
    }
}

