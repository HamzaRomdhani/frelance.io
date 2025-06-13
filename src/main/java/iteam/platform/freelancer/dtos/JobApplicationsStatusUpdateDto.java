package iteam.platform.freelancer.dtos;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class JobApplicationsStatusUpdateDto {

    public static class JobApplicationStatusDto {
        @NotBlank(message = "Application ID must not be blank")
        private String id;

        @NotBlank(message = "Status must not be blank")
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    private List<JobApplicationStatusDto> applications;

    public List<JobApplicationStatusDto> getApplications() {
        return applications;
    }

    public void setApplications(List<JobApplicationStatusDto> applications) {
        this.applications = applications;
    }
}