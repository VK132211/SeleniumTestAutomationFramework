package com.utility;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;
import org.jspecify.annotations.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class JSONUtility {
    public static Environment readJson(Env env)  {
        Gson gson = new Gson();
        File jsonFile = new File(System.getProperty("user.dir")+"\\config\\config.json");
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Config config = gson.fromJson(fileReader, Config.class);
            Environment environment = getEnvironment(env, config, jsonFile);
            return environment;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static @NonNull Environment getEnvironment(Env env, Config config, File jsonFile) {
        Map<String, Environment> environments = config.getEnvironments();
        if (environments == null || !environments.containsKey(env.name())) {
            throw new RuntimeException("Environment '" + env.name() + "' is not configured in " + jsonFile.getPath());
        }
        Environment environment = environments.get(env.name());
        if (environment.getUrl() == null || environment.getUrl().isBlank()) {
            throw new RuntimeException("URL is not configured for environment '" + env.name() + "' in " + jsonFile.getPath());
        }
        return environment;
    }
}
