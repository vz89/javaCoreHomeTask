package com.vz89.hometask.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vz89.hometask.model.Account;
import com.vz89.hometask.model.Developer;
import com.vz89.hometask.model.Skill;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonService {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writeJsonToFile(List<?> list, String file) {
        try {
            Files.writeString(Paths.get(file), gson.toJson(list));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't write in " + file);
        }
    }

    public static <T> List<T> getListFromJson(String file, Class<T> tClass) {
        String string = "";
        TypeToken typeToken;

        try {
            string = Files.readString(Paths.get(file));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't read " + file);
        }
        switch (tClass.getSimpleName()) {
            case "Skill":
                typeToken = new TypeToken<List<Skill>>(){};
                break;
            case "Developer":
                typeToken = new TypeToken<List<Developer>>(){};
                break;
            case "Account" :
                typeToken = new TypeToken<List<Account>>(){};
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tClass.getName());
        }
        return gson.fromJson(string, typeToken.getType());
    }
}
