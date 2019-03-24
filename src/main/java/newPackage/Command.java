package newPackage;

import java.util.List;

public class Command{

    //@Deprecated
    protected static long timeCurrent;
    protected long commandId;
    protected String commandVersion;
    protected String commandName;

    protected int[] arrayData = new int[11];

    protected long importance;
    protected long importanceCurrent;
    protected long timeDesable;
    protected long timeIfSendAbsolut;

    protected Boolean isTimed;
    protected long timeDelta;
    protected long timeNext;

    protected long timeIfExpired;
    protected long timeIfExpiredBonus;


    protected List<CommandO> commandOList;


    public static long getTimeCurrent() {
        return timeCurrent;
    }

    public static void setTimeCurrent(long timeCurrent) {
        Command.timeCurrent = timeCurrent;
    }

    public long getCommandId() {
        return commandId;
    }

    public void setCommandId(long commandId) {
        this.commandId = commandId;
    }

    public String getCommandVersion() {
        return commandVersion;
    }

    public void setCommandVersion(String commandVersion) {
        this.commandVersion = commandVersion;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public int[] getArrayData() {
        return arrayData;
    }

    public void setArrayData(int[] arrayData) {
        this.arrayData = arrayData;
    }

    public long getImportance() {
        return importance;
    }

    public void setImportance(long importance) {
        this.importance = importance;
    }

    public long getTimeDesable() {
        return timeDesable;
    }

    public void setTimeDesable(long timeDesable) {
        this.timeDesable = timeDesable;
    }

    public long getTimeIfSendAbsolut() {
        return timeIfSendAbsolut;
    }

    public void setTimeIfSendAbsolut(long timeIfSendAbsolut) {
        this.timeIfSendAbsolut = timeIfSendAbsolut;
    }

    public Boolean getTimed() {
        return isTimed;
    }

    public void setTimed(Boolean timed) {
        isTimed = timed;
    }

    public long getTimeIfExpired() {
        return timeIfExpired;
    }

    public void setTimeIfExpired(long timeIfExpired) {
        this.timeIfExpired = timeIfExpired;
    }

    public long getTimeIfExpiredBonus() {
        return timeIfExpiredBonus;
    }

    public void setTimeIfExpiredBonus(long timeIfExpiredBonus) {
        this.timeIfExpiredBonus = timeIfExpiredBonus;
    }

    public List<CommandO> getCommandOList() {
        return commandOList;
    }

    public void setCommandOList(List<CommandO> commandOList) {
        this.commandOList = commandOList;
    }


    //Logic
    public void sended(){
        importance = timeIfSendAbsolut;
        if (isTimed) {
            timeNext = timeCurrent + timeDelta;
        }
    }

    public long getImportanceCurrent() {
        importanceCurrent = importance;
        if (isTimed) {
            if (timeNext < timeCurrent) {
                importanceCurrent += timeIfExpired;
                importanceCurrent += (timeCurrent - timeNext) * timeIfExpiredBonus;
            }
        }
        return importanceCurrent;
    }

}
