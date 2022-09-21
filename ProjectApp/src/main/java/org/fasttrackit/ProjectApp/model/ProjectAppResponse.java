package org.fasttrackit.ProjectApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProjectAppResponse {

    private User user;
    private String projectAppToken;

}
