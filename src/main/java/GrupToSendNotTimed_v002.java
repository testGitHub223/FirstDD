import java.util.ArrayList;
import java.util.List;

public class GrupToSendNotTimed_v002 {

    public GrupToSendNotTimed_v002(List<KeyToSend_v002> keyToSendList_v002, int priority) {
        this.keyToSendList_v002 = keyToSendList_v002;
        this.priority = priority;
    }

    public void initialize(){
        int size = keyToSendList_v002.size();
        for (int i = 0; i < size; i++) {
            nextGrups.add(null);
        }
    }

    private List<KeyToSend_v002> keyToSendList_v002;

    private List<GrupToSendNotTimed_v002> nextGrups = new ArrayList<>();


    private int priority = 100;
    private int currentKey = 0;



    public int getPriority() {
        return priority;
    }

    public int getCurrentKey() {
        //getTimeNext();
        return currentKey;
    }

    public int getSize() {
        return keyToSendList_v002.size();
    }

    public GrupToSendNotTimed_v002 getNextGrup() {
        ////
        GrupToSendNotTimed_v002 nextGru = nextGrups.get(currentKey);
        if (nextGru != null) {
            return nextGru;
        }
        return  this;
    }


    public KeyToSend_v002 getKeyToSend(long time) {

        KeyToSend_v002 keyToSend_v002 = keyToSendList_v002.get(currentKey);
        currentKey++;
        if (currentKey >= keyToSendList_v002.size()) {
            currentKey = 0;
        }
        ////System.out.println("d   " + currentKey);
        return keyToSend_v002;
    }


    /*
    // находим в какое время нажимаем следующий раз и попутно какую клавишу
    public long getTimeNext() {
        long time = Long.MAX_VALUE;
        // находим ближайшую нажимаемую клавишу
        for (int i = 0; i < keyToSendList_v002.size(); i++) {
            long tempTime = keyToSendList_v002.get(i).getTimeNext();
            if (tempTime <= time) {
                time = tempTime;
                currentKey = i;
            }
        }
        return time;
    }
*/

    public boolean addNextGrup(int numberOfKey, GrupToSendNotTimed_v002 nextGrup) {
        boolean isOk = false;
        if (numberOfKey >= keyToSendList_v002.size()) {
            numberOfKey =  keyToSendList_v002.size() - 1;
            isOk = true;
        }
        nextGrups.remove(numberOfKey);
        nextGrups.add(numberOfKey, nextGrup);
        return isOk;
    }

}
