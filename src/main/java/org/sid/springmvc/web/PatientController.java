package org.sid.springmvc.web;

import org.sid.springmvc.dao.PatientRepository;
import org.sid.springmvc.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path = "/index")
    public String index(){
        return "index";
    }

    @GetMapping(path = "/deletePatient")
    public String delete(Long id){
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }

    @GetMapping(path = "/patients")
    public String list(Model model, @RequestParam(name = "page",defaultValue = "0") int page,@RequestParam(name = "size",defaultValue = "5") int size,@RequestParam(name = "clavier",defaultValue = "") String mc){
//        Page<Patient> pagePatients=patientRepository.findAll(PageRequest.of(page,size));
        Page<Patient> pagePatients=patientRepository.findByNameContains(mc,PageRequest.of(page,size));
        model.addAttribute("patients",pagePatients.getContent());
        model.addAttribute("pageCourante",page);
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("keyword",mc);
        model.addAttribute("size",size);
        return "patients";
    }
    @GetMapping(path="/formPatient")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        model.addAttribute("mode","new");
        return "formPatient";
    }

        @PostMapping(path="/savePatient")
    public String savePatient(Model model,@Valid Patient patient, BindingResult bindingResult){
//        BindingResult pour gener les erreur pendant la validation
        if (bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        model.addAttribute("patient",patient);
        return "confirmation";
    }
    @GetMapping(path = "/editPatient")
    public String editPatient(Model model, Long id){
        Patient p=patientRepository.findById(id).get();
        model.addAttribute("patient",p);
        model.addAttribute("mode","edit");
        return "formPatient";
    }

//    @GetMapping("/listPatients")
//    @ResponseBody //bech nraja3 json ,methode 1
//    public List<Patient> list(){
//        return patientRepository.findAll();
//    }
//
//    @GetMapping("/patients/{id}")
//    @ResponseBody //bech nraja3 json ,methode 1
//    public Patient getOne(@PathVariable Long id){
//        return patientRepository.findById(id).get();
//    }



}
