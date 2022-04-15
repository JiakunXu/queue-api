package com.example.queue.area.service;

import com.example.queue.area.api.AreaService;
import com.example.queue.area.api.bo.Area;
import com.example.queue.area.dao.dataobject.AreaDO;
import com.example.queue.area.dao.mapper.AreaMapper;
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
public class AreaServiceImpl implements AreaService {

    private static final Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Autowired
    private AreaMapper          areaMapper;

    @Override
    public int countArea(String code, String name, Area area) {
        if (area == null) {
            return 0;
        }

        area.setCode(code);
        area.setName(name);

        return count(BeanUtil.copy(area, AreaDO.class));
    }

    @Override
    public List<Area> listAreas(String code, String name, Area area) {
        if (area == null) {
            return null;
        }

        area.setCode(code);
        area.setName(name);

        return BeanUtil.copy(list(BeanUtil.copy(area, AreaDO.class)), Area.class);
    }

    @Override
    public Area getArea(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        AreaDO areaDO = new AreaDO();
        areaDO.setId(new BigInteger(id));

        return BeanUtil.copy(get(areaDO), Area.class);
    }

    @Override
    public Area insertArea(Area area, String creator) {
        if (area == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        AreaDO areaDO = BeanUtil.copy(area, AreaDO.class);
        areaDO.setCreator(creator);

        try {
            areaMapper.insert(areaDO);
        } catch (Exception e) {
            logger.error(areaDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        area.setId(areaDO.getId());

        return area;
    }

    @Override
    public Area updateArea(BigInteger id, Area area, String modifier) {
        if (id == null || area == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        area.setId(id);

        AreaDO areaDO = BeanUtil.copy(area, AreaDO.class);
        areaDO.setModifier(modifier);

        try {
            if (areaMapper.update(areaDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(areaDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return area;
    }

    @Override
    public Area deleteArea(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        AreaDO areaDO = new AreaDO();
        areaDO.setId(id);
        areaDO.setModifier(modifier);

        try {
            areaMapper.delete(areaDO);
        } catch (Exception e) {
            logger.error(areaDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        // TODO

        return BeanUtil.copy(areaDO, Area.class);
    }

    private int count(AreaDO areaDO) {
        try {
            return areaMapper.count(areaDO);
        } catch (Exception e) {
            logger.error(areaDO.toString(), e);
        }

        return 0;
    }

    private List<AreaDO> list(AreaDO areaDO) {
        try {
            return areaMapper.list(areaDO);
        } catch (Exception e) {
            logger.error(areaDO.toString(), e);
        }

        return null;
    }

    private AreaDO get(AreaDO areaDO) {
        try {
            return areaMapper.get(areaDO);
        } catch (Exception e) {
            logger.error(areaDO.toString(), e);
        }

        return null;
    }

}
