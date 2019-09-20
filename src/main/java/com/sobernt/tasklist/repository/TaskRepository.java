package com.sobernt.tasklist.repository;

import com.sobernt.tasklist.model.dbo.Task;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task getFirstById(Long id);

    @Query( value = "SELECT * FROM task t WHERE t.date_start >= :dateStart AND t.date_start<=:dateEnd", nativeQuery = true)
    List<Task> findAllByDates(@Param("dateStart") Date dateStart,@Param("dateEnd") Date dateEnd);

    @Query( value = "SELECT * FROM task t WHERE (t.date_start < :dateStart AND t.date_end>:dateStart) OR (t.date_start < :dateEnd AND t.date_end> :dateEnd)", nativeQuery = true)
    List<Task> getTasksOverlapping(@Param("dateStart") Date dateStart,@Param("dateEnd") Date dateEnd);

    @Query( value = "SELECT COUNT(*) FROM task t WHERE (t.date_start <= :dateStart AND t.date_end>=:dateStart) " +
            "OR (t.date_start <= :dateEnd AND t.date_end>=:dateEnd)", nativeQuery = true)
    Integer getTasksOverlappingCount(@Param("dateStart") Date dateStart,@Param("dateEnd") Date dateEnd);

    @Query( value = "SELECT COUNT(*) FROM task t WHERE ((t.date_start <= :dateStart AND t.date_end>=:dateStart) " +
            "OR (t.date_start <= :dateEnd AND t.date_end>=:dateEnd)) AND t.id <> :Id", nativeQuery = true)
    Integer getTasksOverlappingCount(@Param("dateStart") Date dateStart,
                                     @Param("dateEnd") Date dateEnd,
                                     @Param("Id")Long taskId);
}
