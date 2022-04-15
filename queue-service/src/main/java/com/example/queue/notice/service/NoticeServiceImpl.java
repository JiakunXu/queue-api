package com.example.queue.notice.service;

import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.notice.api.NoticeService;
import com.example.queue.notice.api.bo.Notice;
import com.example.queue.notice.dao.dataobject.NoticeDO;
import com.example.queue.notice.dao.mapper.NoticeMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    private NoticeMapper        noticeMapper;

    @Override
    public int countNotice(String deptId, Notice notice) {
        if (notice == null) {
            return 0;
        }

        if (StringUtils.isNotBlank(deptId)) {
            notice.setDeptId(new BigInteger(deptId));
        }

        return count(BeanUtil.copy(notice, NoticeDO.class));
    }

    @Override
    public List<Notice> listNotices(String deptId, Notice notice) {
        if (notice == null) {
            return null;
        }

        if (StringUtils.isNotBlank(deptId)) {
            notice.setDeptId(new BigInteger(deptId));
        }

        return BeanUtil.copy(list(BeanUtil.copy(notice, NoticeDO.class)), Notice.class);
    }

    @Override
    public Notice getNotice(String deptId) {
        if (StringUtils.isBlank(deptId)) {
            return null;
        }

        NoticeDO noticeDO = new NoticeDO();
        noticeDO.setDeptId(new BigInteger(deptId));

        return BeanUtil.copy(get(noticeDO), Notice.class);
    }

    @Override
    public Notice insertNotice(BigInteger deptId, Notice notice, String creator) {
        if (deptId == null || notice == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        notice.setDeptId(deptId);

        NoticeDO noticeDO = BeanUtil.copy(notice, NoticeDO.class);
        noticeDO.setCreator(creator);

        try {
            noticeMapper.insert(noticeDO);
        } catch (Exception e) {
            logger.error(noticeDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        notice.setId(noticeDO.getId());

        return notice;
    }

    @Override
    public Notice updateNotice(BigInteger id, Notice notice, String modifier) {
        if (id == null || notice == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        notice.setId(id);

        NoticeDO noticeDO = BeanUtil.copy(notice, NoticeDO.class);
        noticeDO.setModifier(modifier);

        try {
            if (noticeMapper.update(noticeDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(noticeDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return notice;
    }

    @Override
    public Notice deleteNotice(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        NoticeDO noticeDO = new NoticeDO();
        noticeDO.setId(id);
        noticeDO.setModifier(modifier);

        try {
            noticeMapper.delete(noticeDO);
        } catch (Exception e) {
            logger.error(noticeDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(noticeDO, Notice.class);
    }

    private int count(NoticeDO noticeDO) {
        try {
            return noticeMapper.count(noticeDO);
        } catch (Exception e) {
            logger.error(noticeDO.toString(), e);
        }

        return 0;
    }

    private List<NoticeDO> list(NoticeDO noticeDO) {
        try {
            return noticeMapper.list(noticeDO);
        } catch (Exception e) {
            logger.error(noticeDO.toString(), e);
        }

        return null;
    }

    private NoticeDO get(NoticeDO noticeDO) {
        try {
            return noticeMapper.get(noticeDO);
        } catch (Exception e) {
            logger.error(noticeDO.toString(), e);
        }

        return null;
    }

}
