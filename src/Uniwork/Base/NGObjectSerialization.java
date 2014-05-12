package Uniwork.Base;

public interface NGObjectSerialization {

    public NGObject AssignTo();
    public void WriteObject();
    public void ReadObject();
    public void AssignFrom(NGObject aObject);

}
