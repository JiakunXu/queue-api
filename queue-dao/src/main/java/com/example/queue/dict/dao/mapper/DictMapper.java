package com.example.queue.dict.dao.mapper;

import com.example.queue.dict.dao.dataobject.DictDO;
import com.example.queue.framework.mapper.BaseMapper;

/**
 * @author JiakunXu
 */
public interface DictMapper extends BaseMapper<DictDO> {

    /**
     *
     * @param dictDO
     * @return
     */
    int update0(DictDO dictDO);

    /**
     *
     * @param dictDO
     * @return
     */
    int update1(DictDO dictDO);

}
