package com.example.hrtest.service;

import com.example.hrtest.config.AppProperties;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class IPReader {

    private ArrayList<String> blackList;

    public List<String> read() {
        blackList = new ArrayList<>();
        String path = AppProperties.getProperties().getProperty("path");
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            while (line != null) {
                blackList.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Не найден файл blacklist.txt");
            e.printStackTrace();
        }
        return blackList;
    }
}
