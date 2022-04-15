package com.example.queue.version.api;

import com.example.queue.version.api.bo.VersionDetail;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface VersionDetailService {

    /**
     * 
     * @param versionId
     * @param versionDetail
     * @return
     */
    int countVersionDetail(String versionId, VersionDetail versionDetail);

    /**
     * 
     * @param versionId
     * @param versionDetail
     * @return
     */
    List<VersionDetail> listVersionDetails(String versionId, VersionDetail versionDetail);

    /**
     * 
     * @param versionId
     * @param version
     * @return
     */
    VersionDetail getVersionDetail(BigInteger versionId, String version);

    /**
     *
     * @param versionId
     * @param version
     * @param status
     * @param creator
     * @return
     */
    VersionDetail insertVersionDetail(BigInteger versionId, String version, String status,
                                      String creator);

    /**
     *
     * @param id
     * @param status
     * @param modifier
     * @return
     */
    VersionDetail updateVersionDetail(BigInteger id, String status, String modifier);

    /**
     *
     * @param id
     * @param modifier
     * @return
     */
    VersionDetail deleteVersionDetail(BigInteger id, String modifier);

}
