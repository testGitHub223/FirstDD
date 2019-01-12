import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Algoritm {


    public Algoritm(List<GrupToSend_v002> grupToSendList) {
        this.grupToSendList = grupToSendList;
    }

    private List<GrupToSend_v002> grupToSendList = new ArrayList<>();

    private long nextTime = 0;



    public KeyToSend getKey() {
        Calendar gg = new GregorianCalendar();
        long time = gg.getTimeInMillis();

        if (nextTime < time) {

            int size = grupToSendList.size();


            List<GrupToSend_v002> grupsByTime = new ArrayList<>();
            List<GrupToSend_v002> grupsByTurns = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                // если время нажатия меньше, чем текущее время, значит группу можно нажимать
                if (grupToSendList.get(i).getTimeNext() < time) {
                    // проверяем грппа работает по времени (больше приоретет)
                    if (!grupToSendList.get(i).isGrupTimed()) {
                        grupsByTime.add(grupToSendList.get(i));
                    } else {
                        grupsByTurns.add(grupToSendList.get(i));
                    }

                }
            }
            int grupNumber = 0;
            int startPriority = 0;
            if (grupsByTime.size() > 0) {
                for (int i = 0; i < grupsByTime.size(); i++) {
                    if (grupsByTime.get(i).getPriority() > startPriority) {
                        startPriority = grupsByTime.get(i).getPriority();
                        grupNumber = i;
                    }
                }
                if (startPriority > 0) {
                    return grupsByTime.get(grupNumber).getKeyToSend(time);
                }
            } else {
                if (grupsByTurns.size() > 0) {
                    for (int i = 0; i < grupsByTurns.size(); i++) {
                        if (grupsByTurns.get(i).getPriority() > startPriority) {
                            startPriority = grupsByTurns.get(i).getPriority();
                            grupNumber = i;
                        }
                    }
                    if (startPriority > 0) {
                        return grupsByTurns.get(grupNumber).getKeyToSend(time);
                    }
                }
            }
        }
        return new KeyToSend();
    }
}
