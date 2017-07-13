package Uniwork.Graphics;

import Uniwork.Base.NGObject;
import Uniwork.Base.NGSerializePropertyItem;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGSerializeGeometryObjectItem extends NGObject {
    protected NGGeometryObject GO;
    protected CopyOnWriteArrayList<NGSerializePropertyItem> Props;
    public NGSerializeGeometryObjectItem() {
        super();
    }
    public void setGO(NGGeometryObject value) { GO = value;}
    public NGGeometryObject getGO() { return GO; }
    public void setProps(CopyOnWriteArrayList<NGSerializePropertyItem> value) { Props = value;}
    public CopyOnWriteArrayList<NGSerializePropertyItem> getProps() { return Props; }
}
