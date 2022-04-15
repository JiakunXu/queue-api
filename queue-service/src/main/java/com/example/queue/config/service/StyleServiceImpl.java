package com.example.queue.config.service;

import com.example.queue.config.api.StyleService;
import com.example.queue.config.api.bo.Style;
import com.example.queue.config.dao.dataobject.StyleDO;
import com.example.queue.config.dao.mapper.StyleMapper;
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
public class StyleServiceImpl implements StyleService {

    private static final Logger logger = LoggerFactory.getLogger(StyleServiceImpl.class);

    @Autowired
    private StyleMapper         styleMapper;

    @Override
    public int countStyle(String code, String name, Style style) {
        if (style == null) {
            return 0;
        }

        style.setCode(code);
        style.setName(name);

        return count(BeanUtil.copy(style, StyleDO.class));
    }

    @Override
    public List<Style> listStyles(String code, String name, Style style) {
        if (style == null) {
            return null;
        }

        style.setCode(code);
        style.setName(name);

        return BeanUtil.copy(list(BeanUtil.copy(style, StyleDO.class)), Style.class);
    }

    @Override
    public Style getStyle(BigInteger id) {
        if (id == null) {
            return null;
        }

        StyleDO styleDO = new StyleDO();
        styleDO.setId(id);

        return BeanUtil.copy(get(styleDO), Style.class);
    }

    @Override
    public Style insertStyle(Style style, String creator) {
        if (style == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        StyleDO styleDO = BeanUtil.copy(style, StyleDO.class);
        styleDO.setCreator(creator);

        try {
            styleMapper.insert(styleDO);
        } catch (Exception e) {
            logger.error(styleDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        style.setId(styleDO.getId());

        return style;
    }

    @Override
    public Style updateStyle(BigInteger id, Style style, String modifier) {
        if (id == null || style == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        style.setId(id);

        StyleDO styleDO = BeanUtil.copy(style, StyleDO.class);
        styleDO.setModifier(modifier);

        try {
            if (styleMapper.update(styleDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(styleDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return style;
    }

    @Override
    public Style deleteStyle(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        StyleDO styleDO = new StyleDO();
        styleDO.setId(id);
        styleDO.setModifier(modifier);

        try {
            styleMapper.delete(styleDO);
        } catch (Exception e) {
            logger.error(styleDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return BeanUtil.copy(styleDO, Style.class);
    }

    private int count(StyleDO styleDO) {
        try {
            return styleMapper.count(styleDO);
        } catch (Exception e) {
            logger.error(styleDO.toString(), e);
        }

        return 0;
    }

    private List<StyleDO> list(StyleDO styleDO) {
        try {
            return styleMapper.list(styleDO);
        } catch (Exception e) {
            logger.error(styleDO.toString(), e);
        }

        return null;
    }

    private StyleDO get(StyleDO styleDO) {
        try {
            return styleMapper.get(styleDO);
        } catch (Exception e) {
            logger.error(styleDO.toString(), e);
        }

        return null;
    }

}
