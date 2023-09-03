package edu.cbsystematics.com.dataspringcafeteriaproject.controller;

import edu.cbsystematics.com.dataspringcafeteriaproject.models.Role;
import edu.cbsystematics.com.dataspringcafeteriaproject.models.Worker;
import edu.cbsystematics.com.dataspringcafeteriaproject.service.CafeteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cafeteria")
public class WorkerController {

    private final CafeteriaService cafeteriaService;

    @Autowired
    public WorkerController(CafeteriaService cafeteriaService) {
        this.cafeteriaService = cafeteriaService;
    }

    @GetMapping("/workers")
    public String showWorkers(Model model) {
        List<Worker> workers = cafeteriaService.getAllWorkers();
        model.addAttribute("workers", workers);
        return "workers";
    }

    @GetMapping("/add")
    public String showAddWorkerForm(Model model) {
        Worker worker = new Worker();
        List<Role> roles = cafeteriaService.getAllRoles();
        model.addAttribute("worker", worker);
        model.addAttribute("roles", roles);
        return "add-worker";
    }

    @PostMapping("/add")
    public String addWorker(@ModelAttribute("worker") Worker worker) {
        cafeteriaService.saveWorker(worker);
        return "redirect:/cafeteria/workers";
    }

    @GetMapping("/workers/{id}")
    public String showWorkerDetails(@PathVariable Long id, Model model) {
        Optional<Worker> worker = cafeteriaService.getWorkerById(id);
        if (worker.isEmpty()) {
            return "redirect:/cafeteria/workers";
        }
        model.addAttribute("worker", worker.get());
        return "worker-details";
    }

    @GetMapping("/workers/{id}/edit")
    public String showEditWorkerForm(@PathVariable Long id, Model model) {
        Worker worker = cafeteriaService.getWorkerById(id).orElse(null);
        List<Role> roles = cafeteriaService.getAllRoles();
        model.addAttribute("worker", worker);
        model.addAttribute("roles", roles);
        return "edit-worker";
    }

    @PostMapping("/workers/{id}/edit")
    public String editWorker(@PathVariable Long id, @ModelAttribute("worker") Worker worker) {
        Worker existingWorker = cafeteriaService.getWorkerById(id).orElse(null);
        if (existingWorker != null) {
            existingWorker.setFirstName(worker.getFirstName());
            existingWorker.setLastName(worker.getLastName());
            existingWorker.setBirthDate(worker.getBirthDate());
            existingWorker.setEmail(worker.getEmail());
            existingWorker.setRole(worker.getRole());

            // Save the updated Worker
            cafeteriaService.saveWorker(existingWorker);
        }
        return "redirect:/cafeteria/workers";
    }

    @GetMapping("/workers/{id}/delete")
    public String deleteWorker(@PathVariable Long id) {
        cafeteriaService.deleteWorker(id);
        return "redirect:/cafeteria/workers";
    }

    @GetMapping("/search")
    public String showSearchForm() {
        return "search-form";
    }

    @GetMapping("/search-results")
    public String searchWorkers(@RequestParam(name = "searchQuery") String query, Model model) {
        List<Worker> searchResults = cafeteriaService.searchByFullName(query);
        if (searchResults.isEmpty()) {
            model.addAttribute("message", "No workers found with the given criteria.");
        } else {
            model.addAttribute("workers", searchResults);
        }
        return "search-results";
    }

}