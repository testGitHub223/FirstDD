public class SetingsOfSendData{

    public SetingsOfSendData() {
    }

    public SetingsOfSendData(int delayTimeStartKeybord,
                             int delayTimeStartMouse,
                             int delayTimeKeybord, int delayTimeMouse
    ) {
        this.delayTimeStartKeybord = delayTimeStartKeybord;
        this.delayTimeStartMouse = delayTimeStartMouse;
        this.delayTimeKeybord = delayTimeKeybord;
        this.delayTimeMouse = delayTimeMouse;
    }

    static int delayTimeStartKeybord = 20;
    static int delayTimeStartMouse = 20;
    static int delayTimeKeybord = 10;
    static int delayTimeMouse = 10;


    public int getDelayTimeStartKeybord() {
        return delayTimeStartKeybord;
    }
    public void setDelayTimeStartKeybord(int delayTimeStartKeybord) {
        SetingsOfSendData.delayTimeStartKeybord = delayTimeStartKeybord;
    }


    public int getDelayTimeStartMouse() {
        return delayTimeStartMouse;
    }
    public void setDelayTimeStartMouse(int delayTimeStartMouse) {
        SetingsOfSendData.delayTimeStartMouse = delayTimeStartMouse;
    }


    public int getDelayTimeKeybord() {
        return delayTimeKeybord;
    }
    public void setDelayTimeKeybord(int delayTimeKeybord) {
        SetingsOfSendData.delayTimeKeybord = delayTimeKeybord;
    }


    public int getDelayTimeMouse() {
        return delayTimeMouse;
    }
    public void setDelayTimeMouse(int delayTimeMouse) {
        SetingsOfSendData.delayTimeMouse = delayTimeMouse;
    }

}