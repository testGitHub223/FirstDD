public interface Alg {

    int[] getKeys();

    boolean nextTick(long microSeconds);

    void setSend(Boolean isSend);
}
