package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class HomeController {

    private final UserService userService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;
    private final FileService fileService;

    public HomeController(UserService userService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService, FileService fileService) {
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.fileService = fileService;
    }


    @GetMapping("/home")
    public String homeView(Model model, Authentication auth, EncryptionService encryptionService) {
        User user = userService.getUser(auth.getName());
        model.addAttribute("notes", this.noteService.getNotes(user.getUserId()));
        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("files", this.fileService.getFiles(user.getUserId()));
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
        //return "home";
        model.addAttribute("success", "true");
        model.addAttribute("link", "/home");
        return "result";
    }

    @GetMapping("/file/delete/{fileId}")
    public String deleteFile(Model model, @PathVariable(value = "fileId") Integer fileId, Authentication auth) {
        User user = userService.getUser(auth.getName());
        this.fileService.deleteFile(fileId);
        model.addAttribute("files", this.fileService.getFiles(user.getUserId()));
        model.addAttribute("success", "true");
        model.addAttribute("link", "/home");
        return "result";
    }

    @GetMapping("/credential/delete/{credentialId}")
    public String deleteCredential(Model model, @PathVariable(value = "credentialId") Integer credentialId, Authentication auth) {
        User user = userService.getUser(auth.getName());
        this.credentialService.deleteCredential(credentialId);
        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        //return "redirect:/home";
        model.addAttribute("success", "true");
        model.addAttribute("link", "/home");
        return "result";
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
        //return "redirect:/home";
        model.addAttribute("success", "true");
        model.addAttribute("link", "/home");
        return "result";
    }

    @PostMapping("/file")
    public String postFile(FileForm fileForm, Model model, Authentication auth, @RequestParam("fileUpload") MultipartFile fileUpload) throws IOException {
        User user = userService.getUser(auth.getName());
        fileForm.setUserId(user.getUserId().toString());
        fileForm.setFileData(fileUpload.getBytes());
        fileForm.setFileSize(Long.toString(fileUpload.getSize()));
        fileForm.setFileName(fileUpload.getName());
        fileForm.setContentType(fileUpload.getContentType());
        if(fileForm.getFileId() == "") {
            this.fileService.addFile(fileForm);
        } else {
            this.fileService.updateFile(fileForm);
        }
        fileForm.setContentType("");
        fileForm.setFileName("");
        fileForm.setFileSize("");
        model.addAttribute("success", "true");
        model.addAttribute("link", "/home");
        return "result";
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
        credentialForm.setUserName("");
        credentialForm.setPassword("");
        credentialForm.setUrl("");
        model.addAttribute("credentials", this.credentialService.getCredentials(user.getUserId()));
        //return "redirect:/home";
        model.addAttribute("success", "true");
        model.addAttribute("link", "/home");
        return "result";
    }
}
