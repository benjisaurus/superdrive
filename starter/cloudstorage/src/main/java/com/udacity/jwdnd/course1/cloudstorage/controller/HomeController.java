package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;

@Controller
public class HomeController {

    private final UserService userService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public HomeController(UserService userService, NoteService noteService, CredentialService credentialService) {
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }


    @GetMapping("/home")
    public String homeView(Model model, Authentication auth) {
        User user = userService.getUser(auth.getName());
        model.addAttribute("notes", this.noteService.getNotes(user.getUserId()));
        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        return "home";
    }

    @PostMapping("/home")
    public String postHome(Model model) {
        return "home";
    }

    @GetMapping("/note/delete/{noteId}")
    public String deleteNote(Model model, @PathVariable(value = "noteId") Integer noteId, Authentication auth) {
        User user = userService.getUser(auth.getName());
        this.noteService.deleteNote(noteId);
        model.addAttribute("notes", this.noteService.getNotes(user.getUserId()));
        return "home";
    }

    @GetMapping("/credential/delete/{credentialId}")
    public String deleteCredential(Model model, @PathVariable(value = "credentialId") Integer credentialId, Authentication auth) {
        User user = userService.getUser(auth.getName());
        this.credentialService.deleteCredential(credentialId);
        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        return "home";
    }

    @PostMapping("/note")
    public String postNote(NoteForm noteForm, Model model, Authentication auth) {
        User user = userService.getUser(auth.getName());
        noteForm.setUserId(user.getUserId().toString());
        if(noteForm.getNoteId() == "") {
            this.noteService.addNote(noteForm);
        } else {
            this.noteService.updateNote(noteForm);
        }
        noteForm.setNoteDescription("");
        noteForm.setNoteTitle("");
        model.addAttribute("notes", this.noteService.getNotes(user.getUserId()));
        return "home";
    }

    @PostMapping("/credential")
    public String postCredential(CredentialForm credentialForm, Model model, Authentication auth) {
        User user = userService.getUser(auth.getName());
        credentialForm.setUserId(user.getUserId().toString());
        if(credentialForm.getCredentialId() == "") {
            this.credentialService.addCredential(credentialForm);
        } else {
            this.credentialService.updateCredential(credentialForm);
        }
        credentialForm.setCredentialUserName("");
        credentialForm.setCredentialPassword("");
        credentialForm.setCredentialUrl("");
        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        return "home";
    }
}
