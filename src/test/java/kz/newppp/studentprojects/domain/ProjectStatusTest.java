package kz.newppp.studentprojects.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectStatusTest {

    @Test
    void plannedStatusHasRussianLabel() {
        assertEquals("Запланирован", ProjectStatus.PLANNED.getLabel());
    }

    @Test
    void completedStatusHasRussianLabel() {
        assertEquals("Завершён", ProjectStatus.COMPLETED.getLabel());
    }
}
