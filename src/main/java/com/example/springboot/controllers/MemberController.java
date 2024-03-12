package com.example.springboot.controllers;

import com.example.springboot.dtos.MemberRecordDto;
import com.example.springboot.models.MemberModel;
import com.example.springboot.repositories.MemberRespository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MemberController  {

    @Autowired
    MemberRespository memberRespository;

    @PostMapping("/members")
    public ResponseEntity<MemberModel> registerMember(@RequestBody @Valid MemberRecordDto memberRecordDto){
        var memberModel = new MemberModel();
        BeanUtils.copyProperties(memberRecordDto, memberModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberRespository.save(memberModel));

    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberModel>> getAllMembers(){
        List<MemberModel> membersList = memberRespository.findAll();
        if(!membersList.isEmpty()){
            for(MemberModel member : membersList){
                UUID id = member.getIdMember();
                member.add(linkTo(methodOn(MemberController.class).getOneMember(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(memberRespository.findAll());

    }


    @GetMapping("/members/{id}")
    public ResponseEntity<Object>getOneMember(@PathVariable(value = "id")UUID id){
        Optional<MemberModel> member0 = memberRespository.findById(id);
        if(member0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro não encontrado");
        }
        member0.get().add(linkTo(methodOn(MemberController.class).getAllMembers()).withRel("Lista de membros"));
        return ResponseEntity.status(HttpStatus.OK).body(member0.get());
    }


    @PutMapping("/members/{id}")
    public ResponseEntity<Object> updateMember (@PathVariable(value = "id")UUID id, @RequestBody @Valid MemberRecordDto memberRecordDto){
        Optional<MemberModel> member0 = memberRespository.findById(id);
        if(member0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro não encontrado");
        }

        var memberModel = member0.get();
        BeanUtils.copyProperties(memberRecordDto, memberModel);
        return ResponseEntity.status(HttpStatus.OK).body(memberRespository.save(memberModel));
    }


    @DeleteMapping("/members/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id")UUID id){
        Optional<MemberModel> memberO = memberRespository.findById(id);
        if(memberO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro não encontrado");

        }

        memberRespository.delete(memberO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Membro deletado");


    }

    @GetMapping("/upcoming-birthdays")
    public ResponseEntity<List<MemberModel>> getUpcomingBirthdays() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusDays(7);
        LocalDate endDate = currentDate.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String startDateString = startDate.format(formatter);
        String endDateString = endDate.format(formatter);

        List<MemberModel> members = memberRespository.findByBirthBetween(startDateString, endDateString);

        return ResponseEntity.status(HttpStatus.OK).body(members);
    }


}
