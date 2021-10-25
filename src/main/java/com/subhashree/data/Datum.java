package com.subhashree.data;

import lombok.Data;

@Data
public class Datum{
    public Identification identification;
    public Classification classification;
    public Descriptives descriptives;
    public LifeCycle lifeCycle;
    public Responsibilities responsibilities;
    public HistoricalLifeCycle historicalLifeCycle;
    public SystemOfRecord systemOfRecord;
    
    
}
