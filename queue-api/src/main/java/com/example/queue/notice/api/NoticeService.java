package com.example.queue.notice.api;

import com.example.queue.notice.api.bo.Notice;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface NoticeService {

    /**
     *
     * @param deptId
     * @param notice
     * @return
     */
    int countNotice(String deptId, Notice notice);

    /**
     *
     * @param deptId
     * @param notice
     * @return
     */
    List<Notice> listNotices(String deptId, Notice notice);

    /**
     *
     * @param deptId
     * @return
     */
    Notice getNotice(String deptId);

    /**
     *
     * @param notice
     * @param creator
     * @return
     */
    Notice insertNotice(BigInteger deptId, Notice notice, String creator);

    /**
     *
     * @param id
     * @param notice
     * @param modifier
     * @return
     */
    Notice updateNotice(BigInteger id, Notice notice, String modifier);

    /**
     * 
     * @param id
     * @param modifier
     * @return
     */
    Notice deleteNotice(BigInteger id, String modifier);

}
