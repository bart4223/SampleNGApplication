package Uniwork.Misc;

public interface NGLogEventListenerManagement {

    public void addLogListener(NGLogEventListener aLogListener);
    public void removeLogListener(NGLogEventListener aLogListener);
    public void clearLog();
    public String getCompleteLog();
    public String getCompleteLog(String aFormat);

}
