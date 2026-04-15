package com.dev.crm.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="personal_employee_dialog")
@Getter
@Setter
public class PersonalEmployeeDialog {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="companion_1", nullable=false)
    private User companion1;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="companion_2", nullable=false)
    private User companion2;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="avatar_id", nullable=false)
    private Attachment avatar;
}
