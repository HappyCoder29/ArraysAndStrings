package edu.northeastern.ashish;

public class Interval {
    public int start;
    public int end;
    public int diff;

    private Interval(){}
    public Interval(int start, int end){
        this.start = start;
        this.end = end;
        this.diff = end -start;
    }
}
