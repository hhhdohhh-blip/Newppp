package kz.newppp.studentprojects.web;

import kz.newppp.studentprojects.domain.ProjectStatus;
import kz.newppp.studentprojects.domain.StudentProject;
import kz.newppp.studentprojects.repository.StudentProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Arrays;

@Controller
public class ProjectController {

    private final StudentProjectRepository projects;

    public ProjectController(StudentProjectRepository projects) {
        this.projects = projects;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("projects", projects.findAllByOrderByDeadlineAsc());
        model.addAttribute("statuses", ProjectStatus.values());
        model.addAttribute("total", projects.count());
        model.addAttribute("active", projects.countByStatusIn(Arrays.asList(ProjectStatus.PLANNED, ProjectStatus.IN_PROGRESS, ProjectStatus.REVIEW)));
        model.addAttribute("completed", projects.countByStatus(ProjectStatus.COMPLETED));
        return "index";
    }

    @PostMapping("/projects")
    public String create(
            @RequestParam String title,
            @RequestParam String studentName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) LocalDate deadline,
            RedirectAttributes redirectAttributes) {
        if (title.isBlank() || studentName.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Заполните название проекта и имя студента.");
            return "redirect:/";
        }

        StudentProject project = new StudentProject();
        project.setTitle(title.trim());
        project.setStudentName(studentName.trim());
        project.setDescription(description == null ? "" : description.trim());
        project.setDeadline(deadline);
        projects.save(project);
        redirectAttributes.addFlashAttribute("message", "Проект добавлен.");
        return "redirect:/";
    }

    @PostMapping("/projects/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam ProjectStatus status) {
        projects.findById(id).ifPresent(project -> {
            project.setStatus(status);
            projects.save(project);
        });
        return "redirect:/";
    }

    @PostMapping("/projects/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        projects.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Проект удалён.");
        return "redirect:/";
    }
}
