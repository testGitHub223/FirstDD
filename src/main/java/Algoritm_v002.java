import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Algoritm_v002 {


    public Algoritm_v002(AAL_v002 aal_v002) {
        this.grupNotTimed_v002 = aal_v002.getGrupNotTimed_v002();
        this.grupTimed_v002 = aal_v002.getGrupTimed_v002();
    }

    GrupNotTimed_v002 grupNotTimed_v002;
    GrupTimed_v002 grupTimed_v002;

    private long nextTimeAll = 0;



    public KeyToSend_v002 getKey() {
        long time = getCurrentTime();

        if (nextTimeAll <= time){
            // проверяем настало ли время нажимать клавиши по времени
            if (grupTimed_v002.getCurrent().getTimeNext() < time){
                KeyToSend_v002 key = grupTimed_v002.getCurrent().getKeyToSend(time);
                if (key != null){
                    // вычисляем время, в течении которого ничего недбзя нажимать
                    nextTimeAll = time + key.timeDeltaAll;
                    return key;
                }
            }else {
                KeyToSend_v002 key = grupNotTimed_v002.getCurrent().getKeyToSend(time);
                if (key != null){
                    // вычисляем время, в течении которого ничего недбзя нажимать
                    nextTimeAll =  time + key.timeDeltaAll;
                    return key;
                }
            }
        }else {
            try {
                long tt = nextTimeAll - time + 1;
                Thread.sleep(tt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    public long getCurrentTime(){
        Calendar gg = new GregorianCalendar();
        return gg.getTimeInMillis();
    }


    public long getNextTimeAll() {
        return nextTimeAll;
    }
}
