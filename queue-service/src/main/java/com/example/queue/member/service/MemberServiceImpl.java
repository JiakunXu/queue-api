package com.example.queue.member.service;

import com.example.queue.framework.constant.Constants;
import com.example.queue.framework.exception.ServiceException;
import com.example.queue.framework.util.BeanUtil;
import com.example.queue.member.api.MemberService;
import com.example.queue.member.api.bo.Member;
import com.example.queue.member.dao.dataobject.MemberDO;
import com.example.queue.member.dao.mapper.MemberMapper;
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
public class MemberServiceImpl implements MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberMapper        memberMapper;

    @Override
    public int countMember(String nickname, Member member) {
        if (member == null) {
            return 0;
        }

        member.setNickname(nickname);

        return count(BeanUtil.copy(member, MemberDO.class));
    }

    @Override
    public List<Member> listMembers(String nickname, Member member) {
        if (member == null) {
            return null;
        }

        member.setNickname(nickname);

        return BeanUtil.copy(list(BeanUtil.copy(member, MemberDO.class)), Member.class);
    }

    @Override
    public Member getMember(String openid) {
        if (StringUtils.isBlank(openid)) {
            return null;
        }

        MemberDO memberDO = new MemberDO();
        memberDO.setOpenid(openid);

        return BeanUtil.copy(get(memberDO), Member.class);
    }

    @Override
    public Member insertMember(Member member, String creator) {
        if (member == null || StringUtils.isBlank(creator)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        MemberDO memberDO = BeanUtil.copy(member, MemberDO.class);
        memberDO.setCreator(creator);

        try {
            memberMapper.insert(memberDO);
        } catch (Exception e) {
            logger.error(memberDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息创建失败，请稍后再试");
        }

        member.setId(memberDO.getId());

        return member;
    }

    @Override
    public Member updateMember(BigInteger id, Member member, String modifier) {
        if (id == null || member == null || StringUtils.isBlank(modifier)) {
            throw new ServiceException(Constants.MISSING_PARAMETER, "参数信息不能为空");
        }

        member.setId(id);

        MemberDO memberDO = BeanUtil.copy(member, MemberDO.class);
        memberDO.setModifier(modifier);

        try {
            if (memberMapper.update(memberDO) != 1) {
                throw new ServiceException(Constants.BUSINESS_FAILED);
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息不存在");
        } catch (Exception e) {
            logger.error(memberDO.toString(), e);
            throw new ServiceException(Constants.BUSINESS_FAILED, "信息更新失败，请稍后再试");
        }

        return member;
    }

    private int count(MemberDO memberDO) {
        try {
            return memberMapper.count(memberDO);
        } catch (Exception e) {
            logger.error(memberDO.toString(), e);
        }

        return 0;
    }

    private List<MemberDO> list(MemberDO memberDO) {
        try {
            return memberMapper.list(memberDO);
        } catch (Exception e) {
            logger.error(memberDO.toString(), e);
        }

        return null;
    }

    private MemberDO get(MemberDO memberDO) {
        try {
            return memberMapper.get(memberDO);
        } catch (Exception e) {
            logger.error(memberDO.toString(), e);
        }

        return null;
    }

}
