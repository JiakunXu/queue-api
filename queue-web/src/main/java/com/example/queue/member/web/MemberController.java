package com.example.queue.member.web;

import com.example.queue.framework.web.BaseController;
import com.example.queue.framework.response.ObjectResponse;
import com.example.queue.framework.response.PageResponse;
import com.example.queue.member.api.MemberService;
import com.example.queue.member.api.bo.Member;
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
@RequestMapping(value = "/api/member")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<Member> list(HttpServletRequest request, HttpServletResponse response) {
        String nickname = this.getParameter(request, "nickname");
        Member member = this.getParameter(request, new Member());

        int count = memberService.countMember(nickname, member);

        if (count == 0) {
            return new PageResponse<>(member.getPageNo(), member.getPageSize(), 0, null);
        }

        return new PageResponse<>(member.getPageNo(), member.getPageSize(), count,
            memberService.listMembers(nickname, member));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectResponse<Member> update(HttpServletRequest request, HttpServletResponse response) {
        Member member = this.getParameter(request, Member.class);
        return new ObjectResponse<>(
            memberService.updateMember(member.getId(), member, this.getUser().getName()));
    }

}
