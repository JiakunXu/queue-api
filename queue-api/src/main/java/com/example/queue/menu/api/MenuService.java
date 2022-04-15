package com.example.queue.menu.api;

import com.example.queue.menu.api.bo.Menu;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface MenuService {

    /**
     * 
     * @param pid
     * @return
     */
    List<Menu> listMenus(String pid);

    /**
     *
     * @param pid
     * @param menu
     * @param creator
     * @return
     */
    Menu insertMenu(BigInteger pid, Menu menu, String creator);

    /**
     *
     * @param id
     * @param menu
     * @param modifier
     * @return
     */
    Menu updateMenu(BigInteger id, Menu menu, String modifier);

    /**
     * 
     * @param id
     * @param modifier
     * @return
     */
    Menu deleteMenu(BigInteger id, String modifier);

}
