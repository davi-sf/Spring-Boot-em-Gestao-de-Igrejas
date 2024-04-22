package com.example.springboot.services;

import com.example.springboot.models.MemberModel;
import com.example.springboot.models.MinistryModel;
import com.example.springboot.repositories.MemberRepository;
import com.example.springboot.repositories.MinistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MinistryRepository ministryRepository;

    public void addMemberToMinistry(UUID memberId, UUID ministryId) {
        MemberModel member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EmptyResultDataAccessException("Membro não encontrado", 1));
        MinistryModel ministry = ministryRepository.findById(ministryId)
                .orElseThrow(() -> new EmptyResultDataAccessException("Ministério não encontrado", 1));

        member.setMinistry(ministry);
        memberRepository.save(member);
    }

    public List<MemberModel> getAllMembers() {
        return memberRepository.findAll();
    }




}
