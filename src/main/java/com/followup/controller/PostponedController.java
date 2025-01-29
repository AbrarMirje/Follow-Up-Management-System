package com.followup.controller;

import com.followup.Response.JsonDeleteResponse;
import com.followup.Response.JsonResponse;
import com.followup.entity.Postponed;
import com.followup.service.IPostponedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/postponed")
@Tag(name = "Postponed APIs")
@CrossOrigin("*")
public class PostponedController {

    private final IPostponedService postponedService;

    @PostMapping("/add-postponed")
    public ResponseEntity<?> addPostponed(@Valid @RequestBody Postponed postponed){
        Postponed added = postponedService.addPostponed(postponed);
        return ResponseEntity.ok(added);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePostponedById(@PathVariable Long id){
        Boolean isDeleted = postponedService.deletePostponed(id);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new JsonDeleteResponse( true,"Postponed Deleted"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonDeleteResponse( false,"Something went wrong!!"));

        }
    }
}
