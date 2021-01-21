package Model.Value;

import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value{
    String val;
    public StringValue(String v){val=v;}
    public String getVal() {return val;}
    public String toString() {return val;}
    public Type getType() { return new StringType();}

    @Override
    public boolean equals(Object another) {
        if(another == this)
            return true;

        if(!(another instanceof StringType))
            return false;

        StringValue b = (StringValue) another;
        if(this.val.equals(b.val))
            return true;
        else
            return false;
    }
}
