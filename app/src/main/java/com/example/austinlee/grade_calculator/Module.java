package com.example.austinlee.grade_calculator;

import java.util.ArrayList;

/**
 * Created by austinlee on 4/29/17.
 */

public class Module {
    String name;
    Double score;
    Double totalScore;
    String classification;
    Double classWeight;


    public Module(){

    }

    public Module(String name, Double score, Double totalScore, String classification, Double classWeight) {
        this.name = name;
        this.score = score;
        this.totalScore = totalScore;
        this.classification = classification;
        this.classWeight = classWeight;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public Double getScore() {
        return score;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public String getClassification() {
        return classification;
    }

    public Double getClassWeight() {
        return classWeight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setClassWeight(Double classWeight) {
        this.classWeight = classWeight;
    }
}

