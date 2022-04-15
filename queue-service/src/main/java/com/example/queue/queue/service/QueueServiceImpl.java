package com.example.queue.queue.service;

import com.example.queue.dept.api.DeptService;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.queue.api.QueueService;
import com.example.queue.queue.api.bo.Queue;
import com.example.queue.queue.dao.dataobject.QueueDO;
import com.example.queue.queue.dao.mapper.QueueMapper;
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
public class QueueServiceImpl implements QueueService {

    private static final Logger logger = LoggerFactory.getLogger(QueueServiceImpl.class);

    @Autowired
    private DeptService         deptService;

    @Autowired
    private QueueMapper         queueMapper;

    @Override
    public int countQueue(String deptId, Queue queue) {
        if (queue == null) {
            return 0;
        }

        if (StringUtils.isNotBlank(deptId)) {
            queue.setDeptId(new BigInteger(deptId));
        }

        return count(BeanUtil.copy(queue, QueueDO.class));
    }

    @Override
    public List<Queue> listQueues(String deptId, Queue queue) {
        if (queue == null) {
            return null;
        }

        if (StringUtils.isNotBlank(deptId)) {
            queue.setDeptId(new BigInteger(deptId));
        }

        List<Queue> list = BeanUtil.copy(list(BeanUtil.copy(queue, QueueDO.class)), Queue.class);

        if (list == null || list.size() == 0) {
            return null;
        }

        for (Queue q : list) {
            q.setDept(deptService.getDept(q.getDeptId()));
        }

        return list;
    }

    @Override
    public Queue getQueue(BigInteger deptId) {
        if (deptId == null) {
            return null;
        }

        QueueDO queueDO = new QueueDO();
        queueDO.setDeptId(deptId);
        queueDO.setStatus(null);

        return BeanUtil.copy(get(queueDO), Queue.class);
    }

    @Override
    public Queue insertQueue(BigInteger deptId, Queue queue, String creator) {
        if (deptId == null || queue == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        queue.setDeptId(deptId);

        QueueDO queueDO = BeanUtil.copy(queue, QueueDO.class);
        queueDO.setCreator(creator);

        try {
            queueMapper.insert(queueDO);
        } catch (Exception e) {
            logger.error(queueDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        queue.setId(queueDO.getId());

        return queue;
    }

    @Override
    public Queue updateQueue(BigInteger id, Queue queue, String modifier) {
        if (id == null || queue == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        queue.setId(id);

        QueueDO queueDO = BeanUtil.copy(queue, QueueDO.class);
        queueDO.setModifier(modifier);

        try {
            if (queueMapper.update(queueDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(queueDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return queue;
    }

    private int count(QueueDO queueDO) {
        try {
            return queueMapper.count(queueDO);
        } catch (Exception e) {
            logger.error(queueDO.toString(), e);
        }

        return 0;
    }

    private List<QueueDO> list(QueueDO queueDO) {
        try {
            return queueMapper.list(queueDO);
        } catch (Exception e) {
            logger.error(queueDO.toString(), e);
        }

        return null;
    }

    private QueueDO get(QueueDO queueDO) {
        try {
            return queueMapper.get(queueDO);
        } catch (Exception e) {
            logger.error(queueDO.toString(), e);
        }

        return null;
    }

}
