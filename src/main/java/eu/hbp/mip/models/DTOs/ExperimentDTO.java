package eu.hbp.mip.models.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import eu.hbp.mip.models.DAOs.ExperimentDAO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExperimentDTO {

    private UUID uuid;
    private String name;
    private String createdBy;
    private Date created;
    private Date updated;
    private Date finished;
    private Boolean shared;
    private Boolean viewed;
    private List<ExperimentDTO.ResultDTO> result;
    private ExperimentDAO.Status status;

    private AlgorithmDTO algorithm;

    public ExperimentDTO() {
    }

    public AlgorithmDTO getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(AlgorithmDTO algorithm) {
        this.algorithm = algorithm;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

    public List<ExperimentDTO.ResultDTO> getResult() {
        return result;
    }

    public void setResult(List<ExperimentDTO.ResultDTO> result) {
        this.result = result;
    }

    public ExperimentDAO.Status getStatus() {
        return status;
    }

    public void setStatus(ExperimentDAO.Status status) {
        this.status = status;
    }

    public static class ResultDTO {

        private Object data;
        private Object type;

        public Object getData() {
            return this.data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }
    }
}
