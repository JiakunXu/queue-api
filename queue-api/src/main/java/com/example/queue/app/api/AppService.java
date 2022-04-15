package com.example.queue.app.api;

import com.example.queue.app.api.bo.App;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface AppService {

    /**
     * 
     * @param name
     * @param app
     * @return
     */
    int countApp(String name, App app);

    /**
     *
     * @param name
     * @param app
     * @return
     */
    List<App> listApps(String name, App app);

    /**
     * 
     * @param id
     * @return
     */
    App getApp(String id);

    /**
     *
     * @param app
     * @param creator
     * @return
     */
    App insertApp(App app, String creator);

    /**
     *
     * @param id
     * @param app
     * @param modifier
     * @return
     */
    App updateApp(BigInteger id, App app, String modifier);

}
