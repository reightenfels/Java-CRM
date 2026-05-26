package com.dev.crm.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="task_dialog")
@Getter
@Setter
public class TaskDialog {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="task_id", nullable=false)
    private Task task;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="avatar_id", nullable=false)
    private Attachment avatar;

    @OneToMany(mappedBy="dialog", fetch=FetchType.LAZY)
    private List<TaskDialogMessage> messages;
}
