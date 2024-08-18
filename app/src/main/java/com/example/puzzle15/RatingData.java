package com.example.puzzle15;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public class RatingData {
    public long time, date, move;

    public RatingData(long time, long date, long move) {
        this.time = time;
        this.date = date;
        this.move = move;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, date, move);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof RatingData)) return false;
        if (obj == this) return true;
        RatingData temp = (RatingData) obj;
        return temp.move == this.move && temp.time == this.time && temp.date == this.date;
    }

    @NonNull
    @Override
    public String toString() {
        return time + "#" + date + "#" + move;
    }
}
