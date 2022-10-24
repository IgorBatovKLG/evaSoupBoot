package ru.batov.evasoupboot.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Direction {
    private String url;
    private String remdId;
    private String regNum;
    private String regDate;
    private String Stage;
    private String createTime;
    private String refIssDate;
    private String refOrgName;
    private String refOrgOgrn;
    private String fio;
    private String birthDate;
    private String repKind;
    private String recDate;
}
