package Uniwork.Graphics;

import Uniwork.Base.NGObject;

import java.util.ArrayList;

public class NGSerializeGeometryObjectList extends NGObject {
    protected ArrayList<NGSerializeGeometryObjectItem> SGOS;
    public NGSerializeGeometryObjectList() {
        super();
    }
    public void setSGOS(ArrayList<NGSerializeGeometryObjectItem> value) { SGOS = value;}
    public ArrayList<NGSerializeGeometryObjectItem> getSGOS() { return SGOS; }
}
