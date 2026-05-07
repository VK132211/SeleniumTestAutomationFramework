package com.ui.pojo;

import java.util.Map;

public class Config {
    public Map<String, Environment> getEnvironments() {
        return environments;
    }

    public void setEnvironments(Map<String, Environment> environments) {
        this.environments = environments;
    }

    Map<String,Environment> environments;
}
