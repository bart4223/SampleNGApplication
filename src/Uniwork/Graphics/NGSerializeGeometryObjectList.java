package Uniwork.Graphics;

import Uniwork.Base.NGObject;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGSerializeGeometryObjectList extends NGObject {
    protected CopyOnWriteArrayList<NGSerializeGeometryObjectItem> SGOS;
    public NGSerializeGeometryObjectList() {
        super();
    }
    public void setSGOS(CopyOnWriteArrayList<NGSerializeGeometryObjectItem> value) { SGOS = value;}
    public CopyOnWriteArrayList<NGSerializeGeometryObjectItem> getSGOS() { return SGOS; }
}
