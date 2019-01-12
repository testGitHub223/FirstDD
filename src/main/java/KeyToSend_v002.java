import java.util.Calendar;
import java.util.GregorianCalendar;

public class KeyToSend_v002 {

    public KeyToSend_v002() {
    }

    public KeyToSend_v002(long timeDeltaAll, long timeDeltaThis, int[] data) {
        this.timeDeltaAll = timeDeltaAll;
        this.timeDeltaThis = timeDeltaThis;
        this.data = data;
    }

    public KeyToSend_v002(long timeDeltaAll, long timeDeltaThis, long timeNext, int[] data) {
        this.timeDeltaAll = timeDeltaAll;
        this.timeDeltaThis = timeDeltaThis;
        this.timeNext = timeNext;
        this.data = data;
    }

    // самое минимальное времяследующего запуска (на основании его мы узнаем, что общее время нужно уменьшить)
    //static  long minTimeOfAll = 0;

    long timeDeltaAll = 50;      //// на сколько отключаются нажатия всех клавиш по умолчанию
    long timeDeltaThis = 100;     //// на сколько отключается нажатие этой клавишей по умолчанию
    long timeNext = 0;          //// время когда можно будет начать работать с данной клавишей

    long priority = 100;

    int [] data = new int[] {0, 0, 0, 0, 0};




    public long getTimeDeltaAll() {
        return timeDeltaAll;
    }
    public long getTimeDeltaThis() {
        return timeDeltaThis;
    }
    public long getTimeNext() {
        return timeNext;
    }

    public long getPriority() {
        return priority;
    }

    public int[] getData(long time) {
        // стаим время когда можно будет нажать эту клавишу в следующий раз
        this.timeNext = time + timeDeltaThis;
        return data;
    }


    public void tt (){

        Calendar gg = new GregorianCalendar();
        System.out.println(gg.getTimeInMillis());
        long rrrrr = gg.getTimeInMillis();
        rrrrr -=1000;
        gg.setTimeInMillis(rrrrr);
        System.out.println(gg.getTimeInMillis());
        System.out.println(gg.getTimeInMillis());
        System.out.println(Long.MAX_VALUE);

    }


}
