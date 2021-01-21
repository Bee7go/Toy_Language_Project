package Model.ADT;

import Model.Value.Value;

public class VariableValue {

    private String variable = null;
    private Value value = null;

    public VariableValue(String variable, Value value) {
        this.variable = variable;
        this.value = value;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}