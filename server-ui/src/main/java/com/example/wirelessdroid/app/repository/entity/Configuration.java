package com.example.wirelessdroid.app.repository.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Configuration implements KeyValuePair<String, String> {
    @NonNull
    @PrimaryKey
    public String key;
    public String value;

    @Ignore
    public Configuration() {
        key = "default";
        value = "default";
    }

    public Configuration(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public void key(String key) {
        this.key = key;
    }

    @Override
    public void value(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
