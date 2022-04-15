package com.example.queue.member.api;

import com.example.queue.member.api.bo.Member;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JiakunXu
 */
public interface MemberService {

    /**
     * 
     * @param nickname
     * @param member
     * @return
     */
    int countMember(String nickname, Member member);

    /**
     *
     * @param nickname
     * @param member
     * @return
     */
    List<Member> listMembers(String nickname, Member member);

    /**
     * 
     * @param openid
     * @return
     */
    Member getMember(String openid);

    /**
     * 
     * @param member
     * @param creator
     * @return
     */
    Member insertMember(Member member, String creator);

    /**
     * 
     * @param id
     * @param member
     * @param modifier
     * @return
     */
    Member updateMember(BigInteger id, Member member, String modifier);

}
