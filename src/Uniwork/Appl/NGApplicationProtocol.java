package Uniwork.Appl;

import Uniwork.Base.NGObject;
import Uniwork.Misc.NGCustomLogEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class NGApplicationProtocol extends NGObject{

    public static final String CDomainCommon = "COMMON";
    public static final String CSubDomainCommon = "COMMON";

    protected String FDomain;
    protected String FSubDomain;
    protected ArrayList<NGApplicationProtocolItem> FItems;

    public static NGApplicationProtocol Open() {
        return Open(CDomainCommon, CSubDomainCommon);
    }

    public static NGApplicationProtocol Open(String aDomain, String aSubDomain) {
        return new NGApplicationProtocol(aDomain, aSubDomain);
    }

    public NGApplicationProtocol(String aDomain, String aSubDomain) {
        super();
        FItems = new ArrayList<NGApplicationProtocolItem>();
        FDomain = aDomain;
        FSubDomain = aSubDomain;
    }

    public String getDomain() {
        return FDomain;
    }

    public void Close() {
        
    }

    public void writeInfo(String aInfo) {
        this.writeInfo(aInfo, "");
    }

    public void writeInfo(String aInfo, String aHint) {
        FItems.add(new NGApplicationProtocolItem(FSubDomain, aInfo, aHint, NGCustomLogEntry.LogType.Info));
    }

    public void writeWarning(String aWarning) {
        this.writeWarning(aWarning, "");
    }

    public void writeWarning(String aWarning, String aHint) {
        FItems.add(new NGApplicationProtocolItem(FSubDomain, aWarning, aHint, NGCustomLogEntry.LogType.Warning));
    }

    public void writeError(String aError) {
        this.writeError(aError, "");
    }

    public void writeError(String aError, String aHint) {
        FItems.add(new NGApplicationProtocolItem(FSubDomain, aError, aHint, NGCustomLogEntry.LogType.Error));
    }

    public Integer getItemCount() {
        return FItems.size();
    }

    public Iterator<NGApplicationProtocolItem> getItems() {
        return FItems.iterator();
    }

    public void SaveAsText(File aFile) {
        try
        {
            FileOutputStream is = new FileOutputStream(aFile);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);
            for (NGApplicationProtocolItem item : FItems) {
                w.write(item.GetText() + "\n");
            }
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
