import java.util.List;

public class GrupToSendTimed_v002 {

    public GrupToSendTimed_v002(List<KeyToSend_v002> keyToSendList_v002, int priority) {
        this.keyToSendList_v002 = keyToSendList_v002;
        this.priority = priority;

    }

    private List<KeyToSend_v002> keyToSendList_v002;

    private int priority = 100;
    private int currentKey = 0;


    public int getPriority() {
        return priority;
    }
    public int getCurrentKey() {
        getTimeNext();
        return currentKey;
    }


    public KeyToSend_v002 getKeyToSend(long time){
        if (getTimeNext() <= time) {
            KeyToSend_v002 keyToSend_v002 = keyToSendList_v002.get(currentKey);
            ////System.out.println("d   " + currentKey);
            return keyToSend_v002;
        }
        // если нажимать не нужно, возвращаем null
        return null;
    }

    // находим в какое время нажимаем следующий раз и попутно какую клавишу
    public long getTimeNext(){
        long time = Long.MAX_VALUE;
        // находим ближайшую нажимаемую клавишу
        for (int i = 0; i < keyToSendList_v002.size(); i++){
            long tempTime = keyToSendList_v002.get(i).getTimeNext();
            //
            if (tempTime < time){
                time = tempTime;
                currentKey = i;
            } else {
                if (tempTime == time && keyToSendList_v002.get(currentKey).getPriority() < keyToSendList_v002.get(i).getPriority()){
                    time = tempTime;
                    currentKey = i;
                }
            }
        }
        return time;
    }
}
