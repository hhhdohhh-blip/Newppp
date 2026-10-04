package kz.newppp.studentprojects.repository;

import kz.newppp.studentprojects.domain.StudentProject;
import kz.newppp.studentprojects.domain.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentProjectRepository extends JpaRepository<StudentProject, Long> {
    List<StudentProject> findAllByOrderByDeadlineAsc();

    long countByStatusIn(List<ProjectStatus> statuses);

    long countByStatus(ProjectStatus status);
}
