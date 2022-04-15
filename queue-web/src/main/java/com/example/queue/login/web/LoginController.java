package com.example.queue.login.web;

import com.alibaba.fastjson.JSONObject;
import com.example.queue.cache.api.RedisService;
import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.DataResponse;
import com.example.queue.user.api.UserService;
import com.example.queue.user.api.bo.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author JiakunXu
 */
@RestController
@RequestMapping(value = "/api")
public class LoginController extends BaseController {

    @Autowired
    private RedisService<String, Object> redisService;

    @Autowired
    private UserService                  userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DataResponse<Data> save(HttpServletRequest request, HttpServletResponse response) {
        JSONObject parameter = this.getParameter(request, JSONObject.class);
        String passport = parameter == null ? null : parameter.getString("passport");
        String password = parameter == null ? null : parameter.getString("password");

        DataResponse<Data> dataResponse = new DataResponse<>();

        try {
            User user = userService.getUser(passport, password);

            if (user == null) {
                dataResponse.setCode(-1);
                return dataResponse;
            }

            Data data = new Data();
            data.setUser(user);
            data.setToken(getToken(user));

            dataResponse.setData(data);
        } catch (Exception e) {
            dataResponse.setCode(-1);
            dataResponse.setError("");
            dataResponse.setMessage(e.getMessage());
        }

        return dataResponse;
    }

    /**
     *
     * @param user
     * @return
     */
    private String getToken(User user) {
        String key = RedisService.CACHE_KEY_USER_ID + user.getId();
        String token = (String) redisService.get(key);

        if (StringUtils.isBlank(token)) {
            token = UUID.randomUUID().toString();
            redisService.set(key, token);
            redisService.add(token, user);
        } else {
            redisService.set(token, user);
        }

        return token;
    }

    @Getter
    @Setter
    private static class Data implements Serializable {

        private static final long serialVersionUID = -674008207143289016L;

        private User              user;

        private String            token;

    }

}
