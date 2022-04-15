package com.example.queue.version.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.version.api.VersionDetailService;
import com.example.queue.version.api.bo.VersionDetail;
import com.example.queue.version.dao.dataobject.VersionDetailDO;
import com.example.queue.version.dao.mapper.VersionDetailMapper;
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
public class VersionDetailServiceImpl implements VersionDetailService {

    private static final Logger logger = LoggerFactory.getLogger(VersionDetailServiceImpl.class);

    @Autowired
    private VersionDetailMapper versionDetailMapper;

    @Override
    public int countVersionDetail(String versionId, VersionDetail versionDetail) {
        if (StringUtils.isBlank(versionId) || versionDetail == null) {
            return 0;
        }

        versionDetail.setVersionId(new BigInteger(versionId));

        return count(BeanUtil.copy(versionDetail, VersionDetailDO.class));
    }

    @Override
    public List<VersionDetail> listVersionDetails(String versionId, VersionDetail versionDetail) {
        if (StringUtils.isBlank(versionId) || versionDetail == null) {
            return null;
        }

        versionDetail.setVersionId(new BigInteger(versionId));

        return BeanUtil.copy(list(BeanUtil.copy(versionDetail, VersionDetailDO.class)),
            VersionDetail.class);
    }

    @Override
    public VersionDetail getVersionDetail(BigInteger versionId, String version) {
        if (versionId == null || StringUtils.isBlank(version)) {
            return null;
        }

        VersionDetailDO versionDetailDO = new VersionDetailDO();
        versionDetailDO.setVersionId(versionId);
        versionDetailDO.setVersion(version);

        return BeanUtil.copy(get(versionDetailDO), VersionDetail.class);
    }

    @Override
    public VersionDetail insertVersionDetail(BigInteger versionId, String version, String status,
                                             String creator) {
        if (versionId == null || StringUtils.isBlank(version) || StringUtils.isBlank(status)
            || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        VersionDetailDO versionDetailDO = new VersionDetailDO();
        versionDetailDO.setVersionId(versionId);
        versionDetailDO.setVersion(version);
        versionDetailDO.setStatus(status);
        versionDetailDO.setCreator(creator);

        try {
            versionDetailMapper.insert(versionDetailDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(versionDetailDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        return BeanUtil.copy(versionDetailDO, VersionDetail.class);
    }

    @Override
    public VersionDetail updateVersionDetail(BigInteger id, String status, String modifier) {
        if (id == null || StringUtils.isBlank(status) || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        VersionDetailDO versionDetailDO = new VersionDetailDO();
        versionDetailDO.setId(id);
        versionDetailDO.setStatus(status);
        versionDetailDO.setModifier(modifier);

        try {
            if (versionDetailMapper.update(versionDetailDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(JSON.toJSONString(versionDetailDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(versionDetailDO, VersionDetail.class);
    }

    @Override
    public VersionDetail deleteVersionDetail(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        VersionDetailDO versionDetailDO = new VersionDetailDO();
        versionDetailDO.setId(id);
        versionDetailDO.setModifier(modifier);

        try {
            versionDetailMapper.delete(versionDetailDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(versionDetailDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(versionDetailDO, VersionDetail.class);
    }

    private int count(VersionDetailDO versionDetailDO) {
        try {
            return versionDetailMapper.count(versionDetailDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(versionDetailDO), e);
        }

        return 0;
    }

    private List<VersionDetailDO> list(VersionDetailDO versionDetailDO) {
        try {
            return versionDetailMapper.list(versionDetailDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(versionDetailDO), e);
        }

        return null;
    }

    private VersionDetailDO get(VersionDetailDO versionDetailDO) {
        try {
            return versionDetailMapper.get(versionDetailDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(versionDetailDO), e);
        }

        return null;
    }

}
