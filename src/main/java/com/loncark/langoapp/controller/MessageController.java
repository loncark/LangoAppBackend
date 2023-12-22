package com.loncark.langoapp.controller;

import com.loncark.langoapp.domain.Message;
import com.loncark.langoapp.dto.MessageDTO;
import com.loncark.langoapp.dto.UserDTO;
import com.loncark.langoapp.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping
    public List<MessageDTO> getAll() {
        return messageService.findAll();
    }

    @GetMapping("{id}")
    public MessageDTO getById(@PathVariable final String id) {
        return messageService.findById(Long.parseLong(id))
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message was not found by that id")
                );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MessageDTO save(@Valid @RequestBody final Message message) {
        return messageService.save(message)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "A message with the same id already exists"));
    }

    @PutMapping
    public MessageDTO update(@Valid @RequestBody final Message message) {
        return messageService.save(message)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A message was not found by that id"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        messageService.deleteById(Long.parseLong(id));
    }

    @GetMapping(params = {"senderId", "receiverId"})
    public List<MessageDTO> getByLanguage(@RequestParam final String senderId, @RequestParam final String receiverId) {
        return messageService.findBySenderIdAndReceiverId(Long.parseLong(senderId), Long.parseLong(receiverId));
    }
}
