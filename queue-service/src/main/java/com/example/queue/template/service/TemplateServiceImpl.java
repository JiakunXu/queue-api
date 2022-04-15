package com.example.queue.template.service;

import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.queue.api.QueueTemplateService;
import com.example.queue.template.api.TemplateService;
import com.example.queue.template.api.bo.Template;
import com.example.queue.template.dao.dataobject.TemplateDO;
import com.example.queue.template.dao.mapper.TemplateMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    private static final Logger  logger = LoggerFactory.getLogger(TemplateServiceImpl.class);

    @Autowired
    private QueueTemplateService queueTemplateService;

    @Autowired
    private TemplateMapper       templateMapper;

    @Override
    public int countTemplate(String templateId, String name, Template template) {
        if (template == null) {
            return 0;
        }

        template.setTemplateId(templateId);
        template.setName(name);

        return count(BeanUtil.copy(template, TemplateDO.class));
    }

    @Override
    public List<Template> listTemplates(String templateId, String name, Template template) {
        if (template == null) {
            return null;
        }

        template.setTemplateId(templateId);
        template.setName(name);

        return BeanUtil.copy(list(BeanUtil.copy(template, TemplateDO.class)), Template.class);
    }

    @Override
    public Template getTemplate(BigInteger id) {
        if (id == null) {
            return null;
        }

        TemplateDO templateDO = new TemplateDO();
        templateDO.setId(id);

        return BeanUtil.copy(get(templateDO), Template.class);
    }

    @Override
    public Template insertTemplate(String templateId, Template template, String creator) {
        if (StringUtils.isBlank(templateId) || template == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        template.setTemplateId(templateId);

        TemplateDO templateDO = BeanUtil.copy(template, TemplateDO.class);
        templateDO.setCreator(creator);

        try {
            templateMapper.insert(templateDO);
        } catch (Exception e) {
            logger.error(templateDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        template.setId(templateDO.getId());

        return template;
    }

    @Override
    public Template updateTemplate(BigInteger id, Template template, String modifier) {
        if (id == null || template == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        template.setId(id);

        TemplateDO templateDO = BeanUtil.copy(template, TemplateDO.class);
        templateDO.setModifier(modifier);

        try {
            if (templateMapper.update(templateDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(templateDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return template;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Template deleteTemplate(BigInteger id, String modifier) {
        if (id == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        TemplateDO templateDO = new TemplateDO();
        templateDO.setId(id);
        templateDO.setModifier(modifier);

        try {
            templateMapper.delete(templateDO);
        } catch (Exception e) {
            logger.error(templateDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        queueTemplateService.deleteQueueTemplate(null, id, modifier);

        return BeanUtil.copy(templateDO, Template.class);
    }

    private int count(TemplateDO templateDO) {
        try {
            return templateMapper.count(templateDO);
        } catch (Exception e) {
            logger.error(templateDO.toString(), e);
        }

        return 0;
    }

    private List<TemplateDO> list(TemplateDO templateDO) {
        try {
            return templateMapper.list(templateDO);
        } catch (Exception e) {
            logger.error(templateDO.toString(), e);
        }

        return null;
    }

    private TemplateDO get(TemplateDO templateDO) {
        try {
            return templateMapper.get(templateDO);
        } catch (Exception e) {
            logger.error(templateDO.toString(), e);
        }

        return null;
    }

}
