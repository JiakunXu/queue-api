package com.example.queue.dict.web;

import com.example.queue.dict.api.DictTypeService;
import com.example.queue.dict.api.bo.DictType;
import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.framework.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JiakunXu
 */
@RestController
@RequestMapping(value = "/api/dict/type")
public class DictTypeController extends BaseController {

    @Autowired
    private DictTypeService dictTypeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<DictType> list(HttpServletRequest request, HttpServletResponse response) {
        String name = this.getParameter(request, "name");
        String value = this.getParameter(request, "value");
        DictType dictType = this.getParameter(request, new DictType());

        int count = dictTypeService.countDictType(name, value, dictType);

        if (count == 0) {
            return new PageResponse<>(dictType.getPageNo(), dictType.getPageSize(), 0, null);
        }

        return new PageResponse<>(dictType.getPageNo(), dictType.getPageSize(), count,
            dictTypeService.listDictTypes(name, value, dictType));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ObjectResponse<DictType> get(HttpServletRequest request, HttpServletResponse response) {
        String value = this.getParameter(request, "type");
        return new ObjectResponse<>(dictTypeService.getDictType(value));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<DictType> save(HttpServletRequest request, HttpServletResponse response) {
        DictType dictType = this.getParameter(request, DictType.class);
        return new ObjectResponse<>(
            dictTypeService.insertDictType(dictType, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<DictType> update(HttpServletRequest request,
                                           HttpServletResponse response) {
        DictType dictType = this.getParameter(request, DictType.class);
        return new ObjectResponse<>(
            dictTypeService.updateDictType(dictType.getId(), dictType, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<DictType> remove(HttpServletRequest request,
                                           HttpServletResponse response) {
        DictType dictType = this.getParameter(request, DictType.class);
        return new ObjectResponse<>(
            dictTypeService.deleteDictType(dictType.getId(), this.getUser().getName()));
    }

}
