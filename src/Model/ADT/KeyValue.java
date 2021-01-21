package Model.ADT;

import Model.Value.Value;

public class KeyValue {

    private Integer key = null;
    private Value value = null;

    public KeyValue(Integer key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}