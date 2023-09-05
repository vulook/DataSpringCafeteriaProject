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

    @GetMapping("/search-role")
    public String showSearchFormRole() {
        return "role-form";
    }

    @GetMapping("/role-details")
    public String getRoleByName(@RequestParam String role, Model model) {
        Optional<Role> roleOptional = cafeteriaService.findRoleByName(role);
        if (roleOptional.isPresent()) {
            // Role found
            Role foundRole = roleOptional.get();
            model.addAttribute("role", foundRole);

            // Count workers for the found role
            int roleCount = cafeteriaService.countWorkersByRole(foundRole.getRoleName());
            model.addAttribute("roleCount", roleCount);

            // Get the role ID
            Long roleId = foundRole.getId();
            model.addAttribute("roleId", roleId);

        } else {
            // Role not found
            model.addAttribute("errorMessage", "Role " + role + " not found.");
        }

        return "role-details";
    }

    @GetMapping("/workers-by-role")
    public String searchWorkersByRole(@RequestParam(name = "roleId") Long roleId, Model model) {
        List<Worker> workers = cafeteriaService.getWorkersByRoleId(roleId);
        model.addAttribute("workers", workers);
        return "search-results";
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
        cafeteriaService.updateWorkerInfo(id, worker.getFirstName(), worker.getLastName(), worker.getEmail(), worker.getBirthDate(), worker.getRole());
        return "redirect:/cafeteria/workers";
    }

    @GetMapping("/workers/{id}/delete")
    public String deleteWorker(@PathVariable Long id) {
        cafeteriaService.deleteWorker(id);
        return "redirect:/cafeteria/workers";
    }

    @GetMapping("/search-worker")
    public String showSearchFormWorker() {
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