package goldrush.vinnsla;

/**
 * Observer viðmót sem fær tilkynningu þegar stig breytast í leiknum.
 */
public interface StigaListener {
    /**
     * Kallað á þegar stig breytast.
     *
     * @param stig núverandi stig
     * @param haestuStig hæstu stig sem náðst hafa
     */
    void stigBreytt(int stig, int haestuStig);
}
