package com.example.queue.config.api;

import com.example.queue.config.api.bo.Style;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface StyleService {

    /**
     *
     * @param code
     * @param name
     * @param style
     * @return
     */
    int countStyle(String code, String name, Style style);

    /**
     * 
     * @param code
     * @param name
     * @param style
     * @return
     */
    List<Style> listStyles(String code, String name, Style style);

    /**
     * 
     * @param id
     * @return
     */
    Style getStyle(BigInteger id);

    /**
     *
     * @param style
     * @param creator
     * @return
     */
    Style insertStyle(Style style, String creator);

    /**
     *
     * @param id
     * @param style
     * @param modifier
     * @return
     */
    Style updateStyle(BigInteger id, Style style, String modifier);

    /**
     *
     * @param id
     * @param modifier
     * @return
     */
    Style deleteStyle(BigInteger id, String modifier);

}
