package com.example.queue.area.api;

import com.example.queue.area.api.bo.Area;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface AreaService {

    /**
     * 
     * @param code
     * @param name
     * @param area
     * @return
     */
    int countArea(String code, String name, Area area);

    /**
     * 
     * @param code
     * @param name
     * @param area
     * @return
     */
    List<Area> listAreas(String code, String name, Area area);

    /**
     * 
     * @param id
     * @return
     */
    Area getArea(String id);

    /**
     *
     * @param area
     * @param creator
     * @return
     */
    Area insertArea(Area area, String creator);

    /**
     *
     * @param id
     * @param area
     * @param modifier
     * @return
     */
    Area updateArea(BigInteger id, Area area, String modifier);

    /**
     * 
     * @param id
     * @param modifier
     * @return
     */
    Area deleteArea(BigInteger id, String modifier);

}
