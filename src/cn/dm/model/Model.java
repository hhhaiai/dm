package cn.dm.model;

public class Model {
    private String key;
    private long totalTimeStamp;
    private long value;
    private long count;
    private String label;

    public Model() {
    }

    public Model(String key, long totalTimeStamp, long value) {
        this(key, totalTimeStamp, value, 1, null);
    }

    public Model(String key, long totalTimeStamp, long value, long count) {
        this(key, totalTimeStamp, value, count, null);
    }

    public Model(String key, long totalTimeStamp, long value, long count, String label) {
        this.key = key;
        this.totalTimeStamp = totalTimeStamp;
        this.value = value;
        this.count = count;
        this.label = label;
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

    public String toStringOther() {
        return "key:\040" + key + "\r\ntotalTimeStamp:\040" + totalTimeStamp + "\r\nvalue:\040" + value
                + "\r\ncount:\040" + count + (label == null ? "" : ("\r\nlabel:\040" + label));
    }

    @Override
    public String toString() {
        return String.format("key: %s\r\ntotalTimeStamp: %s\r\nvalue: %s\r\ncount: %s", key, totalTimeStamp, value,
                count) + (label == null ? "" : ("\r\nlabel:\040" + label));
    }
}
