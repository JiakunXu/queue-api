package com.example.queue.template.api;

import com.example.queue.template.api.bo.Template;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface TemplateService {

    /**
     *
     * @param templateId
     * @param name
     * @param template
     * @return
     */
    int countTemplate(String templateId, String name, Template template);

    /**
     * 
     * @param templateId
     * @param name
     * @param template
     * @return
     */
    List<Template> listTemplates(String templateId, String name, Template template);

    /**
     * 
     * @param id
     * @return
     */
    Template getTemplate(BigInteger id);

    /**
     *
     * @param templateId
     * @param template
     * @param creator
     * @return
     */
    Template insertTemplate(String templateId, Template template, String creator);

    /**
     *
     * @param id
     * @param template
     * @param modifier
     * @return
     */
    Template updateTemplate(BigInteger id, Template template, String modifier);

    /**
     * 
     * @param id
     * @param modifier
     * @return
     */
    Template deleteTemplate(BigInteger id, String modifier);

}
