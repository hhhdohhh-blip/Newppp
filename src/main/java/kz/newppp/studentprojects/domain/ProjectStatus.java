package kz.newppp.studentprojects.domain;

public enum ProjectStatus {
    PLANNED("Запланирован"),
    IN_PROGRESS("В работе"),
    REVIEW("На проверке"),
    COMPLETED("Завершён");

    private final String label;

    ProjectStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
