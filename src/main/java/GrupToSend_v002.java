import java.util.ArrayList;
import java.util.List;

public class GrupToSend_v002 {

    public GrupToSend_v002(List<KeyToSend> keyToSendList, boolean isEmergency, boolean isGrupTimed, int priority, boolean isByTurns, long timeDeltaAll, long timeDeltaThis) {
        this.keyToSendList = keyToSendList;
        this.isEmergency = isEmergency;
        this.isGrupTimed = isGrupTimed;
        this.priority = priority;
        this.isByTurns = isByTurns;
        this.timeDeltaAll = timeDeltaAll;
        this.timeDeltaThis = timeDeltaThis;
    }

    private List<KeyToSend> keyToSendList = new ArrayList<>();

    private boolean isEmergency = false;
    private boolean isGrupTimed = false;
    private int priority = 10;

    private boolean isByTurns = false;
    private int currentKey = 0;

    private long timeDeltaAll;      // на сколько отключаются нажатия всех клавиш
    private long timeDeltaThis;     // на сколько отключается нажатие этой группы
    private long timeNext;          // время когда можно будет начать работать с данной группой


    public boolean isEmergency() {
        return isEmergency;
    }

    public boolean isGrupTimed() {
        return isGrupTimed;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isByTurns() {
        return isByTurns;
    }

    public int getCurrentKey() {
        return currentKey;
    }

    public long getTimeDeltaAll() {
        return timeDeltaAll;
    }

    public long getTimeDeltaThis() {
        return timeDeltaThis;
    }

    public long getTimeNext() {
        return timeNext;
    }

    public KeyToSend getKeyToSend(long time){
        System.out.println("d   " + currentKey);
        KeyToSend keyToSend = keyToSendList.get(currentKey);
        currentKey++;
        System.out.println("dd  " + currentKey);
        if (currentKey >= keyToSendList.size()){
            System.out.println("ddd " + currentKey);
            currentKey = 0;
        }
        timeNext = time + timeDeltaThis;
        return keyToSend;
    }
}
