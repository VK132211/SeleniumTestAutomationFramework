package com.ui.dataproviders;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import static com.utility.CSVReaderUtility.readCSVFile;

public class LoginDataProvider {
    @DataProvider(name = "LoginTestDataProvider")
    public Iterator<Object[]> loginDataProvider() {
        Gson gson = new Gson();
        File testDataFile = new File(System.getProperty("user.dir") + "\\testData\\loginData.json");
        FileReader fileReader;

        {
            try {
                fileReader = new FileReader(testDataFile);
                TestData testData = gson.fromJson(fileReader, TestData.class);
                ArrayList<Object[]> dataToReturn = new ArrayList<>();
                for(User user:testData.getData()){
                    dataToReturn.add(new Object[] {user});

                }
                return dataToReturn.iterator();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @DataProvider(name = "LoginCSVTestDataProvider")
    public Iterator<User> loginCSVDataProvider(){
        return readCSVFile("loginData.csv");

    }

}
