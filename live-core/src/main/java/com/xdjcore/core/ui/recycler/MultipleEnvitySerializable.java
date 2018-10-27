package com.xdjcore.core.ui.recycler;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jx on 2018/5/8.
 */

public class MultipleEnvitySerializable implements Serializable {
    private static final long serialVersionUID = 1L;
    private MultipleItemEntity itemEntity;
    private ArrayList<MultipleItemEntity> ENTITIES;

    public MultipleEnvitySerializable(MultipleItemEntity itemEntity, ArrayList<MultipleItemEntity> ENTITIES) {
        this.itemEntity = itemEntity;
        this.ENTITIES = ENTITIES;
    }

    public MultipleItemEntity getItemEntity() {
        return itemEntity;
    }

    public ArrayList<MultipleItemEntity> getENTITIES() {
        return ENTITIES;
    }
}
