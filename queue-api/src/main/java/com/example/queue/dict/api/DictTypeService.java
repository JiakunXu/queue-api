package com.example.queue.dict.api;

import com.example.queue.dict.api.bo.DictType;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface DictTypeService {

    /**
     *
     * @param name
     * @param value
     * @param dictType
     * @return
     */
    int countDictType(String name, String value, DictType dictType);

    /**
     *
     * @param name
     * @param value
     * @param dictType
     * @return
     */
    List<DictType> listDictTypes(String name, String value, DictType dictType);

    /**
     * 
     * @param id
     * @return
     */
    DictType getDictType(BigInteger id);

    /**
     *
     * @param value
     * @return
     */
    DictType getDictType(String value);

    /**
     *
     * @param dictType
     * @param creator
     * @return
     */
    DictType insertDictType(DictType dictType, String creator);

    /**
     *
     * @param id
     * @param dictType
     * @param modifier
     * @return
     */
    DictType updateDictType(BigInteger id, DictType dictType, String modifier);

    /**
     * 
     * @param id
     * @param modifier
     * @return
     */
    DictType deleteDictType(BigInteger id, String modifier);

}
