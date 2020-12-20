package com.danilapots.varC3.application;

import com.danilapots.varC3.data.creator.DessertCreator;
import com.danilapots.varC3.data.extractor.DataExtractor;
import com.danilapots.varC3.data.extractor.implementation.CommandExtractor;
import com.danilapots.varC3.data.extractor.implementation.DessertDataExtractor;
import com.danilapots.varC3.entity.Dessert;
import com.danilapots.varC3.logic.DessertCommandExecutor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataExtractor dessertDataExtractor = new DessertDataExtractor();
        DataExtractor commandDataExtractor = new CommandExtractor();
        List<String> dessertData = dessertDataExtractor.extract(args);
        List<String> commandData = commandDataExtractor.extract(args);

        List<Dessert> desserts = readDessertData(dessertData);
        processCommands(desserts, commandData);
    }

    private static List<Dessert> readDessertData(List<String> dessertData) {
        DessertCreator dessertCreator = new DessertCreator();
        List<Dessert> desserts = new ArrayList<>();
        for(String dessert : dessertData) {
            try {
                Dessert newDessert = dessertCreator.createDessert(dessert);
                desserts.add(newDessert);
            } catch (Exception e) {
                System.out.println("Warning: " + dessert + " will be not created. Invalid data.");
            }
        }
        return desserts;
    }

    private static void processCommands(List<Dessert> desserts, List<String> commands) {
        if (desserts.size() == 0) {
            System.out.println("Sorry, but there are no available desserts :(");
        } else {
            DessertCommandExecutor commandExecutor = new DessertCommandExecutor(desserts);
            for (String command : commands) {
                commandExecutor.execute(command);
            }
        }
    }

}