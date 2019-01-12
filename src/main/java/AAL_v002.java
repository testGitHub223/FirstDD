import java.util.ArrayList;
import java.util.List;

public class AAL_v002 {

    public AAL_v002(GrupNotTimed_v002 grupNotTimed_v002, GrupTimed_v002 grupTimed_v002) {
        this.grupNotTimed_v002 = grupNotTimed_v002;
        this.grupTimed_v002 = grupTimed_v002;
    }

    GrupNotTimed_v002 grupNotTimed_v002;
    GrupTimed_v002 grupTimed_v002;

    public GrupNotTimed_v002 getGrupNotTimed_v002() {
        return grupNotTimed_v002;
    }

    public GrupTimed_v002 getGrupTimed_v002() {
        return grupTimed_v002;
    }
}
