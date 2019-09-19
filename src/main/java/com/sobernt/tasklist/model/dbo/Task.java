package com.sobernt.tasklist.model.dbo;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    public String title;
    @Column
    public String description;
    @Column(name = "date_start")
    public Date dateStart;
    @Column(name = "date_end")
    public Date dateEnd;
}
