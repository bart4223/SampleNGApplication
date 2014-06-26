package Uniwork.Graphics;

import Uniwork.Base.NGObject;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.ArrayList;

public class NGSerializeGeometryObjectItem extends NGObject {
    protected NGGeometryObject GO;
    protected ArrayList<NGSerializePropertyItem> Props;
    public NGSerializeGeometryObjectItem() {
        super();
    }
    public void setGO(NGGeometryObject value) { GO = value;}
    public NGGeometryObject getGO() { return GO; }
    public void setProps(ArrayList<NGSerializePropertyItem> value) { Props = value;}
    public ArrayList<NGSerializePropertyItem> getProps() { return Props; }
}
