package Model.Value;

import Model.Type.RefType;
import Model.Type.StringType;
import Model.Type.Type;

public class RefValue implements Value{
    int address;
    Type locationType;

    public RefValue(int _address, Type _locationType){address=_address; locationType = _locationType;}

    public RefValue() { }

    public int getAddr() {return address;}

    public Type getType() { return new RefType(locationType);}

    public Type getLocationType() {return locationType;}
    
    public String toString() { return "(" + Integer.toString(address) + "," + locationType.toString() + ")";}

    @Override
    public boolean equals(Object another) {
        if (another instanceof RefValue)
            return true;
        return false;
    }
}