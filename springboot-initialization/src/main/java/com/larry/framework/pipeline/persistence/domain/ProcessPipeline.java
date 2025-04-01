package com.larry.framework.pipeline.persistence.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="PROCESS_PIPELINE")
@BatchSize(size = 1000)
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProcessPipeline {
    private Long processPipelinePK;

}
