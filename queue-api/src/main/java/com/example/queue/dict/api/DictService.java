package com.example.queue.dict.api;

import com.example.queue.dict.api.bo.Dict;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface DictService {

    /**
     *
     * @param dictTypeId
     * @return
     */
    List<Dict> listDicts(BigInteger dictTypeId);

    /**
     *
     * @param dictTypeValue
     * @return
     */
    List<Dict> listDicts(String dictTypeValue);

    /**
     *
     * @param dictTypeValue
     * @param value
     * @return
     */
    Dict getDict(String dictTypeValue, String value);

    /**
     *
     * @param dictTypeId
     * @param dict
     * @param creator
     * @return
     */
    Dict insertDict(BigInteger dictTypeId, Dict dict, String creator);

    /**
     *
     * @param id
     * @param dict
     * @param modifier
     * @return
     */
    Dict updateDict(BigInteger id, Dict dict, String modifier);

    /**
     * 
     * @param dictTypeId
     * @param dictTypeValue
     * @param modifier
     * @return
     */
    Dict updateDict(BigInteger dictTypeId, String dictTypeValue, String modifier);

    /**
     * 
     * @param dictTypeId
     * @param id
     * @param modifier
     * @return
     */
    Dict deleteDict(BigInteger dictTypeId, BigInteger id, String modifier);

}
