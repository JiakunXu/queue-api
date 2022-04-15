package com.example.queue.version.api;

import com.example.queue.version.api.bo.Version;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface VersionService {

    /**
     *
     * @param appId
     * @param version
     * @return
     */
    int countVersion(String appId, Version version);

    /**
     * 
     * @param appId
     * @param version
     * @return
     */
    List<Version> listVersions(String appId, Version version);

    /**
     * 
     * @param appId
     * @param client
     * @return
     */
    Version getVersion(String appId, String client);

    /**
     *
     * @param appId
     * @param client
     * @param url
     * @param creator
     * @return
     */
    Version insertVersion(BigInteger appId, String client, String url, String creator);

    /**
     *
     * @param id
     * @param url
     * @param modifier
     * @return
     */
    Version updateVersion(BigInteger id, String url, String modifier);

}
