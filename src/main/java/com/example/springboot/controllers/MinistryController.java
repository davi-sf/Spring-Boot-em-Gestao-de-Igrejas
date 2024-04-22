package com.example.springboot.controllers;

import com.example.springboot.dtos.MinistryRecordDto;
import com.example.springboot.models.MemberModel;
import com.example.springboot.models.MinistryModel;
import com.example.springboot.repositories.MemberRepository;
import com.example.springboot.repositories.MinistryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MinistryController {

    @Autowired
    private MinistryRepository ministryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/ministries")
    public ResponseEntity<MinistryModel> registerMinistry(@RequestBody @Valid MinistryRecordDto ministryDto) {
        var ministryModel = new MinistryModel();
        BeanUtils.copyProperties(ministryDto, ministryModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ministryRepository.save(ministryModel));
    }

    @GetMapping("/ministries")
    public ResponseEntity<List<MinistryModel>> getAllMinistries() {
        List<MinistryModel> ministries = ministryRepository.findAll();
        if (!ministries.isEmpty()) {
            for (MinistryModel ministry : ministries) {
                UUID id = ministry.getId();
                ministry.add(linkTo(methodOn(MinistryController.class).getOneMinistry(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(ministries);
    }

    @GetMapping("/ministries/{id}")
    public ResponseEntity<Object> getOneMinistry(@PathVariable(value = "id") UUID id) {
        Optional<MinistryModel> ministryOpt = ministryRepository.findById(id);
        if (ministryOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ministério não encontrado");
        }
        ministryOpt.get().add(linkTo(methodOn(MinistryController.class).getAllMinistries()).withRel("Lista de ministérios"));
        return ResponseEntity.status(HttpStatus.OK).body(ministryOpt.get());
    }

    @PatchMapping("/ministries/{ministryId}/members/{memberId}")
    public ResponseEntity<Object> addMemberToMinistry(@PathVariable UUID ministryId, @PathVariable UUID memberId) {
        Optional<MinistryModel> ministryOpt = ministryRepository.findById(ministryId);
        if (ministryOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ministério não encontrado");
        }

        Optional<MemberModel> memberOpt = memberRepository.findById(memberId);
        if (memberOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membro não encontrado");
        }

        MinistryModel ministry = ministryOpt.get();
        MemberModel member = memberOpt.get();
        member.setMinistry(ministry);
        memberRepository.save(member);

        return ResponseEntity.status(HttpStatus.OK).body("Membro adicionado ao ministério com sucesso");
    }


    @PutMapping("/ministries/{id}")
    public ResponseEntity<Object> updateMinistry(@PathVariable(value = "id") UUID id, @RequestBody @Valid MinistryRecordDto ministryDto) {
        Optional<MinistryModel> ministryOpt = ministryRepository.findById(id);
        if (ministryOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ministério não encontrado");
        }
        MinistryModel ministryModel = ministryOpt.get();
        BeanUtils.copyProperties(ministryDto, ministryModel);
        return ResponseEntity.status(HttpStatus.OK).body(ministryRepository.save(ministryModel));
    }

    @DeleteMapping("/ministries/{id}")
    public ResponseEntity<Object> deleteMinistry(@PathVariable(value = "id") UUID id) {
        Optional<MinistryModel> ministryOpt = ministryRepository.findById(id);
        if (ministryOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ministério não encontrado");
        }
        ministryRepository.delete(ministryOpt.get());
        return ResponseEntity.status(HttpStatus.OK).body("Ministério deletado");
    }
}
