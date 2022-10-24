package ru.batov.evasoupboot.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class History {
    String Date;
    String Action;
    String Author;
    String Department;
    String Comment;
}
