package com.firelord.db.mongo.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class CellMo {
    //#region Fields

    @Setter
    @Getter
    private String key;

    @Setter
    @Getter
    private String value;

    //#endregion
}
