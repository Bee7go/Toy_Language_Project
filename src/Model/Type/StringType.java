package Model.Type;

import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

public class StringType implements Type{

    public Value defaultValue(){
        return new StringValue("");
    }

    public boolean equals(Object another){
        if (another instanceof StringType)
            return true;
        else
            return false;
    }
    public String toString() { return "string";}
}
