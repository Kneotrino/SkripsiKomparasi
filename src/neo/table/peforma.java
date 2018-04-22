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
