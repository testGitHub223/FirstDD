public class GrupTimed_v002 {


    public GrupTimed_v002(GrupToSendTimed_v002 current) {
        //this.grup = grup;
        this.current = current;
    }

    //private List<GrupToSendNotTimed_v002> grup;
    private GrupToSendTimed_v002 current;



    //public List<GrupToSendNotTimed_v002> getGrup() {
    //    return grup;
    //}

    public GrupToSendTimed_v002 getCurrent() {
        GrupToSendTimed_v002 grup = current;
        //current.getNextGrup();
        return current;
    }
}
