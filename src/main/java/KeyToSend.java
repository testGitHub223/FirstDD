import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class KeyToSend {

    public KeyToSend() {
    }

    public KeyToSend(long timeDeltaAll, long timeDeltaThis, int[] data) {
        this.timeDeltaAll = timeDeltaAll;
        this.timeDeltaThis = timeDeltaThis;
        this.data = data;
    }

    public KeyToSend(long timeDeltaAll, long timeDeltaThis, long timeNext, int[] data) {
        this.timeDeltaAll = timeDeltaAll;
        this.timeDeltaThis = timeDeltaThis;
        this.timeNext = timeNext;
        this.data = data;
    }

    // самое минимальное времяследующего запуска (на основании его мы узнаем, что общее время нужно уменьшить)
    //static  long minTimeOfAll = 0;

    long timeDeltaAll = 1;      //// на сколько отключаются нажатия всех клавиш
    long timeDeltaThis = 1;     //// на сколько отключается нажатие этой клавишей
    long timeNext = 0;          //// время когда можно будет начать работать с данной клавишей

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
    public int[] getData() {
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
