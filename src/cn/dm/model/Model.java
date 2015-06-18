package cn.dm.model;

public class Model {
    private String key;
    private long totalTimeStamp;
    private long value;
    private long count;
    private String label;

    @Override
    public String toString() {
        return "key:\040" + key + "\r\ntotalTimeStamp:\040" + totalTimeStamp + "\r\nvalue:\040" + value
                + "\r\ncount:\040" + count + (label == null ? "" : ("\r\nlabel:\040" + label));
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTotalTimeStamp() {
        return totalTimeStamp;
    }

    public void setTotalTimeStamp(long totalTimeStamp) {
        this.totalTimeStamp = totalTimeStamp;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
