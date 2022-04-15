package com.example.queue.dict.web;

import com.example.queue.dict.api.DictService;
import com.example.queue.dict.api.DictTypeService;
import com.example.queue.dict.api.bo.Dict;
import com.example.queue.dict.api.bo.DictType;
import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ListResponse;
import com.example.queue.framework.response.ObjectResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

/**
 * @author JiakunXu
 */
@RestController
@RequestMapping(value = "/api/dict")
public class DictController extends BaseController {

    @Autowired
    private DictTypeService dictTypeService;

    @Autowired
    private DictService     dictService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ListResponse<Dict> listDicts(HttpServletRequest request, HttpServletResponse response) {
        String dictTypeId = this.getParameter(request, "typeId");
        String dictTypeValue = this.getParameter(request, "type");
        return new ListResponse<>(
            StringUtils.isNotBlank(dictTypeId) ? dictService.listDicts(new BigInteger(dictTypeId))
                : dictService.listDicts(dictTypeValue));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ObjectResponse<Dict> get(HttpServletRequest request, HttpServletResponse response) {
        String dictTypeValue = this.getParameter(request, "type");
        String value = this.getParameter(request, "value");
        return new ObjectResponse<>(dictService.getDict(dictTypeValue, value));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ObjectResponse<Dict> save(HttpServletRequest request, HttpServletResponse response) {
        Dict dict = this.getParameter(request, Dict.class);

        DictType dictType = dictTypeService.getDictType(dict.getDictTypeId());
        if (dictType != null) {
            dict.setDictTypeValue(dictType.getValue());
        }

        return new ObjectResponse<>(dictService.insertDict(
            dictType == null ? null : dictType.getId(), dict, this.getUser().getName()));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Dict> update(HttpServletRequest request, HttpServletResponse response) {
        Dict dict = this.getParameter(request, Dict.class);
        return new ObjectResponse<>(
            dictService.updateDict(dict.getId(), dict, this.getUser().getName()));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ObjectResponse<Dict> remove(HttpServletRequest request, HttpServletResponse response) {
        Dict dict = this.getParameter(request, Dict.class);
        return new ObjectResponse<>(
            dictService.deleteDict(null, dict.getId(), this.getUser().getName()));
    }

}
