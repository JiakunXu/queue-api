package com.example.queue.config.service;

import com.example.queue.config.api.VoiceService;
import com.example.queue.config.api.bo.Voice;
import com.example.queue.config.dao.dataobject.VoiceDO;
import com.example.queue.config.dao.mapper.VoiceMapper;
import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
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
public class VoiceServiceImpl implements VoiceService {

    private static final Logger logger = LoggerFactory.getLogger(VoiceServiceImpl.class);

    @Autowired
    private VoiceMapper         voiceMapper;

    @Override
    public int countVoice(String code, String name, Voice voice) {
        if (voice == null) {
            return 0;
        }

        voice.setCode(code);
        voice.setName(name);

        return count(BeanUtil.copy(voice, VoiceDO.class));
    }

    @Override
    public List<Voice> listVoices(String code, String name, Voice voice) {
        if (voice == null) {
            return null;
        }

        voice.setCode(code);
        voice.setName(name);

        return BeanUtil.copy(list(BeanUtil.copy(voice, VoiceDO.class)), Voice.class);
    }

    @Override
    public Voice getVoice(BigInteger id) {
        if (id == null) {
            return null;
        }

        VoiceDO voiceDO = new VoiceDO();
        voiceDO.setId(id);

        return BeanUtil.copy(get(voiceDO), Voice.class);
    }

    @Override
    public Voice insertVoice(Voice voice, String creator) {
        if (voice == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        VoiceDO voiceDO = BeanUtil.copy(voice, VoiceDO.class);
        voiceDO.setCreator(creator);

        try {
            voiceMapper.insert(voiceDO);
        } catch (Exception e) {
            logger.error(voiceDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        voice.setId(voiceDO.getId());

        return voice;
    }

    @Override
    public Voice updateVoice(BigInteger id, Voice voice, String modifier) {
        if (id == null || voice == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        voice.setId(id);

        VoiceDO voiceDO = BeanUtil.copy(voice, VoiceDO.class);
        voiceDO.setModifier(modifier);

        try {
            if (voiceMapper.update(voiceDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(voiceDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return voice;
    }

    @Override
    public Voice deleteVoice(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        VoiceDO voiceDO = new VoiceDO();
        voiceDO.setId(id);
        voiceDO.setModifier(modifier);

        try {
            voiceMapper.delete(voiceDO);
        } catch (Exception e) {
            logger.error(voiceDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(voiceDO, Voice.class);
    }

    private int count(VoiceDO voiceDO) {
        try {
            return voiceMapper.count(voiceDO);
        } catch (Exception e) {
            logger.error(voiceDO.toString(), e);
        }

        return 0;
    }

    private List<VoiceDO> list(VoiceDO voiceDO) {
        try {
            return voiceMapper.list(voiceDO);
        } catch (Exception e) {
            logger.error(voiceDO.toString(), e);
        }

        return null;
    }

    private VoiceDO get(VoiceDO voiceDO) {
        try {
            return voiceMapper.get(voiceDO);
        } catch (Exception e) {
            logger.error(voiceDO.toString(), e);
        }

        return null;
    }

}
