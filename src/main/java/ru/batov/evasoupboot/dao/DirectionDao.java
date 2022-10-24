package ru.batov.evasoupboot.dao;

import ru.batov.evasoupboot.domain.Direction;

public interface DirectionDao {
    void insertDirection(Direction direction);

    int getCountDirectionByUrl(String url);

    boolean getStatusDirectionByUrl(String url);

    void updateDirection(Direction direction);

    Direction getDirectionByUrl(String url);
}
