package com.jpetstore.servlet.persistance;

import com.jpetstore.servlet.domain.Sequence;

public interface SequenceDAO {
    Sequence getSequence(Sequence sequence);
    void updateSequence(Sequence sequence);
}
