package com.example.queue.media.service;

import com.alibaba.fastjson.JSON;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.media.api.MediaService;
import com.example.queue.media.api.bo.Media;
import com.example.queue.media.dao.dataobject.MediaDO;
import com.example.queue.media.dao.mapper.MediaMapper;
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
public class MediaServiceImpl implements MediaService {

    private static final Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);

    @Autowired
    private MediaMapper         mediaMapper;

    @Override
    public int countMedia(String code, String name, Media media) {
        if (media == null) {
            return 0;
        }

        media.setCode(code);
        media.setName(name);

        return count(BeanUtil.copy(media, MediaDO.class));
    }

    @Override
    public List<Media> listMedias(String code, String name, Media media) {
        if (media == null) {
            return null;
        }

        media.setCode(code);
        media.setName(name);

        return BeanUtil.copy(list(BeanUtil.copy(media, MediaDO.class)), Media.class);
    }

    @Override
    public Media getMedia(BigInteger id) {
        if (id == null) {
            return null;
        }

        MediaDO mediaDO = new MediaDO();
        mediaDO.setId(id);

        return BeanUtil.copy(get(mediaDO), Media.class);
    }

    @Override
    public Media insertMedia(Media media, String creator) {
        if (media == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        MediaDO mediaDO = BeanUtil.copy(media, MediaDO.class);
        mediaDO.setCreator(creator);

        try {
            mediaMapper.insert(mediaDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(mediaDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        media.setId(mediaDO.getId());

        return media;
    }

    @Override
    public Media updateMedia(BigInteger id, Media media, String modifier) {
        if (id == null || media == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        media.setId(id);

        MediaDO mediaDO = BeanUtil.copy(media, MediaDO.class);
        mediaDO.setModifier(modifier);

        try {
            if (mediaMapper.update(mediaDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(JSON.toJSONString(mediaDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return media;
    }

    @Override
    public Media deleteMedia(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        MediaDO mediaDO = new MediaDO();
        mediaDO.setId(id);
        mediaDO.setModifier(modifier);

        try {
            mediaMapper.delete(mediaDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(mediaDO), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(mediaDO, Media.class);
    }

    private int count(MediaDO mediaDO) {
        try {
            return mediaMapper.count(mediaDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(mediaDO), e);
        }

        return 0;
    }

    private List<MediaDO> list(MediaDO mediaDO) {
        try {
            return mediaMapper.list(mediaDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(mediaDO), e);
        }

        return null;
    }

    private MediaDO get(MediaDO mediaDO) {
        try {
            return mediaMapper.get(mediaDO);
        } catch (Exception e) {
            logger.error(JSON.toJSONString(mediaDO), e);
        }

        return null;
    }

}
