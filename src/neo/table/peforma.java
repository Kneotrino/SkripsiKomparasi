/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo.table;

import java.io.Serializable;

/**
 *
 * @author SEED
 */
public class peforma implements Serializable{

    public peforma() {
    }

    public peforma(String info) {
        this.info = info;
    }
    
    private long classficationMemory;

    /**
     * Get the value of classficationMemory
     *
     * @return the value of classficationMemory
     */
    public long getClassficationMemory() {
        return classficationMemory;
    }

    /**
     * Set the value of classficationMemory
     *
     * @param classficationMemory new value of classficationMemory
     */
    public void setClassficationMemory(long classficationMemory) {
        this.classficationMemory = classficationMemory;
    }

    private long trainingTime;

    private long trainningMemory;

    /**
     * Get the value of trainningMemory
     *
     * @return the value of trainningMemory
     */
    public long getTrainningMemory() {
        return trainningMemory;
    }

    /**
     * Set the value of trainningMemory
     *
     * @param trainningMemory new value of trainningMemory
     */
    public void setTrainningMemory(long trainningMemory) {
        this.trainningMemory = trainningMemory;
    }

    /**
     * Get the value of trainingTime
     *
     * @return the value of trainingTime
     */
    public long getTrainingTime() {
        return trainingTime;
    }

    /**
     * Set the value of trainingTime
     *
     * @param trainingTime new value of trainingTime
     */
    public void setTrainingTime(long trainingTime) {
        this.trainingTime = trainingTime;
    }

    private long classficationTime;

    /**
     * Get the value of classficationTime
     *
     * @return the value of classficationTime
     */
    public long getClassficationTime() {
        return classficationTime;
    }

    /**
     * Set the value of classficationTime
     *
     * @param classficationTime new value of classficationTime
     */
    public void setClassficationTime(long classficationTime) {
        this.classficationTime = classficationTime;
    }

    private String info;

    /**
     * Get the value of info
     *
     * @return the value of info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Set the value of info
     *
     * @param info new value of info
     */
    public void setInfo(String info) {
        this.info = info;
    }

}
