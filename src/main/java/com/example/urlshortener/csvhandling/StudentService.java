package com.example.urlshortener.csvhandling;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {
    HashMap<Integer, Student> map = new HashMap<>();

    @Value("${csv.file.path}")
    private String filePath;

    public void loadFromCsv() throws IOException, CsvException{
        try{
            CSVReader csvReader = new CSVReader(new FileReader(filePath));
            List<String[]> list = csvReader.readAll();
            for(int i=0;i<list.size();i++){
                String[] record = list.get(i);
                int id = Integer.parseInt(record[0]);
                String name = record[1];
                String dob = record[2];
                map.put(id, new Student(id,name, dob));
            }
        }catch (IOException e){
            System.out.println(e);
        }

    }
    public Student getStudentByid(int id){
        return map.get(id);
    }
    public void printAllStudents(){
        for(int i : map.keySet()){
            System.out.println(i);
        }
    }
}
