package selectionneur;

import inputs.CoupleAvecScore;

import java.util.List;

public interface Selectionneur<O> {
    O selectionner(List<CoupleAvecScore> couplesAvecScores);
}
