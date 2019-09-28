package com.example.rmapplication;

import java.io.Serializable;

public class EpisodeN implements Serializable
{

    String id;
    String name;
    String air_date;
    String episode;
    String characters;
    String url;

    String created;

    public EpisodeN(String id, String name, String air_date, String episode, String characters, String url, String created) {
        this.id = id;
        this.name = name;
        this.air_date = air_date;
        this.episode = episode;
        this.characters = characters;
        this.url = url;
        this.created = created;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getAir_date() {
        return air_date;
    }

    public String getCharacters() {
        return characters;
    }

    public String getUrl() {
        return url;
    }

    public String getCreated() {
        return created;
    }


}
