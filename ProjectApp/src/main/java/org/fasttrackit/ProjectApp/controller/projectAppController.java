package org.fasttrackit.ProjectApp.controller;

import org.fasttrackit.ProjectApp.model.ProjectAppRequest;
import org.fasttrackit.ProjectApp.model.ProjectAppResponse;
import org.fasttrackit.ProjectApp.service.ProjectAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class projectAppController {
    @Autowired
    private ProjectAppService projectAppService;

    @PostMapping({"/authenticate"})
    public ProjectAppResponse createProjectAppToken(@RequestBody ProjectAppRequest projectAppRequest) throws Exception{
       return  projectAppService.createProjectAppToken(projectAppRequest);
    }
}
