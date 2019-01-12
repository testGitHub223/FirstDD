import java.util.List;

public class GrupNotTimed_v002 {


    public GrupNotTimed_v002(GrupToSendNotTimed_v002 current) {
        //this.grup = grup;
        this.current = current;
    }

    //private List<GrupToSendNotTimed_v002> grup;
    private GrupToSendNotTimed_v002 current;



    //public List<GrupToSendNotTimed_v002> getGrup() {
    //    return grup;
    //}

    public GrupToSendNotTimed_v002 getCurrent() {
        GrupToSendNotTimed_v002 grup = current;
        //current.getNextGrup();
        sendNextGrup();
        return grup;
    }

    public void sendNextGrup(){
        this.current = this.current.getNextGrup();
    }


}
